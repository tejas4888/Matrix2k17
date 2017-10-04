/*
 * *
 *  * This file is part of Matrix2017
 *  * Created for the annual technical festival of Sardar Patel Institute of Technology
 *  *
 *  * The original contributors of the software include:
 *  * - Adnan Ansari (psyclone20)
 *  * - Tejas Bhitle (TejasBhitle)
 *  * - Mithil Gotarne (mithilgotarne)
 *  * - Rohit Nahata (rohitnahata)
 *  * - Akshay Shah (akshah1997)
 *  *
 *  * Matrix2017 is free software: you can redistribute it and/or modify
 *  * it under the terms of the MIT License as published by the Massachusetts Institute of Technology
*/

package spit.vortex17.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import spit.vortex17.Activities.EventDetails;
import spit.vortex17.HelperClasses.Event;
import spit.vortex17.HelperClasses.EventListAdapter;
//import spit.matrix2017.HelperClasses.MatrixContentProvider;
import spit.vortex17.HelperClasses.RecyclerItemClickListener;
import spit.vortex17.R;





public class EventListFragment extends Fragment{
    RecyclerView mRecyclerView;
    //MatrixContentProvider matrixContentProvider;
    private String category ="";


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mItemDatabaseReference;
    private ValueEventListener mValueEventListener;

    private EventListAdapter evl;

    private ArrayList<Event> mEvents;
    private ProgressBar pg;

    public static EventListFragment newInstance(String category){
        EventListFragment fragment = new EventListFragment();
        Log.i("ViewPagerFragment ",category);
        Bundle bundle = new Bundle();
        bundle.putString("data",category);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        Bundle bundle = this.getArguments();
        if(bundle != null)
            category = bundle.getString("data");

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mItemDatabaseReference = mFirebaseDatabase.getReference().child("Events").child(category);
        mEvents = new ArrayList<Event>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_layout,container,false);
        //final MatrixContentProvider.MatrixDBConnectionHelper dbConnectionHelper;
        //matrixContentProvider=new MatrixContentProvider();
        //dbConnectionHelper = new MatrixContentProvider().new MatrixDBConnectionHelper(getContext());

        pg = (ProgressBar) view.findViewById(R.id.mypg);

        ConnectivityManager conMan = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        //mobile
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        //wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        if (mobile == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTED) {

        } else  {
            try {
                Snackbar.make(getActivity().findViewById(R.id.navigation_view),"No Internet Connection",Snackbar.LENGTH_LONG).show();
            }catch (Exception e)
            {           }

            Toast.makeText(getActivity(),"Unable to fetch latest data",Toast.LENGTH_SHORT).show();
            pg.setVisibility(View.GONE);
        }


        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragmentRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());





        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever

                        Event event = mEvents.get(position);

                        Intent i = new Intent(getContext(), EventDetails.class);

                        i.putExtra("image",event.getPosterUrl());
                        i.putExtra("name", event.getName());
                        i.putExtra("description", event.getDescription());
                        i.putExtra("venue", event.getVenue());
                        i.putExtra("time", event.getTime());
                        i.putExtra("registration", event.getFeeScheme());
                        i.putExtra("prizes", event.getPrizeScheme());
                        i.putExtra("contact1name", event.getPocName1());
                        i.putExtra("contact1no", event.getPocNumber1());
                        i.putExtra("contact2name", event.getPocName2());
                        i.putExtra("contact2no", event.getPocNumber2());
                        i.putExtra("dates",event.getDates());
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                            ImageView poster = (ImageView)view.findViewById(R.id.thumbnail);
                            poster.setTransitionName("poster");
                            Pair pair = new Pair<>(poster, ViewCompat.getTransitionName(poster));

                            ActivityOptionsCompat optionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),pair);
                            ActivityCompat.startActivity(getActivity(),i,optionsCompat.toBundle());


                        }
                        else{}
                            //getContext().startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })

        );


        EventListFragment.FetchEventList fel = new EventListFragment.FetchEventList();
        fel.execute();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Fragment attached",category);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Fragment detached",category);
    }

    public void updateUI(){
        evl = new EventListAdapter(getContext(),mEvents);
        mRecyclerView.setAdapter(evl);
        mRecyclerView.scrollToPosition(0);
        pg.setVisibility(View.GONE);
    }

    public class FetchEventList extends AsyncTask<Void,Void,ArrayList<Event>> {

        @Override
        protected void onPreExecute() {
            //bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Event> doInBackground(Void... params) {

            mValueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mEvents.clear();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        String name = (String) snapshot.child("name").getValue();
                        if(name == null) {
                            break;
                        }
                        String description =  (String) snapshot.child("description").getValue();
                        String posterUrl = (String) snapshot.child("posterUrl").getValue();
                        String dates = (String) snapshot.child("dates").getValue();
                        String time = (String) snapshot.child("time").getValue();
                        String venue = (String) snapshot.child("venue").getValue();
                        String orgMail = (String) snapshot.child("eventOrgMail").getValue();
                        String pocName1 = (String) snapshot.child("pocName1").getValue();
                        String pocName2 = (String) snapshot.child("pocName2").getValue();
                        String pocNumber1 = (String) snapshot.child("pocNumber1").getValue();
                        String pocNumber2 = (String) snapshot.child("pocNumber2").getValue();
                        String prizeScheme = (String) snapshot.child("prizeScheme").getValue();
                        String fees = (String) snapshot.child("feeScheme").getValue(); //Calculated per person

                        mEvents.add(new Event(name,description,posterUrl,dates,time,venue,orgMail,pocName1,
                                                pocName2,pocNumber1,pocNumber2,prizeScheme,fees));
                    }
                    updateUI();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            mItemDatabaseReference.addValueEventListener(mValueEventListener);
            return null;
        }
    }
}

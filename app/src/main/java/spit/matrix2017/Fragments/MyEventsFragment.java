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

package spit.matrix2017.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import spit.matrix2017.Activities.AddEvent;
import spit.matrix2017.Activities.MainActivity;
import spit.matrix2017.HelperClasses.Event;
import spit.matrix2017.HelperClasses.EventListAdapter;
import spit.matrix2017.HelperClasses.MyEventAdapter;
import spit.matrix2017.HelperClasses.SponsorRecyclerAdapter;
import spit.matrix2017.R;

public class MyEventsFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Event> mEvents ;
    SharedPreferences userInfo;
    String email;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mItemDatabaseReference;
    private ValueEventListener mValueEventListener;


    public MyEventsFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myevents,container,false);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mItemDatabaseReference = mFirebaseDatabase.getReference().child("Events");

        userInfo = this.getActivity().getSharedPreferences("userInfo", Context.MODE_APPEND);
        //email = userInfo.getString("email","xyz@gmail.com");

        email = MainActivity.Email;

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclermyEvents);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.hasFixedSize();
        recyclerView.setNestedScrollingEnabled(false);
        mEvents = new ArrayList<Event>();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.eventFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),AddEvent.class);
                startActivity(i);
            }
        });

        MyEventsFragment.FetchMyEventList fml = new MyEventsFragment.FetchMyEventList();
        fml.execute();


        return view;
    }


    public void updateUI(){
        final EventListAdapter evl = new EventListAdapter(getContext(),mEvents);
        //final MyEventAdapter evl=new MyEventAdapter(mEvents,getContext());
        recyclerView.setAdapter(evl);
        Log.v("Recycler","Hello");
        recyclerView.scrollToPosition(0);
    }



    public class FetchMyEventList extends AsyncTask<Void,Void,ArrayList<Event>> {

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
                        String dname = (String) snapshot.child("name").getValue();
                        if(dname == null) {
                            break;
                        }
                        String org = (String) snapshot.child("eventOrgMail").getValue();
                        if(email.equals(org)){
                            String description = (String) snapshot.child("description").getValue();
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
                            String fees = (String) snapshot.child("fees").getValue(); //Calculated per person

                            mEvents.add(new Event(dname, description, posterUrl, dates, time, venue,orgMail ,pocName1,
                                    pocName2, pocNumber1, pocNumber2, prizeScheme, fees));
                        }
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

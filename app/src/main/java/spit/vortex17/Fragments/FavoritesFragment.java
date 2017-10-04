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
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import spit.vortex17.HelperClasses.Event;
//import spit.matrix2017.HelperClasses.MatrixContentProvider;
import spit.vortex17.HelperClasses.Registration;
import spit.vortex17.HelperClasses.RegistrationAdapter;
import spit.vortex17.HelperClasses.ScheduleAdapter;
import spit.vortex17.R;

public class FavoritesFragment extends Fragment {

    RecyclerView mRecyclerView;
    ProgressBar pg;

    ArrayList<String> days;
    //Comment

    public FavoritesFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_layout,container,false);

        pg = (ProgressBar) view.findViewById(R.id.mypg);
        pg.setVisibility(View.GONE);

        days = new ArrayList<String>();
        days.add("Day 1");
        days.add("Day 2");
        days.add("Day 3");
        days.add("Day 4");

        Integer[] x = {R.drawable.sch_1,R.drawable.sch_2,R.drawable.sch_3,R.drawable.sch_4};

        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragmentRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(new ScheduleAdapter(days,x));


        return view;
    }


}
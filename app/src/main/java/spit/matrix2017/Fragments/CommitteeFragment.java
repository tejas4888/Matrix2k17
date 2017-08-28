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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import spit.matrix2017.R;

public class CommitteeFragment
        extends Fragment{


    ImageView cp,vcp,tech,hoes,creative,admin,sec,hop;

    public CommitteeFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_committee2,container,false);

        cp = (ImageView)view.findViewById(R.id.mcp);
        vcp = (ImageView)view.findViewById(R.id.mvcp);
        tech = (ImageView)view.findViewById(R.id.mtech);
        hoes = (ImageView)view.findViewById(R.id.mhoes);
        creative = (ImageView)view.findViewById(R.id.mcreative);
        admin = (ImageView)view.findViewById(R.id.madmin);
        sec = (ImageView)view.findViewById(R.id.msecurity);
        hop = (ImageView)view.findViewById(R.id.mhop);

        Picasso.with(getContext()).load(R.drawable.mcp).into(cp);
        Picasso.with(getContext()).load(R.drawable.matrixvcps).into(vcp);
        Picasso.with(getContext()).load(R.drawable.mtech).into(tech);
        Picasso.with(getContext()).load(R.drawable.mhoes).into(hoes);
        Picasso.with(getContext()).load(R.drawable.mcreative).into(creative);
        Picasso.with(getContext()).load(R.drawable.madmin).into(admin);
        Picasso.with(getContext()).load(R.drawable.msecurity).into(sec);
        Picasso.with(getContext()).load(R.drawable.mhospitality).into(hop);




        return view;
    }
}
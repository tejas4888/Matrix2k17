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


    ImageView cp,vcp1,vcp2,vcp3,tech1,tech2,hoes1,hoes2,hoes3,hoes4,creative,admin1,admin2,sec1,sec2,hop1,hop2,hop3;

    public CommitteeFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_committee,container,false);

        cp = (ImageView)view.findViewById(R.id.cp);
        vcp1 = (ImageView)view.findViewById(R.id.vcp1);
        vcp2 = (ImageView)view.findViewById(R.id.vcp2);
        vcp3 = (ImageView)view.findViewById(R.id.vcp3);
        tech1 = (ImageView)view.findViewById(R.id.tech1);
        tech2 = (ImageView)view.findViewById(R.id.tech2);
        hoes1 = (ImageView)view.findViewById(R.id.hoes1);
        hoes2 = (ImageView)view.findViewById(R.id.hoes2);
        hoes3 = (ImageView)view.findViewById(R.id.hoes3);
        hoes4 = (ImageView)view.findViewById(R.id.hoes4);
        creative = (ImageView)view.findViewById(R.id.creative);
        admin1 = (ImageView)view.findViewById(R.id.admin1);
        admin2 = (ImageView)view.findViewById(R.id.admin2);
        sec1= (ImageView)view.findViewById(R.id.security1);
        sec2 = (ImageView)view.findViewById(R.id.security2);
        hop1 = (ImageView)view.findViewById(R.id.hop1);
        hop2 = (ImageView)view.findViewById(R.id.hop2);
        hop3 = (ImageView)view.findViewById(R.id.hop3);

        Picasso.with(getActivity()).load(R.drawable.cp).into(cp);
        Picasso.with(getActivity()).load(R.drawable.vcp_kamya).into(vcp1);
        Picasso.with(getActivity()).load(R.drawable.vcp_ashish).into(vcp2);
        Picasso.with(getActivity()).load(R.drawable.vcp_sushmen).into(vcp3);
        Picasso.with(getActivity()).load(R.drawable.tech_aditya).into(tech1);
        Picasso.with(getActivity()).load(R.drawable.tech_aashish).into(tech2);
        Picasso.with(getActivity()).load(R.drawable.hoes_jainam).into(hoes1);
        Picasso.with(getActivity()).load(R.drawable.hoes_vishwa).into(hoes2);
        Picasso.with(getActivity()).load(R.drawable.hoes_saral).into(hoes3);
        Picasso.with(getActivity()).load(R.drawable.hoes_shreya).into(hoes4);
        Picasso.with(getActivity()).load(R.drawable.creative).into(creative);
        Picasso.with(getActivity()).load(R.drawable.image1).into(admin1);
        Picasso.with(getActivity()).load(R.drawable.admin_gagan).into(admin2);
        Picasso.with(getActivity()).load(R.drawable.security_deepam).into(sec1);
        Picasso.with(getActivity()).load(R.drawable.security_ninad).into(sec2);
        Picasso.with(getActivity()).load(R.drawable.hop_ankur).into(hop1);
        Picasso.with(getActivity()).load(R.drawable.hop_astha).into(hop2);
        Picasso.with(getActivity()).load(R.drawable.hop3).into(hop3);

        return view;
    }
}
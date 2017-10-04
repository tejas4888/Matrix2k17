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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import spit.vortex17.HelperClasses.SponsorRecyclerAdapter;
import spit.vortex17.R;

public class SponsorsFragment extends Fragment {


    ArrayList<String> sponsors;
    RecyclerView spon;


    public SponsorsFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sponsors,container,false);


        sponsors = new ArrayList<String>();
        sponsors.add("Aarti Industries - Title");
        sponsors.add("Dow Chemicals - Main");
        sponsors.add("Viswaat - Associate");
        sponsors.add("GARDA - Associate");
        sponsors.add("Amataresu Life Sciences LLP - Associate");
        sponsors.add("Alkyl Animes - Associate");
        sponsors.add("Tata Chemicals - Co");
        sponsors.add("Generex - Co");
        sponsors.add("Maharashtra Times - Powering");
        sponsors.add("Borosil Glassworks - Supporting");
        sponsors.add("Casio - Supporting");
        sponsors.add("IMS - Education Partner");
        sponsors.add("Subway - Food Partner");
        sponsors.add("MOD - Food Partner");
        sponsors.add("Havmor - Food Partner");
        sponsors.add("Taco Bell - Food Partner");
        sponsors.add("College Fever - Ticketing Partner");


        Integer[] x = {R.drawable.sp_aarti,
                        R.drawable.sp_dow,
                        R.drawable.sp_viswaat,R.drawable.sp_gharda,R.drawable.sp_amater,R.drawable.sp_alkyl,
                        R.drawable.sp_tcl,R.drawable.sp_generex,
                        R.drawable.sp_mata,
                        R.drawable.sp_borosil,R.drawable.sp_casio,
                        R.drawable.sp_ims,
                        R.drawable.sp_subway,R.drawable.sp_mod,R.drawable.sp_havmor,R.drawable.sp_taco,
                        R.drawable.sp_college};

        spon = (RecyclerView)view.findViewById(R.id.recyclersponsor);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        spon.setLayoutManager(gridLayoutManager);
        spon.hasFixedSize();
        spon.setNestedScrollingEnabled(false);

        SponsorRecyclerAdapter spx = new SponsorRecyclerAdapter(sponsors,x);

        spon.setAdapter(spx);


        return view;
    }


}

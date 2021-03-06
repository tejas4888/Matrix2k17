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

package spit.matrix17.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import spit.matrix17.R;

public class MainFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    public MainFragment(){}

    public static MainFragment newInstance(){
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        viewPager = (ViewPager)view.findViewById(R.id.fragment_viewPager_tabLayout);
        tabLayout =(TabLayout)view.findViewById(R.id.main_fragment_tabLayout);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        //viewPager.setOffscreenPageLimit(0);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return view;
    }


    class MyAdapter extends FragmentPagerAdapter{

        MyAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            EventListFragment megaEventPage = EventListFragment.newInstance("Championships");
            EventListFragment techEventPage =  EventListFragment.newInstance("Talks and Exhibitions");
            EventListFragment funEventPage =  EventListFragment.newInstance("Featured");
            EventListFragment teEventPage =  EventListFragment.newInstance("Fun");
            switch (position) {
                case 0:
                    return megaEventPage;
                case 1:
                    return techEventPage;
                case 2:
                    return funEventPage;
                case 3:
                    return teEventPage;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:return "Championships";
                case 1:return "Talks and Exhibitions";
                case 2:return "Featured";
                case 3: return "Fun";
            }
            return null;
        }
    }
}

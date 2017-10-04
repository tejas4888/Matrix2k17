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

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import spit.vortex17.R;

public class DevelopersFragment extends Fragment {

    TextView email1, email2, email3, email4, email5, email6;
    TextView name1,name2;
    TextView branch1,branch2;
    Button g1, g2, g3, g4, g5, g6, l1, l2, l3, l4, l5, l6;
    ImageView image1, image2, image3, image4, image5, image6;

    public DevelopersFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_developers,container,false);


        email1=(TextView)view.findViewById(R.id.emailId_Aditya);
        email2=(TextView)view.findViewById(R.id.emailId_Tejas);

        name1=(TextView)view.findViewById(R.id.name_Aditya);
        name2=(TextView)view.findViewById(R.id.name_Tejas);

        branch1=(TextView)view.findViewById(R.id.branch_Aditya);
        branch2=(TextView)view.findViewById(R.id.branch_Tejas);

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/marketdeco.ttf");

        name1.setTypeface(custom_font);
        name2.setTypeface(custom_font);
        branch1.setTypeface(custom_font);
        branch2.setTypeface(custom_font);

        g1=(Button)view.findViewById(R.id.google_aditya);
        g2=(Button)view.findViewById(R.id.google_Tejas);


        l1=(Button)view.findViewById(R.id.linkedin_aditya);
        l2=(Button)view.findViewById(R.id.linkedin_Tejas);


        image1=(ImageView)view.findViewById(R.id.pic_Aditya);
        image2=(ImageView)view.findViewById(R.id.pic_Tejas);


        /*Add Your Pics Here And Not In Xml*/
        Picasso.with(getActivity()).load(R.drawable.dev_aditya).into(image1);
        Picasso.with(getActivity()).load(R.drawable.dev_tejas_chheda).into(image2);



        View.OnClickListener linkListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri;
                switch (v.getId()){

                    /*Google+ links*/
                    case R.id.google_Tejas:
                        uri=Uri.parse(getResources().getString(R.string.devTejasgoogle));
                        break;
                    case R.id.google_aditya:
                        uri=Uri.parse(getResources().getString(R.string.devAdityagoogle));
                        break;


                    /*LInkedin Links*/
                    case R.id.linkedin_Tejas:
                        uri=Uri.parse(getResources().getString(R.string.devTejaslinkedin));
                        break;
                    case R.id.linkedin_aditya:
                        uri=Uri.parse(getResources().getString(R.string.devAdityalinkedin));
                        break;

                    default:uri =Uri.parse(getResources().getString(R.string.devTejaslinkedin));
                }
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                try{
                    startActivity(i);
                }
                catch (Exception e){
                    Toast.makeText(getActivity(),"Error Loading Link",Toast.LENGTH_SHORT).show();
                }

            }
        };

    View.OnClickListener emailListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String to="";
            switch (v.getId()){
                /*Email Ids*/
                case R.id.emailId_Tejas:
                    to = getResources().getString(R.string.devTejasemail);
                    break;
                case R.id.emailId_Aditya:
                    to = getResources().getString(R.string.devAdityaemail);
                    break;

            }
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SENDTO);
            intent.setType("text/plain");
            intent.setData(Uri.parse("mailto:"+to));
            intent.putExtra(Intent.EXTRA_EMAIL,to);
            try{
                startActivity(Intent.createChooser(intent,"Send Email"));
            }
            catch(Exception e){
                Toast.makeText(getActivity(),e.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
            }
            }
        };
        email1.setOnClickListener(emailListener);
        email2.setOnClickListener(emailListener);

        g1.setOnClickListener(linkListener);
        g2.setOnClickListener(linkListener);

        l1.setOnClickListener(linkListener);
        l2.setOnClickListener(linkListener);

        return view;
    }
}

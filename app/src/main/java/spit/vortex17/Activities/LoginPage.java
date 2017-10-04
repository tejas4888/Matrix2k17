package spit.vortex17.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import spit.vortex17.HelperClasses.User;
import spit.vortex17.R;


public class LoginPage extends AppCompatActivity {


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mPushDatabaseReference;

    int i = 1;
    String name,email,profile,uid,type,phoneNo;

    SharedPreferences.Editor sp,spa;
    SharedPreferences userInfo;
    SharedPreferences firstTime;

    TextView uname,uemail,utype;
    EditText uclass,ucontact;
    ImageView uprofile;
    Spinner spinner;
    Button b;
    String x,z;

    private ArrayList admin, eventOrg;


    String fixedFrom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        admin = new ArrayList();
        admin.add("aditya.bhave41@gmail.com");

        // and so on



        eventOrg = new ArrayList();
        //eventOrg.add("aditya.bhave41@gmail.com");
        eventOrg.add("treasurervortex2017@gmail.com");
        eventOrg.add("anaynavandar@gmail.com");
        eventOrg.add("spons.vortex@gmail.com");
        eventOrg.add("oeh.pharma.vortex@gmail.com");
        eventOrg.add("adityabaser1210@gmail.com");
        eventOrg.add("oeh.prodigygeneral.vortex@gmail.com");



        // and so on
        //prjct exhibition, tech quz

        userInfo = getSharedPreferences("userInfo", Context.MODE_APPEND);
        sp = userInfo.edit();

        firstTime = getSharedPreferences("firstTime", Context.MODE_APPEND);
        spa = firstTime.edit();

        Intent i = getIntent();
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");
        profile = i.getStringExtra("profile");
        uid = i.getStringExtra("uid");
        //type = i.getStringExtra("type");

        if(admin.contains(email)){
            type = "Supervisor";
        }
        else if(eventOrg.contains(email)) {
            type = "Event Organiser";
        }
        else{
            type = "Guest";
        }
        sp.putString("type",type);
        sp.commit();


        b = (Button) findViewById(R.id.regButton);
        b.setClickable(false);
        /*Populate the XML fields here

        And add the spinners for getting year and branch
        And hide them until we dont check if we already have them

         */

        uname = (TextView) findViewById(R.id.uName);
        uemail =  (TextView) findViewById(R.id.uEmail);
        utype = (TextView) findViewById(R.id.uType);
        uprofile = (ImageView) findViewById(R.id.uProfile);
        uclass = (EditText) findViewById(R.id.uClass);
        spinner = (Spinner) findViewById(R.id.spinner);
        ucontact = (EditText) findViewById(R.id.uContact);

        uname.setText(name);
        uemail.setText(email);
        utype.setText(type);
        Glide.with(this).load(profile).into(uprofile);

        Toast.makeText(this,userInfo.getString("name","huh?"),Toast.LENGTH_SHORT);



        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("FY");
        categories.add("SY");
        categories.add("TY");
        categories.add("Final Yr");
        categories.add("PG");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                x = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                fixedFrom = "TE" + "," + uclass.getText().toString();
            }
        });



        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Users").child("Vortex");
        mPushDatabaseReference = mFirebaseDatabase.getReference().child("Users").child("Vortex");

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String x = (String) snapshot.child("email").getValue();
                    if(email.equals(x)){
                        Toast.makeText(LoginPage.this,"Welcome back!",Toast.LENGTH_SHORT).show();
                        String y = (String) snapshot.child("from").getValue();
                        sp.putString("from",y);
                        sp.commit();
                        spa.putBoolean("firstSignIn",false);
                        finish();
                    }
                }
                b.setClickable(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(LoginPage.this,"Please Register Yourself",Toast.LENGTH_SHORT).show();
    }

    public void regUser(View v){
        // Get the class + branch here!!

        if(uclass.getText().toString().equals("") || ucontact.getText().toString().equals("")){
            Toast.makeText(LoginPage.this,"Dont leave any field blank :)",Toast.LENGTH_SHORT).show();
            return;
        }
        if(ucontact.getText().toString().length() != 10){
            Toast.makeText(LoginPage.this,"Please Enter a valid phone number",Toast.LENGTH_SHORT).show();
        }

        phoneNo = ucontact.getText().toString();
        z = uclass.getText().toString();
        fixedFrom = x + "," + z;
        mPushDatabaseReference.push().setValue(new User(name,email,profile,uid,type,fixedFrom,phoneNo));
        Toast.makeText(this,"You are registered",Toast.LENGTH_SHORT).show();
        spa.putBoolean("firstSignIn",false);
        spa.commit();
        sp.putString("from", fixedFrom);
        sp.putString("phone",phoneNo);
        sp.commit();
        finish();
    }



}


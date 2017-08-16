package spit.matrix2017.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import spit.matrix2017.HelperClasses.Event;
import spit.matrix2017.HelperClasses.Feedback;
import spit.matrix2017.HelperClasses.User;
import spit.matrix2017.R;


public class LoginPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


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

    String fixedFrom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        userInfo = getSharedPreferences("userInfo",MODE_APPEND);
        sp = userInfo.edit();

        firstTime = getSharedPreferences("firstTime",MODE_APPEND);
        spa = firstTime.edit();

        Intent i = getIntent();
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");
        profile = i.getStringExtra("profile");
        uid = i.getStringExtra("uid");
        type = i.getStringExtra("type");

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


        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("COMPS");
        categories.add("IT");
        categories.add("EXTC");
        categories.add("ETRX");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Users");
        mPushDatabaseReference = mFirebaseDatabase.getReference().child("Users");

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String x = (String) snapshot.child("email").getValue();
                    if(email.equals(x)){
                        Toast.makeText(LoginPage.this,"Already!!",Toast.LENGTH_SHORT).show();
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            this.finish();
        }
    }

    public void regUser(View v){
        // Get the class + branch here!!
        phoneNo = ucontact.getText().toString();
        mPushDatabaseReference.push().setValue(new User(name,email,profile,uid,type,fixedFrom,phoneNo));
        Toast.makeText(this,"You are    registered",Toast.LENGTH_SHORT).show();
        spa.putBoolean("firstSignIn",false);
        spa.commit();
        finish();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String x = parent.getItemAtPosition(position).toString();
        fixedFrom = uclass.getText().toString() + "" + x;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Do something
        fixedFrom = uclass.getText().toString() + "" + "COMPS";
    }
}

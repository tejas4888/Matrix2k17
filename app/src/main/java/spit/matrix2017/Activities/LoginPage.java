package spit.matrix2017.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import spit.matrix2017.HelperClasses.Event;
import spit.matrix2017.HelperClasses.Feedback;
import spit.matrix2017.HelperClasses.User;
import spit.matrix2017.R;


public class LoginPage extends AppCompatActivity {


    private FirebaseAuth mFirebaseAuth;
    FirebaseUser user;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mPushDatabaseReference;

    int i = 1;
    String name,email,profile,uid,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        Intent i = getIntent();
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");
        profile = i.getStringExtra("profile");
        uid = i.getStringExtra("uid");
        type = i.getStringExtra("type");


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Users");
        mPushDatabaseReference = mFirebaseDatabase.getReference().child("Users");

        LoginPage.FetchUserList  ful = new LoginPage.FetchUserList();
        ful.execute();


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


    public class FetchUserList extends AsyncTask<Void,Void,ArrayList<Event>> {
        @Override
        protected void onPreExecute() {
            //bar.setVisibility(View.VISIBLE);
        }
        @Override
        protected ArrayList<Event> doInBackground(Void... params) {
            mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        if(snapshot.child("email").getValue().equals(email)){
                            finish();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mPushDatabaseReference.push().setValue(new User(name,email,profile,uid,type));
            finish();
            //Toast.makeText(MainActivity.this,"Registering",Toast.LENGTH_SHORT).show();
            return null;
        }
    }


}

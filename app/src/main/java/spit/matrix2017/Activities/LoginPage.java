package spit.matrix2017.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
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

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class LoginPage extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ValueEventListener mValueEventListener;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseUser user;

    private String mUserName;

    private ArrayList admin, eventOrg;
    private int i = 1;

    SharedPreferences.Editor userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        admin = new ArrayList();
        admin.add("asdfas");// and so on

        eventOrg = new ArrayList();
        eventOrg.add("sdfasd");// and so on


        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("users");

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    // Code to save userdata
                    String x = "Welcome " + user.getDisplayName() + " to the future";
                    Toast.makeText(LoginPage.this, x ,Toast.LENGTH_SHORT).show();
                    userInfo = getSharedPreferences("userInfo",MODE_APPEND).edit();
                    userInfo.putString("name",user.getDisplayName());
                    userInfo.putString("email",user.getEmail());
                    userInfo.putString("profile",user.getPhotoUrl().toString());
                    userInfo.putString("UID",user.getUid());

                    final String type;
                    if(admin.contains(user.getEmail())){
                        type = "Head";
                    }
                    else if(admin.contains(user.getEmail())) {
                        type = "Admin";
                    }
                    else{
                        type = "Guest";
                    }
                    userInfo.putString("type",type);
                    userInfo.commit();

                    final String UID = user.getUid();
                    mValueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String c_uid = (String) snapshot.child("uid").getValue();
                                if(UID == c_uid){
                                    i = 0;
                                    Toast.makeText(LoginPage.this,"Already registered :)",Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };

                    mDatabaseReference.addValueEventListener(mValueEventListener);

                    if(i == 1) {
                        //mDatabaseReference.push().setValue(new User(user.getDisplayName(), user.getEmail(),
                                //user.getPhotoUrl().toString(), user.getUid(),
                                //type));
                    }

                    Intent i = new Intent(LoginPage.this,MainActivity.class);
                    startActivity(i);

                } else {
                    // User is signed out

                    onSignedOutCleanup();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(AuthUI.GOOGLE_PROVIDER,AuthUI.EMAIL_PROVIDER)
                                    .setTheme(R.style.LoginTheme).setLogo(R.mipmap.ic_launcher)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    private void onSignedInInitialize(String username) {
        mUserName = username;
        //user.sendEmailVerification();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Sign-in succeeded, set up the UI
                //String x = "Signed In as" + user.getDisplayName().toString();
                //Toast.makeText(this, "Signed In", Toast.LENGTH_SHORT).show();

            } else if (resultCode == RESULT_CANCELED) {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void onSignedOutCleanup(){
        mUserName = "ANONYMOUS";
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
}

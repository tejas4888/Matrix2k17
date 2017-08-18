package spit.matrix2017.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import spit.matrix2017.HelperClasses.Registration;
import spit.matrix2017.R;

public class RegConfirm extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mPushDatabaseReference;
    private SharedPreferences sp;

    private Button mButton;

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_confirm);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.8));

        mButton = (Button) findViewById(R.id.regButton);
        mButton.setClickable(false);

        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Registrations").child(getIntent().getStringExtra("name"));
        mPushDatabaseReference = mFirebaseDatabase.getReference().child("Registrations").child(getIntent().getStringExtra("name"));

        email = sp.getString("email",null);

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String x = (String) snapshot.child("email").getValue();
                    if(email.equals(x)){
                        Toast.makeText(RegConfirm.this,"Already!!",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                mButton.setClickable(true);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,email,from;
                name = sp.getString("name",null);
                email = sp.getString("email",null);
                from = sp.getString("from",null);

                mPushDatabaseReference.push().setValue(new Registration(name,email,from));
                Toast.makeText(RegConfirm.this,"Registered!!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
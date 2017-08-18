package spit.matrix2017.Activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import spit.matrix2017.HelperClasses.Event;
import spit.matrix2017.HelperClasses.Feedback;
import spit.matrix2017.HelperClasses.Registration;
import spit.matrix2017.HelperClasses.RegistrationAdapter;
import spit.matrix2017.HelperClasses.ReviewAdapter;
import spit.matrix2017.R;

public class MyRegistrations extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RegistrationAdapter mRegAdapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mItemDatabaseReference;
    private ValueEventListener mValueEventListener;
    //Comment
    private ArrayList<Registration> mRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_registrations);

        mRecyclerView = (RecyclerView) findViewById(R.id.regRecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mItemDatabaseReference = mFirebaseDatabase.getReference().child("Registrations").child(getIntent().getStringExtra("name"));
        mRegistration = new ArrayList<Registration>();


        MyRegistrations.FetchRegList frl = new MyRegistrations.FetchRegList();
        frl.execute();

    }

    public class FetchRegList extends AsyncTask<Void,Void,ArrayList<Event>> {

        @Override
        protected void onPreExecute() {
            //bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Event> doInBackground(Void... params) {

            mValueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mRegistration.clear();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        String namex = (String) snapshot.child("email").getValue();
                        if(namex == null) {
                        break;
                        }
                        String email = (String) snapshot.child("email").getValue();
                        String name = (String) snapshot.child("name").getValue();
                        String from = (String) snapshot.child("from").getValue();
                        mRegistration.add(new Registration(name,from,email));

                    }
                    updateUI();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            mItemDatabaseReference.addValueEventListener(mValueEventListener);
            return null;
        }
    }

    public void updateUI(){
        mRegAdapter = new RegistrationAdapter(mRegistration,MyRegistrations.this);
        mRecyclerView.setAdapter(mRegAdapter);
        mRecyclerView.scrollToPosition(0);
    }
}

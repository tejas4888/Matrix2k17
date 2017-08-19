package spit.matrix2017.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import spit.matrix2017.HelperClasses.Event;
import spit.matrix2017.R;

public class AddEvent2 extends AppCompatActivity {

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    String timings,venue,mail,name1,num1,name2,num2,prize,fee;
    String name,url,desc,date,eventCategory;
    EditText etimings,evenue,email,ename1,enum1,ename2,enum2,eprize,efee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event2);

        etimings=(EditText)findViewById(R.id.event2_timings);
        evenue=(EditText)findViewById(R.id.event2_venue);
        email=(EditText)findViewById(R.id.event2_mail);
        ename1=(EditText)findViewById(R.id.event2_name1);
        enum1=(EditText)findViewById(R.id.event2_num1);
        ename2=(EditText)findViewById(R.id.event2_name2);
        enum2=(EditText)findViewById(R.id.event2_num2);
        eprize=(EditText)findViewById(R.id.event2_prize);
        efee=(EditText)findViewById(R.id.event2_fee);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Events");

        /*  Get a caegory from user out of Mega, Major and Fun,
        accordingly branch the database reference one branch further and then push*/

    }

    public void addEvent2(View v)
    {
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        url=intent.getStringExtra("url");
        desc=intent.getStringExtra("desc");
        date=intent.getStringExtra("date");
        eventCategory=intent.getStringExtra("eventCategory");

        timings=etimings.getText().toString();
        venue=evenue.getText().toString();
        mail=email.getText().toString();
        name1=ename1.getText().toString();
        num1=enum1.getText().toString();
        name2=ename2.getText().toString();
        num2=enum2.getText().toString();
        prize=eprize.getText().toString();
        fee=efee.getText().toString();

        mDatabaseReference.child(eventCategory).push().setValue(new Event(name,desc,url,date,timings,venue,mail,name1,name2,num1,num2,prize,fee));
        finish();
    }
}

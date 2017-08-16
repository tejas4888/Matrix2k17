package spit.matrix2017.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import spit.matrix2017.HelperClasses.Event;
import spit.matrix2017.HelperClasses.User;
import spit.matrix2017.R;

public class AddEvent extends AppCompatActivity {

    String name,desc,date,time,venue,orgMail,pocName1,pocName2,pocNumber1,pocNumber2,prizeScheme,feeScheme;
    EditText ename,edesc,edate,etime,evenue,eOrgMail,epocName1,epocName2,epocNumber1,epocNumber2,eprize,efees;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        ename = (EditText) findViewById(R.id.eventName);
        edesc = (EditText) findViewById(R.id.eventDescription);
        edate = (EditText) findViewById(R.id.eventDates);
        etime = (EditText) findViewById(R.id.eventTime);
        evenue = (EditText) findViewById(R.id.eventVenue);
        eOrgMail = (EditText) findViewById(R.id.eventOrgMail);
        epocName1 = (EditText) findViewById(R.id.eventPocName1);
        epocNumber1 = (EditText) findViewById(R.id.eventPocNumber1);
        epocName2 = (EditText) findViewById(R.id.eventPocName2);
        epocNumber2 = (EditText) findViewById(R.id.eventPocNumber2);

        eprize = (EditText) findViewById(R.id.eventPrizeScheme);
        efees = (EditText) findViewById(R.id.eventFeeScheme);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Events");
    }


    /*Create a form like layout to input all the fields of the events
        Except the eventOrgMail field, it should be automatically added from the sharedprefs
        Just focus on functionality, ui later
        Dont use the v4 vaala circle image view, use the imported one
     */


    public void addEvent(View v){
        name = ename.getText().toString();
        desc = edesc.getText().toString();
        date = edate.getText().toString();
        time = etime.getText().toString();
        venue = evenue.getText().toString();
        orgMail = eOrgMail.getText().toString();
        pocName1 = epocName1.getText().toString();
        pocNumber1 = epocNumber1.getText().toString();
        pocName2 = epocName2.getText().toString();
        pocNumber2 = epocNumber2.getText().toString();
        prizeScheme = eprize.getText().toString();
        feeScheme = efees.getText().toString();

        mDatabaseReference.push().setValue(new Event(name,desc,"",date,time,venue,orgMail,pocName1,pocName2,pocNumber1,pocNumber2,prizeScheme,feeScheme));
        Toast.makeText(this,"Done!",Toast.LENGTH_SHORT);
        finish();
    }

}

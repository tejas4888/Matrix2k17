package spit.matrix2017.Activities;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import spit.matrix2017.HelperClasses.Event;
import spit.matrix2017.HelperClasses.User;
import spit.matrix2017.R;

public class AddEvent extends AppCompatActivity {

    String name,desc,date,time,venue,orgMail,pocName1,pocName2,pocNumber1,pocNumber2,prizeScheme,feeScheme;
    EditText ename,edesc,edate,etime,evenue,eOrgMail,epocName1,epocName2,epocNumber1,epocNumber2,eprize,efees;
    ImageView poster;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mStorageReference;

    String url = null;

    private static final int RC_PHOTO_PICKER =  2;

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

        poster = (ImageView) findViewById(R.id.eventPoster);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Events");

        mFirebaseStorage = FirebaseStorage.getInstance();
        mStorageReference = mFirebaseStorage.getReference().child("EventPosters");
    }


    /*Create a form like layout to input all the fields of the events
        Except the eventOrgMail field, it should be automatically added from the sharedprefs
        Just focus on functionality, ui later
        Dont use the v4 vaala circle image view, use the imported one
     */


    public void addPoster(View v){
        // TODO: Fire an intent to show an image picker
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_PHOTO_PICKER){
            if(resultCode == RESULT_OK){
                Uri selectedImageUri = data.getData();
                StorageReference photoRef = mStorageReference.child(selectedImageUri.getLastPathSegment());

                photoRef.putFile(selectedImageUri)
                        .addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                //noinspection VisibleForTests
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                url = downloadUrl.toString();
                                Glide.with(AddEvent.this).load(url).into(poster);
                            }
                        });
            }
        }
    }

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

        /*Hardcoded Poster*/
        if(url == null) {
            url = "https://s-media-cache-ak0.pinimg.com/originals/8e/f5/9d/8ef59dc3c90a3abd56c87a5901709132.jpg";
        }
        mDatabaseReference.push().setValue(new Event(name,desc,url,date,time,venue,orgMail,pocName1,pocName2,pocNumber1,pocNumber2,prizeScheme,feeScheme));
        Toast.makeText(this,"Done!",Toast.LENGTH_SHORT);
        finish();
    }

}

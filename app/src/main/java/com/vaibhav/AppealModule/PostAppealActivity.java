package com.vaibhav.AppealModule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vaibhav.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostAppealActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    FirebaseDatabase database;

    EditText name;
    EditText bloodGroup;
    EditText units;
    EditText platlets;
    EditText location;
    EditText hospital;
    EditText mobile;
    Button confirm;
    CheckBox platletsCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_appeal);

        name = findViewById(R.id.patientName);
        bloodGroup = findViewById(R.id.patientBloodGroup);
        units = findViewById(R.id.units);
        platlets = findViewById(R.id.platelets);
        location = findViewById(R.id.location);
        hospital = findViewById(R.id.hospital);
        mobile = findViewById(R.id.mobile);
        confirm = findViewById(R.id.conformButton);
        platletsCheckBox = findViewById(R.id.CheckBox);

        platlets.setEnabled(false);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("appeals");

        platletsCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(platletsCheckBox.isChecked()){
                    platlets.setEnabled(true);
                }
                else
                    platlets.setEnabled(false);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                System.out.println(formatter.format(date));
                String key = database.getReference("appeals").push().getKey();
                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("ID" , key);
                childUpdates.put( "name"  , name.getText().toString().trim());
                childUpdates.put("bloodGroup" , bloodGroup.getText().toString().trim());
                childUpdates.put("units" , units.getText().toString().trim());
                childUpdates.put("platelets" , platlets.getText().toString().trim());
                childUpdates.put("location" , location.getText().toString().trim());
                childUpdates.put("hospital" , hospital.getText().toString().trim());
                childUpdates.put("mobile" , mobile.getText().toString().trim());
                childUpdates.put("DateTime" , formatter.format(date));
                childUpdates.put("Response" , " ");
                childUpdates.put("uploadedBy" , user.getEmail());
                childUpdates.put("uploaderID" , user.getUid());
                database.getReference("appeals").child(key).updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            Toast.makeText(getApplicationContext() , "Appeal uploaded" , Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
            }
        });


    }
}

package com.vaibhav.LoginModule;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vaibhav.HelpModule.ViewAppealActivity;
import com.vaibhav.R;
import com.vaibhav.util.Pref;

public class CreateNormalUserActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;

    EditText userName;
    EditText userCity;
    EditText userBlood;
    EditText userAge;
    EditText userMobile;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_normal_user);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#ff3333")));

        userName = findViewById(R.id.userName);
        userCity = findViewById(R.id.userCity);
        userBlood = findViewById(R.id.userBloodGroup);
        userAge = findViewById(R.id.userAge);
        confirm = findViewById(R.id.confirm);
        userMobile = findViewById(R.id.mobileNumber);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        String userID = user.getUid();
        reference = database.getReference().child("users").child(userID);

        boolean isFirstTime = Pref.isFirst(CreateNormalUserActivity.this);

        if(isFirstTime){
            Toast.makeText(getApplicationContext() , "Enter valid details" , Toast.LENGTH_SHORT).show();
        }
        else{
            startActivity(new Intent(getApplicationContext() , ViewAppealActivity.class));
            finish();
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child("Name").setValue(userName.getText().toString().trim());
                reference.child("city").setValue(userCity.getText().toString().trim());
                reference.child("blood").setValue(userBlood.getText().toString().trim());
                reference.child("age").setValue(userAge.getText().toString().trim());
                reference.child("mobile").setValue(userMobile.getText().toString().trim());
                reference.child("verified").setValue("yes");
                Toast.makeText(getApplicationContext() , "You are all set" , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext() , ViewAppealActivity.class));
                finish();
            }
        });



    }
}

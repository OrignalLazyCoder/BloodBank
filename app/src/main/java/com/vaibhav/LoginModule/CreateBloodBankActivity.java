package com.vaibhav.LoginModule;

import android.content.Intent;
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
import com.vaibhav.util.Pref2;

public class CreateBloodBankActivity extends AppCompatActivity {

    EditText name;
    EditText regNumber;
    EditText city;
    Button confirm;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_blood_bank);

        name = findViewById(R.id.bankName);
        regNumber = findViewById(R.id.bankRegNumber);
        city = findViewById(R.id.bankCity);
        confirm = findViewById(R.id.confirm);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        String uId = user.getUid();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("users").child(uId);

        boolean isFirstTime = Pref2.isFirst(CreateBloodBankActivity.this);

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
                reference.child("Name").setValue(name.getText().toString().trim());
                reference.child("regNumber").setValue(regNumber.getText().toString().trim());
                reference.child("city").setValue(city.getText().toString().trim());
                startActivity(new Intent(getApplicationContext() , ViewAppealActivity.class));
                finish();
            }
        });


    }
}

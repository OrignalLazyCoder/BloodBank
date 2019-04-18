package com.vaibhav;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    EditText userEmail;
    EditText userPass;
    Button registerBTN;
    RadioButton bloodBank;
    RadioButton normalUser;

    ProgressDialog progressDialog;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseApp.initializeApp(this);


        userEmail = findViewById(R.id.userid);
        userPass = findViewById(R.id.userpwd);
        registerBTN = findViewById(R.id.regbtn);
        bloodBank = findViewById(R.id.bloodBank);
        normalUser = findViewById(R.id.normalUser);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString();
                String password = userPass.getText().toString();
                addUser(email , password);
            }
        });


    }

    private void addUser(final String email, String password) {
        auth.createUserWithEmailAndPassword(email , password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();

                            String type= " ";

                            String userID = auth.getCurrentUser().getUid();
                            DatabaseReference creationReference = reference.child(userID);
                            if(bloodBank.isChecked()){
                                type= "BloodBank";
                            }
                            if(normalUser.isChecked()){
                                type = "user";
                            }

                            creationReference.child("Email").setValue(email);
                            creationReference.child("Name").setValue("n/a");
                            creationReference.child("city").setValue("n/a");
                            creationReference.child("verified").setValue("no");
                            creationReference.child("type").setValue(type);

                            startActivity(new Intent(getApplicationContext() , MainActivity.class));
                            finish();
                        }
                        else
                            Toast.makeText(RegisterActivity.this , "User not Created" , Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

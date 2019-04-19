package com.vaibhav;

import android.content.Intent;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vaibhav.LoginModule.CreateBloodBankActivity;
import com.vaibhav.LoginModule.CreateNormalUserActivity;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {

    Button logout;
    Button verify;

    TextView emailCheck;

    FirebaseUser user;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = findViewById(R.id.logout);
        verify = findViewById(R.id.verify);

        auth = FirebaseAuth.getInstance();
        user  = auth.getCurrentUser();
        emailCheck = findViewById(R.id.emailCheck);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("users").child(user.getUid());
        final String[] userType = {" "};

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String type = dataSnapshot.child("type").getValue(String.class);
                userType[0] = type;
//                Toast.makeText(getApplicationContext() , type , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(true){
            emailCheck.setText("Email Verified");
            if(userType[0].equals("user")) {
                startActivity(new Intent(Home.this, CreateNormalUserActivity.class));
                finish();
            }
            else{
                startActivity(new Intent(Home.this , CreateBloodBankActivity.class));
                finish();
            }
        }
        else{
            emailCheck.setText("Email Not verified");
        }


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(Home.this , MainActivity.class));
                finish();
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext() , "Email Sent. Please verify yourself" , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}

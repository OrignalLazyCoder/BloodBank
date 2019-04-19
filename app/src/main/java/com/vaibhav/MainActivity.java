package com.vaibhav;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText userEmail;
    EditText userPass;

    private FirebaseAuth auth;
    private FirebaseUser user;

    Button Login;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(MainActivity.this);
        setContentView(R.layout.activity_main);

        userEmail = findViewById(R.id.userid);
        userPass = findViewById(R.id.userpwd);
        Login = findViewById(R.id.loginbtn);
        Register = findViewById(R.id.regbtn);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#ff3333")));

        if(user!=null)
        {
            startActivity(new Intent(MainActivity.this , Home.class));
            finish();
        }

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , RegisterActivity.class));
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginfunction();
            }
        });

    }

    private void loginfunction() {
        String email = userEmail.getText().toString();
        String password = userPass.getText().toString();
        auth.signInWithEmailAndPassword(email , password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(MainActivity.this , Home.class));
                        }
                        else{
                            Toast.makeText(MainActivity.this , "Login Failed" , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

package com.vaibhav.HelpModule;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vaibhav.R;
import com.vaibhav.adapter.BloodBankListViewAdapter;
import com.vaibhav.model.BloodBankModel;

import java.util.ArrayList;

public class CheckBloodBankDetailsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;

    ListView listView;

    ArrayList<BloodBankModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_blood_bank_details);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#ff3333")));

        listView = findViewById(R.id.listView);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("users");

        list = new ArrayList<>();

        final BloodBankListViewAdapter bloodBankListViewAdapter = new BloodBankListViewAdapter(this , R.layout.bloodbank_database_listview , list);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snap : dataSnapshot.getChildren()){
//                    Toast.makeText(getApplicationContext() , snap.child("Name").getValue().toString() , Toast.LENGTH_SHORT).show();
                    if(snap.child("type").getValue().toString().equals("BloodBank")){
                        BloodBankModel b1 =  new BloodBankModel(
                                snap.child("Ap").getValue().toString(),
                                snap.child("An").getValue().toString(),
                                snap.child("Bp").getValue().toString(),
                                snap.child("Bn").getValue().toString(),
                                snap.child("Op").getValue().toString(),
                                snap.child("On").getValue().toString(),
                                snap.child("ABp").getValue().toString(),
                                snap.child("ABn").getValue().toString(),
                                snap.child("Name").getValue().toString(),
                                snap.child("mobile").getValue().toString()
                        );

                        Log.d("testing" , b1.getName());

                        list.add(b1);
                        listView.setAdapter(bloodBankListViewAdapter);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

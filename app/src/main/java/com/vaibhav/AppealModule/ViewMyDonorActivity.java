package com.vaibhav.AppealModule;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vaibhav.R;
import com.vaibhav.adapter.AvailableDonorListViewAdapter;
import com.vaibhav.model.AvailableDonorModel;

import java.util.ArrayList;

public class ViewMyDonorActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;

    ListView myDonorListView;

    ArrayList<AvailableDonorModel> availableDonorModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_donor);

        myDonorListView = findViewById(R.id.lvAvailableDonors);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        String appealId = intent.getStringExtra("appealId");
        reference = database.getReference().child("appeals").child(appealId).child("Response");

        availableDonorModelArrayList = new ArrayList<>();

        final AvailableDonorListViewAdapter availableDonorListViewAdapter = new AvailableDonorListViewAdapter(this, R.layout.available_donor_listview_layout, availableDonorModelArrayList);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    AvailableDonorModel donor = new AvailableDonorModel(
                            snapshot.child("userEmail").getValue().toString(),
                            snapshot.child("userName").getValue().toString(),
                            snapshot.child("location").getValue().toString(),
                            snapshot.child("userMobile").getValue().toString()

                    );

                    availableDonorModelArrayList.add(donor);
                }
                myDonorListView.setAdapter(availableDonorListViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

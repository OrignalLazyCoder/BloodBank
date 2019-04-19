package com.vaibhav.AppealModule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

        availableDonorModelArrayList = new ArrayList<>();

        AvailableDonorModel donor = new AvailableDonorModel(
          "1",
          "Haris Tyagi",
          "Muzaffarnagar",
          "9045997787"
        );

        availableDonorModelArrayList.add(donor);
        availableDonorModelArrayList.add(donor);

        AvailableDonorListViewAdapter availableDonorListViewAdapter = new AvailableDonorListViewAdapter(this, R.layout.available_donor_listview_layout, availableDonorModelArrayList);

        myDonorListView.setAdapter(availableDonorListViewAdapter);
    }
}

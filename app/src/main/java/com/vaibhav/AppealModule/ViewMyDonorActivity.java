package com.vaibhav.AppealModule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.vaibhav.R;
import com.vaibhav.adapter.AvailableDonorListViewAdapter;
import com.vaibhav.model.AvailableDonorModel;

import java.util.ArrayList;

public class ViewMyDonorActivity extends AppCompatActivity {

    ListView myDonorListView;

    ArrayList<AvailableDonorModel> availableDonorModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_donor);

        myDonorListView = findViewById(R.id.lvAvailableDonors);

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

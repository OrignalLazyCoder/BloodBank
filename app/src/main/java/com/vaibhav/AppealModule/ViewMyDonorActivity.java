package com.vaibhav.AppealModule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.vaibhav.R;

public class ViewMyDonorActivity extends AppCompatActivity {

    ListView myDonorListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_donor);

        myDonorListView = findViewById(R.id.lvAvailableDonors);
    }
}

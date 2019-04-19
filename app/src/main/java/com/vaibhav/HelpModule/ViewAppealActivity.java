package com.vaibhav.HelpModule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.vaibhav.R;
import com.vaibhav.adapter.BloodListViewAdapter;
import com.vaibhav.model.BloodModel;

import java.util.ArrayList;

public class ViewAppealActivity extends AppCompatActivity {

    ListView lvBloodAppeal;
    ArrayList<BloodModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appeal);

        lvBloodAppeal = (ListView) findViewById(R.id.lvBLoodAppeal);

        list = new ArrayList<BloodModel>();


        BloodModel b1 = new BloodModel(
                "Haris Tyagi",
                "AB+",
                "1000",
                true,
                "100000",
                "Muzaffarnagar",
                "S.C. Gupta",
                "3 A.M Tommorow",
                "9045997787"
        );

        list.add(b1);

        BloodListViewAdapter bloodListViewAdapter = new BloodListViewAdapter(this, list);

        lvBloodAppeal.setAdapter(bloodListViewAdapter);

    }
}

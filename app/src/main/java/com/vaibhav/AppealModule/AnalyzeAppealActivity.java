package com.vaibhav.AppealModule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vaibhav.R;
import com.vaibhav.adapter.MyAppealListViewAdapter;
import com.vaibhav.model.MyAppealsModel;

import java.util.ArrayList;

public class AnalyzeAppealActivity extends AppCompatActivity {

    ListView myAppealListView;

    ArrayList<MyAppealsModel> myAppealsModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze_appeal);

        myAppealsModelArrayList = new ArrayList<>();
        myAppealListView = findViewById(R.id.lvMyAppeals);

        MyAppealsModel newAppeal = new MyAppealsModel(
                "1",
                "Haris Tyagi",
                "19/04/2019 17:11:32",
                "O-",
                "10000",
                23
        );

        myAppealsModelArrayList.add(newAppeal);
        myAppealsModelArrayList.add(newAppeal);
        myAppealsModelArrayList.add(newAppeal);


        MyAppealListViewAdapter myAppealListViewAdapter = new MyAppealListViewAdapter(this, R.layout.myappeals_listview_layout, myAppealsModelArrayList);

        myAppealListView.setAdapter(myAppealListViewAdapter);
    }
}

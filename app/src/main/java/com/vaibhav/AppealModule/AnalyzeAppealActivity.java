package com.vaibhav.AppealModule;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vaibhav.R;
import com.vaibhav.adapter.MyAppealListViewAdapter;
import com.vaibhav.model.MyAppealsModel;

import java.util.ArrayList;

public class AnalyzeAppealActivity extends AppCompatActivity {

    ListView myAppealListView;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<MyAppealsModel> myAppealsModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze_appeal);

        myAppealsModelArrayList = new ArrayList<>();
        myAppealListView = findViewById(R.id.lvMyAppeals);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database  = FirebaseDatabase.getInstance();
        reference = database.getReference().child("appeals");

        final MyAppealListViewAdapter myAppealListViewAdapter = new MyAppealListViewAdapter(this, R.layout.myappeals_listview_layout, myAppealsModelArrayList);

//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
//                    if(snapshot.child("email").getValue().toString().equals(user.getEmail())){
////                        MyAppealsModel newAppeal = new MyAppealsModel(
////                                "1",
////                                "Haris Tyagi",
////                                "19/04/2019 17:11:32",
////                                "O-",
////                                "10000",
////                                23
////                        );
//                        MyAppealsModel myAppealsModel = new MyAppealsModel(
//                            snapshot.child("ID").getValue().toString(),
//                            snapshot.child("name").getKey().toString(),
//                        );
//                        myAppealsModelArrayList.add(newAppeal);
//                    }
//                }
//                myAppealListView.setAdapter(myAppealListViewAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        myAppealsModelArrayList.add(newAppeal);
//        myAppealsModelArrayList.add(newAppeal);
    }
}

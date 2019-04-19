package com.vaibhav.HelpModule;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vaibhav.AppealModule.PostAppealActivity;
import com.vaibhav.MainActivity;
import com.vaibhav.R;
import com.vaibhav.adapter.BloodListViewAdapter;
import com.vaibhav.model.BloodModel;

import java.util.ArrayList;

public class ViewAppealActivity extends AppCompatActivity {

    ListView lvBloodAppeal;
    ArrayList<BloodModel> list;
    FirebaseAuth auth;
    FirebaseUser user;

    FirebaseDatabase database;
    DatabaseReference reference;
    String userType = " ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appeal);

        lvBloodAppeal = (ListView) findViewById(R.id.lvBLoodAppeal);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String uID = user.getUid();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("users").child(uID);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userType = (String) dataSnapshot.child("type").getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

//        BloodListViewAdapter bloodListViewAdapter = new BloodListViewAdapter(this, list);
//
//        lvBloodAppeal.setAdapter(bloodListViewAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.post:
                startActivity(new Intent(getApplicationContext() , PostAppealActivity.class));
                return true;
            case R.id.logout:
                auth.signOut();
                startActivity(new Intent(getApplicationContext() , MainActivity.class));
                finish();
            case R.id.data:
                if(userType.equals("user")){
                    Toast.makeText(getApplicationContext() , "This feature is only for blood banks" , Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext() , "WOrking on it" , Toast.LENGTH_SHORT).show();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

package com.vaibhav.HelpModule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appeal);

        lvBloodAppeal = (ListView) findViewById(R.id.lvBLoodAppeal);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

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

        BloodModel b2 = new BloodModel(
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
        list.add(b2);
        list.add(b2);

        BloodListViewAdapter bloodListViewAdapter = new BloodListViewAdapter(this, R.layout.blood_appeal_listview_layout, list);

        lvBloodAppeal.setAdapter(bloodListViewAdapter);

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

package com.vaibhav.HelpModule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vaibhav.AppealModule.PostAppealActivity;
import com.vaibhav.MainActivity;
import com.vaibhav.R;

public class ViewAppealActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appeal);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
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

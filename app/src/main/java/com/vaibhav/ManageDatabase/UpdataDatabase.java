package com.vaibhav.ManageDatabase;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vaibhav.R;

public class UpdataDatabase extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;

    EditText An;
    EditText Ap;
    EditText Op;
    EditText On;
    EditText Bp;
    EditText Bn;
    EditText ABp;
    EditText ABn;
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_database);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#ff3333")));

        An = findViewById(R.id.An);
        Ap = findViewById(R.id.Ap);
        Op = findViewById(R.id.Op);
        On = findViewById(R.id.On);
        Bn = findViewById(R.id.Bn);
        Bp = findViewById(R.id.Bp);
        ABp = findViewById(R.id.ABn);
        ABn = findViewById(R.id.ABn);
        upload = findViewById(R.id.update);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("users").child(user.getUid());

        final String[] an = {"0"};
        final String[] ap = {"0"};
        final String[] op = {"0"};
        final String[] on = {"0"};
        final String[] bp = {"0"};
        final String[] bn = {"0"};
        final String[] abp = {"0"};
        final String[] abn = {"0"};

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                an[0] = An.getText().toString().trim();
                ap[0] = Ap.getText().toString().trim();
                op[0] = Op.getText().toString().trim();
                on[0] = On.getText().toString().trim();
                bp[0] = Bp.getText().toString().trim();
                bn[0] = Bn.getText().toString().trim();
                abp[0] = ABp.getText().toString().trim();
                abn[0] = ABn.getText().toString().trim();

                reference.child("Ap").setValue(an[0]);
                reference.child("An").setValue(an[0]);
                reference.child("Bp").setValue(bp[0]);
                reference.child("Bn").setValue((bn[0]));
                reference.child("Op").setValue(op[0]);
                reference.child("On").setValue(on[0]);
                reference.child("ABp").setValue(abp[0]);
                reference.child("ABn").setValue(abn[0]);

            }
        });


    }
}

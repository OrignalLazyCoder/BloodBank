package com.vaibhav.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vaibhav.R;
import com.vaibhav.model.BloodModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BloodListViewAdapter extends ArrayAdapter<BloodModel> {

    private Activity activity;
    private List<BloodModel> bloodModels;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference userRef;
    String name = " ";
    String mobile = " " ;
    String location = " ";
    Boolean canSayYes = true;

    public BloodListViewAdapter(Activity context , int resource , List<BloodModel> objects){
        super(context , resource ,objects);
        this.activity = context;
        this.bloodModels = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater)  activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        final BloodModel bloodModel = getItem(position);



        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.blood_appeal_listview_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        //set content

        holder.tvName.setText(bloodModel.getName());
        holder.tvBloodGroup.setText(bloodModel.getBloodType());
        holder.tvBloodUnit.setText(bloodModel.getBloodUnit());
        holder.tvPlateCount.setText(bloodModel.getPlateletsCount());
        holder.tvHospitalName.setText(bloodModel.getHospital());
        holder.tvTime.setText(bloodModel.getTime());
        holder.tvMobile.setText(bloodModel.getMobile());

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference().child("users").child(user.getUid());


        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = (String) dataSnapshot.child("Name").getValue();
                mobile = (String) dataSnapshot.child("mobile").getValue();
                location = (String) dataSnapshot.child("city").getValue();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + bloodModel.getMobile()));
                getContext().startActivity(intent);
            }
        });

        holder.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = database.getReference().child("appeals").child(bloodModel.getAppealId()).child("Response");
                canSayYes = true;
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot item_snapshot:dataSnapshot.getChildren()) {
                            String email = item_snapshot.child("userEmail").getValue().toString();
                            if(email.equals(user.getEmail())){
                                Log.d("Item ID" , bloodModel.getAppealId());
                                canSayYes = false;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Toast.makeText(activity, "Reference Key: "+ reference.getKey(), Toast.LENGTH_SHORT).show();
                if (canSayYes) {
                    String key = reference.push().getKey();
                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("userEmail", user.getEmail());
                    childUpdates.put("userMobile", mobile);
                    childUpdates.put("userName", name);
                    childUpdates.put("location" , location);

                    reference.child(key).child("userEmail").setValue(user.getEmail());
                    reference.child(key).child("userMobile").setValue(mobile);
                    reference.child(key).child("userName").setValue(name);
                    Log.d("key" , key);
                }
                else{
                    Toast.makeText(getContext() , "You have already volunteered for this"  , Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        // return the total number of view types. this value should never change
        // at runtime. Value 2 is returned because of left and right views.
        return 2;
    }




    private class ViewHolder {


        TextView tvName;
        TextView tvBloodGroup;
        TextView tvBloodUnit;

        TextView tvPlateCount;
        TextView tvHospitalName;

        TextView tvTime;
        TextView tvMobile;

        Button btnCall;
        Button btnYes;


        public ViewHolder(View v) {

            tvName = v.findViewById(R.id.tvName);
            tvBloodGroup = v.findViewById(R.id.tvBloodGroup);
            tvBloodUnit = v.findViewById(R.id.tvBloodUnit);

            tvPlateCount = v.findViewById(R.id.tvPlateletCount);

            tvHospitalName = v.findViewById(R.id.tvHospitalName);

            tvTime = v.findViewById(R.id.tvTime);
            tvMobile = v.findViewById(R.id.tvMobile);

            btnCall = v.findViewById(R.id.btnCall);
            btnYes = v.findViewById(R.id.btnYes);


        }
    }

}
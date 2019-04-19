package com.vaibhav.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.TextView;


import com.vaibhav.R;
import com.vaibhav.model.BloodModel;

import java.util.List;



public class BloodListViewAdapter extends ArrayAdapter<BloodModel> {

    private Activity activity;
    private List<BloodModel> bloodModels;

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
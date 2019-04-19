package com.vaibhav.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.vaibhav.R;
import com.vaibhav.model.BloodModel;

import java.util.ArrayList;

public class BloodListViewAdapter extends ArrayAdapter<BloodModel> {

    private final Context context;
    private final ArrayList<BloodModel> values;

    public BloodListViewAdapter(Context context, ArrayList<BloodModel> values) {
        super(context, R.layout.blood_appeal_listview_layout, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View bloodAppealListViewLayout = inflater.inflate(R.layout.blood_appeal_listview_layout, parent, false);


        // Initializing all fields

        TextView tvName = bloodAppealListViewLayout.findViewById(R.id.tvName);
        TextView tvBloodGroup = bloodAppealListViewLayout.findViewById(R.id.tvBloodGroup);
        TextView tvBloodUnit = bloodAppealListViewLayout.findViewById(R.id.tvBloodUnit);


        TextView tvPlateCount = bloodAppealListViewLayout.findViewById(R.id.tvPlateletCount);

        TextView tvLocation = bloodAppealListViewLayout.findViewById(R.id.tvLocation);
        TextView tvHospitalName = bloodAppealListViewLayout.findViewById(R.id.tvHospitalName);

        TextView tvTime = bloodAppealListViewLayout.findViewById(R.id.tvTime);
        TextView tvMobile = bloodAppealListViewLayout.findViewById(R.id.tvMobile);


        // Setting Data to fields

        tvName.setText(values.get(position).getName());
        tvBloodGroup.setText(values.get(position).getBloodType());
        tvBloodUnit.setText(values.get(position).getBloodUnit());

        if (values.get(position).isPlatelets())
            tvPlateCount.setText(values.get(position).getPlateletsCount());

        tvLocation.setText(values.get(position).getLocation());
        tvHospitalName.setText(values.get(position).getHospital());

        tvTime.setText(values.get(position).getTime());
        tvMobile.setText(values.get(position).getMobile());



        return super.getView(position, convertView, parent);
    }
}

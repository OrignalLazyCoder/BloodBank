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

import com.vaibhav.AppealModule.ViewMyDonorActivity;
import com.vaibhav.R;
import com.vaibhav.model.BloodModel;
import com.vaibhav.model.MyAppealsModel;

import java.util.List;

public class MyAppealListViewAdapter extends ArrayAdapter<MyAppealsModel> {

    private Activity activity;
    private List<MyAppealsModel> myAppealsList;

    private final String AVAILABLE_DONOR = "Available Donors: ";

    public MyAppealListViewAdapter(Activity context , int resource , List<MyAppealsModel> objects){
        super(context , resource ,objects);
        this.activity = context;
        this.myAppealsList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyAppealListViewAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater)  activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        final MyAppealsModel myAppeal = getItem(position);



        if (convertView != null) {
            holder = (MyAppealListViewAdapter.ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.myappeals_listview_layout, parent, false);
            holder = new MyAppealListViewAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }

        //set content

        holder.tvPatientName.setText(myAppeal.getAppealName());
        holder.tvAppealBloodGroup.setText(myAppeal.getAppealBloodGroup());
        holder.tvAppealBloodUnit.setText(myAppeal.getAppealBloodUnit());
        holder.tvAppealTime.setText(myAppeal.getAppealTime());

        holder.btnShowAvailableDonors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), ViewMyDonorActivity.class);

                intent.putExtra("appealId",myAppeal.getAppealId());

                getContext().startActivity(intent);

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


        TextView tvPatientName, tvAppealBloodGroup, tvAppealBloodUnit, tvAppealTime;

        Button btnShowAvailableDonors;


        public ViewHolder(View v) {

            tvPatientName = v.findViewById(R.id.tvPatientName);
            tvAppealBloodGroup = v.findViewById(R.id.tvAppealBloodGroup);
            tvAppealBloodUnit = v.findViewById(R.id.tvAppealBloodUnit);
            tvAppealTime = v.findViewById(R.id.tvAppealTime);

            btnShowAvailableDonors = v.findViewById(R.id.btnShowAvaibleDonors);


        }
    }
}

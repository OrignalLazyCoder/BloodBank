package com.vaibhav.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.vaibhav.R;
import com.vaibhav.model.AvailableDonorModel;
import com.vaibhav.model.MyAppealsModel;

import java.util.List;

public class AvailableDonorListViewAdapter extends ArrayAdapter<AvailableDonorModel> {

    private Activity activity;
    private List<AvailableDonorModel> availableDonorModelList;

    private final String AVAILABLE_DONOR = "Available Donors: ";

    public AvailableDonorListViewAdapter(Activity context , int resource , List<AvailableDonorModel> objects){
        super(context , resource ,objects);
        this.activity = context;
        this.availableDonorModelList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AvailableDonorListViewAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater)  activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        final AvailableDonorModel donorObj = getItem(position);



        if (convertView != null) {
            holder = (AvailableDonorListViewAdapter.ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.myappeals_listview_layout, parent, false);
            holder = new AvailableDonorListViewAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        }

        //set content

        holder.tvDonorName.setText(donorObj.getDonorName());
        holder.tvDonorLocation.setText(donorObj.getDonorLocation());
        holder.tvDonorMobile.setText(donorObj.getDonorMobile());

        holder.btnCallDonor.setOnClickListener(new View.OnClickListener() {
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


        TextView tvDonorName, tvDonorLocation, tvDonorMobile;

        Button btnCallDonor;


        public ViewHolder(View v) {

            tvDonorName = v.findViewById(R.id.tvDonorName);
            tvDonorLocation = v.findViewById(R.id.tvDonorLocation);
            tvDonorMobile = v.findViewById(R.id.tvDonorMobile);

            btnCallDonor = v.findViewById(R.id.btnCallDonor);
        }
    }
}

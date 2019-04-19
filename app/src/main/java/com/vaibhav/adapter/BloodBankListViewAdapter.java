package com.vaibhav.adapter;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.vaibhav.R;
import com.vaibhav.model.BloodBankModel;
import com.vaibhav.model.BloodModel;

import org.w3c.dom.Text;

import java.util.List;

public class BloodBankListViewAdapter extends ArrayAdapter<BloodBankModel> {
   private Activity activity;
   private List<BloodBankModel> bloodBankModel;

   public BloodBankListViewAdapter(Activity context , int resource , List<BloodBankModel> objects){
       super(context , resource , objects);
       this.activity = context;
       this.bloodBankModel = objects;
   }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater)  activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        final BloodBankModel bloodModel = getItem(position);

        if(convertView != null){
            holder = (ViewHolder) convertView.getTag();
        }
        else{
            convertView = inflater.inflate(R.layout.bloodbank_database_listview , parent , false);
            holder = new ViewHolder((convertView));
        }

        holder.tvName.setText(bloodModel.getName());
        holder.tvMobile.setText(bloodModel.getMobile());
        holder.tvAp.setText(bloodModel.getAp());
        holder.tvAn.setText(bloodModel.getAn());
        holder.tvBp.setText(bloodModel.getBp());
        holder.tvBn.setText(bloodModel.getBn());
        holder.tvOp.setText(bloodModel.getOp());
        holder.tvABp.setText(bloodModel.getABp());
        holder.tvOn.setText(bloodModel.getOn());
        holder.tvABn.setText(bloodModel.getABn());

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + bloodModel.getMobile()));
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

    private class ViewHolder{

        TextView tvAp;
        TextView tvAn;
        TextView tvBp;
        TextView tvBn;
        TextView tvOp;
        TextView tvOn;
        TextView tvABp;
        TextView tvABn;
        TextView tvMobile;
        TextView tvName;
        Button call;

        public ViewHolder(View v){
            tvAp = v.findViewById(R.id.tvAp);
            tvAn = v.findViewById(R.id.tvAn);
            tvBp = v.findViewById(R.id.tvBp);
            tvBn = v.findViewById(R.id.tvBn);
            tvOp = v.findViewById(R.id.tvOp);
            tvOn = v.findViewById(R.id.tvOn);
            tvABp = v.findViewById(R.id.tvABp);
            tvABn = v.findViewById(R.id.tvABn);
            tvMobile = v.findViewById(R.id.tvMobile);
            tvName = v.findViewById(R.id.tvName);
            call = v.findViewById(R.id.callBloodBank);


        }

    }


}
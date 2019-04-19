package com.vaibhav.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.vaibhav.model.BloodBankModel;

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
        return super.getView(position, convertView, parent);
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

        public ViewHolder(View v){
        }

    }


}
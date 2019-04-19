//package com.vaibhav.adapter;
//
//import android.app.Activity;
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.CheckBox;
//import android.widget.TextView;
//
//import com.vaibhav.R;
//import com.vaibhav.model.BloodModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BloodListViewAdapter extends ArrayAdapter<BloodModel> {
//
//    private final Context context;
//    private final ArrayList<BloodModel> values;
//
//    public BloodListViewAdapter(Context context, ArrayList<BloodModel> values) {
//        super(context, R.layout.blood_appeal_listview_layout, values);
//        this.context = context;
//        this.values = values;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, View convertView,   ViewGroup parent) {
//        ViewHolder holder;
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View bloodAppealListViewLayout = inflater.inflate(R.layout.blood_appeal_listview_layout, parent , false);
//
//
//        // Initializing all fields
//
//        TextView tvName = bloodAppealListViewLayout.findViewById(R.id.tvName);
//        TextView tvBloodGroup = bloodAppealListViewLayout.findViewById(R.id.tvBloodGroup);
//        TextView tvBloodUnit = bloodAppealListViewLayout.findViewById(R.id.tvBloodUnit);
//
//
//        TextView tvPlateCount = bloodAppealListViewLayout.findViewById(R.id.tvPlateletCount);
//
//        TextView tvLocation = bloodAppealListViewLayout.findViewById(R.id.tvLocation);
//        TextView tvHospitalName = bloodAppealListViewLayout.findViewById(R.id.tvHospitalName);
//
//        TextView tvTime = bloodAppealListViewLayout.findViewById(R.id.tvTime);
//        TextView tvMobile = bloodAppealListViewLayout.findViewById(R.id.tvMobile);
//
//
//        // Setting Data to fields
//
//        tvName.setText(values.get(position).getName());
//        tvBloodGroup.setText(values.get(position).getBloodType());
//        tvBloodUnit.setText(values.get(position).getBloodUnit());
//
//        if (values.get(position).isPlatelets())
//            tvPlateCount.setText(values.get(position).getPlateletsCount());
//
//        tvLocation.setText(values.get(position).getLocation());
//        tvHospitalName.setText(values.get(position).getHospital());
//
//        tvTime.setText(values.get(position).getTime());
//        tvMobile.setText(values.get(position).getMobile());
//
//
//
//        return super.getView(position, convertView, parent);
//    }
//}
package com.vaibhav.adapter;

        import android.app.Activity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;

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

        int layoutResource = 0;
        BloodModel bloodModel = getItem(position);
        int viewType = getItemViewType(position);

        if(true){

            layoutResource = R.layout.blood_appeal_listview_layout;

        }

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        //set content

        holder.tvName.setText(bloodModel.getName());
        holder.tvBloodGroup.setText(bloodModel.getBloodType());
        holder.tvBloodUnit.setText(bloodModel.getBloodUnit());
        holder.tvPlateCount.setText(bloodModel.getPlateletsCount());
        holder.tvLocation.setText(bloodModel.getLocation());
        holder.tvHospitalName.setText(bloodModel.getHospital());
        holder.tvTime.setText(bloodModel.getTime());
        holder.tvMobile.setText(bloodModel.getMobile());


        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        // return the total number of view types. this value should never change
        // at runtime. Value 2 is returned because of left and right views.
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // return a value between 0 and (getViewTypeCount - 1)
        return position %2 ;
    }


    private class ViewHolder {

        //private LinearLayout l;

        TextView tvName;
        TextView tvBloodGroup;
        TextView tvBloodUnit;


        TextView tvPlateCount;
        TextView tvLocation;
        TextView tvHospitalName;

        TextView tvTime;
        TextView tvMobile;


        public ViewHolder(View v) {

            tvName = v.findViewById(R.id.tvName);
            tvBloodGroup = v.findViewById(R.id.tvBloodGroup);
            tvBloodUnit = v.findViewById(R.id.tvBloodUnit);

            tvPlateCount = v.findViewById(R.id.tvPlateletCount);

            tvLocation = v.findViewById(R.id.tvLocation);
            tvHospitalName = v.findViewById(R.id.tvHospitalName);

            tvTime = v.findViewById(R.id.tvTime);
            tvMobile = v.findViewById(R.id.tvMobile);

            //l = v.findViewById(R.id.bg);
        }
    }

}
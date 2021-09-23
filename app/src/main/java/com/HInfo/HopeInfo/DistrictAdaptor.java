package com.HInfo.HopeInfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HInfo.HopeInfo.ui.covidTracker.state_model;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DistrictAdaptor extends RecyclerView.Adapter<DistrictAdaptor.Viewholder> {

    public DistrictAdaptor(ArrayList<state_model> arrayList) {
        this.arrayList = arrayList;
    }

    private ArrayList<state_model> arrayList;

    @NonNull
    @Override
    public DistrictAdaptor.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_layout,parent,  false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictAdaptor.Viewholder holder, int position) {

        state_model model = arrayList.get(position);

        DecimalFormat formatter = new DecimalFormat("#,###,###");

        int death = Integer.parseInt(model.getDeath().toString());
        String d = formatter.format(death);

        int recovered = Integer.parseInt(model.getRecovered().toString());
        String r = formatter.format(recovered);

        int total = Integer.parseInt(model.getTotal().toString());
        String t = formatter.format(total);

        int active = Integer.parseInt(model.getActive());
        String a = formatter.format(active);

        int incD = Integer.parseInt(model.getIncDeath());
        String inc_d = formatter.format(incD);

        int incR = Integer.parseInt(model.getIncRecovered());
        String inc_r = formatter.format(incR);

        int incC = Integer.parseInt(model.getIncConfirmed());
        String inc_c = formatter.format(incC);

        int incA = incC - (incD + incR);
        String inc_a = formatter.format(incA);

        holder.a_cases_tv.setText(""+a);
        holder.state_name.setText(model.getState_name());
        holder.c_cases_tv.setText(""+t);
        holder.d_cases_tv.setText(""+d);
        holder.r_cases_tv.setText(""+r);
        holder.inc_d_tv.setText(""+inc_d);
        holder.inc_r_tv.setText(""+inc_r);
        holder.inc_c_tv.setText(""+inc_c);
        holder.inc_a_tv.setText(""+inc_a);
    }

    @Override
    public int getItemCount() {
        return arrayList!=null?arrayList.size():0;
    }

    public void filterList(ArrayList<state_model> filterList) {
        arrayList = filterList;
        notifyDataSetChanged();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView state_name, c_cases_tv, a_cases_tv, r_cases_tv, d_cases_tv, inc_c_tv, inc_a_tv, inc_r_tv, inc_d_tv;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            state_name = itemView.findViewById(R.id.state_name);
            c_cases_tv = itemView.findViewById(R.id.c_cases_tv);
            a_cases_tv = itemView.findViewById(R.id.a_cases_tv);
            r_cases_tv = itemView.findViewById(R.id.r_cases_tv);
            d_cases_tv = itemView.findViewById(R.id.d_cases_tv);
            inc_a_tv = itemView.findViewById(R.id.inc_a_tv);
            inc_c_tv = itemView.findViewById(R.id.inc_c_tv);
            inc_r_tv = itemView.findViewById(R.id.inc_r_tv);
            inc_d_tv = itemView.findViewById(R.id.inc_d_tv);
        }
    }
}

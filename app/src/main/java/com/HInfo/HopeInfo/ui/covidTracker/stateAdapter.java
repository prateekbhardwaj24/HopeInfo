package com.HInfo.HopeInfo.ui.covidTracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HInfo.HopeInfo.DistrictData;
import com.HInfo.HopeInfo.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class stateAdapter extends RecyclerView.Adapter<stateAdapter.Viewholder> {

    private ArrayList<state_model> arrayList;
    private Context mContext;

    public stateAdapter(ArrayList<state_model> arrayList, Context mContext) {
        this.arrayList = arrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public stateAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_layout,parent,  false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull stateAdapter.Viewholder holder, int position) {

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

        String state_name = model.getState_name();

        holder.state_name.setText(model.getState_name());
        holder.d_cases_tv.setText(""+d);
        holder.r_cases_tv.setText(""+r);
        holder.c_cases_tv.setText(""+t);
        holder.a_cases_tv.setText(""+a);
        holder.inc_d_tv.setText(""+inc_d);
        holder.inc_r_tv.setText(""+inc_r);
        holder.inc_c_tv.setText(""+inc_c);
        holder.inc_a_tv.setText(""+inc_a);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DistrictData.class);
                intent.putExtra("state_name", state_name);
                mContext.startActivity(intent);
            }
        });

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

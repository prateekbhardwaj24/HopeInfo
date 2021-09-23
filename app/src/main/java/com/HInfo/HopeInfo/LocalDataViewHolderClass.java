package com.HInfo.HopeInfo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LocalDataViewHolderClass extends RecyclerView.ViewHolder {

    TextView name , date , time , address , contactNumber, info,  contactNo1, message;
    ImageView image;

    public LocalDataViewHolderClass(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        date = itemView.findViewById(R.id.date);
        time = itemView.findViewById(R.id.time);
        address = itemView.findViewById(R.id.address);
        contactNumber = itemView.findViewById(R.id.contactNumber);
        contactNo1 = itemView.findViewById(R.id.contactNo1);
        info = itemView.findViewById(R.id.info);
        image = itemView.findViewById(R.id.iv_image);
        message = itemView.findViewById(R.id.message);
    }
}

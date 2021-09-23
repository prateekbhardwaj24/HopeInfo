package com.HInfo.HopeInfo.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.HInfo.HopeInfo.R;
import com.HInfo.HopeInfo.licensesActivity;


public class About extends Fragment {

    private TextView licenses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_about, container, false);

        licenses = root.findViewById(R.id.licenses);

        licenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), licensesActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}
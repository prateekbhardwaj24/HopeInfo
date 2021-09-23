package com.HInfo.HopeInfo.ui.dashboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.HInfo.HopeInfo.Hospital_bedsActivity;
import com.HInfo.HopeInfo.MedicineAndInjections;
import com.HInfo.HopeInfo.Oxygen;
import com.HInfo.HopeInfo.R;
import com.HInfo.HopeInfo.showResources;

public class Dashboard_fragment extends Fragment {

    private SharedPreferences sharedPreferences = null;
    private CardView oxygen, medicine, hospital, plasma;
    private ImageView jump;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        oxygen = root.findViewById(R.id.oxygen);
        medicine = root.findViewById(R.id.medicine);
        hospital = root.findViewById(R.id.hospital);
        plasma = root.findViewById(R.id.plasma);
        jump = root.findViewById(R.id.jump);


        oxygen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Oxygen.class);
                startActivity(intent);
            }
        });

        plasma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), showResources.class);
                intent.putExtra("resources", "Plasma");
                startActivity(intent);
            }
        });

        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MedicineAndInjections.class));
            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Hospital_bedsActivity.class));
            }
        });


        return root;
    }
}
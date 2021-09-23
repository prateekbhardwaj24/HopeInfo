package com.HInfo.HopeInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

public class Hospital_bedsActivity extends AppCompatActivity {

    private CardView icu_beds, ventilator, o_beds;
    private NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_beds);
        setTitle("Hospital Beds");

        icu_beds = findViewById(R.id.icu_beds);
        ventilator = findViewById(R.id.ventilator);
        o_beds = findViewById(R.id.o_beds);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        icu_beds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hospital_bedsActivity.this, showResources.class);
                intent.putExtra("resources", "ICU Beds");
                startActivity(intent);
            }
        });

        ventilator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hospital_bedsActivity.this, showResources.class);
                intent.putExtra("resources", "Ventilator");
                startActivity(intent);
            }
        });
        o_beds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hospital_bedsActivity.this, showResources.class);
                intent.putExtra("resources", "Oxygen Beds");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();

    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}
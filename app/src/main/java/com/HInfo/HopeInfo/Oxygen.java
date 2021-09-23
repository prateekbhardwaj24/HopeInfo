package com.HInfo.HopeInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

public class Oxygen extends AppCompatActivity {

    private CardView o_cylinder, o_regulator, oximeter, o_concentrator;
    private NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxygen);

        setTitle("Oxygen");

        o_concentrator = findViewById(R.id.o_concentrator);
        o_cylinder = findViewById(R.id.o_cylinder);
        oximeter = findViewById(R.id.oximeter);
        o_regulator = findViewById(R.id.o_regulator);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        o_regulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Oxygen.this, showResources.class);
                intent.putExtra("resources", "Oxygen Regulator");
                startActivity(intent);
            }
        });
        oximeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Oxygen.this, showResources.class);
                intent.putExtra("resources", "Oximeter");
                startActivity(intent);
            }
        });

        o_cylinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Oxygen.this, showResources.class);
                intent.putExtra("resources", "Oxygen Cylinder");
                startActivity(intent);
            }
        });
        o_concentrator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Oxygen.this, showResources.class);
                intent.putExtra("resources", "Oxygen Concentrator");
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
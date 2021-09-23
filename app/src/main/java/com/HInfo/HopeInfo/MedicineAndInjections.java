package com.HInfo.HopeInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

public class MedicineAndInjections extends AppCompatActivity {

    private CardView remedesivir, Tocilizumab, Fabiflu, favipiravir, oral_pills_syrup;
    private NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_and_injections);
        setTitle("Medicine And Injections");

        remedesivir = findViewById(R.id.remedesivir);
        Tocilizumab = findViewById(R.id.Tocilizumab);
        Fabiflu = findViewById(R.id.Fabiflu);
        oral_pills_syrup = findViewById(R.id.oral_pills_syrup);
        favipiravir = findViewById(R.id.favipiravir);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        remedesivir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicineAndInjections.this, showResources.class);
                intent.putExtra("resources", "Remedesivir");
                startActivity(intent);
            }
        });

        Tocilizumab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicineAndInjections.this, showResources.class);
                intent.putExtra("resources", "Tocilizumab");
                startActivity(intent);
            }
        });
        Fabiflu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicineAndInjections.this, showResources.class);
                intent.putExtra("resources", "Fabiflu");
                startActivity(intent);
            }
        });
        oral_pills_syrup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicineAndInjections.this, showResources.class);
                intent.putExtra("resources", "Oral Pills and Syrup");
                startActivity(intent);
            }
        });
        favipiravir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicineAndInjections.this, showResources.class);
                intent.putExtra("resources", "Favipiravir");
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
package com.HInfo.HopeInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.HInfo.HopeInfo.ui.covidTracker.state_model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DistrictData extends AppCompatActivity {

    private RecyclerView district_recycler;
    private ArrayList<state_model> arrayList;
    private String state_name;
    private EditText district_et;
    private DistrictAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_data);

//        getSupportActionBar().hide();

        state_name = getIntent().getStringExtra("state_name").toString();

        setTitle(state_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        district_recycler = findViewById(R.id.district_recycler);
        district_et = findViewById(R.id.district_et);

        getdistrictDataFromApi();
        setAdaptor();

        district_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<state_model> filterList = new ArrayList<>();

        for (state_model item : arrayList){
            if (item.getState_name().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        adapter.filterList(filterList);
    }

    private void getdistrictDataFromApi() {

        arrayList = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, "https://api.covid19india.org/v2/state_district_wise.json",null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject dataOBJ = response.getJSONObject(i);
                                JSONArray jsonChild = dataOBJ.getJSONArray("districtData");

                                String state = dataOBJ.getString("state");

                                if (state.equals(state_name)){

                                    for (int k = 0; k < jsonChild.length(); k++) {

                                        JSONObject obj =  jsonChild.getJSONObject(k);
                                        JSONObject child = obj.getJSONObject("delta");

                                        arrayList.add(new state_model(obj.getString("district"), obj.getString("deceased"), obj.getString("recovered"), obj.getString("confirmed"), obj.getString("active"), child.getString("deceased"), child.getString("recovered"), child.getString("confirmed")));

                                    }setAdaptor();
                                }

                            }

                        } catch (Exception exp) {

                        }
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(DistrictData.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void setAdaptor() {

        district_recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DistrictAdaptor(arrayList);
        district_recycler.setAdapter(adapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
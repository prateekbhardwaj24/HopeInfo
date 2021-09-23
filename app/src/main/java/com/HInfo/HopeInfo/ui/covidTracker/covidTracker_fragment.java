package com.HInfo.HopeInfo.ui.covidTracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.HInfo.HopeInfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class covidTracker_fragment extends Fragment {

    private TextView c_cases_tv, a_cases_tv, r_cases_tv, d_cases_tv, inc_c_tv, inc_a_tv, inc_r_tv, inc_d_tv;
    private RecyclerView state_recycler;
    private ArrayList<state_model> arrayList;
    private EditText state_et;
    private stateAdapter adapter;
    private int flag = 0;
    private int flag1 = 0;
    private RequestQueue mq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_covid_tracker, container, false);

//        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        c_cases_tv = root.findViewById(R.id.c_cases_tv);
        a_cases_tv = root.findViewById(R.id.a_cases_tv);
        r_cases_tv = root.findViewById(R.id.r_cases_tv);
        d_cases_tv = root.findViewById(R.id.d_cases_tv);
        state_recycler = root.findViewById(R.id.state_recycler);
        state_et = root.findViewById(R.id.state_et);

        inc_c_tv = root.findViewById(R.id.inc_c_tv);
        inc_a_tv = root.findViewById(R.id.inc_a_tv);
        inc_r_tv = root.findViewById(R.id.inc_r_tv);
        inc_d_tv = root.findViewById(R.id.inc_d_tv);


        mq = Volley.newRequestQueue(getActivity());

//        getDataFromApi();
        getTotalDataFromApi();
//        getStateDataFromApi();
        getStateDataFromCovidApi();
        setAdaptor();

        state_et.addTextChangedListener(new TextWatcher() {
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

        return root;
    }

    private void getStateDataFromCovidApi() {

        String url = "https://api.covid19india.org/data.json";

        arrayList = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("statewise");
                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject st = jsonArray.getJSONObject(i);

                                String statename = st.getString("state");
                                if(statename.equals("Total"))
                                {
                                    flag1= 1;
                                }
                                else {
                                    arrayList.add(new state_model(statename, st.getString("deaths"), st.getString("recovered"), st.getString("confirmed"), st.getString("active"), st.getString("deltadeaths"), st.getString("deltarecovered"), st.getString("deltaconfirmed")));
                                }
                            }setAdaptor();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mq.add(request);
    }

    private void getTotalDataFromApi() {

        String url = "https://api.covid19india.org/data.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("statewise");
                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject st = jsonArray.getJSONObject(i);

                                DecimalFormat formatter = new DecimalFormat("#,###,###");

                                String statename = st.getString("state");
                                int active = Integer.parseInt(st.getString("active"));
                                String a = formatter.format(active);

                                int confirmed = Integer.parseInt(st.getString("confirmed"));
                                String c = formatter.format(confirmed);

                                int deaths = Integer.parseInt(st.getString("deaths"));
                                String d = formatter.format(deaths);

                                int recovered = Integer.parseInt(st.getString("recovered"));
                                String r = formatter.format(recovered);

                                int incDeaths = Integer.parseInt(st.getString("deltadeaths"));
                                String incD = formatter.format(incDeaths);

                                int incRecovered = Integer.parseInt(st.getString("deltarecovered"));
                                String incR = formatter.format(incRecovered);

                                int incConfirmed = Integer.parseInt(st.getString("deltaconfirmed"));
                                String incC = formatter.format(incConfirmed);

                                int incA = incConfirmed - (incDeaths + incRecovered);
                                String inc_a = formatter.format(incA);

                                if(statename.equals("Total"))
                                {
                                    flag= 1;
                                }

                                if(flag==1)
                                {
                                    flag=0;
                                    c_cases_tv.setText(""+c);
//                    a_cases_tv.setText(jsonObject.getString(""));
                                    a_cases_tv.setText(""+a);
                                    r_cases_tv.setText(""+r);
                                    d_cases_tv.setText(""+d);
                                    inc_c_tv.setText(""+incC);
                                    inc_d_tv.setText(""+incD);
                                    inc_r_tv.setText(""+incR);
                                    inc_a_tv.setText(""+inc_a);

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mq.add(request);
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

    private void setAdaptor() {

        state_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new stateAdapter(arrayList, getContext());
        state_recycler.setAdapter(adapter);

//        state_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        stateAdapter adapter = new stateAdapter(arrayList);
//        state_recycler.setAdapter(adapter);

    }

//    private void getStateDataFromApi() {
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//
//        String url = "https://api.rootnet.in/covid19-in/stats/latest";
//
//        arrayList = new ArrayList<>();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("regional");
//
//
//                    for (int i = 0; i<jsonArray.length(); i++){
//                        JSONObject data = jsonArray.getJSONObject(i);
//                        arrayList.add(new state_model(data.getString("loc"), data.getString("deaths"), data.getString("discharged"), data.getString("totalConfirmed")));
//                    }setAdaptor();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        requestQueue.add(stringRequest);
//    }

    private void getDataFromApi() {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        String url = "https://api.rootnet.in/covid19-in/stats/latest";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response).getJSONObject("data").getJSONObject("summary");


                    DecimalFormat formatter = new DecimalFormat("#,###,###");

                    int death = Integer.parseInt(jsonObject.getString("deaths").toString());
                    String d = formatter.format(death);

                    int recovered = Integer.parseInt(jsonObject.getString("discharged").toString());
                    String r = formatter.format(recovered);

                    int total = Integer.parseInt(jsonObject.getString("total").toString());
                    String t = formatter.format(total);

                    int active = total - (death + recovered);
                    String a = formatter.format(active);

                    c_cases_tv.setText(""+t);
//                    a_cases_tv.setText(jsonObject.getString(""));
                    a_cases_tv.setText(""+a);
                    r_cases_tv.setText(""+r);
                    d_cases_tv.setText(""+d);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}
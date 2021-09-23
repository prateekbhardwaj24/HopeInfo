package com.HInfo.HopeInfo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DataFromUsers extends Fragment {

    private AutoCompleteTextView autoCompleteTextView ;
    private ImageView search_button;
    private TextView noData;
    RecyclerView rec_view;
    FirebaseRecyclerAdapter<LocalDataMadelClass, LocalDataViewHolderClass> adapter1;
    DatabaseReference mDatabase;
    private String resources;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_data_from_users, container, false);

        resources = getActivity().getIntent().getStringExtra("resources").toString();

        autoCompleteTextView = root.findViewById(R.id.searchEditText);
        search_button =  root.findViewById(R.id.search_button);
        rec_view = root.findViewById(R.id.rec_view);
        noData = root.findViewById(R.id.noData);
        rec_view.setLayoutManager(new LinearLayoutManager(getContext()));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Posts");


        ArrayList<String> CityName = new ArrayList<>(Arrays.asList("Adilabad", "Anantapur", "Chittoor", "Kakinada", "Guntur", "Hyderabad", "Karimnagar",
                "Khammam", "Krishna", "Kurnool", "Mahbubnagar", "Medak", "Nalgonda", "Nizamabad", "Ongole", "Hyderabad", "Srikakulam", "Nellore", "Visakhapatnam", "Vizianagaram", "Warangal", "Eluru", "Kadapa", "Araria", "Aurangabad", "Arwal", "Banka", "Begusarai","Bhagalpur", "Bhojpur", "Buxar", "Darbhanga",
                "East Champaran", "Gaya","Gopalganj", "Jamui", "Jehanabad", "Kaimur", "Katihar", "Khagaria", "Kishanganj", "Lakhisarai", "Madhepura", "Madhubani", "Munger", "Muzaffarpur", "Nalanda", "Nawada", "Patna", "Purnia", "Rohtas",
                "Saharsa",
                "Samastipur",
                "Saran",
                "Sheikhpura",
                "Sheohar",
                "Sitamarhi",
                "Siwan",
                "Supaul",
                "Vaishali",
                "West Champaran",
                "Chandigarh",
                "Bastar",
                "Bijapur",
                "Bilaspur",
                "Dantewada",
                "Dhamtari",
                "Durg",
                "Jashpur",
                "Janjgir-Champa",
                "Korba",
                "Koriya",
                "Kanker",
                "Kabirdham (Kawardha)",
                "Mahasamund",
                "Narayanpur",
                "Raigarh",
                "Rajnandgaon",
                "Raipur",
                "Surguja", "Central Delhi",
                "East Delhi",
                "New Delhi",
                "North Delhi",
                "North East Delhi",
                "North West Delhi",
                "South Delhi",
                "South West Delhi",
                "West Delhi", "North Goa",
                "South Goa", "Ahmedabad",
                "Amreli district",
                "Anand",
                "Banaskantha",
                "Bharuch",
                "Bhavnagar",
                "Dahod",
                "The Dangs",
                "Gandhinagar",
                "Jamnagar",
                "Junagadh",
                "Kutch",
                "Kheda",
                "Mehsana",
                "Narmada",
                "Navsari",
                "Patan",
                "Panchmahal",
                "Porbandar",
                "Rajkot",
                "Sabarkantha",
                "Surendranagar",
                "Surat",
                "Vyara",
                "Vadodara",
                "Valsad", "Ambala",
                "Bhiwani",
                "Faridabad",
                "Fatehabad",
                "Gurgaon",
                "Hissar",
                "Jhajjar",
                "Jind",
                "Karnal",
                "Kaithal",
                "Kurukshetra",
                "Mahendragarh",
                "Mewat",
                "Palwal",
                "Panchkula",
                "Panipat",
                "Rewari",
                "Rohtak",
                "Sirsa",
                "Sonipat",
                "Yamuna Nagar",
                "Agra",
                "Allahabad",
                "Aligarh",
                "Ambedkar Nagar",
                "Auraiya",
                "Azamgarh",
                "Barabanki",
                "Budaun",
                "Bagpat",
                "Bahraich",
                "Bijnor",
                "Ballia",
                "Banda",
                "Balrampur",
                "Bareilly",
                "Basti",
                "Bulandshahr",
                "Chandauli",
                "Chhatrapati Shahuji Maharaj Nagar",
                "Chitrakoot",
                "Deoria",
                "Etah",
                "Kanshi Ram Nagar",
                "Etawah",
                "Firozabad",
                "Farrukhabad",
                "Fatehpur",
                "Faizabad",
                "Gautam Buddh Nagar",
                "Gonda",
                "Ghazipur",
                "Gorakhpur",
                "Ghaziabad",
                "Hamirpur",
                "Hardoi",
                "Mahamaya Nagar",
                "Jhansi",
                "Jalaun",
                "Jyotiba Phule Nagar",
                "Jaunpur district",
                "Ramabai Nagar (Kanpur Dehat)",
                "Kannauj",
                "Kanpur",
                "Kaushambi",
                "Kushinagar",
                "Lalitpur",
                "Lakhimpur Kheri",
                "Lucknow",
                "Mau",
                "Meerut",
                "Maharajganj",
                "Mahoba",
                "Mirzapur",
                "Moradabad",
                "Mainpuri",
                "Mathura",
                "Muzaffarnagar",
                "Panchsheel Nagar district (Hapur)",
                "Pilibhit",
                "Shamli",
                "Pratapgarh",
                "Rampur",
                "Raebareli",
                "Saharanpur",
                "Sitapur",
                "Shahjahanpur",
                "Sant Kabir Nagar",
                "Siddharthnagar",
                "Sonbhadra",
                "Sant Ravidas Nagar",
                "Sultanpur",
                "Shravasti",
                "Unnao",
                "Varanasi"));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item, CityName);
        autoCompleteTextView.setAdapter(adapter);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!autoCompleteTextView.getText().toString().isEmpty())
                {
                    String city = autoCompleteTextView.getText().toString();

                    mDatabase.child(resources).child(city).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (!snapshot.exists()){
                               noData.setVisibility(View.VISIBLE);
                            }

                            else {
                                noData.setVisibility(View.GONE);
                                FirebaseRecyclerOptions<LocalDataMadelClass> options = new FirebaseRecyclerOptions.Builder<LocalDataMadelClass>().setQuery(mDatabase.child(resources).child(city), LocalDataMadelClass.class)
                                        .build();
                                adapter1 = new FirebaseRecyclerAdapter<LocalDataMadelClass, LocalDataViewHolderClass>(options) {
                                    @Override
                                    protected void onBindViewHolder(@NonNull LocalDataViewHolderClass holder, int position, @NonNull LocalDataMadelClass model) {
                                        String fName = model.getFirstName();
                                        String lName = model.getLastName();
                                        String image = model.getUpload_image();
                                        String pincode = model.getPincode();
                                        String phone1 = model.getPhone1();
                                        String phone2 = model.getPhone2();
                                        String timeStamp = model.getCurrentTime();
                                        String landMark = model.getLandmark();
                                        String city = model.getCity();
                                        String locality = model.getLocality();
                                        String state = model.getState();
                                        String spinner = model.getSpinner();
                                        String message = model.getWriteMessage();

//                            long t = Long.parseLong(timeStamp);
                                        Calendar calendar = Calendar.getInstance();
                                        TimeZone tz = TimeZone.getDefault();
//                            calendar.setTimeInMillis(t*1000);
                                        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm a");

                                        String dateString = formatter.format(new Date(Long.parseLong(timeStamp)));
                                        String dateString1 = formatter1.format(new Date(Long.parseLong(timeStamp)));
                                        holder.time.setText(dateString1);
                                        holder.date.setText(dateString);

                                        holder.name.setText(fName+" "+lName);
                                        holder.info.setText(spinner);
                                        holder.address.setText(landMark+","+locality+" , "+city+" - "+pincode+" , "+state);
                                        holder.contactNumber.setText(phone1);
                                        holder.contactNo1.setText(phone2);
                                        holder.message.setText(message);

                                        holder.contactNo1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                ClipboardManager cm = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                                                cm.setText(phone2);
                                                Toast.makeText(getActivity(), "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        holder.contactNumber.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                ClipboardManager cm = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                                                cm.setText(phone1);
                                                Toast.makeText(getActivity(), "Copied To Clipboard", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        holder.image.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Dialog dialog = new Dialog(getContext() , android.R.style.Theme_NoTitleBar_Fullscreen);
                                                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                                                dialog.setContentView(getLayoutInflater().inflate(R.layout.show_image_dialog , null));
                                                ImageView i = dialog.findViewById(R.id.dialog_image);
                                                Picasso.get().load(image).into(i);
                                                dialog.show();

                                            }
                                        });

                                        if (!image.equals("noImage"))
                                        {

                                            Picasso.get().load(image).placeholder(R.drawable.hospital_bed).into(holder.image);
                                            holder.image.setVisibility(View.VISIBLE);


                                        }else
                                        {
                                            holder.image.setVisibility(View.GONE);
                                        }
                                    }

                                    @NonNull
                                    @Override
                                    public LocalDataViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_layout, parent, false);
                                        return  new LocalDataViewHolderClass(v);
                                    }
                                };
                                adapter1.notifyDataSetChanged();
                                adapter1.startListening();
                                rec_view.setAdapter(adapter1);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }
                else
                {
                    Toast.makeText(getContext(), "Please Enter City Name First...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }
}
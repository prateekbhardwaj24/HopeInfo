package com.HInfo.HopeInfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class twitterDataFragment extends Fragment {

    private ImageView search_button ;
    private WebView web_view;
    private AutoCompleteTextView autoCompleteTextView;
    private String resources;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_twitter_data, container, false);

        resources = getActivity().getIntent().getStringExtra("resources").toString();

        autoCompleteTextView = root.findViewById(R.id.searchEditText);
        search_button =  root.findViewById(R.id.search_button);
        web_view =  root.findViewById(R.id.web_view);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!autoCompleteTextView.getText().toString().isEmpty()){
                    String searchText = autoCompleteTextView.getText().toString().trim();
                    web_view.setWebViewClient(new WebViewClient());
                    web_view.loadUrl("https://mobile.twitter.com/search?q=Min_faves%3A20%20"+resources+"%20"+searchText+"%20Verified&src=recent_search_click");
                    WebSettings settings = web_view.getSettings();
                    settings.setJavaScriptEnabled(true);
                    web_view.setVisibility(View.VISIBLE);
                }else
                {
                    Toast.makeText(getContext(), "Please Enter City Name First..", Toast.LENGTH_SHORT).show();
                }
            }
        });


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

        return root;
    }
}
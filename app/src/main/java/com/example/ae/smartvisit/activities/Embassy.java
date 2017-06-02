package com.example.ae.smartvisit.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.modules.PlaceDataModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Embassy extends BaseActivity {

    @Bind(R.id.activity_embassy_list)
    ListView activityEmbassyList;
    private EmbassyListAdapter adapter;
    private ArrayList<PlaceDataModel> embassies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embassy);
        ButterKnife.bind(this);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setupListWithData();


        activityEmbassyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent passedIntent = new Intent(getApplicationContext(),EmbassyInformation.class);
                passedIntent.putExtra("embassy",embassies.get(i));
                startActivity(passedIntent);
            }
        });
        getSupportActionBar().setTitle("Embassy");

        if(!isNetworkAvailable()){
            Toast.makeText(application, "No connection!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupListWithData() {
        embassies = new ArrayList<>();
        embassies.add(new PlaceDataModel("Embassy of the United States of America","",
                "","Palace of Dupara, Kasr El Nil, Cairo Governorate","","30.041119,31.233634","16872","",0));


        embassies.add(new PlaceDataModel("United Arab Emirates Embassy","",
                ""," 4 of Mourad ST, Giza Governorate","","30.025791, 31.217262","02 37766102","",0));


        embassies.add(new PlaceDataModel("Embassy of the United Kingdom","",
                "","11451,7 Ahmed Ragheb, Qasr El Doubara, Kasr El Nil, Cairo Governorate","","30.039092, 31.230405","02 27916000","",0));


        embassies.add(new PlaceDataModel("Embassy of the Republic of India","",
                "","Aziz Abaza, Mohamed Mazhar, Zamalek, Giza","","30.060027, 31.224263","02 27356053","",0));

        adapter = new EmbassyListAdapter(embassies);
        activityEmbassyList.setAdapter(adapter);


    }

    private class EmbassyListAdapter extends BaseAdapter{
        ArrayList<PlaceDataModel> listOfEmbassies;

        EmbassyListAdapter(ArrayList<PlaceDataModel> listOfEmbassies) {
            this.listOfEmbassies = listOfEmbassies;
        }

        @Override
        public int getCount() {
            return listOfEmbassies.size();
        }

        @Override
        public Object getItem(int i) {
            return listOfEmbassies.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View root = LayoutInflater.from(getApplicationContext()).inflate(android.R.layout.simple_list_item_1,viewGroup,false);
            TextView title = (TextView) root.findViewById(android.R.id.text1);
            title.setTextColor(Color.BLACK);
            title.setText(i + "- " + listOfEmbassies.get(i).getName());

            return root;

        }
    }
}

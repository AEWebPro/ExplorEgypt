package com.example.ae.smartvisit.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.modules.PlaceDataModel;
import com.example.ae.smartvisit.modules.RecommandListModule;
import com.example.ae.smartvisit.modules.RecommandProgramsListModule;

import java.util.ArrayList;
import java.util.List;

public class RecommandA extends BaseActivity {
    Intent intent;
    String numberOfPlane;
    RecommandProgramsListModule planeDisplayed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand_a);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ListView list=(ListView)findViewById(R.id.recommand_list);


        intent=getIntent();
        numberOfPlane = intent.getExtras().getString("number");
        planeDisplayed = intent.getParcelableExtra("plane");

        list.setAdapter(new ListResources(this, planeDisplayed.getListOfPlacesInPlan()));

        getSupportActionBar().setTitle(planeDisplayed.getName());
    }

    class ListResources extends BaseAdapter {
        //ArrayList<RecommandListModule> mydata;
        Context context;
        List<PlaceDataModel> plcesInPlane;
        ListResources(Context context, List<PlaceDataModel> placesInPlane) {

            this.context = context;
            this.plcesInPlane = placesInPlane;

            /* mydata = new ArrayList<RecommandListModule>();

            if (numberOfPlane.equals("0")) {
                mydata.add(new RecommandListModule("Abu Simble ", " 12:00 ", R.drawable.abusimble));
                mydata.add(new RecommandListModule("temple of karnak ", " 9:00 PM ", R.drawable.templeofkarnak));
                mydata.add(new RecommandListModule("Temple of Horus  ", " 2:00 ", R.drawable.templeofhorus));

            }
            else if (numberOfPlane.equals("1"))
            {

                mydata.add(new RecommandListModule("temple of karnak ", " 9:00 PM ", R.drawable.templeofkarnak));
                mydata.add(new RecommandListModule("Temple of Horus  ", " 2:00 ", R.drawable.templeofhorus));
                mydata.add(new RecommandListModule("Abu Simble ", " 12:00 ", R.drawable.abusimble));
            }

            else if (numberOfPlane.equals("2"))
            {


                mydata.add(new RecommandListModule("Temple of Horus  ", " 2:00 ", R.drawable.templeofhorus));
                mydata.add(new RecommandListModule("temple of karnak ", " 9:00 PM ", R.drawable.templeofkarnak));
                mydata.add(new RecommandListModule("Abu Simble ", " 12:00 ", R.drawable.abusimble));
            }*/
        }

        @Override
        public int getCount() {
            return plcesInPlane.size();
        }

        @Override
        public Object getItem(int position) {
            return plcesInPlane.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.activity_recommand_list,parent,false);
            TextView title=(TextView) row.findViewById(R.id.textTitle);
            ImageView image= (ImageView) row.findViewById(R.id.imageView);
            PlaceDataModel place = plcesInPlane.get(position);

            title.setText(place.getName());//variables from RecommandListModule.java
            image.setImageResource(R.mipmap.ic_launcher);

            return row;
        }
    }
}

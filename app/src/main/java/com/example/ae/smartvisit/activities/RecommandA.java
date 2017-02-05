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
import com.example.ae.smartvisit.modules.RecommandListModule;

import java.util.ArrayList;

public class RecommandA extends BaseActivity {
    Intent intent;
    String s;
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

        s = intent.getExtras().getString("number");
        list.setAdapter(new ListResources(this));

        getSupportActionBar().setTitle(s);
    }

    class ListResources extends BaseAdapter {
        ArrayList<RecommandListModule> mydata;
        Context context;
        ListResources(Context context) {




            this.context = context;

            mydata = new ArrayList<RecommandListModule>();


            if (s.equals("0")) {
                mydata.add(new RecommandListModule("Abu Simble ", " 12:00 ", R.drawable.abusimble));
                mydata.add(new RecommandListModule("temple of karnak ", " 9:00 PM ", R.drawable.templeofkarnak));
                mydata.add(new RecommandListModule("Temple of Horus  ", " 2:00 ", R.drawable.templeofhorus));

            }
            else if (s.equals("1"))
            {

                mydata.add(new RecommandListModule("temple of karnak ", " 9:00 PM ", R.drawable.templeofkarnak));
                mydata.add(new RecommandListModule("Temple of Horus  ", " 2:00 ", R.drawable.templeofhorus));
                mydata.add(new RecommandListModule("Abu Simble ", " 12:00 ", R.drawable.abusimble));
            }

            else if (s.equals("2"))
            {


                mydata.add(new RecommandListModule("Temple of Horus  ", " 2:00 ", R.drawable.templeofhorus));
                mydata.add(new RecommandListModule("temple of karnak ", " 9:00 PM ", R.drawable.templeofkarnak));
                mydata.add(new RecommandListModule("Abu Simble ", " 12:00 ", R.drawable.abusimble));
            }
        }

        @Override
        public int getCount() {
            return mydata.size();
        }

        @Override
        public Object getItem(int position) {
            return mydata.get(position);
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
            TextView time =(TextView) row.findViewById(R.id.textTime);
            ImageView image= (ImageView) row.findViewById(R.id.imageView);
            RecommandListModule temp=mydata.get(position);

            title.setText(temp.getName());//variables from RecommandListModule.java
            time.setText(temp.getTime());
            image.setImageResource(temp.getImage());

            return row;
        }
    }
}

package com.example.ae.smartvisit.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.modules.PlaceDataModel;
import com.example.ae.smartvisit.modules.RecommandProgramsListModule;

import java.util.ArrayList;
import java.util.List;

public class RecommandPrograms extends BaseActivity {

    List<PlaceDataModel> placesInPlan = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand_programs);

        getSupportActionBar().setTitle("Recommended Plans");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        placesInPlan.add(0,new PlaceDataModel("Pyramids","","","",0,"",""));
        placesInPlan.add(1,new PlaceDataModel("The Sea","","","",1,"",""));
        placesInPlan.add(2,new PlaceDataModel("The Cairo Tower","","","",2,"",""));


        ListView list=(ListView)findViewById(R.id.recommand_programs);
        list.setAdapter(new ListResources(this));

    }




            class ListResources extends BaseAdapter {
                ArrayList<RecommandProgramsListModule> mydata = new ArrayList<RecommandProgramsListModule>(); ;
                Context context;
                ListResources(Context context)
                {
                    this.context=context ;
                    mydata.add(new RecommandProgramsListModule("Program (A) ", R.drawable.view, placesInPlan));
                    mydata.add(new RecommandProgramsListModule("Program (B) ", R.drawable.view, placesInPlan));
                    mydata.add(new RecommandProgramsListModule("Program (C) ", R.drawable.view, placesInPlan));

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
                public View getView(final int position, View convertView, ViewGroup parent) {

                    LayoutInflater inflater=getLayoutInflater();
                    final View row=inflater.inflate(R.layout.activity_recommand_programs_list,parent,false);
                    final TextView title=(TextView) row.findViewById(R.id.textTitle1);
                    final ImageButton image= (ImageButton) row.findViewById(R.id.imageButton1);
                    RecommandProgramsListModule temp=mydata.get(position);

                    title.setText(temp.getName());//variables from RecommandProgramList.java
                    image.setImageResource(temp.getImage());

                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(RecommandPrograms.this, RecommandA.class);
                            i.putExtra("number", Integer.toString(position));
                            i.putExtra("plane",mydata.get(position));
                            startActivity(i);

                        }
                    });

 /*                   image.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v) {



                            if (v ==image) {

                                switch (position) {
                                    case 0:


                                        Intent i = new Intent(RecommandPrograms.this, RecommandA.class);
                                        i.putExtra("number", "0");
                                        startActivity(i);
                                        break;

                                    case 1:
                                        Intent ii = new Intent(RecommandPrograms.this, RecommandA.class);
                    ii.putExtra("number", "1");
                    startActivity(ii);
                    break;

                case 2:
                    Intent iii = new Intent(RecommandPrograms.this, RecommandA.class);
                    iii.putExtra("number", "2");
                    startActivity(iii);
                    break;
            }

        }
    }});
title.setOnClickListener(new View.OnClickListener(){
    public void onClick(View v) {




            if (v == title ) {
                switch (position) {
                case 0:


                    Intent i = new Intent(RecommandPrograms.this, RecommandA.class);
                    i.putExtra("number", "0");
                    startActivity(i);
                    break;



                case 1:
                    Intent ii = new Intent(RecommandPrograms.this, RecommandA.class);
                    ii.putExtra("number", "1");
                    startActivity(ii);
                    break;

                case 2:
                    Intent iii = new Intent(RecommandPrograms.this, RecommandA.class);
                    iii.putExtra("number", "2");
                    startActivity(iii);
                    break;
            }

        }




    }});
*/
return row;
}

    }
}

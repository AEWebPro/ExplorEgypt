package com.example.ae.smartvisit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ae.smartvisit.R;

public class Planes extends BaseActivity implements View.OnClickListener {

  Button your_id,recommand_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planes);
        getSupportActionBar().setTitle("Planes");

        your_id=(Button)findViewById(R.id.your_id);
        recommand_id=(Button)findViewById(R.id.recommand_id);

        your_id.setOnClickListener(this);
        recommand_id.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==your_id) {

            Intent i = new Intent(Planes.this,YourPlanes.class );
            startActivity(i);
        }
        else if(v==recommand_id){

            Intent i = new Intent(Planes.this,RecommandPrograms.class );
            startActivity(i);
        }

    }
}

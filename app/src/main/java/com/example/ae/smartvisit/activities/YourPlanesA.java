package com.example.ae.smartvisit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.modules.RecommandProgramsListModule;

import java.util.ArrayList;

public class YourPlanesA extends BaseActivity {

    private String passedTitle;
    private TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_planes_a);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        titleText = (TextView) findViewById(R.id.activity_your_plan_title_text);
        RecommandProgramsListModule planeDisplayed;
        Intent intent;
        intent = getIntent();
        passedTitle = intent.getExtras().getString("number");
        planeDisplayed = intent.getParcelableExtra("plane");

        titleText.setText(planeDisplayed.getListOfPlacesInPlan().get(1).getName());

        getSupportActionBar().setTitle(passedTitle);
    }
}
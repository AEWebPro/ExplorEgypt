package com.example.ae.smartvisit.activities;

import android.os.Bundle;
import android.view.View;

import com.example.ae.smartvisit.R;

public class Currency extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getSupportActionBar().setTitle("Currency");
    }
}

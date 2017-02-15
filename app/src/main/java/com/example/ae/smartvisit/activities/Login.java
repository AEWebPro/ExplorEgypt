package com.example.ae.smartvisit.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ae.smartvisit.R;

public class Login extends AppCompatActivity  {

    EditText name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);

    }


    public void login(View v) {

        if (name.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(Login.this, "all of the fields are required ", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Login.this, "welcome", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Login.this, HomeActivity.class);
            startActivity(i);
            finish();

        }
    }
    public void signup(View v) {
        Intent i = new Intent(Login.this,Signup .class);
        startActivity(i);
        finish();
    }
}

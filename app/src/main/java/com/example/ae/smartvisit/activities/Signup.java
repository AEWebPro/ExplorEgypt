package com.example.ae.smartvisit.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ae.smartvisit.R;

public class Signup extends AppCompatActivity  {

    EditText name,password,confirm_password,email;
    ImageButton imageperson;

    public final static   int SELECTED_PICTURE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        imageperson=(ImageButton)findViewById(R.id.imgperson);
        name=(EditText)findViewById(R.id.name_signup);
        password=(EditText)findViewById(R.id.password_signup);
        confirm_password=(EditText)findViewById(R.id.confirm_password_signup);
        email=(EditText)findViewById(R.id.email_signup);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SELECTED_PICTURE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
                    Drawable d = new BitmapDrawable(yourSelectedImage);

                    imageperson.setBackground(d);

                }
                break;

            default:
                break;
        }
    }



    public void image(View v) {


            Intent select = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(select, SELECTED_PICTURE);


    }




        public void save(View v) {

        if(imageperson.getDrawable()== null || ( password.getText().toString().isEmpty())||(confirm_password.getText().toString().isEmpty())||(email.getText().toString().isEmpty()))
        {
            Toast.makeText(Signup.this, "all of the fields are required ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(password.getText().toString().equals(confirm_password.getText().toString())) {

                Intent i = new Intent(Signup.this,Login.class);
                startActivity(i);

            }
            else
            {
                Toast.makeText(Signup.this, "the password doesn't match ", Toast.LENGTH_SHORT).show();
            }
        }

    }
}

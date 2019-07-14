package com.example.quicklancher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // activity inside our app
        Button SeconfdActivityBtn = (Button) findViewById(R.id.SeconfdActivity);

        SeconfdActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), SecondActivity.class);
                startIntent.putExtra("com.example.quicklancher.message", "message by activity!");
                startActivity(startIntent);
            }
        });

        // activity outside our app

        Button googleBtn = (Button) findViewById(R.id.buttonGoogle);
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String google = "https://www.google.com";
                Uri webaddress = Uri.parse(google);

                Intent gotoGoogle = new Intent(Intent.ACTION_VIEW, webaddress);
                if(gotoGoogle.resolveActivity(getPackageManager())!= null){
                    startActivity(gotoGoogle);
                }
            }
        });
    }
}

package com.inti.student.countrylogoquiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Welcome extends AppCompatActivity {

    private static int SPLASH_OUT_TIME = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(Welcome.this, MenuActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_OUT_TIME);
    }
}


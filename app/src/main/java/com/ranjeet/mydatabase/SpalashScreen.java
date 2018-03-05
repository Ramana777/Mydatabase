package com.ranjeet.mydatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by admin on 2/26/2018.
 */

public class SpalashScreen extends Activity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spalashscreen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent i = new Intent(SpalashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
        }



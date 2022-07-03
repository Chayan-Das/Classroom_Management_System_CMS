package com.example.classroommanagementsystemcms;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5920;

    //variables

    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        lottieAnimationView = findViewById(R.id.loading_splash);



        //lottieAnimationView.animate().translationX(-1600).setDuration(300).setStartDelay(5920);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



                show();

            }
        },SPLASH_SCREEN);

    }

    private void show() {



        if(isConnected(this)){

            lottieAnimationView.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }



    private boolean isConnected(SplashScreen splashScreen) {
        ConnectivityManager connectivityManager = (ConnectivityManager) splashScreen.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wificon = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobiledata = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wificon != null && wificon.isConnected()) || mobiledata != null && mobiledata.isConnected())
        {
            return true;
        }

        else {
            return false;
        }
    }


}
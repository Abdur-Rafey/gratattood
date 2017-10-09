package com.gratattood.gratattood;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private Boolean isLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startNextActivity();
            }
        }, 2000);
    }

    private void startNextActivity(){
        if (checkLogInStatus()){
//            Intent gotoMainActivity = new Intent(getBaseContext(), MainActivity.class);
//            startActivity(gotoMainActivity);
        } else {
            Intent gotoLogInActivity = new Intent(getBaseContext(), SignInActivity.class);
            startActivity(gotoLogInActivity);
        }
        finish();
    }

    private Boolean checkLogInStatus(){
        return false;
    }
}

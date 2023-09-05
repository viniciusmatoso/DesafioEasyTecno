package com.example.desafioeasytecno.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.desafioeasytecno.R;
import com.example.desafioeasytecno.controller.PostController;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DURATION = 4000;
    private PostController postController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        preferences();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DURATION);
    }

    private void preferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean firstRun = sharedPreferences.getBoolean("firstRun", true);

        if(firstRun){
            postController = new PostController(this);
            postController.insertAllPosts();
            editor.putBoolean("firstRun", false);
            editor.apply();
        }
    }
}
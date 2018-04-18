/*
 * Copyright (c) 2018. This app is work of great ideas and reference to other online resources. If you will have to make use of any part of the content please go on but don't hesitate to reference me @github.com/adelaadeoye
 */

package com.example.dell.tictoe;import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       startActivity(new Intent(SplashActivity.this,MainActivity.class));
       finish();
    }
}

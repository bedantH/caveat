package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class register_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_page);
    }
}
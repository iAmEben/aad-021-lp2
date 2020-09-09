package com.cavalerie.aad_021_lp2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import com.cavalerie.aad_021_lp2.R;

public class SubmitActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private ImageView arrowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        initID();
        initAction();
    }

    @SuppressLint("RestrictedApi")
    private void initAction() {

        //for inflate toolbar
        setSupportActionBar(toolbar);

        arrowBack.setOnClickListener(view -> finish());

    }

    private void initID() {

        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbarSubmit);
        arrowBack = (ImageView) findViewById(R.id.arrowBack);

    }

    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }
}
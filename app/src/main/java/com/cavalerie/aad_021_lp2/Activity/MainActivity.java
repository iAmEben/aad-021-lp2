package com.cavalerie.aad_021_lp2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


import com.cavalerie.aad_021_lp2.Adapter.MainAdapter;
import com.cavalerie.aad_021_lp2.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private MainAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initID();
        initAction();
    }

    private void initAction() {
        //for inflate toolbar
        setSupportActionBar(toolbar);

        //init viewpager and tabLayout
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        btnSubmit.setOnClickListener(v -> startActivity(new Intent(this, SubmitActivity.class)));
    }

    private void initID() {
        toolbar = findViewById(R.id.toolbar);
        btnSubmit = findViewById(R.id.btnSubmit);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabLayout);
        adapter = new MainAdapter(getSupportFragmentManager());
    }
}
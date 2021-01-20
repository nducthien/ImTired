package com.example.asd.imtired.activity;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.asd.imtired.fragment.CategoryFragment;
import com.example.asd.imtired.fragment.SpeedFragment;
import com.example.asd.imtired.fragment.RankingFragment;
import com.example.asd.imtired.R;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectFragment = null;
                switch (item.getItemId()) {
                    case R.id.action_category:
                        selectFragment = CategoryFragment.newInstance();
                        break;
                    case R.id.action_ranking:
                        selectFragment = RankingFragment.newInstance();
                        break;
                    case R.id.action_play:
                        selectFragment = SpeedFragment.newInstance();
                        break;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectFragment);
                transaction.commit();
                return true;

            }
        });

        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, CategoryFragment.newInstance());
        transaction.commit();
    }
}

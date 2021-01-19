package com.example.asd.imtired.Acitvity;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.asd.imtired.Fragment.CategoriFragment;
import com.example.asd.imtired.Fragment.PlayFragment;
import com.example.asd.imtired.Fragment.RankingFragment;
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
                        selectFragment = CategoriFragment.newInstance();
                        break;
                    case R.id.action_ranking:
                        selectFragment = RankingFragment.newInstance();
                        break;
                    case R.id.action_play:
                        selectFragment = PlayFragment.newInstance();
                        break;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectFragment);
                transaction.commit();
                return true;

            }
        });

        setDefaulFragment();
    }

    private void setDefaulFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, CategoriFragment.newInstance());
        transaction.commit();
    }
}

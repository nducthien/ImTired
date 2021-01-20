package com.example.asd.imtired.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.asd.imtired.R;
import com.example.asd.imtired.activity.StartSpeedActivity;
import com.example.asd.imtired.common.Common;

public class SpeedFragment extends Fragment {

    private Button btnStart;

    public static SpeedFragment newInstance() {
        SpeedFragment speedFragment = new SpeedFragment();
        return speedFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_speed, container, false);

        initComponent(view);
        startGame();

        return view;
    }

    private void startGame() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), StartSpeedActivity.class);
                startActivityForResult(intent, Common.REQUEST_CODE_QUIZ);
            }
        });
    }

    private void initComponent(View view) {
        btnStart = view.findViewById(R.id.btnStart);
    }
}
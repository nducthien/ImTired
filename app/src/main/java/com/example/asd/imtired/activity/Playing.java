package com.example.asd.imtired.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.asd.imtired.common.Common;
import com.example.asd.imtired.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Playing extends AppCompatActivity implements View.OnClickListener {

    public final static long INTERVAL = 1000; // 1 SEC
    public final static long TIMEOUT = 7000; // 7 sec
    private int progressValue = 0;

    CountDownTimer mCountDown;

    private int index = 0;
    private int score = 0;
    private int thisQuestion = 0;
    private int totalQuestion;
    private int correctAnswer;

    //Firebase
    FirebaseDatabase database;
    DatabaseReference questions;


    private ProgressBar progressBar;
    private ImageView question_image;
    private Button btnA, btnB, btnC, btnD;
    private TextView tvScore, tvQuestionNum, question_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Question");

        // view
        tvScore = findViewById(R.id.tvScore);
        tvQuestionNum = findViewById(R.id.tvTotalQuestion);
        question_text = findViewById(R.id.question_text);
        question_image = findViewById(R.id.question_image);

        progressBar = findViewById(R.id.progressBar);

        btnA = findViewById(R.id.btnAnswerA);
        btnB = findViewById(R.id.btnAnswerB);
        btnC = findViewById(R.id.btnAnswerC);
        btnD = findViewById(R.id.btnAnswerD);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        mCountDown.cancel();
        if (index < totalQuestion) { // still have question in list
            Button clickedButton = (Button) v;
            if (clickedButton.getText().equals(Common.questionList.get(index).getCorrectAnswer())) {
                // choose correct answer
                score+= 10;
                correctAnswer++;
                showQuestion(++index); // next question

            } else {
                // choose wrong answer
                Intent intent = new Intent(this, Done.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE", score);
                dataSend.putInt("TOTAL", totalQuestion);
                dataSend.putInt("CORRECT", correctAnswer);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }

            tvScore.setText(String.format("%d", score));
        }


    }

    private void showQuestion(int index) {
        if (index < totalQuestion) {
            thisQuestion++;
            tvQuestionNum.setText(String.format("%d / %d", thisQuestion, totalQuestion));
            progressBar.setProgress(0);
            progressValue = 0;

            if (Common.questionList.get(index).getIsImageQuestion().equals("true")) {
                // if is image
                Picasso.with(getBaseContext())
                        .load(Common.questionList.get(index).getQuestion())
                        .into(question_image);
                question_image.setVisibility(View.VISIBLE);
                question_text.setVisibility(View.INVISIBLE);

            } else {
                question_text.setText(Common.questionList.get(index).getQuestion());

                // if question is text , we set image to invisible
                question_image.setVisibility(View.INVISIBLE);
                question_text.setVisibility(View.VISIBLE);
            }

            btnA.setText(Common.questionList.get(index).getAnswerA());
            btnB.setText(Common.questionList.get(index).getAnswerB());
            btnC.setText(Common.questionList.get(index).getAnswerC());
            btnD.setText(Common.questionList.get(index).getAnswerD());

            mCountDown.start(); // start timer
        } else {
            // if it is final question
            Intent intent = new Intent(this, Done.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE", score);
            dataSend.putInt("TOTAL", totalQuestion);
            dataSend.putInt("CORRECT", correctAnswer);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();

        }

    }

    // press Ctrl + 0

    @Override
    protected void onResume() {
        super.onResume();

        totalQuestion = Common.questionList.size();

        mCountDown = new CountDownTimer(TIMEOUT, INTERVAL) {
            @Override
            public void onTick(long milisec) {
                progressBar.setProgress(progressValue);
                progressValue++;

            }

            @Override
            public void onFinish() {
                mCountDown.cancel();
                showQuestion(++index);

            }
        };

        showQuestion(index);
    }
}

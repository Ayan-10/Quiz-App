package io.realworld.android.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    private static final int NUMBER_OF_QUESTIONS = 5;
    TextView resultProgressBar;
    TextView resultPercent;
    Button button;
    private static final int MULTIPLIER_TO_GET_PERCENTAGE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();

        resultProgressBar = findViewById(R.id.result_progress_bar);
        resultPercent = findViewById(R.id.result_percent);
        button = findViewById(R.id.quiz_result_next);
        int score = getIntent().getIntExtra("score", 0);
        setScore(score);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void setScore(int score) {
        int per = score * MULTIPLIER_TO_GET_PERCENTAGE;
        Log.d("kugk","JFY"+per);
        resultPercent.setText(per + " % success rate");
        resultProgressBar.setText(score +" / " + NUMBER_OF_QUESTIONS);
    }
}
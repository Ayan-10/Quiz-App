package io.realworld.android.quizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView counter;
    private ProgressBar loader;
    private ImageView imageView;
    private TextView question;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private int questionNum;
    int score;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        Init();
        Quiz[] quizzes = {
                new Quiz(getString(R.string.question1), getString(R.string.image1),
                        getString(R.string.question1_1), getString(R.string.question1_2),
                        getString(R.string.question1_3),getString(R.string.question1_4),
                        getString(R.string.ans1)),
                new Quiz(getString(R.string.question2), getString(R.string.image2),
                        getString(R.string.question2_1), getString(R.string.question2_2),
                        getString(R.string.question2_3),getString(R.string.question2_4),
                        getString(R.string.ans2)),
                new Quiz(getString(R.string.question3), getString(R.string.image3),
                        getString(R.string.question3_1), getString(R.string.question3_2),
                        getString(R.string.question3_3),getString(R.string.question3_4),
                        getString(R.string.ans3)),
                new Quiz(getString(R.string.question4), getString(R.string.image4),
                        getString(R.string.question4_1), getString(R.string.question4_2),
                        getString(R.string.question4_3),getString(R.string.question4_4),
                        getString(R.string.ans4)),
                new Quiz(getString(R.string.question5), getString(R.string.image5),
                        getString(R.string.question5_1), getString(R.string.question5_2),
                        getString(R.string.question5_3),getString(R.string.question5_4),
                        getString(R.string.ans5))
        };

        showNextQuestion(quizzes);

        next.setOnClickListener(v -> {
            if(questionNum <= quizzes.length) {
                checkAnswer(quizzes);
                radioGroup.clearCheck();
                showNextQuestion(quizzes);
            } else {
                checkAnswer(quizzes);
                showScore();
            }
        });


    }

    private void checkAnswer(Quiz[] quizzes) {
        String right = quizzes[questionNum-2].getCorrectAnswer();
        Log.d("hahaha", right);

        switch (right) {
            case "1":
                if (radioButton1.isChecked()) {
                    score++;
                }
                break;
            case "2":
                if (radioButton2.isChecked()) {
                    score++;
                }
                break;
            case "3":
                if (radioButton3.isChecked()) {
                    score++;
                }
                break;
            case "4":
                if (radioButton4.isChecked()) {
                    score++;
                }
                break;
        }
    }

    private void showScore() {

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }

    private void showNextQuestion(Quiz[] quizzes) {
        final int PROGRESS = (int) Math.ceil(100/quizzes.length);
        progressBar.incrementProgressBy(PROGRESS);

        counter.setText(questionNum+" / "+quizzes.length);
        loader.setVisibility(View.VISIBLE);
        Glide.with(this).load(quizzes[questionNum-1].getImageUrl()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                loader.setVisibility(View.GONE);
                return false;
            }
        }).dontAnimate().transition(DrawableTransitionOptions.withCrossFade()).into(imageView);

        question.setText(quizzes[questionNum-1].getQuestion());
        radioButton1.setText(quizzes[questionNum-1].getOptionA());
        radioButton2.setText(quizzes[questionNum-1].getOptionB());
        radioButton3.setText(quizzes[questionNum-1].getOptionC());
        radioButton4.setText(quizzes[questionNum-1].getOptionD());
        questionNum++;
    }

    private void Init() {
        progressBar = findViewById(R.id.progressBar);
        counter = findViewById(R.id.counter);
        loader = findViewById(R.id.loader);
        imageView = findViewById(R.id.imageView);
        question = findViewById(R.id.question);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton4);
        radioButton2 = findViewById(R.id.radioButton3);
        radioButton3 = findViewById(R.id.radioButton2);
        radioButton4 = findViewById(R.id.radioButton);
        next = findViewById(R.id.button);
        questionNum = 1;
        score = 0;
    }
}
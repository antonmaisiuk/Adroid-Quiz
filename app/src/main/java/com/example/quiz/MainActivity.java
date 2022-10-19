package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private TextView questionTextView;
    private int currentIndex = 0;

    private final Question[] questions = new Question[]{
            new Question(R.string.q1, true),
            new Question(R.string.q2, true),
            new Question(R.string.q3, false),
            new Question(R.string.q4, true),
            new Question(R.string.q5, false),
    };

    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId;
        if(userAnswer == correctAnswer){
            resultMessageId = R.string.correct_answer;
        } else{
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
    }


    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "onStart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button trueButton;
        Button falseButton;
        Button nextButton;

        trueButton = findViewById(R.id.true_btn);
        falseButton = findViewById(R.id.false_btn);
        nextButton = findViewById(R.id.next_btn);
        questionTextView = findViewById(R.id.quest_text_view);

        trueButton.setOnClickListener(v -> checkAnswerCorrectness(true));
        falseButton.setOnClickListener(v -> checkAnswerCorrectness(false));

        nextButton.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1)%questions.length;
            setNextQuestion();
        });
        setNextQuestion();
    }
}
package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {

    private boolean correctAnswer;
    public static final String KEY_EXTRA_ANSWER_SHOW = "com.example.quiz.answerShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        Button showCorrectAnswerButton;
        TextView answerTextView;

        showCorrectAnswerButton = findViewById(R.id.answer_btn);
        answerTextView = findViewById(R.id.answer_text_view);

        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);


        showCorrectAnswerButton.setOnClickListener(view -> {
            int answer = correctAnswer ? R.string.true_button : R.string.false_button;
            answerTextView.setText(answer);
            setAnswerShownResult(true);
        });

    }
    public void setAnswerShownResult(boolean answerWasShown){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOW, answerWasShown);
        setResult(RESULT_OK, resultIntent);
    }
}
package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_CURRENT_INDEX = "currentIndex";
    public boolean answerWasShown;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK){ return;}
        if (requestCode == REQUEST_CODE_PROMPT){
            if (data == null){ return; }
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOW, false);
        }
    }

    public static final String KEY_EXTRA_ANSWER = "com.example.quiz.correctAnswer";
    public static final int REQUEST_CODE_PROMPT = 0;
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
        if (answerWasShown){
            resultMessageId = R.string.answer_was_shown;
        } else {
            if(userAnswer == correctAnswer){
                resultMessageId = R.string.correct_answer;
            } else{
                resultMessageId = R.string.incorrect_answer;
            }
        }

        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
    }


    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("TAG", "Wywołana metoda onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
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
        Log.d("TAG", "onCreate");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }


        Button trueButton;
        Button falseButton;
        Button nextButton;
        Button promptButton;

        trueButton = findViewById(R.id.true_btn);
        falseButton = findViewById(R.id.false_btn);
        nextButton = findViewById(R.id.next_btn);
        promptButton = findViewById(R.id.answer_btn_main_activity);
        questionTextView = findViewById(R.id.quest_text_view);

        trueButton.setOnClickListener(v -> checkAnswerCorrectness(true));
        falseButton.setOnClickListener(v -> checkAnswerCorrectness(false));
        promptButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PromptActivity.class);
            boolean correctAnswer = questions[currentIndex].isTrueAnswer();
            intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
            startActivityForResult(intent, REQUEST_CODE_PROMPT);
        });

        nextButton.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1)%questions.length;
            answerWasShown = false;
            setNextQuestion();
        });
        setNextQuestion();
    }
}
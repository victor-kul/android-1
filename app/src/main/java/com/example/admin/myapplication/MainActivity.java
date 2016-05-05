package com.example.admin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] questins;
    private boolean[] answers;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        Button btnTrue = (Button) findViewById(R.id.btnTrue);
        Button btnFalse = (Button) findViewById(R.id.btnFalse);
        Button btnNext = (Button) findViewById(R.id.btnNext);

        initializeCards();
        if(isStartPermition()){
            getNextQuestion(tvQuestion);
        }else{
            showToast("Error");
        }

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast(R.string.txt_false);
                showToast(Boolean.toString(isAnswerCorrect(false, answers[currentIndex])));
            }
        });

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast(R.string.txt_true);
                showToast(Boolean.toString(isAnswerCorrect(true, answers[currentIndex])));
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast(R.string.txt_next);
                getNextQuestion(tvQuestion);
            }
        });

    }

    private void getNextQuestion(TextView tvQuestion) {
        int oldIndex = currentIndex;
        do{
            currentIndex = (int) (Math.random() * questins.length);
        }while(oldIndex == currentIndex);

        tvQuestion.setText(questins[currentIndex]);
    }

    private boolean isAnswerCorrect(boolean x, boolean y){
        return x == y;
    }

    private boolean isStartPermition() {
        return questins.length == answers.length;
    }

    private void initializeCards() {
//        questins = new String[10];
        questins = new String[]{
                "Question1",
                "Question2",
                "Question3",
        };

        answers = new boolean[]{
                true,
                false,
                true,
        };

    }

    private void showToast(int message) {
        Toast.makeText(MainActivity.this, getResources().getString(message), Toast.LENGTH_SHORT).show();
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}

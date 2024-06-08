package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
    TextView totalCorrect,totalWrong,successRate,emptyAns;
    Button playAgain,btnQuit;
    int correct,wrong,empty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        totalCorrect=findViewById(R.id.textViewTotal);
        totalWrong=findViewById(R.id.textViewWrongTotal);
        successRate=findViewById(R.id.textViewSuccessRate);
        emptyAns=findViewById(R.id.textViewEmpty);

        playAgain=findViewById(R.id.buttonPlayAgain);
        btnQuit=findViewById(R.id.buttonExit);

        correct= getIntent().getIntExtra("correct", 0);
        wrong= getIntent().getIntExtra("wrong", 0);
        empty= getIntent().getIntExtra("empty", 0);

        totalCorrect.setText("Total correct answer: "+correct);
        totalWrong.setText("Total wrong answer: "+ wrong);
        emptyAns.setText("Total skipped answers: "+empty);
        successRate.setText("Success rate: "+(correct*wrong/100));

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent=new Intent(Intent.ACTION_MAIN);
                newIntent.addCategory(Intent.CATEGORY_HOME);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(newIntent);
                finish();
            }
        });
    }
}
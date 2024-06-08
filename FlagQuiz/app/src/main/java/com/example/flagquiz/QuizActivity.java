package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {
    ImageView imageView,imageNext;
    TextView textCorrect,textWrong,textEmpty,textQuestion;
    Button btnA,btnB,btnC,btnD;
    private FlagsDatabase fdatabase;
    private ArrayList<FlagsModel> questions;

    int correct=0,wrong=0,empty=0,question=0;
    private FlagsModel correctFlags;
    private ArrayList<FlagsModel> wrongOptionList;
    HashSet<FlagsModel> mixOptions=new HashSet<>();
    ArrayList<FlagsModel> options=new ArrayList<>();
    boolean buttonControl=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        btnA=findViewById(R.id.buttonA);
        btnB=findViewById(R.id.buttonB);
        btnC=findViewById(R.id.buttonC);
        btnD=findViewById(R.id.buttonD);
        textCorrect=findViewById(R.id.textViewCorrect);
        textWrong=findViewById(R.id.textViewWrong);
        imageNext=findViewById(R.id.imageView2);
        imageView=findViewById(R.id.imageViewFlag);
        textQuestion=findViewById(R.id.textViewQuestion);
        textEmpty=findViewById(R.id.textView3);

        fdatabase=new FlagsDatabase(QuizActivity.this);
        questions=new FlagsDAO().getRandomSevenquestions(fdatabase);
        loadQuestion();

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            answerControl(btnA);
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            answerControl(btnB);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            answerControl(btnC);
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            answerControl(btnD);
            }
        });

        imageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question++;


                if(!buttonControl && question<7) {
                    empty++;
                    textEmpty.setText("Skipped: "+empty);
                    loadQuestion();
                } else if (buttonControl && question<7) {
                    loadQuestion();
                    btnA.setClickable(true);
                    btnB.setClickable(true);
                    btnC.setClickable(true);
                    btnD.setClickable(true);

                    btnA.setBackgroundColor(getResources().getColor(R.color.btnColor));
                    btnB.setBackgroundColor(getResources().getColor(R.color.btnColor));
                    btnC.setBackgroundColor(getResources().getColor(R.color.btnColor));
                    btnD.setBackgroundColor(getResources().getColor(R.color.btnColor));
                }
                else if(question==7){
                    Intent i=new Intent(QuizActivity.this, ResultsActivity.class);
                    i.putExtra("correct",correct);
                    i.putExtra("wrong",wrong);
                    i.putExtra("empty",empty);
                    startActivity(i);
                    finish();
                }

                buttonControl=false;

            }
        });

    }

    public void loadQuestion(){
        textQuestion.setText("Question :"+(question+1));

        correctFlags=questions.get(question);

        imageView.setImageResource(getResources().getIdentifier(correctFlags.getFlag_image(),"drawable",getPackageName()));
        wrongOptionList=new FlagsDAO().getRandomThree(fdatabase,correctFlags.getFlag_id());

        mixOptions.clear();
        mixOptions.add(correctFlags);
        mixOptions.add(wrongOptionList.get(0));
        mixOptions.add(wrongOptionList.get(1));
        mixOptions.add(wrongOptionList.get(2));

        options.clear();

        for(FlagsModel flg:mixOptions){
            options.add(flg);
        }

        btnA.setText(options.get(0).getFlag_name());
        btnB.setText(options.get(1).getFlag_name());
        btnC.setText(options.get(2).getFlag_name());
        btnD.setText(options.get(3).getFlag_name());
    }

    public void answerControl(Button button){

        String buttonText=button.getText().toString();
        String correctAns=correctFlags.getFlag_name();

        if(buttonText.equals(correctAns)){
            correct++;
            button.setBackgroundColor(Color.GREEN);

        }

        else {
            wrong++;
            button.setBackgroundColor(Color.RED);

            if(btnA.getText().toString().equals(correctAns))
                btnA.setBackgroundColor(Color.GREEN);
            if(btnB.getText().toString().equals(correctAns))
                btnB.setBackgroundColor(Color.GREEN);
            if(btnC.getText().toString().equals(correctAns))
                btnC.setBackgroundColor(Color.GREEN);
            if(btnD.getText().toString().equals(correctAns))
                btnD.setBackgroundColor(Color.GREEN);


            btnA.setClickable(false);
            btnB.setClickable(false);
            btnC.setClickable(false);
            btnD.setClickable(false);


        }
        textCorrect.setText("Correct :"+correct);
        textWrong.setText("Wrong :"+wrong);
        buttonControl=true;

    }
}
package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start=findViewById(R.id.buttonStart);

        copyDatabase();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,QuizActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void copyDatabase(){
            try {
                DatabaseCopyHelper databaseCopyHelper=new DatabaseCopyHelper(MainActivity.this);
                databaseCopyHelper.createDataBase();
                databaseCopyHelper.openDataBase();
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}
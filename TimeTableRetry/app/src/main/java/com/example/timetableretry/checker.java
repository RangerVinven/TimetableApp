package com.example.timetableretry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class checker extends AppCompatActivity {

    Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checker);

        String dayOfWeek = intent.getStringExtra("DAYOFWEEK");

        TextView textView = findViewById(R.id.textView3);

        textView.setText(dayOfWeek);
    }
}

package com.example.timetableretry;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.timetableretry.Database.Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Display extends AppCompatActivity {

    Database mydb = new Database(this);
    Button NowBtn, NextBtn, TodayBtn, EverythingBtn;
    TextView textView1;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        NowBtn = findViewById(R.id.NowBtn);
        NextBtn = findViewById(R.id.NextBtn);
        TodayBtn = findViewById(R.id.TodayBtn);
        EverythingBtn = findViewById(R.id.EverythingBtn);
        textView1 = findViewById(R.id.MainTextView);
        floatingActionButton = findViewById(R.id.floatingActionButton5);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Main = new Intent(Display.this, MainActivity.class);
                startActivity(Main);
            }
        });

        NowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsNow();
            }
        });

        EverythingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAll();
            }
        });

        TodayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Display.this, Today.class);
                startActivity(intent);

            }
        });

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.getNextData();
                res.moveToFirst();
                if (res.getCount() == 0) {
                    textView1.setText("Nothing");
                } else {
                    res.moveToFirst();
                    textView1.setText(res.getString(0));
                }
            }
        });

    }
    public void whatsNow() {
//        Cursor res = mydb.getData();
//        res.moveToFirst();
//        if (res.getCount() == 0) {
//            textView1.setText("Nothing");
//        } else {
//            res.moveToFirst();
//            textView1.setText(res.getString(0));
//        }
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("H");

        Cursor res = mydb.getData();
        res.moveToFirst();
        if (res.getCount() == 0) {
            textView1.setText("Nothing");
        } else {
            res.moveToFirst();
            textView1.setText(res.getString(0));
        }

    }
    public void viewAll() {

        Intent intent = new Intent(Display.this, everything.class);
        startActivity(intent);
    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}

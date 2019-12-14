package com.example.timetableretry;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.timetableretry.Database.Database;

import java.util.ArrayList;

public class Thursday extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thursday);
        Database myDB;

        ListView listView = findViewById(R.id.listView);
        myDB = new Database(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.Thursday();

        if(data.getCount() == 0){
            Toast.makeText(Thursday.this, "There is no entries set for this day", Toast.LENGTH_SHORT).show();
        }else {
            while (data.moveToNext()) {
                theList.add(data.getString(1) + " - ID " + data.getString(0) + " / " + data.getString(2) + " - " + data.getString(3));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(listAdapter);
            }
        }
    }
}

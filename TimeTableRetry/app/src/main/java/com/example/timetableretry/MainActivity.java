package com.example.timetableretry;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.timetableretry.Database.Database;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Database mydb;
    EditText NameInput, editStartingTime, editEndingTime, idText, weekInput, startingTime, endingTime;
    Button Displaybtn, backBtn;
    FloatingActionButton addDataButton, deleteBtn, update;
    Spinner WeekInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new Database(this);

        NameInput = findViewById(R.id.NameInput);
        WeekInput = findViewById(R.id.whatdayisthison);
        addDataButton = findViewById(R.id.SaveButton);
        update = findViewById(R.id.EditButton);
        deleteBtn = findViewById(R.id.CancelButton);
        startingTime = findViewById(R.id.startingTime);
        endingTime = findViewById(R.id.endingTime);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        WeekInput.setAdapter(adapter);
        WeekInput.setOnItemSelectedListener(MainActivity.this);

    }
    public void updateDate() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toEdit = new Intent(MainActivity.this, Edit.class);
                startActivity(toEdit);

            }
        });
    }
    public void addData(final String DayOWeek) {

        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String StartingTime = startingTime.getText().toString();
                String EndingTime = endingTime.getText().toString();
                SQLiteDatabase db = mydb.getWritableDatabase();

                String day = DayOWeek;

                try {
                    if (StartingTime.length() == 5 && EndingTime.length() == 5) {

                        char[] StartingTimeArray = StartingTime.toCharArray();
                        char[] EndingTimeArray = EndingTime.toCharArray();

                        String StartingTimeValue = String.valueOf(StartingTimeArray[3]);
                        String EndingTimeValue = String.valueOf(EndingTimeArray[3]);

                        String StartingTimeValue2 = String.valueOf(StartingTimeArray);
                        String EndingTimeValue2 = String.valueOf(EndingTimeArray);

                        String StartingTimeValue21 = StartingTimeValue;
                        String EndingTimeValue21 = EndingTimeValue;

                        String StartingTimeValue1 = StartingTimeValue2.replace(":", "");
                        String EndingTimeValue1 = EndingTimeValue2.replace(":", "");

                        int StartingTimeValue2Int = Integer.valueOf(StartingTimeValue21);
                        int EndingTimeValue2Int = Integer.valueOf(EndingTimeValue21);

                        if (StartingTimeValue2Int > 5 || EndingTimeValue2Int > 5) {
                            Toast.makeText(MainActivity.this, "Invalid Time (It's 24hr!)", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            int StartingInt = Integer.valueOf(StartingTimeValue1);
                            int EndingInt = Integer.valueOf(EndingTimeValue1);

                            String ST = StartingTimeValue1;
                            String ET = StartingTimeValue1;

                            //Trying to have the int keep a 0 at the start

                            if (ST.startsWith("0")) {
                                StartingTimeValue1 = String.format("%01d" , StartingInt);
                            }
                            if (ET.startsWith("0")) {
                                EndingTimeValue1 = String.format("%01d" , EndingInt);
                            }

                            StartingInt = Integer.valueOf(StartingTimeValue1);
                            EndingInt = Integer.valueOf(EndingTimeValue1);

                            //inserting data

                            boolean isInserted =  mydb.insertData(NameInput.getText().toString(), DayOWeek, StartingInt, EndingInt);
                            if(isInserted == true) {
                                Toast.makeText(MainActivity.this, "Data Inserted",Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Data Failed To Inserted",Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                    else {
                        throw new Exception();
                    }

                } catch (Exception e) {
                    if (StartingTime.length() == 4 && EndingTime.length() == 4) {


                        char[] StartingTimeArray = StartingTime.toCharArray();
                        char[] EndingTimeArray = EndingTime.toCharArray();

                        String StartingTimeValue = String.valueOf(StartingTimeArray[2]);
                        String EndingTimeValue = String.valueOf(EndingTimeArray[2]);

                        String StartingTimeValue21 = StartingTimeValue;
                        String EndingTimeValue21 = EndingTimeValue;

                        int StartingTimeValue2Int = Integer.valueOf(StartingTimeValue21);
                        int EndingTimeValue2Int = Integer.valueOf(EndingTimeValue21);


                        if (StartingTimeValue2Int > 5 || EndingTimeValue2Int > 5) {
                            Toast.makeText(MainActivity.this, "Invalid Time (It's 24hr!)", Toast.LENGTH_SHORT).show();
                        } else {

                            int StartingInt = Integer.valueOf(StartingTime);
                            int EndingInt = Integer.valueOf(EndingTime);

                            String ST = startingTime.getText().toString();
                            String ET = endingTime.getText().toString();

                            //Trying to have the int keep a 0 at the start

                            if (ST.startsWith("0")) {
                                StartingTime = String.format("%01d" , StartingInt);
                            }
                            if (ET.startsWith("0")) {
                                EndingTime = String.format("%01d" , EndingInt);
                            }

                            StartingInt = Integer.valueOf(StartingTime);
                            EndingInt = Integer.valueOf(EndingTime);

                            //inserting data

                            boolean isInserted = mydb.insertData(NameInput.getText().toString(), DayOWeek, StartingInt, EndingInt);
                            if (isInserted == true) {
                                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(MainActivity.this, "Data Failed To Inserted", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Time (It's 24hr!)", Toast.LENGTH_SHORT).show();
                    }

                }

            }

        });
    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void deleteData() {
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Display.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String day = adapterView.getItemAtPosition(i).toString();
        addData(day);
        updateDate();
        deleteData();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}

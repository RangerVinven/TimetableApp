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

import java.util.ArrayList;

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

                //Getting input from editTexts

                String StartingTime = startingTime.getText().toString();
                String EndingTime = endingTime.getText().toString();

                //Taking away the colon, if any

                StartingTime = StartingTime.replace(":", "");
                EndingTime = EndingTime.replace(":", "");

                //Turning the string values of the inputs ^ into intergers

                int startingInt = Integer.valueOf(StartingTime);
                int endingInt = Integer.valueOf(EndingTime);

                //Checking if the time inputted is more than 4 characters in length

                if (StartingTime.length() != 4 || EndingTime.length() != 4) {

                    //Displaying "Invalid Time (24hr)" as toast
                    Toast.makeText(MainActivity.this, "Invalid Time (24hr)", Toast.LENGTH_SHORT).show();

                } else {

                    //Turning the inputted values to arrays

                    char[] StartingTimeArray = StartingTime.toCharArray();
                    char[] EndingTimeArray = EndingTime.toCharArray();

                    //Checking if the Starting and Ending time minutes are too high (above 59)

                    int startingMin = Integer.valueOf(String.valueOf(StartingTimeArray[2]));
                    int endingMin = Integer.valueOf(String.valueOf(EndingTimeArray[2]));

                    if (startingMin > 5 || endingMin > 5) {

                        //Displaying "Invalid Time (24hr)" as toast
                        Toast.makeText(MainActivity.this, "Invalid Time (24hr)", Toast.LENGTH_SHORT).show();

                    } else {

                        //Checking if the Starting Time hour is too high (above 23)

                        int startingHour1 = Integer.valueOf(String.valueOf(StartingTimeArray[0]));
                        int endingMinHour1 = Integer.valueOf(String.valueOf(EndingTimeArray[0]));
                        int startingHour2 = Integer.valueOf(String.valueOf(StartingTimeArray[1]));
                        int endingMinHour2 = Integer.valueOf(String.valueOf(EndingTimeArray[1]));

                        if (startingHour1 >= 2 && startingHour2 >= 4) {

                            //Displaying "Invalid Time (24hr)" as toast
                            Toast.makeText(MainActivity.this, "Invalid Time (24hr)", Toast.LENGTH_SHORT).show();

                        } else {

                            //Checking if the Ending Time hour is too high (above 23)

                            if (endingMinHour1 >= 2 && endingMinHour2 >= 4) {

                                //Displaying "Invalid Time (24hr)" as toast
                                Toast.makeText(MainActivity.this, "Invalid Time (24hr)", Toast.LENGTH_SHORT).show();

                            } else {

                                //Calling the insertData functions from Database.java and storing the true or false return to isInserted
                                boolean isInserted =  mydb.insertData(NameInput.getText().toString(), DayOWeek, startingInt, endingInt);
                                if(isInserted == true) {
                                    Toast.makeText(MainActivity.this, "Data Inserted",Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Data Failed To Inserted",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
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

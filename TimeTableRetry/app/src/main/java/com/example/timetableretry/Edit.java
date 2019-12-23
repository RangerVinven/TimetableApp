package com.example.timetableretry;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.timetableretry.Database.Database;

public class Edit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final Database mydb = new Database(this);
        FloatingActionButton floatingActionButton = findViewById(R.id.BackButton);
        final EditText IDEditText = findViewById(R.id.WhatsitsID);
        final Spinner day = findViewById(R.id.whatdayisthison);
        final EditText NameEditText = findViewById(R.id.Whatsitsnewname);
        final EditText StartingTimeEditText = findViewById(R.id.StartingTimeHour);
        final EditText EndingTimeEditText = findViewById(R.id.EndingTime);
        Button update = findViewById(R.id.UpdateButtonUp);
        final Button delete = findViewById(R.id.DeleteButtonDel);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Edit.this, R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(adapter);
        day.setOnItemSelectedListener(Edit.this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit.this, MainActivity.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

    }

    public void delete(){
        final Database mydb = new Database(this);
        FloatingActionButton floatingActionButton = findViewById(R.id.BackButton);
        final EditText IDEditText = findViewById(R.id.WhatsitsID);
        final Spinner day = findViewById(R.id.whatdayisthison);
        final EditText NameEditText = findViewById(R.id.Whatsitsnewname);
        final EditText StartingTimeEditText = findViewById(R.id.StartingTimeHour);
        final EditText EndingTimeEditText = findViewById(R.id.EndingTime);
        Button update = findViewById(R.id.UpdateButtonUp);
        Button delete = findViewById(R.id.DeleteButtonDel);

        mydb.deleteDate(IDEditText.getText().toString());
        Toast.makeText(Edit.this, "Data Deleted", Toast.LENGTH_SHORT).show();
    }

    public void update(String day1){

        final Database mydb = new Database(this);
        FloatingActionButton floatingActionButton = findViewById(R.id.BackButton);
        final EditText IDEditText = findViewById(R.id.WhatsitsID);
        final Spinner day = findViewById(R.id.whatdayisthison);
        final EditText NameEditText = findViewById(R.id.Whatsitsnewname);
        final EditText StartingTimeEditText = findViewById(R.id.StartingTimeHour);
        final EditText EndingTimeEditText = findViewById(R.id.EndingTime);
        Button update = findViewById(R.id.UpdateButtonUp);
        Button delete = findViewById(R.id.DeleteButtonDel);

        String StartingTime = StartingTimeEditText.getText().toString();
        String EndingTime = EndingTimeEditText.getText().toString();
        String DOW1 = day1;

        char[] StartingTimeArray = StartingTime.toCharArray();
        char[] EndingTimeArray = EndingTime.toCharArray();

        StartingTime = StartingTime.replace(":", "");
        EndingTime = EndingTime.replace(":", "");
        DOW1 = DOW1.replace(" ", "");

        final int thirdStarting = Integer.valueOf(StartingTimeArray[2]);
        final int thirdEnding = Integer.valueOf(EndingTimeArray[2]);


        try {

            if (StartingTime.length() == 5 && StartingTime.length() == 5) {

                if (thirdEnding > 5 || thirdStarting > 5) {
                    throw new Exception();
                } else {

                    int Starting = Integer.valueOf(StartingTime);
                    int Ending = Integer.valueOf(EndingTime);

                    boolean isUpdated = mydb.updateData(IDEditText.getText().toString(), NameEditText.getText().toString(), DOW1,
                            Integer.valueOf(StartingTime), Integer.valueOf(EndingTime));
                    if (isUpdated == true) {
                        Toast.makeText(Edit.this, "Data Updated", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Edit.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }

                }
            } else {

                if (StartingTime.length() == 5 && StartingTime.length() == 5) {

                    if (thirdEnding > 5 || thirdStarting > 5) {
                        throw new Exception();
                    } else {

                        int Starting = Integer.valueOf(StartingTime);
                        int Ending = Integer.valueOf(EndingTime);

                        boolean isUpdated = mydb.updateData(IDEditText.getText().toString(), NameEditText.getText().toString(), DOW1,
                                Integer.valueOf(StartingTime), Integer.valueOf(EndingTime));
                        if (isUpdated == true) {
                            Toast.makeText(Edit.this, "Data Updated", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Edit.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                        }

                    }

                }

            }

            if (StartingTime.length() == 5 && StartingTime.length() == 5) {

                if (thirdEnding > 5 || thirdStarting > 5) {
                    throw new Exception("Testing");
                } else {

                    int Starting = Integer.valueOf(StartingTime);
                    int Ending = Integer.valueOf(EndingTime);

                    boolean isUpdated = mydb.updateData(IDEditText.getText().toString(), NameEditText.getText().toString(), DOW1,
                            Integer.valueOf(StartingTime), Integer.valueOf(EndingTime));
                    if (isUpdated == true) {
                        Toast.makeText(Edit.this, "Data Updated", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Edit.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }

                }

            } else {
                throw new Exception();
            }

        } catch (Exception e) {

            StartingTime = StartingTime.replace(":", "");
            EndingTime = EndingTime.replace(":", "");
            DOW1 = DOW1.replace(" ", "");

            int Starting = Integer.valueOf(StartingTime);
            int Ending = Integer.valueOf(EndingTime);

            char[] StartingTimeArray2 = StartingTime.toCharArray();
            char[] EndingTimeArray2 = EndingTime.toCharArray();

            int thirdStarting2 = Integer.valueOf(StartingTimeArray2[2]);
            int thirdEnding2 = Integer.valueOf(EndingTimeArray2[2]);

            if (StartingTime.length() == 4 && EndingTime.length() == 4) {
                if (thirdStarting2 > 5 || thirdEnding2 > 5) {
                    boolean isUpdated = mydb.updateData(IDEditText.getText().toString(), NameEditText.getText().toString(), DOW1,
                            Integer.valueOf(StartingTime), Integer.valueOf(EndingTime));
                    if (isUpdated == true) {
                        Toast.makeText(Edit.this, "Data Updated", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Edit.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Edit.this, "Invalid Time (24hr)", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Edit.this, "Invalid Time (24hr)", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        final String day2 = adapterView.getItemAtPosition(i).toString();

        final Database mydb = new Database(this);
        FloatingActionButton floatingActionButton = findViewById(R.id.BackButton);
        final EditText IDEditText = findViewById(R.id.WhatsitsID);
        final Spinner day = findViewById(R.id.whatdayisthison);
        final EditText NameEditText = findViewById(R.id.Whatsitsnewname);
        final EditText StartingTimeEditText = findViewById(R.id.StartingTimeHour);
        final EditText EndingTimeEditText = findViewById(R.id.EndingTime);
        Button update = findViewById(R.id.UpdateButtonUp);
        final Button delete = findViewById(R.id.DeleteButtonDel);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(day2);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

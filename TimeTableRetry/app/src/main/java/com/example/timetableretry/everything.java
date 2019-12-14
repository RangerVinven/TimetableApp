package com.example.timetableretry;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.timetableretry.Database.Database;

public class everything extends AppCompatActivity {

    Database mydb = new Database(this);
    String items[] = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_everything);

        final ListView listView = findViewById(R.id.listView);
        Button button = findViewById(R.id.button);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(everything.this, Display.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    switch( position )
                    {
                        case 0:  Intent newActivity = new Intent(everything.this, Monday.class);
                            startActivity(newActivity);
                            break;
                        case 1:  Intent newActivity1 = new Intent(everything.this, Tuesday.class);
                            startActivity(newActivity1);
                            break;
                        case 2:  Intent newActivity2 = new Intent(everything.this, Wednesday.class);
                            startActivity(newActivity2);
                            break;
                        case 3:  Intent newActivity3 = new Intent(everything.this, Thursday.class);
                            startActivity(newActivity3);
                            break;
                        case 4:  Intent newActivity4 = new Intent(everything.this, Friday.class);
                            startActivity(newActivity4);
                            break;
                        case 5:  Intent newActivity5 = new Intent(everything.this, Saturday.class);
                            startActivity(newActivity5);
                            break;
                        case 6:  Intent newActivity6 = new Intent(everything.this, Sunday.class);
                            startActivity(newActivity6);
                            break;
                    }

            }
        });

    }
}

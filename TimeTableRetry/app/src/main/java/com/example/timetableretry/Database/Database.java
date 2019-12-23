package com.example.timetableretry.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.timetableretry.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Timetable.db";
    public static final String TABLE_NAME = "Timetable";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "STARTINGTIME";
    public static final String COL_3 = "ENDINGTIME";
    public static final String COL_4 = "ID";
    public static final String COL_5 = "DayOfWeek";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table " + TABLE_NAME + " (ID " + "INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DAYOFWEEK TEXT, STARTINGTIME INTERGER, ENDINGTIME INTERGER) ");
        db.execSQL("create table " + TABLE_NAME + " (ID " + "INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DAYOFWEEK TEXT, STARTINGTIME INTEGER, ENDINGTIME INTEGER) ");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertData(String Name, String DayOfWeek, int StartingTime, int EndingTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, Name);
        contentValues.put(COL_2, StartingTime);
        contentValues.put(COL_3, EndingTime);
        contentValues.put(COL_5, DayOfWeek);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }

    }
    public boolean updateData(String id, String name, String DayOfWeek, int startingTime, int endingTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_4, id);
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, startingTime);
        contentValues.put(COL_3, endingTime);
        contentValues.put(COL_5, DayOfWeek);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }
    public Integer deleteDate(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
//    public Cursor data(String currentDay, int currentTime) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT " + COL_1 + " FROM " + TABLE_NAME + " WHERE " + COL_5 + " = " + "'" + currentDay + "'" + " and " + COL_2 + " <= " + currentTime + " and " + COL_3 + " >= " + currentTime, null);
//        return cursor;
//    }
    public Cursor getData() {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("u");
        SimpleDateFormat sdf1 = new SimpleDateFormat("H");
        SimpleDateFormat sdf2 = new SimpleDateFormat("mm");

        Date date = new Date();
        Calendar calendar4 = Calendar.getInstance();
        calendar4.setTime(date);
        int hour = calendar4.get(Calendar.HOUR_OF_DAY);
        int minute = calendar4.get(Calendar.MINUTE);
        String hourString = String.valueOf(hour);
        String minuteString = String.valueOf(minute);

        StringBuilder hourBuilder = new StringBuilder(hourString);

        int currentTime = Integer.valueOf(hourBuilder.toString().concat(minuteString));

        String Day = sdf.format(calendar.getTime());

        String currentDay;
        int DayInt = Integer.valueOf(Day);

        if (DayInt == 1) {
            currentDay = "Monday";
        } else if (DayInt == 2) {
            currentDay = "Tuesday";
        } else if (DayInt == 3) {
            currentDay = "Wednesday";
        } else if (DayInt == 4) {
            currentDay = "Thursday";
        } else if (DayInt == 5) {
            currentDay = "Friday";
        } else if (DayInt == 6) {
            currentDay = "Saturday";
        } else {
            currentDay = "Sunday";
        }

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT Name FROM " + TABLE_NAME + " WHERE " + COL_5 + " = " + "'" + currentDay + "'" + " and " + "'" + currentTime + "'" + " BETWEEN " + COL_2 + " and " + COL_3 + " Order By " + COL_2, null);
        return res;
    }
    public Cursor getNextData() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("u");
        SimpleDateFormat sdf1 = new SimpleDateFormat("H");
        SimpleDateFormat sdf2 = new SimpleDateFormat("mm");

        String Day = sdf.format(calendar.getTime());
        String Hour1 = sdf1.format(calendar.getTime());
        String Minute = sdf2.format(calendar.getTime());

        int DayInt = Integer.valueOf(Day);

        String currentTimeString = Hour1.concat(Minute);
        int currentTime = Integer.valueOf(currentTimeString);
        String currentDay;

        if (DayInt == 1) {
            currentDay = "Monday";
        } else if (DayInt == 2) {
            currentDay = "Tuesday";
        } else if (DayInt == 3) {
            currentDay = "Wednesday";
        } else if (DayInt == 4) {
            currentDay = "Thursday";
        } else if (DayInt == 5) {
            currentDay = "Friday";
        } else if (DayInt == 6) {
            currentDay = "Saturday";
        } else {
            currentDay = "Sunday";
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT Name, STARTINGTIME FROM " + TABLE_NAME + " WHERE " + COL_2 + " >= '" + currentTime + "' AND " + COL_5 + " = " + "'" + currentDay + "' LIMIT 1", null);

        return cursor;
    }
    public Cursor whatsToday() {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("u");
        String Day = sdf.format(calendar.getTime());
        int DayInt = Integer.valueOf(Day);
        String currentDay;

        if (DayInt == 1) {
            currentDay = "Monday";
        } else if (DayInt == 2) {
            currentDay = "Tuesday";
        } else if (DayInt == 3) {
            currentDay = "Wednesday";
        } else if (DayInt == 4) {
            currentDay = "Thursday";
        } else if (DayInt == 5) {
            currentDay = "Friday";
        } else if (DayInt == 6) {
            currentDay = "Saturday";
        } else {
            currentDay = "Sunday";
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT ID, Name, STARTINGTIME, ENDINGTIME FROM " + TABLE_NAME + " WHERE " + COL_5 + " = '" + currentDay + "' ORDER BY STARTINGTIME ASC", null);

        return res;
    }

    public Cursor Monday() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT ID, Name, STARTINGTIME, ENDINGTIME FROM " + TABLE_NAME + " WHERE " + COL_5 + " = 'Monday' ORDER BY " + COL_2 + " ASC" , null);
        return data;
    }
    public Cursor Tuesday() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT ID, Name, STARTINGTIME, ENDINGTIME FROM " + TABLE_NAME + " WHERE " + COL_5 + " = 'Tuesday' ORDER BY " + COL_2 + " ASC" , null);
        return data;
    }
    public Cursor Wednesday() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT ID, Name, STARTINGTIME, ENDINGTIME FROM " + TABLE_NAME + " WHERE " + COL_5 + " = 'Wednesday' ORDER BY " + COL_2 + " ASC" , null);
        return data;
    }
    public Cursor Thursday() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT ID, Name, STARTINGTIME, ENDINGTIME FROM " + TABLE_NAME + " WHERE " + COL_5 + " = 'Thursday' ORDER BY " + COL_2 + " ASC" , null);
        return data;
    }
    public Cursor Friday() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT ID, Name, STARTINGTIME, ENDINGTIME FROM " + TABLE_NAME + " WHERE " + COL_5 + " = 'Friday' ORDER BY " + COL_2 + " ASC" , null);
        return data;
    }
    public Cursor Saturday() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT ID, Name, STARTINGTIME, ENDINGTIME FROM " + TABLE_NAME + " WHERE " + COL_5 + " = 'Saturday' ORDER BY " + COL_2 + " ASC" , null);
        return data;
    }
    public Cursor Sunday() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT ID, Name, STARTINGTIME, ENDINGTIME FROM " + TABLE_NAME + " WHERE " + COL_5 + " = 'Sunday' ORDER BY " + COL_2 + " ASC" , null);
        return data;
    }
}

package com.pursuit.mypsychicapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * Create a helper class to interact with the SQL database. Here we create all the helper methods that we use to interact with the database.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Objects up top, Strings, then primitives at the bottom
    private static DatabaseHelper resultsDatabaseInstance;
    // important to note that table name and database name is not the same thing. Database can have multiple tables.
    private static final String TABLE_CHOICES = "Choices";
    private static final String DATABASE_NAME = "psychic.db";
    private static final int SCHEMA_VERSION = 1;

    static synchronized DatabaseHelper getInstance(Context context) {
        if (resultsDatabaseInstance == null) {
            resultsDatabaseInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return resultsDatabaseInstance;
    }

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // here we're creating a table with one column called num_choice where we are gonna put the choices we make
        db.execSQL(
                "CREATE TABLE " + TABLE_CHOICES +
                        " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "num_choice INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void addChoice(int choice) {
        //insert a choice into the table. 1 indicates a correct choice, 0 indicates a wrong choice
        if(choice ==1){
        getWritableDatabase().execSQL("INSERT INTO " + TABLE_CHOICES +
                "(num_choice) VALUES(1);");
        }else{
            getWritableDatabase().execSQL("INSERT INTO " + TABLE_CHOICES +
                    "(num_choice) VALUES(0);");
        }
    }

    int getAllChoices(){
        //get all the entries in the DB
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT num_choice" + " FROM " + TABLE_CHOICES + ";", null);

        int total = cursor.getCount();

        //always close the cursor
        cursor.close();
        return total;
    }

    int getCorrectChoices(){
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT num_choice" + " FROM " + TABLE_CHOICES + " WHERE choice = 1 " + ";", null);
        int correct = cursor.getCount();
        cursor.close();
        return correct;
    }

    int getWrongChoices(){
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT num_choice" + " FROM " + TABLE_CHOICES + " WHERE choice = 0 " + ";", null);
        int wrong = cursor.getCount();
        cursor.close();
        return wrong;
    }

    double getPercentage(){
        //use the other methods to return the percentage correct. This isn't formatted properly but it gets us the number we want
        return (double) getCorrectChoices()/getAllChoices();
    }
}

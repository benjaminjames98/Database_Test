package tech.bencloud.receiver.database_test;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PersonDataSource {
    private final static String TAG = "PersonDataSource";

    private SQLiteDatabase database;
    private PersonSQLiteOpenHelper dbHelper;


    private static final String[] allColumns = {
            PersonSQLiteOpenHelper.COLUMN_ID,
            PersonSQLiteOpenHelper.COLUMN_NAME,
            PersonSQLiteOpenHelper.COLUMN_AGE,
            PersonSQLiteOpenHelper.COLUMN_ADDRESS
    };

    public PersonDataSource(Context context) {
        dbHelper = new PersonSQLiteOpenHelper(context);
    }

    public void open() {
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLException sqle) {
            Log.e(TAG, "Could not open database! " + sqle.getMessage());
        }
    }

    public void close() {
        database.close();
    }

    public void insert(String name, int age, String address) {
        ContentValues values = new ContentValues();
        values.put(PersonSQLiteOpenHelper.COLUMN_NAME, name);
        values.put(PersonSQLiteOpenHelper.COLUMN_AGE, age);
        values.put(PersonSQLiteOpenHelper.COLUMN_ADDRESS, address);

        long insertId = database.insert(PersonSQLiteOpenHelper.TABLE_PEOPLE, null, values);

        Log.d(TAG, "Inserted person " + insertId + " into the database!");
    }

    public void delete(Person p) {
        long id = p.getId();

        Log.d(TAG, "record with id: " + id + " deleted");

        database.delete(PersonSQLiteOpenHelper.TABLE_PEOPLE, PersonSQLiteOpenHelper.COLUMN_ID + " = " + id, null);
    }

    public void deleteAllPeople() {
        database.delete(PersonSQLiteOpenHelper.TABLE_PEOPLE, null, null);
    }

    private Person cursorToPerson(Cursor cursor) {
        Person p = new Person(cursor.getString(1), // Field 1 is the name
                Integer.parseInt(cursor.getString(2)), // Field 2 is the age
                        cursor.getString(3) // Field 3 is the address
                );

        p.setId(cursor.getLong(0));

        return p;
    }

    public List<Person> retrieveAllPeople() {
        List<Person> peopleList = new ArrayList<Person>();

        Cursor cursor = database.query(PersonSQLiteOpenHelper.TABLE_PEOPLE, allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Person person = cursorToPerson(cursor);
            peopleList.add(person);

            cursor.moveToNext();
        }

        cursor.close();

        return peopleList;
    }

}
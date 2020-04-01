package tech.bencloud.receiver.database_test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "PersonSQLiteOpenHelper";

    private static final String DATABASE_NAME = "people.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PEOPLE = "people";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_PEOPLE + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_AGE + " text not null);";

    public PersonSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEOPLE);

        onCreate(db);

        Log.d(TAG, "Upgraded database!");
    }
}
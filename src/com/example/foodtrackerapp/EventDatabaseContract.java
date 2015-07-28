package com.example.foodtrackerapp;

import com.example.foodtrackerapp.FoodDatabaseContract.FoodEntry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class EventDatabaseContract {
    
    public EventDatabaseContract() {
    }
    
    /*SQL data types to use for data storage*/
    private static final String TINYINT_TYPE = " TINY_INT";
    private static final String SMALLINT_TYPE = " SMALL_INT";
    private static final String FLOAT_TYPE = " FLOAT";
    private static final String TEXT_TYPE = " TEXT";
    
    private static final String COMMA_SEP = ",";
    
    /*SQL command for table creation*/
    private static final String SQL_CREATE_ENTRIES = 
            "CREATE TABLE " + EventEntry.TABLE_NAME + " (" +
            EventEntry._ID + " INTEGER PRIMARY KEY," +
            EventEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
            EventEntry.COLUMN_NAME_RFID_ID + TEXT_TYPE + COMMA_SEP +
            EventEntry.COLUMN_NAME_AMOUNT + FLOAT_TYPE + COMMA_SEP +
            EventEntry.COLUMN_NAME_DAY + TINYINT_TYPE + COMMA_SEP +
            EventEntry.COLUMN_NAME_MONTH + TINYINT_TYPE + COMMA_SEP +
            EventEntry.COLUMN_NAME_YEAR + SMALLINT_TYPE + COMMA_SEP +
            EventEntry.COLUMN_NAME_HOUR + TINYINT_TYPE + COMMA_SEP +
            EventEntry.COLUMN_NAME_MINUTE + TINYINT_TYPE + COMMA_SEP +
            EventEntry.COLUMN_NAME_SECOND + TINYINT_TYPE + COMMA_SEP + " )";
    
    /*SQL command for table deletion*/
    private static final String SQL_DELETE_ENTRIES = 
            "DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME;
    
    /*Inner class for database helper*/
    public class EventDatabaseHelper extends SQLiteOpenHelper {
        /*If the database schema is changed, the database version number must be incremented*/
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "EventData.db";
        
        public EventDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*When changes are made to the database schema, 
             * logic must be inserted here to specify which fields to 
             * add to/drop from records in the database in order to upgrade it.*/
        }
        
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*When changes are made to the database schema, 
             * logic must be inserted here to specify which fields to 
             * add to/drop from records in the database in order to downgrade it.*/
        }
    }
    
    /*Inner class to define table contents*/
    public static abstract class EventEntry implements BaseColumns {
        public static final String TABLE_NAME= "events";
        public static final String COLUMN_NAME_ENTRY_ID = "entryID";
        public static final String COLUMN_NAME_RFID_ID = "rfidID";
        public static final String COLUMN_NAME_AMOUNT = "amountConsumed";
        public static final String COLUMN_NAME_DAY = "day";
        public static final String COLUMN_NAME_MONTH = "month";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_HOUR = "hour";
        public static final String COLUMN_NAME_MINUTE = "minute";
        public static final String COLUMN_NAME_SECOND = "second";
    }
    

}

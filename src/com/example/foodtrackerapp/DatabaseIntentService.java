package com.example.foodtrackerapp;

import android.app.IntentService;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseIntentService extends IntentService {
    
    private SQLiteOpenHelper helper;
    
    public DatabaseIntentService(SQLiteOpenHelper h) {
        super("DatabaseIntentService");
        helper = h;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SQLiteDatabase db = helper.getReadableDatabase();
    }

}

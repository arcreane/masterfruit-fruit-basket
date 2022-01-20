package com.example.fruitsbasket;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScoreBaseSQLite extends SQLiteOpenHelper {
    private static final String TABLE_SCORES = "TABLES_SCORES";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_DATE = "DATE";
    private static final String COL_SCORE = "SCORES";

    private static final String CREATE_BDD = "CREATE TABLE "+ TABLE_SCORES +
            " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_NAME + " TEXT NOT NULL, " + COL_DATE + " TEXT NOT NULL, " +
            COL_SCORE + " INTEGER); ";

    public ScoreBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_SCORES);
        onCreate(sqLiteDatabase);
    }
}

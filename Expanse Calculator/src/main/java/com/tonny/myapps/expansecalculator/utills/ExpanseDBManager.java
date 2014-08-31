package com.tonny.myapps.expansecalculator.utills;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tonny on 31-08-2014.
 */
public class ExpanseDBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Expanse_Management";
    private static final int DATABASE_VERSION = 2;
    private static final String LOG = "ExpanseDBManager";
    // Table Names
    private static final String EXPANSE = "EXPANSE";
    private static final String EXPANSE_RECORDS = "EXPANSE_RECORDS";
    private static final String PROFILE = "PROFILE";
    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATE_DATE = "create_date";
    private static final String KEY_UPDATE_DATE = "update_date";
    // EXPANSE Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    // Table Create Statements
    // EXPANSE table create statement
    private static final String CREATE_TABLE_EXPANSE = "CREATE TABLE "
            + EXPANSE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
            + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_CREATE_DATE
            + " DATETIME" + KEY_UPDATE_DATE + " DATETIME" + ")";
    // EXPANSE_RECORDS Table - column names
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_EXPANSE_ID = "expanseId";
    // EXPANSE_RECORDS table create statement
    private static final String CREATE_TABLE_EXPANSE_RECORDS = "CREATE TABLE "
            + EXPANSE_RECORDS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_EXPANSE_ID + " INTEGER," + KEY_NAME + " TEXT,"
            + KEY_DESCRIPTION + " TEXT," + KEY_AMOUNT + " INTEGER,"
            + KEY_CREATE_DATE + " DATETIME" + KEY_UPDATE_DATE + " DATETIME" + ")";
    // PROFILE Table - column names
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_EMAIL_ID = "emailId";
    // PROFILE table create statement
    private static final String CREATE_TABLE_PROFILE = "CREATE TABLE "
            + PROFILE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FIRST_NAME
            + " TEXT," + KEY_LAST_NAME + " TEXT," + KEY_EMAIL_ID
            + " TEXT," + KEY_CREATE_DATE + " DATETIME" + KEY_UPDATE_DATE + " DATETIME" + ")";

    ExpanseDBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //creating required tables.
        sqLiteDatabase.execSQL(CREATE_TABLE_EXPANSE);
        sqLiteDatabase.execSQL(CREATE_TABLE_EXPANSE_RECORDS);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //while upgrading drop older table.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_EXPANSE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_EXPANSE_RECORDS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PROFILE);
        //create new tables.
        onCreate(sqLiteDatabase);
    }
}

package com.tonny.myapps.expansecalculator.utills;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tonny.myapps.expansecalculator.beans.Profile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Tonny on 31-08-2014.
 */
public class ExpanseDBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Expanse_Management";
    private static final int DATABASE_VERSION = 2;
    private static final String LOG = "ExpanseDBManager";
    // Table Names
    private static final String EXPANSE = "EXPANSE";
    private static final String DAILY_RECORDS = "DAILY_RECORDS";
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
    private static final String CREATE_TABLE_EXPANSE = "CREATE TABLE IF NOT EXISTS "
            + EXPANSE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
            + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_CREATE_DATE
            + " DATETIME," + KEY_UPDATE_DATE + " DATETIME" + ")";
    // DAILY_RECORDS Table - column names
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_EXPANSE_ID = "expanseId";
    private static final String KEY_PAYER_ID = "payerId";
    // DAILY_RECORDS table create statement
    private static final String CREATE_TABLE_DAILY_RECORDS = "CREATE TABLE IF NOT EXISTS "
            + DAILY_RECORDS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_EXPANSE_ID + " INTEGER," + KEY_PAYER_ID + " INTEGER," + KEY_NAME + " TEXT,"
            + KEY_DESCRIPTION + " TEXT," + KEY_AMOUNT + " INTEGER,"
            + KEY_CREATE_DATE + " DATETIME," + KEY_UPDATE_DATE + " DATETIME" + ")";
    // PROFILE Table - column names
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_EMAIL_ID = "emailId";
    // PROFILE table create statement
    private static final String CREATE_TABLE_PROFILE = "CREATE TABLE IF NOT EXISTS "
            + PROFILE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FIRST_NAME
            + " TEXT," + KEY_LAST_NAME + " TEXT," + KEY_EMAIL_ID
            + " TEXT," + KEY_CREATE_DATE + " DATETIME," + KEY_UPDATE_DATE + " DATETIME" + ")";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public ExpanseDBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //creating required tables.
        sqLiteDatabase.execSQL(CREATE_TABLE_EXPANSE);
        sqLiteDatabase.execSQL(CREATE_TABLE_DAILY_RECORDS);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE);
        Log.i(LOG, CREATE_TABLE_EXPANSE + ", " + CREATE_TABLE_DAILY_RECORDS + " and " + CREATE_TABLE_PROFILE + " tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //while upgrading drop older table.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EXPANSE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DAILY_RECORDS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PROFILE);
        //create new tables.
        onCreate(sqLiteDatabase);
    }

    public long createNewProfile(Profile profile) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, profile.getId());
        contentValues.put(KEY_FIRST_NAME, profile.getFirstName());
        contentValues.put(KEY_LAST_NAME, profile.getLastName());
        contentValues.put(KEY_EMAIL_ID, profile.getEmailId());
        contentValues.put(KEY_CREATE_DATE, getDateTime());
        contentValues.put(KEY_UPDATE_DATE, getDateTime());
        long status = sqLiteDatabase.insert(PROFILE, null, contentValues);
        return status;
    }

    public Profile getProfile(String emailId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + PROFILE + " WHERE " + KEY_EMAIL_ID + "='" + emailId + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Profile profile = new Profile();
        if (null != cursor) {
            cursor.moveToFirst();
        }
        profile.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
        profile.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)));
        profile.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
        profile.setEmailId(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_ID)));
        profile.setCreateDate(stringToDate(cursor.getString(cursor.getColumnIndex(KEY_CREATE_DATE))));
        profile.setUpdateDate(stringToDate(cursor.getString(cursor.getColumnIndex(KEY_UPDATE_DATE))));
        return profile;
    }

    private String getDateTime() {
        Date date = new Date();
        return dateFormat.format(date);
    }

    private Date stringToDate(String sqlDate) {
        Date date = null;
        try {
            date = dateFormat.parse(sqlDate);
        } catch (ParseException pe) {
            Log.e("Parse Exception Occurred", pe.getMessage());
        }
        return date;
    }
}

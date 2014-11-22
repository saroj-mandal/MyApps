package com.tonny.myapps.expansecalculator.utills;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tonny.myapps.expansecalculator.beans.DailyRecords;
import com.tonny.myapps.expansecalculator.beans.Expense;
import com.tonny.myapps.expansecalculator.beans.Profile;

import java.security.interfaces.DSAKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Tonny on 31-08-2014.
 */
public class ExpenseDBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Expense_Management";
    private static final int DATABASE_VERSION = 4;
    private static final String LOG = "ExpenseDBManager";
    // Table Names
    private static final String EXPENSE = "EXPENSE";
    private static final String DAILY_RECORDS = "DAILY_RECORDS";
    private static final String PROFILE = "PROFILE";
    private static final String EXPENSE_USER_RELATION = "EXPENSE_USER_RELATION";
    private static final String RECORDS_PARTICIPANTS_RELATION = "RECORDS_PARTICIPANTS_RELATION";
    // Common column names
    private static final String KEY_ID = "_id";
    private static final String KEY_CREATE_DATE = "create_date";
    private static final String KEY_UPDATE_DATE = "update_date";
    // EXPENSE Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    // Table Create Statements
    // EXPENSE table create statement
    private static final String CREATE_TABLE_EXPENSE = "CREATE TABLE IF NOT EXISTS "
            + EXPENSE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
            + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_CREATE_DATE
            + " DATETIME," + KEY_UPDATE_DATE + " DATETIME" + ")";
    // DAILY_RECORDS Table - column names
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_EXPENSE_ID = "expenseId";
    private static final String KEY_PAYER_ID = "payerId";
    // DAILY_RECORDS table create statement
    private static final String CREATE_TABLE_DAILY_RECORDS = "CREATE TABLE IF NOT EXISTS "
            + DAILY_RECORDS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_EXPENSE_ID + " INTEGER," + KEY_PAYER_ID + " INTEGER," + KEY_NAME + " TEXT,"
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
    // EXPENSE_USER_RELATION Table - column names
    private static final String KEY_PROFILE_ID = "profileId";
    // EXPENSE_USER_RELATION table create statement
    private static final String CREATE_TABLE_EXPENSE_USER_RELATION = "CREATE TABLE IF NOT EXISTS "
            + EXPENSE_USER_RELATION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EXPENSE_ID + " INTEGER,"
            + KEY_PROFILE_ID + " INTEGER," + KEY_CREATE_DATE + " DATETIME," + KEY_UPDATE_DATE + " DATETIME" + ")";
    // RECORDS_PARTICIPANTS_RELATION Table - column names
    private static final String KEY_RECORDS_ID = "recordsId";
    // RECORDS_PARTICIPANTS_RELATION table create statement
    private static final String CREATE_TABLE_RECORDS_PARTICIPANTS_RELATION = "CREATE TABLE IF NOT EXISTS "
            + RECORDS_PARTICIPANTS_RELATION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_RECORDS_ID + " INTEGER,"
            + KEY_PROFILE_ID + " INTEGER," + KEY_CREATE_DATE + " DATETIME," + KEY_UPDATE_DATE + " DATETIME" + ")";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public ExpenseDBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //creating required tables.
        sqLiteDatabase.execSQL(CREATE_TABLE_EXPENSE);
        sqLiteDatabase.execSQL(CREATE_TABLE_DAILY_RECORDS);
        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE);
        sqLiteDatabase.execSQL(CREATE_TABLE_EXPENSE_USER_RELATION);
        sqLiteDatabase.execSQL(CREATE_TABLE_RECORDS_PARTICIPANTS_RELATION);
        Log.i(LOG, EXPENSE + ", " + DAILY_RECORDS + ", " + PROFILE + ", " + EXPENSE_USER_RELATION
                + " and " + RECORDS_PARTICIPANTS_RELATION + " tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //while upgrading drop older table.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EXPENSE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DAILY_RECORDS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PROFILE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EXPENSE_USER_RELATION);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RECORDS_PARTICIPANTS_RELATION);
        //create new tables.
        onCreate(sqLiteDatabase);
    }

    public long createNewProfile(Profile profile) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
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
        Profile profile = null;
        if (null != cursor) {
            profile = new Profile();
            cursor.moveToFirst();
            profile.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
            profile.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)));
            profile.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
            profile.setEmailId(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_ID)));
            profile.setCreateDate(stringToDate(cursor.getString(cursor.getColumnIndex(KEY_CREATE_DATE))));
            profile.setUpdateDate(stringToDate(cursor.getString(cursor.getColumnIndex(KEY_UPDATE_DATE))));
        }
        return profile;
    }

    public long createExpense(Expense expense) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, expense.getName());
        contentValues.put(KEY_DESCRIPTION, expense.getDescription());
        contentValues.put(KEY_CREATE_DATE, getDateTime());
        contentValues.put(KEY_UPDATE_DATE, getDateTime());
        long status = sqLiteDatabase.insert(EXPENSE, null, contentValues);
        return status;
    }

    public List<Expense> getAllExpenseList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + EXPENSE;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        List<Expense> expenseList = new ArrayList<Expense>();
        if (null != cursor) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Expense expense = new Expense();
                expense.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
                expense.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                expense.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                expense.setCreateDate(stringToDate(cursor.getString(cursor.getColumnIndex(KEY_CREATE_DATE))));
                expense.setUpdateDate(stringToDate(cursor.getString(cursor.getColumnIndex(KEY_UPDATE_DATE))));
                expenseList.add(expense);
            }
        }
        return expenseList;
    }

    public List<Profile> getAllUserList() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + PROFILE;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        List<Profile> profileList = new ArrayList<Profile>();
        if (null != cursor) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Profile profile = new Profile();
                profile.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
                profile.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)));
                profile.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
                profile.setEmailId(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_ID)));
                profile.setCreateDate(stringToDate(cursor.getString(cursor.getColumnIndex(KEY_CREATE_DATE))));
                profile.setUpdateDate(stringToDate(cursor.getString(cursor.getColumnIndex(KEY_UPDATE_DATE))));
                profileList.add(profile);
            }
        }
        return profileList;
    }

    public long createUserToExpenseRelation(long expenseId, long profileId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_EXPENSE_ID, expenseId);
        contentValues.put(KEY_PROFILE_ID, profileId);
        contentValues.put(KEY_CREATE_DATE, getDateTime());
        contentValues.put(KEY_UPDATE_DATE, getDateTime());
        long status = sqLiteDatabase.insert(EXPENSE_USER_RELATION, null, contentValues);
        return status;
    }

    public List<Profile> getParticipantList(long expenseId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + PROFILE + " WHERE " + KEY_ID + " IN("
                + " SELECT " + KEY_PROFILE_ID + " FROM " + EXPENSE_USER_RELATION + " WHERE "
                + KEY_EXPENSE_ID + " = " + expenseId + ")";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        List<Profile> participantList = new ArrayList<Profile>();
        if (null != cursor) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Profile profile = new Profile();
                profile.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
                profile.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)));
                profile.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
                profile.setEmailId(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_ID)));
                profile.setCreateDate(stringToDate(cursor.getString(cursor.getColumnIndex(KEY_CREATE_DATE))));
                profile.setUpdateDate(stringToDate(cursor.getString(cursor.getColumnIndex(KEY_UPDATE_DATE))));
                participantList.add(profile);
            }
        }
        return participantList;
    }

    public long createDailyRecords(DailyRecords dailyRecords) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_AMOUNT, dailyRecords.getAmount());
        contentValues.put(KEY_PAYER_ID, dailyRecords.getPayerId());
        contentValues.put(KEY_EXPENSE_ID, dailyRecords.getExpanseId());
        contentValues.put(KEY_CREATE_DATE, getDateTime());
        contentValues.put(KEY_UPDATE_DATE, getDateTime());
        long recordId = sqLiteDatabase.insert(DAILY_RECORDS, null, contentValues);
        if (recordId > 0) {
            contentValues.clear();
            if (dailyRecords.getParticipantIdList().size() > 0) {
                for (long participantId : dailyRecords.getParticipantIdList()) {
                    contentValues.put(KEY_RECORDS_ID, recordId);
                    contentValues.put(KEY_PROFILE_ID, participantId);
                    contentValues.put(KEY_CREATE_DATE, getDateTime());
                    contentValues.put(KEY_UPDATE_DATE, getDateTime());
                    long status = sqLiteDatabase.insert(RECORDS_PARTICIPANTS_RELATION, null, contentValues);
                }
            }
        }
        return recordId;
    }

    public List<DailyRecords> getRecordHistory(long expenseId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<DailyRecords> recordHistory = new ArrayList<DailyRecords>();
        String queryRecords = "SELECT * FROM " + DAILY_RECORDS + " WHERE " + KEY_EXPENSE_ID + " = " + expenseId;
        Cursor cursor = sqLiteDatabase.rawQuery(queryRecords, null);
        if (null != cursor) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                DailyRecords dailyRecord = new DailyRecords();
                dailyRecord.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
                dailyRecord.setAmount(cursor.getLong(cursor.getColumnIndex(KEY_AMOUNT)));
                dailyRecord.setPayerId(cursor.getLong(cursor.getColumnIndex(KEY_PAYER_ID)));
                dailyRecord.setCreateDate(stringToDate(cursor.getString(cursor.getColumnIndex(KEY_CREATE_DATE))));
                dailyRecord.setUpdateDate(stringToDate(cursor.getString(cursor.getColumnIndex(KEY_UPDATE_DATE))));
                String queryParticipants = "SELECT * FROM " + RECORDS_PARTICIPANTS_RELATION + " WHERE " + KEY_RECORDS_ID + " = " + dailyRecord.getId();
                Cursor participantCursor = sqLiteDatabase.rawQuery(queryParticipants, null);
                List<Long> participantList = new ArrayList<Long>();
                if (null != participantCursor) {
                    participantCursor.moveToFirst();
                    while (participantCursor.moveToNext()) {
                        participantList.add(participantCursor.getLong(participantCursor.getColumnIndex(KEY_PROFILE_ID)));
                    }
                }
                dailyRecord.setParticipantIdList(participantList);
                recordHistory.add(dailyRecord);
            }
        }
        return recordHistory;
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

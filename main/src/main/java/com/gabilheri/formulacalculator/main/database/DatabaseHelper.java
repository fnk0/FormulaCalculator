package com.gabilheri.formulacalculator.main.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 6/10/14
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // LogCat Tag
    private static final String LOG_TAG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "formulaCalculator";

    // Tables
    private static final String TABLE_RESULTS = "results";
    private static final String TABLE_FORMULAS = "formulas";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // Result Logs - Column Names
    private static final String KEY_INPUT = "input";
    private static final String KEY_RESULT = "result";

    // Formulas - Column Names
    private static final String KEY_FORMULA = "formula_name";
    private static final String KEY_FORMULA_DRAWABLE = "formula_image";

    // Create Results Table
    private static final String CREATE_TABLE_RESULTS = "CREATE TABLE "
            + TABLE_RESULTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_INPUT
            + " TEXT," + KEY_RESULT + " TEXT," + KEY_CREATED_AT + " DATETIME" + ")";

    // Create Formulas Table
    private static final String CREATE_TABLE_FORMULAS = "CREATE TABLE "
            + TABLE_FORMULAS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FORMULA
            + " TEXT," + KEY_FORMULA_DRAWABLE + " INTEGER," + KEY_CREATED_AT + " DATETIME" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     *
     * @param context
     *         to use to open or create the database
     * @param name
     *         of the database file, or null for an in-memory database
     * @param factory
     *         to use for creating cursor objects, or null for the default
     * @param version
     *         number of the database (starting at 1); if the database is older,
     *         {@link #onUpgrade} will be used to upgrade the database; if the database is
     *         newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     *
     * @param context
     *         to use to open or create the database
     * @param name
     *         of the database file, or null for an in-memory database
     * @param factory
     *         to use for creating cursor objects, or null for the default
     * @param version
     *         number of the database (starting at 1); if the database is older,
     *         {@link #onUpgrade} will be used to upgrade the database; if the database is
     *         newer, {@link #onDowngrade} will be used to downgrade the database
     * @param errorHandler
     *         the {@link DatabaseErrorHandler} to be used when sqlite reports database
     */
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    /**
     *
     * @param db
     *         The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creates the required tables
        db.execSQL(CREATE_TABLE_RESULTS);
        db.execSQL(CREATE_TABLE_FORMULAS);

    }

    /**
     *
     * @param db
     *         The database.
     * @param oldVersion
     *         The old database version.
     * @param newVersion
     *         The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drops the older tables.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORMULAS );
        onCreate(db);

        this.closeDB();
    }

    /**
     *
     * @param resultLog
     * @return
     */
    public long createResultLog(ResultLog resultLog) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_INPUT, resultLog.getInput());
        values.put(KEY_RESULT, resultLog.getResult());

        return db.insert(TABLE_RESULTS, null, values);
    }

    /**
     *
     * @param logId
     * @return
     */
    public ResultLog getResultLog(long logId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_RESULTS + " WHERE " + KEY_ID + " = " + logId;
        Log.i(LOG_TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        ResultLog resultLog = new ResultLog();
        resultLog.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        resultLog.setInput(cursor.getString(cursor.getColumnIndex(KEY_INPUT)));
        resultLog.setResult(cursor.getString(cursor.getColumnIndex(KEY_RESULT)));
        resultLog.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));

        this.closeDB();
        return resultLog;
    }

    /**
     *
     * @return
     */
    public List<ResultLog> getAllResultLogs() {

        List<ResultLog> resultLogs = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_RESULTS;
        Log.i(LOG_TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all rows and add it to the list
        if(cursor.moveToFirst()) {

            do {
                ResultLog resultLog = new ResultLog();
                resultLog.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                resultLog.setInput(cursor.getString(cursor.getColumnIndex(KEY_INPUT)));
                resultLog.setResult(cursor.getString(cursor.getColumnIndex(KEY_RESULT)));
                resultLog.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));
                resultLogs.add(resultLog);
            } while (cursor.moveToNext());
        }

        cursor.close();
        this.closeDB();

        return resultLogs;
    }

    /**
     *
     * @param resultLog
     * @return
     */
    public int updateResultLog(ResultLog resultLog) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INPUT, resultLog.getInput());
        values.put(KEY_RESULT, resultLog.getResult());

        // Updating row
        return db.update(TABLE_RESULTS, values, KEY_ID + " = ?", new String[] {String.valueOf(resultLog.getId())});
    }

    public void deleteResultLog(long logId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_RESULTS, KEY_ID + " = ?", new String[] {String.valueOf(logId)});
        this.closeDB();
    }

    public void deleteAllResultLogs() {
        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_RESULTS, null, null);
        this.closeDB();
    }

    /**
     * Closes the Database Connection.
     */
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}

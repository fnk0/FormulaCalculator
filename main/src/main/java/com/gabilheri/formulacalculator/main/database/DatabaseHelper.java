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
    private static final String TABLE_THEMES = "themes";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_USERNAME = "username";

    // Result Logs - Column Names
    private static final String KEY_INPUT = "input";
    private static final String KEY_RESULT = "result";

    // Formulas - Column Names
    private static final String KEY_FORMULA = "formula_name";
    private static final String KEY_FORMULA_DRAWABLE = "formula_image";

    // Themes Column Names
    private static final String KEY_THEME_TYPE = "theme_type"; // Type of this Theme
    private static final String KEY_THEME_NAME = "theme_name";
    private static final String KEY_PRIMARY_BUTTON = "primary_button"; // Color for the Primary Buttons
    private static final String KEY_SECONDARY_BUTTON = "secondary_button"; // Color for the Secondary Buttons
    private static final String KEY_DISPLAY_COLOR = "display_color"; // Color for the Display Colors
    private static final String KEY_DISPLAY_TEXT_COLOR = "display_text_color"; // Color for the Text Display Color
    private static final String KEY_PRIMARY_BUTTON_TEXT_COLOR = "primary_button_text_color"; // Color for the Text in the Primary Buttons
    private static final String KEY_SECONDARY_BUTTON_TEXT_COLOR = "secondary_button_text_color"; // Color for the Text in the Secondary Buttons
    private static final String KEY_SELECTED_COLOR = "selected_color";
    private static final String KEY_PRIMARY_HIGHLIGHT = "primary_highlight";
    private static final String KEY_SECONDARY_HIGHLIGHT = "secondary_highlight";


    // Create Results Table
    private static final String CREATE_TABLE_RESULTS = "CREATE TABLE "+ TABLE_RESULTS + "(" +
            KEY_ID + " INTEGER PRIMARY KEY," +
            KEY_INPUT + " TEXT," +
            KEY_RESULT + " TEXT," +
            KEY_CREATED_AT + " DATETIME" +
            ")";

    // Create Formulas Table
    private static final String CREATE_TABLE_FORMULAS = "CREATE TABLE " + TABLE_FORMULAS + "(" +
            KEY_ID + " INTEGER PRIMARY KEY," +
            KEY_FORMULA + " TEXT," +
            KEY_FORMULA_DRAWABLE + " INTEGER," +
            KEY_CREATED_AT + " DATETIME" +
            ")";

    private static final String CREATE_TABLE_THEMES = "CREATE TABLE " + TABLE_THEMES + "(" +
            KEY_ID + " INTEGER PRIMARY KEY," +
            KEY_USERNAME + " TEXT," +
            KEY_THEME_NAME + " TEXT," +
            KEY_THEME_TYPE + " TEXT," +
            KEY_PRIMARY_BUTTON + " TEXT," +
            KEY_PRIMARY_HIGHLIGHT + " TEXT," +
            KEY_PRIMARY_BUTTON_TEXT_COLOR + " TEXT," +
            KEY_SECONDARY_BUTTON + " TEXT," +
            KEY_SECONDARY_HIGHLIGHT + " TEXT," +
            KEY_SECONDARY_BUTTON_TEXT_COLOR + " TEXT," +
            KEY_DISPLAY_COLOR + " TEXT," +
            KEY_DISPLAY_TEXT_COLOR + " TEXT," +
            KEY_SELECTED_COLOR + " TEXT," +
            KEY_CREATED_AT + " DATETIME" +
            ")";

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

        Log.i(LOG_TAG, CREATE_TABLE_THEMES);
        // Creates the required tables
        db.execSQL(CREATE_TABLE_THEMES);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THEMES);
        onCreate(db);

        this.closeDB();
    }

    /**
     *
     * @param resultLog
     *          The Result log to be inserted into the Database
     * @return
     *          The inserted statement for the Database - Used internally by Android
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
     * @param theme
     *          The Theme to be inserted in the Database
     * @return
     *          The inserted statement for the Database - Used internally by Android
     */
    public long createTheme(Theme theme) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(theme.getUsername() == null) {
            values.put(KEY_USERNAME, "default");
        } else {
            values.put(KEY_USERNAME, theme.getUsername());
        }
        values.put(KEY_THEME_TYPE, theme.getThemeType());
        values.put(KEY_THEME_NAME, theme.getThemeName());
        values.put(KEY_PRIMARY_BUTTON, theme.getPrimaryColor());
        values.put(KEY_PRIMARY_HIGHLIGHT, theme.getPrimaryHighlightColor());
        values.put(KEY_PRIMARY_BUTTON_TEXT_COLOR, theme.getPrimaryButtonTextColor());
        values.put(KEY_DISPLAY_COLOR, theme.getDisplayColor());
        values.put(KEY_DISPLAY_TEXT_COLOR, theme.getDisplayTextColor());
        values.put(KEY_SECONDARY_BUTTON, theme.getSecondaryColor());
        values.put(KEY_SECONDARY_HIGHLIGHT, theme.getSecondaryHighlightColor());
        values.put(KEY_SECONDARY_BUTTON_TEXT_COLOR, theme.getSecondaryButtonTextColor());
        values.put(KEY_SELECTED_COLOR, theme.getSelectedColor());

        return db.insert(TABLE_THEMES, null, values);
    }

    /**
     *
     * @param logId
     *          The ID for the Result Log
     * @return
     *          The Result Log
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
     * @param themeID
     *          The ID for this theme
     * @param username
     *          The username for this theme
     * @return
     *          A Theme object.
     */
    public Theme getTheme(long themeID, String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_THEMES + " WHERE " + KEY_ID + " = " + themeID +
                " AND " + KEY_USERNAME + " = '" + username + "'";
        Log.i(LOG_TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        Theme selectedTheme = new Theme();
        selectedTheme.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)))
                .setThemeName(cursor.getString(cursor.getColumnIndex(KEY_THEME_NAME)))
                .setPrimaryColor(cursor.getInt(cursor.getColumnIndex(KEY_PRIMARY_BUTTON)))
                .setSecondaryColor(cursor.getInt(cursor.getColumnIndex(KEY_SECONDARY_BUTTON)))
                .setDisplayColor(cursor.getInt(cursor.getColumnIndex(KEY_DISPLAY_COLOR)))
                .setPrimaryButtonTextColor(cursor.getInt(cursor.getColumnIndex(KEY_PRIMARY_BUTTON_TEXT_COLOR)))
                .setSecondaryButtonTextColor(cursor.getInt(cursor.getColumnIndex(KEY_SECONDARY_BUTTON_TEXT_COLOR)))
                .setDisplayTextColor(cursor.getInt(cursor.getColumnIndex(KEY_DISPLAY_TEXT_COLOR)))
                .setSelectedColor(cursor.getInt(cursor.getColumnIndex(KEY_SELECTED_COLOR)))
                .setPrimaryHighlightColor(cursor.getInt(cursor.getColumnIndex(KEY_PRIMARY_HIGHLIGHT)))
                .setSecondaryHighlightColor(cursor.getInt(cursor.getColumnIndex(KEY_SECONDARY_HIGHLIGHT)))
                .setThemeType(cursor.getInt(cursor.getColumnIndex(KEY_THEME_TYPE)));

        //this.closeDB();
        return selectedTheme;
    }

    public Theme getThemeByName(String themeName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_THEMES + " WHERE " + KEY_THEME_NAME + " = '" + themeName + "'";
        Log.i(LOG_TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        Theme selectedTheme = new Theme();
        selectedTheme.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)))
                .setThemeName(cursor.getString(cursor.getColumnIndex(KEY_THEME_NAME)))
                .setPrimaryColor(cursor.getInt(cursor.getColumnIndex(KEY_PRIMARY_BUTTON)))
                .setSecondaryColor(cursor.getInt(cursor.getColumnIndex(KEY_SECONDARY_BUTTON)))
                .setDisplayColor(cursor.getInt(cursor.getColumnIndex(KEY_DISPLAY_COLOR)))
                .setPrimaryButtonTextColor(cursor.getInt(cursor.getColumnIndex(KEY_PRIMARY_BUTTON_TEXT_COLOR)))
                .setSecondaryButtonTextColor(cursor.getInt(cursor.getColumnIndex(KEY_SECONDARY_BUTTON_TEXT_COLOR)))
                .setDisplayTextColor(cursor.getInt(cursor.getColumnIndex(KEY_DISPLAY_TEXT_COLOR)))
                .setSelectedColor(cursor.getInt(cursor.getColumnIndex(KEY_SELECTED_COLOR)))
                .setPrimaryHighlightColor(cursor.getInt(cursor.getColumnIndex(KEY_PRIMARY_HIGHLIGHT)))
                .setSecondaryHighlightColor(cursor.getInt(cursor.getColumnIndex(KEY_SECONDARY_HIGHLIGHT)))
                .setThemeType(cursor.getInt(cursor.getColumnIndex(KEY_THEME_TYPE)));
        //this.closeDB();
        return selectedTheme;
    }

    /**
     *
     * @return
     *      All the logs from the Logs Database
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
     * Returns all the Themes for the Database
     *
     * @param username
     *      The username to get the Themes
     * @return
     *      All the Themes for the specified Username
     *
     */
    public List<Theme> getAllThemesForUser(String username) {

        List<Theme> themesResult = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_THEMES + " WHERE " + KEY_USERNAME + " = '" + username + "' OR " + KEY_THEME_TYPE + " = " + Theme.THEME_SYSTEM;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Theme selectedTheme = new Theme();
                selectedTheme.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)))
                        .setThemeName(cursor.getString(cursor.getColumnIndex(KEY_THEME_NAME)))
                        .setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)))
                        .setPrimaryColor(cursor.getInt(cursor.getColumnIndex(KEY_PRIMARY_BUTTON)))
                        .setSecondaryColor(cursor.getInt(cursor.getColumnIndex(KEY_SECONDARY_BUTTON)))
                        .setDisplayColor(cursor.getInt(cursor.getColumnIndex(KEY_DISPLAY_COLOR)))
                        .setPrimaryButtonTextColor(cursor.getInt(cursor.getColumnIndex(KEY_PRIMARY_BUTTON_TEXT_COLOR)))
                        .setSecondaryButtonTextColor(cursor.getInt(cursor.getColumnIndex(KEY_SECONDARY_BUTTON_TEXT_COLOR)))
                        .setDisplayTextColor(cursor.getInt(cursor.getColumnIndex(KEY_DISPLAY_TEXT_COLOR)))
                        .setSelectedColor(cursor.getInt(cursor.getColumnIndex(KEY_SELECTED_COLOR)))
                        .setPrimaryHighlightColor(cursor.getInt(cursor.getColumnIndex(KEY_PRIMARY_HIGHLIGHT)))
                        .setSecondaryHighlightColor(cursor.getInt(cursor.getColumnIndex(KEY_SECONDARY_HIGHLIGHT)))
                        .setThemeType(cursor.getInt(cursor.getColumnIndex(KEY_THEME_TYPE)));
                themesResult.add(selectedTheme);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //this.closeDB();
        return themesResult;
    }

    /**
     *
     * @param theme
     *          The Theme to be updated
     * @return
     *          The inserted statement for the Database - Used internally by Android
     */
    public int updateTheme(Theme theme) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, theme.getUsername());
        values.put(KEY_THEME_TYPE, theme.getThemeType());
        values.put(KEY_THEME_NAME, theme.getThemeName());
        values.put(KEY_PRIMARY_BUTTON, theme.getPrimaryColor());
        values.put(KEY_PRIMARY_HIGHLIGHT, theme.getPrimaryHighlightColor());
        values.put(KEY_PRIMARY_BUTTON_TEXT_COLOR, theme.getPrimaryButtonTextColor());
        values.put(KEY_DISPLAY_COLOR, theme.getDisplayColor());
        values.put(KEY_DISPLAY_TEXT_COLOR, theme.getDisplayTextColor());
        values.put(KEY_SECONDARY_BUTTON, theme.getSecondaryColor());
        values.put(KEY_SECONDARY_HIGHLIGHT, theme.getSecondaryHighlightColor());
        values.put(KEY_SECONDARY_BUTTON_TEXT_COLOR, theme.getSecondaryButtonTextColor());
        values.put(KEY_SELECTED_COLOR, theme.getSelectedColor());

        return db.update(TABLE_THEMES, values, KEY_ID + " = ?", new String[] {String.valueOf(theme.getId())});
    }

    /**
     *
     * @param resultLog
     *      The Result log to be updated into the Database
     * @return
     *      The inserted statement for the Database - Used internally by Android
     */
    public int updateResultLog(ResultLog resultLog) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INPUT, resultLog.getInput());
        values.put(KEY_RESULT, resultLog.getResult());

        // Updating row
        return db.update(TABLE_RESULTS, values, KEY_ID + " = ?", new String[] {String.valueOf(resultLog.getId())});
    }

    /**
     *
     * @param logId
     *          The Id for the Log to be deleted
     */
    public void deleteResultLog(long logId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_RESULTS, KEY_ID + " = ?", new String[] {String.valueOf(logId)});
        this.closeDB();
    }

    /**
     * Deletes a Theme from the Database
     *
     * @param themeId
     *      The ID for the theme to be deleted
     */
    public void deleteTheme(long themeId, String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        Theme mTheme = getTheme(themeId, username);

        if(mTheme.getThemeType() == Theme.THEME_SYSTEM) {
            return;
        }

        db.delete(TABLE_THEMES, KEY_ID + " = ?", new String[]{String.valueOf(themeId)});
        //this.closeDB();
    }

    /**
     * Deletes all the Result Logs for this Database
     */
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

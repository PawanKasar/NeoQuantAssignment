package com.neoquant.assignment.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class DataBaseOpenHelper extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "NeoquantContact";

    /**
     *
     * Contact master table
     */
    private static final String KEY_Contact_ID = "Contact_ID";
    private static final String KEY_Contact_Person_Name = "Contact_Person_Name";
    private static final String KEY_Contact_Number = "Contact_Number";

    //Table Names
    private static String ContactMasterTable = "ContactMasterTable";


    // Todo table create statement
    private static final String CREATE_TABLE_ContactMasterTable = "CREATE TABLE IF NOT EXISTS " + ContactMasterTable +
            "("
            + KEY_Contact_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_Contact_Person_Name + " TEXT,"
            + KEY_Contact_Number + " TEXT"
            + ")";

    public DataBaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //
        // this.context = context;
        //SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory() + File.separator + DATABASE_NAME, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creating Required Table
        db.execSQL(CREATE_TABLE_ContactMasterTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContactMasterTable);
    }


    /**
     * Insert data Into Store Master Table
     */
    public long insertIntoContactMasterTable(String contactPersonName, String contactNumber){

        long insertedRecord=0;
        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_Contact_Person_Name, contactPersonName);
            values.put(KEY_Contact_Number, contactNumber);
            // Inserting Row
            insertedRecord =  db.insert(ContactMasterTable, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            //Log.e("result","something went wrong");
            e.printStackTrace();
            insertedRecord =0;
        } finally {
            db.endTransaction();
        }
        db.close(); // Closing database connection

        return insertedRecord;
    }

    public JSONObject getDataFromStoreMaster(String contactNumber) {

        SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
        JSONArray resultSet = new JSONArray();
        JSONObject rowObject = null;
        sqliteDatabase.beginTransaction();
        try {
            Cursor cursor = sqliteDatabase.rawQuery("select * from "+ContactMasterTable+" WHERE "+ KEY_Contact_Number +" = '" + contactNumber + "'", null);

            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                int totalColumn = cursor.getColumnCount();
                rowObject = new JSONObject();
                for (int i = 0; i < totalColumn; i++) {
                    if (cursor.getColumnName(i) != null) {
                        try {
                            rowObject.put(cursor.getColumnName(i),
                                    cursor.getString(i));
                        } catch (Exception e) {
                            Log.d("TAG", e.getMessage());
                        }
                    }
                }
                resultSet.put(rowObject);
                cursor.moveToNext();
            }

            cursor.close();
            sqliteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            rowObject = null;
        } finally {
            sqliteDatabase.endTransaction();
        }

        return rowObject;
    }


    public int getContactMasterCount() {
        String countQuery = "SELECT  * FROM " + ContactMasterTable;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    public boolean columnExists(String contactNumber) {
        String rawQuery = "Select * from " + ContactMasterTable + " where " + KEY_Contact_Number + " = " + contactNumber;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(rawQuery, null);
        cursor.moveToFirst();

        // cursor.getInt(0) is 1 if column with value exists
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }
}

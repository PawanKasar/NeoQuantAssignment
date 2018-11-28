package com.neoquant.assignment.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

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
        super(context, Environment.getExternalStorageDirectory() + File.separator + DATABASE_NAME, null, DATABASE_VERSION);
        //
        // this.context = context;
        SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory() + File.separator + DATABASE_NAME, null);
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
    public long insertIntoContactMasterTable(String transactionNo, String strDatetime, String storeIdBarcode,
                                                     String cardId, String dateStr, String deno_2000, String deno_500, String deno_200, String deno_100, String deno_50,
                                                     String deno_20, String deno_10, String deno_5, String denomisccount, String denomiscvalue,
                                                     String total_amount, String supervisor_name, String filePathSign,
                                                     String serialESNNo){

        long insertedRecord=0;
        SQLiteDatabase db = this.getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_HHT_TransactionNumber, transactionNo);
            values.put(KEY_Store_Id, storeIdBarcode);
            values.put(KEY_Card_Mast_Id, cardId);
            values.put(KEY_Collection_Date, dateStr);
            // Inserting Row
            insertedRecord =  db.insert(CollectionTransactionTable, null, values);
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
}

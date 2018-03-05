package com.ranjeet.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "emp_db";
    private  static  final String TABLE_NAME="Details_table";
    private static final String COL1="Firstname";
    private static final String COL2="Lastname";
    private static final String COL3="Qualification";
    private static final String COL4="Email";
    private static final String COL5="Mobileno";
    private static final String COL6="Domain";
    private static final String COL7="Salary";
    private static final String COL8="Companyname";
    private static final String COL9="Address";

// table creation

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    String CREATE_TABLE_CALL = "CREATE TABLE " + TABLE_NAME + "(" + COL1 + " TEXT," + COL2 + " TEXT," + COL3 + " TEXT," + COL4
            + " TEXT," + COL5 + " INTEGER," + COL6 + " TEXT," + COL7 + " INTEGER," + COL8 + " TEXT," + COL9 + " TEXT "+")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("My table is created",CREATE_TABLE_CALL);
        db.execSQL(CREATE_TABLE_CALL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
//this one for inserting data

    public boolean insertData(String firstname,String lastname,String qualification,String email,String mobileno,String domain,String salary,String companyname,String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, firstname);
        contentValues.put(COL2, lastname);
        contentValues.put(COL3, qualification);
        contentValues.put(COL4, email);
        contentValues.put(COL5, mobileno);
        contentValues.put(COL6, domain);
        contentValues.put(COL7, salary);
        contentValues.put(COL8, companyname);
        contentValues.put(COL9, address);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    //This query is used to get all data from database
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }


//this query is used to search data from database pertuclar person through firstnameui

    public Cursor searchquery(String firstname) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.query(TABLE_NAME, new String[] { COL1,
                        COL2, COL3 ,COL4 ,COL5,COL6,COL7,COL8,COL9 }, COL1 + "=?",
                new String[] {(firstname) }, null, null, null, null);

        return res;
    }

    public boolean updateData(String firstname, String lastname, String qualification, String email, String mobileno, String domain ,String salary,String companyname,String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, firstname);
        contentValues.put(COL2, lastname);
        contentValues.put(COL3, qualification);
        contentValues.put(COL4, email);
        contentValues.put(COL5, mobileno);
        contentValues.put(COL6, domain);
        contentValues.put(COL7, salary);
        contentValues.put(COL8, companyname);
        contentValues.put(COL9,address);

        db.update(TABLE_NAME, contentValues,  "COL1 = ?", new String[]{firstname});
        return true;
    }
    public Integer deletedData(String Firstname) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deel=db.delete(TABLE_NAME, "COL1 = ?", new String[]{Firstname});
        return deel;
    }
    }


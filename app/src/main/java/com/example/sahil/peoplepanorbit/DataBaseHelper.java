package com.example.sahil.peoplepanorbit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SAHIL on 17-06-2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME ="meditrack.db";
    public static final String TABLE_NAME ="medicine_table";
   // public static final String COL_1 ="ID";
    public static final String COL_1 ="NAME";
    public static final String COL_2 ="DOSE_FREQ";
    public static final String COL_3 ="QUANTITY";
    public static final String COL_4 ="DOSE_DAY";
    public static final String COL_5 ="NO_PURCHASED";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(NAME TEXT PRIMARY KEY ,DOSE_FREQ TEXT,QUANTITY INTEGER,DOSE_DAY INTEGER,NO_PURCHASED INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXIST"+DATABASE_NAME);
        onCreate(db);

    }

    public  boolean insertData(String name ,String dose_freq,String quantity ,String dose_day , String no_purchased)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,dose_freq);
        contentValues.put(COL_3, quantity);
        contentValues.put(COL_4, dose_day);
        contentValues.put(COL_5, no_purchased);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select name from "+ TABLE_NAME,null);
        return  res;
    }

    public Cursor getAllData2()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select name , quantity , dose_day from "+ TABLE_NAME,null);
        return  res;
    }


}

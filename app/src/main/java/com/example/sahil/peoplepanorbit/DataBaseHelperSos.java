package com.example.sahil.peoplepanorbit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SAHIL on 18-06-2017.
 */

public class DataBaseHelperSos extends SQLiteOpenHelper {


    public static final String DATABASE_NAME ="sos.db";
    public static final String TABLE_NAME ="sos_setting";
    // public static final String COL_1 ="ID";
    public static final String COL_1 ="NAME";
    public static final String COL_2 ="NUMBER";


    public DataBaseHelperSos(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(NAME TEXT PRIMARY KEY ,NUMBER INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXIST"+DATABASE_NAME);
        onCreate(db);

    }

    public  boolean insertData(String name ,String number)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,number);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataSos()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select number from "+ TABLE_NAME,null);
        return  res;
    }
}

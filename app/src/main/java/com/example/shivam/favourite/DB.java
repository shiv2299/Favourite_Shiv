package com.example.shivam.favourite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shivam on 06-09-2019.
 */

public class DB extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabasedb;
    public DB(Context context) {
        super(context, "Data", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Items (content text primary key, fav integer)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabasedb.execSQL("DROP TABLE IF EXISTS table_data");
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String cont, int fav){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("content",cont);
        contentValues.put("fav",fav);
        long result = db.insert("Items",null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from Items",null);

    }
    public Cursor getFav() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from Items where fav=1",null);

    }
    public void updateData(String s,int f){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update Items set fav="+f+" where content='"+s+"'");
    }
}

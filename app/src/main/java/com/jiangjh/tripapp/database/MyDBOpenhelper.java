package com.jiangjh.tripapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JiaHao.Huang on 2018/3/8.
 */

public class MyDBOpenhelper extends SQLiteOpenHelper{
    public MyDBOpenhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE account(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"name VARCHAR(10), password VARCHAR(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("ALTER TABLE account ADD weather Varchar");
    }
}

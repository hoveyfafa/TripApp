package com.jiangjh.tripapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * @author Jinghao.Jiang
 * @date 2018/3/8
 */

public class FavoriteDBOpenHelper extends SQLiteOpenHelper {
    public FavoriteDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE favorite(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"time TEXT, title TEXT,description TEXT,picurl TEXT,url TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("ALTER TABLE favorite ADD weather Varchar");
    }
}

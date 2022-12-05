package com.example.rabbit.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class MySQLiteHelper extends SQLiteOpenHelper {
    public MySQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public MySQLiteHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+Constants.TABLE_NAME_USER+"(user_name varchar,user_password)";
        db.execSQL(sql);
        sql = "create table "+Constants.TABLE_NAME_STUDENT+"(student_name varchar,student_major varchar,student_class varchar,student_gender varchar,student_phone,student_image)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

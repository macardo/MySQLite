package com.macardo.mysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    //1.编写创建表的SQL语句
    private static final String CREATE_COURSE = "create table Course (" +
            "id integer primary key autoincrement," +
            "name text," +
            "teacher text," +
            "price real)";
    private static final String CREATE_STUDENT = "create table Student (" +
            "id integer primary key autoincrement," +
            "name text," +
            "student text," +
            "age integer)" ;

    private Context mContext;

    public DatabaseHelper(Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //2.执行这条sql语句
        db.execSQL(CREATE_COURSE);
        db.execSQL(CREATE_STUDENT);
        Toast.makeText(mContext,"创建数据库成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Course");
        db.execSQL("drop table if exists Student");
        onCreate(db);
    }
}

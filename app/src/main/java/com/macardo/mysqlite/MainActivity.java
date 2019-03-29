package com.macardo.mysqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    //3.声明数据库帮助类
    private DatabaseHelper mHelper;
    Button mButton;
    Button mBtnAdd;
    Button mBtnDelete;
    Button mBtnUpdate;
    Button mBtnQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDb();


    }

    private void initDb() {
        mHelper = new DatabaseHelper(MainActivity.this,"CourseStore",null,2);
        mHelper.getWritableDatabase();
    }

    private void initViews() {
        mButton = findViewById(R.id.button);
        mBtnAdd = findViewById(R.id.btnAdd);
        mBtnDelete = findViewById(R.id.btnDelete);
        mBtnUpdate = findViewById(R.id.btnUpdate);
        mBtnQuery = findViewById(R.id.btnQuery);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //4.初始化帮助类
//                mHelper = new DatabaseHelper(MainActivity.this,"CourseStore",null,1);
                mHelper = new DatabaseHelper(MainActivity.this,"CourseStore",null,2);
                //5.创建数据库
                mHelper.getWritableDatabase();
            }
        });

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        mBtnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });
    }


    private void add() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        //SQL语句：增
/*        db.execSQL("insert into Student(name,student,age) values(?,?,?)",
                new String[]{"SQLite","macardo","21"});*/

        //通过SQLiteDatabase-API:增
        ContentValues values = new ContentValues();//就是一个存储键值对的集合
        values.put("name","Database");
        values.put("student","macardo1");
        values.put("age","21");
        db.insert("Student",null,values);
    }

    private void delete(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        //SQL语句：删
/*        db.execSQL("delete from Student where student = ?",
                new String[]{"macardo"});*/

        //通过SQLiteDatabase-API:删
        db.delete("Student","student = ?",new String[]{"macardo1"});

    }

    private void update() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        //SQL语句：改
/*        db.execSQL("update Student set student = ? where name = ?",
                new String[]{"macardo2","SQLite"});*/

        //通过SQLiteDatabase-API:改
        ContentValues values = new ContentValues();
        values.put("student","macardo3");
        db.update("Student",values,"name = ?",new String[]{"SQLite"});

    }

    private void query() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        //SQL语句：查
        //Cursor cursor = db.rawQuery("select * from Student", null);

        //通过SQLiteDatabase-API:改
        Cursor cursor = db.query("Student",null,null,null,null,null,null);
       
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String student = cursor.getString(cursor.getColumnIndex("student"));
                Integer age = cursor.getInt(cursor.getColumnIndex("age"));
                Log.d("MainActivity","name = " + name);
                Log.d("MainActivity","student = " + student);
                Log.d("MainActivity","age = " + age);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}

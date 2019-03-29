package com.macardo.mysqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //3.声明数据库帮助类
    private DatabaseHelper mHelper;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //4.初始化帮助类
                mHelper = new DatabaseHelper(MainActivity.this,"CourseStore",null,1);
                //5.创建数据库
                mHelper.getWritableDatabase();
            }
        });

    }
}

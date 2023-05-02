package com.example.databaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;


import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<StudentModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data);

        lv = findViewById(R.id.view_list);
        DatabaseHelper dv = new DatabaseHelper(ViewActivity.this);
        data=dv.readdata();
        Log.e("Database Size", ""+data.size());
        lv.setAdapter(new CustomAdapter(ViewActivity.this,data));
    }
}
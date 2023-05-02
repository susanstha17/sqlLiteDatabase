package com.example.databaseproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "MyDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table if not exists studenttable(id integer primary key autoincrement, name varchar, address varchar, phone varchar)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists studenttable");
        onCreate(sqLiteDatabase);
    }

    public void add_data(StudentModel s) {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("Insert into studenttable ()");

        ContentValues cv = new ContentValues();
        cv.put("name",s.getName());
        cv.put("address",s.getAddress());
        cv.put("phone",s.getPhone());
        db.insert("studenttable",null,cv);
    }

    @SuppressLint("Range")
    public ArrayList<StudentModel> readdata() {
        ArrayList<StudentModel> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from studenttable", null);
        if (c.moveToFirst()) {
            do {
                StudentModel sm = new StudentModel();
                sm.setId(c.getInt(c.getColumnIndex("id")));
                sm.setName(c.getString(c.getColumnIndex("name")));
                sm.setAddress(c.getString(c.getColumnIndex("address")));
                sm.setPhone(c.getString(c.getColumnIndex("phone")));
                data.add(sm);
            } while (c.moveToNext());
        }
        return data;
    }

    public void delete(int id) {
      SQLiteDatabase db = this.getWritableDatabase();
     db.execSQL("delete from studenttable where id="+id);

    }
}


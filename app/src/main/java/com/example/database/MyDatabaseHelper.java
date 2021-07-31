package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
 private static final String DATABASE_NAME = "Student.db";
 private static final String TABLE_NAME = "Student_details";
    private static final String ID = "_id";
    private static final String NAME = "Name";
    private static final String AGE = "Age";
    private static final String GENDER = "Gender";
 private static final int VERSION_NUMBER = 2;
 private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255), "+AGE+" INTEGER, "+GENDER+" VARCHAR(15))";
 private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
// private static final String All_DATA = "SELECT * FROM " +TABLE_NAME;
 private Context context;
 public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            Toast.makeText(context,"on create is created ",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }catch (Exception e){

            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

      try{
          Toast.makeText(context,"on Upgrade is created ",Toast.LENGTH_LONG).show();
          sqLiteDatabase.execSQL(DROP_TABLE);
          onCreate(sqLiteDatabase);
      }catch (Exception e){

          Toast.makeText(context,"Exception :"+e,Toast.LENGTH_LONG).show();
      }
    }
    public long insert(String name,String age,String gender){
     SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME,name);
        cv.put(AGE,age);
        cv.put(GENDER,gender);
        long rkid = sqLiteDatabase.insert(TABLE_NAME,null,cv);
        return rkid;
    }
//    Ddata(){
//     SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//     sqLiteDatabase.rawQuery(All_DATA,null);
//
//    }

    public Cursor showAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null );
        return cursor;
    }
 public  int deleteData(String id){

     SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
     int value = sqLiteDatabase.delete(TABLE_NAME, ID+"=?" ,new String[] {id});
     return value;
 }

}




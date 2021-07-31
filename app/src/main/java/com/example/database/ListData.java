package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListData extends AppCompatActivity{
    private ListView listView;
    private MyDatabaseHelper myDatabaseHelper;
    ArrayAdapter<String> adapterView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        listView = findViewById(R.id.listviewid);
        myDatabaseHelper = new MyDatabaseHelper(this);
     loadData();
    }
 public void loadData(){
     ArrayList<String> listData = new ArrayList<>();
     Cursor cursor = myDatabaseHelper.showAllData();


     if (cursor.getCount() == 0) {

         Toast.makeText(getApplicationContext(), "No data is available", Toast.LENGTH_LONG).show();

     }
else{
    while (cursor.moveToNext()){
        listData.add(cursor.getString(0)+" \t "+cursor.getString(1) +"\t" +cursor.getString(2)+"\t" +cursor.getString(3));

    }

     }
     ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.txid,listData);
listView.setAdapter(adapter);
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        String selectValue = adapterView.getItem(i).toString();
      Toast.makeText(getApplicationContext(), "Selected view", Toast.LENGTH_LONG).show();
    }
});

//listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//      Intent intent = new Intent(ListData.this,MainActivity.class);
//        startActivity(intent);
//    }
//});
     ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.list_item,R.id.imgid,listData);
     listView.setAdapter(adapter1);
     if(listView.getId()==R.id.imgid){
         Toast.makeText(getApplicationContext(), " Data is deleted", Toast.LENGTH_LONG).show();

     }


//     if(adapter1 !=null) {
//         int value = myDatabaseHelper.deleteData(id);
//         if (value < 0) {
//             Toast.makeText(getApplicationContext(), " Data is not deleted ", Toast.LENGTH_LONG).show();
//         } else {
//             Toast.makeText(getApplicationContext(), " Data is deleted", Toast.LENGTH_LONG).show();
//         }
//
//     }
//     else {
//         Toast.makeText(getApplicationContext(), " method is wrong", Toast.LENGTH_LONG).show();
//     }
 }

}
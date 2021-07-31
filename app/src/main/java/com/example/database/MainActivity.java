package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText Nameedittext,Ageedittext;
    private Button Addbutton,Allbutton,Udbutton,Dtbutton;
    Spinner Genderspn;

    String[] gender = { "Male", "Female", "others"};
    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabaseHelper = new MyDatabaseHelper(this);
       SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
       Nameedittext = findViewById(R.id.editText1id);
       Ageedittext = findViewById(R.id.editText2id);
       Genderspn = findViewById(R.id.spinnergenderid);

       Addbutton = findViewById(R.id.button1id);
       Allbutton = findViewById(R.id.button2id);
       Udbutton = findViewById(R.id.button3id);
       Dtbutton = findViewById(R.id.button4id);
       Addbutton.setOnClickListener(this);
       Allbutton.setOnClickListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Genderspn.setAdapter(aa);
    Genderspn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Genderspn.setOnItemSelectedListener(this);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });

    }

    @Override
    public void onClick(View v) {
        String name=Nameedittext.getText() .toString();
        String age=Ageedittext.getText().toString();
        String gender=Genderspn.getSelectedItem().toString();
//      save
        if(v.getId()==R.id.button1id){



     if(name.equals("") && age.equals("") && gender.equals("")){

         Toast.makeText(getApplicationContext(), "Please enter all data", Toast.LENGTH_LONG).show();
     }
     else{
         long rkid = myDatabaseHelper.insert(name,age,gender);
         if(rkid==-1){
             Toast.makeText(getApplicationContext(), " Data is not inserted successful", Toast.LENGTH_LONG).show();
            Nameedittext.setText("");
            Ageedittext.setText("");
            Genderspn.isSelected();
         }
         else{

             Toast.makeText(getApplicationContext(), " Data is inserted successful", Toast.LENGTH_LONG).show();
         }
     }
      }
      //show
      else if(v.getId()==R.id.button2id){
          Intent intent = new Intent(MainActivity.this,ListData.class);
          startActivity(intent);


      }
      //update
      else if(v.getId()==R.id.button3id){



      }
      //delete
      else if(v.getId()==R.id.button4id){

//     int value = myDatabaseHelper.deleteData(id);
//     if(value<0){
//         Toast.makeText(getApplicationContext(), " Data is not deleted ", Toast.LENGTH_LONG).show();
//            }
//     else {
//         Toast.makeText(getApplicationContext(), " Data is deleted", Toast.LENGTH_LONG).show();
//     }

      }
    }


    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),gender[position] , Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {

    }
}
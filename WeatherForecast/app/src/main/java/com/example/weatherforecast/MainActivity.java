package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public MyDBHandler database;
    public  SQLiteDatabase db_1=null;
    Toast tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database.onCreate(db_1);

    }
public void login (View v)
{
    EditText user = findViewById(R.id.editText);
    EditText password= findViewById(R.id.editText2);
    String usernameinput = user.getText().toString();
    String passwordinput = password.getText().toString();
     UserData query = database.findUSER(usernameinput);
     if(usernameinput == query.getUsername().toString() & passwordinput == query.getPassword().toString())
     {
         tt.makeText(MainActivity.this,"Valid",Toast.LENGTH_LONG);
         tt.show();
     }
     else
     {
         tt.makeText(MainActivity.this,"Invalid",Toast.LENGTH_LONG);
         tt.show();
     }



}


    public void onClick(View v)
    {

        Intent i = new Intent(this, CreateUser.class);
        startActivity(i);
    }
}

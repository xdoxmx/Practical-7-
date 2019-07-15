package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class CreateUser  extends MainActivity{
Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

    }


    public void Create(View v)
    {
        EditText text = findViewById(R.id.editText3);
        String userinput = text.getText().toString();

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{6,12}$");
        Matcher matcher = pattern.matcher(userinput);
        boolean results = matcher.matches();

        EditText password = findViewById(R.id.editText3);
        String passwordinput = password.getText().toString();

        Pattern pattern2 = Pattern.compile("^(?=.*[!@#])(?=.*[A-Z])(?=.*[0-9])[!@#A-Z0-9]{3,100}$");
        Matcher matcher2 = pattern2.matcher(passwordinput);
        boolean result = matcher2.matches();
        if (results == true & result == true)
        {
            //SharedPreferences.Editor editor = getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE);
            //editor.putstring(USER_NAME, text.getText().toString());
            //editor.putstring(PASS_WORD, password.getText().toString());
            //editor.apply();
            UserData entry = new UserData();
            entry.setUsername(text.getText().toString());
            entry.setPassword(password.getText().toString());
            database.addUser(entry);
            toast.makeText(CreateUser.this,"New User Created Successfully",Toast.LENGTH_LONG);
            toast.show();

        }
        else
        {
            toast.makeText(CreateUser.this,"Invalid User Creation. Please Try Again.",Toast.LENGTH_LONG);
            toast.show();
        }

    }
    public void onCancel(View v)
    {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}

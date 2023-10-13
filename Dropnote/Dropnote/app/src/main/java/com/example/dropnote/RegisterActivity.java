package com.example.dropnote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dropnote.Database.DatabaseHelper;
import com.example.dropnote.model.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    // Views
    private TextView login;
    private EditText editUsername, editEmail, editPassword;
    private Button register, BirthDayBtn;

    // Database Helper
    private DatabaseHelper databaseHelper;

    // Atribute
    private int Year, Month, Day;
    private String date;

    // SharedPreferences
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        InitiateView();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String birthDay = BirthDayBtn.getText().toString();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || birthDay.equals("Birth Day")){
                    Toast.makeText(RegisterActivity.this, "Fill In The Blank", Toast.LENGTH_SHORT).show();
                } else {
                    CreateAccount(username,email,password,birthDay);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        BirthDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopDatePicker(v);
            }
        });

    }

    private void InitiateView(){
        login = findViewById(R.id.login);
        login.setPaintFlags(login.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        editUsername = findViewById(R.id.username);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        BirthDayBtn = findViewById(R.id.birthdayBtn);
        register = findViewById(R.id.register_btn);

        databaseHelper = new DatabaseHelper(this);
        preferences = getSharedPreferences("Data", MODE_PRIVATE);
    }

    private void PopDatePicker(View view){
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date = dateFormat.format(calendar.getTime());
                BirthDayBtn.setText(date);
                calendar.set(Calendar.YEAR, Year);
                calendar.set(Calendar.MONTH, Month);
                calendar.set(Calendar.DAY_OF_MONTH, Day);
            }
        };
        int Style = AlertDialog.THEME_DEVICE_DEFAULT_LIGHT;
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,Style,onDateSetListener,Year,Month,Day);
        datePickerDialog.getDatePicker().setMinDate((System.currentTimeMillis())/ 1000 / 60 / 60 / 24 / 365);
        datePickerDialog.setTitle("Pick Your Birth Day");
        datePickerDialog.show();
    }

    private void CreateAccount(String name ,String email, String password, String birth){
        user user = new user(-1,name,birth,email,password);
        long id = databaseHelper.InsertUser(user);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("id", id);
        editor.apply();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
}
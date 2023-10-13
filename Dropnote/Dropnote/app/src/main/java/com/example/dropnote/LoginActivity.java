package com.example.dropnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dropnote.Database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private TextView forgotPassword, register;
    private EditText editEmail, editPassword;
    private Button loginBtn;

    // Shared Preferences
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitiateViews();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailKey = editEmail.getText().toString();
                String passwordKey = editPassword.getText().toString();
                long id;
                // SQL
                DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
                id = helper.CheckEmailAndPassword(emailKey,passwordKey);

                if (id > 0){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putLong("id", id);
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void InitiateViews(){
        forgotPassword = findViewById(R.id.forgot_password);
        register = findViewById(R.id.register);
        forgotPassword.setPaintFlags(forgotPassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        register.setPaintFlags(register.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_btn);

        preferences = getSharedPreferences("Data",MODE_PRIVATE);
    }

    @Override
    public void onBackPressed() {

    }
}
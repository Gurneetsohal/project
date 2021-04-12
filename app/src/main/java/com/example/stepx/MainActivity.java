package com.example.stepx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        mTextUsername = findViewById(R.id.username_login);
        mTextPassword = findViewById(R.id.password_login);
        mButtonLogin = findViewById(R.id.button_login);
        mRegister = findViewById(R.id.register_login);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                boolean res = db.checkUser(user , pwd);

                if(res == true){
                    Toast.makeText(MainActivity.this,"Successfully Logged In", Toast.LENGTH_SHORT).show();
                    Intent stepx = new Intent(MainActivity.this,Activity2.class);
                    stepx.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    stepx.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(stepx);
                }
                else {
                    Toast.makeText(MainActivity.this,"Login Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
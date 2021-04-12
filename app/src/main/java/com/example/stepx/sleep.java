package com.example.stepx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class sleep extends AppCompatActivity {

    TextView tv_answer;
    EditText e1,e2,e3;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        tv_answer = findViewById(R.id.tv11);
        e1 = findViewById(R.id.edd1);
        e2 = findViewById(R.id.edd2);
        e3 = findViewById(R.id.edd3);
        b1 = findViewById(R.id.bbbtn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age;
                try{
                    age = Integer.parseInt(e1.getText().toString());
                }
                catch (NumberFormatException ex)
                {
                    return;
                }


                if(age >= 0 && age <= 2)
                {
                    tv_answer.setText("14-17 Hours of Daily Sleep");
                }
                else if(age >= 3 && age <= 5)
                {
                    tv_answer.setText("10-13 Hours of Daily Sleep");
                }
                else if(age >= 6 && age <= 13)
                {
                    tv_answer.setText("9-11 Hours of Daily Sleep");
                }
                else if(age >= 14 && age <= 17)
                {
                    tv_answer.setText("8-10 Hours of Daily Sleep");
                }
                else if(age >= 18 && age <= 64)
                {
                    tv_answer.setText("7-9 Hours of Daily Sleep");
                }
                else
                {
                    tv_answer.setText("8 Hours of Daily Sleep");
                }
            }
        });
    }
}
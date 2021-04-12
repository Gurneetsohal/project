package com.example.stepx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    Button calculate, button3, button4;
    EditText weight, height;
    TextView Answer, you, advice;
    String cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        calculate = findViewById(R.id.button2);
        weight = findViewById(R.id.editTextTextPersonName);
        height = findViewById(R.id.editTextTextPersonName2);
        Answer = findViewById(R.id.textView5);
        you = findViewById(R.id.textView4);
        advice = findViewById(R.id.textView3);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diet = new Intent(Activity3.this, DietPlan.class);
                startActivity(diet);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent workout = new Intent(Activity3.this, WorkoutPlan.class);
                startActivity(workout);
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float W;
                Float H;

                try {
                    W = Float.parseFloat(weight.getText().toString());
                    H = Float.parseFloat(height.getText().toString());
                }
                catch (NumberFormatException ex)
                {
                    return;
                }

                Float newH = H/100;
                Float bmi = W/(newH * newH);

                if (bmi < 18.5)
                {
                    you.setText("You are UnderWeight!!");
                    advice.setText("Advice: Checkout Our Diet And Workout Plans!");
                }
                else if (bmi >= 18.5 && bmi < 24.9)
                {
                    you.setText("You Have a Healthy Weight!!");
                    advice.setText("Advice: Just achieve the target of 10,000 steps and you are good to go!!");
                }
                else if (bmi >= 25 && bmi < 29.9){
                    you.setText("You are OverWeight!!");
                    advice.setText("Advice: Checkout Our Diet And Workout Plans!");
                }
                else{
                    you.setText("You are Obese!!");
                    advice.setText("Advice: Checkout Our Diet And Workout Plans!");
                }
                cal = ""+bmi;
                Answer.setText(cal);
            }
        });
    }
}
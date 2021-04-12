package com.example.stepx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class water extends AppCompatActivity {

    EditText e1, e2;
    Button b1;
    TextView tv;
    DecimalFormat one = new DecimalFormat("###,###.#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        e1 = findViewById(R.id.et1);
        e2 = findViewById(R.id.et2);
        b1 = findViewById(R.id.btn10);
        tv = findViewById(R.id.tv10);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float kg;
                float duration;
                try{
                    kg = Float.parseFloat(e1.getText().toString());
                    duration = Float.parseFloat(e2.getText().toString());
                }
                catch (NumberFormatException ex)
                {
                    return;
                }
                float pound = (float) (kg * 2.205);
                float ounces = (float) (pound * 0.66);
                float workout1 = duration / 30 * 12;
                float total = ounces + workout1;
                float litres = (float) (total / 33.814);

                tv.setText(one.format(litres) + " litres/day of Water Intake");


            }
        });

    }
}
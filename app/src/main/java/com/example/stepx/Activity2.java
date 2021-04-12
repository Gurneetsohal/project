package com.example.stepx;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;


public class Activity2 extends AppCompatActivity implements SensorEventListener {

    TextView tv_steps;
    TextView tv_calories;
    TextView tv_distance;
    TextView tt1;

    SensorManager sensorManager;
    boolean running = false;

    DecimalFormat one = new DecimalFormat("###,###.#");
    DecimalFormat two = new DecimalFormat("###,###");
    DecimalFormat three = new DecimalFormat("###,###.##");
    Button button;
    Button sleep;
    Button water;
    float totalSteps = 0f;
    float previousTotalSteps = 0f;
    String a;
    int currentSteps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        sleep = findViewById(R.id.button5);
        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sleep = new Intent(Activity2.this, sleep.class);
                startActivity(sleep);
            }
        });
        water = findViewById(R.id.button6);
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent water = new Intent(Activity2.this, water.class);
                startActivity(water);
            }
        });

        tv_steps = findViewById(R.id.TV_STEPS);
        tv_calories = findViewById(R.id.TV_CALORIES);
        tv_distance = findViewById(R.id.TV_DISTANCE);
        tt1 = findViewById(R.id.tt1);
        loadData();
        resetSteps();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }
    public void openActivity2(){
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor Not Found!!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
            if(event != null)
            {
                totalSteps = event.values[0];
                currentSteps = (int) totalSteps - (int) previousTotalSteps;
                tv_steps.setText(two.format(currentSteps));
            }
            double cal = currentSteps * 0.045;
            tv_calories.setText(one.format(cal));
            double feet = currentSteps * 2.5;
            double dis = feet/3.281;
            tv_distance.setText(three.format(dis));
        }

    }

    public void resetSteps() {
            tt1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    previousTotalSteps = totalSteps;
                    int b = 0;
                    a = "" + b;
                    tv_steps.setText(a);
                    tv_calories.setText(a);
                    tv_distance.setText(a);
                    saveData();
                    return true;
                }
            });
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("Key1", previousTotalSteps);
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        float savedNumber = sharedPreferences.getFloat("Key1", 0f);
        Log.d("Activity2", String.valueOf(savedNumber));
        previousTotalSteps = savedNumber;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
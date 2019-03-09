package com.example.android.allin.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.allin.welcomePage.Bmi;
import com.example.android.allin.welcomePage.Calculator;
import com.example.android.allin.welcomePage.OtherImpl;
import com.example.android.allin.R;

public class MainActivityImpl extends AppCompatActivity implements MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_MainActivity);
    }

    public void openBmi(View view) {
        Intent intent = new Intent(this, Bmi.class);
        startActivity(intent);
    }

    public void openCalculator(View view) {
        Intent intent = new Intent(this, Calculator.class);
        startActivity(intent);
    }

    public void openOther(View view) {
        Intent intent = new Intent(this, OtherImpl.class);
        startActivity(intent);
    }
}

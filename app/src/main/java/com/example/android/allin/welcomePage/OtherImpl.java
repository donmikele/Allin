package com.example.android.allin.welcomePage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.allin.R;
import com.example.android.allin.welcomePage.other.otherPage.CalculateIp;
import com.example.android.allin.welcomePage.other.otherPage.Notepad;
import com.example.android.allin.welcomePage.other.otherPage.YourNotes;

public class OtherImpl extends AppCompatActivity implements Other {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_Other);
    }

    public void openNotepad(View view) {
        Intent intent = new Intent(this, Notepad.class);
        startActivity(intent);
    }

    public void openYourNotes(View view) {
        Intent intent = new Intent(this,YourNotes.class);
        startActivity(intent);
    }

    public void openCalculateIp(View view) {
        Intent intent = new Intent(this,CalculateIp.class);
        startActivity(intent);
    }
}

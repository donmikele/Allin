package com.example.android.allin.welcomePage.other.otherPage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.allin.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Notepad extends AppCompatActivity {

    private EditText mEnterEditTextNotepad;
    private Bundle bundle = new Bundle();
    private String path = Environment.getExternalStorageDirectory().toString() + "/Allin/Notepad";
    private final int MEMORY_ACCESS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_Notepad);

        mEnterEditTextNotepad = findViewById(R.id.enterEditText_other_notepad);
        mEnterEditTextNotepad.setText(bundle.getString("mEnterEditText_other_notepad"));

        if(ActivityCompat.shouldShowRequestPermissionRationale(Notepad.this, Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
        }else{
            ActivityCompat.requestPermissions(Notepad.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MEMORY_ACCESS);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MEMORY_ACCESS:
                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                    //permission granted
                }else{
                    Toast.makeText(getApplicationContext(), "Jesli nie zostanie wyrazona zgoda na dostep do pamieci, nie bedzie mozliwosci zapisania" +
                            "pliku", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPause() {
        bundle.putString("mEnterEditText_other_notepad", mEnterEditTextNotepad.getText().toString());
        super.onPause();
    }

    public void createDir() {
       final File folder = new File(path);
        if (!folder.exists()) {
            try {
                folder.mkdirs();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), (CharSequence) e, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void createFile() {
        if(mEnterEditTextNotepad.length() >= 20) {
            File file = new File(path + "/" + mEnterEditTextNotepad.getText().toString().substring(0, 20) + ".txt");
            FileOutputStream fout;
            OutputStreamWriter myOutWriter;
            try {
                fout = new FileOutputStream(file);
                myOutWriter = new OutputStreamWriter(fout);
                myOutWriter.append(mEnterEditTextNotepad.getText());
                myOutWriter.close();
                fout.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            File file = new File(path + "/" + mEnterEditTextNotepad.getText().toString() + ".txt");
            FileOutputStream fout;
            OutputStreamWriter myOutWriter;
            try {
                fout = new FileOutputStream(file);
                myOutWriter = new OutputStreamWriter(fout);
                myOutWriter.append(mEnterEditTextNotepad.getText());
                myOutWriter.close();
                fout.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void launchSaveNote(View view) {
        createDir();
        createFile();
        finish();
    }
}

package com.example.android.allin.welcomePage.other.otherPage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.allin.R;

import java.util.regex.Pattern;

public class CalculateIp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText mEnterIPEditText;
    private TextView mAddressIPTextView;
    private TextView mMaskTextView;
    private TextView mNetworkAddressTextView;
    private TextView mBroadcastTextView;
    private TextView mNumbOfHostsTextView;
    private TextView mHostMinTextView;
    private TextView mHostMaxTextView;

    private static final String TAG = CalculateIp.class.getSimpleName();
    private String mSpinnerLabel = "";
    private static final Pattern PATTERN = Pattern.compile(
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_CalculateIp);

        mEnterIPEditText = findViewById(R.id.enterIPEditText_other_calcip);
        mAddressIPTextView = findViewById(R.id.addressipSHOW_other_calcip);
        mMaskTextView = findViewById(R.id.maskSHOW_other_calcip);
        mNetworkAddressTextView = findViewById(R.id.networkaddressSHOW_other_calcip);
        mBroadcastTextView = findViewById(R.id.broadcastSHOW_other_calcip);
        mNumbOfHostsTextView = findViewById(R.id.numbofhostsSHOW_other_calcip);
        mHostMinTextView = findViewById(R.id.hostminSHOW_other_calcip);
        mHostMaxTextView = findViewById(R.id.hostmaxSHOW_other_calcip);

        //Spinner
        Spinner spinner = findViewById(R.id.calcip_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.calcip_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }

    public void calculateIP(View view) {

        String net = mEnterIPEditText.getText().toString();
        String mask = mSpinnerLabel.substring(0, 2);
        String substring_mSpinnerLabel = mSpinnerLabel.substring(5, mSpinnerLabel.length());
        String subnet = net + mask;

        if (mEnterIPEditText != null) {
            if (validate(net)) {
                mAddressIPTextView.setText(net);
                //Network Address
                if ((Integer.valueOf(mask.substring(1, 2)) >= 0) && (Integer.valueOf(mask.substring(1, 2)) <= 8)) {
                    //TODO
                }
            } else {
                mAddressIPTextView.setText("Invalid entered address IP!");
            }
            mMaskTextView.setText(substring_mSpinnerLabel);
        }
        //Hide number keyboard after click CALCULATE button
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mSpinnerLabel = parent.getItemAtPosition(position).toString();
        if (mEnterIPEditText != null) {
            calculateIP(view);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d(TAG, "elo");
    }

    public static boolean validate(final String ip) {
        return PATTERN.matcher(ip).matches();
    }


}

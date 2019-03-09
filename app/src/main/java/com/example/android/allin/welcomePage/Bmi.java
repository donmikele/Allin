package com.example.android.allin.welcomePage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.allin.R;
import com.example.android.allin.main.MainActivityImpl;

import static android.graphics.Color.*;

public class Bmi extends AppCompatActivity {

    private static final String LOG_TAG = MainActivityImpl.class.getSimpleName();

    EditText mHeightEditTextBMI;
    EditText mWeightEditTextBMI;
    TextView mShowBmiTextViewBMI;
    TextView mShowResultTextViewBMI;
    Button mButtonBMI;

    /* Checking current texts and color */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mShowResultTextViewBMI.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", mShowBmiTextViewBMI.getText().toString());
            outState.putString("result_text", mShowResultTextViewBMI.getText().toString());
            outState.putString("result_color", String.valueOf(mShowBmiTextViewBMI.getCurrentTextColor()));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_Bmi);

        mHeightEditTextBMI = findViewById(R.id.heightEdit_bmi);
        mWeightEditTextBMI = findViewById(R.id.weightEnter_bmi);
        mShowBmiTextViewBMI = findViewById(R.id.showbmiText_bmi);
        mShowResultTextViewBMI = findViewById(R.id.showResult_bmi);
        mButtonBMI = findViewById(R.id.button_bmi);

        /* Checking if screen is rotated */
        if (savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if (isVisible) {
                //GetString
                mShowBmiTextViewBMI.setText(savedInstanceState.getString("reply_text"));
                mShowResultTextViewBMI.setText(savedInstanceState.getString("result_text"));

                //StringsColors
                mShowBmiTextViewBMI.setTextColor(Integer.parseInt(savedInstanceState.getString("result_color")));
                mShowResultTextViewBMI.setTextColor(Integer.parseInt(savedInstanceState.getString("result_color")));

                //SetVisible
                mShowResultTextViewBMI.setVisibility(View.VISIBLE);
                mShowBmiTextViewBMI.setVisibility(View.VISIBLE);
            }
        }
        //Automatically calling onClick (XML)button_bmi when 'OK' is clicked in weight's keyboard
        mWeightEditTextBMI.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    mButtonBMI.callOnClick();
                }
                return false;
            }
        });
    }

    @SuppressLint("DefaultLocale")
    public void calculateBMI(View view) {
        double height;
        double weight;
        double check;
        String say;

        try {
            height = getOperand(mHeightEditTextBMI);
            weight = getOperand(mWeightEditTextBMI);
        } catch (NumberFormatException nfe) {
            mShowBmiTextViewBMI.setText("");
            mShowResultTextViewBMI.setText(getString(R.string.computationError));
            mShowResultTextViewBMI.setTextColor(RED);
            return;
        }

        //Checking if height is in meters
        if (mHeightEditTextBMI.getText().toString().contains(".")) {
            double calc = weight / (Math.pow(height, 2));
            String calc1 = String.format("%.2f", calc);

            mShowBmiTextViewBMI.setText(calc1);
            check = Math.round(weight / (Math.pow(height, 2)));

            if (check < 18.5) {
                say = "- Underweight";
                mShowBmiTextViewBMI.setTextColor(RED);
                mShowResultTextViewBMI.setText(say);
                mShowResultTextViewBMI.setTextColor(RED);
            } else if (check >= 18.5 && check <= 24.9) {

                say = "- Good";
                mShowBmiTextViewBMI.setTextColor(GREEN);
                mShowResultTextViewBMI.setText(say);
                mShowResultTextViewBMI.setTextColor(GREEN);
            } else if (check >= 25 && check <= 29.9) {
                say = "- Overweight";
                mShowBmiTextViewBMI.setTextColor(RED);
                mShowResultTextViewBMI.setText(say);
                mShowResultTextViewBMI.setTextColor(RED);
            } else if (check >= 30) {
                say = "- Obesity";
                mShowBmiTextViewBMI.setTextColor(RED);
                mShowResultTextViewBMI.setText(say);
                mShowResultTextViewBMI.setTextColor(RED);
            }
        } else {
            say = "You entered wrong height! It must be in meters!";
            mShowBmiTextViewBMI.setText("");
            mShowResultTextViewBMI.setText(say);
            mShowResultTextViewBMI.setTextColor(RED);

        }
        //Hiding numbers keyboard after clicking CALCULATE button
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }
    //Returns operand as Double
    private static double getOperand(EditText operandEditText) {
        String operandText = getOperandText(operandEditText);
        return Double.valueOf(operandText);
    }

    //Returns operand as String
    private static String getOperandText(EditText operandEditText) {
        return operandEditText.getText().toString();
    }
}

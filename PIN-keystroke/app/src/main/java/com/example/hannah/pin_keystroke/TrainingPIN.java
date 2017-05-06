package com.example.hannah.pin_keystroke;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hannah on 4/28/17.
 */

class TrainingPIN extends AppCompatActivity {
    int count = 1;
    String correct;
    char current;
    long start;
    long down;
    boolean isfull = false;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        correct = getIntent().getStringExtra("correct");

        //create the layout
        setContentView(R.layout.training);
        setTitle(correct);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        final EditText digit = (EditText) findViewById(R.id.digit);

        final EditText counter = (EditText) findViewById(R.id.count);
        counter.setText(String.valueOf(count));

        //start in first digit
        digit.requestFocus();
        start = System.nanoTime();

        digit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                down = System.nanoTime();

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!digit.getText().toString().isEmpty()) {
                    Log.d("KeyDown: " + digit.getText().toString().charAt(digit.getText().length() - 1), String.valueOf(start - down));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (digit.getText().length() >= 4){
                    isfull = true;
                }
            }
        });

        digit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (!digit.getText().toString().isEmpty()) {
                    current = digit.getText().toString().charAt(digit.getText().length() - 1);
                }

                // on enter
                if (keyCode == 66) {
                    // clear
                    if (digit.getText().toString().equalsIgnoreCase(correct)) {
                        count--;
                        counter.setText(String.valueOf(count));

                        if (count == 0) {
                            Intent intent = new Intent(v.getContext(), Thanks.class);
                            startActivity(intent);
                        }
                        Log.d ("TotalTime: ", String.valueOf(start - System.nanoTime()));
                    }
                    else {
                        digit.getText().clear();
                        isfull = false;
                        check = false;
                    }

                }
                return false;
            }
        });

    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!check) {
            Log.d("KeyUp: " + current, String.valueOf(down - System.nanoTime()));

            if (isfull){
                check = true;
            }
        }

        return true;
    }



}

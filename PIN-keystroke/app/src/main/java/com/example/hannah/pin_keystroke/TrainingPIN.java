package com.example.hannah.pin_keystroke;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by hannah on 4/28/17.
 */

public class TrainingPIN extends AppCompatActivity {
    int count = 5;
    String correct, username;
    char current;
    long start;
    long down, up, totaltime;
    boolean isfull = false;
    boolean check = false;
    JSONArray Entry = new JSONArray();
    JSONArray TrainingSet = new JSONArray();
    JSONObject finalset = new JSONObject();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        correct = getIntent().getStringExtra("correct");
        username = getIntent().getStringExtra("userID");

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
             /*   if (!digit.getText().toString().isEmpty()) {
                    Log.d("KeyDown: " + digit.getText().toString().charAt(digit.getText().length() - 1), String.valueOf(start - down));
                }*/
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
                    current = (char) keyCode;
                }

                // on enter
                if (keyCode == 66) {
                    // clear
                    if (digit.getText().toString().equalsIgnoreCase(correct)) {
                        count--;
                        counter.setText(String.valueOf(count));


                        JSONObject overAllMetadata = new JSONObject();
                        try {

                            overAllMetadata.put("Total Time", (System.nanoTime() - start));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Entry.put(overAllMetadata);
                        TrainingSet.put(Entry);
                        if (count == 0) {
                            try {
                                finalset.putOpt(username,TrainingSet);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //write to file
                            try {
                                filewriter(finalset.toString(4));
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(v.getContext(), Thanks.class);
                            startActivity(intent);

                        }
                        Entry = null;
                        Entry = new JSONArray();
                        //Log.d ("TotalTime: ", String.valueOf(start - System.nanoTime()));
                    }

                    digit.getText().clear();
                    Entry = null;
                    Entry = new JSONArray();
                    isfull = false;
                    check = false;


                }
                return false;
            }
        });

    }

    public void filewriter(String data) throws IOException {
        File out = new File (Environment.getExternalStorageDirectory(), correct+"-output.txt");
        if (!out.exists()){
            out.createNewFile();
        }
        FileOutputStream fout = new FileOutputStream(out, true);
        fout.write(data.getBytes());
        fout.close();

        }

    public JSONObject createMetadata(){
        JSONObject temp = new JSONObject();
        try {
            temp.put("Digit", current-7);
            temp.put("Key Down", down-start);
            temp.put("Key Up", up-down);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return temp;
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!check) {
            //Log.d("KeyUp: " + current, String.valueOf(down - System.nanoTime()));
            up = System.nanoTime();
            if (keyCode != 66) {
                Entry.put(createMetadata());
            }
            if (isfull){
                check = true;
            }
        }

        return true;
    }



}

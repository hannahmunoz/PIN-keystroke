package com.example.hannah.pin_keystroke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import java.io.IOException;

/**
 * Created by hannah on 5/6/17.
 */

public class Testing extends AppCompatActivity {

    static {
        System.loadLibrary("tensorflow_inference");
    }


    private TensorFlowInferenceInterface inferenceInterface;
    private  static final String MODEL_FILE = "file:///android_asset/test.pb";
    private  static final  String INPUT_NODE = "I";
    private static final  String OUTPUT_NODE = "O";

    long start, down, up;
    boolean isfull, check;
    char current;
    JSONArray AllAttempts = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create the layout
        setContentView(R.layout.testing);
        setTitle("0738");
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        final EditText digit = (EditText) findViewById(R.id.digit);
        inferenceInterface = new TensorFlowInferenceInterface(getAssets(), MODEL_FILE);


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
                    if (digit.getText().toString().equalsIgnoreCase("0738")) {
                         float[] inputs;

                            long totalTime = System.nanoTime() - start;

                           inputs = getFloats ( totalTime);

                           //inferenceInterface.feed(null, inputs, 1, inputs.length);
                           //inferenceInterface.run(new String[] {OUTPUT_NODE});
                           //float[] results = {0,0};
                           //inferenceInterface.fetch(OUTPUT_NODE, results);


                            Intent intent = new Intent(v.getContext(), Thanks.class);
                            startActivity(intent);

                        }

                    digit.getText().clear();
                    isfull = false;
                    check = false;

                }
                return false;
            }
        });



    }

    private float[] getFloats( long totalTime) {
        try {
            float [] temp = {AllAttempts.getJSONObject(0).getLong("Fight"), AllAttempts.getJSONObject(0).getLong("Dwell"),
                             AllAttempts.getJSONObject(1).getLong("Fight"), AllAttempts.getJSONObject(1).getLong("Dwell"),
                             AllAttempts.getJSONObject(2).getLong("Fight"), AllAttempts.getJSONObject(2).getLong("Dwell"),
                             AllAttempts.getJSONObject(3).getLong("Fight"), AllAttempts.getJSONObject(3).getLong("Dwell"),
                             totalTime};


            return temp;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONObject createKey(){
        JSONObject temp = new JSONObject();
        try {
            temp.put("Digit", current-7);
            temp.put("Fight", down-start);
            temp.put("Dwell", up-down);
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
            if (keyCode != 66 && keyCode != 67) {
                AllAttempts.put(createKey());
            }
            if (keyCode == 67){
                AllAttempts.remove(AllAttempts.length()-1);
            }
            if (isfull){
                check = true;
            }
        }

        return true;
    }


}

package com.example.hannah.pin_keystroke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

/**
 * Created by hannah on 4/28/17.
 */

public class TrainingPIN extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create the layout
        setContentView(R.layout.training);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }
}

package com.example.hannah.pin_keystroke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hannah on 5/3/17.
 */

public class PinChoice extends AppCompatActivity implements View.OnClickListener{
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = getIntent().getStringExtra("userID");


        //create the layout
        setContentView(R.layout.pin_choice);

        Button set1 = (Button) findViewById(R.id.set1);
        set1.setOnClickListener(this);

        Button set2 = (Button) findViewById(R.id.set2);
        set2.setOnClickListener(this);

        Button set3 = (Button) findViewById(R.id.set3);
        set3.setOnClickListener(this);

        Button set4 = (Button) findViewById(R.id.set4);
        set4.setOnClickListener(this);

        Button set5 = (Button) findViewById(R.id.set5);
        set5.setOnClickListener(this);

        Button set6 = (Button) findViewById(R.id.set6);
        set6.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), TrainingPIN.class);
        switch (v.getId()){
            case (R.id.set1):
                //go to training main
                intent.putExtra("correct", "1234");
                break;
            case (R.id.set2):
                intent.putExtra("correct", "1122");
                break;
            case (R.id.set3):
                intent.putExtra("correct", "1312");
                break;
            case (R.id.set4):
                intent.putExtra("correct", "8557");
                break;
            case (R.id.set5):
                intent.putExtra("correct", "7394");
                break;
            case (R.id.set6):
                intent.putExtra("correct", "0738");
                break;
        }
        intent.putExtra("userID", username);
        startActivity(intent);

    }
}

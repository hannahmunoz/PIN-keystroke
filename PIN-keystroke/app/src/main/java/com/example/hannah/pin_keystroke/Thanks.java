package com.example.hannah.pin_keystroke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hannah on 5/3/17.
 */

public class Thanks extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create the layout
        setContentView(R.layout.thank_you);
        //back button
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(v.getContext(), MainActivity.class);
        startActivity(intent);
    }
}

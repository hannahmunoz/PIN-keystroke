package com.example.hannah.pin_keystroke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hannah on 4/24/17.
 */

public class TrainingMain extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create the layout
        setContentView(R.layout.training_main);
        //newuser button
        Button newUser = (Button) findViewById(R.id.newUser);
        newUser.setOnClickListener(this);
        //returninguser button
        Button returningUser = (Button) findViewById(R.id.returningUser);
        returningUser.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.newUser):
                //go to training main
                Intent intent = new Intent(v.getContext(), UserRegistration.class);
                startActivity(intent);
                break;
            case (R.id.returningUser):
                break;
        }
    }
}

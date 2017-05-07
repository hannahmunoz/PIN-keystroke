package com.example.hannah.pin_keystroke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hannah on 5/6/17.
 */

public class UserRegistration extends AppCompatActivity implements View.OnClickListener {
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create the layout
        setContentView(R.layout.new_user);
        //newuser button
        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);

        username = (EditText) findViewById(R.id.userID);
    }

    @Override
    public void onClick(View v) {
        if (username.getText().length() > 1) {
            Intent intent = new Intent(v.getContext(), PinChoice.class);
            intent.putExtra("userID", username.getText().toString());
            startActivity(intent);
        }

    }
}

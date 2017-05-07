package com.example.hannah.pin_keystroke;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create the layout
        setContentView(R.layout.activity_main);
        //training button
        Button training = (Button) findViewById(R.id.training);
        training.setOnClickListener(this);
        //testing button
        Button testing = (Button) findViewById(R.id.testing);
        testing.setOnClickListener(this);
        
        getPermissions();

    // Example of a call to a native method
    //TextView tv = (TextView) findViewById(R.id.sample_text);
    //tv.setText(stringFromJNI());
    }

    private void getPermissions() {
        //file permission
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != 0) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

    }

    @Override
    public void onClick(View v) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == 0) {
            Intent intent = null;
            switch (v.getId()) {
                case (R.id.training):
                    //go to training main
                    intent = new Intent(MainActivity.this, UserRegistration.class);
                    break;
                case (R.id.testing):
                    intent = new Intent(MainActivity.this, Testing.class);
                    break;
            }
            startActivity(intent);

        }
        else{
            getPermissions();
        }

    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
   // public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    /*static {
        System.loadLibrary("native-lib");
    }*/
}

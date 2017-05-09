package com.example.hannah.pin_keystroke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

/**
 * Created by hannah on 5/6/17.
 */

public class Testing extends AppCompatActivity {

    static {
        System.loadLibrary("tensorflow_inference");
    }


    private TensorFlowInferenceInterface inferenceInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create the layout
        setContentView(R.layout.testing);

        //inferenceInterface = new TensorFlowInferenceInterface();

    }

}

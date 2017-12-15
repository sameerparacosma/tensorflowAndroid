package com.example.vrlab.tensorflowtutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    static {
        System.loadLibrary("tensorflow_inference");
    }
    private static final String Model_file="file:///android_asset/optimized_tfmodel.pb";
    private static  final String Input_node="I";
    private static final String Output_node="o";
    private static final int[] Input_size={1,3};
    private String[] outputNames;
    private TensorFlowInferenceInterface inteface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inteface= new TensorFlowInferenceInterface(getAssets(),Model_file);
        float[] input={0,0,0};
        inteface.feed(Input_node,input,1,3);


        inteface.run(new String[] {Output_node},true);
        float[] result={0,0};

        inteface.fetch(Output_node,result);
        float res= result[0];
        Toast toast = Toast.makeText(this, Arrays.toString(result), Toast.LENGTH_LONG);
        toast.show();


    }
}

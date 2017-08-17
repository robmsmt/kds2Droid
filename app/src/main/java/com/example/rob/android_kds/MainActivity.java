package com.example.rob.android_kds;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import java.io.File;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream csvStream = getResources().openRawResource(R.raw.test_mfcc_0);

                // this gives you a 2-dimensional array of strings
                List<List<String>> lines = new ArrayList<>();
                Scanner inputStream;

                try {
                    inputStream = new Scanner(csvStream);
                    while (inputStream.hasNext()) {
                        String line = inputStream.next();
                        String[] values = line.split(",");
                        // this adds the currently parsed line to the 2-dimensional string array
                        lines.add(Arrays.asList(values));
                    }
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // the following code lets you iterate through the 2-dimensional array
                int lineNo = 1;
                for (List<String> line : lines) {
                    int columnNo = 1;
                    for (String value : line) {
                        System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
                        columnNo++;
                    }
                    lineNo++;
                }

                System.out.println(lines);


//                /** One time initialization: */
//                TensorFlowInferenceInterface tensorflow = new TensorFlowInferenceInterface();
//                tensorflow.initializeTensorFlow(getAssets(), "file:///android_asset/constant_graph_weights.pb");
//
//                /** Continuous inference (floats used in example, can be any primitive): */
//                //#the_input
//                //#output_node0
//
//                double[][][] arr=new double[1][234][26];
//
//                for(int i=0;i<234;i++){
//                    for(int j=0;j<26;j++){
//                        arr[0][i][j] = 1.0;
//                    }
//
//                }
//
//
//
//                System.out.println(arr);


                // loading new input
//                tensorflow.fillNodeFloat("the_input", INPUT_SHAPE, input); // INPUT_SHAPE is an int[] of expected shape, input is a float[] with the input data
//
//                // running inference for given input and reading output
//                String outputNode = "output_node0";
//                String[] outputNodes = {outputNode};
//                tensorflow.runInference(outputNodes);
//                tensorflow.readNodeFloat(outputNode, output); // output is a preallocated float[] in the size of the expected output vector


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


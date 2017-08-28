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
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import android.os.Trace;

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
//                int lineNo1 = 1;
//                for (List<String> line : lines) {
//                    int columnNo = 1;
//                    for (String value : line) {
//                        System.out.println("Line " + lineNo1 + " Column " + columnNo + ": " + value);
//                        columnNo++;
//                    }
//                    lineNo1++;
//                }

//                System.out.println(lines);


//                /** One time initialization: */
                TensorFlowInferenceInterface tensorflow = new TensorFlowInferenceInterface();
                tensorflow.initializeTensorFlow(getAssets(), "file:///android_asset/constant_graph_weights.pb");
//
//                /** Continuous inference (floats used in example, can be any primitive): */
//                //#the_input
//                //#output_node0
//


//                for(int i=0;i<234;i++){
//                    for(int j=0;j<26;j++){
//
//                    }
//
//                }

                float[] arr=new float[18252];


                // the following code lets you iterate through the 2-dimensional array
                int lineNo = 0;
                int index = 0;
                for (List<String> line : lines) {
                    int columnNo = 0;
                    for (String value : line) {

                        arr[index] = Float.parseFloat(value);
                        columnNo++;
                        index++;
                    }
                    lineNo++;
                }
                lineNo = 0;
                index = 0;
                for (List<String> line : lines) {
                    int columnNo = 0;
                    for (String value : line) {

                        arr[index] = Float.parseFloat(value);
                        columnNo++;
                        index++;
                    }
                    lineNo++;
                }
                lineNo = 0;
                index = 0;
                for (List<String> line : lines) {
                    int columnNo = 0;
                    for (String value : line) {

                        arr[index] = Float.parseFloat(value);
                        columnNo++;
                        index++;
                    }
                    lineNo++;
                }

//                System.out.println(Arrays.deepToString(arr));
//                System.out.println(arr[0][0].length); //arr[0].length=234, arr[0][0]=26


//                int[] INPUT_SHAPE = new int[3];
//                INPUT_SHAPE[0] = 1;
//                INPUT_SHAPE[1] = 234;
//                INPUT_SHAPE[2] = 26;
//

                for(int i=0;i<arr.length;i++){
                    System.out.println(arr[i]);
                    System.out.println(i);

                }

                // Copy the input data into TensorFlow.
                System.out.println("inputNode");
                Trace.beginSection("fillNodeFloat");
                //input is 3x234x26
                tensorflow.fillNodeFloat(
                        "the_input", new int[]{3 * 234 * 26}, arr);
                Trace.endSection();

//                // Run the inference call.
                System.out.println("runInference");
                Trace.beginSection("runInference");
                String outputNode = "output_node0";
                String[] outputNodes = {outputNode};
                tensorflow.runInference(outputNodes);
                Trace.endSection();
//
                // Copy the output Tensor back into the output array.
                System.out.println("readOutput");
                Trace.beginSection("readNodeFloat");
//                tensorflow.fillNodeFloat(
//                        "output_node0", new int[]{3 * 234 * 29}, output);
//                float[][][] output=new float[3][234][29];
                //output should be batchxtimex29 (3 x 234 x 29) = 20358 flattened array
                float[] output=new float[20358];

                tensorflow.readNodeFloat(outputNode, output); // ERROR HERE
                Trace.endSection();


//                tensorflow.fillNodeFloat("the_input", INPUT_SHAPE, arr); // INPUT_SHAPE is an int[] of expected shape, input is a float[] with the input data
//
//                // running inference for given input and reading output






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

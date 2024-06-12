package com.example.mathlytics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Interpolation extends AppCompatActivity {

    EditText numDataPointsInput1, xValuesInput1, yValuesInput1, interpolationPointInput1;
    Button calcButton;
    TextView resultTextView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolation);

        numDataPointsInput1 = findViewById(R.id.num_data_points_input);
        xValuesInput1 = findViewById(R.id.x_values_input);
        yValuesInput1 = findViewById(R.id.y_values_input);
        interpolationPointInput1 = findViewById(R.id.interpolation_point_input);
        resultTextView1 = findViewById(R.id.result_text_view);
        calcButton = findViewById(R.id.calculate_button);

        Button heading = findViewById(R.id.headingInterpolationEqual);
        Intent intent = getIntent();
        if(intent.getStringExtra("Method_name").contains("Forward Interpolation")){
            heading.setText("Forward Interpolation");
        }
        else if(intent.getStringExtra("Method_name").contains("Backward Interpolation")){
            heading.setText("Backward Interpolation");
        }
        else if(intent.getStringExtra("Method_name").contains("Divided Difference")){
            heading.setText("Newton's Divided Difference");
        }
        else if(intent.getStringExtra("Method_name").contains("Lagrange")){
            heading.setText("Lagrange's Formulae");
        }


        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intent.getStringExtra("Method_name").contains("Forward Interpolation")){
                    calculateForwardInterpolation();
                }
                else if(intent.getStringExtra("Method_name").contains("Backward Interpolation")){
                    //method call
                }
                else if(intent.getStringExtra("Method_name").contains("Divided Difference")){
                   calculateDividedDifference();
                }
                else if(intent.getStringExtra("Method_name").contains("Lagrange")){
                    calculateLagrange();
                }

            }
        });
    }


    public void calculateForwardInterpolation() {
        // Get the input values
        try {
            // Parse input values
            int numDataPoints = Integer.parseInt(numDataPointsInput1.getText().toString());
            String[] xValuesArray = xValuesInput1.getText().toString().split(",");
            String[] yValuesArray = yValuesInput1.getText().toString().split(",");
            double interpolationPoint = Double.parseDouble(interpolationPointInput1.getText().toString());

            // Check if input data is valid
            if (xValuesArray.length != numDataPoints || yValuesArray.length != numDataPoints) {
                throw new IllegalArgumentException("Number of x-values and y-values must match the number of data points");
            }

            // Convert input values into arrays of doubles
            double[] xValues = new double[numDataPoints];
            double[] yValues = new double[numDataPoints];
            for (int i = 0; i < numDataPoints; i++) {
                xValues[i] = Double.parseDouble(xValuesArray[i]);
                yValues[i] = Double.parseDouble(yValuesArray[i]);
            }

            // Calculate coefficients of divided differences
            double[][] dividedDifferences = new double[numDataPoints][numDataPoints];
            for (int i = 0; i < numDataPoints; i++) {
                dividedDifferences[i][0] = yValues[i];
            }
            for (int j = 1; j < numDataPoints; j++) {
                for (int i = 0; i < numDataPoints - j; i++) {
                    dividedDifferences[i][j] = (dividedDifferences[i+1][j-1] - dividedDifferences[i][j-1]) / (xValues[i+j] - xValues[i]);
                }
            }

            // Perform forward interpolation using Newton's formula
            double result = dividedDifferences[0][0];
            double term = 1;
            for (int i = 1; i < numDataPoints; i++) {
                term *= (interpolationPoint - xValues[i-1]);
                result += dividedDifferences[0][i] * term;
            }

            // Display result
            resultTextView1.setText(String.format("%.6f", result));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid input", Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void calculateLagrange() {
        // Get the input values
        try {
            // Parse input values
            int numDataPoints = Integer.parseInt(numDataPointsInput1.getText().toString());
            String[] xValuesArray = xValuesInput1.getText().toString().split(",");
            String[] yValuesArray = yValuesInput1.getText().toString().split(",");
            double interpolationPoint = Double.parseDouble(interpolationPointInput1.getText().toString());

            // Check if input data is valid
            if (xValuesArray.length != numDataPoints || yValuesArray.length != numDataPoints) {
                throw new IllegalArgumentException("Number of x-values and y-values must match the number of data points");
            }

            // Convert input values into arrays of doubles
            double[] xValues = new double[numDataPoints];
            double[] yValues = new double[numDataPoints];
            for (int i = 0; i < numDataPoints; i++) {
                xValues[i] = Double.parseDouble(xValuesArray[i]);
                yValues[i] = Double.parseDouble(yValuesArray[i]);
            }

            // Calculate coefficients of divided differences
            double[][] dividedDifferences = new double[numDataPoints][numDataPoints];
            for (int i = 0; i < numDataPoints; i++) {
                dividedDifferences[i][0] = yValues[i];
            }
            for (int j = 1; j < numDataPoints; j++) {
                for (int i = 0; i < numDataPoints - j; i++) {
                    dividedDifferences[i][j] = (dividedDifferences[i+1][j-1] - dividedDifferences[i][j-1]) / (xValues[i+j] - xValues[i]);
                }
            }

            // Perform forward interpolation using Newton's formula
            double result = dividedDifferences[0][0];
            double term = 1;
            for (int i = 1; i < numDataPoints; i++) {
                term *= (interpolationPoint - xValues[i-1]);
                result += dividedDifferences[0][i] * term;
            }

            // Display result
            resultTextView1.setText(String.format("%.6f", result));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid input", Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void calculateDividedDifference() {
        // Get the input values
        try {
            // Parse input values
            int numDataPoints = Integer.parseInt(numDataPointsInput1.getText().toString());
            String[] xValuesArray = xValuesInput1.getText().toString().split(",");
            String[] yValuesArray = yValuesInput1.getText().toString().split(",");
            double interpolationPoint = Double.parseDouble(interpolationPointInput1.getText().toString());

            // Check if input data is valid
            if (xValuesArray.length != numDataPoints || yValuesArray.length != numDataPoints) {
                throw new IllegalArgumentException("Number of x-values and y-values must match the number of data points");
            }

            // Convert input values into arrays of doubles
            double[] xValues = new double[numDataPoints];
            double[] yValues = new double[numDataPoints];
            for (int i = 0; i < numDataPoints; i++) {
                xValues[i] = Double.parseDouble(xValuesArray[i]);
                yValues[i] = Double.parseDouble(yValuesArray[i]);
            }

            // Calculate coefficients of divided differences
            double[][] dividedDifferences = new double[numDataPoints][numDataPoints];
            for (int i = 0; i < numDataPoints; i++) {
                dividedDifferences[i][0] = yValues[i];
            }
            for (int j = 1; j < numDataPoints; j++) {
                for (int i = 0; i < numDataPoints - j; i++) {
                    dividedDifferences[i][j] = (dividedDifferences[i+1][j-1] - dividedDifferences[i][j-1]) / (xValues[i+j] - xValues[i]);
                }
            }

            // Perform forward interpolation using Newton's formula
            double result = dividedDifferences[0][0];
            double term = 1;
            for (int i = 1; i < numDataPoints; i++) {
                term *= (interpolationPoint - xValues[i-1]);
                result += dividedDifferences[0][i] * term;
            }

            // Display result
            resultTextView1.setText(String.format("%.6f", result));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid input", Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
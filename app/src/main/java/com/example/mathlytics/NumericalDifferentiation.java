package com.example.mathlytics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

public class NumericalDifferentiation extends AppCompatActivity {


    private EditText xValuesInput;
    private EditText yValuesInput;
    private Spinner methodSpinner;
    private EditText stepSizeInput;
    private TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical_differentiation);

        // Get references to UI elements
        xValuesInput = findViewById(R.id.x_values_input);
        yValuesInput = findViewById(R.id.y_values_input);
        methodSpinner = findViewById(R.id.method_spinner);
        stepSizeInput = findViewById(R.id.step_size_input);
        resultTextView = findViewById(R.id.result_text_view);

        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get input values
                String xValuesString = xValuesInput.getText().toString();
                String yValuesString = yValuesInput.getText().toString();
                String method = methodSpinner.getSelectedItem().toString();
                double stepSize = Double.parseDouble(stepSizeInput.getText().toString());

                // Convert comma-separated input strings to arrays of doubles
                double[] xValues = Arrays.stream(xValuesString.split(",")).mapToDouble(Double::parseDouble).toArray();
                double[] yValues = Arrays.stream(yValuesString.split(",")).mapToDouble(Double::parseDouble).toArray();

                // Call the appropriate numerical differentiation method based on user selection
                double result = 0;
                if (method.equals("Forward Difference")) {
                    result = forwardDifference(xValues, yValues, stepSize);
                } else if (method.equals("Backward Difference")) {
                    result = backwardDifference(xValues, yValues, stepSize);
                } else if (method.equals("Central Difference")) {
                    result = centralDifference(xValues, yValues, stepSize);
                }

                // Display the result
                resultTextView.setText(String.format("%.4f", result));
            }
        });
    }

    private double forwardDifference(double[] xValues, double[] yValues, double h) {
        double x0 = xValues[0];
        double y0 = yValues[0];
        double y1 = yValues[1];
        return (y1 - y0) / h;
    }

    private double backwardDifference(double[] xValues, double[] yValues, double h) {
        double xn = xValues[xValues.length - 1];
        double yn = yValues[yValues.length - 1];
        double yn1 = yValues[yValues.length - 2];
        return (yn - yn1) / h;
    }

    private double centralDifference(double[] xValues, double[] yValues, double h) {
        double x0 = xValues[0];
        double xn = xValues[xValues.length - 1];
        double y0 = yValues[0];
        double yn = yValues[yValues.length - 1];
        return (yn - y0) / ((xn - x0) / h);
    }
}
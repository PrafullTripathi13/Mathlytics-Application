package com.example.mathlytics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class TranscendentalEquationSolution extends AppCompatActivity {

    private EditText equationInput;
    private EditText intervalStartInput;
    private EditText intervalEndInput;
    private EditText approxInput;
    private Button calcButton;
    private TextView resultTextView,labelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcendental_solution_equation);

        equationInput = findViewById(R.id.equation_input);
        intervalStartInput = findViewById(R.id.interval_start_input);
        intervalEndInput = findViewById(R.id.interval_end_input);
        approxInput = findViewById(R.id.approx);
        resultTextView = findViewById(R.id.result_text_view);
        labelTextView = findViewById(R.id.interval_label);
        calcButton = findViewById(R.id.calculate_button);

        Button heading = findViewById(R.id.headingSolTransEqn);
        Intent intent = getIntent();
        if(intent.getStringExtra("Method_name").contains("Bisection")){
            heading.setText("Bisection Method");
        }
        else if(intent.getStringExtra("Method_name").contains("Regula Falsi")){
            heading.setText("Regula Falsi Method");
            labelTextView.setText(getString(R.string.x0_x1));
            intervalStartInput.setHint(getString(R.string.x0));
            intervalEndInput.setHint(getString(R.string.x1));
        }
        else if(intent.getStringExtra("Method_name").contains("Newton Raphson")){
            heading.setText("Newton Raphson Method");
        }


        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intent.getStringExtra("Method_name").contains("Bisection")){
                   calculateBisection();
                }
                else if(intent.getStringExtra("Method_name").contains("Regula Falsi")){
                    calculateRegula();
                }
                else if(intent.getStringExtra("Method_name").contains("Newton Raphson")){
                   calculateNewton();
                }

            }
        });
    }


    public void calculateBisection() {
        String equation = equationInput.getText().toString();
        String aString = intervalStartInput.getText().toString();
        String bString = intervalEndInput.getText().toString();
        String nString = approxInput.getText().toString();

        if (equation.isEmpty() || aString.isEmpty() || bString.isEmpty() || nString.isEmpty()) {
            resultTextView.setText("Please fill all inputs.");
            return;
        }

        double a = Double.parseDouble(aString);
        double b = Double.parseDouble(bString);
        int n = Integer.parseInt(nString);

        double c = (a+b)/2;
        double epsilon = 0.0001;
        for (int i = 0; i < n; i++) {
            double fa = evaluate(equation,a);
            double fb = evaluate(equation,b);
            double fc = evaluate(equation,c);

            if(fc==0 || (b-a)/2 < epsilon){
                resultTextView.setText(String.format("The root of the equation is: %.4f", c));
                return;
            }
            if(fc * fa < 0){
                b = c;
            }
            else if(fc * fb < 0){
                a = c;
            }
            else{
                resultTextView.setText(String.format("The root of the equation does not exist"));
            }
            c = (a+b)/2;
        }
        resultTextView.setText(String.format("The root of the equation is: %.4f", c));

    }


    public void calculateRegula() {
        String equation = equationInput.getText().toString();
        String aString = intervalStartInput.getText().toString();
        String bString = intervalEndInput.getText().toString();
        String nString = approxInput.getText().toString();

        if (equation.isEmpty() || aString.isEmpty() || bString.isEmpty() || nString.isEmpty()) {
            resultTextView.setText("Please fill all inputs.");
            return;
        }

        double a = Double.parseDouble(aString);
        double b = Double.parseDouble(bString);
        int n = Integer.parseInt(nString);

        double c = (a+b)/2;
        double epsilon = 0.0001;
        for (int i = 0; i < n; i++) {
            double fa = evaluate(equation,a);
            double fb = evaluate(equation,b);
            double fc = evaluate(equation,c);

            if(fc==0 || (b-a)/2 < epsilon){
                resultTextView.setText(String.format("The root of the equation is: %.4f", c));
                return;
            }
            if(fc * fa < 0){
                b = c;
            }
            else if(fc * fb < 0){
                a = c;
            }
            else{
                resultTextView.setText(String.format("The root of the equation does not exist"));
            }
            c = (a+b)/2;
        }
        resultTextView.setText(String.format("The root of the equation is: %.4f", c));

    }


    public void calculateNewton() {
        String equation = equationInput.getText().toString();
        String aString = intervalStartInput.getText().toString();
        String bString = intervalEndInput.getText().toString();
        String nString = approxInput.getText().toString();

        if (equation.isEmpty() || aString.isEmpty() || bString.isEmpty() || nString.isEmpty()) {
            resultTextView.setText("Please fill all inputs.");
            return;
        }

        double a = Double.parseDouble(aString);
        double b = Double.parseDouble(bString);
        int n = Integer.parseInt(nString);

        double c = (a+b)/2;
        double epsilon = 0.0001;
        for (int i = 0; i < n; i++) {
            double fa = evaluate(equation,a);
            double fb = evaluate(equation,b);
            double fc = evaluate(equation,c);

            if(fc==0 || (b-a)/2 < epsilon){
                resultTextView.setText(String.format("The root of the equation is: %.4f", c));
                return;
            }
            if(fc * fa < 0){
                b = c;
            }
            else if(fc * fb < 0){
                a = c;
            }
            else{
                resultTextView.setText(String.format("The root of the equation does not exist"));
            }
            c = (a+b)/2;
        }
        resultTextView.setText(String.format("The root of the equation is: %.4f", c));

    }

    private double evaluate(String equation, double x) {
        String val = Double.toString(x);
        equation = equation.replaceAll("x",val);

        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);

        String result = "";

        Scriptable scriptable = rhino.initStandardObjects();
        result = rhino.evaluateString(scriptable,equation,"Javascript",1,null).toString();

        double ans = Double.parseDouble(result);

        return ans;
    }



}

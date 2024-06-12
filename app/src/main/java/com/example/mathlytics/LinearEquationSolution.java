package com.example.mathlytics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LinearEquationSolution extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearequationsolution);

        //fetching value from the activity
        Button  heading = findViewById(R.id.headingLinearEquation);
        Intent intent = getIntent();
        if(intent.getStringExtra("Method_name").contains("Gauss Jordan")){
             heading.setText("Gauss-Jordan Method");
        }
        else if(intent.getStringExtra("Method_name").contains("Gauss Seidal")){
            heading.setText("Gauss-Seidal Method");
        }
        else if(intent.getStringExtra("Method_name").contains("Crouts Method")){
            heading.setText("Crout's Method");
        }

        heading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method call
            }
        });

    }
}
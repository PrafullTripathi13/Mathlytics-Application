package com.example.mathlytics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button math3Button = findViewById(R.id.mathematics_3_button);

        math3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View  v) {
                Intent intent = new Intent(MainActivity.this,Mathematics3.class);
                startActivity(intent);
            }
        });


    }
}
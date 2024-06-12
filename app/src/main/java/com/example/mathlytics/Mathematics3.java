package com.example.mathlytics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Mathematics3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematics3);

        // Unit1 Drop down options
        Button dropdownButton1 = findViewById(R.id.unit1_button);
        // Unit2 Drop down options
        Button dropdownButton2 = findViewById(R.id.unit2_button);
        // Unit3 Drop down options
        Button dropdownButton3 = findViewById(R.id.unit3_button);
        // Unit4 Drop down options
        Button dropdownButton4 = findViewById(R.id.unit4_button);
        // Unit5 Drop down options
        Button dropdownButton5 = findViewById(R.id.unit5_button);


        // Set OnClickListener for the Button
        dropdownButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Mathematics3.this, dropdownButton1);
                popupMenu.getMenuInflater().inflate(R.menu.unit1, popupMenu.getMenu());

                // Set OnMenuItemClickListener for the PopupMenu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.option1_1:
                                Intent intent1 = new Intent(Mathematics3.this, TranscendentalEquationSolution.class);
                                intent1.putExtra("Method_name", "Bisection");
                                startActivity(intent1);
                                return true;

                            case R.id.option1_2:
                                Intent intent2 = new Intent(Mathematics3.this, TranscendentalEquationSolution.class);
                                intent2.putExtra("Method_name", "Regula Falsi");
                                startActivity(intent2);
                                return true;

                            case R.id.option1_3:
                                Intent intent3 = new Intent(Mathematics3.this, TranscendentalEquationSolution.class);
                                intent3.putExtra("Method_name", "Newton Raphson");
                                startActivity(intent3);
                                return true;

                            case R.id.option1_4:
                                Intent intent4 = new Intent(Mathematics3.this, Interpolation.class);
                                intent4.putExtra("Method_name", "Forward Interpolation");
                                startActivity(intent4);
                                return true;

                            case R.id.option1_5:
                                Intent intent5 = new Intent(Mathematics3.this, Interpolation.class);
                                intent5.putExtra("Method_name", "Backward Interpolation");
                                startActivity(intent5);
                                return true;

                            case R.id.option1_6:
                                Intent intent6 = new Intent(Mathematics3.this, Interpolation.class);
                                intent6.putExtra("Method_name", "Divided Difference");
                                startActivity(intent6);
                                return true;

                            case R.id.option1_7:
                                Intent intent7 = new Intent(Mathematics3.this, Interpolation.class);
                                intent7.putExtra("Method_name", "Lagrange");
                                startActivity(intent7);
                                return true;

                            default:
                                return false;
                        }
                    }
                });

                popupMenu.show();
            }
        });


        // Set OnClickListener for the Button
        dropdownButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(Mathematics3.this, dropdownButton2);
                popupMenu.getMenuInflater().inflate(R.menu.unit2, popupMenu.getMenu());

                // Set OnMenuItemClickListener for the PopupMenu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.option2_1:
                                Intent intent1 = new Intent(Mathematics3.this, NumericalDifferentiation.class);
                                startActivity(intent1);
                                return true;

                            case R.id.option2_2:
                                Intent intent2 = new Intent(Mathematics3.this, NumericalIntegration.class);
                                startActivity(intent2);
                                return true;

                            case R.id.option2_3:
                                Intent intent3 = new Intent(Mathematics3.this, LinearEquationSolution.class);
                                intent3.putExtra("Method_name", "Gauss Jordan");
                                startActivity(intent3);
                                return true;

                            case R.id.option2_4:
                                Intent intent4 = new Intent(Mathematics3.this, LinearEquationSolution.class);
                                intent4.putExtra("Method_name", "Crouts Method");
                                startActivity(intent4);
                                return true;

                            case R.id.option2_5:
                                Intent intent5 = new Intent(Mathematics3.this, LinearEquationSolution.class);
                                intent5.putExtra("Method_name", "Gauss Seidal");
                                startActivity(intent5);
                                return true;

                            default:
                                return false;
                        }
                    }
                });

                popupMenu.show();
            }
        });





        /*Button math3Button1 = findViewById(R.id.unit1_button);
        Button math3Button2 = findViewById(R.id.unit2_button);


        math3Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View  v) {
                Intent intent = new Intent(Mathematics3.this,Unit1.class);
                startActivity(intent);
            }
        });

        math3Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View  v) {
                Intent intent = new Intent(Mathematics3.this,Unit2.class);
                startActivity(intent);
            }
        });*/
    }
}
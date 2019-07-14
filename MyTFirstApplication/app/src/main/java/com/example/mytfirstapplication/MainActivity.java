package com.example.mytfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int num1, num2;

    private void getTexts(){

        EditText editTextFirstNum = (EditText) findViewById(R.id.editTextFirstNum);
        EditText editTextSecondNum = (EditText) findViewById(R.id.editTextSecondNum);
        num1 = Integer.parseInt(editTextFirstNum.getText().toString());
        num2 = Integer.parseInt(editTextSecondNum.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnAdd = (Button) findViewById(R.id.buttonAdd);
        Button btnSub = (Button) findViewById(R.id.buttonSub);
        Button btnMult = (Button) findViewById(R.id.buttonMult);
        Button btnDiv = (Button) findViewById(R.id.buttonDiv);


        final TextView resTextView = (TextView) findViewById(R.id.TextViewResult);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTexts();
                int res= num1+num2;
                resTextView.setText(res +"");
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTexts();
                int res= num1-num2;
                resTextView.setText(res +"");
            }
        });

        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTexts();
                int res= num1*num2;
                resTextView.setText(res +"");
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTexts();
                int res= num1/num2;
                resTextView.setText(res +"");
            }
        });

    }
}

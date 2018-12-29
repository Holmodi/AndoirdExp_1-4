package com.example.holmodi.dicaculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class math extends AppCompatActivity {

    DecimalFormat df = new DecimalFormat("#0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
        final EditText meter = (EditText)findViewById(R.id.meter);
        final EditText foot = (EditText)findViewById(R.id.foot);
        final EditText cos = (EditText)findViewById(R.id.cos);
        final EditText sin = (EditText)findViewById(R.id.sin);
        final EditText tan = (EditText)findViewById(R.id.tan);
        final EditText arcsin = (EditText)findViewById(R.id.arcsin);
        final EditText arccos = (EditText)findViewById(R.id.arccos);
        final EditText arctan = (EditText)findViewById(R.id.arctan);
        Button center1 = (Button)findViewById(R.id.center1);
        Button center2 = (Button)findViewById(R.id.center2);
        Button clean = (Button)findViewById(R.id.cleanmath);
        center1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!meter.getText().toString().isEmpty()){
                    try{
                        foot.setText(String.valueOf(df.format(Double.parseDouble(meter.getText().toString())*3.28)));
                    }catch (Exception e){
                        foot.setText("ERROR");
                    }
                } else if(!foot.getText().toString().isEmpty()){
                    try{
                        meter.setText(String.valueOf(df.format(Double.parseDouble(foot.getText().toString())/3.28)));
                    }catch (Exception e){
                        meter.setText("ERROR");
                    }
                }
            }
        });
        center2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sin.getText().toString().isEmpty()){
                    try {
                        arcsin.setText(String.valueOf(df.format(Math.sin(Double.parseDouble(sin.getText().toString()) * Math.PI / 180))));
                    }catch (Exception e){
                        arcsin.setText("ERROR");
                    }
                }else if(!arcsin.getText().toString().isEmpty()){
                    try {
                        sin.setText(String.valueOf(df.format(Math.asin(Double.parseDouble(arcsin.getText().toString()))*(180/Math.PI))));
                    }catch (Exception e){
                        sin.setText("ERROR");
                    }
                }
                if(!cos.getText().toString().isEmpty()){
                    try {
                        arccos.setText(String.valueOf(df.format(Math.cos(Double.parseDouble(cos.getText().toString()) * Math.PI / 180))));
                    }catch (Exception e){
                        arccos.setText("ERROR");
                    }
                }else if(!arccos.getText().toString().isEmpty()){
                    try {
                        cos.setText(String.valueOf(df.format(Math.acos(Double.parseDouble(arccos.getText().toString()))*(180/Math.PI))));
                    }catch (Exception e){
                        cos.setText("ERROR");
                    }
                }
                if(!tan.getText().toString().isEmpty()){
                    try {
                        arctan.setText(String.valueOf(df.format(Math.tan(Double.parseDouble(tan.getText().toString()) * Math.PI / 180))));
                    }catch (Exception e){
                        arctan.setText("ERROR");
                    }
                }else if(!arctan.getText().toString().isEmpty()){
                    try {
                        tan.setText(String.valueOf(df.format(Math.atan(Double.parseDouble(arctan.getText().toString()))*(180/Math.PI))));
                    }catch (Exception e){
                        tan.setText("ERROR");
                    }
                }
            }
        });
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meter.setText("");
                foot.setText("");
                sin.setText("");
                cos.setText("");
                tan.setText("");
                arcsin.setText("");
                arccos.setText("");
                arctan.setText("");
            }
        });
    }
}
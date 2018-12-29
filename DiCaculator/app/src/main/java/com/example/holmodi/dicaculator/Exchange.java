package com.example.holmodi.dicaculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class Exchange extends AppCompatActivity {

    DecimalFormat df = new DecimalFormat("#.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        final EditText rmb = (EditText)findViewById(R.id.RMB);
        final EditText eur = (EditText)findViewById(R.id.EUR);
        final EditText usd = (EditText)findViewById(R.id.USD);
        final EditText gbp = (EditText)findViewById(R.id.GBP);
        final EditText jpy = (EditText)findViewById(R.id.JPY);
        final EditText rub = (EditText)findViewById(R.id.RUB);
        Button cl = (Button)findViewById(R.id.clean);
        Button ex = (Button)findViewById(R.id.exchangeRate);
        ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!rmb.getText().toString().isEmpty()){
                    double Rmb = Double.parseDouble(rmb.getText().toString());
                    eur.setText(String.valueOf(df.format(0.1278*Rmb)));
                    usd.setText(String.valueOf(df.format(0.1531*Rmb)));
                    gbp.setText(String.valueOf(df.format(0.1151*Rmb)));
                    jpy.setText(String.valueOf(df.format(16.848*Rmb)));
                    rub.setText(String.valueOf(df.format(8.8178*Rmb)));
                }else if(!eur.getText().toString().isEmpty()){
                    double Eur = Double.parseDouble(eur.getText().toString());
                    rmb.setText(String.valueOf(df.format(7.8247*Eur)));
                    usd.setText(String.valueOf(df.format(1.198*Eur)));
                    gbp.setText(String.valueOf(df.format(0.9006*Eur)));
                    jpy.setText(String.valueOf(df.format(131.83*Eur)));
                    rub.setText(String.valueOf(df.format(68.997*Eur)));
                }else if(!usd.getText().toString().isEmpty()){
                    double Usd = Double.parseDouble(usd.getText().toString());
                    rmb.setText(String.valueOf(df.format(6.5317*Usd)));
                    eur.setText(String.valueOf(df.format(0.8347*Usd)));
                    gbp.setText(String.valueOf(df.format(0.7517*Usd)));
                    jpy.setText(String.valueOf(df.format(110.05*Usd)));
                    rub.setText(String.valueOf(df.format(57.595*Usd)));
                }else if(!gbp.getText().toString().isEmpty()){
                    double Gbp = Double.parseDouble(gbp.getText().toString());
                    rmb.setText(String.valueOf(df.format(8.6881*Gbp)));
                    eur.setText(String.valueOf(df.format(1.1103*Gbp)));
                    usd.setText(String.valueOf(df.format(1.3301*Gbp)));
                    jpy.setText(String.valueOf(df.format(146.38*Gbp)));
                    rub.setText(String.valueOf(df.format(76.61*Gbp)));
                }else if(!jpy.getText().toString().isEmpty()){
                    double Jpy = Double.parseDouble(jpy.getText().toString());
                    rmb.setText(String.valueOf(df.format(0.0594*Jpy)));
                    eur.setText(String.valueOf(df.format(0.0076*Jpy)));
                    usd.setText(String.valueOf(df.format(0.0091*Jpy)));
                    gbp.setText(String.valueOf(df.format(0.0068*Jpy)));
                    rub.setText(String.valueOf(df.format(0.5238*Jpy)));
                }else if(!rub.getText().toString().isEmpty()){
                    double Rub = Double.parseDouble(rub.getText().toString());
                    rmb.setText(String.valueOf(df.format(0.1134*Rub)));
                    eur.setText(String.valueOf(df.format(0.0145*Rub)));
                    usd.setText(String.valueOf(df.format(0.0174*Rub)));
                    gbp.setText(String.valueOf(df.format(0.0131*Rub)));
                    jpy.setText(String.valueOf(df.format(1.9106*Rub)));
                }
            }
        });
        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rmb.setText("");
                eur.setText("");
                usd.setText("");
                gbp.setText("");
                jpy.setText("");
                rub.setText("");
            }
        });
    }
}

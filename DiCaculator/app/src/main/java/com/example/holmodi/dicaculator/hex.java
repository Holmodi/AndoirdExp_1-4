package com.example.holmodi.dicaculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class hex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hex);
        final EditText Before = (EditText)findViewById(R.id.before);
        final EditText Number = (EditText)findViewById(R.id.number);
        final EditText After = (EditText)findViewById(R.id.after);
        final TextView result = (TextView)findViewById(R.id.result);
        Button cal = (Button)findViewById(R.id.calculationhex);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int before = Integer.parseInt(Before.getText().toString());
                int after = Integer.parseInt(After.getText().toString());
                try{
                    int t = Integer.parseInt(Number.getText().toString(), before);
                    result.setText(Integer.toString(t, after));
                }catch (Exception e){
                    result.setText("ERROR");
                }
            }
        });
    }
}

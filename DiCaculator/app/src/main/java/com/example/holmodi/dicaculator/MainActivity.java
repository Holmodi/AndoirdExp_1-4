package com.example.holmodi.dicaculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.StringReader;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Stack<Character> optr = new Stack<>();
    private Stack<Double> opnd = new Stack<>();
    private TextView display;
    private StringBuilder builder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView)findViewById(R.id.display);
        Button clean = (Button)findViewById(R.id.clean);
        Button div = (Button)findViewById(R.id.div);
        Button mul = (Button)findViewById(R.id.mul);
        Button delete = (Button)findViewById(R.id.delete);
        Button sub = (Button)findViewById(R.id.sub);
        Button add = (Button)findViewById(R.id.add);
        Button leftBrackets = (Button)findViewById(R.id.leftbrackets);
        Button spot = (Button)findViewById(R.id.spot);
        Button rightBrackets = (Button)findViewById(R.id.rightbrackets);
        Button equal = (Button)findViewById(R.id.equal);
        Button button0 = (Button)findViewById(R.id.button0);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
        Button button6 = (Button)findViewById(R.id.button6);
        Button button7 = (Button)findViewById(R.id.button7);
        Button button8 = (Button)findViewById(R.id.button8);
        Button button9 = (Button)findViewById(R.id.button9);
        clean.setOnClickListener(this);
        div.setOnClickListener(this);
        mul.setOnClickListener(this);
        delete.setOnClickListener(this);
        sub.setOnClickListener(this);
        add.setOnClickListener(this);
        leftBrackets.setOnClickListener(this);
        spot.setOnClickListener(this);
        rightBrackets.setOnClickListener(this);
        equal.setOnClickListener(this);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.hex:
                Intent intent1 = new Intent(MainActivity.this,hex.class);
                startActivity(intent1);
                break;
            case R.id.exchangeRate:
                Intent intent3 = new Intent(MainActivity.this,Exchange.class);
                startActivity(intent3);
                break;
            case R.id.math:
                Intent intent4 = new Intent(MainActivity.this,math.class);
                startActivity(intent4);
                break;
            case R.id.help:
                Toast.makeText(this, "Please send email to developers", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clean:
                builder.delete(0,builder.length());
                display.setText("0");
                opnd.clear();
                optr.clear();
                break;
            case R.id.delete:
                if(builder.length()!=0) {
                    builder.delete(builder.length() - 1, builder.length());
                    display.setText(builder);
                }
                if(builder.length() == 0){
                    display.setText("0");
                }
                break;
            case R.id.div:
                if(builder.toString().isEmpty() || builder.toString().substring(builder.length()-1).equals(".")) break;
                if(permitOptr(builder.toString())){
                    builder.append("/");
                    display.setText(builder.toString());
                }
                break;
            case R.id.mul:
                if(builder.toString().isEmpty() || builder.toString().substring(builder.length()-1).equals(".")) break;
                if(permitOptr(builder.toString())){
                    builder.append("*");
                    display.setText(builder.toString());
                }
                break;
            case R.id.sub:
                if(builder.toString().isEmpty() || builder.toString().substring(builder.length()-1).equals(".")) break;
                if(permitOptr(builder.toString())){
                    builder.append("-");
                    display.setText(builder.toString());
                }
                break;
            case R.id.add:
                if(builder.toString().isEmpty() || builder.toString().substring(builder.length()-1).equals(".")) break;
                if(permitOptr(builder.toString())){
                    builder.append("+");
                    display.setText(builder.toString());
                }
                break;
            case R.id.leftbrackets:
                if(regularBrackets(builder.toString(),"(")){
                    builder.append("(");
                    display.setText(builder.toString());
                }
                break;
            case R.id.rightbrackets:
                if(regularBrackets(builder.toString(),")")){
                    builder.append(")");
                    display.setText(builder.toString());
                }
                break;
            case R.id.spot:
                if(builder.toString().isEmpty()) builder.append("0");
                if(builder.toString().substring(builder.length()-1).equals(".")) break;
                if(builder.toString().substring(builder.length()-1).equals(")")){
                    builder.append("*");
                    builder.append(0);
                }
                builder.append(".");
                display.setText(builder);
                break;
            case R.id.equal:
                if(builder.toString().isEmpty() || ("+-*/.".contains(builder.toString().substring(builder.length()-1)))){
                    display.setText("ERROR");
                    break;
                }else {
                    builder.append("#");
                    display.setText(calculation(builder.toString()));
                    builder.delete(0,builder.length());
                    opnd.clear();
                    optr.clear();
                }
                break;
            case R.id.button9:
                BracketsNumRegular();
                builder.append("9");
                display.setText(builder);
                break;
            case R.id.button8:
                BracketsNumRegular();
                builder.append("8");
                display.setText(builder);
                break;
            case R.id.button7:
                BracketsNumRegular();
                builder.append("7");
                display.setText(builder);
                break;
            case R.id.button6:
                BracketsNumRegular();
                builder.append("6");
                display.setText(builder);
                break;
            case R.id.button5:
                BracketsNumRegular();
                builder.append("5");
                display.setText(builder);
                break;
            case R.id.button4:
                BracketsNumRegular();
                builder.append("4");
                display.setText(builder);
                break;
            case R.id.button3:
                BracketsNumRegular();
                builder.append("3");
                display.setText(builder);
                break;
            case R.id.button2:
                BracketsNumRegular();
                builder.append("2");
                display.setText(builder);
                break;
            case R.id.button1:
                BracketsNumRegular();
                builder.append("1");
                display.setText(builder);
                break;
            case R.id.button0:
                BracketsNumRegular();
                builder.append("0");
                display.setText(builder);
                break;
            default:
                break;
        }
    }

    //判断当前字符是否为数字
    public boolean judgeNumber(char c){
        if(c>='0' && c<='9') return true;
        else return false;
    }

    //处理运算符不符规则输入
    public boolean permitOptr(String builders){
        if(builders.isEmpty()){
            return false;
        }else {
            String s = builders.substring(builders.length()-1);
            if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                builder.delete(builder.length()-1,builder.length());
            }
            return true;
        }
    }

    //括号规则
    public boolean regularBrackets(String builders,String brackets){
        if(brackets.equals("(")){
            if(builders.isEmpty()) return true;
            if(builder.toString().substring(builder.length()-1).equals(".")) return false;
            if("0123456789)".contains(builders.substring(builders.length()-1))){
                builder.append("*");
            }
            return true;
        }else if(brackets.equals(")")){
            if((!builders.isEmpty()) && "+-*/(.".contains(builders.substring(builders.length()-1))) return false;
            if((appearNumber(builders,"(")-appearNumber(builders,")"))>0){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    //判断字符串中某一字符数量
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        int index = 0;
        while ((index = srcText.indexOf(findText, index)) != -1) {
            index = index + findText.length();
            count++;
        }
        return count;
    }

    //处理右括号后直接接数字的操作
    public void BracketsNumRegular(){
        if((!builder.toString().isEmpty()) && builder.toString().substring(builder.toString().length()-1).equals(")")){
            builder.append("*");
        }
    }

    //运算符优先级判断
    public char precede(char c1, char c2) {
        int i = 0;
        int j = 0;
        char array[] = {
        '>','>','<','<','<','>','>',
                '>','>','<','<','<','>','>',
                '>','>','>','>','<','>','>',
                '>','>','>','>','<','>','>',
                '<','<','<','<','<','=','!',
                '>','>','>','>','!','>','>',
                '<','<','<','<','<','!','=' };
        switch (c1)
        {
            case '+':i = 0; break;
            case '-':i = 1; break;
            case '*':i = 2; break;
            case '/':i = 3; break;
            case '(':i = 4; break;
            case ')':i = 5; break;
            case '#':i = 6; break;
            default:break;
        }
        switch (c2)
        {
            case '+':j = 0; break;
            case '-':j = 1; break;
            case '*':j = 2; break;
            case '/':j = 3; break;
            case '(':j = 4; break;
            case ')':j = 5; break;
            case '#':j = 6; break;
            default:break;
        }
        return array[7 * i + j];
    }

    //计算函数
    public String calculation(String s){
        char[] equation = s.toCharArray();
        optr.push('#');
        for(int i=0;i<s.length();i++){
            if(judgeNumber(equation[i])){
                StringBuilder num = new StringBuilder();
                int j=i;
                while (judgeNumber(equation[i+1]) || equation[i+1]=='.'){
                    i++;
                }
                for(;j<=i;j++){
                    num.append(equation[j]);
                }
                opnd.push(Double.parseDouble(num.toString()));
            }else{
                switch (precede(optr.peek(),equation[i])){
                    case '<':
                        optr.push(equation[i]);
                        break;
                    case '=':
                        optr.pop();
                        break;
                    case '>':
                        i--;
                        double b =opnd.pop();
                        double a =opnd.pop();
                        switch (optr.pop()){
                            case '+':
                                opnd.push(a+b);
                                break;
                            case '-':
                                opnd.push(a-b);
                                break;
                            case '*':
                                opnd.push(a*b);
                                break;
                            case '/':
                                opnd.push(a/b);
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return opnd.pop().toString();
    }

}

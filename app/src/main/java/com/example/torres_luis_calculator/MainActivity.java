package com.example.torres_luis_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    Button button, button2, button3, button4, button5, button6, button7, button8, button9, button10, //numbers
            mult, div, add, sub, exp, openPara, closePara, allClear, equal; //operators
    TextView numView;
    StringBuilder numbers = new StringBuilder(""); //number.append("") to add numbers
    String multiplySTR = "*", divisionSTR = "/", addSTR = "+", subSTR = "-",
            openParenthesisSTR = "(", closedParenthesisSTR = ")", expSTR = "^", ERROR = "--E--";
    int numOfOpenPara = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setGUI();

        buttonInput();
    }

    public void buttonInput() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append("1");
                numView.setText(numbers);
               enableButtons();
            }

        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append("2");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append("3");
                numView.setText(numbers);
               enableButtons();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append("4");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append("5");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append("6");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append("7");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append("8");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append("9");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append("0");
                numView.setText(numbers);
                enableButtons();
            }
        });
        //operations
        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append(multiplySTR);
                numView.setText(numbers);
                disablebuttons();
            }
        });

        div.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append(divisionSTR);
                numView.setText(numbers);
                disablebuttons();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append(addSTR);
                numView.setText(numbers);
                disablebuttons();
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numbers.append(subSTR);
                numView.setText(numbers);
                disablebuttons();
            }
        });

        openPara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append(openParenthesisSTR);
                numView.setText(numbers);
                enableCloseParanths();
                numOfOpenPara++;

            }
        });

        //do all arithmetic when closing parenthesis to first open parenthesis . DON"T WORRY ABOUT MULTIPLICATION YET
        closePara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append(closedParenthesisSTR);
                numView.setText(numbers);
                numOfOpenPara--;
                if(numOfOpenPara < 1){
                    disableCloseParanths();
                }
            }
        });
        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append(expSTR);
                numView.setText(numbers);
            }
        });
        allClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.delete(0,numbers.length());
                numView.setText("");
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    postFix();
                    numView.setText(numbers);
                }catch (Exception e){
                    numbers.delete(0,numbers.length());
                    numView.setText(ERROR);
                }
            }
        });
    }

    public void postFix(){
        String result = "";
        Stack<String> stack = new Stack<>();
        for (int i = 0; i <numbers.length() ; i++) {

            String indexDigit = Character.toString(numbers.charAt(i));

            if(checkOperatorPriority(indexDigit)>0){
                while(stack.isEmpty()==false && checkOperatorPriority(stack.peek())>=checkOperatorPriority(indexDigit)){
                    result += stack.pop();
                }
                stack.push(indexDigit);
            }else if(indexDigit.equals(closedParenthesisSTR)){
                String numForUse = stack.pop();
                while(!numForUse.equals(openParenthesisSTR)){
                    result += numForUse;
                    numForUse = stack.pop();
                }
            }else if(indexDigit.equals(openParenthesisSTR)){
                stack.push(indexDigit);
            }else{

                result += indexDigit;
            }
        }
        for (int i = 0; i <=stack.size() ; i++) {
            result += stack.pop();
        }
        numbers.delete(0,numbers.length());
        numbers.append(result);
    }

    public void operations(){
        //uses numbers postfix to do math!
        //use 301 code for rest
    }

    public int checkOperatorPriority(String operator){
        if(operator.equals(addSTR) || operator.equals(subSTR)){
            return 1;
        } else if(operator.equals(multiplySTR) || operator.equals(divisionSTR)){
            return 2;
        }else if (operator.equals(expSTR)){
            return 3;
        }
        return -1;
    }

    public void setGUI() {
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        mult = findViewById(R.id.multiply);
        div = findViewById(R.id.divide);
        add = findViewById(R.id.addition);
        sub = findViewById(R.id.subtract);
        exp = findViewById(R.id.exp);
        openPara = findViewById(R.id.openPara);
        closePara = findViewById(R.id.closePara);
        allClear = findViewById(R.id.allClear);
        equal = findViewById(R.id.equal);

        numView = findViewById(R.id.numView);
        disablebuttons();
        disableCloseParanths();
    }
    public void disableCloseParanths() {
        closePara.setEnabled(false);
    }

    public void enableCloseParanths() {
        closePara.setEnabled(true);
    }

    public void disablebuttons() {
        add.setEnabled(false);
        mult.setEnabled(false);
        div.setEnabled(false);
        sub.setEnabled(false);
        exp.setEnabled(false);
        equal.setEnabled(false);
    }

    public void enableButtons() {
        add.setEnabled(true);
        mult.setEnabled(true);
        div.setEnabled(true);
        sub.setEnabled(true);
        exp.setEnabled(true);
        equal.setEnabled(true);
    }


}

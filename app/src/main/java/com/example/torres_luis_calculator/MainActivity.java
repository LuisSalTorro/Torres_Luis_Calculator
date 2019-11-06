package com.example.torres_luis_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    Button button, button2, button3, button4, button5, button6, button7, button8, button9, button10, //numbers
            mult, div, add, sub, exp, openPara, closePara, allClear, equal, negPos, decimal; //operators
    TextView numView;
    StringBuilder numbers = new StringBuilder(""); //number.append("") to add numbers
    String multiplySTR = " * ", divisionSTR = " / ", addSTR = " + ", subSTR = " - ",
            openParenthesisSTR = " ( ", closedParenthesisSTR = " ) ", expSTR = " ^ ", ERROR = "--E--";
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
                checkClosePara();
                numbers.append("1");
                numView.setText(numbers);
                checkClosePara();
                enableButtons();
            }

        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClosePara();
                numbers.append("2");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClosePara();
                numbers.append("3");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClosePara();
                numbers.append("4");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClosePara();
                numbers.append("5");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClosePara();
                numbers.append("6");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClosePara();
                numbers.append("7");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClosePara();
                numbers.append("8");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClosePara();
                numbers.append("9");
                numView.setText(numbers);
                enableButtons();
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClosePara();
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
                closePara.setEnabled(false);
            }
        });

        div.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        numbers.append(divisionSTR);
                        numView.setText(numbers);
                        disablebuttons();
                        closePara.setEnabled(false);
                    }
                });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append(addSTR);
                numView.setText(numbers);
                disablebuttons();
                closePara.setEnabled(false);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numbers.append(subSTR);
                numView.setText(numbers);
                disablebuttons();
                closePara.setEnabled(false);
            }
        });

        openPara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numbers.length() > 0 && Character.isDigit(numbers.charAt(numbers.length()-1))){
                    numbers.append(multiplySTR);
                }
                negPos.setEnabled(true);
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
                numOfOpenPara = 0;
                disablebuttons();
                disableCloseParanths();
            }
        });
        negPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append("-");
                numView.setText(numbers);
                disablebuttons();
                negPos.setEnabled(false);
            }
        });
        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.append(".");
                numView.setText(numbers);
                disablebuttons();
                negPos.setEnabled(false);
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    double answer = equal(convertToPostFix());
                    numbers.delete(0,numbers.length());
                    numbers.append(answer);
                    numView.setText(numbers);
                }catch (StackOverflowError e){
                    numbers.delete(0,numbers.length());
                    numView.setText("Infinity");
                } catch (Exception e){
                    numbers.delete(0,numbers.length());
                    numView.setText(ERROR);
                }
                disablebuttons();
                disableCloseParanths();

            }
        });
    }

    public ArrayList<String> convertToPostFix(){

        ArrayList<String> postArray = new ArrayList<>();
        String[] numsAndOps = numbers.toString().split(" "); //full numbers and operators
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < numsAndOps.length ; i++) {

            String indexDigit = numsAndOps[i];
            if(checkOperatorPriority(indexDigit)>0){
                while(!stack.isEmpty() && checkOperatorPriority(stack.peek()) >= checkOperatorPriority(indexDigit)){
                    //result += stack.pop();
                    postArray.add(stack.pop());

                }
                stack.push(indexDigit);
            }else if(indexDigit.equals(")")){
                String numForUse = stack.pop(); //removes closing parenthesis and ignores it in postStack
                while(!numForUse.equals("(")){

                    postArray.add(numForUse);

                    numForUse = stack.pop();
                }
            }else if(indexDigit.equals("(")){
                stack.push(indexDigit);
            }
            else if(isNumber(indexDigit)){
                postArray.add(indexDigit);
            }
        }
        if(stack.size() > 0){
            for (int i = 0; i <=stack.size() ; i++) {

                postArray.add(stack.pop());

            }
        }

        return postArray;
    }

    public double equal(ArrayList<String> postArray){
        if(postArray.size() == 1){
            return Double.parseDouble(postArray.get(0));
        }
        //uses numbers postfix to do math!
        double answer = 0;
        double temp;
        Stack<Double> doubleStack = new Stack<>();
        for(int i = 0; i < postArray.size(); i++){
            if(isNumber(postArray.get(i))){
                doubleStack.push(Double.parseDouble(postArray.get(i)));
            }else if(postArray.get(i).equals("*") || postArray.get(i).equals("/")
                    || postArray.get(i).equals("+") || postArray.get(i).equals("-")
                    || postArray.get(i).equals("^")){
                double y = doubleStack.pop(), x = doubleStack.pop();
                temp = arithmetic(postArray.get(i), x, y);
                //answer = temp;
                doubleStack.push(temp);
            } //sometimes empty spaces make it here, so it's best to let them run through the loop
        }

        return doubleStack.pop();
    }

    public double arithmetic(String operator, double x, double y) {
        if (operator.equals("*")) {
            return x * y;
        } else if (operator.equals("/")) {
            if(y == 0){
                throw new ArithmeticException();
            }
            return x / y;
        } else if (operator.equals("+")) {
            return x + y;
        }else if (operator.equals("^")){
            return power(x,y);
        } else{
            return x - y;
        }
    }

    public double power(double base, double exp){

        return Math.pow(base, exp);

    }

    public boolean isNumber(String indexNumber){
        try{
            double tester = Double.parseDouble(indexNumber);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public int checkOperatorPriority(String operator){
        if(operator.equals("+") ||
                operator.equals("-")){
            return 1;
        } else if(operator.equals("*") ||
                operator.equals("/")){
            return 2;
        }else if (operator.equals("^")){
            return 3;
        }
        return -1; //if it's a number
    }

    public void checkClosePara(){
        //when entering a number, we check to see if the previous spot is a closing parantheses.
        try{
            if(numbers.length() > 2){
                String charactercheck = Character.toString(numbers.charAt(numbers.length()-2));
                if(charactercheck.equals(")")){
                    numbers.append(multiplySTR);
                }else{
                    return;
                }
            }else{
                return;
            }
        }catch (Exception e){

        }

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
        negPos = findViewById(R.id.negPos);
        decimal = findViewById(R.id.decimal);

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
        decimal.setEnabled(false);
        negPos.setEnabled(true);
    }

    public void enableButtons() {
        add.setEnabled(true);
        mult.setEnabled(true);
        div.setEnabled(true);
        sub.setEnabled(true);
        exp.setEnabled(true);
        equal.setEnabled(true);
        decimal.setEnabled(true);
        negPos.setEnabled(false);
        if(numOfOpenPara > 0){
            closePara.setEnabled(true);
        }
    }


}

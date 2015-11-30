package com.shinjaehun.suksuk;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public int top, down;
    public int topHundred, topTen, topOne;
    public int downTen, downOne;

    TextView top_hundred, top_ten, top_one;
    TextView down_ten, down_one;

    TextView carrying_hundred, carrying_ten;
    TextView ans_top_one, ans_top_ten, ans_top_hundred, ans_top_thousand;
    TextView ans_down_one, ans_down_ten, ans_down_hundred, ans_down_thousand;
    TextView ans_one, ans_ten, ans_hundred, ans_thousand, ans_tenthousand;

//    public int operand1, operand2;
//    TextView currentTextViewA, currentTextViewB;

    TextView operand1TextView, operand2TextView, input1TextView, input2TextView;

    //    private ArrayList<TextView> answers = new ArrayList<TextView>(17);
//    Stack answers = new Stack<TextView>();
    boolean carrying = true;
    //boolean userInput = false;
//    int currentAnswer = 0;
    int currentStage = 0;
    int ans = 0;
    int totalCarry = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

//        stageOne();

        nextStage();

//        if (userInput) {
//            stageOne();
//        }

    }

    public void init() {

        carrying_hundred = (TextView) findViewById(R.id.carrying_hundred);
        carrying_ten = (TextView) findViewById(R.id.carrying_ten);

        top_hundred = (TextView) findViewById(R.id.top_hundred);
        top_ten = (TextView) findViewById(R.id.top_ten);
        top_one = (TextView) findViewById(R.id.top_one);

        down_ten = (TextView) findViewById(R.id.down_ten);
        down_one = (TextView) findViewById(R.id.down_one);

        ans_top_one = (TextView) findViewById(R.id.ans_top_oen);
        ans_top_ten = (TextView) findViewById(R.id.ans_top_ten);
        ans_top_hundred = (TextView) findViewById(R.id.ans_top_hundred);
        ans_top_thousand = (TextView) findViewById(R.id.ans_top_thousand);

        ans_down_one = (TextView) findViewById(R.id.ans_down_one);
        ans_down_ten = (TextView) findViewById(R.id.ans_down_ten);
        ans_down_hundred = (TextView) findViewById(R.id.ans_down_hundred);
        ans_down_thousand = (TextView) findViewById(R.id.ans_down_thousand);

        ans_one = (TextView) findViewById(R.id.ans_one);
        ans_ten = (TextView) findViewById(R.id.ans_ten);
        ans_hundred = (TextView) findViewById(R.id.ans_hundred);
        ans_thousand = (TextView) findViewById(R.id.ans_thousand);
        ans_tenthousand = (TextView) findViewById(R.id.ans_tenthousand);

        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);
        findViewById(R.id.button_5).setOnClickListener(this);
        findViewById(R.id.button_6).setOnClickListener(this);
        findViewById(R.id.button_7).setOnClickListener(this);
        findViewById(R.id.button_8).setOnClickListener(this);
        findViewById(R.id.button_9).setOnClickListener(this);
        findViewById(R.id.button_0).setOnClickListener(this);
        findViewById(R.id.button_clear).setOnClickListener(this);
        findViewById(R.id.button_enter).setOnClickListener(this);

//        answers.add(carrying_ten);
//        answers.add(ans_down_one);
//        answers.add(carrying_hundred);
//        answers.add(ans_top_ten);
//        answers.add(ans_top_thousand);
//        answers.add(ans_top_hundred);
//        answers.add(carrying_ten);
//        answers.add(ans_down_one);
//        answers.add(carrying_hundred);
//        answers.add(ans_down_ten);
//        answers.add(ans_down_thousand);
//        answers.add(ans_down_hundred);
//        answers.add(ans_one);
//        answers.add(ans_ten);
//        answers.add(ans_hundred);
//        answers.add(ans_thousand);
//        answers.add(ans_tenthousand);

//        answers.push(ans_tenthousand);
//        answers.push(ans_thousand);
//        answers.push(ans_hundred);
//        answers.push(ans_ten);
//        answers.push(ans_one);
//        answers.push(ans_down_hundred);
//        answers.push(ans_down_thousand);
//        answers.push(ans_down_ten);
//        answers.push(carrying_hundred);
//        answers.push(ans_down_one);
//        answers.push(carrying_ten);
//        answers.push(ans_top_hundred);
//        answers.push(ans_top_thousand);
//        answers.push(ans_top_ten);
//        answers.push(carrying_hundred);
//        answers.push(ans_top_one);
//        answers.push(carrying_ten);

        initOperands();

    }

    public void initOperands() {
        /*난수 테스트
        for (int i = 0; i < 100; i++) {
            top = (int) (Math.random() * 900) + 100;
            Log.v(LOG_TAG, String.valueOf(top));
        }*/

        top = (int) (Math.random() * 900) + 100;
//        Log.v(LOG_TAG, String.valueOf(top));

        down = (int) (Math.random() * 90) + 10;
//        Log.v(LOG_TAG, String.valueOf(down));

        topHundred = top / 100 % 10;
        topTen = top / 10 % 10;
        topOne = top % 10;
//        Log.v(LOG_TAG, String.valueOf(topHundred));
//        Log.v(LOG_TAG, String.valueOf(topTen));
//        Log.v(LOG_TAG, String.valueOf(topOne));

        downTen = down / 10 % 10;
        downOne = down % 10;

//        Log.v(LOG_TAG, String.valueOf(downTen));
//        Log.v(LOG_TAG, String.valueOf(downOne));

        top_hundred.setText(String.valueOf(topHundred));
        top_ten.setText(String.valueOf(topTen));
        top_one.setText(String.valueOf(topOne));

        down_ten.setText(String.valueOf(downTen));
        down_one.setText(String.valueOf(downOne));

    }

//    private void stageOne(int num) {
//        int ans = topOne * downOne;
//        Log.v(LOG_TAG, String.valueOf(topOne) + '*' + String.valueOf(downOne) + '=' + String.valueOf(ans));
//        Log.v(LOG_TAG, String.valueOf(ans/10));
//
//        if (ans/10 != 0) {
//            if (num == ans/10) {
//                carrying_ten.setText(String.valueOf(num));
//            } else {
//                Toast toast = Toast.makeText(getApplicationContext(), num +" 은 잘못된 숫자입니다", Toast.LENGTH_LONG);
//                toast.setGravity(Gravity.CENTER, 0, 0);
//                toast.show();
//            }
//        } else {
//            carrying_ten.setText("0");
//        }
//    }

//    private void stageOne(int num, int operand1, int operand2, boolean last, TextView carrying) {
//        int ans = operand1 * operand2;
//        Log.v(LOG_TAG, String.valueOf(operand1) + '*' + String.valueOf(operand2) + '=' + String.valueOf(ans));
//        Log.v(LOG_TAG, String.valueOf(ans/10));
//
//        if (ans/10 != 0 || last) {
//            if (num == ans/10) {
//                carrying.setText(String.valueOf(num));
//            } else {
//                Toast toast = Toast.makeText(getApplicationContext(), num +" 은 잘못된 숫자입니다", Toast.LENGTH_LONG);
//                toast.setGravity(Gravity.CENTER, 0, 0);
//                toast.show();
//            }
//        } else {
//            carrying.setText("0");
//        }
//    }

//    private void stageOne() {
//        int ans = topOne * downOne;
//        Log.v(LOG_TAG, String.valueOf(topOne) + '*' + String.valueOf(downOne) + '=' + String.valueOf(ans));
//        Log.v(LOG_TAG, String.valueOf(ans/10));
//
//        if (ans == currentAnswer) {
//            if (ans/10 <= 0) {
//                carrying_ten.setText("0");
//                ans_top_one.setText(String.valueOf(currentAnswer));
//            } else {
//                carrying_ten.setText(String.valueOf(ans/10%10));
//                ans_top_one.setText(String.valueOf(ans%10));
//            }
//        } else {
//            Toast toast = Toast.makeText(getApplicationContext(), currentAnswer +" 은 잘못된 숫자입니다", Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.CENTER, 0, 0);
//            toast.show();
//        }
//    }

//    private void logic_carry(int num, int operand1, int operand2, boolean isCarrying) {
//        int ans = operand1 * operand2;
//        Log.v(LOG_TAG, String.valueOf(operand1) + '*' + String.valueOf(operand2) + '=' + String.valueOf(ans));
//        Log.v(LOG_TAG, String.valueOf(ans / 10));
//
//        if (ans <= 10) {
//            current.setText('0');
//            answers.remove(current);
//        } else if (ans / 10 == num){
//            current.setText(String.valueOf(num));
//        }
//
//        current = carrying_ten;
//        current.setText('?');
//
//
//
//    }

//
//    private void buttonListener(final int value) {
//        Button button_1 = (Button)findViewById(R.id.button_1);
//        button_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (value/10%10 == 1) {
//                    carrying_ten.setText("1");
//                }
//            }
//        });
//
//    }

//    private void stageOne() {
//        top_one.setTextColor(Color.RED);
//        down_one.setTextColor(Color.RED);
//
//        userInput();
//
//        operand1 = topOne;
//        operand2 = downOne;
//
////        if(result(topOne, downOne)) {
////            top_one.setTextColor(Color.GRAY);
////            down_one.setTextColor(Color.GRAY);
////            return;
////        }
//    }

//    private void nextStage() {
//        top_ten.setTextColor(Color.RED);
//
//        userInput();
//
//        operand1 = topTen;
//
//
//    }

    private void nextStage() {

        currentStage += 1;
        Log.v(LOG_TAG, "Current Stage : " + String.valueOf(currentStage));

//        userInput();

        switch (currentStage) {
            case 1:
                operand1TextView = top_one;
                operand2TextView = down_one;
                ans = topOne * downOne;
                input1TextView = carrying_ten;
                input2TextView = ans_top_one;
                break;

            case 2:
                operand1TextView = top_ten;
                operand2TextView = down_one;
                ans = topTen * downOne + Integer.parseInt(carrying_ten.getText().toString());
                input1TextView = carrying_hundred;
                input2TextView = ans_top_ten;
                break;

            case 3:
                operand1TextView = top_hundred;
                operand2TextView = down_one;
                ans = topHundred * downOne + Integer.parseInt(carrying_hundred.getText().toString());
                input1TextView = ans_top_thousand;
                input2TextView = ans_top_hundred;
                break;

            case 4:
                operand1TextView = top_one;
                operand2TextView = down_ten;
                ans = topOne * downTen;
                input1TextView = carrying_ten;
                input2TextView = ans_down_one;
                break;

            case 5:
                operand1TextView = top_ten;
                operand2TextView = down_ten;
                ans = topTen * downTen + Integer.parseInt(carrying_ten.getText().toString());
                input1TextView = carrying_hundred;
                input2TextView = ans_down_ten;
                break;

            case 6:
                operand1TextView = top_hundred;
                operand2TextView = down_ten;
                ans = topHundred * downTen + Integer.parseInt(carrying_hundred.getText().toString());
                input1TextView = ans_down_thousand;
                input2TextView = ans_down_hundred;
                break;

            case 7:
                operand1TextView = ans_top_one;
                operand2TextView = null;
                ans = Integer.parseInt(ans_top_one.getText().toString());
                input1TextView = ans_one;
                input2TextView = null;
                break;

            case 8:
                operand1TextView = ans_top_ten;
                operand2TextView = ans_down_one;
                ans = Integer.parseInt(ans_top_ten.getText().toString())
                        + Integer.parseInt(ans_down_one.getText().toString())
                        + totalCarry;
                totalCarry = 0;
                input1TextView = ans_ten;
                input2TextView = null;
                break;

            case 9:
                operand1TextView = ans_top_hundred;
                operand2TextView = ans_down_ten;
                ans = Integer.parseInt(ans_top_hundred.getText().toString())
                        + Integer.parseInt(ans_down_ten.getText().toString())
                        + totalCarry;
                totalCarry = 0;
                input1TextView = ans_hundred;
                input2TextView = null;
                break;

            case 10:
                operand1TextView = ans_top_thousand;
                operand2TextView = ans_down_hundred;
                ans = Integer.parseInt(ans_top_thousand.getText().toString())
                        + Integer.parseInt(ans_down_hundred.getText().toString())
                        + totalCarry;
                totalCarry = 0;
                input1TextView = ans_thousand;
                input2TextView = null;
                break;

            case 11:
                operand2TextView = null;
                operand1TextView = ans_down_thousand;
                ans = Integer.parseInt(ans_down_thousand.getText().toString()) + totalCarry;
                totalCarry = 0;
                input1TextView = ans_tenthousand;
                input2TextView = null;
                break;

            default:
                break;

        }

        if (operand1TextView != null) {
            operand1TextView.setTextColor(Color.RED);
        }
        if (operand2TextView != null) {
            operand2TextView.setTextColor(Color.RED);
        }
        if (input1TextView != null) {
            input1TextView.setText("A");
        }
        if (input2TextView != null) {
            input2TextView.setText("B");
        }


        if (currentStage >= 7 && ans >= 10) {
            totalCarry = ans / 10;
            ans = ans % 10;
        }

    }

    private boolean result() {

//        int ans = operand1 * operand2;
//        Log.v(LOG_TAG, String.valueOf(operand1) + '*' + String.valueOf(operand2) + '=' + String.valueOf(ans));

        int temp = 0, temp1 = 0, temp2 = 0;

        if (currentStage < 7) {

            try {
                temp1 = Integer.parseInt(input1TextView.getText().toString());
                Log.v(LOG_TAG, "temp1 : " + String.valueOf(temp1));
            } catch(NumberFormatException nfe) {
                return wrongAnswer(input1TextView.getText().toString());
            }

            try {
                temp2 = Integer.parseInt(input2TextView.getText().toString());
                Log.v(LOG_TAG, "temp2 : " + String.valueOf(temp1));
            } catch(NumberFormatException nfe) {
                return wrongAnswer(input2TextView.getText().toString());
            }

//            temp2 = Integer.parseInt(input2TextView.getText().toString());
//            Log.v(LOG_TAG, "temp2 : " + String.valueOf(temp2));

            temp = temp1 * 10 + temp2;
            Log.v(LOG_TAG, "temp : " + String.valueOf(temp));

        } else {
            if (input1TextView != null) {
                temp = Integer.parseInt(input1TextView.getText().toString());
                Log.v(LOG_TAG, "ans : " + String.valueOf(ans));
                Log.v(LOG_TAG, "temp : " + String.valueOf(temp));

            }
//            if (inputTextView2 != null) {
//                temp2 = Integer.parseInt(inputTextView2.getText().toString());
//                Log.v(LOG_TAG, "temp2 : " + String.valueOf(temp2));
//
//            }
//            temp = temp1 + temp2;
//            Log.v(LOG_TAG, "temp : " + String.valueOf(temp));

        }

        if (ans == temp) {
            Toast toast = Toast.makeText(getApplicationContext(), "딩동댕", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            if (operand1TextView != null) {
                operand1TextView.setTextColor(Color.GRAY);
            }

            if (operand2TextView != null) {
                operand2TextView.setTextColor(Color.GRAY);
            }

            if (currentStage == 11) {
                finalStage();
            }
            return true;

        } else {
            return wrongAnswer(String.valueOf(temp));
        }
    }

    private boolean wrongAnswer(String temp) {
        Toast toast = Toast.makeText(getApplicationContext(), temp + "는 틀렸어. 바보야.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        if (input1TextView != null) {
            input1TextView.setText("A");
        }
        if (input2TextView != null) {
            input2TextView.setText("B");
        }
        carrying = true;
        return false;
    }

    private void finalStage() {
        Toast toastR = Toast.makeText(getApplicationContext(), "축하합니다!", Toast.LENGTH_LONG);
        toastR.setGravity(Gravity.CENTER, 0, 0);
        toastR.show();

        currentStage = 0;
        totalCarry = 0;
        carrying = true;

        top_hundred.setText(String.valueOf("0"));
        top_ten.setText(String.valueOf("0"));
        top_one.setText(String.valueOf("0"));

        down_ten.setText(String.valueOf("0"));
        down_one.setText(String.valueOf("0"));

        initOperands();
        initNumbers();
    }

    private void initNumbers() {

        if (operand1TextView != null) {
            operand1TextView.setTextColor(Color.GRAY);
        }
        if (operand2TextView != null) {
            operand2TextView.setTextColor(Color.GRAY);
        }

        carrying_hundred.setText(String.valueOf("0"));
        carrying_ten.setText(String.valueOf("0"));

        ans_top_one.setText(String.valueOf("0"));
        ans_top_ten.setText(String.valueOf("0"));
        ans_top_hundred.setText(String.valueOf("0"));
        ans_top_thousand.setText(String.valueOf("0"));

        ans_down_one.setText(String.valueOf("0"));
        ans_down_ten.setText(String.valueOf("0"));
        ans_down_hundred.setText(String.valueOf("0"));
        ans_down_thousand.setText(String.valueOf("0"));

        ans_one.setText(String.valueOf("0"));
        ans_ten.setText(String.valueOf("0"));
        ans_hundred.setText(String.valueOf("0"));
        ans_thousand.setText(String.valueOf("0"));
        ans_tenthousand.setText(String.valueOf("0"));

    }

//    private void userInput() {
//        currentTextViewA = (TextView) answers.pop();
//        currentTextViewB = (TextView) answers.pop();
//        currentTextViewA.setText("A");
//        currentTextViewB.setText("B");
//    }
//int ans = topOne * downOne;
//        Log.v(LOG_TAG, String.valueOf(topOne) + '*' + String.valueOf(downOne) + '=' + String.valueOf(ans));
//        Log.v(LOG_TAG, String.valueOf(ans/10));
//
//        if (ans == currentAnswer) {
//            if (ans/10 <= 0) {
//                carrying_ten.setText("0");
//                ans_top_one.setText(String.valueOf(currentAnswer));
//            } else {
//                carrying_ten.setText(String.valueOf(ans/10%10));
//                ans_top_one.setText(String.valueOf(ans%10));
//            }
//        } else {
//            Toast toast = Toast.makeText(getApplicationContext(), currentAnswer +" 은 잘못된 숫자입니다", Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.CENTER, 0, 0);
//            toast.show();
//        }

//    private void buttonClicked(int num) {
//
////        if (currentAnswer < 10) {
////            currentAnswer = currentAnswer * 10 + num;
////            Log.v(LOG_TAG, "currentAnswer : " + String.valueOf(currentAnswer));
//
//        if (currentAnswer < 10) {
//            if (num == 0) {
//                currentTextViewA.setText("0");
//                currentAnswer = 10;
//                Log.v(LOG_TAG, "currentAnswer1 : " + String.valueOf(currentAnswer));
//            } else {
//                currentTextViewA.setText(String.valueOf(num));
//                currentAnswer = num * 10;
//                Log.v(LOG_TAG, "currentAnswer2 : " + String.valueOf(currentAnswer));
//            }
//        } else {
//            currentTextViewB.setText(String.valueOf(num));
//            currentAnswer = 0;
//            Log.v(LOG_TAG, "currentAnswer3 : " + String.valueOf(currentAnswer));
//        }
//    }

//    private void buttonClicked(int num) {
//        if (currentStage < 7) {
//            if (carrying) {
//                if (num == 0) {
//                    input1TextView.setText("0");
//                    carrying = false;
//                } else {
//                    input1TextView.setText(String.valueOf(num));
//                    carrying = false;
//                }
//            } else {
//                input2TextView.setText(String.valueOf(num));
//                carrying = true;
//            }
//        } else {
//            input1TextView.setText(String.valueOf(num));
//        }
//    }

    private void buttonClicked(int num) {
        if (currentStage < 7) {
            if (carrying) {
                input1TextView.setText(String.valueOf(num));
                carrying = false;
                } else {
                input2TextView.setText(String.valueOf(num));
                carrying = true;
            }
        } else {
            input1TextView.setText(String.valueOf(num));
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button_1:
                buttonClicked(1);
                break;
            case R.id.button_2:
                buttonClicked(2);
                break;
            case R.id.button_3:
                buttonClicked(3);
                break;
            case R.id.button_4:
                buttonClicked(4);
                break;
            case R.id.button_5:
                buttonClicked(5);
                break;
            case R.id.button_6:
                buttonClicked(6);
                break;
            case R.id.button_7:
                buttonClicked(7);
                break;
            case R.id.button_8:
                buttonClicked(8);
                break;
            case R.id.button_9:
                buttonClicked(9);
                break;
            case R.id.button_0:
                buttonClicked(0);
                break;
            case R.id.button_clear:
                currentStage = 0;
                totalCarry = 0;
                carrying = true;
                initNumbers();
                nextStage();
                break;
            case R.id.button_enter:
                if (result()) {
                    nextStage();
                    break;
                }
            default:
                break;
        }

//        stageOne(num, topOne, downOne, false, carrying_ten);
//        stageTwo(num, topOne, downOne, false, ans_top_one);


    }


}

package com.shinjaehun.suksuk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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

    public int operand1, operand2;
    TextView currentTextViewA, currentTextViewB;

//    private ArrayList<TextView> numbers = new ArrayList<TextView>(17);
    Stack numbers = new Stack<TextView>();
    boolean isCarrying = true;
    //boolean userInput = false;
    int currentAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        operand();

//        if (userInput) {
//            stageOne();
//        }

    }

    public void init() {

        carrying_hundred = (TextView)findViewById(R.id.carrying_hundred);
        carrying_ten = (TextView)findViewById(R.id.carrying_ten);

        top_hundred = (TextView)findViewById(R.id.top_hundred);
        top_ten =  (TextView)findViewById(R.id.top_ten);
        top_one = (TextView)findViewById(R.id.top_one);

        down_ten = (TextView)findViewById(R.id.down_ten);
        down_one = (TextView)findViewById(R.id.down_one);

        ans_top_one = (TextView)findViewById(R.id.ans_top_oen);
        ans_top_ten = (TextView)findViewById(R.id.ans_top_ten);
        ans_top_hundred = (TextView)findViewById(R.id.ans_top_hundred);
        ans_top_thousand = (TextView)findViewById(R.id.ans_top_thousand);

        ans_down_one = (TextView)findViewById(R.id.ans_down_one);
        ans_down_ten = (TextView)findViewById(R.id.ans_down_ten);
        ans_down_hundred = (TextView)findViewById(R.id.ans_down_hundred);
        ans_down_thousand = (TextView)findViewById(R.id.ans_down_thousand);

        ans_one = (TextView)findViewById(R.id.ans_one);
        ans_ten = (TextView)findViewById(R.id.ans_ten);
        ans_hundred = (TextView)findViewById(R.id.ans_hundred);
        ans_thousand = (TextView)findViewById(R.id.ans_thousand);
        ans_tenthousand = (TextView)findViewById(R.id.ans_tenthousand);

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

//        numbers.add(carrying_ten);
//        numbers.add(ans_down_one);
//        numbers.add(carrying_hundred);
//        numbers.add(ans_top_ten);
//        numbers.add(ans_top_thousand);
//        numbers.add(ans_top_hundred);
//        numbers.add(carrying_ten);
//        numbers.add(ans_down_one);
//        numbers.add(carrying_hundred);
//        numbers.add(ans_down_ten);
//        numbers.add(ans_down_thousand);
//        numbers.add(ans_down_hundred);
//        numbers.add(ans_one);
//        numbers.add(ans_ten);
//        numbers.add(ans_hundred);
//        numbers.add(ans_thousand);
//        numbers.add(ans_tenthousand);

        numbers.push(ans_tenthousand);
        numbers.push(ans_thousand);
        numbers.push(ans_hundred);
        numbers.push(ans_ten);
        numbers.push(ans_one);
        numbers.push(ans_down_hundred);
        numbers.push(ans_down_thousand);
        numbers.push(ans_down_ten);
        numbers.push(carrying_hundred);
        numbers.push(ans_down_one);
        numbers.push(carrying_ten);
        numbers.push(ans_top_hundred);
        numbers.push(ans_top_thousand);
        numbers.push(ans_top_ten);
        numbers.push(carrying_hundred);
        numbers.push(ans_top_one);
        numbers.push(carrying_ten);

        initNumbers();

    }

    public void initNumbers() {
        /*난수 테스트
        for (int i = 0; i < 100; i++) {
            top = (int) (Math.random() * 900) + 100;
            Log.v(LOG_TAG, String.valueOf(top));
        }*/

        top = (int) (Math.random() * 900) + 100;
//        Log.v(LOG_TAG, String.valueOf(top));

        down = (int)(Math.random() * 90) + 10;
//        Log.v(LOG_TAG, String.valueOf(down));

        topHundred = top/100%10;
        topTen = top/10%10;
        topOne = top%10;
//        Log.v(LOG_TAG, String.valueOf(topHundred));
//        Log.v(LOG_TAG, String.valueOf(topTen));
//        Log.v(LOG_TAG, String.valueOf(topOne));

        downTen = down/10%10;
        downOne = down%10;

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

    private void stageTwo(int num, int operand1, int operand2, boolean last, TextView value) {
        int ans = operand1 * operand2;
        Log.v(LOG_TAG, String.valueOf(operand1) + '*' + String.valueOf(operand2) + '=' + String.valueOf(ans));
        Log.v(LOG_TAG, String.valueOf(ans / 10));

        if (ans % 10 == num) {
            value.setText(String.valueOf(num));
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), num + " 은 잘못된 숫자입니다", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

//    private void logic_carry(int num, int operand1, int operand2, boolean isCarrying) {
//        int ans = operand1 * operand2;
//        Log.v(LOG_TAG, String.valueOf(operand1) + '*' + String.valueOf(operand2) + '=' + String.valueOf(ans));
//        Log.v(LOG_TAG, String.valueOf(ans / 10));
//
//        if (ans <= 10) {
//            current.setText('0');
//            numbers.remove(current);
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

    private void operand() {
        currentTextViewA = (TextView)numbers.pop();
        currentTextViewB = (TextView)numbers.pop();
        currentTextViewA.setText("A");
        currentTextViewB.setText("?");

    }
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

    private void buttonClicked(int num) {
        if (currentAnswer < 10) {
            currentAnswer = currentAnswer * 10 + num;
            currentTextViewA.setText(String.valueOf(num));
            Log.v(LOG_TAG, "currentAnswer1 : " + String.valueOf(currentAnswer));


//        if (currentAnswer < 10) {
//            currentAnswer = currentAnswer * 10 + num;
//            Log.v(LOG_TAG, "currentAnswer : " + String.valueOf(currentAnswer));

        } else {
            currentTextViewB.setText(String.valueOf(num));
            Log.v(LOG_TAG, "currentAnswer2 : " + String.valueOf(currentAnswer));
            //구현이 끝난 다음
            currentAnswer = 0;
            Log.v(LOG_TAG, "currentAnswer3 : " + String.valueOf(currentAnswer));
        }
    }


    public void onClick(View view) {
        int num = 0;

        switch (view.getId()) {

            case R.id.button_1:
                Log.v(LOG_TAG, "Button 1 Clicked");
                num = 1;
                break;
            case R.id.button_2:
                Log.v(LOG_TAG, "Button 2 Clicked");
                num = 2;
                break;
            case R.id.button_3:
                Log.v(LOG_TAG, "Button 3 Clicked");
                num = 3;
                break;
            case R.id.button_4:
                num = 4;
                break;
            case R.id.button_5:
                num = 5;
                break;
            case R.id.button_6:
                num = 6;
                break;
            case R.id.button_7:
                num = 7;
                break;
            case R.id.button_8:
                num = 8;
                break;
            case R.id.button_9:
                num = 9;
                break;
            case R.id.button_0:
                num = 0;
                break;
            case R.id.button_clear:
                break;
            case R.id.button_enter:
                break;
            default:
                num = -1;
                break;
        }

//        stageOne(num, topOne, downOne, false, carrying_ten);
//        stageTwo(num, topOne, downOne, false, ans_top_one);

        buttonClicked(num);

    }




}

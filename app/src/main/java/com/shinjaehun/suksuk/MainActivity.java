package com.shinjaehun.suksuk;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    TextView operand1TextView, operand2TextView, input1TextView, input2TextView;

    boolean carrying = true;
    boolean zeroCarrying = false;
    int currentStage = 0;
    int ans = 0;
    int totalCarry = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        nextStage();
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

        initOperands();
    }

    public void initOperands() {
        /*난수 테스트
        for (int i = 0; i < 100; i++) {
            top = (int) (Math.random() * 900) + 100;
            Log.v(LOG_TAG, String.valueOf(top));
        }*/

        top = (int) (Math.random() * 900) + 100;
        down = (int) (Math.random() * 90) + 10;

//        top = 90;
//        down = 57;

        topHundred = top / 100 % 10;
        topTen = top / 10 % 10;
        topOne = top % 10;

        downTen = down / 10 % 10;
        downOne = down % 10;

        top_hundred.setText(String.valueOf(topHundred));
        top_ten.setText(String.valueOf(topTen));
        top_one.setText(String.valueOf(topOne));

        down_ten.setText(String.valueOf(downTen));
        down_one.setText(String.valueOf(downOne));
    }

    private void nextStage() {
        currentStage += 1;
        Log.v(LOG_TAG, "Current Stage : " + String.valueOf(currentStage));

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
                input1TextView = ans_ten;
                input2TextView = null;
                break;

            case 9:
                operand1TextView = ans_top_hundred;
                operand2TextView = ans_down_ten;
                ans = Integer.parseInt(ans_top_hundred.getText().toString())
                        + Integer.parseInt(ans_down_ten.getText().toString())
                        + totalCarry;
                input1TextView = ans_hundred;
                input2TextView = null;
                break;

            case 10:
                operand1TextView = ans_top_thousand;
                operand2TextView = ans_down_hundred;
                ans = Integer.parseInt(ans_top_thousand.getText().toString())
                        + Integer.parseInt(ans_down_hundred.getText().toString())
                        + totalCarry;
                input1TextView = ans_thousand;
                input2TextView = null;
                break;

            case 11:
                operand1TextView = ans_down_thousand;
                operand2TextView = null;
                ans = Integer.parseInt(ans_down_thousand.getText().toString()) + totalCarry;
                input1TextView = ans_tenthousand;
                input2TextView = null;
                break;

            default:
                break;
        }
        totalCarry = 0;

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

//        if (currentStage >= 7 && ans >= 10) {
//            totalCarry = ans / 10;
//            ans = ans % 10;
//        }


//        if (currentStage < 7 && (Integer.parseInt(operand1TextView.getText().toString()) == 0
//                || Integer.parseInt(operand2TextView.getText().toString()) == 0)) {
//            if (Integer.parseInt(input1TextView.getText().toString()) != 0) {
//                input2TextView.setText(input1TextView.getText().toString());
//            } else {
//                input1TextView.setText("0");
//                input2TextView.setText("0");
//            }
//            operand1TextView.setTextColor(Color.GRAY);
//            operand2TextView.setTextColor(Color.GRAY);
//            nextStage();
//        }
        if (currentStage < 7) {
            if (Integer.parseInt(operand1TextView.getText().toString()) == 0 || ans < 10) {
                input1TextView.setText("0");
                zeroCarrying = true;
//            operand1TextView.setTextColor(Color.GRAY);
//            operand2TextView.setTextColor(Color.GRAY);
//            nextStage();
            } else {
                zeroCarrying = false;
            }
        } else {
            totalCarry = ans / 10;
            ans = ans % 10;
        }
    }

//    private void buttonClicked(int num) {
//        if (currentStage < 7) {
//            if (carrying) {
//                input1TextView.setText(String.valueOf(num));
//                carrying = false;
//            } else {
//                input2TextView.setText(String.valueOf(num));
//                carrying = true;
//            }
//        } else {
//            input1TextView.setText(String.valueOf(num));
//        }
//        Log.v(LOG_TAG, "zeroCarrying : " + String.valueOf(zeroCarrying));
//        Log.v(LOG_TAG, "carrying : " + String.valueOf(carrying));
//        Log.v(LOG_TAG, "totalCarry : " + String.valueOf(totalCarry));
//    }

    private void buttonClicked(int num) {
        if (currentStage < 7) {
            if (zeroCarrying) {
                input2TextView.setText(String.valueOf(num));
            } else {
                if (carrying) {
                    input1TextView.setText(String.valueOf(num));
                    carrying = false;
                } else {
                    input2TextView.setText(String.valueOf(num));
                    carrying = true;
                }
            }
        } else {
            input1TextView.setText(String.valueOf(num));
        }
        Log.v(LOG_TAG, "zeroCarrying : " + String.valueOf(zeroCarrying));
        Log.v(LOG_TAG, "carrying : " + String.valueOf(carrying));
        Log.v(LOG_TAG, "totalCarry : " + String.valueOf(totalCarry));
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
    }

    private boolean result() {
        int temp = 0, temp1 = 0, temp2 = 0;

        if (currentStage < 7) {
            try {
                temp1 = Integer.parseInt(input1TextView.getText().toString());
                Log.v(LOG_TAG, "temp1 : " + String.valueOf(temp1));
            } catch (NumberFormatException nfe) {
                return wrongAnswer(input1TextView.getText().toString());
            }

            try {
                temp2 = Integer.parseInt(input2TextView.getText().toString());
                Log.v(LOG_TAG, "temp2 : " + String.valueOf(temp2));
            } catch (NumberFormatException nfe) {
                return wrongAnswer(input2TextView.getText().toString());
            }

            temp = temp1 * 10 + temp2;
            Log.v(LOG_TAG, "temp : " + String.valueOf(temp));
        } else {
            if (input1TextView != null) {
                try {
                    temp = Integer.parseInt(input1TextView.getText().toString());
                    Log.v(LOG_TAG, "ans : " + String.valueOf(ans));
                    Log.v(LOG_TAG, "temp : " + String.valueOf(temp));
                } catch (NumberFormatException nfe) {
                    return wrongAnswer(input1TextView.getText().toString());
                }
            }
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

        if (zeroCarrying) {
            input1TextView.setText("0");
            carrying = false;
            return false;
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
}

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public int top, down;
    public int topHundred, topTen, topOne;
    public int downTen, downOne;

    TextView carrying_hundred, carrying_ten;
    TextView top_hundred, top_ten, top_one;
    TextView down_ten, down_one;

    TextView ans_top_one;

    boolean userInput = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carrying_hundred = (TextView)findViewById(R.id.carrying_hundred);
        carrying_ten = (TextView)findViewById(R.id.carrying_ten);

        top_hundred = (TextView)findViewById(R.id.top_hundred);
        top_ten =  (TextView)findViewById(R.id.top_ten);
        top_one = (TextView)findViewById(R.id.top_one);

        down_ten = (TextView)findViewById(R.id.down_ten);
        down_one = (TextView)findViewById(R.id.down_one);

        ans_top_one = (TextView)findViewById(R.id.ans_top_oen);

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


        initNumbers();

//        if (userInput) {
//            stageOne();
//        }

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

        userInput = true;
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

    private void stageOne(int num, int operand1, int operand2, boolean last, TextView carrying) {
        int ans = operand1 * operand2;
        Log.v(LOG_TAG, String.valueOf(operand1) + '*' + String.valueOf(operand2) + '=' + String.valueOf(ans));
        Log.v(LOG_TAG, String.valueOf(ans/10));

        if (ans/10 != 0 || last) {
            if (num == ans/10) {
                carrying.setText(String.valueOf(num));
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), num +" 은 잘못된 숫자입니다", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        } else {
            carrying.setText("0");
        }
    }

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
            default:
                num = -1;
                break;
        }

        stageOne(num, topOne, downOne, false, carrying_ten);
        stageTwo(num, topOne, downOne, false, ans_top_one);

    }


}

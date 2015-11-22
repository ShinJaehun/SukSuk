package com.shinjaehun.suksuk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public int top, down;
    public int topHundred, topTen, topOne;
    public int downTen, downOne;

    TextView carrying_hundred, carrying_ten;
    TextView top_hundred, top_ten, top_one;
    TextView down_ten, down_one;

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



        initNumbers();
        if (userInput) {
            stageOne();
        }

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

    private void stageOne() {
        int multiply = topOne * downOne;
        Log.v(LOG_TAG, String.valueOf(multiply));

        buttonListener(multiply);

    }

    private void buttonListener(final int value) {
        Button button_1 = (Button)findViewById(R.id.button_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (value/10%10 == 1) {
                    carrying_ten.setText("1");
                }
            }
        });

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_1:
                Log.v(LOG_TAG, "Button 1 Clicked");
                break;
        }

    }


}

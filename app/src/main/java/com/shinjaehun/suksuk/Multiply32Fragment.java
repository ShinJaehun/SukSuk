package com.shinjaehun.suksuk;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by shinjaehun on 2016-04-19.
 */
public class Multiply32Fragment extends Fragment implements NumberpadClickListener {

    private static final String LOG_TAG = Multiply32Fragment.class.getSimpleName();

    //private NumberpadFragment numberpadFragment;
    private Context mContext = null;

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

    //곱셈 결과를 입력할 순서 저장
    boolean carrying = true;

    //세 자리 수 중 하나가 0이거나 곱셈 결과가 받아올림이 없는 경우를 처리할 스위치
    boolean zeroCarrying = false;

    //현재 과정
    int currentStage = 0;

    //곱셈 결과
    int ans = 0;

    //일의 자리, 십의 자리 곱셈 결과를 더할 때 받아올림 값 저장
    int totalCarry = 0;


    public void startPractice() {
        initOperands();
        nextStage();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_multiply32, container);

        carrying_hundred = (TextView)v.findViewById(R.id.carrying_hundred);
        carrying_ten = (TextView)v.findViewById(R.id.carrying_ten);

        top_hundred = (TextView)v.findViewById(R.id.top_hundred);
        top_ten = (TextView)v.findViewById(R.id.top_ten);
        top_one = (TextView)v.findViewById(R.id.top_one);

        down_ten = (TextView)v.findViewById(R.id.down_ten);
        down_one = (TextView)v.findViewById(R.id.down_one);

        ans_top_one = (TextView)v.findViewById(R.id.ans_top_oen);
        ans_top_ten = (TextView)v.findViewById(R.id.ans_top_ten);
        ans_top_hundred = (TextView)v.findViewById(R.id.ans_top_hundred);
        ans_top_thousand = (TextView)v.findViewById(R.id.ans_top_thousand);

        ans_down_one = (TextView)v.findViewById(R.id.ans_down_one);
        ans_down_ten = (TextView)v.findViewById(R.id.ans_down_ten);
        ans_down_hundred = (TextView)v.findViewById(R.id.ans_down_hundred);
        ans_down_thousand = (TextView)v.findViewById(R.id.ans_down_thousand);

        ans_one = (TextView)v.findViewById(R.id.ans_one);
        ans_ten = (TextView)v.findViewById(R.id.ans_ten);
        ans_hundred = (TextView)v.findViewById(R.id.ans_hundred);
        ans_thousand = (TextView)v.findViewById(R.id.ans_thousand);
        ans_tenthousand = (TextView)v.findViewById(R.id.ans_tenthousand);

        return v;
    }


    public void initOperands() {
        /* 피연산자 생성 */
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

        //피연산자 표시
        top_hundred.setText(String.valueOf(topHundred));
        top_ten.setText(String.valueOf(topTen));
        top_one.setText(String.valueOf(topOne));

        down_ten.setText(String.valueOf(downTen));
        down_one.setText(String.valueOf(downOne));
    }

    private void nextStage() {
        currentStage += 1;
        Log.v(LOG_TAG, "Current Stage : " + String.valueOf(currentStage));

        //currentStage에 따라 곱셈할 자리수, 곱셈 결과, 입력할 자리수 지정
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

        //일의 자리, 십의 자리 곱셈 결과에 받아올린 값을 더한 후 totalCarry는 다시 0으로
        totalCarry = 0;

        //곱셈할 각 자리수를 빨간색으로 표시
        if (operand1TextView != null) {
            operand1TextView.setTextColor(Color.RED);
        }
        if (operand2TextView != null) {
            operand2TextView.setTextColor(Color.RED);
        }

        //입력할 텍스트 뷰를 임시로 'A'와 'B'로 표시
        if (input1TextView != null) {
            input1TextView.setText("A");
        }
        if (input2TextView != null) {
            input2TextView.setText("B");
        }

        //일의 자리, 십의 자리 곱셈 결과를 더하는 과정
        if (currentStage < 7) {

            //세 자리 수 중 하나가 0이거나 곱셈 결과가 받아올림이 없는 경우
            if (Integer.parseInt(operand1TextView.getText().toString()) == 0 || ans < 10) {
                input1TextView.setText("0");
                zeroCarrying = true;
            } else {
                zeroCarrying = false;
            }

        } else {

            //곱셈 결과에서 받아올림이 발생했을 때 값 저장
            totalCarry = ans / 10;
            //곱셈 결과 값 다시 저장
            ans = ans % 10;
        }
    }

    private boolean result() {
        int temp = 0, temp1 = 0, temp2 = 0;

        //각 자리수를 곱하는 과정 처리
        if (currentStage < 7) {

            //temp1에 사용자의 첫번째 입력 값 저장
            try {
                temp1 = Integer.parseInt(input1TextView.getText().toString());
                Log.v(LOG_TAG, "temp1 : " + String.valueOf(temp1));
            } catch (NumberFormatException nfe) {
                return wrongAnswer(input1TextView.getText().toString());
            }

            //temp2에 사용자의 두번째 입력 값 저장
            try {
                temp2 = Integer.parseInt(input2TextView.getText().toString());
                Log.v(LOG_TAG, "temp2 : " + String.valueOf(temp2));
            } catch (NumberFormatException nfe) {
                return wrongAnswer(input2TextView.getText().toString());
            }

            //사용자가 입력한 값을 바탕으로 곱셈 결과 temp에 저장
            temp = temp1 * 10 + temp2;
            Log.v(LOG_TAG, "temp : " + String.valueOf(temp));

            //일의 자리, 십의 자리 곱셈 결과를 더하는 과정 처리
        } else {

            //사용자가 입력한 값을 temp에 저장
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

        //정답처리
        if (ans == temp) {
            Toast toast = Toast.makeText(mContext, "딩동댕", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            //연산했던 자리수를 다시 회색으로 되돌리기
            if (operand1TextView != null) {
                operand1TextView.setTextColor(Color.GRAY);
            }
            if (operand2TextView != null) {
                operand2TextView.setTextColor(Color.GRAY);
            }

            //모든 연산이 끝나면
            if (currentStage == 11) {
                finalStage();
            }
            return true;

            //오답처리
        } else {
            return wrongAnswer(String.valueOf(temp));
        }
    }

    private boolean wrongAnswer(String temp) {
        Toast toast = Toast.makeText(mContext, temp + "는 틀렸어. 바보야.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        //사용자가 입력할 텍스트 뷰를 다시 'A'와 'B'로 되돌림
        if (input1TextView != null) {
            input1TextView.setText("A");
        }
        if (input2TextView != null) {
            input2TextView.setText("B");
        }

        //받아올림이 없는 곱셈에서는 input1TextView를 0으로 되돌림
        if (zeroCarrying) {
            input1TextView.setText("0");
            return false;
        }

        //다시 첫번째 입력으로 되돌림
        carrying = true;
        return false;
    }

    private void finalStage() {
        /* finalStage()에서 값을 초기화한 후 다시 nextStage()를 호출하지는 않는데
        * 어차피 입력 버튼을 누르면 nextStage()를 호출하기 때문 */

        Toast toastR = Toast.makeText(mContext, "축하합니다!", Toast.LENGTH_LONG);
        toastR.setGravity(Gravity.CENTER, 0, 0);
        toastR.show();

        //모든 변수 초기화
        currentStage = 0;
        totalCarry = 0;
        carrying = true;
        ans = 0;

        //피연산자 초기화
        initOperands();
        //피연산자를 제외한 나머지 모든 숫자 초기화
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

    @Override
    public void onNumberClicked(int number) {

                /* 버튼이 클릭되었을 때 처리 */

        //각 자리수를 곱하는 과정 처리
        if (currentStage < 7) {
            //세 자리 수 중 하나가 0이거나 곱셈 결과가 받아올림이 없는 경우 0을 입력하는 수고를 덜도록
            //사용자는 input2TextView에만 값을 입력함
            if (zeroCarrying) {
                input2TextView.setText(String.valueOf(number));
            } else {
                //사용자 입력 처리 : 첫번째 입력인 경우 input1TextView에 값을 입력함
                if (carrying) {
                    input1TextView.setText(String.valueOf(number));
                    carrying = false;
                } else {
                    //두번째 입력인 경우 input2TextView에 값을 입력함
                    input2TextView.setText(String.valueOf(number));
                    carrying = true;
                }
            }
        } else {
            //일의 자리, 십의 자리 곱셈 결과를 더하는 과정 처리 : input1TextView에만 값을 입력함
            input1TextView.setText(String.valueOf(number));
        }
        Log.v(LOG_TAG, "zeroCarrying : " + String.valueOf(zeroCarrying));
        Log.v(LOG_TAG, "carrying : " + String.valueOf(carrying));
        Log.v(LOG_TAG, "totalCarry : " + String.valueOf(totalCarry));


    }

    public void onClearClicked() {
        currentStage = 0;
        totalCarry = 0;
        carrying = true;
        ans = 0;
        initNumbers();
        nextStage();
    }

    public void onOKClicked() {
        if (result()) {
            nextStage();
        }
    }

}

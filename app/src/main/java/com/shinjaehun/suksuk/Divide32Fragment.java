package com.shinjaehun.suksuk;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by shinjaehun on 2016-04-19.
 */
public class Divide32Fragment extends ProblemFragment implements NumberpadClickListener {

    private static final String LOG_TAG = Divide32Fragment.class.getSimpleName();

    private int divisor, dividend, quotient;
    private int dividendHundred, dividendTen, dividendOne;
    private int divisorTen, divisorOne;
    private int quotientTen, quotientOne;

    private View ans_first_line, ans_second_line;

    private TextView quotient_ten, quotient_one;

    private TextView carrying_divisor_ten;
    private TextView divisor_ten, divisor_one;

    private TextView carrying_dividend_hundred, carrying_dividend_ten_10, carrying_dividend_ten_1;
    private TextView carrying_dividend_one_10, carrying_dividend_one_1;
    private ImageView carrying_dividend_ten_cover;

//    private ImageView currentMark;

    private ImageView dividend_hundred_cover, dividend_ten_cover, dividend_one_cover;
    private TextView dividend_hundred, dividend_ten, dividend_one;

    private TextView first_multiply_hundred, first_multiply_ten, first_multiply_one;

    private LinearLayout carrying_l2_first_subtract_hundred, carrying_l2_first_subtract_ten, carrying_l2_first_subtract_one;
    private ImageView carrying_first_subtract_ten_cover;
    private ImageView first_subtract_hundred_cover, first_subtract_ten_cover, first_subtract_one_cover;
    private TextView carrying_first_subtract_hundred, carrying_first_subtract_ten_10, carrying_first_subtract_ten_1,
            carrying_first_subtract_one_10, carrying_first_subtract_one_1;
    private TextView carrying_l2_first_subtract_ten_10, carrying_l2_first_subtract_ten_1 ;

    private TextView first_subtract_hundred, first_subtract_ten, first_subtract_one;
    private TextView second_multiply_hundred, second_multiply_ten, second_multiply_one;
    private TextView remainder_ten, remainder_one;

//    private TextView operand1TextView, operand2TextView, operand3TextView, operand4TextView, operand5TextView, operand6TextView;
//    private TextView input1TextView, input2TextView, input3TextView;
//
//    private Button help;

//    //몫이 두 자리 수인 경우 체크하는 스위치
//    private boolean isFullDivide = true;

//    //세개의 inputTextView 입력을 받기 위한 스위치
//    private int inputEntry = 0;
//    private int inputNext = 0;
//
//    private boolean carrying = true;
//
//    //여기서 multiInput은 입력할 inputTextView가 하나인지 둘인지를 결정하는 스위치
//    //multiInput이 true이면 입력 하나만, true이면 입력 둘
//    private boolean multiInput = false;
//
//    //현재 과정
//    private int currentStage = 0;
//
//    //최종 단계인가?
//    private boolean isFinal = false;
//
//    //곱셈 결과
//    private int ans = 0;

    public void startPractice() {
        initOperands();
        nextStage();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_divide32, container, false);

        quotient_ten = (TextView)v.findViewById(R.id.quotient_ten);
        quotient_one = (TextView)v.findViewById(R.id.quotient_one);

        carrying_divisor_ten = (TextView)v.findViewById(R.id.carrying_divisor_ten);

        divisor_ten = (TextView)v.findViewById(R.id.divisor_ten);
        divisor_one = (TextView)v.findViewById(R.id.divisor_one);

        carrying_dividend_hundred = (TextView)v.findViewById(R.id.carrying_dividend_hundred);
        carrying_dividend_ten_10 = (TextView)v.findViewById(R.id.carrying_dividend_ten_10);
        carrying_dividend_ten_1 = (TextView)v.findViewById(R.id.carrying_dividend_ten_1);
        carrying_dividend_one_10 = (TextView)v.findViewById(R.id.carrying_dividend_one_10);
        carrying_dividend_one_1 = (TextView)v.findViewById(R.id.carrying_dividend_one_1);

        carrying_dividend_ten_cover = (ImageView)v.findViewById(R.id.carrying_dividend_ten_cover);

        dividend_hundred_cover = (ImageView)v.findViewById(R.id.dividend_hundred_cover);
        dividend_ten_cover = (ImageView)v.findViewById(R.id.dividend_ten_cover);
        dividend_one_cover = (ImageView)v.findViewById(R.id.dividend_one_cover);

        dividend_hundred = (TextView)v.findViewById(R.id.dividend_hundred);
        dividend_ten = (TextView)v.findViewById(R.id.dividend_ten);
        dividend_one = (TextView)v.findViewById(R.id.dividend_one);

        carrying_l2_first_subtract_hundred = (LinearLayout)v.findViewById(R.id.carrying_l2_first_subtract_hundred);
        carrying_l2_first_subtract_ten = (LinearLayout)v.findViewById(R.id.carrying_l2_first_subtract_ten);
        carrying_l2_first_subtract_one = (LinearLayout)v.findViewById(R.id.carrying_l2_first_subtract_one);

        carrying_l2_first_subtract_ten_10 = (TextView)v.findViewById(R.id.carrying_l2_first_subtract_ten_10);
        carrying_l2_first_subtract_ten_1 = (TextView)v.findViewById(R.id.carrying_l2_first_subtract_ten_1);

        carrying_first_subtract_hundred = (TextView)v.findViewById(R.id.carrying_first_subtract_hundred);
        carrying_first_subtract_ten_10 = (TextView)v.findViewById(R.id.carrying_first_subtract_ten_10);
        carrying_first_subtract_ten_1 = (TextView)v.findViewById(R.id.carrying_first_subtract_ten_1);
        carrying_first_subtract_one_10 = (TextView)v.findViewById(R.id.carrying_first_subtract_one_10);
        carrying_first_subtract_one_1 = (TextView)v.findViewById(R.id.carrying_first_subtract_one_1);

        carrying_first_subtract_ten_cover = (ImageView)v.findViewById(R.id.carrying_first_subtract_ten_cover);

        first_subtract_hundred_cover = (ImageView)v.findViewById(R.id.first_subtract_hundred_cover);
        first_subtract_ten_cover = (ImageView)v.findViewById(R.id.first_subtract_ten_cover);
        first_subtract_one_cover = (ImageView)v.findViewById(R.id.first_subtract_one_cover);

        first_multiply_hundred = (TextView)v.findViewById(R.id.first_multiply_hundred);
        first_multiply_ten = (TextView)v.findViewById(R.id.first_multiply_ten);
        first_multiply_one = (TextView)v.findViewById(R.id.first_multiply_one);

        ans_first_line = v.findViewById(R.id.ans_first_line);

        first_subtract_hundred= (TextView)v.findViewById(R.id.first_subtract_hundred);
        first_subtract_ten = (TextView)v.findViewById(R.id.first_subtract_ten);
        first_subtract_one = (TextView)v.findViewById(R.id.first_subtract_one);

        second_multiply_hundred = (TextView)v.findViewById(R.id.second_multiply_hundred);
        second_multiply_ten = (TextView)v.findViewById(R.id.second_multiply_ten);
        second_multiply_one = (TextView)v.findViewById(R.id.second_multiply_one);

        ans_second_line = v.findViewById(R.id.ans_second_line);

        remainder_ten = (TextView)v.findViewById(R.id.remainder_ten);
        remainder_one = (TextView)v.findViewById(R.id.remainder_one);

        help = (Button)v.findViewById(R.id.help);
        help.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
                intent.putExtra("help", "divide32");
                startActivity(intent);
            }
        });

        return v;

    }

    private void initOperands() {
        /* 피연산자 생성 */
        /*난수 테스트
        for (int i = 0; i < 100; i++) {
            top = (int) (Math.random() * 900) + 100;
            Log.v(LOG_TAG, String.valueOf(top));
        }*/

//        dividend = (int) (Math.random() * 900) + 100;
//        divisor = (int) (Math.random() * 90) + 10;

////
        int a = (int) (Math.random() * 90) + 10;
        int b = (int) (Math.random() * 90) + 10;

        if (a >= b) {
            dividend = a * 10 + (int)(Math.random() * 9);
            divisor = b;
        } else {
            dividend = b * 10 + (int)(Math.random() * 9);
            divisor = a;
        }

        dividend = 310;
        divisor = 21;

        quotient = dividend / divisor;

//        top = 854;
//        down = 57;

        dividendHundred = dividend / 100 % 10;
        dividendTen = dividend / 10 % 10;
        dividendOne = dividend % 10;

        divisorTen = divisor / 10 % 10;
        divisorOne = divisor % 10;

        quotientTen = quotient / 10 % 10;
        quotientOne = quotient % 10;

//        if (dividendHundred * 10 + dividendTen < divisor) {
//            isFullDivide = false;
//        }

        //피연산자 표시
        dividend_hundred.setText(String.valueOf(dividendHundred));
        dividend_ten.setText(String.valueOf(dividendTen));
        dividend_one.setText(String.valueOf(dividendOne));

        divisor_ten.setText(String.valueOf(divisorTen));
        divisor_one.setText(String.valueOf(divisorOne));

    }

    public void nextStage() {
        currentStage += 1;
        Log.v(LOG_TAG, "Current Stage : " + String.valueOf(currentStage));

        //currentStage에 따라 곱셈할 자리수, 곱셈 결과, 입력할 자리수 지정
        switch (currentStage) {
            case 1:
                operand1TextView = divisor_ten;
                operand2TextView = divisor_one;
                operand3TextView = dividend_hundred;
                operand4TextView = dividend_ten;

                //곱셈에 따라 여기서 operand5Text를 사용할 수도 있음
                operand5TextView = null;

                input1TextView = null;
                input2TextView = quotient_ten;

                ans = quotientTen;
//                multiInput = true;

                break;

            case 2:
//                //나누어지는 수 회색으로
//                dividend_hundred.setTextColor(Color.GRAY);
//                dividend_ten.setTextColor(Color.GRAY);
//
//                //나누는 수 십의 자리 회색으로
//                divisor_ten.setTextColor(Color.GRAY);

                operand1TextView = divisor_one;
                operand2TextView = quotient_ten;
                operand3TextView = null;
                operand4TextView = null;
                operand5TextView = null;


                ans = divisorOne * quotientTen;

                //곱셈과정에서 받아올림이 있는 경우와 없는 경우 처리
                if (ans < 10) {
                    input1TextView = null;
                } else {
                    input1TextView = carrying_divisor_ten;
                }
                input2TextView = first_multiply_ten;

                break;

            case 3:
//                //나누는 수 일의 자리 회색으로
//                divisor_one.setTextColor(Color.GRAY);

                operand1TextView = divisor_ten;
                operand2TextView = quotient_ten;
                operand3TextView = null;
                operand4TextView = null;
                operand5TextView = null;

                input1TextView = null;
                input2TextView = first_multiply_hundred;

                ans = divisorTen * quotientTen +
                        Integer.parseInt(carrying_divisor_ten.getText().toString());
//                multiInput = true;

                break;

            case 4:
//                //나누는 수 십의 자리 회색으로
//                divisor_ten.setTextColor(Color.GRAY);
//
//                //1차 곱셈 결과 회색으로
//                first_multiply_hundred.setTextColor(Color.GRAY);
//                first_multiply_ten.setTextColor(Color.GRAY);

                //나누는 수 받아올림 삭제
                carrying_divisor_ten.setText("0");
                carrying_divisor_ten.setTextColor(Color.WHITE);

                //나눗셈 형태 확인
                // 0 < 9 라면...
                // 9 >= 9 라면...


/* first ine 레이아웃 2 모두 삭제할 것을 심각하게 고려중;;;;;; 바보야 나누어지는 수 일의 자리에 받아내림이 필요한 경우는
몫이 한 자리인 경우 밖에 없잖아!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                //first line의 레이아웃 2 활성화
                carrying_l2_divisor_ten.setVisibility(View.VISIBLE);
                carrying_l2_divisor_one.setVisibility(View.VISIBLE);
                carrying_l2_dividend_hundred.setVisibility(View.VISIBLE);
                carrying_l2_dividend_ten.setVisibility(View.VISIBLE);
                carrying_l2_dividend_one.setVisibility(View.VISIBLE);
*/
                if (dividendTen >= Integer.parseInt(first_multiply_ten.getText().toString())) {
                    // 9 >= 9 라면...

                    operand1TextView = dividend_hundred;
                    operand2TextView = dividend_ten;
                    operand3TextView = first_multiply_hundred;
                    operand4TextView = first_multiply_ten;
                    operand5TextView = null;

                    ans_first_line.setVisibility(View.VISIBLE);

                    ans = (dividendHundred * 10 + dividendTen) -
                            (Integer.parseInt(first_multiply_hundred.getText().toString()) * 10 +
                                    Integer.parseInt(first_multiply_ten.getText().toString()));

                    //뺄셈 결과 백의 자리가 0이라면...
                    if (ans < 10) {
                        input1TextView = null;
                    } else {
                        input1TextView = first_subtract_hundred;
                    }
                    input2TextView = first_subtract_ten;

                    currentStage = 7;
                    // 이 스위치 한방으로 받아내림을 생략하고 바로 첫번재 뺄셈을 진행하게 된다.

                } else {
                    // 0 < 9라면...

                    operand1TextView = dividend_hundred;
                    operand2TextView = null;
                    operand3TextView = null;
                    operand4TextView = null;
                    operand5TextView = null;
                    //받아내림 표시하기
                    currentMark = dividend_hundred_cover;
                    markSlashOn();

                    input1TextView = null;
                    input2TextView = carrying_dividend_hundred;

                    ans = dividendHundred - 1;
//                multiInput = true;
                }


                break;

            case 5:
                operand1TextView = dividend_ten;
                operand2TextView = null;
                operand3TextView = null;
                operand4TextView = null;
                operand5TextView = null;

                currentMark = dividend_ten_cover;
                markSlashOn();

                input1TextView = carrying_dividend_ten_10;
                input2TextView = carrying_dividend_ten_1;

                ans = Integer.parseInt(dividend_ten.getText().toString()) + 10;
//                multiInput = false;

                break;

            case 6:
                operand1TextView = carrying_dividend_ten_10;
                operand2TextView = carrying_dividend_ten_1;
                operand3TextView = first_multiply_ten;
                operand4TextView = null;
                operand5TextView = null;

                input1TextView = null;
                input2TextView = first_subtract_ten;

                ans = (Integer.parseInt(carrying_dividend_ten_10.getText().toString()) * 10 +
                    Integer.parseInt(carrying_dividend_ten_1.getText().toString())) -
                        Integer.parseInt(first_multiply_ten.getText().toString());

                ans_first_line.setVisibility(View.VISIBLE);

                break;

            case 7:
                operand1TextView = carrying_dividend_hundred;
                operand2TextView = first_multiply_hundred;
                operand3TextView = null;
                operand4TextView = null;
                operand5TextView = null;

                input1TextView = null;
                input2TextView = first_subtract_hundred;

                ans = Integer.parseInt(carrying_dividend_hundred.getText().toString()) -
                        Integer.parseInt(first_multiply_hundred.getText().toString());

                break;

            case 8:
                if (Integer.parseInt(first_subtract_hundred.getText().toString()) == 0) {
                    // 첫번째 뺄셈 결과 백의 자리가 0이면 백의 자리 삭제
                    first_subtract_hundred.setText("0");
                    first_subtract_hundred.setTextColor(Color.WHITE);
                    if (Integer.parseInt(first_subtract_ten.getText().toString()) == 0) {
                        //첫번째 뺄셈 결과 백의 자리도 0, 십의 자리도 0이라면 모두 삭제
                        first_subtract_ten.setText("0");
                        first_subtract_ten.setTextColor(Color.WHITE);
                    }
                }

                operand1TextView = dividend_one;
                operand2TextView = null;
                operand3TextView = null;
                operand4TextView = null;
                operand5TextView = null;

                input1TextView = null;
                input2TextView = first_subtract_one;

                ans = dividendOne;

                break;

            case 9:
                operand1TextView = divisor_ten;
                operand2TextView = divisor_one;

                if (Integer.parseInt(first_subtract_hundred.getText().toString()) != 0) {
                    operand3TextView = first_subtract_hundred;
                    operand4TextView = first_subtract_ten;
                    operand5TextView = first_subtract_one;
                } else {
                    if (Integer.parseInt(first_subtract_ten.getText().toString()) != 0){
                        operand3TextView = first_subtract_ten;
                        operand4TextView = first_subtract_one;
                    } else {
                        // 첫번째 뺄셈 결과 백의 자리, 십의 자리가 모두 0이라면 일의 자리 값만 유효하다
                        operand3TextView = first_subtract_one;
                        operand4TextView = null;

                        // 나머지가 일의 자리이기 때문에 종료
                        isFinal = true;
                    }
                    operand5TextView = null;
                }

                if (Integer.parseInt(first_subtract_hundred.getText().toString()) * 100 +
                        Integer.parseInt(first_subtract_ten.getText().toString()) * 10 +
                        Integer.parseInt(first_subtract_one.getText().toString()) <
                        divisor) {
                    //첫번째 뺄셈 결과가 나누는 수 보다 작으면 바로 종료
                    isFinal = true;
                }

                input1TextView = null;
                input2TextView = quotient_one;

                ans = quotientOne;

                break;

            case 10:
                operand1TextView = divisor_one;
                operand2TextView = quotient_one;
                operand3TextView = null;
                operand4TextView = null;
                operand5TextView = null;

                ans = divisorOne * quotientOne;

                // 곱셈 받아올림 처리
                if (ans < 10) {
                    input1TextView = null;
                } else {
                    input1TextView = carrying_divisor_ten;
                }
                input2TextView = second_multiply_one;

                break;

            case 11:
                operand1TextView = divisor_ten;
                operand2TextView = quotient_one;
                operand3TextView = null;
                operand4TextView = null;
                operand5TextView = null;

                ans = divisorTen * quotientOne +
                        Integer.parseInt(carrying_divisor_ten.getText().toString());

                // 두번째 곱셈 결과 백의 자리가 0이라면...
                //즉, 이전에 첫번째 뺄셈 결과 백의 자리가 0인 상태를 의미함...
                if (ans < 10) {
                    input1TextView = null;
                } else {
                    input1TextView = second_multiply_hundred;
                }
                input2TextView = second_multiply_ten;

                break;

            case 12:
                //나누는 수 받아올림 삭제
                carrying_divisor_ten.setText("0");
                carrying_divisor_ten.setTextColor(Color.WHITE);

                //나눗셈 형태 확인
                // 5 >= 4 & 8 >= 8
                // 9 >= 7 & 2 < 4
                // 1 < 9 & 9 >= 4
                // 1 < 9 & 1 < 4

                if ((Integer.parseInt(first_subtract_ten.getText().toString()) >=
                        Integer.parseInt(second_multiply_ten.getText().toString())) &&
                        ((Integer.parseInt(first_subtract_one.getText().toString()) >=
                                Integer.parseInt(second_multiply_one.getText().toString())))) {
                    // 5 >= 4 & 8 >= 8

                    operand1TextView = first_subtract_ten;
                    operand2TextView = first_subtract_one;
                    operand3TextView = second_multiply_ten;
                    operand4TextView = second_multiply_one;
                    operand5TextView = null;

                    ans = (Integer.parseInt(first_subtract_ten.getText().toString()) * 10 +
                        Integer.parseInt(first_subtract_one.getText().toString())) -
                            (Integer.parseInt(second_multiply_ten.getText().toString()) * 10 +
                            Integer.parseInt(second_multiply_one.getText().toString()));

                    if (ans < 10) {
                        //뺄셈 결과 나머지가 일의 자리이면 입력은 일의 자리만...
                        input1TextView = null;
                    } else {
                        input1TextView = remainder_ten;
                    }
                    input2TextView = remainder_one;

                    ans_second_line.setVisibility(View.VISIBLE);

                    isFinal = true;
                    //두번째 뺄셈 결과는 반드시 두 자리 이하여야 하므로 곱셈에서처럼 백의 자리를 걱정할 필요는 없다.

                } else if ((Integer.parseInt(first_subtract_ten.getText().toString()) >=
                            Integer.parseInt(second_multiply_ten.getText().toString())) &&
                            ((Integer.parseInt(first_subtract_one.getText().toString()) <
                                    Integer.parseInt(second_multiply_one.getText().toString())))) {
                    // 9 >= 7 & 2 < 4

                    operand1TextView = first_subtract_ten;
                    operand2TextView = null;
                    operand3TextView = null;
                    operand4TextView = null;
                    operand5TextView = null;

                    currentMark = first_subtract_ten_cover;
                    markSlashOn();

                    input1TextView = null;
                    input2TextView = carrying_first_subtract_ten_1;

                    ans = Integer.parseInt(first_subtract_ten.getText().toString()) - 1;

                } else {
                    // 1 < 9 & 9 > 4
                    // 1 < 9 & 1 < 4
                    //두 형태 모두 동일

                    //레이아웃 2 활성화
                    carrying_l2_first_subtract_hundred.setVisibility(View.VISIBLE);
                    carrying_l2_first_subtract_ten.setVisibility(View.VISIBLE);
                    carrying_l2_first_subtract_one.setVisibility(View.VISIBLE);

                    operand1TextView = first_subtract_hundred;
                    operand2TextView = null;
                    operand3TextView = null;
                    operand4TextView = null;
                    operand5TextView = null;

                    currentMark = first_subtract_hundred_cover;
                    markSlashOn();

                    input1TextView = null;
                    input2TextView = carrying_first_subtract_hundred;

                    ans = Integer.parseInt(first_subtract_hundred.getText().toString()) - 1;
                }
                break;

            case 13:

                if ((Integer.parseInt(first_subtract_ten.getText().toString()) >=
                Integer.parseInt(second_multiply_ten.getText().toString())) &&
                ((Integer.parseInt(first_subtract_one.getText().toString()) <
                        Integer.parseInt(second_multiply_one.getText().toString())))) {
                    // 9 >= 7 & 2 < 4

                    operand1TextView = first_subtract_one;
                    operand2TextView = null;
                    operand3TextView = null;
                    operand4TextView = null;
                    operand5TextView = null;

                    currentMark = first_subtract_one_cover;
                    markSlashOn();

                    input1TextView = carrying_first_subtract_one_10;
                    input2TextView = carrying_first_subtract_one_1;

                    ans = Integer.parseInt(first_subtract_one.getText().toString()) + 10;

                } else {
                    // 1 < 9 & 9 > 4
                    // 1 < 9 & 1 < 4
                    // 두 형태 모두 동일

                    operand1TextView = first_subtract_ten;
                    operand2TextView = null;
                    operand3TextView = null;
                    operand4TextView = null;
                    operand5TextView = null;

                    currentMark = first_subtract_ten_cover;
                    markSlashOn();

                    input1TextView = carrying_first_subtract_ten_10;
                    input2TextView = carrying_first_subtract_ten_1;

                    ans = Integer.parseInt(first_subtract_ten.getText().toString()) + 10;

                }
                break;

            case 14:
                if ((Integer.parseInt(first_subtract_ten.getText().toString()) >=
                        Integer.parseInt(second_multiply_ten.getText().toString())) &&
                        ((Integer.parseInt(first_subtract_one.getText().toString()) <
                                Integer.parseInt(second_multiply_one.getText().toString())))) {
                    // 9 >= 7 & 2 < 4

                    operand1TextView = carrying_first_subtract_one_10;
                    operand2TextView = carrying_first_subtract_one_1;
                    operand3TextView = second_multiply_one;
                    operand4TextView = null;
                    operand5TextView = null;

                    input1TextView = null;
                    input2TextView = remainder_one;

                    ans = Integer.parseInt(carrying_first_subtract_one_10.getText().toString()) * 10 +
                            Integer.parseInt(carrying_first_subtract_one_1.getText().toString()) -
                            Integer.parseInt(second_multiply_one.getText().toString());

                    if (Integer.parseInt(carrying_first_subtract_ten_10.getText().toString()) * 10 +
                            Integer.parseInt(carrying_first_subtract_ten_1.getText().toString()) -
                            Integer.parseInt(second_multiply_ten.getText().toString()) == 0) {
                        //바로 앞 자리(받아내림하고 남은 십의 자리 - 두번째 곱셈 십의 자리)가 0인 경우, 연산 종료
                        isFinal = true;
                    }

                    ans_second_line.setVisibility(View.VISIBLE);

                } else if ((Integer.parseInt(first_subtract_ten.getText().toString()) <
                        Integer.parseInt(second_multiply_ten.getText().toString())) &&
                        ((Integer.parseInt(first_subtract_one.getText().toString()) >=
                                Integer.parseInt(second_multiply_one.getText().toString())))) {
                    // 1 < 9 & 9 >= 4

                    operand1TextView = first_subtract_one;
                    operand2TextView = second_multiply_one;
                    operand3TextView = null;
                    operand4TextView = null;
                    operand5TextView = null;

                    input1TextView = null;
                    input2TextView = remainder_one;

                    ans = Integer.parseInt(first_subtract_one.getText().toString()) -
                            Integer.parseInt(second_multiply_one.getText().toString());

                    if (Integer.parseInt(carrying_first_subtract_ten_10.getText().toString()) * 10 +
                            Integer.parseInt(carrying_first_subtract_ten_1.getText().toString()) -
                            Integer.parseInt(second_multiply_ten.getText().toString()) == 0) {
                        //바로 앞 자리(받아내림하고 남은 십의 자리 - 두번째 곱셈 십의 자리)가 0인 경우, 연산 종료
                        isFinal = true;
                    }

                    ans_second_line.setVisibility(View.VISIBLE);

                }else {
                    // 1 < 9 & 1 < 4
                    operand1TextView = carrying_first_subtract_ten_10;
                    operand2TextView = carrying_first_subtract_ten_1;
                    operand3TextView = null;
                    operand4TextView = null;
                    operand5TextView = null;

                    currentMark = carrying_first_subtract_ten_cover;
                    markSlashOn();

                    ans = Integer.parseInt(carrying_first_subtract_ten_10.getText().toString()) * 10 +
                            Integer.parseInt(carrying_first_subtract_ten_1.getText().toString()) - 1;

                    if (ans < 10) {
                        //2단계 받아내림 값이 10보다 작다면 input은 하나만 입력하도록 한다.
                        input1TextView = null;
                    } else {
                        input1TextView = carrying_l2_first_subtract_ten_10;
                    }
                    input2TextView = carrying_l2_first_subtract_ten_1;

                }
                break;

            case 15:

                if ((Integer.parseInt(first_subtract_ten.getText().toString()) >=
                        Integer.parseInt(second_multiply_ten.getText().toString())) &&
                        ((Integer.parseInt(first_subtract_one.getText().toString()) <
                                Integer.parseInt(second_multiply_one.getText().toString())))) {
                    // 9 >= 7 & 2 < 4

                    operand1TextView = carrying_first_subtract_ten_1;
                    operand2TextView = second_multiply_ten;
                    operand3TextView = null;
                    operand4TextView = null;
                    operand5TextView = null;

                    input1TextView = null;
                    input2TextView = remainder_ten;

                    ans = Integer.parseInt(carrying_first_subtract_ten_1.getText().toString()) -
                            Integer.parseInt(second_multiply_ten.getText().toString());

                    isFinal = true;

                } else if ((Integer.parseInt(first_subtract_ten.getText().toString()) <
                        Integer.parseInt(second_multiply_ten.getText().toString())) &&
                        ((Integer.parseInt(first_subtract_one.getText().toString()) >=
                                Integer.parseInt(second_multiply_one.getText().toString())))) {
                    // 1 < 9 & 9 >= 4

                    operand1TextView = carrying_first_subtract_ten_10;
                    operand2TextView = carrying_first_subtract_ten_1;
                    operand3TextView = second_multiply_ten;
                    operand4TextView = null;
                    operand5TextView = null;

                    input1TextView = null;
                    input2TextView = remainder_ten;

                    ans = Integer.parseInt(carrying_first_subtract_ten_10.getText().toString()) * 10 +
                            Integer.parseInt(carrying_first_subtract_ten_1.getText().toString()) -
                            Integer.parseInt(second_multiply_ten.getText().toString());

                    isFinal = true;

                } else {
                    // 1 < 9 & 1 < 4

                    operand1TextView = first_subtract_one;
                    operand2TextView = null;
                    operand3TextView = null;
                    operand4TextView = null;
                    operand5TextView = null;

                    currentMark = first_subtract_one_cover;
                    markSlashOn();

                    input1TextView = carrying_first_subtract_one_10;
                    input2TextView = carrying_first_subtract_one_1;

                    ans = Integer.parseInt(first_subtract_one.getText().toString()) + 10;
                }
                break;

            case 16:
                operand1TextView = carrying_first_subtract_one_10;
                operand2TextView = carrying_first_subtract_one_1;
                operand3TextView = second_multiply_one;
                operand4TextView = null;
                operand5TextView = null;

                ans_second_line.setVisibility(View.VISIBLE);

                input1TextView = null;
                input2TextView = remainder_one;

                ans = Integer.parseInt(carrying_first_subtract_one_10.getText().toString()) * 10 +
                    Integer.parseInt(carrying_first_subtract_one_1.getText().toString()) -
                    Integer.parseInt(second_multiply_one.getText().toString());

                if (Integer.parseInt(carrying_l2_first_subtract_ten_10.getText().toString()) * 10 +
                        Integer.parseInt(carrying_l2_first_subtract_ten_1.getText().toString()) -
                        Integer.parseInt(second_multiply_ten.getText().toString()) == 0) {
                    //바로 앞 자리(받아내림하고 남은 십의 자리 - 두번째 곱셈 십의 자리)가 0인 경우, 연산 종료
                    isFinal = true;
                }

                break;

            case 17:
                if (Integer.parseInt(carrying_l2_first_subtract_ten_10.getText().toString()) == 0) {
                    operand1TextView = null;
                } else {
                    operand1TextView = carrying_l2_first_subtract_ten_10;
                }
                operand2TextView = carrying_l2_first_subtract_ten_1;
                operand3TextView = second_multiply_ten;
                operand4TextView = null;
                operand5TextView = null;

                input1TextView = null;
                input2TextView = remainder_ten;

                ans = Integer.parseInt(carrying_l2_first_subtract_ten_10.getText().toString()) * 10 +
                        Integer.parseInt(carrying_l2_first_subtract_ten_1.getText().toString()) -
                        Integer.parseInt(second_multiply_ten.getText().toString());

                isFinal = true;

                break;

            default:
                break;
        }

        markOperandAndInput();

//        //곱셈할 각 자리수를 빨간색으로 표시
//        if (operand1TextView != null) {
//            operand1TextView.setTextColor(Color.RED);
//        }
//        if (operand2TextView != null) {
//            operand2TextView.setTextColor(Color.RED);
//        }
//        if (operand3TextView != null) {
//            operand3TextView.setTextColor(Color.RED);
//        }
//        if (operand4TextView != null) {
//            operand4TextView.setTextColor(Color.RED);
//        }
//        if (operand5TextView != null) {
//            operand5TextView.setTextColor(Color.RED);
//        }
//
//        //입력할 텍스트 뷰를 임시로 'A'와 'B'로 표시
//        if (input1TextView != null) {
//            input1TextView.setText("?");
//            input1TextView.setTextColor(Color.BLUE);
//        }
//        if (input2TextView != null) {
//            input2TextView.setText("?");
//            input2TextView.setTextColor(Color.BLUE);
//        }
//
//////        if (currentStage < 4 || (currentStage > 8 && currentStage < 12)) {
////        if (currentStage == 2) {
////
////            //곱셈 과정에서 받아올림이 없는 경우
////            if (ans < 10) {
////                input1TextView.setText("0");
////                input1TextView.setTextColor(Color.WHITE);
//////                multiInput = true;
////            } else {
//////                multiInput = false;
////            }
////            //곱셈 결과를 더하는 과정에서는?
////
////
////        }
//
//        if (input1TextView != null && input2TextView != null) {
//            multiInput = true;
//        } else {
//            multiInput = false;
//        }


//
//        if (input1TextView != null) {
//            inputEntry = 1;
//        } else if (input2TextView != null) {
//            inputEntry = 2;
//        } else {
//            inputEntry = 3;
//        }
//
//        inputNext = 1;
//
//        if (currentStage < 7) {
//            //세 자리 수 중 하나가 0이거나 곱셈 결과가 받아올림이 없는 경우
//            if (Integer.parseInt(operand1TextView.getText().toString()) == 0 || ans < 10) {
//                input1TextView.setText("0");
//                input1TextView.setTextColor(Color.WHITE);
//                multiInput = true;
//            } else {
//                multiInput = false;
//            }
//        } else {
//            //곱셈 결과를 더하는 과정에서 받아올림이 없는 경우
//            if (ans < 10) {
//                if (input1TextView != null) {
//                    input1TextView.setText("0");
//                    input1TextView.setTextColor(Color.WHITE);
//                }
//                //carrying = false;
//                multiInput = true;
//            } else {
//                multiInput = false;
//            }
//        }
    }

//    private void markSlashOn() {
//        currentMark.setVisibility(View.VISIBLE);
//        currentMark.setImageResource(R.drawable.slash_red);
//    }
//
//    private void markSlashOff() {
//        //완전히 삭제해버릴 필요는 없겠지?
////        iv.setVisibility(View.INVISIBLE);
//        currentMark.setImageResource(R.drawable.slash_gray);
//        currentMark = null;
//    }

//    private boolean result() {
//        int temp = 0, temp1 = 0, temp2 = 0;
//
//        //temp1에 사용자의 첫번째 입력 값 저장
//        if (input1TextView == null) {
//            temp1 = 0;
//        } else {
//            try {
//                temp1 = Integer.parseInt(input1TextView.getText().toString());
//                Log.v(LOG_TAG, "temp1 : " + String.valueOf(temp1));
//            } catch (NumberFormatException nfe) {
//                nfe.printStackTrace();
//            }
//        }
//
//        //temp2에 사용자의 두번째 입력 값 저장
//        if (input2TextView == null) {
//            temp2 = 0;
//        } else {
//            try {
//                temp2 = Integer.parseInt(input2TextView.getText().toString());
//                Log.v(LOG_TAG, "temp2 : " + String.valueOf(temp2));
//            } catch (NumberFormatException nfe) {
//                nfe.printStackTrace();
//            }
//        }
//
////        if (input3TextView == null || !input3TextView.getText().toString().matches("[0-9]")) {
////            temp3 = 0;
////        } else {
////            try {
////                temp3 = Integer.parseInt(input3TextView.getText().toString());
////                Log.v(LOG_TAG, "temp3 : " + String.valueOf(temp2));
////            } catch (NumberFormatException nfe) {
////                nfe.printStackTrace();
////            }
////        }
//
//        temp = temp1 * 10 + temp2;
//
////        //각 자리수를 곱하는 과정 처리
////        if (currentStage < 7) {
////            //사용자가 입력한 값을 바탕으로 곱셈 결과 temp에 저장
////            temp = temp1 * 10 + temp2;
////            Log.v(LOG_TAG, "temp : " + String.valueOf(temp));
////
////        //일의 자리, 십의 자리 곱셈 결과를 더하는 과정 처리
////        } else {
////
////            //사용자가 입력한 값을 temp에 저장
////            if (input2TextView == null) {
////                temp = temp1;
//////                try {
//////                    temp = Integer.parseInt(input1TextView.getText().toString());
//////                    Log.v(LOG_TAG, "ans : " + String.valueOf(ans));
//////                    Log.v(LOG_TAG, "temp : " + String.valueOf(temp));
//////                } catch (NumberFormatException nfe) {
//////                    return wrongAnswer(input1TextView.getText().toString());
//////                }
////            } else {
////                temp = temp1 * 10 + temp2;
////            }
////        }
//
//        //정답처리
//        if (ans == temp) {
//            flashText(true);
//
//            //연산했던 자리수를 다시 회색으로 되돌리기
//            if (operand1TextView != null) {
//                operand1TextView.setTextColor(Color.GRAY);
//            }
//            if (operand2TextView != null) {
//                operand2TextView.setTextColor(Color.GRAY);
//            }
//            if (operand3TextView != null) {
//                operand3TextView.setTextColor(Color.GRAY);
//            }
//            if (operand4TextView != null) {
//                operand4TextView.setTextColor(Color.GRAY);
//            }
//            if (operand5TextView != null) {
//                operand5TextView.setTextColor(Color.GRAY);
//            }
//
//            //입력했던 내용 회색으로 되돌리기
//            if (input1TextView != null) {
//                input1TextView.setTextColor(Color.GRAY);
//            }
//            if (input2TextView != null) {
//                input2TextView.setTextColor(Color.GRAY);
//            }
//
//            //받아내림 표시 회색으로 되돌리기
//            if (currentMark != null) {
//                markSlashOff();
//            }
///* 없어도 되지 않는가??????
//
//            //첫 뺄셈 결과 백의 자리가 없는 경우 0을 삭제
//            //이거 어떵할꺼냐!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//            if (currentStage == 8) {
//                if (Integer.parseInt(first_subtract_hundred.getText().toString()) == 0) {
//                    first_multiply_hundred.setText("0");
//                    first_multiply_hundred.setTextColor(Color.WHITE);
//                }
//
//            }
//*/
//
//
///*
//            //모든 연산이 끝나면
//            if ((currentStage == 3 && !isFullDivide) || currentStage == 7) {
//                finalStage();
//                return false;
//                //여기에 false를 넣지 않으면 finalStage로 가면서 nextStage()가 한번 더 실행된다!!
//                //잡기 어려웠던 버그 중 하나!
//            }*/
//
//            if (isFinal) {
//                finalStage();
//                return false;
//            }
//
//            return true;
//
//            //오답처리
//        } else {
//            //진동 발사
//            Vibrator vibrator = (Vibrator)getActivity().getSystemService(Context.VIBRATOR_SERVICE);
//            vibrator.vibrate(300);
//
//            //오답 텍스트 보여주기
//            flashText(false);
//
//            //사용자가 입력할 텍스트 뷰를 다시 'A'와 'B'로 되돌림
//            if (input1TextView != null) {
//                input1TextView.setText("?");
//                input1TextView.setTextColor(Color.BLUE);
//            }
//            if (input2TextView != null) {
//                input2TextView.setText("?");
//                input2TextView.setTextColor(Color.BLUE);
//            }
//
//            return false;
//        }
//    }

    //flashText()와 finalStage()를 ProblemFragment로 옮겼다.
//    private void finalStage() {
//        /* finalStage()에서 값을 초기화한 후 다시 nextStage()를 호출하지는 않는데
//        * 어차피 입력 버튼을 누르면 nextStage()를 호출하기 때문 */
//
//        Toast toastR = Toast.makeText(getActivity(), "축하합니다!", Toast.LENGTH_LONG);
//        toastR.setGravity(Gravity.CENTER, 0, 0);
//        toastR.show();
//
//        //모든 변수 초기화
//        currentStage = 0;
////        multiInput = false;
//        ans = 0;
//
//        isFullDivide = true;
//
//        //피연산자 초기화
//        initOperands();
//        //피연산자를 제외한 나머지 모든 숫자 초기화
//        initNumbers();
//    }

//    private void initNumbers() {
////        if (operand1TextView != null) {
////            operand1TextView.setTextColor(Color.GRAY);
////        }
////        if (operand2TextView != null) {
////            operand2TextView.setTextColor(Color.GRAY);
////        }
////        if (operand3TextView != null) {
////            operand3TextView.setTextColor(Color.GRAY);
////        }
//
//
//        divisor_ten.setTextColor(Color.GRAY);
//        divisor_one.setTextColor(Color.GRAY);
//
//        dividend_hundred.setTextColor(Color.GRAY);
//        dividend_ten.setTextColor(Color.GRAY);
//        dividend_one.setTextColor(Color.GRAY);
//
//        quotient_ten.setText(String.valueOf("0"));
//        quotient_ten.setTextColor(Color.WHITE);
//        quotient_one.setText(String.valueOf("0"));
//        quotient_one.setTextColor(Color.WHITE);
//
//        first_multiply_hundred.setText(String.valueOf("0"));
//        first_multiply_hundred.setTextColor(Color.WHITE);
//        first_multiply_ten.setText(String.valueOf("0"));
//        first_multiply_ten.setTextColor(Color.WHITE);
//        first_multiply_one.setText(String.valueOf("0"));
//        first_multiply_one.setTextColor(Color.WHITE);
//
//        ans_first_line.setBackgroundColor(Color.WHITE);
//
//        first_subtract_hundred.setText(String.valueOf("0"));
//        first_subtract_hundred.setTextColor(Color.WHITE);
//        first_subtract_ten.setText(String.valueOf("0"));
//        first_subtract_ten.setTextColor(Color.WHITE);
//        first_subtract_one.setText(String.valueOf("0"));
//        first_subtract_one.setTextColor(Color.WHITE);
//
//        second_multiply_hundred.setText(String.valueOf("0"));
//        second_multiply_hundred.setTextColor(Color.WHITE);
//        second_multiply_ten.setText(String.valueOf("0"));
//        second_multiply_ten.setTextColor(Color.WHITE);
//        second_multiply_one.setText(String.valueOf("0"));
//        second_multiply_one.setTextColor(Color.WHITE);
//
//        ans_second_line.setBackgroundColor(Color.WHITE);
//
//        remainder_ten.setText(String.valueOf("0"));
//        remainder_ten.setTextColor(Color.WHITE);
//        remainder_one.setText(String.valueOf("0"));
//        remainder_one.setTextColor(Color.WHITE);
//    }

//    @Override
//    public void onNumberClicked(int number) {
////        switch (inputNext) {
////            case 1:
////                switch (inputEntry) {
////                    case 1:
////                        if (input1TextView != null) {
////                            input1TextView.setText(String.valueOf(number));
////                        }
////                        inputNext = 2;
////                        break;
////                    case 2:
////                        input2TextView.setText(String.valueOf(number));
////                        inputNext = 2;
////                        break;
////                    case 3:
////                        input3TextView.setText(String.valueOf(number));
////                        inputNext = 1;
////                        break;
////                    default:
////                        break;
////                }
////                break;
////            case 2:
////                switch (inputEntry) {
////                    case 1:
////                        input2TextView.setText(String.valueOf(number));
////                        inputNext = 3;
////                        break;
////                    case 2:
////                        input3TextView.setText(String.valueOf(number));
////                        inputNext = 1;
////                        break;
////                    default:
////                        break;
////                }
////                break;
////            case 3:
////                switch (inputEntry) {
////                    case 1:
////                        input3TextView.setText(String.valueOf(number));
////                        inputNext = 1;
////                        break;
////                    default:
////                        break;
////                }
////                break;
////            default:
////                break;
////        }
//
//        if (!multiInput) {
//            if (input2TextView != null) {
//                input2TextView.setText(String.valueOf(number));
//            }
//        } else {
//            //사용자 입력 처리 : 첫번째 입력인 경우 input1TextView에 값을 입력함
//            if (carrying) {
//                if (input1TextView != null) {
//                    input1TextView.setText(String.valueOf(number));
//                }
//                carrying = false;
////                Log.v(LOG_TAG, "carrying : " + carrying);
//
//            } else {
//                //두번째 입력인 경우 input2TextView에 값을 입력함
//                if (input2TextView != null) {
//                    input2TextView.setText(String.valueOf(number));
//                }
//                carrying = true;
////                Log.v(LOG_TAG, "carrying : " + carrying);
//
//            }
//        }
//    }
//
//    public void onClearClicked() {
////        currentStage = 0;
////        ans = 0;
////        initNumbers();
////        nextStage();
//
//        //사용자가 입력할 텍스트 뷰를 다시 'A'와 'B'로 되돌림
//        if (input1TextView != null) {
//            input1TextView.setText("?");
//            input1TextView.setTextColor(Color.BLUE);
//        }
//        if (input2TextView != null) {
//            input2TextView.setText("?");
//            input2TextView.setTextColor(Color.BLUE);
//        }
//        carrying = true;
//        Log.v(LOG_TAG, "carrying : " + carrying);
//
//
//    }
//
//    public void onOKClicked() {
//        if (input1TextView == null) {
//            if (input2TextView.getText().toString().matches("[0-9]")) {
//                if (result()) {
//                    nextStage();
//                }            }
//        } else {
//            if (input1TextView.getText().toString().matches("[0-9]") && input2TextView.getText().toString().matches("[0-9]"))
//                if (result()) {
//                    nextStage();
//                }        }
//
////
////        if (result()) {
////            nextStage();
////        }
//    }

}

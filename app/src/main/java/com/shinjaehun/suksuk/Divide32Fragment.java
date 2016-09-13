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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by shinjaehun on 2016-04-19.
 */
public class Divide32Fragment extends ProblemFragment {

    private static final String LOG_TAG = Divide32Fragment.class.getSimpleName();

    private int divisor, dividend, quotient;
    private int dividendHundred, dividendTen, dividendOne;
    private int divisorTen, divisorOne;
    private int quotientTen, quotientOne;

    private View ansFirstLineV, ansSecondLineV;

    private TextView quotientTenTV, quotientOneTV;

    private TextView carryingDivisorTenTV;
    private TextView divisorTenTV, divisorOneTV;

    private TextView carryingDividendHundredTV, carryingDividendTen10TV, carryingDividendTen1TV;
    private TextView carryingDividendOne10TV, carryingDividendOne1TV;
    private ImageView carryingDividendTenCoverIV;

    private ImageView divSignIV;

//    private ImageView currentMarkIV;

    private ImageView dividendHundredCoverIV, dividendTenCoverIV, dividendOneCoverIV;
    private TextView dividendHundredTV, dividendTenTV, dividendOneTV;

    private TextView firstMultiplyHundredTV, firstMultiplyTenTV, firstMultiplyOneTV;

    private LinearLayout carryingL2DividendHundredLL, carryingL2DividendTenLL, carryingL2DividendOneLL;
    private LinearLayout carryingL2FirstSubtractHundredLL, carryingL2FirstSubtractTenLL, carryingL2FirstSubtractOneLL;
    private ImageView carryingFirstSubtractTenCoverIV;
    private ImageView firstSubtractHundredCoverIV, firstSubtractTenCoverIV, firstSubtractOneCoverIV;
    private TextView carryingFirstSubtractHundredTV, carryingFirstSubtractTen10TV, carryingFirstSubtractTen1TV,
            carryingFirstSubtractOne10TV, carryingFirstSubtractOne1TV;
    private TextView carryingL2FirstSubtractTen10TV, carryingL2FirstSubtractTen1TV;
    private TextView carryingL2DividendTen10TV, carryingL2DividendTen1TV;

    private TextView firstSubtractHundredTV, firstSubtractTenTV, firstSubtractOneTV;
    private TextView secondMultiplyHundredTV, secondMultiplyTenTV, secondMultiplyOneTV;
    private TextView remainderTenTV, remainderOneTV;

//    private TextView operand1TV, operand2TV, operand3TV, operand4TV, operand5TV, operand6TextView;
//    private TextView input1TV, input2TV, input3TextView;
//
//    private ImageButton helpBTN;

    //몫이 두 자리 수인 경우 체크하는 스위치
    private boolean isFullDivide = true;

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

        startTime = System.nanoTime();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_divide32, container, false);

        quotientTenTV = (TextView)v.findViewById(R.id.quotient_ten);
        quotientOneTV = (TextView)v.findViewById(R.id.quotient_one);

        carryingDivisorTenTV = (TextView)v.findViewById(R.id.carrying_divisor_ten);

        divisorTenTV = (TextView)v.findViewById(R.id.divisor_ten);
        divisorOneTV = (TextView)v.findViewById(R.id.divisor_one);

        carryingDividendHundredTV = (TextView)v.findViewById(R.id.carrying_dividend_hundred);
        carryingDividendTen10TV = (TextView)v.findViewById(R.id.carrying_dividend_ten_10);
        carryingDividendTen1TV = (TextView)v.findViewById(R.id.carrying_dividend_ten_1);
        carryingDividendOne10TV = (TextView)v.findViewById(R.id.carrying_dividend_one_10);
        carryingDividendOne1TV = (TextView)v.findViewById(R.id.carrying_dividend_one_1);

        carryingDividendTenCoverIV = (ImageView)v.findViewById(R.id.carrying_dividend_ten_cover);

        dividendHundredCoverIV = (ImageView)v.findViewById(R.id.dividend_hundred_cover);
        dividendTenCoverIV = (ImageView)v.findViewById(R.id.dividend_ten_cover);
        dividendOneCoverIV = (ImageView)v.findViewById(R.id.dividend_one_cover);

        dividendHundredTV = (TextView)v.findViewById(R.id.dividend_hundred);
        dividendTenTV = (TextView)v.findViewById(R.id.dividend_ten);
        dividendOneTV = (TextView)v.findViewById(R.id.dividend_one);

        carryingL2DividendHundredLL = (LinearLayout)v.findViewById(R.id.carrying_l2_dividend_hundred);
        carryingL2DividendTenLL = (LinearLayout)v.findViewById(R.id.carrying_l2_dividend_ten);
        carryingL2DividendOneLL = (LinearLayout)v.findViewById(R.id.carrying_l2_dividend_one);

        carryingL2FirstSubtractHundredLL = (LinearLayout)v.findViewById(R.id.carrying_l2_first_subtract_hundred);
        carryingL2FirstSubtractTenLL = (LinearLayout)v.findViewById(R.id.carrying_l2_first_subtract_ten);
        carryingL2FirstSubtractOneLL = (LinearLayout)v.findViewById(R.id.carrying_l2_first_subtract_one);

        carryingL2FirstSubtractTen10TV = (TextView)v.findViewById(R.id.carrying_l2_first_subtract_ten_10);
        carryingL2FirstSubtractTen1TV = (TextView)v.findViewById(R.id.carrying_l2_first_subtract_ten_1);

        carryingL2DividendTen10TV = (TextView)v.findViewById(R.id.carrying_l2_dividend_ten_10);
        carryingL2DividendTen1TV = (TextView)v.findViewById(R.id.carrying_l2_dividend_ten_1);

        carryingFirstSubtractHundredTV = (TextView)v.findViewById(R.id.carrying_first_subtract_hundred);
        carryingFirstSubtractTen10TV = (TextView)v.findViewById(R.id.carrying_first_subtract_ten_10);
        carryingFirstSubtractTen1TV = (TextView)v.findViewById(R.id.carrying_first_subtract_ten_1);
        carryingFirstSubtractOne10TV = (TextView)v.findViewById(R.id.carrying_first_subtract_one_10);
        carryingFirstSubtractOne1TV = (TextView)v.findViewById(R.id.carrying_first_subtract_one_1);

        carryingFirstSubtractTenCoverIV = (ImageView)v.findViewById(R.id.carrying_first_subtract_ten_cover);

        firstSubtractHundredCoverIV = (ImageView)v.findViewById(R.id.first_subtract_hundred_cover);
        firstSubtractTenCoverIV = (ImageView)v.findViewById(R.id.first_subtract_ten_cover);
        firstSubtractOneCoverIV = (ImageView)v.findViewById(R.id.first_subtract_one_cover);

        firstMultiplyHundredTV = (TextView)v.findViewById(R.id.first_multiply_hundred);
        firstMultiplyTenTV = (TextView)v.findViewById(R.id.first_multiply_ten);
        firstMultiplyOneTV = (TextView)v.findViewById(R.id.first_multiply_one);

        ansFirstLineV = v.findViewById(R.id.ans_first_line);

        firstSubtractHundredTV = (TextView)v.findViewById(R.id.first_subtract_hundred);
        firstSubtractTenTV = (TextView)v.findViewById(R.id.first_subtract_ten);
        firstSubtractOneTV = (TextView)v.findViewById(R.id.first_subtract_one);

        secondMultiplyHundredTV = (TextView)v.findViewById(R.id.second_multiply_hundred);
        secondMultiplyTenTV = (TextView)v.findViewById(R.id.second_multiply_ten);
        secondMultiplyOneTV = (TextView)v.findViewById(R.id.second_multiply_one);

        ansSecondLineV = v.findViewById(R.id.ans_second_line);

        remainderTenTV = (TextView)v.findViewById(R.id.remainder_ten);
        remainderOneTV = (TextView)v.findViewById(R.id.remainder_one);

        divSignIV = (ImageView)v.findViewById(R.id.div_sign);

        helpBTN = (ImageButton) v.findViewById(R.id.help);
        challengeCounterTV = (TextView)v.findViewById(R.id.challengeCounter);

        if (isChallenge == false) {
            challengeCounterTV.setVisibility(View.GONE);

            helpBTN.setOnClickListener(new Button.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
                    intent.putExtra(operation, "divide32");
                    startActivity(intent);
                }
            });
        } else {
//            Log.v(LOG_TAG, "challengeNumber : " + challengeNumber);

            challengeCounterTV.setVisibility(View.VISIBLE);
            challengeCounterTV.setText(String.valueOf(challengeNumber));

        }
        return v;

    }

    private void initOperands() {
        /* 피연산자 생성 */
        /*난수 테스트
        for (int i = 0; i < 100; i++) {
            top = (int) (Math.random() * 900) + 100;
            Log.v(LOG_TAG, String.valueOf(top));
        }*/

        dividend = (int) (Math.random() * 900) + 100;
        divisor = (int) (Math.random() * 90) + 10;

////
//        int a = (int) (Math.random() * 90) + 10;
//        int b = (int) (Math.random() * 90) + 10;
//
//        if (a >= b) {
//            dividend = a * 10 + (int)(Math.random() * 9);
//            divisor = b;
//        } else {
//            dividend = b * 10 + (int)(Math.random() * 9);
//            divisor = a;
//        }

//        dividend = 801;
//        divisor = 49;

//        dividend = 105;
//        divisor = 96;

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

        //나누는 수가 나누어지는 수의 백의자리, 십의자리보다 크면 몫이 하나인 나눗셈 전개
        if (divisor > dividendHundred * 10 + dividendTen) {
            isFullDivide = false;
        }

        //피연산자 표시
        dividendHundredTV.setText(String.valueOf(dividendHundred));
        dividendTenTV.setText(String.valueOf(dividendTen));
        dividendOneTV.setText(String.valueOf(dividendOne));

        divisorTenTV.setText(String.valueOf(divisorTen));
        divisorOneTV.setText(String.valueOf(divisorOne));

    }

    public void nextStage() {
        currentStage += 1;
//        Log.v(LOG_TAG, "Current Stage : " + String.valueOf(currentStage));

        if (isFullDivide) {
            //currentStage에 따라 곱셈할 자리수, 곱셈 결과, 입력할 자리수 지정
            switch (currentStage) {
                case 1:
                    operand1TV = divisorTenTV;
                    operand2TV = divisorOneTV;
                    operand3TV = dividendHundredTV;
                    operand4TV = dividendTenTV;

                    //곱셈에 따라 여기서 operand5Text를 사용할 수도 있음
                    operand5TV = null;

                    input1TV = null;
                    input2TV = quotientTenTV;

                    ans = quotientTen;
//                multiInput = true;

                    break;

                case 2:
//                //나누어지는 수 회색으로
//                dividendHundredTV.setTextColor(Color.GRAY);
//                dividendTenTV.setTextColor(Color.GRAY);
//
//                //나누는 수 십의 자리 회색으로
//                divisorTenTV.setTextColor(Color.GRAY);

                    operand1TV = divisorOneTV;
                    operand2TV = quotientTenTV;
                    operand3TV = null;
                    operand4TV = null;
                    operand5TV = null;


                    ans = divisorOne * quotientTen;

                    //곱셈과정에서 받아올림이 있는 경우와 없는 경우 처리
                    if (ans < 10) {
                        input1TV = null;
                    } else {
                        input1TV = carryingDivisorTenTV;
                    }
                    input2TV = firstMultiplyTenTV;

                    break;

                case 3:
//                //나누는 수 일의 자리 회색으로
//                divisorOneTV.setTextColor(Color.GRAY);

                    operand1TV = divisorTenTV;
                    operand2TV = quotientTenTV;
                    operand3TV = null;
                    operand4TV = null;
                    operand5TV = null;

                    input1TV = null;
                    input2TV = firstMultiplyHundredTV;

                    ans = divisorTen * quotientTen +
                            Integer.parseInt(carryingDivisorTenTV.getText().toString());
//                multiInput = true;

                    break;

                case 4:
//                //나누는 수 십의 자리 회색으로
//                divisorTenTV.setTextColor(Color.GRAY);
//
//                //1차 곱셈 결과 회색으로
//                firstMultiplyHundredTV.setTextColor(Color.GRAY);
//                firstMultiplyTenTV.setTextColor(Color.GRAY);

                    //나누는 수 받아올림 삭제
                    carryingDivisorTenTV.setText("0");
                    carryingDivisorTenTV.setTextColor(Color.WHITE);

                    //나눗셈 형태 확인
                    // 0 < 9 라면...
                    // 9 >= 9 라면...


/* first ine 레이아웃 2 모두 삭제할 것을 심각하게 고려중;;;;;; 바보야 나누어지는 수 일의 자리에 받아내림이 필요한 경우는
몫이 한 자리인 경우 밖에 없잖아!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                //first line의 레이아웃 2 활성화
                carrying_l2_divisor_ten.setVisibility(View.VISIBLE);
                carrying_l2_divisor_one.setVisibility(View.VISIBLE);
                carryingL2DividendHundredLL.setVisibility(View.VISIBLE);
                carryingL2DividendTenLL.setVisibility(View.VISIBLE);
                carryingL2DividendOneLL.setVisibility(View.VISIBLE);
*/
                    if (dividendTen >= Integer.parseInt(firstMultiplyTenTV.getText().toString())) {
                        // 9 >= 9 라면...

                        operand1TV = dividendHundredTV;
                        operand2TV = dividendTenTV;
                        operand3TV = firstMultiplyHundredTV;
                        operand4TV = firstMultiplyTenTV;
                        operand5TV = null;

                        ansFirstLineV.setVisibility(View.VISIBLE);

                        ans = (dividendHundred * 10 + dividendTen) -
                                (Integer.parseInt(firstMultiplyHundredTV.getText().toString()) * 10 +
                                        Integer.parseInt(firstMultiplyTenTV.getText().toString()));

                        //뺄셈 결과 백의 자리가 0이라면...
                        if (ans < 10) {
                            input1TV = null;
                        } else {
                            input1TV = firstSubtractHundredTV;
                        }
                        input2TV = firstSubtractTenTV;

                        currentStage = 7;
                        // 이 스위치 한방으로 받아내림을 생략하고 바로 첫번재 뺄셈을 진행하게 된다.

                    } else {
                        // 0 < 9라면...

                        operand1TV = dividendHundredTV;
                        operand2TV = null;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;
                        //받아내림 표시하기
                        currentMarkIV = dividendHundredCoverIV;
                        markSlashOn();

                        input1TV = null;
                        input2TV = carryingDividendHundredTV;

                        ans = dividendHundred - 1;
//                multiInput = true;
                    }


                    break;

                case 5:
                    operand1TV = dividendTenTV;
                    operand2TV = null;
                    operand3TV = null;
                    operand4TV = null;
                    operand5TV = null;

                    currentMarkIV = dividendTenCoverIV;
                    markSlashOn();

                    input1TV = carryingDividendTen10TV;
                    input2TV = carryingDividendTen1TV;

                    ans = Integer.parseInt(dividendTenTV.getText().toString()) + 10;
//                multiInput = false;

                    break;

                case 6:
                    operand1TV = carryingDividendTen10TV;
                    operand2TV = carryingDividendTen1TV;
                    operand3TV = firstMultiplyTenTV;
                    operand4TV = null;
                    operand5TV = null;

                    input1TV = null;
                    input2TV = firstSubtractTenTV;

                    ans = (Integer.parseInt(carryingDividendTen10TV.getText().toString()) * 10 +
                            Integer.parseInt(carryingDividendTen1TV.getText().toString())) -
                            Integer.parseInt(firstMultiplyTenTV.getText().toString());

                    ansFirstLineV.setVisibility(View.VISIBLE);

                    break;

                case 7:
                    operand1TV = carryingDividendHundredTV;
                    operand2TV = firstMultiplyHundredTV;
                    operand3TV = null;
                    operand4TV = null;
                    operand5TV = null;

                    input1TV = null;
                    input2TV = firstSubtractHundredTV;

                    ans = Integer.parseInt(carryingDividendHundredTV.getText().toString()) -
                            Integer.parseInt(firstMultiplyHundredTV.getText().toString());

                    break;

                case 8:
                    if (Integer.parseInt(firstSubtractHundredTV.getText().toString()) == 0) {
                        // 첫번째 뺄셈 결과 백의 자리가 0이면 백의 자리 삭제
                        firstSubtractHundredTV.setText("0");
                        firstSubtractHundredTV.setTextColor(Color.WHITE);
                        if (Integer.parseInt(firstSubtractTenTV.getText().toString()) == 0) {
                            //첫번째 뺄셈 결과 백의 자리도 0, 십의 자리도 0이라면 모두 삭제
                            firstSubtractTenTV.setText("0");
                            firstSubtractTenTV.setTextColor(Color.WHITE);
                        }
                    }

                    operand1TV = dividendOneTV;
                    operand2TV = null;
                    operand3TV = null;
                    operand4TV = null;
                    operand5TV = null;

                    input1TV = null;
                    input2TV = firstSubtractOneTV;

                    ans = dividendOne;

                    break;

                case 9:
                    operand1TV = divisorTenTV;
                    operand2TV = divisorOneTV;

                    if (Integer.parseInt(firstSubtractHundredTV.getText().toString()) != 0) {
                        operand3TV = firstSubtractHundredTV;
                        operand4TV = firstSubtractTenTV;
                        operand5TV = firstSubtractOneTV;
                    } else {
                        if (Integer.parseInt(firstSubtractTenTV.getText().toString()) != 0) {
                            operand3TV = firstSubtractTenTV;
                            operand4TV = firstSubtractOneTV;
                        } else {
                            // 첫번째 뺄셈 결과 백의 자리, 십의 자리가 모두 0이라면 일의 자리 값만 유효하다
                            operand3TV = firstSubtractOneTV;
                            operand4TV = null;

                            // 나머지가 일의 자리이기 때문에 종료
                            isFinal = true;
                        }
                        operand5TV = null;
                    }

                    if (Integer.parseInt(firstSubtractHundredTV.getText().toString()) * 100 +
                            Integer.parseInt(firstSubtractTenTV.getText().toString()) * 10 +
                            Integer.parseInt(firstSubtractOneTV.getText().toString()) <
                            divisor) {
                        //첫번째 뺄셈 결과가 나누는 수 보다 작으면 바로 종료
                        isFinal = true;
                    }

                    input1TV = null;
                    input2TV = quotientOneTV;

                    ans = quotientOne;

                    break;

                case 10:
                    operand1TV = divisorOneTV;
                    operand2TV = quotientOneTV;
                    operand3TV = null;
                    operand4TV = null;
                    operand5TV = null;

                    ans = divisorOne * quotientOne;

                    // 곱셈 받아올림 처리
                    if (ans < 10) {
                        input1TV = null;
                    } else {
                        input1TV = carryingDivisorTenTV;
                    }
                    input2TV = secondMultiplyOneTV;

                    break;

                case 11:
                    operand1TV = divisorTenTV;
                    operand2TV = quotientOneTV;
                    operand3TV = null;
                    operand4TV = null;
                    operand5TV = null;

                    ans = divisorTen * quotientOne +
                            Integer.parseInt(carryingDivisorTenTV.getText().toString());

                    // 두번째 곱셈 결과 백의 자리가 0이라면...
                    //즉, 이전에 첫번째 뺄셈 결과 백의 자리가 0인 상태를 의미함...
                    if (ans < 10) {
                        input1TV = null;
                    } else {
                        input1TV = secondMultiplyHundredTV;
                    }
                    input2TV = secondMultiplyTenTV;

                    break;

                case 12:
                    //나누는 수 받아올림 삭제
                    carryingDivisorTenTV.setText("0");
                    carryingDivisorTenTV.setTextColor(Color.WHITE);

                    //나눗셈 형태 확인
                    // 5 >= 4 & 8 >= 8
                    // 9 >= 7 & 2 < 4
                    // 1 < 9 & 9 >= 4
                    // 1 < 9 & 1 < 4

                    if ((Integer.parseInt(firstSubtractTenTV.getText().toString()) >=
                            Integer.parseInt(secondMultiplyTenTV.getText().toString())) &&
                            ((Integer.parseInt(firstSubtractOneTV.getText().toString()) >=
                                    Integer.parseInt(secondMultiplyOneTV.getText().toString())))) {
                        // 5 >= 4 & 8 >= 8

                        operand1TV = firstSubtractTenTV;
                        operand2TV = firstSubtractOneTV;
                        operand3TV = secondMultiplyTenTV;
                        operand4TV = secondMultiplyOneTV;
                        operand5TV = null;

                        ans = (Integer.parseInt(firstSubtractTenTV.getText().toString()) * 10 +
                                Integer.parseInt(firstSubtractOneTV.getText().toString())) -
                                (Integer.parseInt(secondMultiplyTenTV.getText().toString()) * 10 +
                                        Integer.parseInt(secondMultiplyOneTV.getText().toString()));

                        if (ans < 10) {
                            //뺄셈 결과 나머지가 일의 자리이면 입력은 일의 자리만...
                            input1TV = null;
                        } else {
                            input1TV = remainderTenTV;
                        }
                        input2TV = remainderOneTV;

                        ansSecondLineV.setVisibility(View.VISIBLE);

                        isFinal = true;
                        //두번째 뺄셈 결과는 반드시 두 자리 이하여야 하므로 곱셈에서처럼 백의 자리를 걱정할 필요는 없다.

                    } else if ((Integer.parseInt(firstSubtractTenTV.getText().toString()) >=
                            Integer.parseInt(secondMultiplyTenTV.getText().toString())) &&
                            ((Integer.parseInt(firstSubtractOneTV.getText().toString()) <
                                    Integer.parseInt(secondMultiplyOneTV.getText().toString())))) {
                        // 9 >= 7 & 2 < 4

                        operand1TV = firstSubtractTenTV;
                        operand2TV = null;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        currentMarkIV = firstSubtractTenCoverIV;
                        markSlashOn();

                        input1TV = null;
                        input2TV = carryingFirstSubtractTen1TV;

                        ans = Integer.parseInt(firstSubtractTenTV.getText().toString()) - 1;

                    } else {
                        // 1 < 9 & 9 > 4
                        // 1 < 9 & 1 < 4
                        //두 형태 모두 동일

                        operand1TV = firstSubtractHundredTV;
                        operand2TV = null;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        currentMarkIV = firstSubtractHundredCoverIV;
                        markSlashOn();

                        input1TV = null;
                        input2TV = carryingFirstSubtractHundredTV;

                        ans = Integer.parseInt(firstSubtractHundredTV.getText().toString()) - 1;
                    }
                    break;

                case 13:

                    if ((Integer.parseInt(firstSubtractTenTV.getText().toString()) >=
                            Integer.parseInt(secondMultiplyTenTV.getText().toString())) &&
                            ((Integer.parseInt(firstSubtractOneTV.getText().toString()) <
                                    Integer.parseInt(secondMultiplyOneTV.getText().toString())))) {
                        // 9 >= 7 & 2 < 4

                        operand1TV = firstSubtractOneTV;
                        operand2TV = null;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        currentMarkIV = firstSubtractOneCoverIV;
                        markSlashOn();

                        input1TV = carryingFirstSubtractOne10TV;
                        input2TV = carryingFirstSubtractOne1TV;

                        ans = Integer.parseInt(firstSubtractOneTV.getText().toString()) + 10;

                    } else {
                        // 1 < 9 & 9 > 4
                        // 1 < 9 & 1 < 4
                        // 두 형태 모두 동일

                        operand1TV = firstSubtractTenTV;
                        operand2TV = null;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        currentMarkIV = firstSubtractTenCoverIV;
                        markSlashOn();

                        input1TV = carryingFirstSubtractTen10TV;
                        input2TV = carryingFirstSubtractTen1TV;

                        ans = Integer.parseInt(firstSubtractTenTV.getText().toString()) + 10;

                    }
                    break;

                case 14:
                    if ((Integer.parseInt(firstSubtractTenTV.getText().toString()) >=
                            Integer.parseInt(secondMultiplyTenTV.getText().toString())) &&
                            ((Integer.parseInt(firstSubtractOneTV.getText().toString()) <
                                    Integer.parseInt(secondMultiplyOneTV.getText().toString())))) {
                        // 9 >= 7 & 2 < 4

                        operand1TV = carryingFirstSubtractOne10TV;
                        operand2TV = carryingFirstSubtractOne1TV;
                        operand3TV = secondMultiplyOneTV;
                        operand4TV = null;
                        operand5TV = null;

                        input1TV = null;
                        input2TV = remainderOneTV;

                        ans = Integer.parseInt(carryingFirstSubtractOne10TV.getText().toString()) * 10 +
                                Integer.parseInt(carryingFirstSubtractOne1TV.getText().toString()) -
                                Integer.parseInt(secondMultiplyOneTV.getText().toString());

                        if (Integer.parseInt(carryingFirstSubtractTen10TV.getText().toString()) * 10 +
                                Integer.parseInt(carryingFirstSubtractTen1TV.getText().toString()) -
                                Integer.parseInt(secondMultiplyTenTV.getText().toString()) == 0) {
                            //바로 앞 자리(받아내림하고 남은 십의 자리 - 두번째 곱셈 십의 자리)가 0인 경우, 연산 종료
                            isFinal = true;
                        }

                        ansSecondLineV.setVisibility(View.VISIBLE);

                    } else if ((Integer.parseInt(firstSubtractTenTV.getText().toString()) <
                            Integer.parseInt(secondMultiplyTenTV.getText().toString())) &&
                            ((Integer.parseInt(firstSubtractOneTV.getText().toString()) >=
                                    Integer.parseInt(secondMultiplyOneTV.getText().toString())))) {
                        // 1 < 9 & 9 >= 4

                        operand1TV = firstSubtractOneTV;
                        operand2TV = secondMultiplyOneTV;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        input1TV = null;
                        input2TV = remainderOneTV;

                        ans = Integer.parseInt(firstSubtractOneTV.getText().toString()) -
                                Integer.parseInt(secondMultiplyOneTV.getText().toString());

                        if (Integer.parseInt(carryingFirstSubtractTen10TV.getText().toString()) * 10 +
                                Integer.parseInt(carryingFirstSubtractTen1TV.getText().toString()) -
                                Integer.parseInt(secondMultiplyTenTV.getText().toString()) == 0) {
                            //바로 앞 자리(받아내림하고 남은 십의 자리 - 두번째 곱셈 십의 자리)가 0인 경우, 연산 종료
                            isFinal = true;
                        }

                        ansSecondLineV.setVisibility(View.VISIBLE);

                    } else {
                        // 1 < 9 & 1 < 4

                        //레이아웃 2 활성화
                        carryingL2FirstSubtractHundredLL.setVisibility(View.VISIBLE);
                        carryingL2FirstSubtractTenLL.setVisibility(View.VISIBLE);
                        carryingL2FirstSubtractOneLL.setVisibility(View.VISIBLE);

                        operand1TV = carryingFirstSubtractTen10TV;
                        operand2TV = carryingFirstSubtractTen1TV;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        currentMarkIV = carryingFirstSubtractTenCoverIV;
                        markSlashOn();

                        ans = Integer.parseInt(carryingFirstSubtractTen10TV.getText().toString()) * 10 +
                                Integer.parseInt(carryingFirstSubtractTen1TV.getText().toString()) - 1;

                        if (ans < 10) {
                            //2단계 받아내림 값이 10보다 작다면 input은 하나만 입력하도록 한다.
                            input1TV = null;
                        } else {
                            input1TV = carryingL2FirstSubtractTen10TV;
                        }
                        input2TV = carryingL2FirstSubtractTen1TV;

                    }
                    break;

                case 15:

                    if ((Integer.parseInt(firstSubtractTenTV.getText().toString()) >=
                            Integer.parseInt(secondMultiplyTenTV.getText().toString())) &&
                            ((Integer.parseInt(firstSubtractOneTV.getText().toString()) <
                                    Integer.parseInt(secondMultiplyOneTV.getText().toString())))) {
                        // 9 >= 7 & 2 < 4

                        operand1TV = carryingFirstSubtractTen1TV;
                        operand2TV = secondMultiplyTenTV;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        input1TV = null;
                        input2TV = remainderTenTV;

                        ans = Integer.parseInt(carryingFirstSubtractTen1TV.getText().toString()) -
                                Integer.parseInt(secondMultiplyTenTV.getText().toString());

                        isFinal = true;

                    } else if ((Integer.parseInt(firstSubtractTenTV.getText().toString()) <
                            Integer.parseInt(secondMultiplyTenTV.getText().toString())) &&
                            ((Integer.parseInt(firstSubtractOneTV.getText().toString()) >=
                                    Integer.parseInt(secondMultiplyOneTV.getText().toString())))) {
                        // 1 < 9 & 9 >= 4

                        operand1TV = carryingFirstSubtractTen10TV;
                        operand2TV = carryingFirstSubtractTen1TV;
                        operand3TV = secondMultiplyTenTV;
                        operand4TV = null;
                        operand5TV = null;

                        input1TV = null;
                        input2TV = remainderTenTV;

                        ans = Integer.parseInt(carryingFirstSubtractTen10TV.getText().toString()) * 10 +
                                Integer.parseInt(carryingFirstSubtractTen1TV.getText().toString()) -
                                Integer.parseInt(secondMultiplyTenTV.getText().toString());

                        isFinal = true;

                    } else {
                        // 1 < 9 & 1 < 4

                        operand1TV = firstSubtractOneTV;
                        operand2TV = null;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        currentMarkIV = firstSubtractOneCoverIV;
                        markSlashOn();

                        input1TV = carryingFirstSubtractOne10TV;
                        input2TV = carryingFirstSubtractOne1TV;

                        ans = Integer.parseInt(firstSubtractOneTV.getText().toString()) + 10;
                    }
                    break;

                case 16:
                    operand1TV = carryingFirstSubtractOne10TV;
                    operand2TV = carryingFirstSubtractOne1TV;
                    operand3TV = secondMultiplyOneTV;
                    operand4TV = null;
                    operand5TV = null;

                    ansSecondLineV.setVisibility(View.VISIBLE);

                    input1TV = null;
                    input2TV = remainderOneTV;

                    ans = Integer.parseInt(carryingFirstSubtractOne10TV.getText().toString()) * 10 +
                            Integer.parseInt(carryingFirstSubtractOne1TV.getText().toString()) -
                            Integer.parseInt(secondMultiplyOneTV.getText().toString());

                    if (Integer.parseInt(carryingL2FirstSubtractTen10TV.getText().toString()) * 10 +
                            Integer.parseInt(carryingL2FirstSubtractTen1TV.getText().toString()) -
                            Integer.parseInt(secondMultiplyTenTV.getText().toString()) == 0) {
                        //바로 앞 자리(받아내림하고 남은 십의 자리 - 두번째 곱셈 십의 자리)가 0인 경우, 연산 종료
                        isFinal = true;
                    }

                    break;

                case 17:
                    if (Integer.parseInt(carryingL2FirstSubtractTen10TV.getText().toString()) == 0) {
                        operand1TV = null;
                    } else {
                        operand1TV = carryingL2FirstSubtractTen10TV;
                    }
                    operand2TV = carryingL2FirstSubtractTen1TV;
                    operand3TV = secondMultiplyTenTV;
                    operand4TV = null;
                    operand5TV = null;

                    input1TV = null;
                    input2TV = remainderTenTV;

                    ans = Integer.parseInt(carryingL2FirstSubtractTen10TV.getText().toString()) * 10 +
                            Integer.parseInt(carryingL2FirstSubtractTen1TV.getText().toString()) -
                            Integer.parseInt(secondMultiplyTenTV.getText().toString());

                    isFinal = true;

                    break;

                default:
                    break;
            }
        } else {
            switch (currentStage) {
                case 1:
                    operand1TV = divisorTenTV;
                    operand2TV = divisorOneTV;
                    operand3TV = dividendHundredTV;
                    operand4TV = dividendTenTV;
                    operand5TV = dividendOneTV;

                    input1TV = null;
                    input2TV = quotientOneTV;

                    ans = quotientOne;

                    break;

                case 2:
                    operand1TV = divisorOneTV;
                    operand2TV = quotientOneTV;
                    operand3TV = null;
                    operand4TV = null;
                    operand5TV = null;

                    ans = divisorOne * quotientOne;

                    if (ans < 10) {
                        input1TV = null;
                    } else {
                        input1TV = carryingDivisorTenTV;
                    }
                    input2TV = firstMultiplyOneTV;
                    break;

                case 3:
                    operand1TV = divisorTenTV;
                    operand2TV = quotientOneTV;
                    operand3TV = null;
                    operand4TV = null;
                    operand5TV = null;

                    input2TV = firstMultiplyTenTV;

                    ans = divisorTen * quotientOne +
                            Integer.parseInt(carryingDivisorTenTV.getText().toString());

                    if (ans < 10) {
                        input1TV = null;
                    } else {
                        input1TV = firstMultiplyHundredTV;
                    }

                    break;

                case 4:

                    carryingDivisorTenTV.setText("0");
                    carryingDivisorTenTV.setTextColor(Color.WHITE);

                    if (dividendTen >= Integer.parseInt(firstMultiplyTenTV.getText().toString()) &&
                            dividendOne >= Integer.parseInt(firstMultiplyOneTV.getText().toString())) {
                        operand1TV = dividendTenTV;
                        operand2TV = dividendOneTV;
                        operand3TV = firstMultiplyTenTV;
                        operand4TV = firstMultiplyOneTV;
                        operand5TV = null;

                        ans = (dividendTen * 10 + dividendOne) -
                                (Integer.parseInt(firstMultiplyTenTV.getText().toString()) * 10 +
                                        Integer.parseInt(firstMultiplyOneTV.getText().toString()));

                        if (ans < 10) {
                            input1TV = null;
                        } else {
                            input1TV = firstSubtractTenTV;
                        }
                        input2TV = firstSubtractOneTV;

                        ansFirstLineV.setVisibility(View.VISIBLE);

                        isFinal = true;

                    } else if (dividendTen >= Integer.parseInt(firstMultiplyTenTV.getText().toString()) &&
                            dividendOne < Integer.parseInt(firstMultiplyOneTV.getText().toString())) {
                        operand1TV = dividendTenTV;
                        operand2TV = null;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        currentMarkIV = dividendTenCoverIV;
                        markSlashOn();

                        input1TV = null;
                        input2TV = carryingDividendTen1TV;

                        ans = dividendTen - 1;

                    } else {
                        operand1TV = dividendHundredTV;
                        operand2TV = null;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        currentMarkIV = dividendHundredCoverIV;
                        markSlashOn();

                        input1TV = null;
                        input2TV = carryingDividendHundredTV;

                        ans = dividendHundred - 1;
                    }

                    break;

                case 5:

                    if (dividendTen >= Integer.parseInt(firstMultiplyTenTV.getText().toString()) &&
                            dividendOne < Integer.parseInt(firstMultiplyOneTV.getText().toString())) {
                        operand1TV = dividendOneTV;
                        operand2TV = null;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        currentMarkIV = dividendOneCoverIV;
                        markSlashOn();

                        input1TV = carryingDividendOne10TV;
                        input2TV = carryingDividendOne1TV;

                        ans = dividendOne + 10;

                    } else {

                        operand1TV = dividendHundredTV;
                        operand2TV = null;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        currentMarkIV = dividendHundredCoverIV;
                        markSlashOn();

                        input1TV = carryingDividendTen10TV;
                        input2TV = carryingDividendTen1TV;

                        ans = dividendTen + 10;
                    }

                    break;

                case 6:
                    if (dividendTen >= Integer.parseInt(firstMultiplyTenTV.getText().toString()) &&
                            dividendOne < Integer.parseInt(firstMultiplyOneTV.getText().toString())) {

                        operand1TV = carryingDividendOne10TV;
                        operand2TV = carryingDividendOne1TV;
                        operand3TV = firstMultiplyOneTV;
                        operand4TV = null;
                        operand5TV = null;

                        input1TV = null;
                        input2TV = firstSubtractOneTV;

                        ans = Integer.parseInt(carryingDividendOne10TV.getText().toString()) * 10 +
                                Integer.parseInt(carryingDividendOne1TV.getText().toString()) -
                                Integer.parseInt(firstMultiplyOneTV.getText().toString());

                        if (Integer.parseInt(carryingDividendTen10TV.getText().toString()) * 10 +
                                Integer.parseInt(carryingDividendTen1TV.getText().toString()) -
                                Integer.parseInt(firstMultiplyTenTV.getText().toString()) == 0) {
                            //바로 앞 자리(받아내림하고 남은 십의 자리 - 두번째 곱셈 십의 자리)가 0인 경우, 연산 종료
                            isFinal = true;
                        }

                        ansFirstLineV.setVisibility(View.VISIBLE);

                    } else if (dividendTen < Integer.parseInt(firstMultiplyTenTV.getText().toString()) &&
                        dividendOne >= Integer.parseInt(firstMultiplyOneTV.getText().toString())) {
                        operand1TV = dividendOneTV;
                        operand2TV = firstMultiplyOneTV;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        input1TV = null;
                        input2TV = firstSubtractOneTV;

                        ans = dividendOne -
                                Integer.parseInt(firstMultiplyOneTV.getText().toString());

                        if (Integer.parseInt(carryingDividendTen10TV.getText().toString()) * 10 +
                                Integer.parseInt(carryingDividendTen1TV.getText().toString()) -
                                Integer.parseInt(firstMultiplyTenTV.getText().toString()) == 0) {
                            //바로 앞 자리(받아내림하고 남은 십의 자리 - 두번째 곱셈 십의 자리)가 0인 경우, 연산 종료
                            isFinal = true;
                        }

                        ansFirstLineV.setVisibility(View.VISIBLE);

                    } else {

                        //레이아웃 2 활성화
                        carryingL2DividendHundredLL.setVisibility(View.VISIBLE);
                        carryingL2DividendTenLL.setVisibility(View.VISIBLE);
                        carryingL2DividendOneLL.setVisibility(View.VISIBLE);

                        divSignIV.setImageResource(R.drawable.div_sign_long_2);


                        operand1TV = carryingDividendTen10TV;
                        operand2TV = carryingDividendTen1TV;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        currentMarkIV = carryingDividendTenCoverIV;
                        markSlashOn();

                        ans = Integer.parseInt(carryingDividendTen10TV.getText().toString()) * 10 +
                                Integer.parseInt(carryingDividendTen1TV.getText().toString()) - 1;

                        if (ans < 10) {
                            //2단계 받아내림 값이 10보다 작다면 input은 하나만 입력하도록 한다.
                            input1TV = null;
                        } else {
                            input1TV = carryingL2DividendTen10TV;
                        }
                        input2TV = carryingL2DividendTen1TV;

                    }

                    break;

                case 7:
                    if (dividendTen >= Integer.parseInt(firstMultiplyTenTV.getText().toString()) &&
                            dividendOne < Integer.parseInt(firstMultiplyOneTV.getText().toString())) {
                        operand1TV = carryingDividendTen1TV;
                        operand2TV = firstMultiplyTenTV;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        input1TV = null;
                        input2TV = firstSubtractTenTV;

                        ans = Integer.parseInt(carryingDividendTen1TV.getText().toString()) -
                                Integer.parseInt(firstMultiplyTenTV.getText().toString());

                        isFinal = true;

                    } else if (dividendTen < Integer.parseInt(firstMultiplyTenTV.getText().toString()) &&
                            dividendOne >= Integer.parseInt(firstMultiplyOneTV.getText().toString())) {
                        operand1TV = carryingDividendTen10TV;
                        operand2TV = carryingDividendTen1TV;
                        operand3TV = firstMultiplyTenTV;
                        operand4TV = null;
                        operand5TV = null;

                        input1TV = null;
                        input2TV = firstSubtractTenTV;

                        ans = Integer.parseInt(carryingDividendTen10TV.getText().toString()) * 10 +
                                Integer.parseInt(carryingDividendTen1TV.getText().toString()) -
                                Integer.parseInt(firstMultiplyTenTV.getText().toString());

                        isFinal = true;

                    } else {
                        operand1TV = dividendOneTV;
                        operand2TV = null;
                        operand3TV = null;
                        operand4TV = null;
                        operand5TV = null;

                        currentMarkIV = dividendOneCoverIV;
                        markSlashOn();

                        input1TV = carryingDividendOne10TV;
                        input2TV = carryingDividendOne1TV;

                        ans = dividendOne + 10;
                    }

                    break;

                case 8:
                    operand1TV = carryingDividendOne10TV;
                    operand2TV = carryingDividendOne1TV;
                    operand3TV = firstMultiplyOneTV;
                    operand4TV = null;
                    operand5TV = null;

                    ansFirstLineV.setVisibility(View.VISIBLE);

                    input1TV = null;
                    input2TV = firstSubtractOneTV;

                    ans = Integer.parseInt(carryingDividendOne10TV.getText().toString()) * 10 +
                            Integer.parseInt(carryingDividendOne1TV.getText().toString()) -
                            Integer.parseInt(firstMultiplyOneTV.getText().toString());

                    if (Integer.parseInt(carryingL2DividendTen10TV.getText().toString()) * 10 +
                            Integer.parseInt(carryingL2DividendTen1TV.getText().toString()) -
                            Integer.parseInt(firstMultiplyTenTV.getText().toString()) == 0) {
                        //바로 앞 자리(받아내림하고 남은 십의 자리 - 두번째 곱셈 십의 자리)가 0인 경우, 연산 종료
                        isFinal = true;
                    }

                    break;

                case 9:
                    if (Integer.parseInt(carryingL2DividendTen10TV.getText().toString()) == 0) {
                        operand1TV = null;
                    } else {
                        operand1TV = carryingL2DividendTen10TV;
                    }
                    operand2TV = carryingL2DividendTen1TV;
                    operand3TV = firstMultiplyTenTV;
                    operand4TV = null;
                    operand5TV = null;

                    input1TV = null;
                    input2TV = firstSubtractTenTV;

                    ans = Integer.parseInt(carryingL2DividendTen10TV.getText().toString()) * 10 +
                            Integer.parseInt(carryingL2DividendTen1TV.getText().toString()) -
                            Integer.parseInt(firstMultiplyTenTV.getText().toString());

                    isFinal = true;

                    break;

                default:
                    break;

            }
        }

        markOperandAndInput();

//        //곱셈할 각 자리수를 빨간색으로 표시
//        if (operand1TV != null) {
//            operand1TV.setTextColor(Color.RED);
//        }
//        if (operand2TV != null) {
//            operand2TV.setTextColor(Color.RED);
//        }
//        if (operand3TV != null) {
//            operand3TV.setTextColor(Color.RED);
//        }
//        if (operand4TV != null) {
//            operand4TV.setTextColor(Color.RED);
//        }
//        if (operand5TV != null) {
//            operand5TV.setTextColor(Color.RED);
//        }
//
//        //입력할 텍스트 뷰를 임시로 'A'와 'B'로 표시
//        if (input1TV != null) {
//            input1TV.setText("?");
//            input1TV.setTextColor(Color.BLUE);
//        }
//        if (input2TV != null) {
//            input2TV.setText("?");
//            input2TV.setTextColor(Color.BLUE);
//        }
//
//////        if (currentStage < 4 || (currentStage > 8 && currentStage < 12)) {
////        if (currentStage == 2) {
////
////            //곱셈 과정에서 받아올림이 없는 경우
////            if (ans < 10) {
////                input1TV.setText("0");
////                input1TV.setTextColor(Color.WHITE);
//////                multiInput = true;
////            } else {
//////                multiInput = false;
////            }
////            //곱셈 결과를 더하는 과정에서는?
////
////
////        }
//
//        if (input1TV != null && input2TV != null) {
//            multiInput = true;
//        } else {
//            multiInput = false;
//        }


//
//        if (input1TV != null) {
//            inputEntry = 1;
//        } else if (input2TV != null) {
//            inputEntry = 2;
//        } else {
//            inputEntry = 3;
//        }
//
//        inputNext = 1;
//
//        if (currentStage < 7) {
//            //세 자리 수 중 하나가 0이거나 곱셈 결과가 받아올림이 없는 경우
//            if (Integer.parseInt(operand1TV.getText().toString()) == 0 || ans < 10) {
//                input1TV.setText("0");
//                input1TV.setTextColor(Color.WHITE);
//                multiInput = true;
//            } else {
//                multiInput = false;
//            }
//        } else {
//            //곱셈 결과를 더하는 과정에서 받아올림이 없는 경우
//            if (ans < 10) {
//                if (input1TV != null) {
//                    input1TV.setText("0");
//                    input1TV.setTextColor(Color.WHITE);
//                }
//                //carrying = false;
//                multiInput = true;
//            } else {
//                multiInput = false;
//            }
//        }
    }

//    private void markSlashOn() {
//        currentMarkIV.setVisibility(View.VISIBLE);
//        currentMarkIV.setImageResource(R.drawable.slash_red);
//    }
//
//    private void markSlashOff() {
//        //완전히 삭제해버릴 필요는 없겠지?
////        iv.setVisibility(View.INVISIBLE);
//        currentMarkIV.setImageResource(R.drawable.slash_gray);
//        currentMarkIV = null;
//    }

//    private boolean result() {
//        int temp = 0, temp1 = 0, temp2 = 0;
//
//        //temp1에 사용자의 첫번째 입력 값 저장
//        if (input1TV == null) {
//            temp1 = 0;
//        } else {
//            try {
//                temp1 = Integer.parseInt(input1TV.getText().toString());
//                Log.v(LOG_TAG, "temp1 : " + String.valueOf(temp1));
//            } catch (NumberFormatException nfe) {
//                nfe.printStackTrace();
//            }
//        }
//
//        //temp2에 사용자의 두번째 입력 값 저장
//        if (input2TV == null) {
//            temp2 = 0;
//        } else {
//            try {
//                temp2 = Integer.parseInt(input2TV.getText().toString());
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
////            if (input2TV == null) {
////                temp = temp1;
//////                try {
//////                    temp = Integer.parseInt(input1TV.getText().toString());
//////                    Log.v(LOG_TAG, "ans : " + String.valueOf(ans));
//////                    Log.v(LOG_TAG, "temp : " + String.valueOf(temp));
//////                } catch (NumberFormatException nfe) {
//////                    return wrongAnswer(input1TV.getText().toString());
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
//            if (operand1TV != null) {
//                operand1TV.setTextColor(Color.GRAY);
//            }
//            if (operand2TV != null) {
//                operand2TV.setTextColor(Color.GRAY);
//            }
//            if (operand3TV != null) {
//                operand3TV.setTextColor(Color.GRAY);
//            }
//            if (operand4TV != null) {
//                operand4TV.setTextColor(Color.GRAY);
//            }
//            if (operand5TV != null) {
//                operand5TV.setTextColor(Color.GRAY);
//            }
//
//            //입력했던 내용 회색으로 되돌리기
//            if (input1TV != null) {
//                input1TV.setTextColor(Color.GRAY);
//            }
//            if (input2TV != null) {
//                input2TV.setTextColor(Color.GRAY);
//            }
//
//            //받아내림 표시 회색으로 되돌리기
//            if (currentMarkIV != null) {
//                markSlashOff();
//            }
///* 없어도 되지 않는가??????
//
//            //첫 뺄셈 결과 백의 자리가 없는 경우 0을 삭제
//            //이거 어떵할꺼냐!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//            if (currentStage == 8) {
//                if (Integer.parseInt(firstSubtractHundredTV.getText().toString()) == 0) {
//                    firstMultiplyHundredTV.setText("0");
//                    firstMultiplyHundredTV.setTextColor(Color.WHITE);
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
//            if (input1TV != null) {
//                input1TV.setText("?");
//                input1TV.setTextColor(Color.BLUE);
//            }
//            if (input2TV != null) {
//                input2TV.setText("?");
//                input2TV.setTextColor(Color.BLUE);
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
////        if (operand1TV != null) {
////            operand1TV.setTextColor(Color.GRAY);
////        }
////        if (operand2TV != null) {
////            operand2TV.setTextColor(Color.GRAY);
////        }
////        if (operand3TV != null) {
////            operand3TV.setTextColor(Color.GRAY);
////        }
//
//
//        divisorTenTV.setTextColor(Color.GRAY);
//        divisorOneTV.setTextColor(Color.GRAY);
//
//        dividendHundredTV.setTextColor(Color.GRAY);
//        dividendTenTV.setTextColor(Color.GRAY);
//        dividendOneTV.setTextColor(Color.GRAY);
//
//        quotientTenTV.setText(String.valueOf("0"));
//        quotientTenTV.setTextColor(Color.WHITE);
//        quotientOneTV.setText(String.valueOf("0"));
//        quotientOneTV.setTextColor(Color.WHITE);
//
//        firstMultiplyHundredTV.setText(String.valueOf("0"));
//        firstMultiplyHundredTV.setTextColor(Color.WHITE);
//        firstMultiplyTenTV.setText(String.valueOf("0"));
//        firstMultiplyTenTV.setTextColor(Color.WHITE);
//        firstMultiplyOneTV.setText(String.valueOf("0"));
//        firstMultiplyOneTV.setTextColor(Color.WHITE);
//
//        ansFirstLineV.setBackgroundColor(Color.WHITE);
//
//        firstSubtractHundredTV.setText(String.valueOf("0"));
//        firstSubtractHundredTV.setTextColor(Color.WHITE);
//        firstSubtractTenTV.setText(String.valueOf("0"));
//        firstSubtractTenTV.setTextColor(Color.WHITE);
//        firstSubtractOneTV.setText(String.valueOf("0"));
//        firstSubtractOneTV.setTextColor(Color.WHITE);
//
//        secondMultiplyHundredTV.setText(String.valueOf("0"));
//        secondMultiplyHundredTV.setTextColor(Color.WHITE);
//        secondMultiplyTenTV.setText(String.valueOf("0"));
//        secondMultiplyTenTV.setTextColor(Color.WHITE);
//        secondMultiplyOneTV.setText(String.valueOf("0"));
//        secondMultiplyOneTV.setTextColor(Color.WHITE);
//
//        ansSecondLineV.setBackgroundColor(Color.WHITE);
//
//        remainderTenTV.setText(String.valueOf("0"));
//        remainderTenTV.setTextColor(Color.WHITE);
//        remainderOneTV.setText(String.valueOf("0"));
//        remainderOneTV.setTextColor(Color.WHITE);
//    }

//    @Override
//    public void onNumberClicked(int number) {
////        switch (inputNext) {
////            case 1:
////                switch (inputEntry) {
////                    case 1:
////                        if (input1TV != null) {
////                            input1TV.setText(String.valueOf(number));
////                        }
////                        inputNext = 2;
////                        break;
////                    case 2:
////                        input2TV.setText(String.valueOf(number));
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
////                        input2TV.setText(String.valueOf(number));
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
//            if (input2TV != null) {
//                input2TV.setText(String.valueOf(number));
//            }
//        } else {
//            //사용자 입력 처리 : 첫번째 입력인 경우 input1TextView에 값을 입력함
//            if (carrying) {
//                if (input1TV != null) {
//                    input1TV.setText(String.valueOf(number));
//                }
//                carrying = false;
////                Log.v(LOG_TAG, "carrying : " + carrying);
//
//            } else {
//                //두번째 입력인 경우 input2TextView에 값을 입력함
//                if (input2TV != null) {
//                    input2TV.setText(String.valueOf(number));
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
//        if (input1TV != null) {
//            input1TV.setText("?");
//            input1TV.setTextColor(Color.BLUE);
//        }
//        if (input2TV != null) {
//            input2TV.setText("?");
//            input2TV.setTextColor(Color.BLUE);
//        }
//        carrying = true;
//        Log.v(LOG_TAG, "carrying : " + carrying);
//
//
//    }
//
//    public void onOKClicked() {
//        if (input1TV == null) {
//            if (input2TV.getText().toString().matches("[0-9]")) {
//                if (result()) {
//                    nextStage();
//                }            }
//        } else {
//            if (input1TV.getText().toString().matches("[0-9]") && input2TV.getText().toString().matches("[0-9]"))
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

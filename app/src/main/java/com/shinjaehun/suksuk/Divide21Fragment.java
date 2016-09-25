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
import android.widget.TextView;

/**
 * Created by shinjaehun on 2016-04-19.
 */
public class Divide21Fragment extends ProblemFragment {

    private static final String LOG_TAG = Divide21Fragment.class.getSimpleName();
//    public Context mContext = null;

    private int divisor, dividend, quotient;
    private int dividendTen, dividendOne;
    private int divisorOne;
    private int quotientTen, quotientOne;

    private View ansFirstLineV, ansSecondLineV;

    private TextView quotientTenTV, quotientOneTV;

    private TextView carryingDividendTenTV, carryingDividendOne10TV, carryingDividendOne1TV;
    private TextView carryingFirstSubtractTenTV, carryingFirstSubtractOne10TV, carryingFirstSubtractOne1TV;

    private ImageView dividendTenCoverIV, dividendOneCoverIV;
    private ImageView firstSubtractTenCoverIV, firstSubtractOneCoverIV;

    private TextView divisorOneTV;
    private TextView dividendTenTV, dividendOneTV;
    private TextView firstMultiplyTenTV, firstMultiplyOneTV;
    private TextView firstSubtractTenTV, firstSubtractOneTV;
    private TextView secondMultiplyTenTV, secondMultiplyOneTV;
    private TextView remainderOneTV;

//    private TextView operand1TV, operand2TV, operand3TV, operand4TV;
//    private TextView input1TV, input2TV;
//
//    private ImageButton helpBTN;

//    private boolean isFullDivide = true;

    //세개의 inputTextView 입력을 받기 위한 스위치
//    private int inputEntry = 0;
//    private int inputNext = 0;
//    private int inputEntry = 0;
//    private int inputNext = 0;
//
//    //현재 과정
//    private int currentStage = 0;
//
//    //곱셈 결과
//    private int ans = 0;

    public void startPractice() {
        initOperands();
        nextStage();

        startTime = System.nanoTime();
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        this.mContext = context;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_divide21, container, false);

        quotientTenTV = (TextView)v.findViewById(R.id.quotient_ten);
        quotientOneTV = (TextView)v.findViewById(R.id.quotient_one);

        divisorOneTV = (TextView)v.findViewById(R.id.divisor_one);

        carryingDividendTenTV = (TextView)v.findViewById(R.id.carrying_dividend_ten);
        carryingDividendOne10TV = (TextView)v.findViewById(R.id.carrying_dividend_one_10);
        carryingDividendOne1TV = (TextView)v.findViewById(R.id.carrying_dividend_one_1);

        carryingFirstSubtractTenTV = (TextView)v.findViewById(R.id.carrying_first_subtract_ten);
        carryingFirstSubtractOne10TV = (TextView)v.findViewById(R.id.carrying_first_subtract_one_10);
        carryingFirstSubtractOne1TV = (TextView)v.findViewById(R.id.carrying_first_subtract_one_1);

        dividendTenCoverIV = (ImageView)v.findViewById(R.id.dividend_ten_cover);
        dividendOneCoverIV = (ImageView)v.findViewById(R.id.dividend_one_cover);

        firstSubtractTenCoverIV = (ImageView)v.findViewById(R.id.first_subtract_ten_cover);
        firstSubtractOneCoverIV = (ImageView)v.findViewById(R.id.first_subtract_one_cover);

        dividendTenTV = (TextView)v.findViewById(R.id.dividend_ten);
        dividendOneTV = (TextView)v.findViewById(R.id.dividend_one);

        firstMultiplyTenTV = (TextView)v.findViewById(R.id.first_multiply_ten);
        firstMultiplyOneTV = (TextView)v.findViewById(R.id.first_multiply_one);

        ansFirstLineV = (View)v.findViewById(R.id.ans_first_line);

        firstSubtractTenTV = (TextView)v.findViewById(R.id.first_subtract_ten);
        firstSubtractOneTV = (TextView)v.findViewById(R.id.first_subtract_one);

        secondMultiplyTenTV = (TextView)v.findViewById(R.id.second_multiply_ten);
        secondMultiplyOneTV = (TextView)v.findViewById(R.id.second_multiply_one);

        ansSecondLineV = (View)v.findViewById(R.id.ans_second_line);

        remainderOneTV = (TextView)v.findViewById(R.id.remainder_one);

        helpBTN = (ImageButton)v.findViewById(R.id.help);

        challengeCounterTV = (TextView)v.findViewById(R.id.challengeCounter);

        if (isChallenge == false) {
            challengeCounterTV.setVisibility(View.GONE);

//            helpBTN.setOnClickListener(new Button.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
//                    intent.putExtra("help", "divide21");
//                    startActivity(intent);
//                }
//            });
        } else {
//            Log.v(LOG_TAG, "challengeNumber : " + challengeNumber);

//            helpBTN.setBackgroundColor(0x9E9E9E);
//            helpBTN.setText(String.valueOf(challengeNumber));
//            helpBTN.setClickable(false);

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

//        do {
//            dividend = (int) (Math.random() * 90) + 10;
//            divisor = (int) (Math.random() * 90) + 10;
//        } while (dividend < divisor);
//        dividend = 868;
//        divisor = 56;

//        divisor = (int) (Math.random() * 9) + 1;
        //두 자리 나누기 한 자리할 때는 1을 곱하는 문제는 빼자
        //(int)(Math.random() * (max - min + 1) + min)
        divisor = (int) (Math.random() * 8) + 2;
        dividend = (int) (Math.random() * 90) + 10;

//        divisor = 8;
//        dividend = 94;

//        Log.v(LOG_TAG, String.valueOf("divisor : " + divisor));
//        Log.v(LOG_TAG, String.valueOf("dividend : " + dividend));

        quotient = dividend / divisor;

//        top = 798;
//        down = 57;

        dividendTen = dividend / 10 % 10;
        dividendOne = dividend % 10;

        divisorOne = divisor % 10;

        quotientTen = quotient / 10 % 10;
        quotientOne = quotient % 10;

        //몫이 한 자리 수인 나누기, 몫이 두 자리 수인 나누기 구분해서 HelpActivity 실행하기
        if (quotient > 0 && quotient < 10) {
            //몫이 한 자리 수인 나누기
            helpBTN.setOnClickListener(new Button.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
                    intent.putExtra("help", "divide2_1");
                    startActivity(intent);
                }
            });
        } else {
            //몫이 두 자리 수인 나누기
            helpBTN.setOnClickListener(new Button.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
                    intent.putExtra("help", "divide21_10");
                    startActivity(intent);
                }
            });
        }

//        if (dividendTen < divisor) {
//            isFullDivide = false;
//        }

//        Log.v(LOG_TAG, String.valueOf("isFullDivide? : " + String.valueOf(isFullDivide)));


        //피연산자 표시
        dividendTenTV.setText(String.valueOf(dividendTen));
        dividendOneTV.setText(String.valueOf(dividendOne));

        divisorOneTV.setText(String.valueOf(divisorOne));

    }

    public void nextStage() {
        currentStage += 1;
//        Log.v(LOG_TAG, "Current Stage : " + String.valueOf(currentStage));

        //currentStage에 따라 곱셈할 자리수, 곱셈 결과, 입력할 자리수 지정
        switch (currentStage) {
            case 1:
                if (divisor > dividendTen) {
                    //몫이 한 자리인 나눗셈
                    operand1TV = divisorOneTV;
                    operand2TV = dividendTenTV;
                    operand3TV = dividendOneTV;
                    operand4TV = null;

                    input1TV = null;
                    input2TV = quotientOneTV;

                    ans = quotientOne;
                } else {
                    //몫이 두 자리인 나눗셈
                    operand1TV = divisorOneTV;
                    operand2TV = dividendTenTV;
                    operand3TV = null;
                    operand4TV = null;

                    input1TV = null;
                    input2TV = quotientTenTV;

                    ans = quotientTen;
                }

                break;

            case 2:
                operand3TV = null;
                operand4TV = null;

                if (divisor > dividendTen) {
                    //몫이 한 자리인 나눗셈
                    operand1TV = divisorOneTV;
                    operand2TV = quotientOneTV;

                    ans = divisorOne * quotientOne;
                    //나누는 수 * 몫을 했을 때 결과가 두 자리인지, 한 자리 인지 확인
                    if (ans < 10) {
                        input1TV = null;
                    } else {
                        input1TV = firstMultiplyTenTV;
                    }
//                    input1TV = firstMultiplyTenTV;
                    input2TV = firstMultiplyOneTV;

                } else {
                    //몫이 두 자리인 나눗셈 : 나누는 수 * 몫 십의 자리
                    operand1TV = divisorOneTV;
                    operand2TV = quotientTenTV;

                    ans = divisorOne * quotientTen;

                    input1TV = null;
                    input2TV = firstMultiplyTenTV;

                }

                break;

            case 3:
                if (divisor > dividendTen) {
                    //몫이 한 자리인 나눗셈
                    if (dividendOne >=
                            Integer.parseInt(firstMultiplyOneTV.getText().toString())) {
                        //받아내림 없이 바로 뺄셈하고 연산 종료
                        operand1TV = dividendTenTV;
                        operand2TV = dividendOneTV;
                        operand3TV = firstMultiplyTenTV;
                        operand4TV = firstMultiplyOneTV;

                        ans = dividend % divisor;

                        input1TV = null;
                        input2TV = firstSubtractOneTV;

                        ansFirstLineV.setVisibility(View.VISIBLE);


                        isFinal = true;
                    } else {
                        //나누어지는 수 일의 자리가 첫번째 곱셈 일의 자리보다 작아서 받아내림 시작
                        operand1TV = dividendTenTV;
                        operand2TV = null;
                        operand3TV = null;
                        operand4TV = null;

                        currentMarkIV = dividendTenCoverIV;
                        markSlashOn();

                        ans = dividendTen - 1;

                        input1TV = null;
                        input2TV = carryingDividendTenTV;

                    }

                } else {
                    //몫이 두 자리인 나눗셈 : 나누어지는 수 십의 자리와 첫번째 곱셉 십의 자리 뻴셈
                    operand1TV = dividendTenTV;
                    operand2TV = firstMultiplyTenTV;
                    operand3TV = null;
                    operand4TV = null;

                    ans = dividendTen -
                            Integer.parseInt(firstMultiplyTenTV.getText().toString());

                    ansFirstLineV.setVisibility(View.VISIBLE);


                    input1TV = null;
                    input2TV = firstSubtractTenTV;

                }

                break;

            case 4:
                if (Integer.parseInt(firstSubtractTenTV.getText().toString()) == 0) {
                    //첫번째 뺄셈 십의 자리 수가 0일때 지우기
                    firstSubtractTenTV.setText("0");
                    firstSubtractTenTV.setTextColor(Color.WHITE);
                }

                if (divisor > dividendTen && dividendOne <
                        Integer.parseInt(firstMultiplyOneTV.getText().toString())) {
                    //몫이 한 자리인 나눗셈이며
                    //나누는 수 일의 자리가 첫번째 곱셈 일의 자리보다 작아 받아내림
                    //십의 자리에서 내린 값 10 더하기
                    operand1TV = dividendOneTV;
                    operand2TV = null;
                    operand3TV = null;
                    operand4TV = null;

                    currentMarkIV = dividendOneCoverIV;
                    markSlashOn();

                    ans = dividendOne + 10;

                    input1TV = carryingDividendOne10TV;
                    input2TV = carryingDividendOne1TV;

                } else {
                    //몫이 두 자리인 나눗셈 : 나누어지는 수 일의 자리 - 0 (내리기)

                    operand1TV = dividendOneTV;
                    operand2TV = null;
                    operand3TV = null;
                    operand4TV = null;

                    input1TV = null;
                    input2TV = firstSubtractOneTV;

                    ans = dividendOne;
                }

                break;

            case 5:

                if (divisor > dividendTen && dividendOne <
                        Integer.parseInt(firstMultiplyOneTV.getText().toString())) {
                    //몫이 한 자리인 나눗셈이며
                    //나누는 수 일의 자리가 첫번째 곱셈 일의 자리보다 작아 받아내림
                    //받아내린 값 - 첫번째 곱셈 일의 자리
                    operand1TV = carryingDividendOne10TV;
                    operand2TV = carryingDividendOne1TV;
                    operand3TV = firstMultiplyOneTV;
                    operand4TV = null;

                    ans = Integer.parseInt(carryingDividendOne10TV.getText().toString()) * 10 +
                            Integer.parseInt(carryingDividendOne1TV.getText().toString()) -
                            Integer.parseInt(firstMultiplyOneTV.getText().toString());

                    ansFirstLineV.setVisibility(View.VISIBLE);

                    input1TV = null;
                    input2TV = firstSubtractOneTV;

                    isFinal = true;
                } else {
                    //몫이 두 자리수인 나눗셈 : 몫 일의 자리 구하는 과정
                    operand1TV = divisorOneTV;

                    if (Integer.parseInt(firstSubtractTenTV.getText().toString()) == 0) {
                        //첫번째 뺄셈 결과 일의 자리만 남아 있을 수 있음
                        operand2TV = null;
                    } else {
                        operand2TV = firstSubtractTenTV;
                    }
                    operand3TV = firstSubtractOneTV;
                    operand4TV = null;

                    ans = quotientOne;

                    input1TV = null;
                    input2TV = quotientOneTV;

                    if (divisor >
                            Integer.parseInt(firstSubtractTenTV.getText().toString()) * 10 +
                                    Integer.parseInt(firstSubtractOneTV.getText().toString())) {
                        //첫번째 뺄셈 결과가 나누는 수 보다 작으면 연산 종료
                        isFinal = true;
                    }
                }
                break;

            case 6:
                //몫이 두자리인 나눗셈 : 나누는 수 * 몫 십의 자리
                operand1TV = divisorOneTV;
                operand2TV = quotientOneTV;
                operand3TV = null;
                operand4TV = null;

                ans = divisorOne * quotientOne;

                if (ans < 10) {
                    //나누는 수 * 몫 십의 자리가 한 자리 수인 경우
                    input1TV = null;
                } else {
                    input1TV = secondMultiplyTenTV;
                }
                input2TV = secondMultiplyOneTV;

                break;

            case 7:
                if (Integer.parseInt(firstSubtractOneTV.getText().toString()) >=
                        Integer.parseInt(secondMultiplyOneTV.getText().toString())) {
                    //몫이 두 자리인 나눗셈 : 첫번째 뺄셈 - 두번째 곱셈, 받아내림이 없는 경우
                    if (Integer.parseInt(firstSubtractTenTV.getText().toString()) == 0) {
                        //첫번째 뺄셈이 일의 자리이면 십의 자리 0은 삭제
                        operand1TV = null;
                    } else {
                        operand1TV = firstSubtractTenTV;
                    }
                    operand2TV = firstSubtractOneTV;

                    if (Integer.parseInt(secondMultiplyTenTV.getText().toString()) == 0) {
                        //두번째 곱셈이 일의 자리이면 십의 자리 0은 삭제
                        operand3TV = null;
                    } else {
                        operand3TV = secondMultiplyTenTV;
                    }
                    operand4TV = secondMultiplyOneTV;

                    ans = dividend % divisor;

                    ansSecondLineV.setVisibility(View.VISIBLE);

                    input1TV = null;
                    input2TV = remainderOneTV;

                    isFinal = true;

                } else {
                    //몫이 한 자리인 나눗셈 : 첫번째 뺄셈 십의 자리에서 받아내림 시작
                    operand1TV = firstSubtractTenTV;
                    operand2TV = null;
                    operand3TV = null;
                    operand4TV = null;

                    currentMarkIV = firstSubtractTenCoverIV;
                    markSlashOn();

                    ans = Integer.parseInt(firstSubtractTenTV.getText().toString()) -1;

                    input1TV = null;
                    input2TV = carryingFirstSubtractTenTV;

                }

                break;

            case 8:
                //몫이 한 자리인 나눗셈 : 받아내림 계속
                operand1TV = firstSubtractOneTV;
                operand2TV = null;
                operand3TV = null;
                operand4TV = null;

                currentMarkIV = firstSubtractOneCoverIV;
                markSlashOn();

                ans = Integer.parseInt(firstSubtractOneTV.getText().toString()) + 10;

                input1TV = carryingFirstSubtractOne10TV;
                input2TV = carryingFirstSubtractOne1TV;

                break;

            case 9:
                //몫이 한 자리인 나눗셈 : 마무리
                operand1TV = carryingFirstSubtractOne10TV;
                operand2TV = carryingFirstSubtractOne1TV;
                operand3TV = secondMultiplyOneTV;
                operand4TV = null;

                ans = dividend % divisor;

                ansSecondLineV.setVisibility(View.VISIBLE);

                input1TV = null;
                input2TV = remainderOneTV;

                isFinal = true;

                break;


            default:
                break;
        }

        markOperandAndInput();

//
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
//
//        //입력할 텍스트 뷰를 임시로 'A'와 'B'로 표시
//        if (input1TV != null) {
//            input1TV.setText("?");
//            input1TV.setTextColor(Color.BLACK);
//        }
//        if (input2TV != null) {
//            input2TV.setText("?");
//            input2TV.setTextColor(Color.BLACK);
//        }
//
//
//        if (input1TV != null) {
//            inputEntry = 1;
//        } else {
//            inputEntry = 2;
//        }
//
//        inputNext = 1;

//
//        if (currentStage < 7) {
//            //세 자리 수 중 하나가 0이거나 곱셈 결과가 받아올림이 없는 경우
//            if (Integer.parseInt(operand1TV.getText().toString()) == 0 || ans < 10) {
//                input1TV.setText("0");
//                input1TV.setTextColor(Color.WHITE);
//                zeroCarrying = true;
//            } else {
//                zeroCarrying = false;
//            }
//        } else {
//            //곱셈 결과를 더하는 과정에서 받아올림이 없는 경우
//            if (ans < 10) {
//                if (input1TV != null) {
//                    input1TV.setText("0");
//                    input1TV.setTextColor(Color.WHITE);
//                }
//                //carrying = false;
//                zeroCarrying = true;
//            } else {
//                zeroCarrying = false;
//            }
//        }
    }

//    private boolean result() {
//        int temp = 0, temp1 = 0, temp2 = 0;
//
//        //temp1에 사용자의 첫번째 입력 값 저장
//        if (input1TV == null || !input1TV.getText().toString().matches("[0-9]")) {
////            if (input1TV == null) {
//                temp1 = 0;
//        } else {
//            try {
//                temp1 = Integer.parseInt(input1TV.getText().toString());
//                Log.v(LOG_TAG, "temp1 : " + String.valueOf(temp1));
//            } catch (NumberFormatException nfe) {
//                nfe.printStackTrace();
//            }
//        }
//
//
//        //temp2에 사용자의 두번째 입력 값 저장
//        if (input2TV == null || !input2TV.getText().toString().matches("[0-9]")) {
////            if (input2TV == null) {
//
//                temp2 = 0;
//        } else {
//            try {
//                temp2 = Integer.parseInt(input2TV.getText().toString());
//                Log.v(LOG_TAG, "temp2 : " + String.valueOf(temp2));
//            } catch (NumberFormatException nfe) {
//                nfe.printStackTrace();
//            }
//        }
//
//        temp = temp1 * 10 + temp2;
//        Log.v(LOG_TAG, "ans : " + ans );
//
//        Log.v(LOG_TAG, "temp : " + temp );
//
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
////            Toast toast = Toast.makeText(getActivity(), "딩동댕", Toast.LENGTH_SHORT);
////            toast.setGravity(Gravity.CENTER, 0, 0);
////            toast.show();
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
//
//            //모든 연산이 끝나면
//            if ((currentStage == 3 && !isFullDivide) || currentStage == 7) {
//                finalStage();
//                return false;
//                //여기에 false를 넣지 않으면 finalStage로 가면서 nextStage()가 한번 더 실행된다!!
//                //잡기 어려웠던 버그 중 하나!
//            }
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
//            //inputTV 다시 원위치
//            if (input1TV != null) {
//                input1TV.setText("?");
//                input1TV.setTextColor(Color.BLACK);
//            }
//            if (input2TV != null) {
//                input2TV.setText("?");
//                input2TV.setTextColor(Color.BLACK);
//            }
//            return false;
//
//        }
//    }
//
//    //flashText()와 finalStage()를 ProblemFragment로 옮겼다.
////    private void finalStage() {
////        /* finalStage()에서 값을 초기화한 후 다시 nextStage()를 호출하지는 않는데
////        * 어차피 입력 버튼을 누르면 nextStage()를 호출하기 때문 */
////
////        Toast toastR = Toast.makeText(getActivity(), "축하합니다!", Toast.LENGTH_LONG);
////        toastR.setGravity(Gravity.CENTER, 0, 0);
////        toastR.show();
////
////        //모든 변수 초기화
////        currentStage = 0;
//////        zeroCarrying = false;
////        ans = 0;
////
////        isFullDivide = true;
////
////        //피연산자 초기화
////        initOperands();
////        //피연산자를 제외한 나머지 모든 숫자 초기화
////        initNumbers();
////    }
//
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
//        divisorOneTV.setTextColor(Color.GRAY);
//
//        dividendTenTV.setTextColor(Color.GRAY);
//        dividendOneTV.setTextColor(Color.GRAY);
//
//        quotientTenTV.setText(String.valueOf("0"));
//        quotientTenTV.setTextColor(Color.WHITE);
//        quotientOneTV.setText(String.valueOf("0"));
//        quotientOneTV.setTextColor(Color.WHITE);
//
//        firstMultiplyTenTV.setText(String.valueOf("0"));
//        firstMultiplyTenTV.setTextColor(Color.WHITE);
//        firstMultiplyOneTV.setText(String.valueOf("0"));
//        firstMultiplyOneTV.setTextColor(Color.WHITE);
//
//        firstSubtractTenTV.setText(String.valueOf("0"));
//        firstSubtractTenTV.setTextColor(Color.WHITE);
//        firstSubtractOneTV.setText(String.valueOf("0"));
//        firstSubtractOneTV.setTextColor(Color.WHITE);
//
//        secondMultiplyTenTV.setText(String.valueOf("0"));
//        secondMultiplyTenTV.setTextColor(Color.WHITE);
//        secondMultiplyOneTV.setText(String.valueOf("0"));
//        secondMultiplyOneTV.setTextColor(Color.WHITE);
//
//        ansFirstLineV.setBackgroundColor(Color.WHITE);
//        ansSecondLineV.setBackgroundColor(Color.WHITE);
//
//        remainderOneTV.setText(String.valueOf("0"));
//        remainderOneTV.setTextColor(Color.WHITE);
//    }
//
//    @Override
//    public void onNumberClicked(int number) {
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
//            } else {
//                //두번째 입력인 경우 input2TextView에 값을 입력함
//                if (input2TV != null) {
//                    input2TV.setText(String.valueOf(number));
//                }
//                carrying = true;
//            }
//        }
//    }
//
//    public void onClearClicked() {
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

package com.shinjaehun.suksuk;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

    private View ans_first_line, ans_second_line;

    private TextView quotient_ten, quotient_one;

    private TextView carrying_dividend_ten, carrying_dividend_one_10, carrying_dividend_one_1;

    private ImageView dividend_ten_cover, dividend_one_cover;

    private TextView divisor_one;
    private TextView dividend_ten, dividend_one;
    private TextView first_multiply_ten, first_multiply_one;
    private TextView first_subtract_ten, first_subtract_one;
    private TextView second_multiply_ten, second_multiply_one;
    private TextView remainder_one;

//    private TextView operand1TextView, operand2TextView, operand3TextView, operand4TextView;
//    private TextView input1TextView, input2TextView;
//
//    private ImageButton help;

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

        quotient_ten = (TextView)v.findViewById(R.id.quotient_ten);
        quotient_one = (TextView)v.findViewById(R.id.quotient_one);

        divisor_one = (TextView)v.findViewById(R.id.divisor_one);

        carrying_dividend_ten = (TextView)v.findViewById(R.id.carrying_dividend_ten);
        carrying_dividend_one_10 = (TextView)v.findViewById(R.id.carrying_dividend_one_10);
        carrying_dividend_one_1 = (TextView)v.findViewById(R.id.carrying_dividend_one_1);

        dividend_ten_cover = (ImageView)v.findViewById(R.id.dividend_ten_cover);
        dividend_one_cover = (ImageView)v.findViewById(R.id.dividend_one_cover);

        dividend_ten = (TextView)v.findViewById(R.id.dividend_ten);
        dividend_one = (TextView)v.findViewById(R.id.dividend_one);

        first_multiply_ten = (TextView)v.findViewById(R.id.first_multiply_ten);
        first_multiply_one = (TextView)v.findViewById(R.id.first_multiply_one);

        ans_first_line = (View)v.findViewById(R.id.ans_first_line);

        first_subtract_ten = (TextView)v.findViewById(R.id.first_subtract_ten);
        first_subtract_one = (TextView)v.findViewById(R.id.first_subtract_one);

        second_multiply_ten = (TextView)v.findViewById(R.id.second_multiply_ten);
        second_multiply_one = (TextView)v.findViewById(R.id.second_multiply_one);

        ans_second_line = (View)v.findViewById(R.id.ans_second_line);

        remainder_one = (TextView)v.findViewById(R.id.remainder_one);

        help = (ImageButton)v.findViewById(R.id.help);
        help.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
                intent.putExtra("help", "divide21");
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

//        do {
//            dividend = (int) (Math.random() * 90) + 10;
//            divisor = (int) (Math.random() * 90) + 10;
//        } while (dividend < divisor);
//        dividend = 868;
//        divisor = 56;

        divisor = (int) (Math.random() * 9) + 1;
        dividend = (int) (Math.random() * 90) + 10;

        divisor = 8;
        dividend = 23;

        Log.v(LOG_TAG, String.valueOf("divisor : " + divisor));
        Log.v(LOG_TAG, String.valueOf("dividend : " + dividend));

        quotient = dividend / divisor;

//        top = 798;
//        down = 57;

        dividendTen = dividend / 10 % 10;
        dividendOne = dividend % 10;

        divisorOne = divisor % 10;

        quotientTen = quotient / 10 % 10;
        quotientOne = quotient % 10;

//        if (dividendTen < divisor) {
//            isFullDivide = false;
//        }

//        Log.v(LOG_TAG, String.valueOf("isFullDivide? : " + String.valueOf(isFullDivide)));


        //피연산자 표시
        dividend_ten.setText(String.valueOf(dividendTen));
        dividend_one.setText(String.valueOf(dividendOne));

        divisor_one.setText(String.valueOf(divisorOne));

    }

    public void nextStage() {
        currentStage += 1;
        Log.v(LOG_TAG, "Current Stage : " + String.valueOf(currentStage));

        //currentStage에 따라 곱셈할 자리수, 곱셈 결과, 입력할 자리수 지정
        switch (currentStage) {
            case 1:
                if (divisor > dividendTen) {
                    //몫이 한 자리인 나눗셈
                    operand1TextView = divisor_one;
                    operand2TextView = dividend_ten;
                    operand3TextView = dividend_one;
                    operand4TextView = null;

                    input1TextView = null;
                    input2TextView = quotient_one;

                    ans = quotientOne;
                } else {
                    //몫이 두 자리인 나눗셈
                    operand1TextView = divisor_one;
                    operand2TextView = dividend_ten;
                    operand3TextView = null;
                    operand4TextView = null;

                    input1TextView = null;
                    input2TextView = quotient_ten;

                    ans = quotientTen;
                }

                break;

            case 2:
                operand3TextView = null;
                operand4TextView = null;

                if (divisor > dividendTen) {
                    //몫이 한 자리인 나눗셈
                    operand1TextView = divisor_one;
                    operand2TextView = quotient_one;

                    ans = divisorOne * quotientOne;
                    //나누는 수 * 몫을 했을 때 결과가 두 자리인지, 한 자리 인지 확인
                    if (ans < 10) {
                        input1TextView = null;
                    } else {
                        input1TextView = first_multiply_ten;
                    }
//                    input1TextView = first_multiply_ten;
                    input2TextView = first_multiply_one;

                }  else {
                    //몫이 두 자리인 나눗셈 : 나누는 수 * 몫 십의 자리
                    operand1TextView = divisor_one;
                    operand2TextView = quotient_ten;

                    ans = divisorOne * quotientTen;

                    input1TextView = null;
                    input2TextView = first_multiply_ten;

                }

                break;

            case 3:
                if (divisor > dividendTen) {
                    //몫이 한 자리인 나눗셈
                    if(dividendOne >=
                            Integer.parseInt(first_multiply_one.getText().toString())) {
                        //받아내림 없이 바로 뺄셈하고 연산 종료
                        operand1TextView = dividend_ten;
                        operand2TextView = dividend_one;
                        operand3TextView = first_multiply_ten;
                        operand4TextView = first_multiply_one;

                        ans = dividend % divisor;

                        input1TextView = null;
                        input2TextView = first_subtract_one;

                        ans_first_line.setVisibility(View.VISIBLE);

                        isFinal = true;
                    } else {
                        //나누어지는 수 일의 자리가 첫번째 곱셈 일의 자리보다 작아서 받아내림 시작
                        operand1TextView = dividend_ten;
                        operand2TextView = null;
                        operand3TextView = null;
                        operand4TextView = null;

                        currentMark = dividend_ten_cover;
                        markSlashOn();

                        ans = dividendTen - 1;

                        input1TextView = null;
                        input2TextView = carrying_dividend_ten;

                    }

                } else {
                    //몫이 두 자리인 나눗셈 : 나누어지는 수 십의 자리와 첫번째 곱셉 십의 자리 뻴셈
                    operand1TextView = dividend_ten;
                    operand2TextView = first_multiply_ten;
                    operand3TextView = null;
                    operand4TextView = null;

                    ans = dividendTen -
                            Integer.parseInt(first_multiply_ten.getText().toString());

                    ans_first_line.setVisibility(View.VISIBLE);


                    input1TextView = null;
                    input2TextView = first_subtract_ten;

                }

                break;

            case 4:
                if (Integer.parseInt(first_subtract_ten.getText().toString()) == 0) {
                    //첫번째 뺄셈 십의 자리 수가 0일때 지우기
                    first_subtract_ten.setText("0");
                    first_subtract_ten.setTextColor(Color.WHITE);
                }

                if (divisor > dividendTen && dividendOne <
                    Integer.parseInt(first_multiply_one.getText().toString())) {
                    //몫이 한 자리인 나눗셈이며
                    //나누는 수 일의 자리가 첫번째 곱셈 일의 자리보다 작아 받아내림
                    //십의 자리에서 내린 값 10 더하기
                    operand1TextView = dividend_one;
                    operand2TextView = null;
                    operand3TextView = null;
                    operand4TextView = null;

                    currentMark = dividend_one_cover;
                    markSlashOn();

                    ans = dividendOne + 10;

                    input1TextView = carrying_dividend_one_10;
                    input2TextView = carrying_dividend_one_1;

                } else {
                    //몫이 두 자리인 나눗셈 : 나누어지는 수 일의 자리 - 0 (내리기)

                    operand1TextView = dividend_one;
                    operand2TextView = null;
                    operand3TextView = null;
                    operand4TextView = null;

                    input1TextView = null;
                    input2TextView = first_subtract_one;

                    ans = dividendOne;
                }

                break;

            case 5:

                if (divisor > dividendTen && dividendOne <
                        Integer.parseInt(first_multiply_one.getText().toString())) {
                    //몫이 한 자리인 나눗셈이며
                    //나누는 수 일의 자리가 첫번째 곱셈 일의 자리보다 작아 받아내림
                    //받아내린 값 - 첫번째 곱셈 일의 자리
                    operand1TextView = carrying_dividend_one_10;
                    operand2TextView = carrying_dividend_one_1;
                    operand3TextView = first_multiply_one;
                    operand4TextView = null;

                    ans = Integer.parseInt(carrying_dividend_one_10.getText().toString()) * 10 +
                            Integer.parseInt(carrying_dividend_one_1.getText().toString()) -
                            Integer.parseInt(first_multiply_one.getText().toString());

                    ans_first_line.setVisibility(View.VISIBLE);

                    input1TextView = null;
                    input2TextView = first_subtract_one;

                    isFinal = true;
                } else {
                    //몫이 두 자리수인 나눗셈 : 몫 일의 자리 구하는 과정
                    operand1TextView = divisor_one;

                    if (Integer.parseInt(first_subtract_ten.getText().toString()) == 0) {
                        //첫번째 뺄셈 결과 일의 자리만 남아 있을 수 있음
                        operand2TextView = null;
                    } else {
                        operand2TextView = first_subtract_ten;
                    }
                    operand3TextView = first_subtract_one;
                    operand4TextView = null;

                    ans = quotientOne;

                    input1TextView = null;
                    input2TextView = quotient_one;

                    if (divisor >
                            Integer.parseInt(first_subtract_ten.getText().toString()) * 10 +
                                    Integer.parseInt(first_subtract_one.getText().toString())) {
                        //첫번째 뺄셈 결과가 나누는 수 보다 작으면 연산 종료
                        isFinal = true;
                    }
                }
                break;

            case 6:
                //몫이 두자리인 나눗셈 : 나누는 수 * 몫 십의 자리
                operand1TextView = divisor_one;
                operand2TextView = quotient_one;
                operand3TextView = null;
                operand4TextView = null;

                ans = divisorOne * quotientOne;

                if (ans < 10) {
                    //나누는 수 * 몫 십의 자리가 한 자리 수인 경우
                    input1TextView = null;
                } else {
                    input1TextView = second_multiply_ten;
                }
                input2TextView = second_multiply_one;

                break;

            case 7:
                //몫이 두 자리인 나눗셈 : 첫번째 뺄셈 - 두번째 곱셈
                if (Integer.parseInt(first_subtract_ten.getText().toString()) == 0) {
                    //첫번째 뺄셈이 일의 자리이면 십의 자리 0은 삭제
                    operand1TextView = null;
                } else {
                    operand1TextView = first_subtract_ten;
                }
                operand2TextView = first_subtract_one;

                if (Integer.parseInt(second_multiply_ten.getText().toString()) == 0) {
                    //두번째 곱셈이 일의 자리이면 십의 자리 0은 삭제
                    operand3TextView = null;
                } else {
                    operand3TextView = second_multiply_ten;
                }
                operand4TextView = second_multiply_one;

                ans = dividend % divisor;

                ans_second_line.setVisibility(View.VISIBLE);

                input1TextView = null;
                input2TextView = remainder_one;

                isFinal = true;

                break;

            default:
                break;
        }

        markOperandAndInput();


//
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
//
//        //입력할 텍스트 뷰를 임시로 'A'와 'B'로 표시
//        if (input1TextView != null) {
//            input1TextView.setText("?");
//            input1TextView.setTextColor(Color.BLACK);
//        }
//        if (input2TextView != null) {
//            input2TextView.setText("?");
//            input2TextView.setTextColor(Color.BLACK);
//        }
//
//
//        if (input1TextView != null) {
//            inputEntry = 1;
//        } else {
//            inputEntry = 2;
//        }
//
//        inputNext = 1;

//
//        if (currentStage < 7) {
//            //세 자리 수 중 하나가 0이거나 곱셈 결과가 받아올림이 없는 경우
//            if (Integer.parseInt(operand1TextView.getText().toString()) == 0 || ans < 10) {
//                input1TextView.setText("0");
//                input1TextView.setTextColor(Color.WHITE);
//                zeroCarrying = true;
//            } else {
//                zeroCarrying = false;
//            }
//        } else {
//            //곱셈 결과를 더하는 과정에서 받아올림이 없는 경우
//            if (ans < 10) {
//                if (input1TextView != null) {
//                    input1TextView.setText("0");
//                    input1TextView.setTextColor(Color.WHITE);
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
//        if (input1TextView == null || !input1TextView.getText().toString().matches("[0-9]")) {
////            if (input1TextView == null) {
//                temp1 = 0;
//        } else {
//            try {
//                temp1 = Integer.parseInt(input1TextView.getText().toString());
//                Log.v(LOG_TAG, "temp1 : " + String.valueOf(temp1));
//            } catch (NumberFormatException nfe) {
//                nfe.printStackTrace();
//            }
//        }
//
//
//        //temp2에 사용자의 두번째 입력 값 저장
//        if (input2TextView == null || !input2TextView.getText().toString().matches("[0-9]")) {
////            if (input2TextView == null) {
//
//                temp2 = 0;
//        } else {
//            try {
//                temp2 = Integer.parseInt(input2TextView.getText().toString());
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
////            Toast toast = Toast.makeText(getActivity(), "딩동댕", Toast.LENGTH_SHORT);
////            toast.setGravity(Gravity.CENTER, 0, 0);
////            toast.show();
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
//            if (input1TextView != null) {
//                input1TextView.setText("?");
//                input1TextView.setTextColor(Color.BLACK);
//            }
//            if (input2TextView != null) {
//                input2TextView.setText("?");
//                input2TextView.setTextColor(Color.BLACK);
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
//        divisor_one.setTextColor(Color.GRAY);
//
//        dividend_ten.setTextColor(Color.GRAY);
//        dividend_one.setTextColor(Color.GRAY);
//
//        quotient_ten.setText(String.valueOf("0"));
//        quotient_ten.setTextColor(Color.WHITE);
//        quotient_one.setText(String.valueOf("0"));
//        quotient_one.setTextColor(Color.WHITE);
//
//        first_multiply_ten.setText(String.valueOf("0"));
//        first_multiply_ten.setTextColor(Color.WHITE);
//        first_multiply_one.setText(String.valueOf("0"));
//        first_multiply_one.setTextColor(Color.WHITE);
//
//        first_subtract_ten.setText(String.valueOf("0"));
//        first_subtract_ten.setTextColor(Color.WHITE);
//        first_subtract_one.setText(String.valueOf("0"));
//        first_subtract_one.setTextColor(Color.WHITE);
//
//        second_multiply_ten.setText(String.valueOf("0"));
//        second_multiply_ten.setTextColor(Color.WHITE);
//        second_multiply_one.setText(String.valueOf("0"));
//        second_multiply_one.setTextColor(Color.WHITE);
//
//        ans_first_line.setBackgroundColor(Color.WHITE);
//        ans_second_line.setBackgroundColor(Color.WHITE);
//
//        remainder_one.setText(String.valueOf("0"));
//        remainder_one.setTextColor(Color.WHITE);
//    }
//
//    @Override
//    public void onNumberClicked(int number) {
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
//            } else {
//                //두번째 입력인 경우 input2TextView에 값을 입력함
//                if (input2TextView != null) {
//                    input2TextView.setText(String.valueOf(number));
//                }
//                carrying = true;
//            }
//        }
//    }
//
//    public void onClearClicked() {
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

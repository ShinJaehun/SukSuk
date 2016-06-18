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
import android.widget.TextView;

/**
 * Created by shinjaehun on 2016-04-19.
 */
public class Divide22Fragment extends ProblemFragment implements NumberpadClickListener {

    private static final String LOG_TAG = Divide22Fragment.class.getSimpleName();
//    public Context mContext = null;

    private int divisor, dividend, quotient;
    private int dividendTen, dividendOne;
    private int divisorTen, divisorOne;
    private int quotientOne;

    private View ans_first_line;

    private TextView quotient_one;

    private TextView carrying_divisor_ten;

    private TextView divisor_ten, divisor_one;

    private TextView carrying_dividend_ten, carrying_dividend_one_10, carrying_dividend_one_1;

    private ImageView dividend_ten_cover, dividend_one_cover;

    private TextView dividend_ten, dividend_one;
    private TextView first_multiply_ten, first_multiply_one;
    private TextView remainder_ten, remainder_one;

//    private TextView operand1TextView, operand2TextView, operand3TextView, operand4TextView;
//    private TextView input1TextView, input2TextView;
//
//    private Button help;

//    private ImageView currentMark;

//    //세개의 inputTextView 입력을 받기 위한 스위치
//    private int inputEntry = 0;
//    private int inputNext = 0;

//    private boolean carrying = true;
//    private boolean multiInput = false;
//    private boolean isFinal = false;
//
//    //현재 과정
//    int currentStage = 0;
//
//    //곱셈 결과
//    int ans = 0;

    public void startPractice() {
        initOperands();
        nextStage();
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        this.mContext = context;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_divide22, container, false);

        quotient_one = (TextView)v.findViewById(R.id.quotient_one);

        carrying_divisor_ten = (TextView)v.findViewById(R.id.carrying_divisor_ten);

        divisor_ten = (TextView)v.findViewById(R.id.divisor_ten);
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

        remainder_ten = (TextView)v.findViewById(R.id.remainder_ten);
        remainder_one = (TextView)v.findViewById(R.id.remainder_one);

        help = (Button)v.findViewById(R.id.help);
        help.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
                intent.putExtra("help", "divide22");
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

        int a = (int) (Math.random() * 90) + 10;
        int b = (int) (Math.random() * 90) + 10;

        if (a > b) {
            dividend = a;
            divisor = b;
        } else {
            dividend = b;
            divisor = a;
        }

        quotient = dividend / divisor;

//        top = 798;
//        down = 57;

        dividendTen = dividend / 10 % 10;
        dividendOne = dividend % 10;

        divisorTen = divisor / 10 % 10;
        divisorOne = divisor % 10;

        quotientOne = quotient % 10;

        //피연산자 표시
        dividend_ten.setText(String.valueOf(dividendTen));
        dividend_one.setText(String.valueOf(dividendOne));

        divisor_ten.setText(String.valueOf(divisorTen));
        divisor_one.setText(String.valueOf(divisorOne));

    }

    private void nextStage() {
        currentStage += 1;
        Log.v(LOG_TAG, "Current Stage : " + String.valueOf(currentStage));

        //currentStage에 따라 곱셈할 자리수, 곱셈 결과, 입력할 자리수 지정
        switch (currentStage) {
            case 1:
                operand1TextView = divisor_ten;
                operand2TextView = divisor_one;
                operand3TextView = dividend_ten;
                operand4TextView = dividend_one;

                input1TextView = null;
                input2TextView = quotient_one;

                ans = quotientOne;

                break;

            case 2:
                operand1TextView = divisor_one;
                operand2TextView = quotient_one;
                operand3TextView = null;
                operand4TextView = null;

                ans = divisorOne * quotientOne;

                if (ans < 10) {
                    //받아올림이 없으면 한 자리만 입력함
                    input1TextView = null;
                } else {
                    input1TextView = carrying_divisor_ten;
                }
                input2TextView = first_multiply_one;

                break;

            case 3:
                operand1TextView = divisor_ten;
                operand2TextView = quotient_one;
                operand3TextView = null;
                operand4TextView = null;

//                ans_first_line.setBackgroundColor(Color.GRAY);

//                ans = dividend - (divisor * quotientOne);

                ans = divisorTen * quotientOne +
                        Integer.parseInt(carrying_divisor_ten.getText().toString());

                input1TextView = null;
                input2TextView = first_multiply_ten;

                break;

            case 4:
                //나누는 수 받아올림 삭제
                carrying_divisor_ten.setText("0");
                carrying_divisor_ten.setTextColor(Color.WHITE);

                if (dividendOne >= Integer.parseInt(first_multiply_one.getText().toString())) {
                    //받아내림이 없다면

                    operand1TextView = dividend_ten;
                    operand2TextView = dividend_one;
                    operand3TextView = first_multiply_ten;
                    operand4TextView = first_multiply_one;

                    ans_first_line.setVisibility(View.VISIBLE);

                    ans = dividend % divisor;

                    if (ans < 10) {
                        //뺄셈 결과 일의 자리라면 입력은 하나만
                        input1TextView = null;
                    } else {
                        input1TextView = remainder_ten;
                    }
                    input2TextView = remainder_one;

                    isFinal = true;
                    //뺄셈 후 연산 종료

                } else {
                    //받아내림이 있다면

                    operand1TextView = dividend_ten;
                    operand2TextView = null;
                    operand3TextView = null;
                    operand4TextView = null;

                    currentMark = dividend_ten_cover;
                    markSlashOn();

                    input1TextView = null;
                    input2TextView = carrying_dividend_ten;

                    ans = dividendTen - 1;

                }

                break;

            case 5:
                operand1TextView = dividend_one;
                operand2TextView = null;
                operand3TextView = null;
                operand4TextView = null;

                currentMark = dividend_one_cover;
                markSlashOn();

//                if (ans < 10) {
//                    //받아내림 결과가 10보다 작다면
//                    input1TextView = null;
//                } else {
//                    input1TextView = carrying_dividend_ten;
//                }
//                이게 필요 없는게 어차피 받아내림 결과가 10보다 클 거 아냐...

                ans = dividendOne + 10;

                input1TextView = carrying_dividend_one_10;
                input2TextView = carrying_dividend_one_1;

                break;

            case 6:
                operand1TextView = carrying_dividend_one_10;
                operand2TextView = carrying_dividend_one_1;
                operand3TextView = first_multiply_one;
                operand4TextView = null;

                ans = Integer.parseInt(carrying_dividend_one_10.getText().toString()) * 10 +
                    Integer.parseInt(carrying_dividend_one_1.getText().toString()) -
                    Integer.parseInt(first_multiply_one.getText().toString());

                input1TextView = null;
                input2TextView = remainder_one;

                ans_first_line.setVisibility(View.VISIBLE);

                if (Integer.parseInt(carrying_dividend_ten.getText().toString()) -
                        Integer.parseInt(first_multiply_ten.getText().toString()) == 0) {
                    //바로 앞 자리(받아내림하고 남은 십의 자리 - 두번째 곱셈 십의 자리)가 0인 경우, 연산 종료
                    isFinal = true;
                }
                break;

            case 7:
                operand1TextView = carrying_dividend_ten;
                operand2TextView = first_multiply_ten;
                operand3TextView = null;
                operand4TextView = null;

                ans = dividend % divisor / 10;

                input1TextView = null;
                input2TextView = remainder_ten;

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
//        if (input1TextView != null && input2TextView != null) {
//            multiInput = true;
//        } else {
//            multiInput = false;
//        }

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

//    private void markSlashOn() {
//        currentMark.setVisibility(View.VISIBLE);
//        currentMark.setImageResource(R.drawable.slash_red);
//    }
//
//    private void markSlashOff() {
//        currentMark.setImageResource(R.drawable.slash_gray);
//        currentMark = null;
//    }
//
//    private boolean result() {
//        int temp = 0, temp1 = 0, temp2 = 0;
//
//        Log.v(LOG_TAG, "ans : " + ans);
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
//
//            //모든 연산이 끝나면
//            if (isFinal) {
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
//                input1TextView.setTextColor(Color.BLUE);
//            }
//            if (input2TextView != null) {
//                input2TextView.setText("?");
//                input2TextView.setTextColor(Color.BLUE);
//            }
//            return false;
//
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
////        zeroCarrying = false;
//        ans = 0;
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
//        dividend_ten.setTextColor(Color.GRAY);
//        dividend_one.setTextColor(Color.GRAY);
//
//        quotient_one.setText(String.valueOf("0"));
//        quotient_one.setTextColor(Color.WHITE);
//
//        first_multiply_ten.setText(String.valueOf("0"));
//        first_multiply_ten.setTextColor(Color.WHITE);
//        first_multiply_one.setText(String.valueOf("0"));
//        first_multiply_one.setTextColor(Color.WHITE);
//
//        ans_first_line.setBackgroundColor(Color.WHITE);
//
//        remainder_ten.setText(String.valueOf("0"));
//        remainder_ten.setTextColor(Color.WHITE);
//        remainder_one.setText(String.valueOf("0"));
//        remainder_one.setTextColor(Color.WHITE);
//    }

    @Override
    public void onNumberClicked(int number) {
//        switch (inputNext) {
//            case 1:
//                switch (inputEntry) {
//                    case 1:
//                        if (input1TextView != null) {
//                            input1TextView.setText(String.valueOf(number));
//                        }
//                        inputNext = 2;
//                        break;
//                    case 2:
//                        input2TextView.setText(String.valueOf(number));
//                        inputNext = 1;
//                        break;
//                    default:
//                        break;
//                }
//                break;
//            case 2:
//                switch (inputEntry) {
//                    case 1:
//                        if (input2TextView != null) {
//                            input2TextView.setText(String.valueOf(number));
//                        }
//                        inputNext = 1;
//                        break;
//                    default:
//                        break;
//                }
//                break;
//            default:
//                break;
//
//        }
        if (!multiInput) {
            if (input2TextView != null) {
                input2TextView.setText(String.valueOf(number));
            }
        } else {
            //사용자 입력 처리 : 첫번째 입력인 경우 input1TextView에 값을 입력함
            if (carrying) {
                if (input1TextView != null) {
                    input1TextView.setText(String.valueOf(number));
                }
                carrying = false;
            } else {
                //두번째 입력인 경우 input2TextView에 값을 입력함
                if (input2TextView != null) {
                    input2TextView.setText(String.valueOf(number));
                }
                carrying = true;
            }
        }

    }

    public void onClearClicked() {
//        currentStage = 0;
//        ans = 0;
//        initNumbers();
//        nextStage();
//        inputNext = 1;

        //사용자가 입력할 텍스트 뷰를 다시 'A'와 'B'로 되돌림
        if (input1TextView != null) {
            input1TextView.setText("?");
            input1TextView.setTextColor(Color.BLUE);
        }
        if (input2TextView != null) {
            input2TextView.setText("?");
            input2TextView.setTextColor(Color.BLUE);
        }
        carrying = true;
    }

    public void onOKClicked() {
        if (input1TextView == null) {
            if (input2TextView.getText().toString().matches("[0-9]")) {
                if (result()) {
                    nextStage();
                }            }
        } else {
            if (input1TextView.getText().toString().matches("[0-9]") && input2TextView.getText().toString().matches("[0-9]"))
                if (result()) {
                    nextStage();
                }        }

//
//        if (result()) {
//            nextStage();
//        }
    }

}

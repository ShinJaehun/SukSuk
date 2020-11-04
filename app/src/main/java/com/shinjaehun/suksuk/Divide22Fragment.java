package com.shinjaehun.suksuk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
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
public class Divide22Fragment extends ProblemFragment {

    private static final String LOG_TAG = Divide22Fragment.class.getSimpleName();
//    public Context mContext = null;

    private int divisor, dividend, quotient;
    private int dividendTen, dividendOne;
    private int divisorTen, divisorOne;
    private int quotientOne;

    private View ansFirstLineV;

    private TextView quotientOneTV;

    private TextView carryingDivisorTenTV;

    private TextView divisorTenTV, divisorOneTV;

    private TextView carryingDividendTenTV, carryingDividendOne10TV, carryingDividendOne1TV;

    private ImageView dividendTenCoverIV, dividendOneCoverIV;

    private TextView dividendTenTV, dividendOneTV;
    private TextView firstMultiplyTenTV, firstMultiplyOneTV;
    private TextView remainderTenTV, remainderOneTV;

//    private TextView operand1TV, operand2TV, operand3TV, operand4TV;
//    private TextView input1TV, input2TV;
//
//    private ImageButton helpBTN;

//    private ImageView currentMarkIV;

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
        View v = inflater.inflate(R.layout.fragment_divide22, container, false);

        quotientOneTV = (TextView)v.findViewById(R.id.quotient_one);

        carryingDivisorTenTV = (TextView)v.findViewById(R.id.carrying_divisor_ten);

        divisorTenTV = (TextView)v.findViewById(R.id.divisor_ten);
        divisorOneTV = (TextView)v.findViewById(R.id.divisor_one);

        carryingDividendTenTV = (TextView)v.findViewById(R.id.carrying_dividend_ten);
        carryingDividendOne10TV = (TextView)v.findViewById(R.id.carrying_dividend_one_10);
        carryingDividendOne1TV = (TextView)v.findViewById(R.id.carrying_dividend_one_1);

        dividendTenCoverIV = (ImageView)v.findViewById(R.id.dividend_ten_cover);
        dividendOneCoverIV = (ImageView)v.findViewById(R.id.dividend_one_cover);

        dividendTenTV = (TextView)v.findViewById(R.id.dividend_ten);
        dividendOneTV = (TextView)v.findViewById(R.id.dividend_one);

        firstMultiplyTenTV = (TextView)v.findViewById(R.id.first_multiply_ten);
        firstMultiplyOneTV = (TextView)v.findViewById(R.id.first_multiply_one);

        ansFirstLineV = (View)v.findViewById(R.id.ans_first_line);

        remainderTenTV = (TextView)v.findViewById(R.id.remainder_ten);
        remainderOneTV = (TextView)v.findViewById(R.id.remainder_one);

        helpBTN = (ImageButton) v.findViewById(R.id.help);
        challengeCounterTV = (TextView)v.findViewById(R.id.challengeCounter);

        if(isChallenge == false) {
            challengeCounterTV.setVisibility(View.GONE);

//            helpBTN.setOnClickListener(new Button.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
//                    intent.putExtra("help", "divide22");
//                    startActivity(intent);
//                }
//            });
        } else {
//            Log.v(LOG_TAG, "challengeNumber : " + challengeNumber);
            helpBTN.setVisibility(View.GONE);

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
        SharedPreferences debug = getActivity().getSharedPreferences("debug", Context.MODE_PRIVATE);

        if (debug.getBoolean("isDebugging", false)) {
            dividend = debug.getInt("firstNumber", 0);
            divisor = debug.getInt("secondNumber", 0);
            SharedPreferences.Editor editor = debug.edit();
            editor.clear();
            editor.commit();

        } else {

            //어쨌든 큰 수가 나뉘는 수가 되어야 한다.
            int a = (int) (Math.random() * 90) + 10;
            int b = (int) (Math.random() * 90) + 10;

            if (a > b) {
                dividend = a;
                divisor = b;
            } else {
                dividend = b;
                divisor = a;
            }
        }
        quotient = dividend / divisor;

//        top = 798;
//        down = 57;

        dividendTen = dividend / 10 % 10;
        dividendOne = dividend % 10;

        divisorTen = divisor / 10 % 10;
        divisorOne = divisor % 10;

        quotientOne = quotient % 10;

        //다른 fragment와 통일하기 위해 helpBTN의 리스너를 여기에 달았다.
        helpBTN.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
                intent.putExtra("help", "divide22");
                startActivity(intent);
            }
        });

        //피연산자 표시
        dividendTenTV.setText(String.valueOf(dividendTen));
        dividendOneTV.setText(String.valueOf(dividendOne));

        divisorTenTV.setText(String.valueOf(divisorTen));
        divisorOneTV.setText(String.valueOf(divisorOne));

    }

    public void nextStage() {
        currentStage += 1;
//        Log.v(LOG_TAG, "Current Stage : " + String.valueOf(currentStage));

        //currentStage에 따라 곱셈할 자리수, 곱셈 결과, 입력할 자리수 지정
        switch (currentStage) {
            case 1:
                operand1TV = divisorTenTV;
                operand2TV = divisorOneTV;
                operand3TV = dividendTenTV;
                operand4TV = dividendOneTV;

                input1TV = null;
                input2TV = quotientOneTV;

                ans = quotientOne;

                break;

            case 2:
                operand1TV = divisorOneTV;
                operand2TV = quotientOneTV;
                operand3TV = null;
                operand4TV = null;

                ans = divisorOne * quotientOne;

                if (ans < 10) {
                    //받아올림이 없으면 한 자리만 입력함
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

//                ansFirstLineV.setBackgroundColor(Color.GRAY);

//                ans = dividend - (divisor * quotientOne);

                ans = divisorTen * quotientOne +
                        Integer.parseInt(carryingDivisorTenTV.getText().toString());

                input1TV = null;
                input2TV = firstMultiplyTenTV;

                break;

            case 4:
                //나누는 수 받아올림 삭제
                carryingDivisorTenTV.setText("0");
                carryingDivisorTenTV.setTextColor(Color.WHITE);

                if (dividendOne >= Integer.parseInt(firstMultiplyOneTV.getText().toString())) {
                    //받아내림이 없다면

                    operand1TV = dividendTenTV;
                    operand2TV = dividendOneTV;
                    operand3TV = firstMultiplyTenTV;
                    operand4TV = firstMultiplyOneTV;

                    ansFirstLineV.setVisibility(View.VISIBLE);

                    ans = dividend % divisor;

                    if (ans < 10) {
                        //뺄셈 결과 일의 자리라면 입력은 하나만
                        input1TV = null;
                    } else {
                        input1TV = remainderTenTV;
                    }
                    input2TV = remainderOneTV;

                    isFinal = true;
                    //뺄셈 후 연산 종료

                } else {
                    //받아내림이 있다면

                    operand1TV = dividendTenTV;
                    operand2TV = null;
                    operand3TV = null;
                    operand4TV = null;

                    currentMarkIV = dividendTenCoverIV;
                    markSlashOn();

                    input1TV = null;
                    input2TV = carryingDividendTenTV;

                    ans = dividendTen - 1;

                }

                break;

            case 5:
                operand1TV = dividendOneTV;
                operand2TV = null;
                operand3TV = null;
                operand4TV = null;

                currentMarkIV = dividendOneCoverIV;
                markSlashOn();

//                if (ans < 10) {
//                    //받아내림 결과가 10보다 작다면
//                    input1TV = null;
//                } else {
//                    input1TV = carryingDividendTenTV;
//                }
//                이게 필요 없는게 어차피 받아내림 결과가 10보다 클 거 아냐...

                ans = dividendOne + 10;

                input1TV = carryingDividendOne10TV;
                input2TV = carryingDividendOne1TV;

                break;

            case 6:
                operand1TV = carryingDividendOne10TV;
                operand2TV = carryingDividendOne1TV;
                operand3TV = firstMultiplyOneTV;
                operand4TV = null;

                ans = Integer.parseInt(carryingDividendOne10TV.getText().toString()) * 10 +
                    Integer.parseInt(carryingDividendOne1TV.getText().toString()) -
                    Integer.parseInt(firstMultiplyOneTV.getText().toString());

                input1TV = null;
                input2TV = remainderOneTV;

                ansFirstLineV.setVisibility(View.VISIBLE);

                if (Integer.parseInt(carryingDividendTenTV.getText().toString()) -
                        Integer.parseInt(firstMultiplyTenTV.getText().toString()) == 0) {
                    //바로 앞 자리(받아내림하고 남은 십의 자리 - 두번째 곱셈 십의 자리)가 0인 경우, 연산 종료
                    isFinal = true;
                }
                break;

            case 7:
                operand1TV = carryingDividendTenTV;
                operand2TV = firstMultiplyTenTV;
                operand3TV = null;
                operand4TV = null;

                ans = dividend % divisor / 10;

                input1TV = null;
                input2TV = remainderTenTV;

                isFinal = true;

                break;

            default:
                break;
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
//        if (input1TV != null && input2TV != null) {
//            multiInput = true;
//        } else {
//            multiInput = false;
//        }

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

//    private void markSlashOn() {
//        currentMarkIV.setVisibility(View.VISIBLE);
//        currentMarkIV.setImageResource(R.drawable.slash_red);
//    }
//
//    private void markSlashOff() {
//        currentMarkIV.setImageResource(R.drawable.slash_gray);
//        currentMarkIV = null;
//    }
//
//    private boolean result() {
//        int temp = 0, temp1 = 0, temp2 = 0;
//
//        Log.v(LOG_TAG, "ans : " + ans);
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
//            if (input1TV != null) {
//                input1TV.setText("?");
//                input1TV.setTextColor(Color.BLUE);
//            }
//            if (input2TV != null) {
//                input2TV.setText("?");
//                input2TV.setTextColor(Color.BLUE);
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
//        dividendTenTV.setTextColor(Color.GRAY);
//        dividendOneTV.setTextColor(Color.GRAY);
//
//        quotientOneTV.setText(String.valueOf("0"));
//        quotientOneTV.setTextColor(Color.WHITE);
//
//        firstMultiplyTenTV.setText(String.valueOf("0"));
//        firstMultiplyTenTV.setTextColor(Color.WHITE);
//        firstMultiplyOneTV.setText(String.valueOf("0"));
//        firstMultiplyOneTV.setTextColor(Color.WHITE);
//
//        ansFirstLineV.setBackgroundColor(Color.WHITE);
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
////                        inputNext = 1;
////                        break;
////                    default:
////                        break;
////                }
////                break;
////            case 2:
////                switch (inputEntry) {
////                    case 1:
////                        if (input2TV != null) {
////                            input2TV.setText(String.valueOf(number));
////                        }
////                        inputNext = 1;
////                        break;
////                    default:
////                        break;
////                }
////                break;
////            default:
////                break;
////
////        }
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
//
//    }
//
//    public void onClearClicked() {
////        currentStage = 0;
////        ans = 0;
////        initNumbers();
////        nextStage();
////        inputNext = 1;
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

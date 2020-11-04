package com.shinjaehun.suksuk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by shinjaehun on 2016-04-19.
 */
public class Multiply22Fragment extends ProblemFragment {

    private static final String LOG_TAG = Multiply22Fragment.class.getSimpleName();

    private int top, down;
    private int topTen, topOne;
    private int downTen, downOne;

    private View ansLineV;

    private TextView topTenTV, topOneTV;
    private TextView downTenTV, downOneTV;

    private TextView carryingTenTV;
    private TextView ansCarryingThousandTV, ansCarryingHundredTV;
    private TextView ansTopOneTV, ansTopTenTV, ansTopHundredTV;
    private TextView ansDownOneTV, ansDownTenTV, ansDownHundredTV;
    private TextView ansOneTV, ansTenTV, ansHundredTV, ansThousandTV;

//    private TextView operand1TV, operand2TV, input1TV, input2TV;
//
//    private ImageButton helpBTN;

    //곱셈 결과를 입력할 순서 저장
//    private boolean carrying = true;
//
//    //세 자리 수 중 하나가 0이거나 곱셈 결과가 받아올림이 없는 경우를 처리할 스위치
//    //곱셈 결과를 더할 때 받아올림이 있는 경우에도 사용함
//    private boolean multiInput = false;
//
//    //현재 과정
//    private int currentStage = 0;
//
//    //곱셈 결과
//    private int ans = 0;
//
//    //최종 단계인가?
//    private boolean isFinal = false;

    public void startPractice() {
        initOperands();
        nextStage();

        startTime = System.nanoTime();
        //계산 시간 측정을 위한 변수, ProblemFragment에 구현되어 있음

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_multiply22, container, false);

        carryingTenTV = (TextView)v.findViewById(R.id.carrying_ten);

        topTenTV = (TextView)v.findViewById(R.id.top_ten);
        topOneTV = (TextView)v.findViewById(R.id.top_one);

        downTenTV = (TextView)v.findViewById(R.id.down_ten);
        downOneTV = (TextView)v.findViewById(R.id.down_one);

        ansCarryingThousandTV = (TextView)v.findViewById(R.id.ans_carrying_thousand);
        ansCarryingHundredTV = (TextView)v.findViewById(R.id.ans_carrying_hundred);

        ansTopOneTV = (TextView)v.findViewById(R.id.ans_top_oen);
        ansTopTenTV = (TextView)v.findViewById(R.id.ans_top_ten);
        ansTopHundredTV = (TextView)v.findViewById(R.id.ans_top_hundred);

        ansDownOneTV = (TextView)v.findViewById(R.id.ans_down_one);
        ansDownTenTV = (TextView)v.findViewById(R.id.ans_down_ten);
        ansDownHundredTV = (TextView)v.findViewById(R.id.ans_down_hundred);

        ansLineV = (View)v.findViewById(R.id.ans_line);

        ansOneTV = (TextView)v.findViewById(R.id.ans_one);
        ansTenTV = (TextView)v.findViewById(R.id.ans_ten);
        ansHundredTV = (TextView)v.findViewById(R.id.ans_hundred);
        ansThousandTV = (TextView)v.findViewById(R.id.ans_thousand);

        helpBTN = (ImageButton) v.findViewById(R.id.help);
        challengeCounterTV = (TextView)v.findViewById(R.id.challengeCounter);

        if (isChallenge == false) {
            challengeCounterTV.setVisibility(View.GONE);

//            helpBTN.setOnClickListener(new Button.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
//                    intent.putExtra("help", "multiply22");
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

        SharedPreferences debug = getActivity().getSharedPreferences("debug", Context.MODE_PRIVATE);

        if (debug.getBoolean("isDebugging", false)) {
            top = debug.getInt("firstNumber", 0);
            down = debug.getInt("secondNumber", 0);
            SharedPreferences.Editor editor = debug.edit();
            editor.clear();
            editor.commit();

        } else {
            top = (int) (Math.random() * 90) + 10;
            down = (int) (Math.random() * 90) + 10;
        }
//        top = 14;
//        down = 67;

        topTen = top / 10 % 10;
        topOne = top % 10;

        downTen = down / 10 % 10;
        downOne = down % 10;

        //다른 fragment와 통일하기 위해 helpBTN의 리스너를 여기에 달았다.
        helpBTN.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
                intent.putExtra("help", "multiply22");
                startActivity(intent);
            }
        });

        //피연산자 표시
        topTenTV.setText(String.valueOf(topTen));
        topOneTV.setText(String.valueOf(topOne));

        downTenTV.setText(String.valueOf(downTen));
        downOneTV.setText(String.valueOf(downOne));
    }

    public void nextStage() {
        currentStage += 1;
//        Log.v(LOG_TAG, "Current Stage : " + String.valueOf(currentStage));

        //currentStage에 따라 곱셈할 자리수, 곱셈 결과, 입력할 자리수 지정
        switch (currentStage) {
            case 1:
                operand1TV = topOneTV;
                operand2TV = downOneTV;
                ans = topOne * downOne;

                if (ans < 10) {
                    //받아올림이 없으면 입력은 하나만...
                    input1TV = null;
                } else {
                    input1TV = carryingTenTV;
                }
                input2TV = ansTopOneTV;
                break;

            case 2:
                operand1TV = topTenTV;
                operand2TV = downOneTV;
                ans = topTen * downOne + Integer.parseInt(carryingTenTV.getText().toString());

                if (ans < 10) {
                    //곱셈 결과가 십의 자리 밖에 되지 않으면 입력은 하나만...
                    input1TV = null;
                } else {
                    input1TV = ansTopHundredTV;
                }
                input2TV = ansTopTenTV;
                break;

            case 3:
                //십의 자리 곱셈을 하기 전 받아올림 내용을 삭제
                carryingTenTV.setText("0");
                carryingTenTV.setTextColor(Color.WHITE);

                //곱셈 결과를 회색으로 처리
                if (Integer.parseInt(ansTopHundredTV.getText().toString()) == 0) {
                    ansTopHundredTV.setTextColor(Color.WHITE);
                }
//                else {
//                    ansTopHundredTV.setTextColor(Color.GRAY);
//                }
//                ansTopTenTV.setTextColor(Color.GRAY);
//                ansTopOneTV.setTextColor(Color.GRAY);

                operand1TV = topOneTV;
                operand2TV = downTenTV;
                ans = topOne * downTen;

                if (ans < 10) {
                    //받아올림이 없으면 입력은 하나만...
                    input1TV = null;
                } else {
                    input1TV = carryingTenTV;
                }
                input2TV = ansDownOneTV;
                break;

            case 4:
                operand1TV = topTenTV;
                operand2TV = downTenTV;
                ans = topTen * downTen + Integer.parseInt(carryingTenTV.getText().toString());

                if (ans < 10) {
                    //곱셈 결과가 십의 자리 밖에 되지 않으면 입력은 하나만...
                    input1TV = null;
                } else {
                    input1TV = ansDownHundredTV;
                }
                input2TV = ansDownTenTV;
                break;

            case 5:
                //십의 자리 곱셈을 하기 전 받아올림 내용을 삭제
                carryingTenTV.setText("0");
                carryingTenTV.setTextColor(Color.WHITE);

                //곱셈 결과를 회색으로 처리
                if (Integer.parseInt(ansDownHundredTV.getText().toString()) == 0) {
                    ansDownHundredTV.setTextColor(Color.WHITE);
                }
//                else {
//                    ansDownHundredTV.setTextColor(Color.GRAY);
//                }
//                ansDownTenTV.setTextColor(Color.GRAY);
//                ansDownOneTV.setTextColor(Color.GRAY);

                //곱셈 결과를 더하기 전에 아래 선 긋기
                ansLineV.setBackgroundColor(Color.GRAY);

                operand1TV = ansTopOneTV;
                operand2TV = null;
                ans = Integer.parseInt(ansTopOneTV.getText().toString());
                input1TV = null;
                input2TV = ansOneTV;
                break;

            case 6:
                operand1TV = ansTopTenTV;
                operand2TV = ansDownOneTV;
                ans = Integer.parseInt(ansTopTenTV.getText().toString())
                        + Integer.parseInt(ansDownOneTV.getText().toString());

                if (ans < 10) {
                    //덧셈 결과 받아올림이 없으면 입력은 하나만...
                    input1TV = null;
                } else {
                    input1TV = ansCarryingHundredTV;
                }
                input2TV = ansTenTV;
                break;

            case 7:
                if (Integer.parseInt(ansTopHundredTV.getText().toString()) == 0) {
                    //첫번째 곱셈 결과가 십의 자리이면 '0'을 붉게 표시하지 않는다.
                    operand1TV = null;
                } else {
                    operand1TV = ansTopHundredTV;
                }
                operand2TV = ansDownTenTV;
                ans = Integer.parseInt(ansTopHundredTV.getText().toString())
                        + Integer.parseInt(ansDownTenTV.getText().toString())
                        + Integer.parseInt(ansCarryingHundredTV.getText().toString());
                if (ans < 10) {
                    //덧셈 결과 받아올림이 없으면 입력은 하나만...
                    input1TV = null;
                    if (Integer.parseInt(ansDownHundredTV.getText().toString()) == 0) {
                        //받아올림이 없고 두번째 곱셈 결과 백의 자리가 0이면 여기서 연산 종료
                        isFinal = true;
                    }
                } else {
                    input1TV = ansCarryingThousandTV;
                }
                input2TV = ansHundredTV;
                break;

            case 8:
                operand1TV = null;
                operand2TV = ansDownHundredTV;
                ans = Integer.parseInt(ansCarryingThousandTV.getText().toString())
                        + Integer.parseInt(ansDownHundredTV.getText().toString());
                input1TV = null;
                input2TV = ansThousandTV;
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

//        if (currentStage < 5) {
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
//            //score 테스트
//            score++;
//            Log.v(LOG_TAG, "현재 스코어는 : " + score);
//
//            //연산했던 자리수를 다시 회색으로 되돌리기
//            if (operand1TV != null) {
//                operand1TV.setTextColor(Color.GRAY);
//            }
//            if (operand2TV != null) {
//                operand2TV.setTextColor(Color.GRAY);
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
//            //score 테스트
//            score--;
//            Log.v(LOG_TAG, "현재 스코어는 : " + score);
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
////
////            if (multiInput) {
////                if (input1TV != null) {
////                    input1TV.setText("0");
////                    input1TV.setTextColor(Color.WHITE);
////                }
////            }
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
//        //참잘했어요 이미지 표시하기
//        ImageView verygood = (ImageView)getActivity().findViewById(R.id.verygood);
//        verygood.setVisibility(View.VISIBLE);
//
//        //참잘했어요 이미지 나온 이후에 버튼 입력 해제
//        ((ProblemActivity)getActivity()).unSetListener();
//
//        //이미지 클릭하면 액티비티 재시작!
//        verygood.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = getActivity().getIntent();
//                getActivity().finish();
//                startActivity(intent);
//            }
//        });
//
////        new CountDownTimer(5000, 1000) {
////            @Override
////            public void onTick(long millisUntilFinished) {
////
////            }
////
////            @Override
////            public void onFinish() {
////
////            }
////
////        }.start();
//
////        try {
////            Thread.sleep(5000);
////        } catch (InterruptedException e) {}
//
////        Handler handler = new Handler();
////        handler.postDelayed(new Runnable() {
////            @Override
////            public void run() {
////
////            }
////        }, 5000);
//
////        //모든 변수 초기화
////        currentStage = 0;
////        multiInput = false;
////        carrying = true;
////        ans = 0;
////
////        //피연산자 초기화
////        initOperands();
////        //피연산자를 제외한 나머지 모든 숫자 초기화
////        initNumbers();
//    }

//    private void initNumbers() {
////        if (operand1TV != null) {
////            operand1TV.setTextColor(Color.GRAY);
////        }
////        if (operand2TV != null) {
////            operand2TV.setTextColor(Color.GRAY);
////        }
//
//        topTenTV.setTextColor(Color.GRAY);
//        topOneTV.setTextColor(Color.GRAY);
//
//        downTenTV.setTextColor(Color.GRAY);
//        downOneTV.setTextColor(Color.GRAY);
//
//        carryingTenTV.setText(String.valueOf("0"));
//        carryingTenTV.setTextColor(Color.WHITE);
//
//        ansCarryingThousandTV.setText(String.valueOf("0"));
//        ansCarryingThousandTV.setTextColor(Color.WHITE);
//        ansCarryingHundredTV.setText(String.valueOf("0"));
//        ansCarryingHundredTV.setTextColor(Color.WHITE);
//
//        ansTopOneTV.setText(String.valueOf("0"));
//        ansTopOneTV.setTextColor(Color.WHITE);
//        ansTopTenTV.setText(String.valueOf("0"));
//        ansTopTenTV.setTextColor(Color.WHITE);
//        ansTopHundredTV.setText(String.valueOf("0"));
//        ansTopHundredTV.setTextColor(Color.WHITE);
//
//        ansDownOneTV.setText(String.valueOf("0"));
//        ansDownOneTV.setTextColor(Color.WHITE);
//        ansDownTenTV.setText(String.valueOf("0"));
//        ansDownTenTV.setTextColor(Color.WHITE);
//        ansDownHundredTV.setText(String.valueOf("0"));
//        ansDownHundredTV.setTextColor(Color.WHITE);
//
//        ansLineV.setBackgroundColor(Color.WHITE);
//
//        ansOneTV.setText(String.valueOf("0"));
//        ansOneTV.setTextColor(Color.WHITE);
//        ansTenTV.setText(String.valueOf("0"));
//        ansTenTV.setTextColor(Color.WHITE);
//        ansHundredTV.setText(String.valueOf("0"));
//        ansHundredTV.setTextColor(Color.WHITE);
//        ansThousandTV.setText(String.valueOf("0"));
//        ansThousandTV.setTextColor(Color.WHITE);
//    }

//    @Override
//    public void onNumberClicked(int number) {
//                /* 버튼이 클릭되었을 때 처리 */
//        if (!multiInput) {
//            if (input2TV != null) {
//                input2TV.setText(String.valueOf(number));
//            }
//
//        } else {
//            //사용자 입력 처리 : 첫번째 입력인 경우 input1TextView에 값을 입력함
//            if (carrying) {
//                if (input1TV != null) {
//                    input1TV.setText(String.valueOf(number));
//                }
//                carrying = false;
//                Log.v(LOG_TAG, "carrying : " + carrying);
//
//            } else {
//                //두번째 입력인 경우 input2TextView에 값을 입력함
//                if (input2TV != null) {
//                    input2TV.setText(String.valueOf(number));
//                }
//                carrying = true;
//                Log.v(LOG_TAG, "carrying : " + carrying);
//
//            }
//        }
//
//
////        //각 자리수를 곱하는 과정 처리
////        if (currentStage < 7) {
////            //세 자리 수 중 하나가 0이거나 곱셈 결과가 받아올림이 없는 경우 0을 입력하는 수고를 덜도록
////            //사용자는 input2TextView에만 값을 입력함
////            if (multiInput) {
////                input2TV.setText(String.valueOf(number));
////            } else {
////                //사용자 입력 처리 : 첫번째 입력인 경우 input1TextView에 값을 입력함
////                if (carrying) {
////                    input1TV.setText(String.valueOf(number));
////                    carrying = false;
////                } else {
////                    //두번째 입력인 경우 input2TextView에 값을 입력함
////                    input2TV.setText(String.valueOf(number));
////                    carrying = true;
////                }
////            }
////        } else {
////
////            if (input1TV != null) {
////                input1TV.setText(String.valueOf(number));
////            }
////            if (input2TV != null) {
////                input2TV.setText(String.valueOf(number));
////            }
////        }
//
//
//    }
//
//    public void onClearClicked() {
////        currentStage = 0;
////        carrying = true;
////        multiInput = false;
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
//
//    }
//
//    public void onOKClicked() {
//
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

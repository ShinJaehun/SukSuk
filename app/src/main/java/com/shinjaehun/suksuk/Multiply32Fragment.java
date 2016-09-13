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
import android.widget.TextView;

/**
 * Created by shinjaehun on 2016-04-19.
 */
public class Multiply32Fragment extends ProblemFragment {

    private static final String LOG_TAG = Multiply32Fragment.class.getSimpleName();

    private int top, down;
    private int topHundred, topTen, topOne;
    private int downTen, downOne;

    private View ansLineV;

    private TextView topHundredTV, topTenTV, topOneTV;
    private TextView downTenTV, downOneTV;

    private TextView carryingHundredTV, carryingTenTV;
    private TextView ansCarryingTenThousandTV, ansCarryingThousandTV, ansCarryingHundredTV;
    private TextView ansTopOneTV, ansTopTenTV, ansTopHundredTV, ansTopThousandTV;
    private TextView ansDownOneTV, ansDownTenTV, ansDownHundredTV, ansDownThousandTV;
    private TextView ansOneTV, ansTenTV, ansHundredTV, ansThousandTV, ansTenThousandTV;

//    private TextView operand1TV, operand2TV, input1TV, input2TV;
//
//    private ImageButton helpBTN;

//    //곱셈 결과를 입력할 순서 저장
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
//    //최종 스테이지인가?
//    private boolean isFinal = false;

    public void startPractice() {
        initOperands();
        nextStage();

        startTime = System.nanoTime();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_multiply32, container, false);

        carryingHundredTV = (TextView)v.findViewById(R.id.carrying_hundred);
        carryingTenTV = (TextView)v.findViewById(R.id.carrying_ten);

        topHundredTV = (TextView)v.findViewById(R.id.top_hundred);
        topTenTV = (TextView)v.findViewById(R.id.top_ten);
        topOneTV = (TextView)v.findViewById(R.id.top_one);

        downTenTV = (TextView)v.findViewById(R.id.down_ten);
        downOneTV = (TextView)v.findViewById(R.id.down_one);

        ansCarryingTenThousandTV = (TextView)v.findViewById(R.id.ans_carrying_tenthousand);
        ansCarryingThousandTV = (TextView)v.findViewById(R.id.ans_carrying_thousand);
        ansCarryingHundredTV = (TextView)v.findViewById(R.id.ans_carrying_hundred);

        ansTopOneTV = (TextView)v.findViewById(R.id.ans_top_oen);
        ansTopTenTV = (TextView)v.findViewById(R.id.ans_top_ten);
        ansTopHundredTV = (TextView)v.findViewById(R.id.ans_top_hundred);
        ansTopThousandTV = (TextView)v.findViewById(R.id.ans_top_thousand);

        ansDownOneTV = (TextView)v.findViewById(R.id.ans_down_one);
        ansDownTenTV = (TextView)v.findViewById(R.id.ans_down_ten);
        ansDownHundredTV = (TextView)v.findViewById(R.id.ans_down_hundred);
        ansDownThousandTV = (TextView)v.findViewById(R.id.ans_down_thousand);

        ansLineV = (View)v.findViewById(R.id.ans_line);

        ansOneTV = (TextView)v.findViewById(R.id.ans_one);
        ansTenTV = (TextView)v.findViewById(R.id.ans_ten);
        ansHundredTV = (TextView)v.findViewById(R.id.ans_hundred);
        ansThousandTV = (TextView)v.findViewById(R.id.ans_thousand);
        ansTenThousandTV = (TextView)v.findViewById(R.id.ans_tenthousand);

        helpBTN = (ImageButton) v.findViewById(R.id.help);
        challengeCounterTV = (TextView)v.findViewById(R.id.challengeCounter);

        if (isChallenge == false) {
            challengeCounterTV.setVisibility(View.GONE);

            helpBTN.setOnClickListener(new Button.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
                    intent.putExtra(operation, "multiply32");
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

    private  void initOperands() {
        /* 피연산자 생성 */
        /*난수 테스트
        for (int i = 0; i < 100; i++) {
            top = (int) (Math.random() * 900) + 100;
            Log.v(LOG_TAG, String.valueOf(top));
        }*/

        top = (int) (Math.random() * 900) + 100;
        down = (int) (Math.random() * 90) + 10;

//        top = 100;
//        down = 11;

        topHundred = top / 100 % 10;
        topTen = top / 10 % 10;
        topOne = top % 10;

        downTen = down / 10 % 10;
        downOne = down % 10;

        //피연산자 표시
        topHundredTV.setText(String.valueOf(topHundred));
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
                    //받아올림이 없으면 입력은 하나만...
                    input1TV = null;
                } else {
                    input1TV = carryingHundredTV;
                }
                input2TV = ansTopTenTV;
                break;

            case 3:
                operand1TV = topHundredTV;
                operand2TV = downOneTV;
                ans = topHundred * downOne + Integer.parseInt(carryingHundredTV.getText().toString());

                if (ans < 10) {
                    //곱셈 결과가 백의 자리 밖에 되지 않는다면 입력은 하나만...
                    input1TV = null;
                } else {
                    input1TV = ansTopThousandTV;
                }

                input2TV = ansTopHundredTV;
                break;

            case 4:
                //십의 자리 곱셈을 하기 전 받아올림 내용을 삭제
                carryingHundredTV.setText("0");
                carryingHundredTV.setTextColor(Color.WHITE);
                carryingTenTV.setText("0");
                carryingTenTV.setTextColor(Color.WHITE);

                //곱셈 결과를 천의 자리가 0이면 지우기
                if (Integer.parseInt(ansTopThousandTV.getText().toString()) == 0) {
                    ansTopThousandTV.setTextColor(Color.WHITE);
                }
//                else {
//                    ansTopThousandTV.setTextColor(Color.GRAY);
//                }
//                ansTopHundredTV.setTextColor(Color.GRAY);
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

            case 5:
                operand1TV = topTenTV;
                operand2TV = downTenTV;
                ans = topTen * downTen + Integer.parseInt(carryingTenTV.getText().toString());

                if (ans < 10) {
                    //받아올림이 없으면 입력은 하나만...
                    input1TV = null;
                } else {
                    input1TV = carryingHundredTV;
                }
                input2TV = ansDownTenTV;
                break;

            case 6:
                operand1TV = topHundredTV;
                operand2TV = downTenTV;
                ans = topHundred * downTen + Integer.parseInt(carryingHundredTV.getText().toString());

                if (ans < 10) {
                    //곱셈 결과 백의 자리 밖에 되지 않는다면 입력은 하나만
                    input1TV = null;
                } else {
                    input1TV = ansDownThousandTV;
                }
                input2TV = ansDownHundredTV;
                break;

            case 7:
                //곱셈 결과를 더하기 전에 받아올림 내용을 삭제
                carryingHundredTV.setText("0");
                carryingHundredTV.setTextColor(Color.WHITE);
                carryingTenTV.setText("0");
                carryingTenTV.setTextColor(Color.WHITE);

                //곱셈 결과를 천의 자리가 0이면 지우기
                if (Integer.parseInt(ansDownThousandTV.getText().toString()) == 0) {
                    ansDownThousandTV.setTextColor(Color.WHITE);
                }
//                else {
//                    ansDownThousandTV.setTextColor(Color.GRAY);
//                }
//                ansDownHundredTV.setTextColor(Color.GRAY);
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

            case 8:
                operand1TV = ansTopTenTV;
                operand2TV = ansDownOneTV;
                ans = Integer.parseInt(ansTopTenTV.getText().toString())
                        + Integer.parseInt(ansDownOneTV.getText().toString());

                if (ans < 10) {
                    //받아올림이 없는 경우 입력은 하나만...
                    input1TV = null;
                } else {
                    input1TV = ansCarryingHundredTV;
                }
                input2TV = ansTenTV;
                break;

            case 9:
                operand1TV = ansTopHundredTV;
                operand2TV = ansDownTenTV;
                ans = Integer.parseInt(ansTopHundredTV.getText().toString())
                        + Integer.parseInt(ansDownTenTV.getText().toString())
                        + Integer.parseInt(ansCarryingHundredTV.getText().toString());

                if (ans < 10) {
                    //덧셈 결과 받아올림이 없으면 입력은 하나만...
                    input1TV = null;
                } else {
                    input1TV = ansCarryingThousandTV;
                }
                input2TV = ansHundredTV;
                break;

            case 10:
                if (Integer.parseInt(ansTopThousandTV.getText().toString()) == 0) {
                    operand1TV = null;
                } else {
                    operand1TV = ansTopThousandTV;
                }
                operand2TV = ansDownHundredTV;
                ans = Integer.parseInt(ansTopThousandTV.getText().toString())
                        + Integer.parseInt(ansDownHundredTV.getText().toString())
                        + Integer.parseInt(ansCarryingThousandTV.getText().toString());

                if (ans < 10) {
                    //덧셈 결과 받아올림이 없으면 입력은 하나만...
                    input1TV = null;

                    if (Integer.parseInt(ansDownThousandTV.getText().toString()) == 0) {
                        //받아올림이 없고 두번째 곱셈 결과 천의 자리가 0이면 여기서 연산 종료
                        isFinal = true;
                    }
                } else {
                    input1TV = ansCarryingTenThousandTV;
                }
                input2TV = ansThousandTV;
                break;

            case 11:
                operand1TV = null;
                //두번째 곱셈 결과 천의 자리가 0이면 두번째 곱셈 결과 천의 자리 '0'을 붉게 표시하지 않는다.
                if (Integer.parseInt(ansDownThousandTV.getText().toString()) == 0) {
                    operand2TV = null;
                } else {
                    operand2TV = ansDownThousandTV;
                }
                ans = Integer.parseInt(ansDownThousandTV.getText().toString())
                        + Integer.parseInt(ansCarryingTenThousandTV.getText().toString());
                input1TV = null;
                input2TV = ansTenThousandTV;
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
//                if (operand1TV == null && operand2TV == null) {
//                    finalStage();
//                    nextStage();
//                }
//
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
//    private void flashText(boolean trueOrFalse) {
//        TextView textView;
//        String answer = null;
//        int random = (int)(Math.random() * 5) + 1;
//        if (trueOrFalse) {
//            textView = (TextView)getActivity().findViewById(R.id.answer_right);
//            switch (random) {
//                case 1:
//                    answer = "정답!";
//                    break;
//                case 2:
//                    answer = "제법인데~";
//                    break;
//                case 3:
//                    answer = "훌륭해!";
//                    break;
//                case 4:
//                    answer = "꽤 하는걸?";
//                    break;
//                case 5:
//                    answer = "맞았어!";
//                    break;
//                default:
//                    break;
//            }
//        } else {
//            textView = (TextView)getActivity().findViewById(R.id.answer_wrong);
//            switch (random) {
//                case 1:
//                    answer = "아니거든!";
//                    break;
//                case 2:
//                    answer = "땡!!!";
//                    break;
//                case 3:
//                    answer = "메롱메롱~";
//                    break;
//                case 4:
//                    answer = "제대로 해봐!";
//                    break;
//                case 5:
//                    answer = "다시 해보셈!";
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        textView.setText(answer);
//        textView.setVisibility(View.VISIBLE);
//        textView.setAlpha(1.0f);
//        textView.animate().alpha(0.0f).setDuration(1000).start();
//    }

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
//        multiInput = false;
//        carrying = true;
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
//        topHundredTV.setTextColor(Color.GRAY);
//        topTenTV.setTextColor(Color.GRAY);
//        topOneTV.setTextColor(Color.GRAY);
//
//        downTenTV.setTextColor(Color.GRAY);
//        downOneTV.setTextColor(Color.GRAY);
//
//        carryingHundredTV.setText(String.valueOf("0"));
//        carryingHundredTV.setTextColor(Color.WHITE);
//        carryingTenTV.setText(String.valueOf("0"));
//        carryingTenTV.setTextColor(Color.WHITE);
//
//        ansCarryingTenThousandTV.setText(String.valueOf("0"));
//        ansCarryingTenThousandTV.setTextColor(Color.WHITE);
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
//        ansTopThousandTV.setText(String.valueOf("0"));
//        ansTopThousandTV.setTextColor(Color.WHITE);
//
//        ansDownOneTV.setText(String.valueOf("0"));
//        ansDownOneTV.setTextColor(Color.WHITE);
//        ansDownTenTV.setText(String.valueOf("0"));
//        ansDownTenTV.setTextColor(Color.WHITE);
//        ansDownHundredTV.setText(String.valueOf("0"));
//        ansDownHundredTV.setTextColor(Color.WHITE);
//        ansDownThousandTV.setText(String.valueOf("0"));
//        ansDownThousandTV.setTextColor(Color.WHITE);
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
//        ansTenThousandTV.setText(String.valueOf("0"));
//        ansTenThousandTV.setTextColor(Color.WHITE);
//    }

//    @Override
//    public void onNumberClicked(int number) {
//
//        /* 버튼이 클릭되었을 때 처리 */
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
//            } else {
//                //두번째 입력인 경우 input2TextView에 값을 입력함
//                if (input2TV != null) {
//                    input2TV.setText(String.valueOf(number));
//                }
//                carrying = true;
//            }
//        }
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
//        Log.v(LOG_TAG, "carrying : " + String.valueOf(carrying));
//
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
////        Log.v(LOG_TAG, "carrying : " + carrying);
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

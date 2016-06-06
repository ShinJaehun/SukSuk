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
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by shinjaehun on 2016-04-19.
 */
public class Multiply32Fragment extends ProblemFragment implements NumberpadClickListener {

    private static final String LOG_TAG = Multiply32Fragment.class.getSimpleName();

    private int top, down;
    private int topHundred, topTen, topOne;
    private int downTen, downOne;

    private View ans_line;

    private TextView top_hundred, top_ten, top_one;
    private TextView down_ten, down_one;

    private TextView carrying_hundred, carrying_ten;
    private TextView ans_carrying_tenthousand, ans_carrying_thousand, ans_carrying_hundred;
    private TextView ans_top_one, ans_top_ten, ans_top_hundred, ans_top_thousand;
    private TextView ans_down_one, ans_down_ten, ans_down_hundred, ans_down_thousand;
    private TextView ans_one, ans_ten, ans_hundred, ans_thousand, ans_tenthousand;

    private TextView operand1TextView, operand2TextView, input1TextView, input2TextView;

    private Button help;

    //곱셈 결과를 입력할 순서 저장
    private boolean carrying = true;

    //세 자리 수 중 하나가 0이거나 곱셈 결과가 받아올림이 없는 경우를 처리할 스위치
    //곱셈 결과를 더할 때 받아올림이 있는 경우에도 사용함
    private boolean zeroCarrying = false;

    //현재 과정
    private int currentStage = 0;

    //곱셈 결과
    private int ans = 0;

    public void startPractice() {
        initOperands();
        nextStage();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_multiply32, container, false);

        carrying_hundred = (TextView)v.findViewById(R.id.carrying_hundred);
        carrying_ten = (TextView)v.findViewById(R.id.carrying_ten);

        top_hundred = (TextView)v.findViewById(R.id.top_hundred);
        top_ten = (TextView)v.findViewById(R.id.top_ten);
        top_one = (TextView)v.findViewById(R.id.top_one);

        down_ten = (TextView)v.findViewById(R.id.down_ten);
        down_one = (TextView)v.findViewById(R.id.down_one);

        ans_carrying_tenthousand = (TextView)v.findViewById(R.id.ans_carrying_tenthousand);
        ans_carrying_thousand = (TextView)v.findViewById(R.id.ans_carrying_thousand);
        ans_carrying_hundred = (TextView)v.findViewById(R.id.ans_carrying_hundred);

        ans_top_one = (TextView)v.findViewById(R.id.ans_top_oen);
        ans_top_ten = (TextView)v.findViewById(R.id.ans_top_ten);
        ans_top_hundred = (TextView)v.findViewById(R.id.ans_top_hundred);
        ans_top_thousand = (TextView)v.findViewById(R.id.ans_top_thousand);

        ans_down_one = (TextView)v.findViewById(R.id.ans_down_one);
        ans_down_ten = (TextView)v.findViewById(R.id.ans_down_ten);
        ans_down_hundred = (TextView)v.findViewById(R.id.ans_down_hundred);
        ans_down_thousand = (TextView)v.findViewById(R.id.ans_down_thousand);

        ans_line = (View)v.findViewById(R.id.ans_line);

        ans_one = (TextView)v.findViewById(R.id.ans_one);
        ans_ten = (TextView)v.findViewById(R.id.ans_ten);
        ans_hundred = (TextView)v.findViewById(R.id.ans_hundred);
        ans_thousand = (TextView)v.findViewById(R.id.ans_thousand);
        ans_tenthousand = (TextView)v.findViewById(R.id.ans_tenthousand);

        help = (Button)v.findViewById(R.id.help);
        help.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
                intent.putExtra("help", "multiply32");
                startActivity(intent);
            }
        });

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

//        top = 111;
//        down = 11;

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
                //십의 자리 곱셈을 하기 전 받아올림 내용을 삭제
                carrying_hundred.setText("0");
                carrying_hundred.setTextColor(Color.WHITE);
                carrying_ten.setText("0");
                carrying_ten.setTextColor(Color.WHITE);

                //곱셈 결과를 회색으로 처리
                if (Integer.parseInt(ans_top_thousand.getText().toString()) == 0) {
                    ans_top_thousand.setTextColor(Color.WHITE);
                } else {
                    ans_top_thousand.setTextColor(Color.GRAY);
                }
                ans_top_hundred.setTextColor(Color.GRAY);
                ans_top_ten.setTextColor(Color.GRAY);
                ans_top_one.setTextColor(Color.GRAY);

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
                //곱셈 결과를 더하기 전에 받아올림 내용을 삭제
                carrying_hundred.setText("0");
                carrying_hundred.setTextColor(Color.WHITE);
                carrying_ten.setText("0");
                carrying_ten.setTextColor(Color.WHITE);

                //곱셈 결과를 회색으로 처리
                if (Integer.parseInt(ans_down_thousand.getText().toString()) == 0) {
                    ans_down_thousand.setTextColor(Color.WHITE);
                } else {
                    ans_down_thousand.setTextColor(Color.GRAY);
                }
                ans_down_hundred.setTextColor(Color.GRAY);
                ans_down_ten.setTextColor(Color.GRAY);
                ans_down_one.setTextColor(Color.GRAY);

                //곱셈 결과를 더하기 전에 아래 선 긋기
                ans_line.setBackgroundColor(Color.GRAY);

                operand1TextView = ans_top_one;
                operand2TextView = null;
                ans = Integer.parseInt(ans_top_one.getText().toString());
                input1TextView = null;
                input2TextView = ans_one;
                break;

            case 8:
                operand1TextView = ans_top_ten;
                operand2TextView = ans_down_one;
                ans = Integer.parseInt(ans_top_ten.getText().toString())
                        + Integer.parseInt(ans_down_one.getText().toString());
                input1TextView = ans_carrying_hundred;
                input2TextView = ans_ten;
                break;

            case 9:
                operand1TextView = ans_top_hundred;
                operand2TextView = ans_down_ten;
                ans = Integer.parseInt(ans_top_hundred.getText().toString())
                        + Integer.parseInt(ans_down_ten.getText().toString())
                        + Integer.parseInt(ans_carrying_hundred.getText().toString());
                input1TextView = ans_carrying_thousand;
                input2TextView = ans_hundred;
                break;

            case 10:
                if (Integer.parseInt(ans_top_thousand.getText().toString()) == 0) {
                    operand1TextView = null;
                } else {
                    operand1TextView = ans_top_thousand;
                }
                operand2TextView = ans_down_hundred;
                ans = Integer.parseInt(ans_top_thousand.getText().toString())
                        + Integer.parseInt(ans_down_hundred.getText().toString())
                        + Integer.parseInt(ans_carrying_thousand.getText().toString());
                input1TextView = ans_carrying_tenthousand;
                input2TextView = ans_thousand;
                break;

            case 11:
                operand1TextView = null;
                if ((Integer.parseInt(ans_down_thousand.getText().toString()) == 0)
                        && (Integer.parseInt(ans_carrying_tenthousand.getText().toString()) == 0)) {
                    operand2TextView = null;
                } else {
                    operand2TextView = ans_down_thousand;
                }
                ans = Integer.parseInt(ans_down_thousand.getText().toString())
                        + Integer.parseInt(ans_carrying_tenthousand.getText().toString());
                input1TextView = null;
                input2TextView = ans_tenthousand;
                break;

            default:
                break;
        }

        //곱셈할 각 자리수를 빨간색으로 표시
        if (operand1TextView != null) {
            operand1TextView.setTextColor(Color.RED);
        }
        if (operand2TextView != null) {
            operand2TextView.setTextColor(Color.RED);
        }

        //입력할 텍스트 뷰를 임시로 'A'와 'B'로 표시
        if (input1TextView != null) {
            input1TextView.setText("?");
            input1TextView.setTextColor(Color.BLACK);
        }
        if (input2TextView != null) {
            input2TextView.setText("?");
            input2TextView.setTextColor(Color.BLACK);
        }

        if (currentStage < 7) {
            //세 자리 수 중 하나가 0이거나 곱셈 결과가 받아올림이 없는 경우
            if (Integer.parseInt(operand1TextView.getText().toString()) == 0 || ans < 10) {
                input1TextView.setText("0");
                input1TextView.setTextColor(Color.WHITE);
                zeroCarrying = true;
            } else {
                zeroCarrying = false;
            }
        } else {
            //곱셈 결과를 더하는 과정에서 받아올림이 없는 경우
            if (ans < 10) {
                if (operand1TextView == null && operand2TextView == null) {
                    finalStage();
                    nextStage();
                }

                if (input1TextView != null) {
                    input1TextView.setText("0");
                    input1TextView.setTextColor(Color.WHITE);
                }
                //carrying = false;
                zeroCarrying = true;
            } else {
                zeroCarrying = false;
            }
        }
    }

    private boolean result() {
        int temp = 0, temp1 = 0, temp2 = 0;

        //temp1에 사용자의 첫번째 입력 값 저장
        if (input1TextView == null || !input1TextView.getText().toString().matches("[0-9]")) {
            temp1 = 0;
        } else {
            try {
                temp1 = Integer.parseInt(input1TextView.getText().toString());
                Log.v(LOG_TAG, "temp1 : " + String.valueOf(temp1));
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }

        //temp2에 사용자의 두번째 입력 값 저장
        if (input2TextView == null || !input2TextView.getText().toString().matches("[0-9]")) {
            temp2 = 0;
        } else {
            try {
                temp2 = Integer.parseInt(input2TextView.getText().toString());
                Log.v(LOG_TAG, "temp2 : " + String.valueOf(temp2));
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }

        temp = temp1 * 10 + temp2;



//        //각 자리수를 곱하는 과정 처리
//        if (currentStage < 7) {
//            //사용자가 입력한 값을 바탕으로 곱셈 결과 temp에 저장
//            temp = temp1 * 10 + temp2;
//            Log.v(LOG_TAG, "temp : " + String.valueOf(temp));
//
//        //일의 자리, 십의 자리 곱셈 결과를 더하는 과정 처리
//        } else {
//
//            //사용자가 입력한 값을 temp에 저장
//            if (input2TextView == null) {
//                temp = temp1;
////                try {
////                    temp = Integer.parseInt(input1TextView.getText().toString());
////                    Log.v(LOG_TAG, "ans : " + String.valueOf(ans));
////                    Log.v(LOG_TAG, "temp : " + String.valueOf(temp));
////                } catch (NumberFormatException nfe) {
////                    return wrongAnswer(input1TextView.getText().toString());
////                }
//            } else {
//                temp = temp1 * 10 + temp2;
//            }
//        }

        //정답처리
        if (ans == temp) {
            flashText(true);

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
                return false;
                //여기에 false를 넣지 않으면 finalStage로 가면서 nextStage()가 한번 더 실행된다!!
                //잡기 어려웠던 버그 중 하나!
            }
            return true;

            //오답처리
        } else {
            //진동 발사
            Vibrator vibrator = (Vibrator)getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(300);

            //오답 텍스트 보여주기
            flashText(false);

            //사용자가 입력할 텍스트 뷰를 다시 'A'와 'B'로 되돌림
            if (input1TextView != null) {
                input1TextView.setText("?");
                input1TextView.setTextColor(Color.BLACK);
            }
            if (input2TextView != null) {
                input2TextView.setText("?");
                input2TextView.setTextColor(Color.BLACK);
            }

            if (zeroCarrying) {
                if (input1TextView != null) {
                    input1TextView.setText("0");
                    input1TextView.setTextColor(Color.WHITE);
                }
            }

            return false;
        }
    }

    // 이 내용을 ProblemFragment로 옮겼습니다~
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
//        zeroCarrying = false;
//        carrying = true;
//        ans = 0;
//
//        //피연산자 초기화
//        initOperands();
//        //피연산자를 제외한 나머지 모든 숫자 초기화
//        initNumbers();
//    }

    private void initNumbers() {
//        if (operand1TextView != null) {
//            operand1TextView.setTextColor(Color.GRAY);
//        }
//        if (operand2TextView != null) {
//            operand2TextView.setTextColor(Color.GRAY);
//        }
        top_hundred.setTextColor(Color.GRAY);
        top_ten.setTextColor(Color.GRAY);
        top_one.setTextColor(Color.GRAY);

        down_ten.setTextColor(Color.GRAY);
        down_one.setTextColor(Color.GRAY);

        carrying_hundred.setText(String.valueOf("0"));
        carrying_hundred.setTextColor(Color.WHITE);
        carrying_ten.setText(String.valueOf("0"));
        carrying_ten.setTextColor(Color.WHITE);

        ans_carrying_tenthousand.setText(String.valueOf("0"));
        ans_carrying_tenthousand.setTextColor(Color.WHITE);
        ans_carrying_thousand.setText(String.valueOf("0"));
        ans_carrying_thousand.setTextColor(Color.WHITE);
        ans_carrying_hundred.setText(String.valueOf("0"));
        ans_carrying_hundred.setTextColor(Color.WHITE);

        ans_top_one.setText(String.valueOf("0"));
        ans_top_one.setTextColor(Color.WHITE);
        ans_top_ten.setText(String.valueOf("0"));
        ans_top_ten.setTextColor(Color.WHITE);
        ans_top_hundred.setText(String.valueOf("0"));
        ans_top_hundred.setTextColor(Color.WHITE);
        ans_top_thousand.setText(String.valueOf("0"));
        ans_top_thousand.setTextColor(Color.WHITE);

        ans_down_one.setText(String.valueOf("0"));
        ans_down_one.setTextColor(Color.WHITE);
        ans_down_ten.setText(String.valueOf("0"));
        ans_down_ten.setTextColor(Color.WHITE);
        ans_down_hundred.setText(String.valueOf("0"));
        ans_down_hundred.setTextColor(Color.WHITE);
        ans_down_thousand.setText(String.valueOf("0"));
        ans_down_thousand.setTextColor(Color.WHITE);

        ans_line.setBackgroundColor(Color.WHITE);

        ans_one.setText(String.valueOf("0"));
        ans_one.setTextColor(Color.WHITE);
        ans_ten.setText(String.valueOf("0"));
        ans_ten.setTextColor(Color.WHITE);
        ans_hundred.setText(String.valueOf("0"));
        ans_hundred.setTextColor(Color.WHITE);
        ans_thousand.setText(String.valueOf("0"));
        ans_thousand.setTextColor(Color.WHITE);
        ans_tenthousand.setText(String.valueOf("0"));
        ans_tenthousand.setTextColor(Color.WHITE);
    }

    @Override
    public void onNumberClicked(int number) {

        /* 버튼이 클릭되었을 때 처리 */
        if (zeroCarrying) {
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
//        //각 자리수를 곱하는 과정 처리
//        if (currentStage < 7) {
//            //세 자리 수 중 하나가 0이거나 곱셈 결과가 받아올림이 없는 경우 0을 입력하는 수고를 덜도록
//            //사용자는 input2TextView에만 값을 입력함
//            if (zeroCarrying) {
//                input2TextView.setText(String.valueOf(number));
//            } else {
//                //사용자 입력 처리 : 첫번째 입력인 경우 input1TextView에 값을 입력함
//                if (carrying) {
//                    input1TextView.setText(String.valueOf(number));
//                    carrying = false;
//                } else {
//                    //두번째 입력인 경우 input2TextView에 값을 입력함
//                    input2TextView.setText(String.valueOf(number));
//                    carrying = true;
//                }
//            }
//        } else {
//
//            if (input1TextView != null) {
//                input1TextView.setText(String.valueOf(number));
//            }
//            if (input2TextView != null) {
//                input2TextView.setText(String.valueOf(number));
//            }
//        }
        Log.v(LOG_TAG, "zeroCarrying : " + String.valueOf(zeroCarrying));
        Log.v(LOG_TAG, "carrying : " + String.valueOf(carrying));

    }

    public void onClearClicked() {
        currentStage = 0;
        carrying = true;
        zeroCarrying = false;
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

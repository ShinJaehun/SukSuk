package com.shinjaehun.suksuk;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shinjaehun on 2016-06-06.
 */
public class ProblemFragment extends Fragment implements NumberpadClickListener {

    private static final String LOG_TAG = ProblemFragment.class.getSimpleName();


//    private SoundPool soundPool;
//    private int soundBeep;

    public TextView operand1TextView, operand2TextView, operand3TextView, operand4TextView, operand5TextView;
    public TextView input1TextView, input2TextView;

    //나누기 받아내림을 표시하기 위한 ImageView
    public ImageView currentMark;

    //선생님 도와주세요 버튼
    public Button help;

    //두 자리 수를 입력할 때 inputTextView를 전환하는 스위치
    public boolean carrying = true;

    //여기서 multiInput은 입력할 inputTextView가 하나인지 둘인지를 결정하는 스위치
    //multiInput이 true이면 입력 하나만, true이면 입력 둘
    public boolean multiInput = false;

    //현재 과정
    public int currentStage = 0;

    //최종 단계인가?
    public boolean isFinal = false;

    //곱셈 또는 나눗셈 결과
    public int ans = 0;

    public final static Map<String, Integer> myRecords = new HashMap<String, Integer>() {
        //테스트중
        //결국 이 Map 값도 Object로 외부로 넘겨야 하지 않을까 싶다...
        {
//            put("triple", 0);
//            put("fifth", 0);
            put("nomiss", 0);
            put("mul22first", 0);
            put("mul22expert", 0); //five times
            put("mul22master", 0); //ten times
            put("mul22fastest", 0);
        }
    };

    public int score = 0;
    //테스트중
    //score 값은 activity가 재실행되면서 다시 0으로 리셋된다.
    //이걸 이용하면 '한번도 틀리지 않고 문제 해결하기' 기록을 구현할 수 있겠다.
    //현재 Multiply22Fragment에서 테스트중;;;;;

    public void startPractice() {
    }

//    private void initSound() {
//        soundPool = new SoundPool(1, AudioManager.STREAM_ALARM, 0);
//        soundBeep = soundPool.load(getActivity().getApplicationContext(), R.raw.beep, 1);
//    }

    public void markOperandAndInput() {
        //곱셈할 각 자리수를 빨간색으로 표시
        if (operand1TextView != null) {
            operand1TextView.setTextColor(Color.RED);
        }
        if (operand2TextView != null) {
            operand2TextView.setTextColor(Color.RED);
        }
        if (operand3TextView != null) {
            operand3TextView.setTextColor(Color.RED);
        }
        if (operand4TextView != null) {
            operand4TextView.setTextColor(Color.RED);
        }
        if (operand5TextView != null) {
            operand5TextView.setTextColor(Color.RED);
        }

        //입력할 텍스트 뷰를 임시로 'A'와 'B'로 표시
        if (input1TextView != null) {
            input1TextView.setText("?");
            input1TextView.setTextColor(Color.BLUE);
        }
        if (input2TextView != null) {
            input2TextView.setText("?");
            input2TextView.setTextColor(Color.BLUE);
        }

        if (input1TextView != null && input2TextView != null) {
            multiInput = true;
        } else {
            multiInput = false;
        }
    }

    public boolean result() {
        //입력한 결과 처리

        int temp = 0, temp1 = 0, temp2 = 0;

        //temp1에 사용자의 첫번째 입력 값 저장
        if (input1TextView == null) {
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
        if (input2TextView == null) {
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
            if (operand3TextView != null) {
                operand3TextView.setTextColor(Color.GRAY);
            }
            if (operand4TextView != null) {
                operand4TextView.setTextColor(Color.GRAY);
            }
            if (operand5TextView != null) {
                operand5TextView.setTextColor(Color.GRAY);
            }

            //입력했던 내용 회색으로 되돌리기
            if (input1TextView != null) {
                input1TextView.setTextColor(Color.GRAY);
            }
            if (input2TextView != null) {
                input2TextView.setTextColor(Color.GRAY);
            }

            //받아내림 표시 회색으로 되돌리기
            if (currentMark != null) {
                markSlashOff();
            }

            //연산 종료 처리
            if (isFinal) {
                finalStage();
                return false;
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
                input1TextView.setTextColor(Color.BLUE);
            }
            if (input2TextView != null) {
                input2TextView.setText("?");
                input2TextView.setTextColor(Color.BLUE);
            }

            return false;
        }
    }

    public void markSlashOn() {
        //나눗셈 받아내림 표시하기
        currentMark.setVisibility(View.VISIBLE);
        currentMark.setImageResource(R.drawable.slash_red);
    }

    public void markSlashOff() {
        //나눗셈 받아내림 표시 해제하기
        //완전히 삭제해버릴 필요는 없겠지?
        //iv.setVisibility(View.INVISIBLE);
        currentMark.setImageResource(R.drawable.slash_gray);
        currentMark = null;
    }

    public void flashText(boolean result) {
        //화면 한 가운데 정오답 결과 표시하기
        TextView textView;

//        initSound();
//        soundPool.play(soundBeep, 1f, 1f, 0, 0, 1f);


        Effects.getInstance().playBeep(Effects.SOUND_1);

        String[] correctMsg = {"정답!", "제법인데~", "훌륭해!", "꽤 하는걸?", "맞았어!",
                "잘했어!", "끝내준다!", "바로 그거야", "축하축하", "짝짝짝"};
        String[] wrongMsg = {"아니거든!", "땡~", "메롱메롱~", "제대로 해봐!", "다시 해보셈",
                "ㅋㅋㅋ", "약오르지~", "틀리셨습니다", "아니라고!", "실수했구나"};

        if (result) {
            textView = (TextView)getActivity().findViewById(R.id.answer_right);
            textView.setText(correctMsg[(int)(Math.random() * correctMsg.length)]);
        } else {
            textView = (TextView)getActivity().findViewById(R.id.answer_wrong);
            textView.setText(wrongMsg[(int)(Math.random() * wrongMsg.length)]);
        }

        textView.setVisibility(View.VISIBLE);
        textView.setAlpha(1.0f);
        textView.animate().alpha(0.0f).setDuration(1000).start();
    }

    public void finalStage() {
        //연산 종료 후 처리
        /* finalStage()에서 값을 초기화한 후 다시 nextStage()를 호출하지는 않는데
        * 어차피 입력 버튼을 누르면 nextStage()를 호출하기 때문 */

//        Toast toastR = Toast.makeText(getActivity(), "축하합니다!", Toast.LENGTH_LONG);
//        toastR.setGravity(Gravity.CENTER, 0, 0);
//        toastR.show();

        //타~다~
        Effects.getInstance().playTada(Effects.SOUND_2);

        //참잘했어요 이미지 표시하기
        ImageView verygood = (ImageView)getActivity().findViewById(R.id.verygood);
        verygood.setVisibility(View.VISIBLE);

        //참잘했어요 이미지 나온 이후에 버튼 입력 해제
        ((ProblemActivity)getActivity()).unSetListener();

        //이미지 클릭하면 액티비티 재시작!
        verygood.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getActivity().getIntent();
                getActivity().finish();
                startActivity(intent);
            }
        });

    }


    @Override
    public void onNumberClicked(int number) {
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

    public void nextStage(){

    }

    public void onClearClicked() {

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

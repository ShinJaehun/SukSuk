package com.shinjaehun.suksuk;

import android.app.Fragment;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shinjaehun on 2016-06-06.
 */
public class ProblemFragment extends Fragment {

//    private SoundPool soundPool;
//    private int soundBeep;

    private int i = 0;

    public final static Map<String, Integer> myRecords = new HashMap<String, Integer>() {
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
    //score 값은 activity가 재실행되면서 다시 0으로 리셋된다.
    //이걸 이용하면 '한번도 틀리지 않고 문제 해결하기' 기록을 구현할 수 있겠다.

    public void startPractice() {
    }



//    private void initSound() {
//        soundPool = new SoundPool(1, AudioManager.STREAM_ALARM, 0);
//        soundBeep = soundPool.load(getActivity().getApplicationContext(), R.raw.beep, 1);
//    }

    public void flashText(boolean result) {
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

        //이딴 식으로 구현하지 말고... 배열에다 문자열 집어 넣어서... random으로 선택하라면 되잖어!!!!!
//        String answer = null;
//        int random = (int)(Math.random() * 5) + 1;
//        if (result) {
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
        textView.setVisibility(View.VISIBLE);
        textView.setAlpha(1.0f);
        textView.animate().alpha(0.0f).setDuration(1000).start();
    }


    public void finalStage() {
        /* finalStage()에서 값을 초기화한 후 다시 nextStage()를 호출하지는 않는데
        * 어차피 입력 버튼을 누르면 nextStage()를 호출하기 때문 */

        Toast toastR = Toast.makeText(getActivity(), "축하합니다!", Toast.LENGTH_LONG);
        toastR.setGravity(Gravity.CENTER, 0, 0);
        toastR.show();

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

//        new CountDownTimer(5000, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//
//        }.start();

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {}

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 5000);

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
    }

}

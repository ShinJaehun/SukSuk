package com.shinjaehun.suksuk;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by shinjaehun on 2016-06-06.
 */
public class ProblemFragment extends Fragment implements NumberpadClickListener {

    private static final String DESCRIBABLE_KEY = "describable_key";

    private static final String LOG_TAG = ProblemFragment.class.getSimpleName();

//    private SoundPool soundPool;
//    private int soundBeep;

    public TextView operand1TextView, operand2TextView, operand3TextView, operand4TextView, operand5TextView;
    public TextView input1TextView, input2TextView;

    //나누기 받아내림을 표시하기 위한 ImageView
    public ImageView currentMark;

    //선생님 도와주세요 버튼
    public ImageButton help;

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

//    public final Map<String, Integer> achievementsMap= new HashMap<String, Integer>() {
//        //테스트중
//        //결국 이 Map 값도 Object로 외부로 넘겨야 하지 않을까 싶다...
//        {
////            put("triple", 0);
////            put("fifth", 0);
//            put("noerrors", 0);
//            put("mul22first", 0);
//            put("mul22expert", 0); //five times
//            put("mul22master", 0); //ten times
//            put("mul22fastest", 0);
//        }
//    };

    //걸린 시간 처리를 위한 변수들
    public long startTime; //각 Fragment의 startPractice()에 정의되어 있다.
    private long endTime, elapsedTime; //여기서 사용할거라서 private으로 정의함.

    //실수가 없는지 확인하기 위한 스위치
    private boolean isMistake = false;

    //활동 결과를 표시해줄 dialog
    private DialogResult dialogResult;

    //ProblemActivity에서 받아온 DAO를 여기에 저장한다.
    public static AchievementDAO aDAO;

    //newInstance()를 통해 받아올 operation 값
    private static String op;

    public static final ProblemFragment newInstance(String operation, AchievementDAO achievementDAO) {

        //이건 effective java에 나오는 기술인데
        //생성자 대신 static factory 메소드 사용하기
        //'자신의 클래스 인스턴스만 반환하는 생성자와 달리 static factory 메소드는 자신이 반환하는 타입의
        // 어떤 서브 타입 객체도 반환할 수 있다.'

        op = operation;
        ProblemFragment problemFragment = null;
        switch (operation) {
            case "multiply32":
//                    multiply32Fragment = new Multiply32Fragment();
//                    ft.add(R.id.fragment_container, multiply32Fragment).commit();
//                    break;
                problemFragment = new Multiply32Fragment();
                break;
            case "multiply22":
                problemFragment = new Multiply22Fragment();
                break;
            case "divide21":
                problemFragment = new Divide21Fragment();
                break;
            case "divide22":
                problemFragment = new Divide22Fragment();
                break;
            case "divide32":
                problemFragment = new Divide32Fragment();
                break;
            default:
                //이건 실행되면 안돼
                break;
        }
        Bundle args = new Bundle();
        args.putSerializable(DESCRIBABLE_KEY, achievementDAO);
        aDAO = achievementDAO;
        //이렇게 하여 PlaceActivity에서 생성한 DAO instance를 Fragment로 넘길 수 있다.
        return problemFragment;
    }

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

        if (ans == temp) {
           //정답처리

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

            Log.v(LOG_TAG, "Is a Mistake(No!) : " + String.valueOf(isMistake));
            return true;

        } else {
           //오답처리
            isMistake = true;

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

            Log.v(LOG_TAG, "Is a Mistake(Yes!) : " + String.valueOf(isMistake));
            return false;
        }
    }

    public void markSlashOn() {
        //나눗셈 받아내림 표시하기
        currentMark.setVisibility(View.VISIBLE);
        currentMark.setImageResource(R.mipmap.slash_red);
    }

    public void markSlashOff() {
        //나눗셈 받아내림 표시 해제하기
        //완전히 삭제해버릴 필요는 없겠지?
        //iv.setVisibility(View.INVISIBLE);
        currentMark.setImageResource(R.mipmap.slash_gray);
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
        //투명한 효과
        textView.setAlpha(1.0f);
        //바로 사라지기
        textView.animate().alpha(0.0f).setDuration(1000).start();
    }

    public void finalStage() {
        //연산 종료 후 처리
        /* finalStage()에서 값을 초기화한 후 다시 nextStage()를 호출하지는 않는데
        * 어차피 입력 버튼을 누르면 nextStage()를 호출하기 때문 */

//        Toast toastR = Toast.makeText(getActivity(), "축하합니다!", Toast.LENGTH_LONG);
//        toastR.setGravity(Gravity.CENTER, 0, 0);
//        toastR.show();

        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        //걸린 시간 측정

        //테스트...

        List<Achievement> achievementsLists = new ArrayList<Achievement>();
        achievementsLists = aDAO.getAchivementsByType(op);

//        switch (op) {
//            case "multiply32":
//                break;
//            case "multiply22":
//                aLists = aDAO.getAchivementsByType("mul22");
//                break;
//            case "divide21":
//                break;
//            case "divide22":
//                break;
//            case "divide32":
//                break;
//            default:
//                break;
//        }
//        aLists = aDAO.getAchivementsByType("common");
//        bLists = aDAO.getAchivementsByType(op);
//        achievementsLists.addAll(aLists);
//        achievementsLists.addAll(bLists);

        StringBuilder sb = new StringBuilder();
        //계산하는데 걸린 시간 -> 최종적으로는 빼기
        sb.append(getElapsedTime(elapsedTime) + "가 걸렸습니다!\n");

        for (Achievement achievement : achievementsLists) {
            Log.v(LOG_TAG, achievement.getName());
        }

        for (Achievement achievement : achievementsLists) {
            if ((achievement.getIsUnlock() == 0) && (achievement.getType().equals(op) && achievement.getAka().equals("first"))) {
                Log.v(LOG_TAG, achievement.getName());
                sb.append(achievement.getName() + "\n" + achievement.getDescription() + "\n\n");
                aDAO.updateAchievement(achievement.getId(), 1, achievement.getNumber(), achievement.getValue());
            }
            if (isMistake == false && achievement.getAka().equals("noerrors")) {
                Log.v(LOG_TAG, achievement.getName() + " " + achievement.getNumber());
                int ag = achievement.getNumber();
                int number = ag + 1;
                sb.append(achievement.getName() + " " + number + "회 성공!\n" + achievement.getDescription() + "\n\n");
                aDAO.updateAchievement(achievement.getId(), 1, number, achievement.getValue());
            }
//            if (elapsedTime < Long.parseLong(a.getValue())) {
//
//            }
        }

        final String resultMessage = sb.toString();

        isMistake = false;
        //다시 원래대로?

        //타~다~
        Effects.getInstance().playTada(Effects.SOUND_2);

        //참잘했어요 이미지 표시하기
        ImageView verygood = (ImageView)getActivity().findViewById(R.id.verygood);
        verygood.setVisibility(View.VISIBLE);

        //참잘했어요 이미지 나온 이후에 버튼 입력 해제
        ((ProblemActivity)getActivity()).unSetListener();

        verygood.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이미지 클릭하면 액티비티 재시작!
//                Intent intent = getActivity().getIntent();
//                getActivity().finish();
//                startActivity(intent);
                dialogResult = new DialogResult(getActivity(),
                        "결과", resultMessage, clickListener);
                //clickListener는 dialogResult의 clickListener, 아래 구현되어 있다.
                elapsedTime = 0;
                dialogResult.show();
            }
        });

    }

    private static String getElapsedTime(long elapsedTime) {
        //계산하는데 걸린 시간 측정을 위한 함수
        if (elapsedTime < 0) {
            throw new IllegalArgumentException("elapsedTime must be greater than 0!");
        }

//        long days = TimeUnit.NANOSECONDS.toDays(elapsedTime);
//        elapsedTime -= TimeUnit.DAYS.toMillis(days);

        //TimeUnit.MILLISECONDS로 썼다가 계속 문제가 발생했었음 NanoTime()으로 계산했으니 NANOSECONDS로 변환해야 함.
        long hours = TimeUnit.NANOSECONDS.toHours(elapsedTime);
        Log.v(LOG_TAG, "hours : " + Long.valueOf(hours));
        elapsedTime -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.NANOSECONDS.toMinutes(elapsedTime);
        Log.v(LOG_TAG, "minutes : " + Long.valueOf(minutes));

        elapsedTime -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.NANOSECONDS.toSeconds(elapsedTime);
        Log.v(LOG_TAG, "seconds : " + Long.valueOf(seconds));

        //이쪽 로직이 간단해보이는데 이해하기 어렵다.
//        long minutes = TimeUnit.NANOSECONDS.toHours(elapsedTime);
//        long seconds = TimeUnit.NANOSECONDS.toSeconds(elapsedTime) -
//                TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(elapsedTime));

        StringBuilder sb = new StringBuilder(64);
        if (hours > 1) {
            sb.append(hours);
            sb.append("시간 ");
        }
        if (minutes > 1) {
            sb.append(minutes);
            sb.append("분 ");
        }
        sb.append(seconds);
        sb.append("초");

        return(sb.toString());

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        //dialog의 clickListener를 여기서 처리한다.
        @Override
        public void onClick(View v) {
            //dialog 확인 버튼을 클릭하면 액티비티 재시작!
            Intent intent = getActivity().getIntent();
            dialogResult.dismiss();
            //dialog를 dismiss()하지 않으면 android view windowleaked 오류가 발생한다.
            getActivity().finish();
            startActivity(intent);
        }
    };


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
        //다시할래 버튼 눌렀을 때 처리

        //사용자가 입력할 텍스트 뷰를 다시 'A'와 'B'로 되돌림
        if (input1TextView != null) {
            input1TextView.setText("?");
            input1TextView.setTextColor(Color.BLUE);
        }
        if (input2TextView != null) {
            input2TextView.setText("?");
            input2TextView.setTextColor(Color.BLUE);
        }
        //inputTextView가 둘 일 때 다시할래 누른 다음 다시 input1TextView부터 입력을 받을 수 있도록
        carrying = true;
    }

    public void onOKClicked() {
        //입력 버튼 눌렀을 때 처리
        if (input1TextView == null) {
            if (input2TextView.getText().toString().matches("[0-9]")) {
                //'?' 기호가 그대로 입력되는 문제 방지
                if (result()) {
                    nextStage();
                }
            }
        } else {
            if (input1TextView.getText().toString().matches("[0-9]") && input2TextView.getText().toString().matches("[0-9]"))
                //'?' 기호가 그대로 입력되는 문제 방지
                if (result()) {
                    nextStage();
                }
        }

//      nextStage()를 바로 실행해버리면 '?' 기호가 그대로 입력되는 문제가 발생한다.
//        if (result()) {
//            nextStage();
//        }
    }


}

package com.shinjaehun.suksuk;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by shinjaehun on 2016-04-16.
 */
public class ProblemActivity extends AppCompatActivity {

    private static final String LOG_TAG = ProblemActivity.class.getSimpleName();
    private NumberpadFragment numberpadFragment;
    private ProblemFragment problemFragment;


//    private SoundPool soundPool;
//    private int soundBeep;

//    private Multiply32Fragment multiply32Fragment;
//    private Multiply22Fragment multiply22Fragment;
//    private Divide21Fragment divide21Fragment;
//    private Divide22Fragment divide22Fragment;
//    private Divide32Fragment divide32Fragment;

    String operation;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_problems);
        Intent intent = getIntent();
        operation = intent.getStringExtra("operation");

//        initSound();

        Effects.getInstance().init(this);
        numberpadFragment = (NumberpadFragment) getFragmentManager().findFragmentById(R.id.numberPadFragment);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

//        if (intent != null) {
//            switch (operation) {
//                case "multiply32":
////                    multiply32Fragment = new Multiply32Fragment();
////                    ft.add(R.id.fragment_container, multiply32Fragment).commit();
////                    break;
//                    problemFragment = new Multiply32Fragment();
//                    ft.add(R.id.fragment_container, problemFragment).commit();
//                    break;
//
//                case "multiply22":
//                    multiply22Fragment = new Multiply22Fragment();
//                    ft.add(R.id.fragment_container, multiply22Fragment).commit();
//                    break;
//                case "divide21":
//                    divide21Fragment = new Divide21Fragment();
//                    ft.add(R.id.fragment_container, divide21Fragment).commit();
//                    break;
//                case "divide22":
//                    divide22Fragment = new Divide22Fragment();
//                    ft.add(R.id.fragment_container, divide22Fragment).commit();
//                    break;
//                case "divide32":
//                    divide32Fragment = new Divide32Fragment();
//                    ft.add(R.id.fragment_container, divide32Fragment).commit();
//                    break;
//            }
//        }

        if (intent != null) {
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
                    problemFragment = new Divide32NewFragment();
                    break;
            }
        }
        ft.add(R.id.fragment_container, problemFragment).commit();


    }


//    private void initSound() {
//        soundPool = new SoundPool(1, AudioManager.STREAM_ALARM, 0);
//        soundBeep = soundPool.load(getApplicationContext(), R.raw.beep, 1);
//    }

//    @Override
//    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        switch (operation) {
//            case "multiply32":
//                numberpadFragment.setClickListener(multiply32Fragment);
//                multiply32Fragment.startPractice();
//                break;
//            case "multiply22":
//                numberpadFragment.setClickListener(multiply22Fragment);
//                multiply22Fragment.startPractice();
//                break;
//            case "divide21":
//                numberpadFragment.setClickListener(divide21Fragment);
//                divide21Fragment.startPractice();
//                break;
//            case "divide22":
//                numberpadFragment.setClickListener(divide22Fragment);
//                divide22Fragment.startPractice();
//                break;
//            case "divide32":
//                numberpadFragment.setClickListener(divide32Fragment);
//                divide32Fragment.startPractice();
//                break;
//        }
//
//    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        setListener(operation);
        switch (operation) {
            case "multiply32":
//                numberpadFragment.setClickListener(multiply32Fragment);
//                multiply32Fragment.startPractice();
//                break;
                numberpadFragment.setClickListener((Multiply32Fragment)problemFragment);
                break;
            case "multiply22":
                numberpadFragment.setClickListener((Multiply22Fragment)problemFragment);
                break;
            case "divide21":
                numberpadFragment.setClickListener((Divide21Fragment)problemFragment);
                break;
            case "divide22":
                numberpadFragment.setClickListener((Divide22Fragment)problemFragment);
                break;
            case "divide32":
                numberpadFragment.setClickListener((Divide32NewFragment)problemFragment);
                break;
        }
        problemFragment.startPractice();

    }

    //ClickListener를 set 했다 unset 했다 할 일이 있을까봐 이렇게 따로 구현했는데
    //Activity를 다시 시작하는 과정을 통해 자동적으로 ClickListener가 재시작되므로 필요가 없어졌다.
//    public void setListener(String op) {
//        switch (op) {
//            case "multiply32":
////                numberpadFragment.setClickListener(multiply32Fragment);
////                multiply32Fragment.startPractice();
////                break;
//                numberpadFragment.setClickListener((Multiply32Fragment)problemFragment);
//                break;
//            case "multiply22":
//                numberpadFragment.setClickListener((Multiply22Fragment)problemFragment);
//                break;
//            case "divide21":
//                numberpadFragment.setClickListener((Divide21Fragment)problemFragment);
//                break;
//            case "divide22":
//                numberpadFragment.setClickListener((Divide22Fragment)problemFragment);
//                break;
//            case "divide32":
//                numberpadFragment.setClickListener((Divide32Fragment)problemFragment);
//                break;
//        }
//    }

    public void unSetListener() {
        numberpadFragment.unSetClickListener();
    }

    /*
    나름대로 Multipane 모드를 구현하려고 했으나 potrait에서 landscape로 전환할 때 다시 activity가 oncreate 해버려서
    다시 처음부터 실행되는 문제가 있었다. saveinstance 할 정보가 많지 않으면 문제가 없겠으나 너무 많아서... 포기
    걍 potrait를 고정시키는 것으로 문제를 해결했다.

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        if(isMultipane()) {
            setContentView(R.layout.activity_problems_multipane);
        } else {
            setContentView(R.layout.activity_problems);
        }
        deployFragments();

    }

    public boolean isMultipane() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public void deployFragments() {
        numberpadFragment = (NumberpadFragment)getFragmentManager().findFragmentById(R.id.numberPadFragment);
        multiply32Fragment = (Multiply32Fragment)getFragmentManager().findFragmentById(R.id.multiply32Fragment);

        numberpadFragment.setClickListener(multiply32Fragment);
        multiply32Fragment.startPractice();
    }*/


  /*  private void buttonClicked(int num) {

    }

    public void onClick(View view) {
        //입력한 버튼에 따라 buttonClicked로 값을 넘김
        switch (view.getId()) {
            case R.id.button_1:
                buttonClicked(1);
                break;
            case R.id.button_2:
                buttonClicked(2);
                break;
            case R.id.button_3:
                buttonClicked(3);
                break;
            case R.id.button_4:
                buttonClicked(4);
                break;
            case R.id.button_5:
                buttonClicked(5);
                break;
            case R.id.button_6:
                buttonClicked(6);
                break;
            case R.id.button_7:
                buttonClicked(7);
                break;
            case R.id.button_8:
                buttonClicked(8);
                break;
            case R.id.button_9:
                buttonClicked(9);
                break;
            case R.id.button_0:
                buttonClicked(0);
                break;

            //입력 초기화
            case R.id.button_clear:
                currentStage = 0;
                totalCarry = 0;
                carrying = true;
                ans = 0;
                initNumbers();
                nextStage();
                break;

            //사용자가 입력한 결과 : result() 결과에 따라 다음 과정으로 진행함
            case R.id.button_enter:
                if (result()) {
                    nextStage();
                    break;
                }

            default:
                break;
        }
    }*/

}

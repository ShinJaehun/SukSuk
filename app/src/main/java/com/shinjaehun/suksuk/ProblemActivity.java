package com.shinjaehun.suksuk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by shinjaehun on 2016-04-16.
 */
public class ProblemActivity extends AppCompatActivity {

    private static final String LOG_TAG = ProblemActivity.class.getSimpleName();
    private NumberpadFragment numberpadFragment;
    private Multiply32Fragment multiply32Fragment;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_problems);


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


        numberpadFragment = (NumberpadFragment)getFragmentManager().findFragmentById(R.id.numberPadFragment);
        multiply32Fragment = (Multiply32Fragment)getFragmentManager().findFragmentById(R.id.multiply32Fragment);

        numberpadFragment.setClickListener(multiply32Fragment);
        multiply32Fragment.startPractice();
    }







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

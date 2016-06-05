package com.shinjaehun.suksuk;

import android.app.Fragment;
import android.view.View;
import android.widget.TextView;

/**
 * Created by shinjaehun on 2016-06-06.
 */
public class ProblemFragment extends Fragment {

    public void startPractice() {

    }

    public void flashText(boolean trueOrFalse) {
        TextView textView;
        String answer = null;
        int random = (int)(Math.random() * 5) + 1;
        if (trueOrFalse) {
            textView = (TextView)getActivity().findViewById(R.id.answer_right);
            switch (random) {
                case 1:
                    answer = "정답!";
                    break;
                case 2:
                    answer = "제법인데~";
                    break;
                case 3:
                    answer = "훌륭해!";
                    break;
                case 4:
                    answer = "꽤 하는걸?";
                    break;
                case 5:
                    answer = "맞았어!";
                    break;
                default:
                    break;
            }
        } else {
            textView = (TextView)getActivity().findViewById(R.id.answer_wrong);
            switch (random) {
                case 1:
                    answer = "아니거든!";
                    break;
                case 2:
                    answer = "땡!!!";
                    break;
                case 3:
                    answer = "메롱메롱~";
                    break;
                case 4:
                    answer = "제대로 해봐!";
                    break;
                case 5:
                    answer = "다시 해보셈!";
                    break;
                default:
                    break;
            }
        }

        textView.setText(answer);
        textView.setVisibility(View.VISIBLE);
        textView.setAlpha(1.0f);
        textView.animate().alpha(0.0f).setDuration(1000).start();
    }
}

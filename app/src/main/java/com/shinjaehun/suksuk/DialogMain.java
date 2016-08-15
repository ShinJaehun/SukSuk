package com.shinjaehun.suksuk;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by shinjaehun on 2016-08-14.
 */
public class DialogMain extends Dialog {

    private Context mContext;
//    private View.OnClickListener mClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // dialog 외부 흐림 효과
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_main);

        LinearLayout multiply22 = (LinearLayout)findViewById(R.id.multiply22);
        multiply22.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProblemActivity.class);
                intent.putExtra("operation", "multiply22");
                mContext.startActivity(intent);
                destroyDialog();
            }
        });

        LinearLayout multiply32 = (LinearLayout)findViewById(R.id.multiply32);
        multiply32.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProblemActivity.class);
                intent.putExtra("operation", "multiply32");
                mContext.startActivity(intent);
                destroyDialog();
            }
        });

        LinearLayout div21 = (LinearLayout)findViewById(R.id.divide21);
        div21.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProblemActivity.class);
                intent.putExtra("operation", "divide21");
                mContext.startActivity(intent);
                destroyDialog();
            }
        });

        LinearLayout div22 = (LinearLayout)findViewById(R.id.divide22);
        div22.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProblemActivity.class);
                intent.putExtra("operation", "divide22");
                mContext.startActivity(intent);
                destroyDialog();
            }
        });

        LinearLayout div32 = (LinearLayout)findViewById(R.id.divide32);
        div32.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProblemActivity.class);
                intent.putExtra("operation", "divide32");
                mContext.startActivity(intent);
                destroyDialog();
            }
        });

        Button confirmBT = (Button)findViewById(R.id.button_confirm);
        confirmBT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                destroyDialog();
            }
        });

    }

    private void destroyDialog() {
        dismiss();
        //dialog를 dismiss()하지 않으면 android view windowleaked 오류가 발생한다.
    }

    public DialogMain(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Dialog 창에서 제목 없애기
        mContext = context;
//        mClickListener = clickListener;
    }


}

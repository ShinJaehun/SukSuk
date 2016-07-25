package com.shinjaehun.suksuk;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by shinjaehun on 2016-07-25.
 */
public class DialogResult extends Dialog {

    private TextView titleTV, contentTV;
    private Button confirmBTN;
    private String title, content;

    private View.OnClickListener clickListener;
    //이렇게 clickListener를 dialog 내에서 처리하기보다 Activity쪽으로 넘겨 주는 편이 훨씬 낫다!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // dialog 외부 흐림 효과
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_result);

        titleTV = (TextView)findViewById(R.id.text_title);
        contentTV = (TextView)findViewById(R.id.text_content);
        confirmBTN = (Button)findViewById(R.id.button_confirm);

        titleTV.setText(title);
        contentTV.setText(content);

//        confirmBTN.setOnClickListener(new Button.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), ProblemActivity.class);
//                getContext().startActivity(intent);
//            }
//        });

        confirmBTN.setOnClickListener(clickListener);
    }

    public DialogResult(Context context, String title, String content, View.OnClickListener clickListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.title = title;
        this.content = content;
        this.clickListener = clickListener;
    }
}

package com.shinjaehun.suksuk;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by shinjaehun on 2016-07-25.
 */
public class DialogResult extends Dialog implements AdapterView.OnItemClickListener {

//    private TextView titleTV;
    private Button confirmBTN;
    private ListView achievementsLV;

    private View.OnClickListener clickListener;
    //이렇게 clickListener를 dialog 내에서 처리하기보다 Activity쪽으로 넘겨 주는 편이 훨씬 낫다!

//    String operation;
//    AchievementDAO achievementDAO;
//    Long elapseTime;
//    Boolean isMistake;
    ListAchievementAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // dialog 외부 흐림 효과
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_result);

//        titleTV = (TextView)findViewById(R.id.text_title);
//        contentTV = (TextView)findViewById(R.id.text_content);
        achievementsLV = (ListView)findViewById(R.id.list_achievements);

//        ListAchievementAdapter adapter = new ListAchievementAdapter(getContext(), new ArrayList<Achievement>());
        achievementsLV.setAdapter(adapter);

//        AchievementMessageTask achievementMessageTask = new AchievementMessageTask(getContext(), operation, achievementDAO, elapseTime, isMistake, adapter);
//        achievementMessageTask.execute();

        achievementsLV.setOnItemClickListener(this);

        confirmBTN = (Button)findViewById(R.id.button_confirm);

//        contentTV.setText(content);

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

    public DialogResult(Context context, ListAchievementAdapter laa, View.OnClickListener clickListener) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Dialog 창에서 제목 없애기

//        this.operation = op;
//        this.achievementDAO = aDAO;
//        this.elapseTime = eTime;
//        this.isMistake = miss;
        this.adapter = laa;
        this.clickListener = clickListener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long today = ((Achievement)parent.getAdapter().getItem(position)).getTimestamp();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        DateFormat sdf = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.KOREAN);
        Date resultDate = new Date(today);
        Toast.makeText(getContext(), sdf.format(resultDate), Toast.LENGTH_SHORT).show();
    }
}

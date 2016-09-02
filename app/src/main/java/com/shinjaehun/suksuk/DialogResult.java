package com.shinjaehun.suksuk;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by shinjaehun on 2016-07-25.
 */
public class DialogResult extends Dialog {
//    public class DialogResult extends Dialog implements AdapterView.OnItemClickListener {

    private static final String LOG_TAG = DialogResult.class.getSimpleName();

    //    private TextView titleTV;
    private Button confirmBTN;
//    private ListView resultLV;
//    private ListView achievementsLV;
    private TextView operationTV;
    private TextView operationNumberTV;
    private TextView totalNumberTV;
    private LinearLayout hasMistakeL;
    private LinearLayout timeL;
    private TextView timeMinuteTV;
    private TextView timeSecondTV;

    private static TodayRecords todayRecords;

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

        operationTV = (TextView)findViewById(R.id.text_operation);
        operationNumberTV = (TextView)findViewById(R.id.text_operation_number);
        totalNumberTV = (TextView)findViewById(R.id.text_total_number);
        hasMistakeL = (LinearLayout)findViewById(R.id.layout_has_mistake);
        timeL = (LinearLayout)findViewById(R.id.layout_elapsed_time);
        timeMinuteTV = (TextView)findViewById(R.id.text_time_minute);
        timeSecondTV = (TextView)findViewById(R.id.text_time_second);

        for (Record r : todayRecords.getRecords()) {
            Log.v(LOG_TAG, "todayRecords in DialogResult : " + r.getOperation() + " " + r.getToday() + " " + r.getElapsedTime() + " " + r.isMistake());
        }

        List<Record> records = todayRecords.getRecords();
        Record currentRecord = records.get(records.size() - 1);

        operationTV.setText(currentRecord.getOperation());

        int currentOperationNumber = 0;
        for (Record r : records) {
            if (r.getOperation().equals(currentRecord.getOperation())) {
                currentOperationNumber++;
            }
        }

        operationNumberTV.setText(String.valueOf(currentOperationNumber));
        totalNumberTV.setText(String.valueOf(records.size()));

        if (!currentRecord.isMistake()) {
            hasMistakeL.setVisibility(View.VISIBLE);
        }

//        titleTV = (TextView)findViewById(R.id.text_title);
//        contentTV = (TextView)findViewById(R.id.text_content);
//        resultLV = (ListView)findViewById(R.id.list_achievements);

//        ListAchievementAdapter adapter = new ListAchievementAdapter(getContext(), new ArrayList<Achievement>());
//        resultLV.setAdapter(adapter);

//        AchievementMessageTask achievementMessageTask = new AchievementMessageTask(getContext(), operation, achievementDAO, elapseTime, isMistake, adapter);
//        achievementMessageTask.execute();

//        resultLV.setOnItemClickListener(this);

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

    public DialogResult(Context context, View.OnClickListener clickListener, TodayRecords todayRecords) {
//        public DialogResult(Context context, ListAchievementAdapter laa, View.OnClickListener clickListener) {

        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Dialog 창에서 제목 없애기

//        this.operation = op;
//        this.achievementDAO = aDAO;
//        this.elapseTime = eTime;
//        this.isMistake = miss;
//        this.adapter = laa;
        this.todayRecords = todayRecords;
        this.clickListener = clickListener;
    }

/*    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long today = ((Achievement)parent.getAdapter().getItem(position)).getTimestamp();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        DateFormat sdf = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.KOREAN);
        Date resultDate = new Date(today);
        Toast.makeText(getContext(), sdf.format(resultDate), Toast.LENGTH_SHORT).show();
    }*/
}

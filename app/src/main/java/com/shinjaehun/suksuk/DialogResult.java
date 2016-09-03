package com.shinjaehun.suksuk;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

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

    private static CurrentRecords currentRecords;

    private View.OnClickListener clickListener;
    //이렇게 clickListener를 dialog 내에서 처리하기보다 Activity쪽으로 넘겨 주는 편이 훨씬 낫다!

//    String operation;
//    AchievementDAO achievementDAO;
//    Long elapseTime;
//    Boolean isMistake;
//    ListAchievementAdapter adapter;

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

        for (Record r : currentRecords.getTodayRecords()) {
            Log.v(LOG_TAG, "currentRecords in DialogResult : " + r.getOperation() + " " + r.getDay() + " " + r.getElapsedTime() + " " + r.hasMistake());
        }

        //todayRecords에서 가장 마지막 record를 가져옴
        List<Record> records = currentRecords.getTodayRecords();
        Record currentRecord = records.get(records.size() - 1);

        //operation에 따라 문제 유형 표시
        switch (currentRecord.getOperation()) {
            case "multiply32":
                operationTV.setText("세 자리 수 곱하기 두 자리 수");
                break;
            case "multiply22":
                operationTV.setText("두 자리 수 곱하기 두 자리 수");
                break;
            case "divide21":
                operationTV.setText("두 자리 수 나누기 한 자리 수");
                break;
            case "divide22":
                operationTV.setText("두 자리 수 나누기 두 자리 수");
                break;
            case "divide32":
                operationTV.setText("세 자리 수 나누기 두 자리 수");
                break;
            case "challenge":
                operationTV.setText("도전! 문제풀기");
                break;
            default:
                //이건 실행되면 안돼
                break;
        }

        //해당 유형별 해결한 문제 수 표시
        int currentOperationNumber = 0;
        for (Record r : records) {
            if (r.getOperation().equals(currentRecord.getOperation())) {
                currentOperationNumber++;
            }
        }
        operationNumberTV.setText(String.valueOf(currentOperationNumber));

        //오늘 해결한 문제 수 표시
        totalNumberTV.setText(String.valueOf(records.size()));

        //실수가 없었다면! '한번도 실수하지 않았습니다' 레이아웃 표시
        if (currentRecord.hasMistake() == 0) {
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

    public DialogResult(Context context, View.OnClickListener clickListener, CurrentRecords currentRecords) {
//        public DialogResult(Context context, ListAchievementAdapter laa, View.OnClickListener clickListener) {

        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Dialog 창에서 제목 없애기

//        this.operation = op;
//        this.achievementDAO = aDAO;
//        this.elapseTime = eTime;
//        this.isMistake = miss;
//        this.adapter = laa;
        this.currentRecords = currentRecords;
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

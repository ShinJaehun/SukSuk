package com.shinjaehun.suksuk;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by shinjaehun on 2016-08-01.
 */
public class GetRecordsTask extends AsyncTask<Void, Void, Void> {

    private static final String LOG_TAG = GetRecordsTask.class.getSimpleName();
    private final Context context;
    private final RecordDAO recordDAO;
    private static CurrentRecords currentRecords;

    DialogResult dialogResult;

    ProgressDialog asyncDialog;

    public GetRecordsTask(Context context, RecordDAO recordDAO, CurrentRecords currentRecords) {
        this.context = context;
        this.recordDAO = recordDAO;
        //DAO는 사실 ProblemActivity의 onCreate() 생성되고 ProblemActivity를 생성할 때 인자로 넘어간다.
        //그리고나서 AchievementTask 오브젝트를 생성하는 과정에서 다시 인자로 넘어오게 된다.

        this.currentRecords = currentRecords;
        asyncDialog = new ProgressDialog(this.context);
    }

    @Override
    protected void onPreExecute() {
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.setMessage("결과를 받아옵니다...");
        asyncDialog.show();
        super.onPreExecute();
        Log.v("AsyncTask", "onPreExecute");
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.v(LOG_TAG, "In GetRecordsTask");

        //currentRecords에서 가장 마지막 record를 DB에 저장한다.
        Record currentRecord = currentRecords.getCurrentRecords().get(currentRecords.getCurrentRecords().size() - 1);
        recordDAO.insertRecord(currentRecord.getOperation(), currentRecord.getDay(), currentRecord.getElapsedTime(), currentRecord.hasMistake());

        Log.v(LOG_TAG, "today of currentRecords in GetRecordsTask : " + currentRecords.getToday());


//        List<Record> allRecords = recordDAO.getAllRecords();
        List<Record> todayRecords = recordDAO.getRecordsByDay(currentRecords.getToday());

        for (Record r : todayRecords) {
            Log.v(LOG_TAG, "Today's Records in DB : " + r.getOperation() + " " + r.getDay() + " " + r.getElapsedTime() + " " + r.hasMistake());
        }

        final Map<String, Integer> recordsMap = new HashMap<String, Integer>() {
            {
                put("multiply32", 0);
                put("multiply22", 0);
                put("divide21", 0);
                put("divide22", 0);
                put("divide32", 0);
                put("challenge", 0);
            }
        };

        for (Record r : todayRecords) {
            //아~ 진짜 아름다운 코드! 코드 오브 더 데이!! : todayRecords의 결과를 recordsMap으로 매칭시키는 코드임다ㅠㅠ
            recordsMap.put(r.getOperation(), recordsMap.get(r.getOperation()) + 1);
        }

        for (String operation : recordsMap.keySet()) {
            Log.v(LOG_TAG, "recordsMap " + operation + " : " + recordsMap.get(operation));
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
        asyncDialog.dismiss();

        dialogResult = new DialogResult(context, clickListener, currentRecords);
        dialogResult.setCanceledOnTouchOutside(false);
        dialogResult.show();

        recordDAO.close();

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        //dialog의 clickListener를 여기서 처리한다.
        @Override
        public void onClick(View v) {

            //dialog 확인 버튼을 클릭하면 액티비티 재시작!
            Intent intent = ((Activity)context).getIntent();
            dialogResult.dismiss();
            //dialog를 dismiss()하지 않으면 android view windowleaked 오류가 발생한다.
            ((Activity)context).finish();
            context.startActivity(intent);
        }
    };

    private static String getElapsedTime(long elapsedTime) {
        //측정을 하다보니 오류가 있었는데 아직 뭐가 문제인지 정확히 해결하지는 못했다.
        //초가 60초를 넘어가는 문제...

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
}

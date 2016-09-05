package com.shinjaehun.suksuk;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by shinjaehun on 2016-08-01.
 */
public class ResultTask extends AsyncTask<Void, Void, Void> {

    private static final String LOG_TAG = ResultTask.class.getSimpleName();
    private final Context context;
//    private AchievementDAO achievementDAO;
    private final RecordDAO recordDAO;
    private static CurrentRecords currentRecords;
    private Record currentRecord;
    private RecordMapOfTheDay recordMapOfToday;

    private DialogResult dialogResult;

    private ProgressDialog asyncDialog;

//    private List<Achievement> userAchievements;
    private static List<String> resultMessages;

    //연속풀기 카운터와 스위치
    private static int continueCount = 1;
    private static boolean continueCountSaved = false;

    public ResultTask(Context context, RecordDAO recordDAO, CurrentRecords currentRecords) {
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
        Log.v(LOG_TAG, "In ResultTask");
//        achievementDAO = new AchievementDAO(context);
        resultMessages = new ArrayList<>();

        //currentRecords에서 가장 마지막 record를 DB에 저장한다.
        currentRecord = currentRecords.getCurrentRecords().get(currentRecords.getCurrentRecords().size() - 1);
        recordDAO.insertRecord(currentRecord.getOperation(), currentRecord.getDay(), currentRecord.getElapsedTime(), currentRecord.hasMistake());

        List<Record> records = recordDAO.getAllRecords();
//        List<Record> recordsOfTheDay = new ArrayList<>();

        for (Record r : records) {
            Log.v(LOG_TAG, "All Records in DB : " + r.getOperation() + " " + r.getDay() + " " + r.getElapsedTime() + " " + r.hasMistake());
        }

        //RecordMapOfTheDay : 날짜에 따라 각 유형의 문제를 얼마나 풀었는지 저장하는 자료구조
        //listRecordMapOfTheDay : records에 저장된 모든 자료를 RecordMapOfTheDay 형태로 저장함
        List<RecordMapOfTheDay> listRecordMapOfTheDay = new ArrayList<>();

        if (records != null) {
            //날짜별로 RecordMapOfTheDay 자료를 저장하기 위해 먼저 기존에 저장되어 있는 records에서 중복되지 않게 날짜만 ArrayList로 가져온다.
            List<String> days = new ArrayList<>();
            for (Record r : records) {
                if (days != null) {
                    if (!days.contains(r.getDay())) {
                        days.add(r.getDay());
                    }
                } else {
                    days.add(r.getDay());
                }
            }

//            for (String d : days) {
//                Log.v(LOG_TAG, "Day : " + d);
//            }

            //records에서 날짜에 따라 유형별로 푼 문제 수를 저장해서
            for (String day : days) {
                RecordMapOfTheDay recordMapOfTheDay = new RecordMapOfTheDay();
                recordMapOfTheDay.setDay(day);

                for (Record r : records) {
                    if (r.getDay().equals(day)) {
                        recordMapOfTheDay.setRecordsMap(r);
                    }
                }

                //ArrayList로 저장한다.
                listRecordMapOfTheDay.add(recordMapOfTheDay);

            }

            for (RecordMapOfTheDay rmot : listRecordMapOfTheDay) {
                Log.v(LOG_TAG, "Day " + rmot.getDay());
                for (String operation : rmot.getRecordsMap().keySet()) {
                    Log.v(LOG_TAG, "recordsMap " + operation + " : " + rmot.getRecordsMap().get(operation));
                }
            }

        } else {
            //문제가 있지... currentRecord를 DB에 저장했으니 records에는 최소한 currentRecord가 들어 있어야 한다.
        }

//        for (RecordMapOfTheDay r : recordMapOfTheDays) {
//            Log.v(LOG_TAG, "The day : " + r.getDay());
//            for (String operation : r.getRecordsMap().keySet()) {
//                Log.v(LOG_TAG, "recordMap " + operation + " : " + r.getRecordsMap().get(operation));
//            }
//        }

//        for (String operation : recordsMap.keySet()) {
//            Log.v(LOG_TAG, "recordsMap " + operation + " : " + recordsMap.get(operation));
//        }

//        List<Record> allRecords = recordDAO.getAllRecords();
        //todayRecords에 DB에 들어있는 것까지 모두 포함하여 오늘 기록을 모두 저장한다.

        //오늘 날짜에 해당하는 record만 가져온다.
        List<Record> todayRecords = new ArrayList<>();
        if (records != null) {
            for (Record r : records) {
                if (r.getDay().equals(currentRecord.getDay())) {
                    todayRecords.add(r);
                }
            }
        }

        for (Record r : todayRecords) {
            Log.v(LOG_TAG, "Today's Records in DB : " + r.getOperation() + " " + r.getDay() + " " + r.getElapsedTime() + " " + r.hasMistake());
        }

        recordMapOfToday = new RecordMapOfTheDay();
        recordMapOfToday.setDay(currentRecord.getDay());
        for (Record r : todayRecords) {
            recordMapOfToday.setRecordsMap(r);
        }

        for (String operation : recordMapOfToday.getRecordsMap().keySet()) {
            Log.v(LOG_TAG, "Today's recordsMap " + operation + " : " + recordMapOfToday.getRecordsMap().get(operation));
        }

        int todayTotal = recordMapOfToday.getTotal();
        Log.v(LOG_TAG, "오늘 " + todayTotal + " 문제 풀었어요.");

        int total = 0;
//            int mul32 = 0;
//            int mul22 = 0;
//            int div21 = 0;
//            int div22 = 0;
//            int div32 = 0;
//            int challenge = 0;

        for (RecordMapOfTheDay rmot : listRecordMapOfTheDay) {
            Log.v(LOG_TAG, rmot.getDay() + " : " + rmot.getTotal() + " 문제");
            if (!rmot.getDay().equals(recordMapOfToday.getDay())) {
                if (rmot.getTotal() > total) {
                    total = rmot.getTotal();
                }
            }
        }

        if (todayTotal > total) {
//            Log.v(LOG_TAG, "하루에 가장 많은 문제 풀기");
//            Log.v(LOG_TAG, "지금까지 최고기록 " + total + " 문제를 " + todayTotal + " 문제로 경신하다!");

            StringBuilder sb = new StringBuilder();
            sb.append("하루에 가장 많은 문제 풀기 기록 경신 : ");
            sb.append(todayTotal);
            sb.append("회");

            resultMessages.add(sb.toString());
        }

        String message = compareAllRecord(currentRecord.getOperation(), recordMapOfToday, listRecordMapOfTheDay);
        if (message != null) {
            resultMessages.add(message);
        }

        if (resultMessages.size() != 0) {
            for (String m : resultMessages) {
                Log.v(LOG_TAG, m);
            }
        }

//        List<Record> todayRecords = recordDAO.getRecordsByDay(currentRecords.getToday());
//        int todayTotal = todayRecords.size();

//        Log.v(LOG_TAG, "오늘 " + todayTotal + " 문제 풀었어요.");

//        int totalOfTheDay = 0;
//        for (RecordMapOfTheDay rmot : listRecordMapOfTheDay) {
//            Log.v(LOG_TAG, rmot.getDay() + " : " + rmot.getTotal() + " 문제");
//           if (rmot.getTotal() > totalOfTheDay && !rmot.getDay().equals(currentRecord.getDay())) {
//               totalOfTheDay = rmot.getTotal();
//           }
//        }
//
//        if (todayTotal > totalOfTheDay) {
//            Log.v(LOG_TAG, "지금까지 최고기록 " + totalOfTheDay + " 문제를 " + todayTotal + " 문제로 경신하다!");
//            resultMessages.add("하루에 가장 많은 문제 풀기");
//        }







//
//
//
//
//
//
//
//        //연속풀기 카운터를 DB에 저장했다면 다시 원래대로
//        if (continueCountSaved == true) {
//            continueCount = 1;
//            continueCountSaved = false;
//        } else if ((currentRecords.getCurrentRecords().size() - 2) != -1) {
//            //DB에 저장한 기록이 없고 currentRecord 바로 전 기록이 null이 아니라면
//            if (currentRecord.getOperation().equals(currentRecords.getCurrentRecords().get(currentRecords.getCurrentRecords().size() - 2).getOperation())) {
//                //currentRecord와 바로 전 기록을 비교하여 같은 문제라면 연속풀기 카운터를 증가시킨
//                continueCount++;
//            }
//        }
//        Log.v(LOG_TAG, currentRecord.getOperation() + " 문제 " + continueCount + " 회 연속 풀기.");
//
//        int todayNoerrors = 0;
//        for (Record r : todayRecords) {
//            if (r.hasMistake() == 0) {
//                todayNoerrors++;
//            }
//        }
//        Log.v(LOG_TAG, "오늘 한번도 틀리지 않은 문제 수는 " + todayNoerrors);
//
////        for (Record r : todayRecords) {
////            Log.v(LOG_TAG, "Today's Records in DB : " + r.getOperation() + " " + r.getDay() + " " + r.getElapsedTime() + " " + r.hasMistake());
////        }
//
////        final Map<String, Integer> recordsMap = new HashMap<String, Integer>() {
////            {
////                put("multiply32", 0);
////                put("multiply22", 0);
////                put("divide21", 0);
////                put("divide22", 0);
////                put("divide32", 0);
////                put("challenge", 0);
////            }
////        };
//
//        for (Record r : todayRecords) {
//            //아~ 진짜 아름다운 코드! 코드 오브 더 데이!! : todayRecords의 결과를 recordsMap으로 매칭시키는 코드임다ㅠㅠ
//            recordsMap.put(r.getOperation(), recordsMap.get(r.getOperation()) + 1);
//        }
//
////        for (String operation : recordsMap.keySet()) {
////            Log.v(LOG_TAG, "recordsMap " + operation + " : " + recordsMap.get(operation));
////        }
//
//
//        userAchievements = new ArrayList<>();
//        for (String operation : recordsMap.keySet()) {
//            List<Achievement> achievementsByOperation = achievementDAO.getAchievementsByType(operation);
//
//            for (Achievement achievement : achievementsByOperation) {
//
//                if (achievement.getType().equals(currentRecord.getOperation()) && achievement.getAka().equals("first") && achievement.getLock() == 0) {
////                    achievement.setNumber(achievement.getNumber() + 1);
//                    userAchievements.add(achievement);
//                    achievementDAO.updateAchievement(achievement.getId(), 1, achievement.getNumber(), currentRecords.getToday());
//                    Log.v(LOG_TAG, "DB에 기록 성공! " + operation + " 오늘 처음으로 했어요!");
//                }
//
//                if (achievement.getType().equals(currentRecord.getOperation()) && achievement.getAka().equals("master") && recordsMap.get(operation) > achievement.getNumber()) {
//                    achievement.setNumber(recordsMap.get(operation));
//                    userAchievements.add(achievement);
//                    achievementDAO.updateAchievement(achievement.getId(), 1, achievement.getNumber(), currentRecords.getToday());
//                    Log.v(LOG_TAG, "DB에 기록 성공! " + operation + " : " + achievement.getNumber() + "문제로 기록을 경신하다!!");
//                }
//
//                if (achievement.getType().equals(currentRecord.getOperation()) && achievement.getAka().equals("expert") && continueCount == 3) {
//                    achievement.setNumber(achievement.getNumber() + 1);
//                    userAchievements.add(achievement);
//                    achievementDAO.updateAchievement(achievement.getId(), 1, achievement.getNumber(), currentRecords.getToday());
//                    Log.v(LOG_TAG, "DB에 기록 성공! " + operation + " 3회 연속 풀기 " + achievement.getNumber() + "회!");
//                    continueCountSaved = true;
//                }
//            }
//        }
//
//        Achievement achievementOfNoErrors = achievementDAO.getAchievementByAKA("noerrors");
//        if (todayNoerrors > achievementOfNoErrors.getNumber()) {
//            achievementOfNoErrors.setNumber(todayNoerrors);
//            userAchievements.add(achievementOfNoErrors);
//            achievementDAO.updateAchievement(achievementOfNoErrors.getId(), 1, achievementOfNoErrors.getNumber(), currentRecords.getToday());
//            Log.v(LOG_TAG, "DB에 기록 성공! " + " 완벽주의자 " + todayNoerrors + "회!");
//        }
//
//        Achievement achievementOfTotal = achievementDAO.getAchievementByAKA("master");
//        if (todayTotal > achievementOfTotal.getNumber()) {
//            achievementOfTotal.setNumber(todayTotal);
//            userAchievements.add(achievementOfTotal);
//            achievementDAO.updateAchievement(achievementOfTotal.getId(), 1, achievementOfTotal.getNumber(), currentRecords.getToday());
//            Log.v(LOG_TAG, "DB에 기록 성공! " + " 수학도사 : " + todayTotal + "문제로 기록을 경신하다!");
//        }

        return null;
    }

    private String compareAllRecord(String operation, RecordMapOfTheDay recordMapOfToday, List<RecordMapOfTheDay> listRecordMapOfTheDay) {

        int todayNum = recordMapOfToday.getRecordsMap().get(operation);
        int total = 0;

        for (RecordMapOfTheDay rmot : listRecordMapOfTheDay) {
            if (!rmot.getDay().equals(recordMapOfToday.getDay())) {
                if (rmot.getRecordsMap().get(operation) > total) {
                    total = rmot.getRecordsMap().get(operation);
                }
            }
        }

        if (todayNum > total) {

            StringBuilder sb = new StringBuilder();
            switch (operation) {
                case "multiply32":
                    sb.append("세 자리 수 곱하기 두 자리수 문제 풀기 기록 경신 : ");
                    break;
                case "multiply22":
                    sb.append("두 자리 수 곱하기 세 자리수 문제 풀기 기록 경신 : ");
                    break;
                case "divide21":
                    sb.append("두 자리 수 나누기 한 자리수 문제 풀기 기록 경신 : ");
                    break;
                case "divide22":
                    sb.append("두 자리 수 나누기 두 자리수 문제 풀기 기록 경신 : ");
                    break;
                case "divide32":
                    sb.append("세 자리 수 나누기 두 자리수 문제 풀기 기록 경신 : ");
                    break;
                case "challenge":
                    sb.append("도전! 문제풀기 기록 경신 : ");
                    break;
                default:
                    break;
            }
            sb.append(todayNum);
            sb.append("회");

//            Log.v(LOG_TAG, "지금까지 최고기록 " + total + " 문제를 " + todayNum + " 문제로 경신하다!");
            return sb.toString();
        } else {
            return null;
        }


    }

    @Override
    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
        asyncDialog.dismiss();

        dialogResult = new DialogResult(context, clickListener, currentRecord, recordMapOfToday);
        dialogResult.setCanceledOnTouchOutside(false);
        dialogResult.show();

//        achievementDAO.close();
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

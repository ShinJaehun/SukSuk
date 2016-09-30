package com.shinjaehun.suksuk;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

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
    private RecordMap recordMapOfToday;

    private DialogResult dialogResult;

    private ProgressDialog asyncDialog;

//    private List<Achievement> userAchievements;
    private static List<String> resultMessages;

    //연속풀기 카운터와 스위치
//    private static int continueCount = 1;
//    private static boolean continueCountSaved = false;

    //continueCounter를 여기서 static으로 증가시키면
    //연속으로 몇 문제 풀어서 continueCounter가 늘어난 상황에서
    //메인화면으로 나갔다 오더라도, 즉, 중간에 다른 문제를 풀게되면
    //continueCounter가 다시 0으로 초기화되지 않는다!
    //따라서 CurrentRecords에 멤버 변수로 continueCounter를 넣어두고
    //MainActivity에서 초기화시켜야 한다.

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
//        Log.v("AsyncTask", "onPreExecute");
    }

    @Override
    protected Void doInBackground(Void... params) {
//        Log.v(LOG_TAG, "In ResultTask");
//        achievementDAO = new AchievementDAO(context);
        resultMessages = new ArrayList<>();

        //currentRecords에서 가장 마지막 record를 currentRecord로 저장하고 DB에 insert.
        currentRecord = currentRecords.getCurrentRecords().get(currentRecords.getCurrentRecords().size() - 1);
        recordDAO.insertRecord(currentRecord.getOperation(), currentRecord.getDay(), currentRecord.getElapsedTime(), currentRecord.hasMistake());

//        for (Record r : records) {
//            Log.v(LOG_TAG, "All Records in DB : " + r.getOperation() + " " + r.getDay() + " " + r.getElapsedTime() + " " + r.hasMistake());
//        }

        List<Record> records = recordDAO.getAllRecords();

        //RecordMap : 날짜에 따라 각 유형의 문제를 얼마나 풀었는지 저장하는 자료구조
        //recordMapList : records에 저장된 모든 자료를 RecordMap 형태로 저장한 ArrayList
        List<RecordMap> recordMapList = new ArrayList<>();

        if (records != null) {
            //날짜별로 RecordMap 자료를 저장하기 위해 먼저 기존에 저장되어 있는 records에서 중복되지 않게 날짜만 ArrayList로 가져온다.
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

            //records에서 날짜에 따라 유형별로 푼 문제 수를 RecordMap 형태로 저장해서
            for (String day : days) {
                RecordMap recordMap = new RecordMap();
                recordMap.setDay(day);

                for (Record r : records) {
                    if (r.getDay().equals(day)) {
                        recordMap.setRecordsMap(r);
                    }
                }

                //listRecordMap에 추가
                recordMapList.add(recordMap);

            }

//            for (RecordMap rmot : recordMapList) {
//                Log.v(LOG_TAG, "Day " + rmot.getDay());
//                for (String operation : rmot.getRecordsMap().keySet()) {
//                    Log.v(LOG_TAG, "recordsMap " + operation + " : " + rmot.getRecordsMap().get(operation));
//                }
//            }

        } else {
            //여길 실행해버리면 문제가 있지... currentRecord를 DB에 저장했으니 records에는 최소한 currentRecord가 들어 있어야 한다.
        }

//        for (RecordMap r : recordMapOfTheDays) {
//            Log.v(LOG_TAG, "The day : " + r.getDay());
//            for (String operation : r.getRecordsMap().keySet()) {
//                Log.v(LOG_TAG, "recordMap " + operation + " : " + r.getRecordsMap().get(operation));
//            }
//        }

//        for (String operation : recordsMap.keySet()) {
//            Log.v(LOG_TAG, "recordsMap " + operation + " : " + recordsMap.get(operation));
//        }

        //todayRecords : records에서 오늘 날짜에 해당하는 Record만 가져와서 저장
        List<Record> todayRecords = new ArrayList<>();
        if (records != null) {
            for (Record r : records) {
                if (r.getDay().equals(currentRecord.getDay())) {
                    todayRecords.add(r);
                }
            }
        }

//        for (Record r : todayRecords) {
//            Log.v(LOG_TAG, "Today's Records in DB : " + r.getOperation() + " " + r.getDay() + " " + r.getElapsedTime() + " " + r.hasMistake());
//        }

        //recordMapOfToday : 오늘 날짜에 해당하는 record들(todayRecords)의 RecordMap
        recordMapOfToday = new RecordMap();
        recordMapOfToday.setDay(currentRecord.getDay());
        for (Record r : todayRecords) {
            recordMapOfToday.setRecordsMap(r);
        }

//        for (String operation : recordMapOfToday.getRecordsMap().keySet()) {
//            Log.v(LOG_TAG, "Today's recordsMap " + operation + " : " + recordMapOfToday.getRecordsMap().get(operation));
//        }

        checkFirst(currentRecord.getOperation());
        checkPerfect();
        checkAllRound(currentRecord.getOperation());

        checkTotal(recordMapList);
        checkAllRecord(currentRecord.getOperation(), recordMapList);
        //스킬 챌린지를 제외하고 연속 문제 풀기 체크
        if (!currentRecord.getOperation().equals("challenge")) {
            checkContinue(currentRecord.getOperation());
        }
//        else {
//            checkChallenge();
//        }

//        if (resultMessages.size() != 0) {
//            for (String m : resultMessages) {
//                Log.v(LOG_TAG, "Message in ResultTask : " + m);
//            }
//        }

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

    private void checkFirst(String operation) {
        if (recordMapOfToday.getTotal() == 1) {
                resultMessages.add("오늘 첫 문제 풀기");
        }

        if (recordMapOfToday.getRecordsMap().get(operation) == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("오늘 처음으로 ");
            sb.append(operationToString(operation));
            sb.append(" 풀기");

            if (sb.toString() != null) {
                resultMessages.add(sb.toString());
            }
        }
    }

    private void checkPerfect() {
        if (currentRecord.hasMistake() == 0) {
            resultMessages.add("한 번도 틀리지 않기");
        }
    }

    private void checkAllRound(String operation) {
        boolean thisOp = false;
        boolean otherOps = true;

        //방금 푼 문제 수 첫 문제라면!
        if (recordMapOfToday.getRecordsMap().get(operation) == 1) {
            thisOp = true;
        }

        //방금 푼 문제를 제외하고 오늘 푼 문제들이 1보다 작을 경우, 즉 한번도 푼 적이 없다면
        //otherOps 스위치는 false가 된다.
        //따라서 방금 푼 문제가 첫 문제이고 다른 문제들을 한 번 이상 풀어본 경우에
        //otherOps 스위치는 true가 된다.
        for (String op : recordMapOfToday.getRecordsMap().keySet()) {
            if (!op.equals(operation) && (recordMapOfToday.getRecordsMap().get(op) < 1)) {
                otherOps = false;
            }
        }

        if (thisOp == true && otherOps == true) {
            resultMessages.add("하루에 모든 유형의 문제 풀기");
        }
    }

    private void checkTotal(List<RecordMap> recordMapList) {
        //recordMapOfToday : 오늘 날짜에 해당하는 record들의 RecordMap
        //recordMapList : records에 저장된 모든 자료를 RecordMap 형태로 저장한 ArrayList

        //오늘 푼 문제 수
        int todayTotal = recordMapOfToday.getTotal();
//        Log.v(LOG_TAG, "오늘 " + todayTotal + " 문제 풀었어요.");

        //recordMapList에서 가장 큰 total 값을 임시로 저장
        int total = 0;

        //maximum 구하기
        for (RecordMap rmot : recordMapList) {
//            Log.v(LOG_TAG, rmot.getDay() + " : " + rmot.getTotal() + " 문제");
            if (!rmot.getDay().equals(recordMapOfToday.getDay())) {
                if (rmot.getTotal() > total) {
                    total = rmot.getTotal();
                }
            }
        }

        //오늘 푼 문제 수가 지금까지 total 값 보다 크다면
        if (todayTotal > total) {
//            Log.v(LOG_TAG, "하루에 가장 많은 문제 풀기");
//            Log.v(LOG_TAG, "지금까지 최고기록 " + total + " 문제를 " + todayTotal + " 문제로 경신하다!");

            StringBuilder sb = new StringBuilder();
            sb.append("하루에 가장 많은 문제 풀기 기록 경신 : ");
            sb.append(todayTotal);
            sb.append("회");

//            Log.v(LOG_TAG, "Message in method : " + sb.toString());

            if (sb.toString() != null) {
                resultMessages.add(sb.toString());
            }
        }
    }

    private void checkAllRecord(String operation, List<RecordMap> recordMapList) {
        //recordMapOfToday : 오늘 날짜에 해당하는 record들의 RecordMap
        //recordMapList : records에 저장된 모든 자료를 RecordMap 형태로 저장한 ArrayList

        //오늘 해당 operation 문제를 푼 수
        int todayNum = recordMapOfToday.getRecordsMap().get(operation);

        //recordMapList에서 해당 operation의 가장 큰 값을 임시로 저장
        int total = 0;

        //maximum 구하기
        for (RecordMap rmot : recordMapList) {
            if (!rmot.getDay().equals(recordMapOfToday.getDay())) {
                if (rmot.getRecordsMap().get(operation) > total) {
                    total = rmot.getRecordsMap().get(operation);
                }
            }
        }

        //오늘 해당 operation 문제를 푼 수가 지금까지 값 보다 크다면
        if (todayNum > total) {

            StringBuilder sb = new StringBuilder();
            sb.append(operationToString(operation));
            sb.append(" 풀기 기록 경신 : ");
            sb.append(todayNum);
            sb.append("회");

//            Log.v(LOG_TAG, "Message in method : " + sb.toString());

            if (sb.toString() != null) {
                resultMessages.add(sb.toString());
            }

//            Log.v(LOG_TAG, "지금까지 최고기록 " + total + " 문제를 " + todayNum + " 문제로 경신하다!");
        }
    }

//    private void checkChallenge() {
//        if (currentRecord.getOperation().equals("challenge")) {
//            resultMessages.add("도전! 문제 풀기");
//        }
//    }

    private String operationToString(String operation) {
        switch (operation) {
            case "multiply32":
                return "세 자리 수 곱하기 두 자리수 문제";
            case "multiply22":
                return "두 자리 수 곱하기 두 자리수 문제";
            case "divide21":
                return "두 자리 수 나누기 한 자리수 문제";
            case "divide22":
                return "두 자리 수 나누기 두 자리수 문제";
            case "divide32":
                return "세 자리 수 나누기 두 자리수 문제";
            case "challenge":
                return "도전! 문제";
            default:
                break;
        }
        return null;
    }

    private void checkContinue(String operation) {
//        Log.v(LOG_TAG, "현재 continueCount는 " + currentRecords.getContinueCounter());

        if (currentRecords.getContinueCounter() == 0) {
            //continueCounter는 0이지만 실제 currentRecords에는 records가 최소 하나라도 저장되므로
            currentRecords.setContinueCounter(1);
//            Log.v(LOG_TAG, "바뀐 continueCount는 " + currentRecords.getContinueCounter());

        } else if (currentRecord.getOperation().equals(currentRecords.getCurrentRecords().get(currentRecords.getCurrentRecords().size() - 2).getOperation())) {
            //currentCounter가 1이상이면 현재 문제를 둘 이상 풀었다는 뜻이므로 이전에 푼 문제와 비교 가능하다

            //비교 후 currentCounter 증가
            currentRecords.setContinueCounter(currentRecords.getContinueCounter() + 1);

//            Log.v(LOG_TAG, "바뀐 continueCount는 " + currentRecords.getContinueCounter());

            //이렇게 currentCounter 값을 증가시키다 currentCounter가 3이되면...
            if (currentRecords.getContinueCounter() == 3) {
                StringBuilder sb = new StringBuilder();
                sb.append(operationToString(operation));
                sb.append(" 연속 3회 풀기");

                if (sb.toString() != null) {
                    resultMessages.add(sb.toString());
                }

                currentRecords.setContinueCounter(0);
//                Log.v(LOG_TAG, "바뀐 continueCount는 " + currentRecords.getContinueCounter());

            }
        }

//        if (currentRecords.getContinueCounter() == 3) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(operationToString(operation));
//            sb.append(" 연속 3회 풀기");
//
//            if (sb.toString() != null) {
//                resultMessages.add(sb.toString());
//            }
//
//            currentRecords.setContinueCounter(1);
//        } else if ((currentRecords.getCurrentRecords().size() - 2) != -1) {
//            //currentRecord 바로 전 기록이 null이 아니라면
//            if (currentRecord.getOperation().equals(currentRecords.getCurrentRecords().get(currentRecords.getCurrentRecords().size() - 2).getOperation())) {
//                //currentRecord와 바로 전 기록을 비교하여 같은 문제라면 연속풀기 카운터를 증가시킨
//                currentRecords.setContinueCounter(currentRecords.getContinueCounter() + 1);
//                Log.v(LOG_TAG, "바뀐 continueCount는 " + currentRecords.getContinueCounter());
//
//
//            }
////            Log.v(LOG_TAG, currentRecord.getOperation() + " 문제 " + continueCount + " 회 연속 풀기.");
//        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
        asyncDialog.dismiss();

        dialogResult = new DialogResult(context, clickListener, currentRecord, recordMapOfToday, resultMessages);
        dialogResult.setCanceledOnTouchOutside(false);
        //dialogResult 외부 화면은 터치해도 반응하지 않음
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

/*

    //빨리 문제를 푸는 게 대체 어떤 교육적 효과가 있을까...
    //한 문제라도 꼼꼼히 천천히 풀더라도 기다려주는게 교사가 해야하는 일 아니냐고... 이 븅신아. 니가 그러고도 선생이냐

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
*/

}

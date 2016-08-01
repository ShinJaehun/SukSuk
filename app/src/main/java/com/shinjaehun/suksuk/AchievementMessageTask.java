package com.shinjaehun.suksuk;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by shinjaehun on 2016-08-01.
 */
public class AchievementMessageTask extends AsyncTask<Void, Void, List<Achievement>> {


    private static final String LOG_TAG = AchievementMessageTask.class.getSimpleName();
    private final Context mContext;
    private final AchievementDAO mAchievementDAO;
    private final String operation;

    Long elapsedTime;
    boolean isMistake;
//    String resultMessage;

    ListAchievementAdapter mListAchievementAdapter;
    ProgressDialog asyncDialog;
    private TaskCompleted mTaskCompleted = null;

//    public AchievementMessageTask(Context context, String op, AchievementDAO aDAO, Long eTime, boolean miss, String message, TaskCompleted taskCompleted) {
    public AchievementMessageTask(Context context, String op, AchievementDAO aDAO, Long eTime, boolean miss, ListAchievementAdapter laa) {
        mContext = context;
        mAchievementDAO = aDAO;
        operation = op;
        elapsedTime = eTime;
        isMistake = miss;
//        resultMessage = message;
        asyncDialog = new ProgressDialog(mContext);
//        mTaskCompleted = taskCompleted;
        mListAchievementAdapter = laa;
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
    protected List<Achievement> doInBackground(Void... params) {

        List<Achievement> achievementsLists = new ArrayList<Achievement>();
        achievementsLists = mAchievementDAO.getAchivementsByType(operation);

        List<Achievement> userAchievements = new ArrayList<Achievement>();

//        StringBuilder sb = new StringBuilder();
//        계산하는데 걸린 시간 -> 최종적으로는 빼기
//        sb.append(getElapsedTime(elapsedTime) + "가 걸렸습니다!\n");

        for (Achievement achievement : achievementsLists) {
            Log.v(LOG_TAG, achievement.getName());
        }

        for (Achievement achievement : achievementsLists) {
            if ((achievement.getIsUnlock() == 0) && (achievement.getType().equals(operation) && achievement.getAka().equals("first"))) {
                Log.v(LOG_TAG, achievement.getName());
//                sb.append(achievement.getName() + "\n" + achievement.getDescription() + "\n\n");
                userAchievements.add(achievement);
                mAchievementDAO.updateAchievement(achievement.getId(), 1, achievement.getNumber(), achievement.getValue());
            }
            if (isMistake == false && achievement.getAka().equals("noerrors")) {
                Log.v(LOG_TAG, achievement.getName() + " " + achievement.getNumber());
                int ag = achievement.getNumber();
                int number = ag + 1;
//                sb.append(achievement.getName() + " " + number + "회 성공!\n" + achievement.getDescription() + "\n\n");
                userAchievements.add(achievement);
                mAchievementDAO.updateAchievement(achievement.getId(), 1, number, achievement.getValue());
            }
//            if (elapsedTime < Long.parseLong(a.getValue())) {
//
//            }
        }

//        resultMessage = sb.toString();

        return userAchievements;



//        return userAchievements;
    }

    @Override
    protected void onPostExecute(List<Achievement> userAchievements) {
        asyncDialog.dismiss();
        Log.v("AsyncTask", "onPostExecute");

        for (Achievement a : userAchievements) {
            Log.v(LOG_TAG, "UserAchievement : " + a.getName());
        }

        for (Achievement a : userAchievements) {
            mListAchievementAdapter.add(a);
        }

//        Log.v(LOG_TAG, message);

//        mTaskCompleted.onTaskCompleted(message);
        mAchievementDAO.close();
    }

    private static String getElapsedTime(long elapsedTime) {
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

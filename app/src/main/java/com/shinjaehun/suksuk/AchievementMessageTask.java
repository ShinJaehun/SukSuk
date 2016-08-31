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
import java.util.concurrent.TimeUnit;

/**
 * Created by shinjaehun on 2016-08-01.
 */
public class AchievementMessageTask extends AsyncTask<Void, Void, List<Achievement>> {

    private static final String LOG_TAG = AchievementMessageTask.class.getSimpleName();
    private final Context mContext;
    private final AchievementDAO mAchievementDAO;
    private final String operation;

    DialogResult dialogResult;

    Long elapsedTime;
    boolean isMistake;
//    String resultMessage;

    boolean isChallenge;

    ListAchievementAdapter mListAchievementAdapter;
    ProgressDialog asyncDialog;

//    private TaskCompleted mTaskCompleted = null;
//    public AchievementMessageTask(Context context, String op, AchievementDAO aDAO, Long eTime, boolean miss, String message, TaskCompleted taskCompleted) {
//  도전과제를 ListAdapter와 ListView로 표현하지 않고 AchievementTask 결과 생성한 String으로 표현하려고 한 적이 있다.
//  그때 해결 방법이 ProblemFragment에 TaskCompleted 인터페이스의 onTaskCompleted()를 구현해서 결과를 받아오는 방법이었다.
//    이제는 이럴 필요가 없어져서 TaskCompleted 인터페이스도 의미가 없다.

    public AchievementMessageTask(Context context, String op, AchievementDAO aDAO, Long eTime, boolean miss, boolean challenge, ListAchievementAdapter laa) {
        mContext = context;
        mAchievementDAO = aDAO;
        //DAO는 사실 ProblemActivity의 onCreate() 생성되고 ProblemActivity를 생성할 때 인자로 넘어간다.
        //그리고나서 AchievementTask 오브젝트를 생성하는 과정에서 다시 인자로 넘어오게 된다.
        operation = op;
        elapsedTime = eTime;
        isMistake = miss;
//        resultMessage = message;
        isChallenge = challenge;
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
        List<Achievement> userAchievements = new ArrayList<Achievement>();

        if (isChallenge == false) {
            //스킬챌린지가 아닌 경우

            List<Achievement> achievementsLists = mAchievementDAO.getAchivementsByType(operation);

//        StringBuilder sb = new StringBuilder();
//        계산하는데 걸린 시간 -> 최종적으로는 빼기
//        sb.append(getElapsedTime(elapsedTime) + "가 걸렸습니다!\n");

            for (Achievement achievement : achievementsLists) {
                Log.v(LOG_TAG, achievement.getName());
            }

            for (Achievement achievement : achievementsLists) {
                if ((achievement.getIsunlock() == 0) && (achievement.getType().equals(operation) && achievement.getAka().equals("first"))) {
                    //잠깐... 근데 DAO에 getAchievementByAKA를 구현해 놨는데... 이걸 이용하는 편이 낫지 않을까?

                    //처음으로 하는 계산 unlock하기
//                Log.v(LOG_TAG, achievement.getName());
//                sb.append(achievement.getName() + "\n" + achievement.getDescription() + "\n\n");
                    achievement.setNumber(achievement.getNumber() + 1);
                    Log.v(LOG_TAG, achievement.getName() + " " + achievement.getNumber());

                    userAchievements.add(achievement);
                    mAchievementDAO.updateAchievement(achievement.getId(), 1, achievement.getNumber(), System.currentTimeMillis());
                    //끝나고 나서 DB 업데이트
                }
                if (isMistake == false && achievement.getAka().equals("noerrors")) {
                    //완벽주의자 구현
                    achievement.setNumber(achievement.getNumber() + 1);
                    Log.v(LOG_TAG, achievement.getName() + " " + achievement.getNumber());

//                sb.append(achievement.getName() + " " + number + "회 성공!\n" + achievement.getDescription() + "\n\n");
                    userAchievements.add(achievement);
                    mAchievementDAO.updateAchievement(achievement.getId(), 1, achievement.getNumber(), System.currentTimeMillis());
                }
//            if (elapsedTime < Long.parseLong(a.getValue())) {
//
//            }
            }
        } else {
            //스킬챌린지라면...
            List<Achievement> achievementsLists = mAchievementDAO.getAchivementsByType("challenge");
            for (Achievement achievement : achievementsLists) {
                Log.v(LOG_TAG, achievement.getName());
            }

            for (Achievement achievement : achievementsLists) {
                if ((achievement.getIsunlock() == 0) && (achievement.getAka().equals("first"))) {
                    //도전과제 '문제풀기 시작'처리
                    achievement.setNumber(achievement.getNumber() + 1);
                    Log.v(LOG_TAG, achievement.getName() + " " + achievement.getNumber());

                    userAchievements.add(achievement);
                    mAchievementDAO.updateAchievement(achievement.getId(), 1, achievement.getNumber(), System.currentTimeMillis());
                }
                if (achievement.getAka().equals("master")) {
                    //도전과제 '문제풀기 도사' 처리
                    achievement.setNumber(achievement.getNumber() + 1);
                    Log.v(LOG_TAG, achievement.getName() + " " + achievement.getNumber());

                    userAchievements.add(achievement);
                    mAchievementDAO.updateAchievement(achievement.getId(), 1, achievement.getNumber(), System.currentTimeMillis());
                }
            }
        }

//        resultMessage = sb.toString();

        for (Achievement a : userAchievements) {
            Log.v(LOG_TAG, "UserAchievement in Progress : " + a.getName() + " " + a.getNumber());
        }

        return userAchievements;

//        return userAchievements;
    }

    @Override
    protected void onPostExecute(List<Achievement> userAchievements) {
        asyncDialog.dismiss();
        Log.v(LOG_TAG, "onPostExecute");

        for (Achievement a : userAchievements) {
            Log.v(LOG_TAG, "UserAchievement in Post : " + a.getName() + " " + a.getNumber());
        }

        for (Achievement a : userAchievements) {
            mListAchievementAdapter.add(a);
        }
        //FieldTrip 때 AsyncTask에서 onPostExecute() 구현한 것과 동일하다.
        //비동기 작업이 끝난 후 가져온 List<Achievement>를 adapter에 추가

        for (Achievement a : mListAchievementAdapter.getItems()) {
            Log.v(LOG_TAG, "UserAchievement in Post in Adapter: " + a.getName() + " " + a.getNumber());
        }

//        Log.v(LOG_TAG, message);

//        mTaskCompleted.onTaskCompleted(message);
        mAchievementDAO.close();

        dialogResult = new DialogResult(mContext, mListAchievementAdapter, clickListener);
        dialogResult.setCanceledOnTouchOutside(false);
        //Dialog 외부를 터치하게 되면 발생할 수 있는 오류를 미연에 방지한다.
        dialogResult.show();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        //dialog의 clickListener를 여기서 처리한다.
        @Override
        public void onClick(View v) {
            Log.v(LOG_TAG, "is Challenge in AsyncTask : " + isChallenge);

            //dialog 확인 버튼을 클릭하면 액티비티 재시작!
            Intent intent = ((Activity)mContext).getIntent();
            dialogResult.dismiss();
            //dialog를 dismiss()하지 않으면 android view windowleaked 오류가 발생한다.
            ((Activity)mContext).finish();
            mContext.startActivity(intent);
        }
    };

    //ProblemFragment에서 실행되던 때의 원래 코드
    //    private View.OnClickListener clickListener = new View.OnClickListener() {
//        //dialog의 clickListener를 여기서 처리한다.
//        @Override
//        public void onClick(View v) {
//            //dialog 확인 버튼을 클릭하면 액티비티 재시작!
//            Intent intent = getActivity().getIntent();
//            dialogResult.dismiss();
//            //dialog를 dismiss()하지 않으면 android view windowleaked 오류가 발생한다.
//            getActivity().finish();
//            startActivity(intent);
//        }
//    };


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

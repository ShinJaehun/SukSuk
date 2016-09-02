package com.shinjaehun.suksuk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shinjaehun on 2016-07-30.
 */

/*

public class AchievementDAO implements Serializable {
    private static final String LOG_TAG = AchievementDAO.class.getSimpleName();

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns= { DBHelper.COLUMN_ACHIEVEMENT_ID, DBHelper.COLUMN_ACHIEVEMENT_NAME, DBHelper.COLUMN_ACHIEVEMENT_TYPE,
            DBHelper.COLUMN_ACHIEVEMENT_IS_UNLOCK, DBHelper.COLUMN_ACHIEVEMENT_AKA, DBHelper.COLUMN_ACHIEVEMENT_DESCRIPTION,
            DBHelper.COLUMN_ACHIEVEMENT_NUMBER, DBHelper.COLUMN_ACHIEVEMENT_DAY  };

    public AchievementDAO(Context context) {
        dbHelper = DBHelper.getInstance(context);
        database = dbHelper.getDb();
        //생성된 DBHelper를 이용해서 instance를 받아오고 DB를 받아온다.
    }

    public void close() {
        dbHelper.close();
    }

    public void updateAchievement(long id, int isUnlock, int number, String day) {
        //Achievement 값 수정하기
        ContentValues newValues = new ContentValues();
        newValues.put(DBHelper.COLUMN_ACHIEVEMENT_IS_UNLOCK, isUnlock);
        newValues.put(DBHelper.COLUMN_ACHIEVEMENT_NUMBER, number);
        newValues.put(DBHelper.COLUMN_ACHIEVEMENT_DAY, day);

        database.update(DBHelper.TABLE_ACHIEVEMENTS, newValues, DBHelper.COLUMN_ACHIEVEMENT_ID + " = " + id, null);
    }

    public List<Achievement> getAllAchievements() {
        //Achievement 레코드 모두 받아오기 : 나중에 도전 과제 화면 하나를 Activity로 줘서 어떤 도전 과제가 있는지
        //확인 가능하도록 하는 용도
        List<Achievement> listAchievements = new ArrayList<Achievement>();
        Cursor cursor = database.query(DBHelper.TABLE_ACHIEVEMENTS, allColumns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                Achievement achievement = cursorToAchievement(cursor);
                listAchievements.add(achievement);
                cursor.moveToNext();
            }
            cursor.close();
            Log.v(LOG_TAG, "DB and DBHelper has been closed successfully.");
        }
        return listAchievements;
    }

    public List<Achievement> getAchievementsByType(String type) {
        List<Achievement> listAchievements = new ArrayList<Achievement>();
        Cursor cursor = database.query(DBHelper.TABLE_ACHIEVEMENTS, allColumns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Achievement achievement = cursorToAchievement(cursor);
                if (achievement.getType().equals(type) || achievement.getType().equals("common")) {
                    //type이 common 항목도 함께 저장한다.
                    listAchievements.add(achievement);
                }
                cursor.moveToNext();
            }
            cursor.close();
            Log.v(LOG_TAG, "DB and DBHelper has been closed successfully.");
        }
        return listAchievements;
    }

    public Achievement getAchievementByAKA(String aka) {
        //별칭을 통해 Achievement 받아오기
        Cursor cursor = database.query(DBHelper.TABLE_ACHIEVEMENTS, allColumns, DBHelper.COLUMN_ACHIEVEMENT_AKA + " =?",
                new String[] { aka }, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        Achievement achievement = cursorToAchievement(cursor);
        cursor.close();
        Log.v(LOG_TAG, "DB and DBHelper has been closed successfully.");
        return achievement;
    }

    public Achievement getAchievementById(long id) {
        //ID를 통해 Achievement 받아오기
        Cursor cursor = database.query(DBHelper.TABLE_ACHIEVEMENTS, allColumns, DBHelper.COLUMN_ACHIEVEMENT_ID + " =?",
                new String[]{ String.valueOf(id) }, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        Achievement achievement = cursorToAchievement(cursor);
        cursor.close();
        Log.v(LOG_TAG, "DB and DBHelper has been closed successfully.");
        return achievement;
    }

    private Achievement cursorToAchievement(Cursor cursor) {
        //제발 DB에 COLUMN 하나 추가하면 여기에도 정보 업데이트 하는 거 좀 잊지 마라!!!

        Achievement achievement = new Achievement();
        achievement.setId(cursor.getLong(0));
        achievement.setName(cursor.getString(1));
        achievement.setType(cursor.getString(2));
        achievement.setIsunlock(cursor.getInt(3));
        achievement.setAka(cursor.getString(4));
        achievement.setDescription(cursor.getString(5));
        achievement.setNumber(cursor.getInt(6));
        achievement.setDay(cursor.getString(7));
        return achievement;
    }
}

*/

package com.shinjaehun.suksuk;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by shinjaehun on 2016-07-30.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = DBHelper.class.getSimpleName();

    public static final String TABLE_ACHIEVEMENTS = "achievements";
    public static final String COLUMN_ACHIEVEMENT_ID = "_id";
    public static final String COLUMN_ACHIEVEMENT_NAME = "name";
    public static final String COLUMN_ACHIEVEMENT_TYPE = "type";
    public static final String COLUMN_ACHIEVEMENT_IS_UNLOCK = "is_unlock";
    public static final String COLUMN_ACHIEVEMENT_AKA = "aka";
    public static final String COLUMN_ACHIEVEMENT_DESCRIPTION = "description";
    public static final String COLUMN_ACHIEVEMENT_NUMBER = "number";
    public static final String COLUMN_ACHIEVEMENT_VALUE = "value";

    private static final String DATABASE_NAME = "achievements.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_ACHIEVEMENTS = "CREATE TABLE " + TABLE_ACHIEVEMENTS + "(" +
            COLUMN_ACHIEVEMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_ACHIEVEMENT_NAME + " TEXT NOT NULL, " +
            COLUMN_ACHIEVEMENT_TYPE + " TEXT NOT NULL, " +
            COLUMN_ACHIEVEMENT_IS_UNLOCK + " INTEGER DEFAULT 0, " +
            COLUMN_ACHIEVEMENT_AKA + " TEXT NOT NULL, " +
            COLUMN_ACHIEVEMENT_DESCRIPTION + " TEXT NOT NULL, " +
            COLUMN_ACHIEVEMENT_NUMBER + " INTEGER DEFAULT 0, " +
            COLUMN_ACHIEVEMENT_VALUE + " TEXT DEFAULT NULL " +
            ");";
    //빌어먹을 테이블 생성할 때 나중에 갱신할 String 값을 TEXT NOT NULL로 해 놓고
    //raw 값을 insert하면 오류 로그도 없이 테이블 생성이 안된다.
    //알아내는데 졸라 빡셌어

    private static DBHelper instance;
    private static SQLiteDatabase db;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        Log.i(TAG, "Create or Open database from Constructor : " + DATABASE_NAME);
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static void initialize(Context context) {
        if (instance == null) {
            //DBHelper instance가 없을 때는 새로 DBHelper instance를 생성한다.
            instance = new DBHelper(context);
            try {
                Log.i(TAG, "Create or Open database from initialize method : " + DATABASE_NAME);
                //DBHelper로부터 쓰기 가능한 DB를 받아온다
                db = instance.getWritableDatabase();
            } catch (SQLException se) {
                Log.e(TAG, "Couldn't create or open the database : " + DATABASE_NAME);
            }
            Log.i(TAG, "Instance of database " + DATABASE_NAME + " created!");
        }
    }

    public static final DBHelper getInstance(Context context) {
        initialize(context);
        return instance;
    }

    public static final SQLiteDatabase getDb() {
        return db;
    }

    public void close() {
        if (instance != null) {
            Log.i(TAG, "Closing the database " + DATABASE_NAME);
            db.close();
            instance = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_ACHIEVEMENTS);
        //테이블 생성

        db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENTS + " VALUES ('1', '완벽주의자', 'common', 0, 'noerrors', '한 번도 실수하지 않았습니다!', 0, null);");
        db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENTS + " VALUES ('2', '곱하기 도전', 'multiply22', 0, 'first', '처음으로 두 자리 수 곱하기 두 자리수 문제를 풀었습니다.', 0, null);");
        db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENTS + " VALUES ('3', '스피드레이서', 'multiply22', 0, 'fastest', '가장 빠른 두 자리 수 곱하기 두 자리 수 계산 기록을 경신했습니다!', 0, null);");
        //raw 값 insert
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACHIEVEMENTS);
        onCreate(db);
    }
}

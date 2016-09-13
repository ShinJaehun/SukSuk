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

    private static final String DATABASE_NAME = "suksuk.db";
    private static final int DATABASE_VERSION = 1;

//    public static final String TABLE_ACHIEVEMENTS = "achievements";
//    public static final String COLUMN_ACHIEVEMENT_ID = "_id";
//    public static final String COLUMN_ACHIEVEMENT_NAME = "name";
//    public static final String COLUMN_ACHIEVEMENT_TYPE = "type";
//    public static final String COLUMN_ACHIEVEMENT_LOCK = "lock";
//    public static final String COLUMN_ACHIEVEMENT_AKA = "aka";
//    public static final String COLUMN_ACHIEVEMENT_DESCRIPTION = "description";
//    public static final String COLUMN_ACHIEVEMENT_NUMBER = "number";
//    public static final String COLUMN_ACHIEVEMENT_DAY= "day";
//
//    private static final String SQL_CREATE_TABLE_ACHIEVEMENTS = "CREATE TABLE " + TABLE_ACHIEVEMENTS + "(" +
//            COLUMN_ACHIEVEMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            COLUMN_ACHIEVEMENT_NAME + " TEXT NOT NULL, " +
//            COLUMN_ACHIEVEMENT_TYPE + " TEXT NOT NULL, " +
//            COLUMN_ACHIEVEMENT_LOCK + " INTEGER DEFAULT 0, " +
//            COLUMN_ACHIEVEMENT_AKA + " TEXT NOT NULL, " +
//            COLUMN_ACHIEVEMENT_DESCRIPTION + " TEXT NOT NULL, " +
//            COLUMN_ACHIEVEMENT_NUMBER + " INTEGER DEFAULT 0, " +
//            COLUMN_ACHIEVEMENT_DAY + " TEXT " +
//            ");";
    //빌어먹을 테이블 생성할 때 나중에 갱신할 String 값을 TEXT NOT NULL로 해 놓고
    //raw 값을 insert하면 오류 로그도 없이 테이블 생성이 안된다.
    //알아내는데 졸라 빡셌어

    public static final String TABLE_RECORDS = "records";
    public static final String COLUMN_RECORD_ID = "_id";
    public static final String COLUMN_RECORD_OPERATION = "operation";
    public static final String COLUMN_RECORD_DAY = "day";
    public static final String COLUMN_RECORD_ELAPSED_TIME = "elapsed_time";
    public static final String COLUMN_RECORD_MISTAKE = "mistake";

//    private static final String DATABASE_NAME = "records.db";
//    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_RECORDS = "CREATE TABLE " + TABLE_RECORDS + "(" +
            COLUMN_RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_RECORD_OPERATION + " TEXT NOT NULL, " +
            COLUMN_RECORD_DAY + " TEXT NOT NULL, " +
            COLUMN_RECORD_ELAPSED_TIME + " INTEGER DEFAULT 0, " +
            COLUMN_RECORD_MISTAKE + " INTEGER DEFAULT 0 " +
            ");";


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
//                Log.i(TAG, "Create or Open database from initialize method : " + DATABASE_NAME);
                //DBHelper로부터 쓰기 가능한 DB를 받아온다
                db = instance.getWritableDatabase();
            } catch (SQLException se) {
                Log.e(TAG, "Couldn't create or open the database : " + DATABASE_NAME);
            }
//            Log.i(TAG, "Instance of database " + DATABASE_NAME + " created!");
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
//            Log.i(TAG, "Closing the database " + DATABASE_NAME);
            db.close();
            instance = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_RECORDS);
//        db.execSQL(SQL_CREATE_TABLE_ACHIEVEMENTS);
        //테이블 생성

//        db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENTS + " VALUES ('1', '완벽주의자', 'common', 0, 'noerrors', '한 번도 실수하지 않았습니다', 0, null);");
//        db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENTS + " VALUES ('2', '수학도사 ', 'common', 0, 'master', '지금까지 가장 많은 문제를 풀었습니다', 0, null);");
//        db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENTS + " VALUES ('3', '수학해결사 ', 'common', 0, 'allround', '모든 유형의 문제를 풀었습니다', 0, null);");
//        db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENTS + " VALUES ('4', '문제풀기 도전자', 'challenge', 0, 'first', '처음으로 문제풀기에 도전했습니다', 0, null);");
//        db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENTS + " VALUES ('5', '문제풀기 고수', 'challenge', 0, 'master', '도전! 문제풀기 기록을 깼습니다', 0, null);");
//        db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENTS + " VALUES ('6', '곱하기 도전자', 'multiply22', 0, 'first', '처음으로 두 자리 수 곱하기 두 자리수에 도전했습니다', 0, null);");
//        db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENTS + " VALUES ('7', '곱하기 숙련자', 'multiply22', 0, 'expert', '두 자리 수 곱하기 두 자리수 문제 연속 풀기', 0, null);");
//        db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENTS + " VALUES ('8', '곱하기 고수', 'multiply22', 0, 'master', '두 자리 수 곱하기 두 자리 수 기록을 깼습니다', 0, null);");
//        db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENTS + " VALUES ('9', '나누기 도전자', 'divide22', 0, 'first', '처음으로 두 자리 수 나누기 두 자리수에 도전했습니다', 0, null);");
//        db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENTS + " VALUES ('10', '나누기 숙련자', 'divide22', 0, 'expert', '두 자리 수 나누기 두 자리수 문제 연속 풀기', 0, null);");
//        db.execSQL("INSERT INTO " + TABLE_ACHIEVEMENTS + " VALUES ('11', '나누기 고수', 'divide22', 0, 'master', '두 자리 수 나누기 두 자리 수 기록을 깼습니다', 0, null);");
        //raw 값 insert
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
        onCreate(db);
    }
}

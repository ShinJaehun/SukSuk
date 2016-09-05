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
 * Created by shinjaehun on 2016-09-03.
 */
public class RecordDAO implements Serializable {
    private static final String LOG_TAG = RecordDAO.class.getSimpleName();

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.COLUMN_RECORD_ID, DBHelper.COLUMN_RECORD_OPERATION,
            DBHelper.COLUMN_RECORD_DAY, DBHelper.COLUMN_RECORD_ELAPSED_TIME,
            DBHelper.COLUMN_RECORD_MISTAKE };

    public RecordDAO(Context context) {
        dbHelper = DBHelper.getInstance(context);
        database = dbHelper.getDb();
        //생성된 DBHelper를 이용해서 instance를 받아오고 DB를 받아온다.
    }

    public void close() { dbHelper.close(); }

    public void insertRecord(String operation, String day, long elapsedTime, int mistake) {
        //DB에 record 저장하기

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_RECORD_OPERATION, operation);
        values.put(DBHelper.COLUMN_RECORD_DAY, day);
        values.put(DBHelper.COLUMN_RECORD_ELAPSED_TIME, elapsedTime);
        values.put(DBHelper.COLUMN_RECORD_MISTAKE, mistake);

        database.insert(DBHelper.TABLE_RECORDS, null, values);
    }

    public List<Record> getAllRecords() {
        //DB에서 모든 record 받아오기

        List<Record> records = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.TABLE_RECORDS, allColumns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                Record record = cursorToRecord(cursor);
                records.add(record);
                cursor.moveToNext();
            }
            cursor.close();
            Log.v(LOG_TAG, "DB and DBHelper has been closed successfully.");
        }
        return records;
    }

    public List<Record> getRecordsByDay(String day) {
        //day에 해당하는 record 받아오기
        List<Record> records = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.TABLE_RECORDS, allColumns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                Record r = cursorToRecord(cursor);
                if (r.getDay().equals(day)) {
                    records.add(r);
                }
                cursor.moveToNext(); //이게 if 절 안에 들어가 있으면 안돼! 걍 다 넘어가불어;;;
            }
            cursor.close();
            Log.v(LOG_TAG, "DB and DBHelper has been closed successfully.");
        }
        return records;
    }


    private Record cursorToRecord(Cursor cursor) {
        Record record = new Record(cursor.getString(2));
        record.setId(cursor.getLong(0));
        record.setOperation(cursor.getString(1));
        record.setElapsedTime(cursor.getLong(3));
        record.setMistake(cursor.getInt(4));
        return record;
    }
}

package com.shinjaehun.suksuk;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by shinjaehun on 2016-09-01.
 */
public class CurrentRecords implements Serializable {
    private static String today;
    private static List<Record> records;

    private int continueCounter;

    private static final CurrentRecords currentRecords = new CurrentRecords();
    public static CurrentRecords getInstance() {
        //CurrentRecords.getInstance()를 실행하면서 timestamp를 찍고
        //String 형태로 변환해서 today로 저장한다
        DateFormat sdf = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.KOREAN);
        Date resultDate = new Date(System.currentTimeMillis());
        today = sdf.format(resultDate);

        //records 초기화
        records = new ArrayList<>();

        return currentRecords;
    }

    public String getToday() {
        return today;
    }

    public List<Record> getCurrentRecords() {
        return records;
    }

    public void addTodayRecords(Record record) {
        records.add(record);
    }

    public int getContinueCounter() {
        return continueCounter;
    }

    public void setContinueCounter(int continueCounter) {
        this.continueCounter = continueCounter;
    }
}

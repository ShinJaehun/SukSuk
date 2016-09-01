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
public class TodayRecords implements Serializable {
    private static String today;
    private static List<Record> records;

    private static final TodayRecords todayRecords = new TodayRecords();
    public static TodayRecords getInstance() {
        DateFormat sdf = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.KOREAN);
        Date resultDate = new Date(System.currentTimeMillis());
        today = sdf.format(resultDate);
        records = new ArrayList<>();
        return todayRecords;
    }

    public String getToday() {
        return today;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void addRecords(Record record) {
        records.add(record);
    }
}

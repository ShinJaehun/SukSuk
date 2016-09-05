package com.shinjaehun.suksuk;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by shinjaehun on 2016-09-01.
 */
public class RecordMapOfTheDay {
    private String day;
    private int total;
    final Map<String, Integer> recordsMap = new HashMap<String, Integer>() {
        {
            put("multiply32", 0);
            put("multiply22", 0);
            put("divide21", 0);
            put("divide22", 0);
            put("divide32", 0);
            put("challenge", 0);
        }
    };

    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }

    public void setRecordsMap(Record record) {
        total++;
        recordsMap.put(record.getOperation(), recordsMap.get(record.getOperation()) + 1);
    }

    public Map<String, Integer> getRecordsMap() {
        return recordsMap;
    }

    public int getTotal() {
        return total;
    }

}

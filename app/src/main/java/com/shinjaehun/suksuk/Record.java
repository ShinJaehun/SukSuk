package com.shinjaehun.suksuk;

import java.io.Serializable;

/**
 * Created by shinjaehun on 2016-08-31.
 */
public class Record {
    private String operation;
//    private long timeStamp;
//    private long total;
    private String today;
    private long elapsedTime;
    private boolean mistake;

//    private static final Record record = new Record();
//    public static Record getInstance() {
//        return record;
//    }
    public Record(String today) {
         this.today = today;
    }

    public String getToday() { return today; }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public boolean isMistake() {
        return mistake;
    }

    public void setMistake(boolean mistake) {
        this.mistake = mistake;
    }
//
//    private long divide21;
//    private long divide22;
//    private long divide32;
//    private long multiply22;
//    private long multiply32;
//    private long challenge;
//    private long noerrors;
//
////    private Map<String, Integer> todayRecords = new HashMap<String, Integer>() {
////        {
////            put("divide21", 0);
////            put("divide22", 0);
////            put("divide32", 0);
////            put("multiply32", 0);
////            put("multiply22", 0);
////            put("challenge", 0);
////            put("noerrors", 0);
////        }
////    };
//
//    public Record(long today) {
//        this.today = today;
//    }
//
//    public long getToday() {
//        return today;
//    }
//
////    public void setToday(long today) {
////        this.today = today;
////    }
//
//    public long getTotal() {
//        return total;
//    }
//
//    public void setTotal(long total) {
//        this.total = total;
//    }
//
////    public Map<String, Integer> getTodayRecords() {
////        return todayRecords;
////    }
////
////    public void setTodayRecords(Map<String, Integer> todayRecords) {
////        this.todayRecords = todayRecords;
////    }
//
//
//    public long getDivide21() {
//        return divide21;
//    }
//
//    public void setDivide21(long divide21) {
//        this.divide21 = divide21;
//    }
//
//    public long getDivide22() {
//        return divide22;
//    }
//
//    public void setDivide22(long divide22) {
//        this.divide22 = divide22;
//    }
//
//    public long getDivide32() {
//        return divide32;
//    }
//
//    public void setDivide32(long divide32) {
//        this.divide32 = divide32;
//    }
//
//    public long getMultiply22() {
//        return multiply22;
//    }
//
//    public void setMultiply22(long multiply22) {
//        this.multiply22 = multiply22;
//    }
//
//    public long getMultiply32() {
//        return multiply32;
//    }
//
//    public void setMultiply32(long multiply32) {
//        this.multiply32 = multiply32;
//    }
//
//    public long getChallenge() {
//        return challenge;
//    }
//
//    public void setChallenge(long challenge) {
//        this.challenge = challenge;
//    }
//
//    public long getNoerrors() {
//        return noerrors;
//    }
//
//    public void setNoerrors(long noerrors) {
//        this.noerrors = noerrors;
//    }
}

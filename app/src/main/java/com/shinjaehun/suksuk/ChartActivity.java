package com.shinjaehun.suksuk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.AxisValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shinjaehun on 2016-09-09.
 */
public class ChartActivity extends AppCompatActivity {
    private static final String LOG_TAG = ChartActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        LineChart chart = (LineChart)findViewById(R.id.chart);

        RecordDAO recordDAO = new RecordDAO(this);

        List<Record> records = recordDAO.getAllRecords();
        //RecordMap : 날짜에 따라 각 유형의 문제를 얼마나 풀었는지 저장하는 자료구조
        //recordMapList : records에 저장된 모든 자료를 RecordMap 형태로 저장한 ArrayList
        List<RecordMap> recordMapList = new ArrayList<>();

        if (records != null) {
            //날짜별로 RecordMap 자료를 저장하기 위해 먼저 기존에 저장되어 있는 records에서 중복되지 않게 날짜만 ArrayList로 가져온다.
            List<String> days = new ArrayList<>();
            for (Record r : records) {
                if (days != null) {
                    if (!days.contains(r.getDay())) {
                        days.add(r.getDay());
                    }
                } else {
                    days.add(r.getDay());
                }
            }

//            for (String d : days) {
//                Log.v(LOG_TAG, "Day : " + d);
//            }

            //records에서 날짜에 따라 유형별로 푼 문제 수를 RecordMap 형태로 저장해서
            for (String day : days) {
                RecordMap recordMap = new RecordMap();
                recordMap.setDay(day);

                for (Record r : records) {
                    if (r.getDay().equals(day)) {
                        recordMap.setRecordsMap(r);
                    }
                }

                //listRecordMap에 추가
                recordMapList.add(recordMap);

            }

            for (RecordMap rmot : recordMapList) {
                Log.v(LOG_TAG, "Day " + rmot.getDay());
                for (String operation : rmot.getRecordsMap().keySet()) {
                    Log.v(LOG_TAG, "recordsMap " + operation + " : " + rmot.getRecordsMap().get(operation));
                }
            }

        } else {
            //여길 실행해버리면 문제가 있지... currentRecord를 DB에 저장했으니 records에는 최소한 currentRecord가 들어 있어야 한다.
        }

        List<Entry> entries = new ArrayList<>();
        final ArrayList<String> labels = new ArrayList<>();

        for (int i = 0 ; i < recordMapList.size(); i++) {
            Entry e = new Entry((float)i, (float)(recordMapList.get(i).getTotal()));

            Log.v(LOG_TAG, "Data " + i + " " + (float)i + " " + (float)(recordMapList.get(i).getTotal()));
            entries.add(e);
            labels.add(recordMapList.get(i).getDay());
        }

        LineDataSet dataset  = new LineDataSet(entries, "total");

//        IAxisValueFormatter formatter = new IAxisValueFormatter() {
//
//            final String[] quarters = new String[] { "Q1", "Q2", "Q3", "Q4" };
//
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
////                return labels.get((int)value);
//                return quarters[(int) value];
//
//            }
//
//            @Override
//            public int getDecimalDigits() {
//                return 0;
//            }
//        };

        String[] xxvalues = labels.toArray(new String[0]);

        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(new MyXAxisValueFormatter(xxvalues));

        LineData lineData = new LineData(dataset);
        chart.setData(lineData);
        chart.invalidate();

//        for (RecordMap rm : recordMapList) {
//            Log.v(LOG_TAG, "Data : " + Float.valueOf(rm.getDay()) + Float.valueOf(rm.getTotal()));
////            entries.add(new Entry(Float.valueOf(rm.getDay()), Float.valueOf(rm.getTotal())));
//        }
    }
//
    public class MyXAxisValueFormatter implements IAxisValueFormatter, AxisValueFormatter {
    // AxisValueFormatter 때문에 피봤다...
    // 공식 위키 문서에도 이런 내용은 나오지도 않았음!!!!

        private String[] mValues;

        public MyXAxisValueFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            // "value" represents the position of the label on the axis (x or y)
            return mValues[(int) value];
        }

        /** this is only needed if numbers are returned, else return 0 */
        @Override
        public int getDecimalDigits() { return 0; }
    }

//        private [] mValues;
//
//        public MyYAxisValueFormatter(float[] values) {
//            this.mValues = values;
//        }



}

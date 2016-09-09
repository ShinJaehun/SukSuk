package com.shinjaehun.suksuk;

import android.graphics.Color;
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
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

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

        LineChart chart = (LineChart) findViewById(R.id.chart);

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

/*        List<Entry> entries_total = new ArrayList<>();
        final ArrayList<String> labels_total = new ArrayList<>();

        for (int i = 0; i < recordMapList.size(); i++) {
            Entry e = new Entry((float) (recordMapList.get(i).getTotal()), i);

            Log.v(LOG_TAG, "Data " + i + " " + (float) i + " " + (float) (recordMapList.get(i).getTotal()));
            entries_total.add(e);
            labels_total.add(recordMapList.get(i).getDay());
        }

//        YAxis yAxis = chart.getAxisLeft();
//        yAxis.setValueFormatter(new ValueFormatter());

        LineDataSet set_total = new LineDataSet(entries_total, "total");
        set_total.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<ILineDataSet> dataSets_total = new ArrayList<>();

        dataSets_total.add(set_total);

        LineData data = new LineData(labels_total, dataSets_total);
        data.setValueFormatter(new MyValueFormatter());*/

        List<Entry> entries_total = new ArrayList<>();
        final ArrayList<String> labels_total = new ArrayList<>();

        for (int i = 0; i < recordMapList.size(); i++) {
            Entry e = new Entry((float)i, (float)(recordMapList.get(i).getTotal()));

            Log.v(LOG_TAG, "Data " + i + " " + (float) i + " " + (float) (recordMapList.get(i).getTotal()));
            entries_total.add(e);
            labels_total.add(recordMapList.get(i).getDay());
        }

//        YAxis yAxis = chart.getAxisLeft();
//        yAxis.setValueFormatter(new ValueFormatter());

        LineDataSet set_total = new LineDataSet(entries_total, "total");
        set_total.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<ILineDataSet> dataSets_total = new ArrayList<>();
        dataSets_total.add(set_total);

        LineData data = new LineData(dataSets_total);
        data.setValueFormatter(new MyValueFormatter());

//        String[] xValues = labels_total.toArray(new String[0]);

//        for (int i = 0; i < xValues.length; i++) {
//            Log.v(LOG_TAG, "xValues [" + i + "] : " + xValues[i]);
//        }

        XAxis xAxis = chart.getXAxis();

        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);

        xAxis.setDrawGridLines(false);

        xAxis.setValueFormatter(new MyXAixsValueFormatter(labels_total));

        chart.setData(data);
        chart.invalidate();

    }

    public class MyXAixsValueFormatter implements AxisValueFormatter {

//        private String[] mValues;

        private ArrayList<String> labels;
        public MyXAixsValueFormatter(ArrayList<String> labels) {
            this.labels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            Log.v(LOG_TAG, "labels value : " + value);

            if (labels.size() == 1) {
                //만일 labels에 값이 하나 뿐일 때, 즉 하루만 입력했을 때
                //진짜 기가 막히는게 XAxis value로 -1, 0, 1이 표시된다.
                //이게 대체 왜 -1이 나오는지 몰라서 혼자 알아내느라 뒤지는 줄 알았다.
                //매뉴얼, 튜토리얼, 심지어 stackoverflow에도 없음.ㅠㅠ
                if ((int)value != 0) {
                    return "";
                } else {
//                    return String.valueOf(value);
                    return labels.get((int)value);
                }
            }

//            return String.valueOf(value);
            return labels.get((int)value);
        }

        @Override
        public int getDecimalDigits() {
            return 0;
        }
    }


    public class MyValueFormatter implements ValueFormatter {

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            Log.v(LOG_TAG, "value : " + String.valueOf((int)value));
            return String.valueOf((int)value);
        }
    }


//    public class ValueFormatter implements YAxisValueFormatter {
    //이건 Y 축에 나오는 값을 수정하는 formatter
//
//        @Override
//        public String getFormattedValue(float value, YAxis yAxis) {
//            Log.v(LOG_TAG, "value : " + String.valueOf((int)value));
//            return String.valueOf((int)value);
//        }
//    }

//    public class MyYAxisValueFormatter implements IValueFormatter {
////    public class MyXAxisValueFormatter implements IAxisValueFormatter, AxisValueFormatter {
//
//        // AxisValueFormatter 때문에 피봤다...
//        // 공식 위키 문서에도 이런 내용은 나오지도 않았음!!!!
//
//        @Override
//        public String getFormattedValue(float value, AxisBase axis) {
//            // "value" represents the position of the label on the axis (x or y)
//            return String.valueOf((int)value);
//        }
//    }

//        private [] mValues;
//
//        public MyYAxisValueFormatter(float[] values) {
//            this.mValues = values;
//        }



}

package com.shinjaehun.suksuk;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shinjaehun on 2016-09-12.
 */
public class DrawingChartTask extends AsyncTask<Void, Void, List<ILineDataSet>> {

    private static final String LOG_TAG = DrawingChartTask.class.getSimpleName();
    private final Context context;
    private final RecordDAO recordDAO;
    private LineChart chart;

    private ProgressDialog asyncDialog;

    private ArrayList<String> labels_day;

    public DrawingChartTask(Context context, RecordDAO recordDAO, LineChart chart) {
        this.context = context;
        this.recordDAO = recordDAO;
        this.chart = chart;
        asyncDialog = new ProgressDialog(this.context, R.style.AppTheme);
    }

    @Override
    protected void onPreExecute() {
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.setMessage("차트를 준비합니다...");
        asyncDialog.show();
        super.onPreExecute();
        Log.v("AsyncTask", "onPreExecute");
    }


    @Override
    protected List<ILineDataSet> doInBackground(Void... params) {

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
        // records를 recordMapList로 변환하는 과정만 doInBackground()에 넣었었는데 'The application may be doing too much work on its main thread' 경고 메시지가 나왔다.
        // linechart에 사용할 entry 리스트와 dataSets를 생성하는 과정까지 doInBackground()에서 처리하니 그때 경고 메시지가 사라졌음.
        // chart와 관련된 메소드들은 모두 UI 쓰레드에서 처리해야 하기 때문에 doInBackground()에 담을 수 없다.

        List<Entry> entries_total = new ArrayList<>();
        List<Entry> entries_divide21 = new ArrayList<>();
        List<Entry> entries_divide22 = new ArrayList<>();
        List<Entry> entries_divide32 = new ArrayList<>();
        List<Entry> entries_multiply22 = new ArrayList<>();
        List<Entry> entries_multiply32 = new ArrayList<>();
        List<Entry> entries_challenge = new ArrayList<>();

        labels_day = new ArrayList<>();

        for (int i = 0; i < recordMapList.size(); i++) {
            entries_total.add(new Entry((float)i, (float)(recordMapList.get(i).getTotal())));
            entries_divide21.add(new Entry((float)i, (float)(recordMapList.get(i).getRecordsMap().get("divide21"))));
            entries_divide22.add(new Entry((float)i, (float)(recordMapList.get(i).getRecordsMap().get("divide22"))));
            entries_divide32.add(new Entry((float)i, (float)(recordMapList.get(i).getRecordsMap().get("divide32"))));
            entries_multiply22.add(new Entry((float)i, (float)(recordMapList.get(i).getRecordsMap().get("multiply22"))));
            entries_multiply32.add(new Entry((float)i, (float)(recordMapList.get(i).getRecordsMap().get("multiply32"))));
            entries_challenge.add(new Entry((float)i, (float)(recordMapList.get(i).getRecordsMap().get("challenge"))));

            labels_day.add(recordMapList.get(i).getDay().substring((recordMapList.get(i).getDay().indexOf(".") + 2), recordMapList.get(i).getDay().length()));
        }

//        YAxis yAxis = chart.getAxisLeft();
//        yAxis.setValueFormatter(new ValueFormatter());

        LineDataSet set_total = new LineDataSet(entries_total, "합계");
        LineDataSet set_divide21 = new LineDataSet(entries_divide21, "두 자리 ÷ 한 자리");
        LineDataSet set_divide22 = new LineDataSet(entries_divide22, "두 자리 ÷ 두 자리");
        LineDataSet set_divide32 = new LineDataSet(entries_divide32, "세 자리 ÷ 두 자리");
        LineDataSet set_multiply22 = new LineDataSet(entries_multiply22, "두 자리 X 두 자리");
        LineDataSet set_multiply32 = new LineDataSet(entries_multiply32, "세 자리 X 두 자리");
        LineDataSet set_challenge = new LineDataSet(entries_challenge, "도전! 문제풀기");

        set_total.setAxisDependency(YAxis.AxisDependency.LEFT);
        set_total.setColor(Color.rgb(193, 37, 82));
        set_total.setValueTextSize(12f);
        set_total.setCircleColor(Color.rgb(193, 37, 82));
        set_total.setLineWidth(3f);
        set_total.setCircleRadius(5f);

//        setCustomLineDataSet(set_total, Color.rgb(193, 37, 82));
        setCustomLineDataSet(set_divide21, Color.rgb(255, 102, 0));
        setCustomLineDataSet(set_divide22, Color.rgb(245, 199, 0));
        setCustomLineDataSet(set_divide32, Color.rgb(106, 150, 31));
        setCustomLineDataSet(set_multiply22, Color.rgb(179, 100, 53));
        setCustomLineDataSet(set_multiply32, Color.rgb(53, 194, 209));
        setCustomLineDataSet(set_challenge,  Color.rgb(106, 167, 134));

//        set_total.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set_total.setColor(Color.rgb(192, 255, 140));
//        set_total.setValueTextSize(12f);
//        set_total.setCircleColor(Color.rgb(192, 255, 140));
//        set_total.setLineWidth(3f);
//        set_total.setCircleRadius(5f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set_total);
        dataSets.add(set_divide21);
        dataSets.add(set_divide22);
        dataSets.add(set_divide32);
        dataSets.add(set_multiply22);
        dataSets.add(set_multiply32);
        dataSets.add(set_challenge);

        return dataSets;
    }

    @Override
    protected void onPostExecute(List<ILineDataSet> dataSets) {
//        super.onPostExecute(recordMapList);
        asyncDialog.dismiss();

        LineData data = new LineData(dataSets);
        data.setValueFormatter(new MyValueFormatter());

//        String[] xValues = labels_total.toArray(new String[0]);

//        for (int i = 0; i < xValues.length; i++) {
//            Log.v(LOG_TAG, "xValues [" + i + "] : " + xValues[i]);
//        }

        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.BLACK);
//        xAxis.setDrawGridLines(false); // no grid lines
        xAxis.enableGridDashedLine(10f, 10f, 10f); //grid line을 점선으로
        xAxis.setValueFormatter(new MyXAixsValueFormatter(labels_day));

        YAxis left = chart.getAxisLeft();
//        left.setDrawLabels(false); // Y축에 표시되는 값
//        left.setDrawAxisLine(false); // Y축
//        left.setDrawGridLines(false); // no grid lines
        left.enableGridDashedLine(10f, 10f, 10f); //grid line을 점선으로
        left.setDrawZeroLine(true); // draw a zero line

        left.setAxisMinValue(0f);

        YAxis right = chart.getAxisRight();
        right.setAxisMinValue(0f);
        right.setEnabled(false); // 오른쪽에 있는 Y축 삭제
//        chart.getAxisRight().setEnabled(false); //이렇게 해도 돼

        Legend l = chart.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f);

        chart.setDescription(""); //차트 아래에 Description이라는 글자를 지워줌
//        chart.moveViewTo(100f, 0f, YAxis.AxisDependency.LEFT);
//        chart.centerViewTo(100f, 100f, YAxis.AxisDependency.LEFT);
        chart.setData(data);
        chart.invalidate();

    recordDAO.close();
    }


    private void setCustomLineDataSet(LineDataSet lineDataSet, int color) {
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setColor(color);
        lineDataSet.setValueTextSize(12f);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(4f);
    }

    public class MyXAixsValueFormatter implements AxisValueFormatter {

        private ArrayList<String> labels; //굳이 tutorial처럼 array of string을 쓸 필요가 없다!
        public MyXAixsValueFormatter(ArrayList<String> labels) {
            this.labels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
//            Log.v(LOG_TAG, "labels value : " + value);

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
//            Log.v(LOG_TAG, "value : " + String.valueOf((int)value));
            return String.valueOf((int)value);
        }
    }

}

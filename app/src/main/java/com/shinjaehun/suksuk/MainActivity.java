package com.shinjaehun.suksuk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private DialogMain dialogMain;
    private CurrentRecords currentRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final Record record = new Record(System.currentTimeMillis());
        currentRecords = CurrentRecords.getInstance();
        currentRecords.setContinueCounter(0);
        //currentRecords의 continueCounter를 여기서 초기화해줘야
        //메인화면으로 한번 나갔다오면 continueCounter가 다시 0으로 초기화된다.

        //records에 기록을 남기는 날짜를 저장하기 위해 MainActivity가 실행될 때 CurrentRecords를 생성한다.
//        Log.v(LOG_TAG, "today of currentRecords in MainActivity : " + currentRecords.getToday());

        FrameLayout problemBT = (FrameLayout)findViewById(R.id.main_problem);
        problemBT.setOnClickListener(new ImageButton.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogMain = new DialogMain(MainActivity.this, currentRecords);
                dialogMain.show();
            }
        });

        FrameLayout chartBT = (FrameLayout)findViewById(R.id.main_chart);
        chartBT.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChartActivity.class);
                startActivity(intent);
            }
        });



/*
        Button mul22 = (Button)findViewById(R.id.multiply22);
        mul22.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProblemActivity.class);
                //startActivity(new Intent(getApplicationContext(), ProblemActivity.class));
                intent.putExtra("operation", "multiply22");
//                startActivityForResult(intent, 1);
                startActivity(intent);
            }
        });

        Button mul32 = (Button)findViewById(R.id.multiply32);
        mul32.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProblemActivity.class);
                //startActivity(new Intent(getApplicationContext(), ProblemActivity.class));
                intent.putExtra("operation", "multiply32");

                startActivity(intent);

//                startActivityForResult(intent, 1);

            }
        });

        Button div21 = (Button)findViewById(R.id.divide21);
        div21.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProblemActivity.class);
                //startActivity(new Intent(getApplicationContext(), ProblemActivity.class));
                intent.putExtra("operation", "divide21");
                startActivity(intent);
            }
        });

        Button div22 = (Button)findViewById(R.id.divide22);
        div22.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProblemActivity.class);
                //startActivity(new Intent(getApplicationContext(), ProblemActivity.class));
                intent.putExtra("operation", "divide22");
                startActivity(intent);
            }
        });

        Button div32 = (Button)findViewById(R.id.divide32);
        div32.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProblemActivity.class);
                intent.putExtra("operation", "divide32");

                startActivity(intent);
            }
        });
*/

    }


//    private View.OnClickListener clickListener = new View.OnClickListener() {
//        //dialog의 clickListener를 여기서 처리한다.
//        @Override
//        public void onClick(View v) {
//            //dialog 확인 버튼을 클릭하면 액티비티 재시작!
//            dialogMain.dismiss();
//            //dialog를 dismiss()하지 않으면 android view windowleaked 오류가 발생한다.
//
//        }
//    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(requestCode == 1) {
//                Log.v(LOG_TAG, "Test" + data.getStringExtra("result"));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_debug:
                DialogDebug dialogDebug = new DialogDebug(MainActivity.this, currentRecords);
                dialogDebug.show();
                return true;
            case R.id.action_insert_dummy:
                insertDummies();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertDummies() {
        RecordDAO recordDAO = new RecordDAO(this);

        recordDAO.insertRecord("multiply22", "2016. 9. 1", 0, 1);
        recordDAO.insertRecord("multiply22", "2016. 9. 1", 0, 1);
        recordDAO.insertRecord("divide22", "2016. 9. 1", 0, 0);
        recordDAO.insertRecord("divide21", "2016. 9. 1", 0, 1);

        recordDAO.insertRecord("multiply22", "2016. 9. 2", 0, 1);
        recordDAO.insertRecord("multiply22", "2016. 9. 2", 0, 0);
        recordDAO.insertRecord("multiply32", "2016. 9. 2", 0, 0);
        recordDAO.insertRecord("multiply32", "2016. 9. 2", 0, 0);
        recordDAO.insertRecord("divide32", "2016. 9. 2", 0, 1);
        recordDAO.insertRecord("divide22", "2016. 9. 2", 0, 0);
        recordDAO.insertRecord("multiply32", "2016. 9. 2", 0, 0);

        recordDAO.insertRecord("multiply22", "2016. 9. 3", 0, 1);
        recordDAO.insertRecord("multiply22", "2016. 9. 3", 0, 1);
        recordDAO.insertRecord("multiply32", "2016. 9. 3", 0, 0);
        recordDAO.insertRecord("divide21", "2016. 9. 3", 0, 0);
        recordDAO.insertRecord("divide21", "2016. 9. 3", 0, 0);
        recordDAO.insertRecord("divide32", "2016. 9. 3", 0, 0);
        recordDAO.insertRecord("divide32", "2016. 9. 3", 0, 1);
        recordDAO.insertRecord("divide22", "2016. 9. 3", 0, 0);
        recordDAO.insertRecord("multiply32", "2016. 9. 3", 0, 0);

        recordDAO.insertRecord("multiply32", "2016. 9. 4", 0, 1);
        recordDAO.insertRecord("multiply32", "2016. 9. 4", 0, 1);
        recordDAO.insertRecord("divide32", "2016. 9. 4", 0, 0);
        recordDAO.insertRecord("divide21", "2016. 9. 4", 0, 0);

        recordDAO.insertRecord("multiply22", "2016. 9. 5", 0, 1);
        recordDAO.insertRecord("multiply22", "2016. 9. 5", 0, 1);
        recordDAO.insertRecord("multiply32", "2016. 9. 5", 0, 0);
        recordDAO.insertRecord("divide21", "2016. 9. 5", 0, 0);
        recordDAO.insertRecord("divide21", "2016. 9. 5", 0, 0);
        recordDAO.insertRecord("divide32", "2016. 9. 5", 0, 0);
        recordDAO.insertRecord("divide32", "2016. 9. 5", 0, 1);
        recordDAO.insertRecord("divide22", "2016. 9. 5", 0, 0);
        recordDAO.insertRecord("multiply32", "2016. 9. 5", 0, 0);
        recordDAO.insertRecord("divide21", "2016. 9. 5", 0, 0);
        recordDAO.insertRecord("divide32", "2016. 9. 5", 0, 0);
        recordDAO.insertRecord("divide32", "2016. 9. 5", 0, 1);
        recordDAO.insertRecord("multiply22", "2016. 9. 5", 0, 0);
        recordDAO.insertRecord("multiply32", "2016. 9. 5", 0, 0);

        recordDAO.insertRecord("divide32", "2016. 9. 6", 0, 1);
        recordDAO.insertRecord("divide22", "2016. 9. 6", 0, 0);
        recordDAO.insertRecord("multiply32", "2016. 9. 6", 0, 0);

        recordDAO.insertRecord("divide21", "2016. 9. 7", 0, 0);
        recordDAO.insertRecord("divide21", "2016. 9. 7", 0, 0);
        recordDAO.insertRecord("divide32", "2016. 9. 7", 0, 0);
        recordDAO.insertRecord("divide32", "2016. 9. 7", 0, 1);
        recordDAO.insertRecord("divide22", "2016. 9. 7", 0, 0);
        recordDAO.insertRecord("divide21", "2016. 9. 7", 0, 0);
        recordDAO.insertRecord("divide21", "2016. 9. 7", 0, 0);
        recordDAO.insertRecord("divide32", "2016. 9. 7", 0, 0);
//
//        recordDAO.insertRecord("divide22", "2016. 9. 8", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 9. 8", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 9. 8", 0, 1);
//        recordDAO.insertRecord("multiply32", "2016. 9. 8", 0, 1);
//        recordDAO.insertRecord("divide32", "2016. 9. 8", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 8", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 8", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 8", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 8", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 8", 0, 0);
//
//        recordDAO.insertRecord("divide22", "2016. 9. 9", 0, 0);
//        recordDAO.insertRecord("divide22", "2016. 9. 9", 0, 0);
//        recordDAO.insertRecord("divide22", "2016. 9. 9", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 9", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 9", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 9. 9", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 9. 9", 0, 1);
//        recordDAO.insertRecord("multiply32", "2016. 9. 9", 0, 1);
//        recordDAO.insertRecord("multiply32", "2016. 9. 9", 0, 0);
//        recordDAO.insertRecord("multiply22", "2016. 9. 9", 0, 1);
//        recordDAO.insertRecord("multiply22", "2016. 9. 9", 0, 1);
//        recordDAO.insertRecord("divide32", "2016. 9. 9", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 9", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 9", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 9", 0, 0);
//
//        recordDAO.insertRecord("divide21", "2016. 9. 10", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 10", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 10", 0, 0);
//        recordDAO.insertRecord("divide22", "2016. 9. 10", 0, 0);
//        recordDAO.insertRecord("divide22", "2016. 9. 10", 0, 0);
//        recordDAO.insertRecord("divide22", "2016. 9. 10", 0, 0);
//
//        recordDAO.insertRecord("multiply32", "2016. 9. 11", 0, 1);
//        recordDAO.insertRecord("multiply32", "2016. 9. 11", 0, 1);
//        recordDAO.insertRecord("multiply32", "2016. 9. 11", 0, 1);
//        recordDAO.insertRecord("divide32", "2016. 9. 11", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 11", 0, 0);
//        recordDAO.insertRecord("divide22", "2016. 9. 11", 0, 0);
//
//        recordDAO.insertRecord("divide21", "2016. 9. 12", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 12", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 12", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 12", 0, 1);
//        recordDAO.insertRecord("divide22", "2016. 9. 12", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 12", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 12", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 12", 0, 0);
//
//        recordDAO.insertRecord("divide32", "2016. 9. 13", 0, 1);
//        recordDAO.insertRecord("divide22", "2016. 9. 13", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 9. 13", 0, 0);
//
//        recordDAO.insertRecord("divide21", "2016. 9. 14", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 14", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 14", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 14", 0, 1);
//        recordDAO.insertRecord("divide22", "2016. 9. 14", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 14", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 14", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 14", 0, 0);
//
//        recordDAO.insertRecord("divide22", "2016. 9. 15", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 9. 15", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 9. 15", 0, 1);
//        recordDAO.insertRecord("multiply32", "2016. 9. 15", 0, 1);
//        recordDAO.insertRecord("divide32", "2016. 9. 15", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 15", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 15", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 15", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 15", 0, 0);
//
//        recordDAO.insertRecord("multiply22", "2016. 9. 16", 0, 1);
//        recordDAO.insertRecord("multiply22", "2016. 9. 16", 0, 1);
//        recordDAO.insertRecord("multiply32", "2016. 9. 16", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 16", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 16", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 16", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 16", 0, 1);
//        recordDAO.insertRecord("divide22", "2016. 9. 16", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 9. 16", 0, 0);
//
//        recordDAO.insertRecord("multiply32", "2016. 9. 17", 0, 1);
//        recordDAO.insertRecord("multiply32", "2016. 9. 17", 0, 1);
//        recordDAO.insertRecord("divide32", "2016. 9. 17", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 17", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 9. 17", 0, 1);
//        recordDAO.insertRecord("multiply32", "2016. 9. 17", 0, 1);
//        recordDAO.insertRecord("divide32", "2016. 9. 17", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 17", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 9. 17", 0, 1);
//        recordDAO.insertRecord("multiply32", "2016. 9. 17", 0, 1);
//        recordDAO.insertRecord("divide32", "2016. 9. 17", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 17", 0, 0);
//
//        recordDAO.insertRecord("multiply22", "2016. 9. 18", 0, 1);
//        recordDAO.insertRecord("multiply22", "2016. 9. 18", 0, 1);
//
//        recordDAO.insertRecord("multiply32", "2016. 9. 19", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 19", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 19", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 19", 0, 0);
//
//        recordDAO.insertRecord("divide32", "2016. 9. 20", 0, 1);
//        recordDAO.insertRecord("divide22", "2016. 9. 20", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 9. 20", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 20", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 20", 0, 0);
//
//        recordDAO.insertRecord("divide32", "2016. 9. 21", 0, 1);
//        recordDAO.insertRecord("multiply22", "2016. 9. 21", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 9. 21", 0, 0);
//
//        recordDAO.insertRecord("divide32", "2016. 9. 22", 0, 1);
//        recordDAO.insertRecord("divide22", "2016. 9. 22", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 9. 22", 0, 0);
//
//        recordDAO.insertRecord("divide21", "2016. 9. 23", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 23", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 23", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 23", 0, 1);
//        recordDAO.insertRecord("divide22", "2016. 9. 23", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 23", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 23", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 23", 0, 0);
//
//        recordDAO.insertRecord("divide22", "2016. 9. 24", 0, 0);
//        recordDAO.insertRecord("divide22", "2016. 9. 24", 0, 0);
//        recordDAO.insertRecord("divide22", "2016. 9. 24", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 24", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 24", 0, 0);
//
//        recordDAO.insertRecord("multiply32", "2016. 9. 25", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 9. 25", 0, 1);
//        recordDAO.insertRecord("multiply32", "2016. 9. 25", 0, 1);
//        recordDAO.insertRecord("multiply32", "2016. 9. 25", 0, 0);
//        recordDAO.insertRecord("multiply22", "2016. 9. 25", 0, 1);
//        recordDAO.insertRecord("multiply22", "2016. 9. 25", 0, 1);
//        recordDAO.insertRecord("divide32", "2016. 9. 25", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 25", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 25", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 25", 0, 0);
//
//        recordDAO.insertRecord("divide21", "2016. 9. 26", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 26", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 26", 0, 0);
//        recordDAO.insertRecord("divide22", "2016. 9. 26", 0, 0);
//        recordDAO.insertRecord("divide22", "2016. 9. 26", 0, 0);
//        recordDAO.insertRecord("divide22", "2016. 9. 26", 0, 0);
//
//        recordDAO.insertRecord("multiply32", "2016. 9. 27", 0, 1);
//        recordDAO.insertRecord("multiply32", "2016. 9. 27", 0, 1);
//        recordDAO.insertRecord("multiply32", "2016. 9. 27", 0, 1);
//        recordDAO.insertRecord("divide32", "2016. 9. 27", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 27", 0, 0);
//        recordDAO.insertRecord("divide22", "2016. 9. 27", 0, 0);
//
//        recordDAO.insertRecord("divide21", "2016. 9. 28", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 28", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 28", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 28", 0, 1);
//        recordDAO.insertRecord("divide22", "2016. 9. 28", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 28", 0, 0);
//
//        recordDAO.insertRecord("divide21", "2016. 9. 29", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 9. 29", 0, 0);
//
//        recordDAO.insertRecord("multiply22", "2016. 9. 30", 0, 1);
//        recordDAO.insertRecord("multiply22", "2016. 9. 30", 0, 1);
//        recordDAO.insertRecord("divide22", "2016. 9. 30", 0, 0);
//        recordDAO.insertRecord("divide21", "2016. 9. 30", 0, 1);
//
//        recordDAO.insertRecord("multiply22", "2016. 10. 1", 0, 1);
//        recordDAO.insertRecord("multiply22", "2016. 10. 1", 0, 0);
//
//        recordDAO.insertRecord("multiply32", "2016. 10. 2", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 10. 2", 0, 0);
//        recordDAO.insertRecord("divide32", "2016. 10. 2", 0, 1);
//        recordDAO.insertRecord("divide22", "2016. 10. 2", 0, 0);
//        recordDAO.insertRecord("multiply32", "2016. 10. 2", 0, 0);

        recordDAO.close();

    }
}

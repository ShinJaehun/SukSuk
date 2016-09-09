package com.shinjaehun.suksuk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.Chart;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    DialogMain dialogMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final Record record = new Record(System.currentTimeMillis());
        final CurrentRecords currentRecords = CurrentRecords.getInstance();
        currentRecords.setContinueCounter(0);
        //currentRecords의 continueCounter를 여기서 초기화해줘야
        //메인화면으로 한번 나갔다오면 continueCounter가 다시 0으로 초기화된다.

        //records에 기록을 남기는 날짜를 저장하기 위해 MainActivity가 실행될 때 CurrentRecords를 생성한다.
        Log.v(LOG_TAG, "today of currentRecords in MainActivity : " + currentRecords.getToday());

        ImageView mainBT = (ImageView)findViewById(R.id.main_button);
        mainBT.setOnClickListener(new ImageButton.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogMain = new DialogMain(MainActivity.this, currentRecords);
                dialogMain.show();
            }
        });

        Button thisBT = (Button) findViewById(R.id.chart_button);
        thisBT.setOnClickListener(new Button.OnClickListener() {


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
                Log.v(LOG_TAG, "Test" + data.getStringExtra("result"));
            }
        }
    }
}

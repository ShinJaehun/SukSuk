package com.shinjaehun.suksuk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }

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

package com.shinjaehun.suksuk;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by shinjaehun on 2016-08-14.
 */
public class DialogDebug extends Dialog {

    private static final String TAG = DialogDebug.class.getSimpleName();

    private Context context;
    //    private View.OnClickListener mClickListener;
    private CurrentRecords currentRecords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // dialog 외부 흐림 효과
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_debug);

        final EditText firstNumberET = (EditText)findViewById(R.id.first_number);
        final EditText secondNumberET = (EditText)findViewById(R.id.second_number);

        final RadioGroup calcRG = (RadioGroup)findViewById(R.id.type);
//        calcRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//            }
//        });



//
//        LinearLayout multiply22 = (LinearLayout)findViewById(R.id.multiply22);
//        multiply22.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ProblemActivity.class);
//                intent.putExtra("operation", "multiply22");
//                intent.putExtra("currentRecords", currentRecords);
//                context.startActivity(intent);
//                destroyDialog();
//            }
//        });
//
//        LinearLayout multiply32 = (LinearLayout)findViewById(R.id.multiply32);
//        multiply32.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ProblemActivity.class);
//                intent.putExtra("operation", "multiply32");
//                intent.putExtra("currentRecords", currentRecords);
//
//                context.startActivity(intent);
//                destroyDialog();
//            }
//        });
//
//        LinearLayout div21 = (LinearLayout)findViewById(R.id.divide21);
//        div21.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ProblemActivity.class);
//                intent.putExtra("operation", "divide21");
//                intent.putExtra("currentRecords", currentRecords);
//
//                context.startActivity(intent);
//                destroyDialog();
//            }
//        });
//
//        LinearLayout div22 = (LinearLayout)findViewById(R.id.divide22);
//        div22.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ProblemActivity.class);
//                intent.putExtra("operation", "divide22");
//                intent.putExtra("currentRecords", currentRecords);
//
//                context.startActivity(intent);
//                destroyDialog();
//            }
//        });
//
//        LinearLayout div32 = (LinearLayout)findViewById(R.id.divide32);
//        div32.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ProblemActivity.class);
//                intent.putExtra("operation", "divide32");
//                intent.putExtra("currentRecords", currentRecords);
//
//                context.startActivity(intent);
//                destroyDialog();
//            }
//        });
//
//        LinearLayout challenge = (LinearLayout)findViewById(R.id.challenge);
//        challenge.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ProblemActivity.class);
//                intent.putExtra("operation", "challenge");
//                intent.putExtra("currentRecords", currentRecords);
//
//                context.startActivity(intent);
//                destroyDialog();
//            }
//        });

        Button confirmBT = (Button)findViewById(R.id.button_confirm);
        confirmBT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int firstNumber = 0;
                int secondNumber = 0;

                int firstNumberDigit = 0;
                int secondNumberDigit = 0;

                if (firstNumberET.getText().toString().matches("") || secondNumberET.getText().toString().matches("")) {
                    Toast.makeText(getContext(), "수가 비었음!", Toast.LENGTH_SHORT).show();
                } else {

                    firstNumber = Integer.parseInt(firstNumberET.getText().toString());
                    firstNumberDigit = firstNumberET.getText().toString().length();

                    secondNumber = Integer.parseInt(secondNumberET.getText().toString());
                    secondNumberDigit = secondNumberET.getText().toString().length();

                    if (firstNumberDigit <= 1 || secondNumberDigit >= 3 ) {
                        Toast.makeText(getContext(), "첫 번째 수는 두 자리 수 이상, 두 번째 수는 두 자리 수 이하", Toast.LENGTH_SHORT).show();
                    } else {

                        if (calcRG.getCheckedRadioButtonId() == R.id.multiply) {

                            if (firstNumberDigit == 2 && secondNumberDigit == 2) {

                                Intent intent = new Intent(context, ProblemActivity.class);
                                intent.putExtra("operation", "multiply22");
                                intent.putExtra("currentRecords", currentRecords);

                                commitDebug(firstNumber, secondNumber);

                                context.startActivity(intent);
                                destroyDialog();

                            } else if (firstNumberDigit == 3 && secondNumberDigit == 2) {

                                Intent intent = new Intent(context, ProblemActivity.class);
                                intent.putExtra("operation", "multiply32");
                                intent.putExtra("currentRecords", currentRecords);

                                commitDebug(firstNumber, secondNumber);

                                context.startActivity(intent);
                                destroyDialog();

                            } else {
                                Toast.makeText(getContext(), "쑥쑥수학에서 계산할 수 없는 자리 수입니다", Toast.LENGTH_SHORT).show();
                            }

                        } else if (calcRG.getCheckedRadioButtonId() == R.id.divide){

                            if (firstNumberDigit == 2 && secondNumberDigit == 1) {

                                if (secondNumber == 0 || secondNumber == 1) {
                                    Toast.makeText(getContext(), "나누는 수가 0 또는 1인 경우 제외", Toast.LENGTH_SHORT).show();

                                } else {

                                    Intent intent = new Intent(context, ProblemActivity.class);
                                    intent.putExtra("operation", "divide21");
                                    intent.putExtra("currentRecords", currentRecords);

                                    commitDebug(firstNumber, secondNumber);

                                    context.startActivity(intent);
                                    destroyDialog();
                                }
                            } else if (firstNumberDigit == 2 && secondNumberDigit == 2) {
                                if (firstNumber <= secondNumber) {
                                    Toast.makeText(getContext(), "나누는 수가 나뉠 수보다 같거나 커서는 안됩니다", Toast.LENGTH_SHORT).show();
                                } else {

                                    Intent intent = new Intent(context, ProblemActivity.class);
                                    intent.putExtra("operation", "divide22");
                                    intent.putExtra("currentRecords", currentRecords);

                                    commitDebug(firstNumber, secondNumber);

                                    context.startActivity(intent);
                                    destroyDialog();

                                }

                            } else if (firstNumberDigit == 3 && secondNumberDigit == 2) {

                                Intent intent = new Intent(context, ProblemActivity.class);
                                intent.putExtra("operation", "divide32");
                                intent.putExtra("currentRecords", currentRecords);

                                commitDebug(firstNumber, secondNumber);

                                context.startActivity(intent);
                                destroyDialog();
                            } else {
                                Toast.makeText(getContext(), "쑥쑥수학에서 계산할 수 없는 자리 수입니다", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getContext(), "곱하기, 나누기 중에 선택하세요", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        Button backBT = (Button)findViewById(R.id.button_back);
        backBT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                destroyDialog();
            }
        });

    }

    private void commitDebug(int firstNumber, int secondNumber) {
        SharedPreferences debug = context.getSharedPreferences("debug", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = debug.edit();
        editor.putBoolean("isDebugging", true);
        editor.putInt("firstNumber", firstNumber);
        editor.putInt("secondNumber", secondNumber);
        editor.commit();
    }


    private void destroyDialog() {
        dismiss();
        //dialog를 dismiss()하지 않으면 android view windowleaked 오류가 발생한다.
    }

    public DialogDebug(Context context, CurrentRecords currentRecords) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Dialog 창에서 제목 없애기
        this.context = context;
        this.currentRecords = currentRecords;
//        mClickListener = clickListener;
    }


}

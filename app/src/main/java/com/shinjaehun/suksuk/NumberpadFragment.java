package com.shinjaehun.suksuk;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by shinjaehun on 2016-04-18.
 */
public class NumberpadFragment extends Fragment{
    private NumberpadClickListener listener;

    Button button1, button2, button3;
    Button button0, button4, button5, button6, button7, button8, button9;
    ImageButton buttonClear, buttonOK;
//    int num;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_numberpad, container);

        button0 = (Button)v.findViewById(R.id.button_0);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        button1 = (Button)v.findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        button2 = (Button)v.findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        button3 = (Button)v.findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        button4 = (Button)v.findViewById(R.id.button_4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        button5 = (Button)v.findViewById(R.id.button_5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        button6 = (Button)v.findViewById(R.id.button_6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        button7 = (Button)v.findViewById(R.id.button_7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        button8 = (Button)v.findViewById(R.id.button_8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        button9 = (Button)v.findViewById(R.id.button_9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        buttonClear = (ImageButton)v.findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearClicked();
            }
        });

        buttonOK = (ImageButton)v.findViewById(R.id.button_enter);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOKClicked();
            }
        });
        return v;

    }

    public void setClickListener(NumberpadClickListener listener) { this.listener = listener; }
    public void unSetClickListener() { this.listener = null; }

    public void onNumberClicked(View v) {
        switch (v.getId()) {
            case R.id.button_1:
                buttonClicked(1);
                break;
            case R.id.button_2:
                buttonClicked(2);
                break;
            case R.id.button_3:
                buttonClicked(3);
                break;
            case R.id.button_4:
                buttonClicked(4);
                break;
            case R.id.button_5:
                buttonClicked(5);
                break;
            case R.id.button_6:
                buttonClicked(6);
                break;
            case R.id.button_7:
                buttonClicked(7);
                break;
            case R.id.button_8:
                buttonClicked(8);
                break;
            case R.id.button_9:
                buttonClicked(9);
                break;
            case R.id.button_0:
                buttonClicked(0);
                break;
            case R.id.button_clear:
                onClearClicked();
                break;
            case R.id.button_enter:
                onOKClicked();
                break;
            default:
                break;
        }
    }

    public void buttonClicked(int num) {
        if (listener != null) {
            listener.onNumberClicked(num);
        }
    }
    public void onOKClicked() {
        if (listener != null) {
            listener.onOKClicked();
        }
    }
    public void onClearClicked() {
        if (listener != null) {
            listener.onClearClicked();
        }
    }

}

package com.shinjaehun.suksuk;

import android.app.Fragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
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

    Button number1BTN, number2BTN, number3BTN;
    Button number0BTN, number4BTN, number5BTN, number6BTN, number7BTN, number8BTN, number9BTN;
    ImageButton clearIB, OKIB;
//    int num;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_numberpad, container);

        number0BTN = (Button)v.findViewById(R.id.button_0);
        number0BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        number1BTN = (Button)v.findViewById(R.id.button_1);
        number1BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        number2BTN = (Button)v.findViewById(R.id.button_2);
        number2BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        number3BTN = (Button)v.findViewById(R.id.button_3);
        number3BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        number4BTN = (Button)v.findViewById(R.id.button_4);
        number4BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        number5BTN = (Button)v.findViewById(R.id.button_5);
        number5BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        number6BTN = (Button)v.findViewById(R.id.button_6);
        number6BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        number7BTN = (Button)v.findViewById(R.id.button_7);
        number7BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        number8BTN = (Button)v.findViewById(R.id.button_8);
        number8BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        number9BTN = (Button)v.findViewById(R.id.button_9);
        number9BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClicked(v);
            }
        });

        clearIB = (ImageButton)v.findViewById(R.id.button_clear);
        clearIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearClicked();
            }
        });

        OKIB = (ImageButton)v.findViewById(R.id.button_enter);
        OKIB.setOnClickListener(new View.OnClickListener() {
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

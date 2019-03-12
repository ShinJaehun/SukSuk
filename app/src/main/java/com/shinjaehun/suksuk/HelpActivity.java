package com.shinjaehun.suksuk;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by shinjaehun on 2016-05-06.
 */
public class HelpActivity extends AppCompatActivity {

    WebView displayYoutubeVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Intent intent = getIntent();
        String operation = intent.getStringExtra("help");
        String url = null;

        if (intent != null) {
            switch (operation) {
                case "multiply22" :
                    url = "https://www.youtube.com/embed/GDRlMTHaONk";
                    break;
                case "multiply32" :
                    url = "https://www.youtube.com/embed/6xaEJfQwQL8";
                    break;
                case "divide21_1" :
                    url = "https://www.youtube.com/embed/p1FWxV7sCZk";
                    break;
                case "divide21_10" :
                    url = "https://www.youtube.com/embed/hGqlRzL8oWg";
                    break;
                case "divide22" :
                    url = "https://www.youtube.com/embed/Jq1jjld0sOk";
                    break;
                case "divide32_1" :
                    url = "https://www.youtube.com/embed/vu2C_dRymYw";
                    break;
                case "divide32_10" :
                    url = "https://www.youtube.com/embed/mrBB0qVxO-s";
                    break;
                default:
                    break;
            }

        }

        displayYoutubeVideo = (WebView)findViewById(R.id.youtube_web_view);
        ChromeClient webViewClient = new ChromeClient(this);


        WebSettings webSettings = displayYoutubeVideo.getSettings();
        webSettings.setSaveFormData(false);
        webSettings.setSupportZoom(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        displayYoutubeVideo.setWebChromeClient(webViewClient);
        displayYoutubeVideo.setWebViewClient(new WebViewClient());
        displayYoutubeVideo.loadUrl(url);

        if (Build.VERSION.SDK_INT >= 11) {
            getWindow().addFlags(16777216);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
//            Class.forName("MainActivity").getMethod("onPause", (Class[])null).invoke(displayYoutubeVideo, (Object[]) null);
            Class.forName("android.webkit.WebView").getMethod("onPause", (Class[])null).invoke(displayYoutubeVideo, (Object[]) null);
//            ((AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE)).requestAudioFocus(new AudioManager.OnAudioFocusChangeListener() { @Override public void onAudioFocusChange(int focusChange) {} }, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            Class.forName("android.webkit.WebView").getMethod("onResume", (Class[])null).invoke(displayYoutubeVideo, (Object[])null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        if(displayYoutubeVideo != null) {
            displayYoutubeVideo.destroy();
            displayYoutubeVideo = null;
        }

        super.onDestroy();
    }
}

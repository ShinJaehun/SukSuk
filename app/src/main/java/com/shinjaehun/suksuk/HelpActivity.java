package com.shinjaehun.suksuk;

import android.content.Intent;
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
        String help = intent.getStringExtra("help");
        String url = null;

        if (intent != null) {
            switch (help) {
                case "multiply22" :
                    url = "https://www.youtube.com/embed/wNkOkrOGURk";
                    break;
                case "multiply32" :
                    url = "https://www.youtube.com/embed/5HXXk5H-hCs";
                    break;
                case "divide21" :
                    url = "https://www.youtube.com/embed/ZiwXgBjDk48";
                    break;
                case "divide22" :
                    url = "https://www.youtube.com/embed/ZiwXgBjDk48";
                    break;
                case "divide32" :
                    url = "https://www.youtube.com/embed/pIXC7Zxg-m0";
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
            Class.forName("MainActivity").getMethod("onPause", (Class[])null).invoke(displayYoutubeVideo, (Object[]) null);
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

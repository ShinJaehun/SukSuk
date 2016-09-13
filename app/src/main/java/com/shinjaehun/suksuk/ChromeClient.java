package com.shinjaehun.suksuk;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

/**
 * Created by shinjaehun on 2016-05-06.
 */
public class ChromeClient extends WebChromeClient {
    private View customView;
    private Activity clientActivity;

    public ChromeClient(Activity activity) {
        this.clientActivity = activity;
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        result.confirm();
        return super.onJsAlert(view, url, message, result);
    }

    private int originalOrientation;
    private FullscreenHolder fullscreenContainer;
    private CustomViewCallback customViewCollback;

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {

        if (customView != null) {
            callback.onCustomViewHidden();
            return;
        }

        originalOrientation = clientActivity.getRequestedOrientation();

        FrameLayout decor = (FrameLayout) clientActivity.getWindow().getDecorView();

        fullscreenContainer = new FullscreenHolder(clientActivity);
        fullscreenContainer.addView(view, ViewGroup.LayoutParams.MATCH_PARENT);
        decor.addView(fullscreenContainer, ViewGroup.LayoutParams.MATCH_PARENT);
        customView = view;
        customViewCollback = callback;
        clientActivity.setRequestedOrientation(originalOrientation);

    }

    @Override
    public void onHideCustomView() {
        if (customView == null) {
            return;
        }

        FrameLayout decor = (FrameLayout) clientActivity.getWindow().getDecorView();
        decor.removeView(fullscreenContainer);
        fullscreenContainer = null;
        customView = null;
        customViewCollback.onCustomViewHidden();

        clientActivity.setRequestedOrientation(originalOrientation);
    }


    static class FullscreenHolder extends FrameLayout {

        public FullscreenHolder(Context ctx) {
            super(ctx);
            setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
        }

        @Override
        public boolean onTouchEvent(MotionEvent evt) {
            return true;
        }
    }

}

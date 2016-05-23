package com.github.yinyuetai.util;

import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by Mr.Yangxiufeng on 2016/5/9.
 */
public class ComWebViewChromeClient extends WebChromeClient{
    private ProgressListener listener;
    public interface ProgressListener{
        public void onProgressChanged(int progress);
    }

    public void setListener(ProgressListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCloseWindow(WebView window) {
        super.onCloseWindow(window);
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        return super.onJsConfirm(view, url, message, result);
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (listener != null){
            listener.onProgressChanged(newProgress);
        }

    }
}

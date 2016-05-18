package com.github.yinyuetai.util;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Mr.Yangxiufeng on 2016/5/9.
 */
public class ComWebViewClient extends WebViewClient{

    public interface PageLoadListener{
        void onPageFinished();
        void onPageStarted();
        void onReceivedError();
    }
    private PageLoadListener pageLoadListener;
    private WebView webview;
    public ComWebViewClient(PageLoadListener pageLoadListener,WebView webview) {
        this.pageLoadListener = pageLoadListener;
        webview.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (pageLoadListener != null){
            pageLoadListener.onPageStarted();
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view,url);
        if (pageLoadListener != null){
            pageLoadListener.onPageFinished();
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (pageLoadListener != null){
            pageLoadListener.onReceivedError();
        }
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
        view.loadUrl(url);
        return true;
    }
}

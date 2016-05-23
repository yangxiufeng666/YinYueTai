package com.github.yinyuetai.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.yinyuetai.R;
import com.github.yinyuetai.util.ComWebViewChromeClient;
import com.github.yinyuetai.util.ComWebViewClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/17
 * YinYueTai
 */
public class WebActivity extends BaseActivity {
    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.progress)
    MaterialProgressBar progressBar;
    private MaterialDialog.Builder builder;
    private MaterialDialog materialDialog;

    private ComWebViewChromeClient.ProgressListener progressListener = new ComWebViewChromeClient.ProgressListener() {
        @Override
        public void onProgressChanged(int progress) {
           showLoading(progress);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        ButterKnife.bind(this);
        toolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTranslucenttatus(ContextCompat.getColor(this, R.color.tab_color_1));
        webview.setWebViewClient(new ComWebViewClient(webview));
        ComWebViewChromeClient chromeClient = new ComWebViewChromeClient();
        chromeClient.setListener(progressListener);
        webview.setWebChromeClient(chromeClient);
        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");
        webview.loadUrl(url);
        progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.colorPrimary)));
    }

    private void showLoading(int progress) {
        if (progress == 100){
            dismissLoading();
        }else{
            progressBar.setProgress(progress);
        }
    }

    private void dismissLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

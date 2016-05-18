package com.github.yinyuetai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.yinyuetai.R;
import com.github.yinyuetai.util.ComWebViewClient;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/17
 * YinYueTai
 */
public class WebActivity extends BaseActivity implements ComWebViewClient.PageLoadListener{
    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.webview)
    WebView webview;
    private MaterialDialog.Builder builder;
    private MaterialDialog materialDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        ButterKnife.bind(this);
        toolBar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTranslucenttatus(ContextCompat.getColor(this, R.color.tab_color_1));
        webview.setWebViewClient(new ComWebViewClient(this,webview));
        webview.setWebChromeClient(new WebChromeClient());
        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");
        webview.loadUrl(url);
        showLoading();
    }
    private void showLoading() {
        if (builder == null) {

            builder = new MaterialDialog.Builder(this);
            builder.title("等一下");
            builder.content("正在努力加载...")
                    .progress(true, 0);
        }
        materialDialog = builder.show();
    }

    private void dismissLoading() {
        materialDialog.dismiss();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageFinished() {
        dismissLoading();
    }

    @Override
    public void onPageStarted() {

    }

    @Override
    public void onReceivedError() {
        Toast.makeText(this,"加载出错",Toast.LENGTH_SHORT).show();
    }
}

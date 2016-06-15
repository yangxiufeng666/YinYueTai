package com.github.yinyuetai.setting;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.yinyuetai.R;
import com.github.yinyuetai.activity.AboutActivity;
import com.github.yinyuetai.activity.BaseActivity;

import java.io.File;
import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/6/3
 * YinYueTai
 */
public class SettingActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.cacheSize)
    TextView cacheSize;
    @Bind(R.id.clear)
    RelativeLayout clear;
    @Bind(R.id.switch_push)
    SwitchCompat switchPush;
    @Bind(R.id.switch_load_image_non_wifi)
    SwitchCompat switchLoadImageNonWifi;
    @Bind(R.id.current_version)
    TextView currentVersion;
    @Bind(R.id.check_update)
    RelativeLayout checkUpdate;
    @Bind(R.id.about)
    RelativeLayout about;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
        toolbar.setTitle("设置");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        currentVersion.setText(getVersionName());
        File file = Glide.getPhotoCacheDir(this);
        DecimalFormat fnum = new DecimalFormat("##0.00");
        String dd = fnum.format(getDirSize(file));
        cacheSize.setText(dd + "M");
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(getApplication()).clearDiskCache();
                    }
                }).start();
                cacheSize.setText("0.00M");
            }
        });
        setTranslucenttatus(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
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
    private String getVersionName()
    {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = packInfo.versionName;
        return version;
    }
    private float getDirSize(File file){
        if (file.exists()){
            if (file.isDirectory()){
                File[] children = file.listFiles();
                float size = 0;
                for (File f:children) {
                    size += getDirSize(f);
                }
                return size;
            }else {
                float size = (float)file.length()/1024/1024;
                return size;
            }
        }else {
            return 0.0f;
        }
    }
}

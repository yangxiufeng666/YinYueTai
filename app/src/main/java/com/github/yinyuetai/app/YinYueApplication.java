package com.github.yinyuetai.app;

import android.app.Application;

//import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by Mr.Yangxiufeng on 2016/5/10.
 */
public class YinYueApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext());
//        LeakCanary.install(this);
    }
}

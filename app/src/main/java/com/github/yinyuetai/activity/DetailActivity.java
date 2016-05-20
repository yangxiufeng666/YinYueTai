package com.github.yinyuetai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.yinyuetai.R;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/17
 * YinYueTai
 */
public class DetailActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mv_detail_layout);
    }
}

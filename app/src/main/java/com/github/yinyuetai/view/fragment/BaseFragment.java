package com.github.yinyuetai.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;


/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/6/12
 * YinYueTai
 */
public class BaseFragment extends Fragment{
    protected LinearLayoutManager linearLayoutManager;
    protected View rootView;
    protected int mWidth;
    protected int mHeight;
    protected boolean refresh;
    protected int lastVisibleItem;
    protected boolean hasMore = true;
    protected static final int SIZE = 20;
    protected int mOffset = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected void observerView(int imgHeight,int imgWidth ) {
        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        mWidth = metric.widthPixels;
        mHeight = (mWidth * imgHeight) / imgWidth;
    }
}

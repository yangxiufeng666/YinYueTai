package com.github.yinyuetai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.yinyuetai.R;
import com.github.yinyuetai.adapter.FirstRecycleViewAdapter;
import com.github.yinyuetai.domain.FirstPageBean;
import com.github.yinyuetai.http.OkHttpManager;
import com.github.yinyuetai.http.callback.StringCallBack;
import com.github.yinyuetai.util.URLProviderUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/10
 * YinYueTai
 */
public class FirstPageFragment extends Fragment {


    @Bind(R.id.first_page_recyclerView)
    RecyclerView firstPageRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private FirstRecycleViewAdapter recycleViewAdapter;
    private View rootView;
    private ArrayList<FirstPageBean> firstPageBeanList;
    int mWidth;
    int mHeight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.first_page_fragment, container, false);
            ButterKnife.bind(this, rootView);
            firstPageBeanList = new ArrayList<>();
            boserverView();
            initList();
            getData();
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void boserverView() {
        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        mWidth = metric.widthPixels;
        mHeight = ((mWidth * 0x21c) / 0x280);
        Log.i("HomeActivity", "mHeight =" + mHeight);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initList() {
        recycleViewAdapter = new FirstRecycleViewAdapter(firstPageBeanList, getActivity(), mWidth, mHeight);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        firstPageRecyclerView.setLayoutManager(linearLayoutManager);
        firstPageRecyclerView.setAdapter(recycleViewAdapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       swipeRefreshLayout.setRefreshing(false);
                    }
                },200);
            }
        });
    }

    private void getData() {
        OkHttpManager.getOkHttpManager().asyncGet(URLProviderUtil.getMainPageUrl(), FirstPageFragment.this, new StringCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onResponse(String response) {
                swipeRefreshLayout.setRefreshing(false);
                //创建一个JsonParser
                JsonParser parser = new JsonParser();
                //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
                JsonElement el = parser.parse(response);
                //把JsonElement对象转换成JsonArray
                JsonArray jsonArray = null;
                if (el.isJsonArray()) {
                    jsonArray = el.getAsJsonArray();
                }
                Iterator it = jsonArray.iterator();
                while (it.hasNext()) {
                    JsonElement e = (JsonElement) it.next();
                    //JsonElement转换为JavaBean对象
                    FirstPageBean field = new Gson().fromJson(e, FirstPageBean.class);
                    firstPageBeanList.add(field);
                }
                System.out.println(firstPageBeanList.size());
                recycleViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

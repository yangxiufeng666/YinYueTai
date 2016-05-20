package com.github.yinyuetai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.github.yinyuetai.R;
import com.github.yinyuetai.adapter.FirstRecycleViewAdapter;
import com.github.yinyuetai.domain.VideoBean;
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
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private LinearLayoutManager linearLayoutManager;
    private FirstRecycleViewAdapter recycleViewAdapter;
    private View rootView;
    private ArrayList<VideoBean> firstPageBeanList;
    private int mWidth;
    private int mHeight;
    private Runnable action;
    private MaterialDialog.Builder builder;
    private MaterialDialog materialDialog;
    private int lastVisibleItem;
    boolean hasMore = true;
    private static final int SIZE = 20;
    private int mOffset = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.first_page_fragment, container, false);
            ButterKnife.bind(this, rootView);
            firstPageBeanList = new ArrayList<>();
            boserverView();
            initList();
            getData(mOffset, SIZE);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void boserverView() {
        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        mWidth = metric.widthPixels;
        mHeight = (mWidth * 540) / 640;
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
        swipeRefreshLayout.setColorSchemeResources(R.color.tab_color_1);
        action = new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        };
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(action, 200);
            }
        });
        showLoading();
        firstPageRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(getActivity()).resumeRequests();
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (lastVisibleItem + 1 == recycleViewAdapter.getItemCount()) && hasMore) {
                    swipeRefreshLayout.setRefreshing(true);
                    getData(mOffset, SIZE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                Glide.with(getActivity()).pauseRequests();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstPageRecyclerView.smoothScrollToPosition(0);
            }
        });
    }

    private void showLoading() {
        if (builder == null) {

            builder = new MaterialDialog.Builder(getActivity());
            builder.cancelable(false);
            builder.title("等一下");
            builder.content("正在努力加载...")
                    .progress(true, 0);
        }
        materialDialog = builder.show();
    }

    private void dismissLoading() {
        materialDialog.dismiss();
    }

    private void getData(int offset, int size) {
        OkHttpManager.getOkHttpManager().asyncGet(URLProviderUtil.getMainPageUrl(offset, size), FirstPageFragment.this, new StringCallBack() {
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
                if (it.hasNext()) {
                    hasMore = true;
                    int size = 0;
                    while (it.hasNext()) {
                        JsonElement e = (JsonElement) it.next();
                        //JsonElement转换为JavaBean对象
                        VideoBean field = new Gson().fromJson(e, VideoBean.class);
                        firstPageBeanList.add(field);
                        size++;
                    }
                    System.out.println(firstPageBeanList.size());
                    mOffset += size;
                    recycleViewAdapter.notifyDataSetChanged();

                } else {
                    hasMore = false;
                }
                dismissLoading();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroyView() {
        if (action != null) {
            swipeRefreshLayout.removeCallbacks(action);
        }
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}

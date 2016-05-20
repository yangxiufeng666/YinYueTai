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
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.yinyuetai.R;
import com.github.yinyuetai.adapter.MVRecycleViewAdapter;
import com.github.yinyuetai.domain.MVListBean;
import com.github.yinyuetai.domain.VideoBean;
import com.github.yinyuetai.http.OkHttpManager;
import com.github.yinyuetai.http.callback.StringCallBack;
import com.github.yinyuetai.util.URLProviderUtil;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/11
 * YinYueTai
 */
public class MVViewPagerItemFragment extends Fragment {

    @Bind(R.id.fab)
    FloatingActionButton fab;

    public static Fragment getInstance(String areaCode) {
        MVViewPagerItemFragment mvViewPagerItemFragment = new MVViewPagerItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("areaCode", areaCode);
        mvViewPagerItemFragment.setArguments(bundle);
        return mvViewPagerItemFragment;
    }

    private static final int SIZE = 20;
    private int mOffset = 0;
    private String areaCode;
    @Bind(R.id.mv_RecyclerView)
    RecyclerView mvRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    MVRecycleViewAdapter recycleViewAdapter;
    private ArrayList<VideoBean> videosList = new ArrayList<VideoBean>();
    private View rootView;
    private int lastVisibleItem;
    boolean hasMore = true;
    private Runnable action;
    private int mWidth;
    private int mHeight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.mv_viewpager_item_fragment, container, false);
            Bundle bundle = getArguments();
            areaCode = bundle.getString("areaCode");
            boserverView();
            ButterKnife.bind(this, rootView);
            load();
        } else {
            ButterKnife.bind(this, rootView);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    private void boserverView() {
        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        mWidth = metric.widthPixels;
        mHeight = (mWidth * 360) / 640;
        Log.i("MVViewPagerItemFragment", "mHeight =" + mHeight);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        recycleViewAdapter = new MVRecycleViewAdapter(videosList, getActivity(),mWidth,mHeight);
        mvRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mvRecyclerView.setAdapter(recycleViewAdapter);
        mvRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(getActivity()).resumeRequests();
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (lastVisibleItem + 1 == recycleViewAdapter.getItemCount()) && hasMore) {
                    getData(mOffset + 1, SIZE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                Glide.with(getActivity()).pauseRequests();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.tab_color_2);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dismissProgress(null);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvRecyclerView.smoothScrollToPosition(0);
            }
        });
    }

    private void getData(int offset, int size) {
        showProgress();
        OkHttpManager.getOkHttpManager().asyncGet(URLProviderUtil.getMVListUrl(areaCode, offset, size), MVViewPagerItemFragment.this, new StringCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                dismissProgress(null);
            }

            @Override
            public void onResponse(String response) {
                dismissProgress(response);
            }
        });
    }

    private void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    private void dismissProgress(final String response) {
        //为了swipeRefreshLayout能显示出来，好看一些，故意延迟500ms
        action = new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                if (response != null) {
                    MVListBean mvListBean = new Gson().fromJson(response, MVListBean.class);
                    if (mvListBean.getVideos() == null || mvListBean.getVideos().size() == 0) {
                        hasMore = false;
                    } else {
                        hasMore = true;
                        int pos = videosList.size() - 1;
                        videosList.addAll(mvListBean.getVideos());
                        recycleViewAdapter.notifyItemRangeChanged(pos, mvListBean.getVideos().size());
                        mOffset += mvListBean.getVideos().size();
                    }
                }
            }
        };
        if (swipeRefreshLayout != null){
            swipeRefreshLayout.postDelayed(action, 250);
        }

    }

    protected void load() {
        initView();
        getData(mOffset, SIZE);
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

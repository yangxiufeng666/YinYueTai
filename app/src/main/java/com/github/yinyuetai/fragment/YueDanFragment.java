package com.github.yinyuetai.fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.github.yinyuetai.R;
import com.github.yinyuetai.adapter.YueDanRecycleViewAdapter;
import com.github.yinyuetai.domain.YueDanBean;
import com.github.yinyuetai.http.OkHttpManager;
import com.github.yinyuetai.http.callback.StringCallBack;
import com.github.yinyuetai.util.URLProviderUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/10
 * YinYueTai
 */
public class YueDanFragment extends Fragment {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private View rootView;
    private int lastVisibleItem;
    boolean hasMore = true;
    private static final int SIZE = 20;
    private int mOffset = 0;
    private YueDanRecycleViewAdapter recycleViewAdapter;
    List<YueDanBean.PlayListsBean> playLists = new ArrayList<>();
    private MaterialDialog.Builder builder;
    private MaterialDialog materialDialog;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.common_recycleview_layout, container, false);
            ButterKnife.bind(this, rootView);
            initView();
            showLoading();
            getData(mOffset, SIZE);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initView() {
        fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(),R.color.tab_color_4)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.tab_color_4);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dismissProgress(null);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycleViewAdapter = new YueDanRecycleViewAdapter(playLists, getActivity());
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(getActivity()).resumeRequests();
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE && hasMore && (lastVisibleItem == recycleViewAdapter.getItemCount() - 1)) {
                    getData(mOffset + 1, SIZE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Glide.with(getActivity()).pauseRequests();
                lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }
        });
    }

    private void getData(int offset, int size) {
        showProgress();
        OkHttpManager.getOkHttpManager().asyncGet(URLProviderUtil.getMainPageYueDanUrl(offset, size), YueDanFragment.this, new StringCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                dismissProgress(null);
                dismissLoading();
            }

            @Override
            public void onResponse(String response) {
                dismissLoading();
                dismissProgress(response);
            }
        });
    }

    private void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    private Runnable action;

    private void dismissProgress(final String response) {
        //为了swipeRefreshLayout能显示出来，好看一些，故意延迟500ms
        action = new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                if (response != null) {
                    YueDanBean yueDanBean = new Gson().fromJson(response, YueDanBean.class);
                    if (yueDanBean.getPlayLists() == null || yueDanBean.getPlayLists().size() == 0) {
                        hasMore = false;
                    } else {
                        hasMore = true;
                        int pos = playLists.size() - 1;
                        playLists.addAll(yueDanBean.getPlayLists());
                        recycleViewAdapter.notifyItemRangeChanged(pos, yueDanBean.getPlayLists().size());
                        mOffset += yueDanBean.getPlayLists().size();
                    }
                }
            }
        };
        swipeRefreshLayout.postDelayed(action, 250);
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

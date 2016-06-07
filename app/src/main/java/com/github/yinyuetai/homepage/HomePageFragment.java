package com.github.yinyuetai.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.github.yinyuetai.R;
import com.github.yinyuetai.adapter.FirstRecycleViewAdapter;
import com.github.yinyuetai.domain.VideoBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/10
 * YinYueTai
 */
public class HomePageFragment extends Fragment implements HomePageFragmentContract.View {
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
    private boolean refresh;
    private MaterialDialog.Builder builder;
    private MaterialDialog materialDialog;
    private int lastVisibleItem;
    boolean hasMore = true;
    private static final int SIZE = 20;
    private int mOffset = 0;

    private HomePageFragmentContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.first_page_fragment, container, false);
            ButterKnife.bind(this, rootView);
            firstPageBeanList = new ArrayList<>();
            observerView();
            new HomePagePresenter(this);
            initView();
            presenter.getData(mOffset, SIZE);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void observerView() {
        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        mWidth = metric.widthPixels;
        mHeight = (mWidth * 540) / 640;
    }

    private void initView() {
        recycleViewAdapter = new FirstRecycleViewAdapter(firstPageBeanList, getActivity(), mWidth, mHeight);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        firstPageRecyclerView.setLayoutManager(linearLayoutManager);
        firstPageRecyclerView.setAdapter(recycleViewAdapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.tab_color_1);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh = true;
                presenter.getData(0, SIZE);
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
                    presenter.getData(mOffset, SIZE);
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

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    @Override
    public void setData(ArrayList<VideoBean> dataList) {
        if (refresh){
            refresh = false;
            firstPageBeanList.clear();
            mOffset = 0;
        }
        if (dataList.size() > 0){
            hasMore = true;
        }else {
            hasMore = false;
        }
        mOffset += dataList.size();
        firstPageBeanList.addAll(dataList);
        recycleViewAdapter.notifyDataSetChanged();
        dismissLoading();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setError(String msg) {
        swipeRefreshLayout.setRefreshing(false);
        if (refresh){
            refresh = false;
            Toast.makeText(getActivity(),"刷新失败",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(),"获取数据失败",Toast.LENGTH_SHORT).show();
        }
        dismissLoading();
    }

    @Override
    public void setPresenter(HomePageFragmentContract.Presenter presenter) {
        this.presenter = presenter;
    }
}

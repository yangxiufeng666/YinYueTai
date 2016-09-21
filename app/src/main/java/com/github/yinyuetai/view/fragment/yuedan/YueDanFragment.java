package com.github.yinyuetai.view.fragment.yuedan;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.github.yinyuetai.presenter.YueDanFragmentContract;
import com.github.yinyuetai.presenter.YueDanFragmentPresenter;
import com.github.yinyuetai.view.fragment.BaseFragment;
import com.github.yinyuetai.R;
import com.github.yinyuetai.view.adapter.YueDanRecycleViewAdapter;
import com.github.yinyuetai.model.domain.YueDanBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/10
 * YinYueTai
 */
public class YueDanFragment extends BaseFragment implements YueDanFragmentContract.View{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private YueDanRecycleViewAdapter recycleViewAdapter;
    List<YueDanBean.PlayListsBean> playLists = new ArrayList<>();
    private MaterialDialog.Builder builder;
    private MaterialDialog materialDialog;

    private YueDanFragmentContract.Presenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.common_recycleview_layout, container, false);
            observerView(360,640);
            ButterKnife.bind(this, rootView);
            initView();
            new YueDanFragmentPresenter(this);
            showLoading();
            presenter.getData(mOffset, SIZE);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initView() {
        fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(),R.color.colorPrimary)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh = true;
                presenter.getData(0,SIZE);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycleViewAdapter = new YueDanRecycleViewAdapter(playLists, getActivity(),mWidth,mHeight);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(getActivity()).resumeRequests();
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE && hasMore && (lastVisibleItem == recycleViewAdapter.getItemCount() - 1)) {
                    showProgress(true);
                    presenter.getData(mOffset + 1, SIZE);
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

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    @Override
    public void setData(List<YueDanBean.PlayListsBean> data) {
        dismissLoading();
        showProgress(false);
        if (refresh){
            refresh = false;
            playLists.clear();
            mOffset = 0;
        }
        if (data == null || data.size() == 0) {
            hasMore = false;
        } else {
            hasMore = true;
            int pos = playLists.size() - 1;
            playLists.addAll(data);
            recycleViewAdapter.notifyItemRangeChanged(pos, data.size());
            mOffset += data.size();
        }
    }

    @Override
    public void setError(String msg) {
        dismissLoading();
        showProgress(false);
        if (refresh){
            refresh = false;
            Toast.makeText(getActivity(),"刷新失败",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(),"获取数据失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setPresenter(YueDanFragmentContract.Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void showLoading() {
        if (builder == null) {
            builder = new MaterialDialog.Builder(getActivity());
            builder.cancelable(false);
            builder.title("等一下");
            builder.content("正在努力加载...")
                    .progress(true, 0);
        }
        materialDialog = builder.show();
    }
    @Override
    public void dismissLoading() {
        materialDialog.dismiss();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showProgress(boolean flag) {
        swipeRefreshLayout.setRefreshing(flag);
    }
}

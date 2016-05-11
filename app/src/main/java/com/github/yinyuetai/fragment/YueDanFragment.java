package com.github.yinyuetai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.yinyuetai.R;
import com.github.yinyuetai.adapter.YueDanRecycleViewAdapter;
import com.github.yinyuetai.domain.MVListBean;
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
    private View rootView;
    private int lastVisibleItem;
    boolean hasMore = true;
    private static final int SIZE = 10;
    private int mOffset = 0;
    private YueDanRecycleViewAdapter recycleViewAdapter;
    List<YueDanBean.PlayListsBean> playLists = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.common_recycleview_layout, container, false);
            ButterKnife.bind(this, rootView);
            initView();
            getData(mOffset,SIZE);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    private void initView(){
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dismissProgress(null);
            }
        });
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        showProgress();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recycleViewAdapter = new YueDanRecycleViewAdapter(playLists,getActivity());
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && hasMore && (lastVisibleItem == recycleViewAdapter.getItemCount()-1)){
                    getData(mOffset+1,SIZE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }
        });
    }
    private void getData(int offset,int size){
        showProgress();
        OkHttpManager.getOkHttpManager().asyncGet(URLProviderUtil.getMainPageYueDanUrl(offset, size), YueDanFragment.this, new StringCallBack() {
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
    private void showProgress(){
        swipeRefreshLayout.setRefreshing(true);
    }
    private void dismissProgress(final String response){
        //为了swipeRefreshLayout能显示出来，好看一些，故意延迟500ms
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                if (response != null){
                    YueDanBean yueDanBean = new Gson().fromJson(response,YueDanBean.class);
                    if (yueDanBean.getPlayLists() == null || yueDanBean.getPlayLists().size() == 0) {
                        hasMore = false;
                    } else {
                        hasMore = true;
                        int pos = playLists.size()-1;
                        playLists.addAll(yueDanBean.getPlayLists());
                        recycleViewAdapter.notifyItemRangeChanged(pos,yueDanBean.getPlayLists().size());
                        mOffset += yueDanBean.getPlayLists().size();
                    }
                }
            }
        },500);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

package com.github.yinyuetai.mv;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.yinyuetai.R;
import com.github.yinyuetai.adapter.MVRecycleViewAdapter;
import com.github.yinyuetai.domain.VideoBean;
import com.github.yinyuetai.listener.ArrowUpListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/11
 * YinYueTai
 */
public class MVViewPagerItemFragment extends Fragment implements ArrowUpListener,MVItemFragmentContract.View{

    public static MVViewPagerItemFragment getInstance(String areaCode) {
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
    private ArrayList<VideoBean> videosList = new ArrayList<>();
    private View rootView;
    private int lastVisibleItem;
    boolean hasMore = true;
    private int mWidth;
    private int mHeight;
    private boolean refresh;
    private MVItemFragmentContract.Presenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("setUserVisibleHint","onCreateView="+areaCode);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.mv_viewpager_item_fragment, container, false);
            observerView();
            ButterKnife.bind(this, rootView);
            initView();
            new MVItemFragmentPresenter(this);
            presenter.getData(mOffset,SIZE,areaCode);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    private void observerView() {
        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        mWidth = metric.widthPixels;
        mHeight = (mWidth * 360) / 640;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        areaCode = bundle.getString("areaCode");
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
                    presenter.getData(mOffset + 1, SIZE,areaCode);
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
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh = true;
                presenter.getData(0,SIZE,areaCode);
            }
        });

    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    @Override
    public void smoothScrollToTop() {
        mvRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void scrollToTop() {
        mvRecyclerView.scrollToPosition(0);
    }

    @Override
    public void setData(List<VideoBean> videoList) {
        if (refresh){
            refresh = false;
            mOffset = 0;
            videosList.clear();
        }
        if (videoList == null || videoList.size() == 0) {
            hasMore = false;
        } else {
            hasMore = true;
            int pos = videosList.size() - 1;
            videosList.addAll(videoList);
            recycleViewAdapter.notifyItemRangeChanged(pos, videoList.size());
            mOffset += videoList.size();
        }
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
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void dismissLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setPresenter(MVItemFragmentContract.Presenter presenter) {
        this.presenter = presenter;
    }
}

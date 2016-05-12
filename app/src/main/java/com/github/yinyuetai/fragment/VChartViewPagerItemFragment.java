package com.github.yinyuetai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.yinyuetai.R;
import com.github.yinyuetai.adapter.VCharRecycleViewAdapter;
import com.github.yinyuetai.domain.VChartBean;
import com.github.yinyuetai.domain.VChartPeriod;
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
 * DATE 2016/5/12
 * YinYueTai
 */
public class VChartViewPagerItemFragment extends Fragment {
    @Bind(R.id.vchart_left_period)
    ImageView vchartLeftPeriod;
    @Bind(R.id.vchart_period)
    TextView vchartPeriod;
    @Bind(R.id.vchart_right_period)
    ImageView vchartRightPeriod;
    @Bind(R.id.period_layout)
    RelativeLayout periodLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private View rootView;
    private boolean hasCreatedOnce;
    private String areaCode;

    private VChartPeriod vChartPeriod;
    private List<VChartPeriod.PeriodsBean> periodsBeanArrayList;
    private VChartBean vChartBean;
    private List<VChartBean.VideosBean> videosBeen=new ArrayList<>();

    private VCharRecycleViewAdapter viewAdapter;

    public static Fragment newInstance(String areaCode) {
        VChartViewPagerItemFragment vChartViewPagerItemFragment = new VChartViewPagerItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("areaCode", areaCode);
        vChartViewPagerItemFragment.setArguments(bundle);
        return vChartViewPagerItemFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(R.layout.vchart_viewpager_fragment, container, false);
        }
        ButterKnife.bind(this, rootView);
        if (!hasCreatedOnce){
            hasCreatedOnce = true;
            initView();
            getPeriod();
        }
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        areaCode = getArguments().getString("areaCode");
    }
    private void initView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewAdapter = new VCharRecycleViewAdapter(getActivity(),videosBeen);
        recyclerView.setAdapter(viewAdapter);

    }
    private void getPeriod(){
        OkHttpManager.getOkHttpManager().asyncGet(URLProviderUtil.getVChartPeriodUrl(areaCode), VChartViewPagerItemFragment.this, new StringCallBack() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                vChartPeriod = new Gson().fromJson(response,VChartPeriod.class);
                periodsBeanArrayList = vChartPeriod.getPeriods();
                VChartPeriod.PeriodsBean periodsBean = periodsBeanArrayList.get(0);
                vchartPeriod.setText(String.format(getString(R.string.period_format),periodsBean.getYear(),periodsBean.getNo(),periodsBean.getBeginDateText(),periodsBean.getEndDateText()));
                getDataByPeriod(areaCode,periodsBean.getDateCode());
            }
        });
    }
    private void getDataByPeriod(String area,int dateCode){
        OkHttpManager.getOkHttpManager().asyncGet(URLProviderUtil.getVChartListUrl(area, dateCode), this, new StringCallBack() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                vChartBean = new Gson().fromJson(response,VChartBean.class);
                videosBeen.addAll(vChartBean.getVideos());
                viewAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

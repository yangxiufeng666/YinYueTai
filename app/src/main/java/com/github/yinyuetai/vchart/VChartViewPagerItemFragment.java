package com.github.yinyuetai.vchart;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.yinyuetai.R;
import com.github.yinyuetai.adapter.VCharRecycleViewAdapter;
import com.github.yinyuetai.domain.VChartPeriod;
import com.github.yinyuetai.domain.VideoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/12
 * YinYueTai
 */
public class VChartViewPagerItemFragment extends Fragment implements VChartPagerItemContract.View{
    @Bind(R.id.vchart_period)
    TextView vchartPeriod;
    @Bind(R.id.period_layout)
    RelativeLayout periodLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private View rootView;
    private boolean hasCreatedOnce;
    private String areaCode;
    private int dateCode;

    private List<VChartPeriod.PeriodsBean> periodsBeanArrayList;
    private List<VideoBean> videosBeen = new ArrayList<>();
    private VCharRecycleViewAdapter viewAdapter;
    private int mWidth;
    private int mHeight;

    private MaterialDialog materialDialog;
    private List<Integer> years;
    boolean scrolling;
    private SparseArray<List<VChartPeriod.PeriodsBean>> sparseArray;
    private WheelView periodWheelView;
    private WheelView yearWheelView;
    private PeriodAdapter periodAdapter;
    private boolean refresh;

    private VChartPagerItemContract.Presenter presenter;

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
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.vchart_viewpager_fragment, container, false);
            observerView();
        }
        ButterKnife.bind(this, rootView);
        if (!hasCreatedOnce) {
            hasCreatedOnce = true;
            initView();
            new VChartPagerItemPresenter(this);
            presenter.getPeriod(areaCode);
        }
        return rootView;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        areaCode = getArguments().getString("areaCode");
    }
    private void observerView() {
        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        mWidth = metric.widthPixels;
        mHeight = (mWidth * 360) / 640;
    }
    private void initView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewAdapter = new VCharRecycleViewAdapter(getActivity(), videosBeen,mWidth,mHeight);
        recyclerView.setAdapter(viewAdapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.tab_color_3);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh = true;
                presenter.getDataByPeriod(areaCode, dateCode);
            }
        });
        periodLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (materialDialog == null){
                    boolean wrapInScrollView = true;
                    materialDialog =new MaterialDialog.Builder(getActivity())
                            .customView(R.layout.period_pick_layout, wrapInScrollView)
                            .negativeText("取消")
                            .positiveText("确定").onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    VChartPeriod.PeriodsBean currentPeriodsBean = periodAdapter.getCurrentPeriodsBean(periodWheelView.getCurrentItem());
                                    if (currentPeriodsBean != null){
                                        vchartPeriod.setText(String.format(getString(R.string.period_format), currentPeriodsBean.getYear(), currentPeriodsBean.getNo(), currentPeriodsBean.getBeginDateText(), currentPeriodsBean.getEndDateText()));
                                        swipeRefreshLayout.setRefreshing(true);
                                        videosBeen.clear();
                                        viewAdapter.notifyDataSetChanged();
                                        dateCode = currentPeriodsBean.getDateCode();
                                        presenter.getDataByPeriod(areaCode, dateCode);
                                    }
                                }
                            }).show();
                    yearWheelView = (WheelView) materialDialog.getCustomView().findViewById(R.id.year);
                    yearWheelView.setViewAdapter(new YearsAdapter(getActivity(),years));
                    periodWheelView = (WheelView) materialDialog.getCustomView().findViewById(R.id.period);
                    yearWheelView.setDrawShadows(false);
                    yearWheelView.setWheelBackground(R.drawable.cu_wheel_bg);
                    periodWheelView.setDrawShadows(false);
                    periodWheelView.setWheelBackground(R.drawable.cu_wheel_bg);

                    yearWheelView.addChangingListener(new OnWheelChangedListener() {
                        @Override
                        public void onChanged(WheelView wheel, int oldValue, int newValue) {
                            if (!scrolling) {
                                List<VChartPeriod.PeriodsBean> p = sparseArray.get(yearWheelView.getCurrentItem());
                                updateCities(periodWheelView,p );
                            }
                        }
                    });

                    yearWheelView.addScrollingListener( new OnWheelScrollListener() {
                        @Override
                        public void onScrollingStarted(WheelView wheel) {
                            scrolling = true;
                        }
                        @Override
                        public void onScrollingFinished(WheelView wheel) {
                            scrolling = false;
                            List<VChartPeriod.PeriodsBean> p = sparseArray.get(yearWheelView.getCurrentItem());
                            updateCities(periodWheelView,p);
                        }
                    });
                    yearWheelView.setCurrentItem(0);
                    updateCities(periodWheelView,sparseArray.get(0));
                }else{
                    materialDialog.show();
                }
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override
    public void setAreaData(VChartPeriod vChartPeriod) {
        periodsBeanArrayList = vChartPeriod.getPeriods();
        VChartPeriod.PeriodsBean currentPeriodsBean = periodsBeanArrayList.get(0);
        vchartPeriod.setText(String.format(getString(R.string.period_format), currentPeriodsBean.getYear(), currentPeriodsBean.getNo(), currentPeriodsBean.getBeginDateText(), currentPeriodsBean.getEndDateText()));
        presenter.getDataByPeriod(areaCode, currentPeriodsBean.getDateCode());
        years = vChartPeriod.getYears();
        sparseArray = new SparseArray<>();
        int index=0;
        for (Integer integer : years){
            List<VChartPeriod.PeriodsBean> list = new ArrayList<>();
            for (VChartPeriod.PeriodsBean p : periodsBeanArrayList){
                if (integer == p.getYear()){
                    list.add(p);
                }
            }
            sparseArray.put(index,list);
            index++;
        }
    }

    @Override
    public void setVideoData(List<VideoBean> videoBeen) {
        swipeRefreshLayout.setRefreshing(false);
        if (refresh){
            refresh = false;
            videosBeen.clear();
        }
        videosBeen.addAll(videoBeen);
        viewAdapter.notifyDataSetChanged();
    }

    @Override
    public void setError(String msg) {
        if (refresh){
            refresh = false;
            Toast.makeText(getActivity(),"刷新失败",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(),"获取数据失败",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void setPresenter(VChartPagerItemContract.Presenter presenter) {
        this.presenter = presenter;
    }
    /**
     * Updates the city wheel
     */
    private void updateCities(WheelView city, List<VChartPeriod.PeriodsBean> list) {
        periodAdapter = new PeriodAdapter(getActivity(),list);
        city.setViewAdapter(periodAdapter);
        city.setCurrentItem(0);
    }
    /**
     * Adapter for countries
     */
    private class YearsAdapter extends AbstractWheelTextAdapter {
        private List<Integer> years;
        /**
         * Constructor
         */
        protected YearsAdapter(Context context,List<Integer> years) {
            super(context, R.layout.period_year, NO_RESOURCE);
            this.years = years;
            setItemTextResource(R.id.country_name);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return years.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return years.get(index)+"";
        }
    }
    private class PeriodAdapter extends AbstractWheelTextAdapter{
        List<VChartPeriod.PeriodsBean> list;
        public PeriodAdapter(Context context,List<VChartPeriod.PeriodsBean> list) {
            super(context, R.layout.period_year, NO_RESOURCE);
            this.list = list;
            setItemTextResource(R.id.country_name);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int i) {
            VChartPeriod.PeriodsBean bean = list.get(i);
            return "第"+bean.getNo()+"期"+"("+bean.getBeginDateText()+"-"+bean.getEndDateText()+")";
        }
        public VChartPeriod.PeriodsBean getCurrentPeriodsBean(int index){
            if (index < 0 || index > list.size()){
                return null;
            }
            return list.get(index);
        }
    }
}

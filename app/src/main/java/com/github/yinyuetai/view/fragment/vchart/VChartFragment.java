package com.github.yinyuetai.view.fragment.vchart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.yinyuetai.R;
import com.github.yinyuetai.presenter.VChartFragmentContract;
import com.github.yinyuetai.presenter.VChartFragmentPresenter;
import com.github.yinyuetai.view.adapter.MVViewPagerAdapter;
import com.github.yinyuetai.model.domain.AreaBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/10
 * YinYueTai
 */
public class VChartFragment extends Fragment implements  VChartFragmentContract.View{
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    private View rootView;
    private boolean hasCreatedOnce;
    private MVViewPagerAdapter pagerAdapter;
    private MaterialDialog.Builder builder;
    private MaterialDialog materialDialog;
    private VChartFragmentContract.Presenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView==null){
            rootView = inflater.inflate(R.layout.vchart_page_fragment, container, false);
        }
        ButterKnife.bind(this, rootView);
        if (!hasCreatedOnce){
            hasCreatedOnce = true;
            new VChartFragmentPresenter(this);
            showLoading();
            presenter.getData(0,0);
        }
        return rootView;
    }
    private void initViewPager(ArrayList<Fragment> fragments,ArrayList<AreaBean> areaBeanArrayList){
        pagerAdapter = new MVViewPagerAdapter(getFragmentManager(),fragments,areaBeanArrayList);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setData(ArrayList<AreaBean> areaBeanArrayList) {
        dismissLoading();
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (AreaBean area :
                areaBeanArrayList) {
            fragments.add(VChartViewPagerItemFragment.newInstance(area.getCode()));
        }
        initViewPager(fragments,areaBeanArrayList);
    }

    @Override
    public void setError(String msg) {
        dismissLoading();
        Toast.makeText(getActivity(),"获取数据失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(VChartFragmentContract.Presenter presenter) {
        this.presenter = presenter;
    }
    public void showLoading(){
        if (builder == null){

            builder = new MaterialDialog.Builder(getActivity());
            builder.cancelable(false);
            builder.title("等一下");
            builder.content("正在努力加载...")
                    .progress(true, 0);
        }
        materialDialog = builder.show();
    }
    public void dismissLoading(){
        materialDialog.dismiss();
    }
}

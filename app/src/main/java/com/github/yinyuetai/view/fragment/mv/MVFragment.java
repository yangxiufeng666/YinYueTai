package com.github.yinyuetai.view.fragment.mv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.yinyuetai.R;
import com.github.yinyuetai.presenter.MVFragmentContract;
import com.github.yinyuetai.presenter.MVFragmentPresenter;
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
public class MVFragment extends Fragment implements MVFragmentContract.View{
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.mv_pager)
    ViewPager mvPager;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private View rootView;
    private MVViewPagerAdapter pagerAdapter;
    private MaterialDialog.Builder builder;
    private MaterialDialog materialDialog;
    ArrayList<MVViewPagerItemFragment> fragments = new ArrayList<>();
    private MVFragmentContract.Presenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.mv_page_fragment, container, false);
            ButterKnife.bind(this, rootView);
            initView();
            new MVFragmentPresenter(this);
            showLoading();
            presenter.getData(0,0);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    private void initView(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void initViewPager(ArrayList<MVViewPagerItemFragment> fragments,ArrayList<AreaBean> areaBeanArrayList){
        pagerAdapter = new MVViewPagerAdapter(getFragmentManager(),fragments,areaBeanArrayList);
        mvPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(mvPager);
    }
    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
    @Override
    public void setPresenter(MVFragmentContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setData(ArrayList<AreaBean> areaBeanArrayList) {
        dismissLoading();
        for (AreaBean area :
                areaBeanArrayList) {
            fragments.add(MVViewPagerItemFragment.getInstance(area.getCode()));
        }
        initViewPager(fragments,areaBeanArrayList);
    }

    @Override
    public void setError(String msg) {
        dismissLoading();
        Toast.makeText(getActivity(),"获取数据失败:"+msg,Toast.LENGTH_SHORT).show();
    }
    @Override
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
    @Override
    public void dismissLoading(){
        materialDialog.dismiss();
    }
}

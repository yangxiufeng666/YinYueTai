package com.github.yinyuetai.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.github.yinyuetai.domain.AreaBean;

import java.util.ArrayList;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/11
 * YinYueTai
 */
public class MVViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments=new ArrayList<>();
    ArrayList<AreaBean> areaBeanArrayList = new ArrayList<>();

    public MVViewPagerAdapter(FragmentManager fm,ArrayList<? extends Fragment> fragments,ArrayList<AreaBean> areaBeanArrayList) {
        super(fm);
        this.fragments.addAll(fragments);
        this.areaBeanArrayList.addAll(areaBeanArrayList);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return areaBeanArrayList.get(position).getName();
    }
}

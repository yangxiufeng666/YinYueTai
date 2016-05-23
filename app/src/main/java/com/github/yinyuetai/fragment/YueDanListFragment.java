package com.github.yinyuetai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.yinyuetai.R;
import com.github.yinyuetai.domain.YueDanDetailBean;

import butterknife.Bind;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/23
 * YinYueTai
 */
public class YueDanListFragment extends Fragment{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private View rootView;
    private boolean hasLoadOnce;

    public static YueDanListFragment newInstance(YueDanDetailBean yueDanDetailBean){
        YueDanListFragment fragment = new YueDanListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("yueDanDetailBean",yueDanDetailBean);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

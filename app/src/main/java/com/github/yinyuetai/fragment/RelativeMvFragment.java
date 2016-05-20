package com.github.yinyuetai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.yinyuetai.R;
import com.github.yinyuetai.adapter.RelativeMvRecycleAdapter;
import com.github.yinyuetai.domain.MVDetailBean;
import com.github.yinyuetai.widget.RecycleViewDivider;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/20
 * YinYueTai
 */
public class RelativeMvFragment extends Fragment {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private MVDetailBean mvDetailBean;
    private View rootView;
    private boolean hasLoadOnce;
    private RelativeMvRecycleAdapter adapter;

    public static RelativeMvFragment newInstance(MVDetailBean mvDetailBean) {
        RelativeMvFragment fragment = new RelativeMvFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("mvDetailBean", mvDetailBean);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvDetailBean = getArguments().getParcelable("mvDetailBean");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.recycleview_layout, container, false);
        }
        ButterKnife.bind(this, rootView);
        if (!hasLoadOnce){
            hasLoadOnce = true;
            initData();
        }
        return rootView;
    }
    private void initData(){
        adapter = new RelativeMvRecycleAdapter(getActivity(),mvDetailBean.getRelatedVideos());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecycleViewDivider(getActivity(),LinearLayoutManager.HORIZONTAL));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

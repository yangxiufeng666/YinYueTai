package com.github.yinyuetai.view.fragment.yuedan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.yinyuetai.R;
import com.github.yinyuetai.model.domain.YueDanDetailBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/23
 * YinYueTai
 */
public class YueDanDescribeFragment extends Fragment {
    @Bind(R.id.artistName)
    TextView artistName;
    @Bind(R.id.author)
    TextView author;
    @Bind(R.id.update_date)
    TextView updateDate;
    @Bind(R.id.play_count)
    TextView playCount;
    @Bind(R.id.play_pc_count)
    TextView playPcCount;
    @Bind(R.id.play_mobile_count)
    TextView playMobileCount;
    @Bind(R.id.describe)
    TextView describe;
    private View rootView;
    private boolean hasLoadOnce;

    public static YueDanDescribeFragment newInstance(YueDanDetailBean yueDanDetailBean){
        YueDanDescribeFragment fragment = new YueDanDescribeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("yueDanDetailBean",yueDanDetailBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.yuedan_describe_layout, container, false);
        }
        ButterKnife.bind(this, rootView);
        if (!hasLoadOnce){
            initView();
            hasLoadOnce = true;
        }
        return rootView;
    }

    private void initView(){
        Bundle bundle = getArguments();
        YueDanDetailBean yueDanDetailBean = bundle.getParcelable("yueDanDetailBean");
        artistName.setText(yueDanDetailBean.getTitle());
        author.setText("作者："+yueDanDetailBean.getCreator().getNickName());
        updateDate.setText("更新时间："+yueDanDetailBean.getUpdateTime());
        playCount.setText("播放次数："+yueDanDetailBean.getTotalViews());
        playPcCount.setText("收藏次数："+yueDanDetailBean.getTotalFavorites());
        playMobileCount.setText("获得积分："+yueDanDetailBean.getIntegral());
        describe.setText(yueDanDetailBean.getDescription());
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

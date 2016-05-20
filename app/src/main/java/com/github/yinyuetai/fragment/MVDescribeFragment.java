package com.github.yinyuetai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.yinyuetai.R;
import com.github.yinyuetai.domain.MVDetailBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/20
 * YinYueTai
 */
public class MVDescribeFragment extends Fragment {
    @Bind(R.id.profile_image)
    CircleImageView profileImage;
    @Bind(R.id.yirenlayout)
    TextView yirenlayout;
    @Bind(R.id.artistName)
    TextView artistName;
    @Bind(R.id.play_count)
    TextView playCount;
    @Bind(R.id.play_pc_count)
    TextView playPcCount;
    @Bind(R.id.play_mobile_count)
    TextView playMobileCount;
    @Bind(R.id.describe)
    TextView describe;
    private View rootView;

    private MVDetailBean mvDetailBean;

    public static MVDescribeFragment newInstance(MVDetailBean mvDetailBean){
        MVDescribeFragment fragment = new MVDescribeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("mvDetailBean",mvDetailBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.mv_describe_layout, container, false);
        }
        ButterKnife.bind(this, rootView);
        initData();
        return rootView;
    }
    private void initData(){
        Glide.with(this).load(mvDetailBean.getArtists().get(0).getArtistAvatar()).centerCrop().into(profileImage);
        artistName.setText(mvDetailBean.getArtistName());
        playCount.setText("播放次数："+String.valueOf(mvDetailBean.getTotalViews()));
        playPcCount.setText("pc端："+String.valueOf(mvDetailBean.getTotalPcViews()));
        playMobileCount.setText("移动端："+String.valueOf(mvDetailBean.getTotalMobileViews()));
        describe.setText(mvDetailBean.getDescription());
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvDetailBean = getArguments().getParcelable("mvDetailBean");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

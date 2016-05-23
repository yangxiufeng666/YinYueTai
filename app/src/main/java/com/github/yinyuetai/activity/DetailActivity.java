package com.github.yinyuetai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.github.yinyuetai.R;
import com.github.yinyuetai.domain.MVDetailBean;
import com.github.yinyuetai.fragment.MVDescribeFragment;
import com.github.yinyuetai.fragment.RelativeMvFragment;
import com.github.yinyuetai.http.OkHttpManager;
import com.github.yinyuetai.http.callback.StringCallBack;
import com.github.yinyuetai.util.URLProviderUtil;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/17
 * YinYueTai
 */
public class DetailActivity extends BaseActivity {
    int id;
    @Bind(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    @Bind(R.id.nav)
    LinearLayout nav;
    @Bind(R.id.mv_describe)
    ImageView mvDescribe;
    @Bind(R.id.mv_comment)
    ImageView mvComment;
    @Bind(R.id.relative_mv)
    ImageView relativeMv;
    private MaterialDialog.Builder builder;
    private MaterialDialog materialDialog;
    private MVDetailBean detailBean;

    private MVDescribeFragment describeFragment;
    private RelativeMvFragment relativeMvFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mv_detail_layout);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", -10);
        mvDescribe.setOnClickListener(imageClickListener);
        mvComment.setOnClickListener(imageClickListener);
        relativeMv.setOnClickListener(imageClickListener);
        getData();
    }
    private View.OnClickListener imageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.mv_describe:
                    setImageBackground(mvDescribe,R.drawable.player_mv_p);
                    setImageBackground(mvComment,R.drawable.player_comment);
                    setImageBackground(relativeMv,R.drawable.player_relative_mv);
                    setFragment(describeFragment);
                    break;
                case R.id.mv_comment:
                    setImageBackground(mvDescribe,R.drawable.player_mv);
                    setImageBackground(mvComment,R.drawable.player_comment_p);
                    setImageBackground(relativeMv,R.drawable.player_relative_mv);
                    break;
                case R.id.relative_mv:
                    setImageBackground(mvDescribe,R.drawable.player_mv);
                    setImageBackground(mvComment,R.drawable.player_comment);
                    setImageBackground(relativeMv,R.drawable.player_relative_mv_p);
                    setFragment(relativeMvFragment);
                    break;
            }
        }
    };
    private void setImageBackground(ImageView imageView,int resId){
        imageView.setBackgroundResource(resId);
    }
    private void getData() {
        showLoading();
        OkHttpManager.getOkHttpManager().asyncGet(URLProviderUtil.getRelativeVideoListUrl(id), this, new StringCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                dismissLoading();
            }

            @Override
            public void onResponse(String response) {
                dismissLoading();
                detailBean = new Gson().fromJson(response, MVDetailBean.class);
                videoplayer.setUp(detailBean.getUrl(), detailBean.getTitle());
                Glide.with(DetailActivity.this).load(detailBean.getPosterPic()).centerCrop().into(videoplayer.ivThumb);
                describeFragment = MVDescribeFragment.newInstance(detailBean);
                relativeMvFragment = RelativeMvFragment.newInstance(detailBean);
                setFragment(describeFragment);
            }
        });
    }

    private void setFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragment.isAdded() && fragment.isVisible()) {
            return;
        }
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.replace(R.id.fragment_content, fragment);
        }
        transaction.commit();
    }

    private void showLoading() {
        if (builder == null) {

            builder = new MaterialDialog.Builder(this);
            builder.title("等一下");
            builder.content("正在努力加载...")
                    .cancelable(false)
                    .progress(true, 0);
        }
        materialDialog = builder.show();
    }

    private void dismissLoading() {
        materialDialog.dismiss();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}

package com.github.yinyuetai.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.yinyuetai.R;
import com.github.yinyuetai.domain.MVDetailBean;
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
    @Bind(R.id.navi_mvdetail_collection)
    ImageView naviMvdetailCollection;
    @Bind(R.id.navi_mvdetail_down)
    ImageView naviMvdetailDown;
    @Bind(R.id.navi_mvdetail_shared)
    ImageView naviMvdetailShared;
    @Bind(R.id.navi_mvdetail_game)
    ImageView naviMvdetailGame;
    @Bind(R.id.navi_mvdetail_reply)
    ImageView naviMvdetailReply;
    @Bind(R.id.navi_mvdetail_gift)
    ImageView naviMvdetailGift;
    @Bind(R.id.navi_mvdetail_addylist)
    ImageView naviMvdetailAddylist;
    @Bind(R.id.bottom_navi_LinearLayout)
    LinearLayout bottomNaviLinearLayout;
    int id;
    @Bind(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    private MaterialDialog.Builder builder;
    private MaterialDialog materialDialog;
    private MVDetailBean detailBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mv_detail_layout);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", -10);
        getData();
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
                System.out.println(response);
                detailBean = new Gson().fromJson(response, MVDetailBean.class);
                videoplayer.setUp(detailBean.getUrl(),detailBean.getTitle());
            }
        });
    }

    private void showLoading() {
        if (builder == null) {

            builder = new MaterialDialog.Builder(this);
            builder.title("等一下");
            builder.content("正在努力加载...")
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

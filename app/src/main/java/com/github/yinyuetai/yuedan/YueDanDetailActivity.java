package com.github.yinyuetai.yuedan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.yinyuetai.R;
import com.github.yinyuetai.activity.BaseActivity;
import com.github.yinyuetai.activity.SwipeBackAppCompatActivity;
import com.github.yinyuetai.domain.YueDanDetailBean;
import com.github.yinyuetai.http.OkHttpManager;
import com.github.yinyuetai.http.callback.StringCallBack;
import com.github.yinyuetai.listener.PlayVideoListener;
import com.github.yinyuetai.util.URLProviderUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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
public class YueDanDetailActivity extends SwipeBackAppCompatActivity {
    @Bind(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    @Bind(R.id.yuedan_describe)
    ImageView yuedanDescribe;
    @Bind(R.id.yuedan_comment)
    ImageView yuedanComment;
    @Bind(R.id.yuedan_list)
    ImageView yuedanList;
    private MaterialDialog.Builder builder;
    private MaterialDialog materialDialog;
    private YueDanDescribeFragment describeFragment;
    private YueDanListFragment yueDanListFragment;
    private int id;
    YueDanDetailBean yueDanDetailBean;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yuedan_detail_layout);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", -10);
        initView();
        getData();
    }
    private void initView(){
        yuedanComment.setOnClickListener(imageClickListener);
        yuedanDescribe.setOnClickListener(imageClickListener);
        yuedanList.setOnClickListener(imageClickListener);
    }
    private void getData() {
        showLoading();
        OkHttpManager.getOkHttpManager().asyncGet(URLProviderUtil.getPeopleYueDanList(id), this, new StringCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                Toast.makeText(YueDanDetailActivity.this,"获取数据失败.",Toast.LENGTH_SHORT).show();
                dismissLoading();
            }

            @Override
            public void onResponse(String response) {
                dismissLoading();
                try {
                    yueDanDetailBean = new Gson().fromJson(response,YueDanDetailBean.class);
                    videoplayer.setUp(yueDanDetailBean.getVideos().get(0).getHdUrl(), yueDanDetailBean.getVideos().get(0).getTitle());
                    videoplayer.ivThumb.performClick();
//                    Glide.with(YueDanDetailActivity.this).load(yueDanDetailBean.getThumbnailPic()).centerCrop().into(videoplayer.ivThumb);
                    describeFragment = YueDanDescribeFragment.newInstance(yueDanDetailBean);
                    yueDanListFragment = YueDanListFragment.newInstance(yueDanDetailBean);
                    yueDanListFragment.setPlayVideoListener(playVideoListener);
                    setFragment(describeFragment);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    Toast.makeText(YueDanDetailActivity.this,"error:"+response,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void setImageBackground(ImageView imageView,int resId){
        imageView.setBackgroundResource(resId);
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
    private View.OnClickListener imageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.yuedan_describe:
                    setImageBackground(yuedanDescribe,R.drawable.player_yue_p);
                    setImageBackground(yuedanComment,R.drawable.player_yue_comment);
                    setImageBackground(yuedanList,R.drawable.player_yuelist);
                    setFragment(describeFragment);
                    break;
                case R.id.yuedan_comment:
                    setImageBackground(yuedanDescribe,R.drawable.player_yue);
                    setImageBackground(yuedanComment,R.drawable.player_yue_comment_p);
                    setImageBackground(yuedanList,R.drawable.player_yuelist);
                    break;
                case R.id.yuedan_list:
                    setImageBackground(yuedanDescribe,R.drawable.player_yue);
                    setImageBackground(yuedanComment,R.drawable.player_yue_comment);
                    setImageBackground(yuedanList,R.drawable.player_yuelist_p);
                    setFragment(yueDanListFragment);
                    break;
            }
        }
    };
    private PlayVideoListener playVideoListener = new PlayVideoListener() {
        @Override
        public void playVideo(String url,String title) {
            videoplayer.setUp(url, title);
            videoplayer.ivThumb.performClick();
        }
    };
}

package com.github.yinyuetai.mv;

import com.github.yinyuetai.domain.MVListBean;
import com.github.yinyuetai.http.OkHttpManager;
import com.github.yinyuetai.http.callback.StringCallBack;
import com.github.yinyuetai.util.URLProviderUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import okhttp3.Call;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/6/7
 * YinYueTai
 */
public class MVItemFragmentPresenter implements MVItemFragmentContract.Presenter{
    private MVItemFragmentContract.View itemView;

    public MVItemFragmentPresenter(MVItemFragmentContract.View itemView) {
        this.itemView = itemView;
        this.itemView.setPresenter(this);
    }

    @Override
    public void getData(int offset, int size) {

    }

    @Override
    public void getData(int offset, int size, String areaCode) {
        itemView.showLoading();
        OkHttpManager.getOkHttpManager().asyncGet(URLProviderUtil.getMVListUrl(areaCode, offset, size), itemView, new StringCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                itemView.setError(e.getLocalizedMessage());
            }

            @Override
            public void onResponse(String response) {
                itemView.dismissLoading();
                if (response != null) {
                    try {
                        MVListBean mvListBean = new Gson().fromJson(response, MVListBean.class);
                        itemView.setData(mvListBean.getVideos());
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        itemView.setError(e.getLocalizedMessage());
                    }
                }else{
                    itemView.setError("");
                }
            }
        });
    }
}

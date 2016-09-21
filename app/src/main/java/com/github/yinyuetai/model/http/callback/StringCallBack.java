package com.github.yinyuetai.model.http.callback;

import okhttp3.Response;

/**
 * Created with com.github.tingolife.http.callback
 * User:YangXiuFeng
 * Date:2016/3/29
 * Time:18:46
 */
public abstract class StringCallBack extends CallBack<String> {
    @Override
    public String parseNetworkResponse(Response response) throws Exception {
        return response.body().string();
    }
}

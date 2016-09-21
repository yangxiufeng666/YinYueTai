package com.github.yinyuetai.model.http.callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created with com.github.tingolife.http.callback
 * User:YangXiuFeng
 * Date:2016/3/30
 * Time:9:05
 */
public abstract class CallBack<T> {
    /**
     * UI Thread
     *
     * @param request
     */
    public void onBefore(Request request)
    {
    }

    /**
     * UI Thread
     *
     * @param
     */
    public void onAfter()
    {
    }

    /**
     * UI Thread
     *
     * @param progress
     */
    public void inProgress(float progress)
    {

    }
    /**
     * Thread Pool Thread
     *
     * @param response
     */
    public abstract T parseNetworkResponse(Response response) throws Exception;

    public abstract void onError(Call call, Exception e);

    public abstract void onResponse(T response);
}

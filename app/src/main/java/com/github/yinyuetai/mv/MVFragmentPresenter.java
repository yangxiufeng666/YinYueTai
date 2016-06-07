package com.github.yinyuetai.mv;

import com.github.yinyuetai.domain.AreaBean;
import com.github.yinyuetai.http.OkHttpManager;
import com.github.yinyuetai.http.callback.StringCallBack;
import com.github.yinyuetai.util.URLProviderUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.Call;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/6/7
 * YinYueTai
 */
public class MVFragmentPresenter implements MVFragmentContract.Presenter{
    private MVFragmentContract.View mvView;

    public MVFragmentPresenter(MVFragmentContract.View mvView) {
        this.mvView = mvView;
        this.mvView.setPresenter(this);
    }

    @Override
    public void getData(int offset, int size) {
        OkHttpManager.getOkHttpManager().asyncGet(URLProviderUtil.getMVareaUrl(), mvView, new StringCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                mvView.setError(e.getLocalizedMessage());
            }

            @Override
            public void onResponse(String response) {
                //创建一个JsonParser
                JsonParser parser = new JsonParser();
                //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
                JsonElement el = null;
                ArrayList<AreaBean> areaBeanArrayList;
                try {
                    el = parser.parse(response);
                    //把JsonElement对象转换成JsonArray
                    JsonArray jsonArray = null;
                    if(el.isJsonArray()){
                        jsonArray = el.getAsJsonArray();
                    }
                    areaBeanArrayList = new ArrayList<>();
                    Iterator it = jsonArray.iterator();
                    while(it.hasNext()){
                        JsonElement e = (JsonElement)it.next();
                        //JsonElement转换为JavaBean对象
                        AreaBean field = new Gson().fromJson(e, AreaBean.class);
                        areaBeanArrayList.add(field);
                    }
                    mvView.setData(areaBeanArrayList);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    mvView.setError(e.getLocalizedMessage());
                }
            }
        });
    }
}

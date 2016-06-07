package com.github.yinyuetai.vchart;

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
public class VChartFragmentPresenter implements VChartFragmentContract.Presenter{
    private VChartFragmentContract.View chartView;

    public VChartFragmentPresenter(VChartFragmentContract.View chartView) {
        this.chartView = chartView;
        this.chartView.setPresenter(this);
    }

    @Override
    public void getData(int offset, int size) {
        OkHttpManager.getOkHttpManager().asyncGet(URLProviderUtil.getVChartAreasUrl(), chartView, new StringCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                chartView.setError(e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                //创建一个JsonParser
                JsonParser parser = new JsonParser();
                try {
                    //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
                    JsonElement el = parser.parse(response);
                    //把JsonElement对象转换成JsonArray
                    JsonArray jsonArray = null;
                    if(el.isJsonArray()){
                        jsonArray = el.getAsJsonArray();
                    }
                    ArrayList<AreaBean> areaBeanArrayList = new ArrayList<>();
                    Iterator it = jsonArray.iterator();
                    while(it.hasNext()){
                        JsonElement e = (JsonElement)it.next();
                        //JsonElement转换为JavaBean对象
                        AreaBean field = new Gson().fromJson(e, AreaBean.class);
                        areaBeanArrayList.add(field);
                    }
                    chartView.setData(areaBeanArrayList);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    chartView.setError(e.getMessage());
                }
            }
        });
    }
}

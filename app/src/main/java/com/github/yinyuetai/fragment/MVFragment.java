package com.github.yinyuetai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.yinyuetai.R;
import com.github.yinyuetai.adapter.MVViewPagerAdapter;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/10
 * YinYueTai
 */
public class MVFragment extends Fragment {
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.mv_pager)
    ViewPager mvPager;
    private View rootView;
    private ArrayList<AreaBean> areaBeanArrayList;
    private MVViewPagerAdapter pagerAdapter;
    private MaterialDialog.Builder builder;
    private MaterialDialog materialDialog;
    private void showLoading(){
        if (builder == null){

            builder = new MaterialDialog.Builder(getActivity());
            builder.cancelable(false);
            builder.title("等一下");
            builder.content("正在努力加载...")
                    .progress(true, 0);
        }
        materialDialog = builder.show();
    }
    private void dismissLoading(){
        materialDialog.dismiss();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.mv_page_fragment, container, false);
            ButterKnife.bind(this, rootView);
            initArea();
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    private void initArea(){
        showLoading();
        OkHttpManager.getOkHttpManager().asyncGet(URLProviderUtil.getMVareaUrl(), MVFragment.this, new StringCallBack() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                //创建一个JsonParser
                JsonParser parser = new JsonParser();
                //通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
                JsonElement el = null;
                try {
                    el = parser.parse(response);
                    //把JsonElement对象转换成JsonArray
                    JsonArray jsonArray = null;
                    if(el.isJsonArray()){
                        jsonArray = el.getAsJsonArray();
                    }
                    areaBeanArrayList = new ArrayList<AreaBean>();
                    Iterator it = jsonArray.iterator();
                    while(it.hasNext()){
                        JsonElement e = (JsonElement)it.next();
                        //JsonElement转换为JavaBean对象
                        AreaBean field = new Gson().fromJson(e, AreaBean.class);
                        areaBeanArrayList.add(field);
                    }
                    ArrayList<Fragment> fragments = new ArrayList<>();
                    for (AreaBean area :
                            areaBeanArrayList) {
                        fragments.add(MVViewPagerItemFragment.getInstance(area.getCode()));
                    }
                    initViewPager(fragments);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"error:"+response,Toast.LENGTH_SHORT).show();
                }
                dismissLoading();
            }
        });
    }
    private void initViewPager(ArrayList<Fragment> fragments){
        pagerAdapter = new MVViewPagerAdapter(getFragmentManager(),fragments,areaBeanArrayList);
        mvPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(mvPager);
    }
    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}

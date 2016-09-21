package com.github.yinyuetai.presenter;

import com.github.yinyuetai.model.domain.VideoBean;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/6/7
 * YinYueTai
 */
public interface MVItemFragmentContract {
    interface Presenter extends BasePresenter{
        void getData(int offset, final int size,String areaCode);
    }
    interface View extends BaseView<Presenter>{
        void setData(List<VideoBean> videosList);
        void setError(String msg);
        void showLoading();
        void dismissLoading();
    }
}

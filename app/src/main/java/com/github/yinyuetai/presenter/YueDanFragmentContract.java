package com.github.yinyuetai.presenter;

import com.github.yinyuetai.model.domain.YueDanBean;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/6/7
 * YinYueTai
 */
public interface YueDanFragmentContract {
    interface Presenter extends BasePresenter{

    }
    interface View extends BaseView<Presenter>{
        void setData(List<YueDanBean.PlayListsBean> data);
        void setError(String msg);
        void showLoading();
        void showProgress(boolean flag);
        void dismissLoading();
    }
}

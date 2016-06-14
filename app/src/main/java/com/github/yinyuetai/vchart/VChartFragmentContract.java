package com.github.yinyuetai.vchart;

import com.github.yinyuetai.BasePresenter;
import com.github.yinyuetai.BaseView;
import com.github.yinyuetai.domain.AreaBean;

import java.util.ArrayList;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/6/7
 * YinYueTai
 */
public class VChartFragmentContract {
    interface Presenter extends BasePresenter{

    }
    interface View extends BaseView<Presenter>{
        void setData(ArrayList<AreaBean> areaBeanArrayList);
        void setError(String msg);
    }
}

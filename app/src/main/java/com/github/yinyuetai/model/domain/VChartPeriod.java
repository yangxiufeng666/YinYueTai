package com.github.yinyuetai.model.domain;

import java.util.List;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/5/12
 * YinYueTai
 */
public class VChartPeriod {
    private List<Integer> years;
    /**
     * no : 19
     * year : 2016
     * dateCode : 20160502
     * beginDateText : 05.02
     * endDateText : 05.08
     */

    private List<PeriodsBean> periods;

    public List<Integer> getYears() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }

    public List<PeriodsBean> getPeriods() {
        return periods;
    }

    public void setPeriods(List<PeriodsBean> periods) {
        this.periods = periods;
    }

    public static class PeriodsBean {
        private int no;
        private int year;
        private int dateCode;
        private String beginDateText;
        private String endDateText;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getDateCode() {
            return dateCode;
        }

        public void setDateCode(int dateCode) {
            this.dateCode = dateCode;
        }

        public String getBeginDateText() {
            return beginDateText;
        }

        public void setBeginDateText(String beginDateText) {
            this.beginDateText = beginDateText;
        }

        public String getEndDateText() {
            return endDateText;
        }

        public void setEndDateText(String endDateText) {
            this.endDateText = endDateText;
        }
    }
}

package com.uhyils.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 数据的title工具类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2018年12月27日 09时41分
 */
public class DataTitleUtil {


    /**
     * 检查couldTitle是不是data的title
     *
     * @param couldTitle
     * @param data
     * @return
     */
    public static Boolean check(List<Object> couldTitle, List<List<Object>> data) {
        HashMap<Integer, ColInfo> map = new HashMap<>();
        //获取每一列的标准差和平均值
        for (int i = 0; i < couldTitle.size(); i++) {
            //如果有中文,则直接判定为title
            if (couldTitle.get(i) instanceof String) {
                return true;
            }

            ColInfo colInfo = new ColInfo();

            double sum = 0.0;
            for (int j = 1; j < data.size(); j++) {
                sum += (double) data.get(j).get(i);
            }
            colInfo.meanValue = sum / (data.size() - 1);
            //方差
            double variance = 0.0;
            for (int j = 1; j < data.size(); j++) {
                double v = (double) data.get(j).get(i) - colInfo.meanValue;
                variance += v * v;
            }
            variance = variance / (data.size() - 1);
            colInfo.standardDeviation = Math.sqrt(variance);
            map.put(i, colInfo);
        }
        //计算        |值 - 均值| / 标准差 平均偏离了几个标准差,如果平均偏离差大于给定的个数 则说明是title
        double sum = 0.0;
        for (int i = 0; i < couldTitle.size(); i++) {
            ColInfo colInfo = map.get(i);
            sum += Math.abs((double) couldTitle.get(i) - colInfo.meanValue) / colInfo.standardDeviation;
        }
        double result = sum / couldTitle.size();
        if (result > 2) {
            return true;
        } else {
            return false;
        }
    }

    private static class ColInfo {
        /**
         * 均值
         */
        private Double meanValue;
        /**
         * 标准
         */
        private Double standardDeviation;


        public Double getMeanValue() {
            return meanValue;
        }

        public void setMeanValue(Double meanValue) {
            this.meanValue = meanValue;
        }

        public Double getStandardDeviation() {
            return standardDeviation;
        }

        public void setStandardDeviation(Double standardDeviation) {
            this.standardDeviation = standardDeviation;
        }

    }
}

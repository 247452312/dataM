package com.uhyils.service.impl;

import com.uhyils.enums.DefectDataDealType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 数据挖掘数据清洗类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2018年12月25日 16时26分
 */
public class DataCleaning {

    /**
     * 数据缺失处理
     *
     * @param data     数据
     * @param dealType 缺失数据处理方式
     * @param dealMap  缺失数据列处理方式
     */
    public static List<List<Object>> DataDefect(List<List<Object>> data, DefectDataDealType dealType, HashMap<String, DefectDataDealType> dealMap) {
        List<List<Object>> tempData = new ArrayList<List<Object>>();
        if (data == null || data.size() < 2) {
            return null;
        }
        List<Object> title = tempData.get(0);
        tempData.add(title);
        out:
        for (int i = 1; i < data.size(); i++) {
            List<Object> list = data.get(i);
            List<Object> tempList = new ArrayList<Object>();
            for (int j = 0; j < list.size(); j++) {
                Object obj = list.get(j);
                if (obj != null) {
                    tempList.add(obj);
                } else {
                    DefectDataDealType type;
                    if ((type = dealMap.get(title.get(j))) != null) {
                        switch (type) {
                            case DELETE_DATA:
                                continue out;
                            case NOT_TO_DEAL:
                                tempList.add(obj);
                                break;
                            case FILL_ACERAGE:
                                tempList.add(getAcerage(data, j));
                                break;
                            case FILL_SIMILAR_ACERAGE:
                                tempList.add(getSimilarAcerage(data, j));
                                break;
                            default:
                                break;
                        }
                    } else {

                    }
                }
            }
            tempData.add(tempList);
        }

    }

    /**
     * 获取这一行数据最接近的数据的均值与波动值并加高斯分布
     *
     * @param data 原始数据
     * @param j    某一列
     * @return
     */
    private static Object getSimilarAcerage(List<List<Object>> data, int j) {
        for (int i = 1; i < data.size(); i++) {
            Boolean b = true;
            for (int k = 0; k < data.get(i).size(); k++) {
                if (k == j) {
                    continue;
                }


            }
        }
        return null;
    }

    private static Object getAcerage(List<List<Object>> data, int j) {
        Double sum = 0.0;
        Integer count = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get(j) != null) {
                sum += (Double) data.get(i).get(j);
                count++;
            }
        }
        return sum / count;
    }
}

package com.uhyils.service.impl;

import com.uhyils.core.DataPreprocessing;
import com.uhyils.core.DataTypeConverter;
import com.uhyils.core.OutliersRule;
import com.uhyils.core.key.DataToKey;
import com.uhyils.entity.Data;
import com.uhyils.enums.DefectDataDealType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2018年12月27日 10时48分
 */
public class DataPreprocessingImpl implements DataPreprocessing {

    /**
     * 缺失数据-数据预处理
     *
     * @param data         原始数据,可带有title,也可不带
     * @param allDealType  缺失数据处理方法
     * @param colDealTypes 部分行数据缺失处理方法(优先级较高)
     * @return 处理完成的数据(如果原始数据带有title, 则这里也有, 如果没有, 这里也没有)
     */
    @Override
    public void dealMissingData(Data data, DefectDataDealType allDealType, HashMap<Integer, DefectDataDealType> colDealTypes) {
        List<List<Object>> listData = data.getData();
        for (int i = 0; i < listData.size(); i++) {
            for (int j = 0; j < listData.get(0).size(); j++) {
                if (listData.get(i).get(j) == null || listData.get(i).get(j) instanceof String && ((String) listData.get(i).get(j)).isEmpty()) {
                    if (colDealTypes != null && colDealTypes.get(j) != null) {
                        Object missing = getMissing(listData, i, j, colDealTypes.get(j));
                        listData.get(i).set(j, missing);
                        System.out.println("第" + j + "个数据 为空,填充为" + missing);
                    } else {
                        Object missing = getMissing(listData, i, j, allDealType);
                        listData.get(i).set(j, missing);
                        System.out.println("第" + (i + 1) + "行" + (j + 1) + "个数据 为空,填充为" + missing);
                    }
                }
            }
        }
    }

    private Object getMissing(List<List<Object>> listData, int i, int j, DefectDataDealType allDealType) {
        //TODO 获取消失的数据 根据DefectDataDealType使用不同的决策,来填补消失的数据
        switch (allDealType) {
            case FILL_SIMILAR_ACERAGE:
                //TODO 根据局部信息填充数据
            case DELETE_DATA:
                return null;
            case NOT_TO_DEAL:
                return listData.get(i).get(j);
            case FILL_ACERAGE:
                int count = 0;
                double sum = 0.0;
                for (int a = 0; a < listData.size(); a++) {
                    if (a == j) {
                        continue;
                    }
                    Object o = listData.get(a).get(j);
                    if (o != null) {
                        sum += (double) o;
                        count++;
                    }
                }
                return sum / count;
            default:
                return null;
        }
    }

    /**
     * 异常值-数据预处理
     *
     * @param data             原始数据
     * @param outliersColRules 可能出现异常值的列的处理方式
     * @return
     */
    @Override
    public void dealOutliersData(Data data, HashMap<Object, OutliersRule> outliersColRules) throws Exception {
        List<List<Object>> listData = data.getData();
        //获取每一行
        for (int i = 0; i < listData.size(); i++) {
            //获取是第几列需要检测异常值
            for (Map.Entry<Object, OutliersRule> entry : outliersColRules.entrySet()) {
                Integer col = null;
                Object key = entry.getKey();
                //如果给定的列标识是String
                if (key instanceof String) {
                    List<Object> title = data.getTitle();
                    for (int j = 0; j < title.size(); j++) {
                        if (title.get(j).equals(key)) {
                            col = j;
                            break;
                        }
                    }
                    if (col == null) {
                        throw new Exception("列名没有找到!");
                    }
                } else {
                    //给定列标识是Integer
                    col = (Integer) key;
                }
                Object result = entry.getValue().inspectData(listData.get(i).get(col));
                listData.get(i).set(col, result);
            }
        }
    }

    /**
     * 离群值-数据预处理
     *
     * @param data          原始数据
     * @param noOutliersCol 不可能出现离群点的列(可以是Integer 也可以是String)
     * @return
     */
    @Override
    public void dealOutliersData(Data data, List<Object> noOutliersCol) throws Exception {
        List<Integer> noOutliersColInt = new ArrayList<>();
        out:
        for (Object o : noOutliersCol) {
            if (o instanceof Integer) {
                noOutliersColInt.add((Integer) o);
            } else {
                for (int i = 0; i < data.getTitle().size(); i++) {
                    Object o1 = data.getTitle().get(i);
                    if (o.equals(o1)) {
                        noOutliersColInt.add(i);
                        continue out;
                    }
                }
                throw new Exception("没找到列名:" + o);
            }
        }
        List<List<Object>> listData = data.getData();
        for (int i = 0; i < listData.get(0).size(); i++) {
            if (noOutliersColInt.contains(i)) {
                continue;
            }
        }
    }


    @Override
    public void dealDuplicateData(Data data, HashMap<Object, DataToKey> key) {
    }

    @Override
    public void dealDataType(Data data, HashMap<Object, DataTypeConverter> converters) {
    }

    @Override
    public void samplingEdgePoint(Data data) {
    }

    @Override
    public void samplingbalance(Data data, List<Object> col) {
    }

    @Override
    public void dataNormalization(Data data, List<Object> noNormalization, List<Object> gaussCol) {
    }

    @Override
    public void dealOfPCA(Data data, Object resultCol, List<Object> noAttribute) {
    }
}

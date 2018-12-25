package com.uhyils.service;

import com.uhyils.enums.DefectDataDealType;

import java.util.HashMap;
import java.util.List;

/**
 * 数据预处理
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2018年12月25日 18时05分
 */
public interface DataPreprocessing {

    /**
     * 缺失数据预处理
     *
     * @param data         原始数据,可带有title,也可不带
     * @param allDealType  缺失数据处理方法
     * @param colDealTypes 部分行数据缺失处理方法(优先级较高)
     * @return 处理完成的数据(如果原始数据带有title, 则这里也有, 如果没有, 这里也没有)
     */
    List<List<Object>> dealMissingData(List<List<Object>> data, DefectDataDealType allDealType, HashMap<Integer, DefectDataDealType> colDealTypes);

    /**
     * 离群值处理
     *
     * @param data          原始数据
     * @param noOutliersCol 不可能出现离群点的列(可以是Integer 也可以是String)
     * @return
     */
    List<List<Object>> dealOutliersData(List<List<Object>> data, List<Object> noOutliersCol);


}

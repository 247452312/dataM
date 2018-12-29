package com.uhyils.core;

import com.uhyils.core.key.DataToKey;
import com.uhyils.entity.Data;
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
     * 缺失数据-数据预处理
     *
     * @param data         原始数据,可带有title,也可不带
     * @param allDealType  缺失数据处理方法
     * @param colDealTypes 部分行数据缺失处理方法(优先级较高)
     * @return 处理完成的数据(如果原始数据带有title, 则这里也有, 如果没有, 这里也没有)
     */
    void dealMissingData(Data data, DefectDataDealType allDealType, HashMap<Integer, DefectDataDealType> colDealTypes);

    /**
     * 异常值-数据预处理
     *
     * @param data             原始数据
     * @param outliersColRules 可能出现异常值的列的处理方式
     * @return
     */
    void dealOutliersData(Data data, HashMap<Object, OutliersRule> outliersColRules) throws Exception;

    /**
     * 离群值-数据预处理
     *
     * @param data          原始数据
     * @param noOutliersCol 不可能出现离群点的列(可以是Integer 也可以是String)
     * @return
     */
    void dealOutliersData(Data data, List<Object> noOutliersCol) throws Exception;

    /**
     * 检测与删除重复数据
     *
     * @param data
     * @return
     */
    void dealDuplicateData(Data data, HashMap<Object, DataToKey> key);

    /**
     * 类型转换-数据预处理
     *
     * @param data
     * @return
     */
    void dealDataType(Data data, HashMap<Object, DataTypeConverter> converters);

    /**
     * 采样-获取边缘点
     *
     * @param data
     * @return
     */
    void samplingEdgePoint(Data data);

    /**
     * 采样-平衡某列的值使他们趋近于平衡
     *
     * @param data
     * @param col
     * @return
     */
    void samplingbalance(Data data, List<Object> col);

    /**
     * 数据标准化
     * <p>
     * 高斯数据使用公式 x = (数据 - 均值) /标准方差
     * </p>
     * <p>
     * 正常数据使用公式 x = (数据-最小值) / (最大值-最小值) * (目标最大值 - 目标最小值) + 目标最小值
     * </p>
     *
     * @param data            原始数据
     * @param noNormalization 不需要标准化的列
     * @param gaussCol        高斯数据列
     * @return
     */
    void dataNormalization(Data data, List<Object> noNormalization, List<Object> gaussCol);

    /**
     * 主成分分析(PCA)
     *
     * @param data        数据
     * @param resultCol   目标列
     * @param noAttribute 不能当做属性的列
     * @return
     */
    void dealOfPCA(Data data, Object resultCol, List<Object> noAttribute);


}

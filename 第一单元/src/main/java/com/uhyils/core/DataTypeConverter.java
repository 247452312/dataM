package com.uhyils.core;

/**
 * 类型转换
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2018年12月26日 12时56分
 */
public abstract class DataTypeConverter {
    /**
     * 数据类型,连续性数据(例如优良中差),或者不连续性数据(颜色,民族)
     */
    private Integer type;

    public abstract Object converterData(Object data);

}

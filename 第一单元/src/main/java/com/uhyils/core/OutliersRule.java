package com.uhyils.core;

import com.uhyils.entity.Data;

/**
 * 异常值的规则
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2018年12月26日 12时49分
 */
public interface OutliersRule {

    /**
     * 监测数据是否异常
     *
     * @param data 数据值
     * @return
     */
    Boolean inspectData(Object data);

    /**
     * 获取正常的值
     *
     * @param data
     * @return
     */
    Object getCorrectData(Data data);
}

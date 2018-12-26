package com.uhyils.core;

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
     * @param data
     * @return
     */
    Boolean inspectData(Object data);
}

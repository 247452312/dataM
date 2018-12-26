package com.uhyils.core.key;

/**
 * 每列数据如何转换为key
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2018年12月26日 13时03分
 */
public interface DataToKey {

    String getKey(Object object);
}

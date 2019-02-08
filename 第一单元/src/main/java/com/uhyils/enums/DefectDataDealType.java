package com.uhyils.enums;

/**
 * 缺失数据处理类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2018年12月25日 16时38分
 */
public enum DefectDataDealType {
    DELETE_DATA("删除数据行", 1),
    FILL_ACERAGE("填充均值", 2),
    FILL_SIMILAR_ACERAGE("填充局部均值", 3),
    NOT_TO_DEAL("不作处理", 4),
    FILL_MODE_NUM("填充众数",5);

    private String name;
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    DefectDataDealType(String name, Integer type) {
        this.name = name;
        this.type = type;
    }
}

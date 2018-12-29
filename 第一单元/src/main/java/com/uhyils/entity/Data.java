package com.uhyils.entity;

import com.uhyils.util.DataTitleUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 数据集
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2018年12月27日 09时32分
 */
public class Data {
    /**
     * 此数据有没有title
     */
    private boolean haveTitle;

    /**
     * 去掉title的数据
     */
    private List<List<Object>> data;

    /**
     * title
     */
    private List<Object> title;


    public Data(List<List<Object>> data) throws Exception {
        if (data == null || data.size() < 2) {
            throw new Exception("数据不能为空");
        }

        //获取第一行,看看是不是title
        List<Object> couldTitle = data.get(0);
        Boolean haveTitle = DataTitleUtil.check(couldTitle, data);
        this.haveTitle = haveTitle;
        if (haveTitle) {
            title = couldTitle;
            this.data = new ArrayList<>(data.size() - 1);

            for (int i = 1; i < data.size(); i++) {
                List<Object> list = data.get(i);
                this.data.add(list);
            }
        } else {
            this.data = data;
        }

    }

    public Data(boolean haveTitle, List<List<Object>> data, List<Object> title) {
        this.haveTitle = haveTitle;
        this.data = data;
        this.title = title;
    }

    public boolean isHaveTitle() {
        return haveTitle;
    }

    public void setHaveTitle(boolean haveTitle) {
        this.haveTitle = haveTitle;
    }

    public List<List<Object>> getData() {
        return data;
    }

    public void setData(List<List<Object>> data) {
        this.data = data;
    }

    public List<Object> getTitle() {
        return title;
    }

    public void setTitle(List<Object> title) {
        this.title = title;
    }
}

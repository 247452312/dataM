package com.uhyils.entity;

import com.uhyils.util.DataTitleUtil;

import java.util.*;

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


        HashMap<Integer, Integer> colCount = new HashMap<>();
        for (List<Object> datum : this.data) {
            if (colCount.keySet().contains(datum.size())) {
                colCount.put(datum.size(), colCount.get(datum.size()) + 1);
            } else {
                colCount.put(datum.size(), 1);
            }
        }
        if (colCount.size() > 1) {
            //多少列
            int max = 0;
            //出现的次数
            int count = 0;
            for (Map.Entry<Integer, Integer> entry : colCount.entrySet()) {
                if (entry.getValue() > count) {
                    count = entry.getValue();
                    max = entry.getKey();
                }
            }

            for (int i = 0; i < this.data.size(); i++) {
                List<Object> list = this.data.get(i);
                if (list.size() > max) {
                    List<Object> listTemp = new ArrayList<>();
                    for (int j = 0; j < max; j++) {
                        listTemp.add(list.get(j));
                    }
                    this.data.set(i, listTemp);
                } else if (list.size() < max) {
                    List<Object> listTemp = new ArrayList<>();
                    for (int j = 0; j < list.size(); j++) {
                        listTemp.add(list.get(j));
                    }
                    for (int j = list.size(); j < max; j++) {
                        listTemp.add(null);
                    }
                    this.data.set(i, listTemp);
                }
            }
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

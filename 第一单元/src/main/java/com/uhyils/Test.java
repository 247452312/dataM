package com.uhyils;

import com.uhyils.core.DataPreprocessing;
import com.uhyils.entity.Data;
import com.uhyils.enums.DefectDataDealType;
import com.uhyils.service.impl.DataPreprocessingImpl;
import com.uhyils.service.impl.ReadFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2019年01月06日 10时29分
 */
public class Test {
    public static void main(String[] args) throws Exception {
        List<List<Object>> lists = ReadFile.readFile("D:\\share\\ideaSrc\\dataM\\第一单元\\src\\main\\resources\\学生体测数据.xlsx", "数据");
        DataPreprocessing dataPreprocessing = new DataPreprocessingImpl();

        List<Object> list = lists.get(10);
        List<Object> listTemp = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            listTemp.add(list.get(i));
        }

        lists.set(10, listTemp);

        Data data = new Data(lists);

        //缺失值处理
        dataPreprocessing.dealMissingData(data, DefectDataDealType.FILL_ACERAGE, null);

        System.out.println(1);
    }
}

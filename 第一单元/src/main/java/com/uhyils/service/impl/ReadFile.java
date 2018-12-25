package com.uhyils.service.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取文件
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2018年12月25日 15时56分
 */
public class ReadFile {

    private static final String XLSX = "xlsx";
    private static final String XLS = "xls";

    public static List<List<Object>> readFile(String path, String sheetName) throws Exception {
        Workbook sheets = null;
        try {
            File file = new File(path);
            if (file.getName().endsWith(XLSX)) {
                sheets = new XSSFWorkbook(new FileInputStream(file));
            } else if (file.getName().endsWith(XLS)) {
                sheets = new HSSFWorkbook(new FileInputStream(file));
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("未找到 " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (sheets == null) {
            return null;
        }
        List<List<Object>> data = new ArrayList<List<Object>>();
        for (Sheet sheet : sheets) {
            String tempSheetName = sheet.getSheetName();
            if (tempSheetName.contains(sheetName)) {
                for (Row row : sheet) {
                    List<Object> list = new ArrayList<Object>();
                    for (Cell cell : row) {
                        switch (cell.getCellType()) {
                            case _NONE:
                                list.add(null);
                                break;
                            case BLANK:
                                list.add(0);
                                break;
                            case ERROR:
                                list.add(null);
                                break;
                            case STRING:
                                list.add(cell.getStringCellValue());
                                break;
                            case BOOLEAN:
                                list.add(cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                list.add(cell.getNumericCellValue());
                                break;
                            case NUMERIC:
                                list.add(cell.getNumericCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                    data.add(list);
                }
                return data;
            }
        }
        throw new Exception("未找到数据页");
    }

}

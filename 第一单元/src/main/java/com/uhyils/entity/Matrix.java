package com.uhyils.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 矩阵
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2018年12月26日 10时25分
 */
public class Matrix {

    private BigDecimal[][] data;

    private int colNum;
    private int rowNum;

    public Matrix(double[][] data) throws Exception {
        if (data == null) {
            throw new Exception("数据不能为空!");
        }
        rowNum = data.length;
        colNum = data[0].length;
        this.data = new BigDecimal[rowNum][colNum];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                this.data[i][j] = new BigDecimal(data[i][j]);
            }
        }
    }

    public Matrix(BigDecimal[][] data) throws Exception {
        if (data == null) {
            throw new Exception("数据不能为空!");
        }
        this.data = data;
        rowNum = data.length;
        colNum = data[0].length;
    }


    /**
     * 加
     *
     * @param matrix
     * @return
     * @throws Exception
     */
    public Matrix add(Matrix matrix) throws Exception {
        if (this.colNum != matrix.colNum || this.rowNum != matrix.rowNum) {
            throw new Exception("不能相加,行列不对!");
        }
        BigDecimal[][] b = new BigDecimal[rowNum][colNum];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                b[i][j] = data[i][j].add(matrix.data[i][j]);
            }
        }
        return new Matrix(b);
    }

    /**
     * 减
     *
     * @param matrix
     * @return
     * @throws Exception
     */
    public Matrix subtraction(Matrix matrix) throws Exception {
        if (this.colNum != matrix.colNum || this.rowNum != matrix.rowNum) {
            throw new Exception("不能相减,行列不对!");
        }
        BigDecimal[][] b = new BigDecimal[rowNum][colNum];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                b[i][j] = data[i][j].subtract(matrix.data[i][j]);
            }
        }
        return new Matrix(b);
    }

    /**
     * 乘法
     *
     * @param matrix
     * @return
     * @throws Exception
     */
    public Matrix multiply(Matrix matrix) throws Exception {
        if (this.colNum != matrix.rowNum) {
            throw new Exception("不能计算值,因为第一个矩阵的列数和第二个矩阵的行数不相等");
        }
        BigDecimal[][] b = new BigDecimal[this.rowNum][matrix.colNum];
        for (int i = 0; i < this.rowNum; i++) {
            for (int j = 0; j < matrix.colNum; j++) {
                BigDecimal bi = new BigDecimal(0);
                for (int k = 0; k < this.colNum; k++) {
                    bi.add(this.data[i][k].multiply(matrix.data[k][j]));
                }
                b[i][j] = bi;
            }
        }
        return new Matrix(b);
    }

    /**
     * 除法
     *
     * @param matrix
     * @return
     * @throws Exception
     */
    public Matrix division(Matrix matrix) throws Exception {
        if (this.colNum != matrix.rowNum) {
            throw new Exception("不能计算值,因为第一个矩阵的列数和第二个矩阵的行数不相等");
        }
        Matrix inverse = matrix.inverse();
        return this.multiply(inverse);
    }

    /**
     * 获得此矩阵的逆矩阵
     *
     * @return
     */
    public Matrix inverse() throws Exception {
        BigDecimal[][] reverseMartrix = getReverseMartrix(data);
        return new Matrix(reverseMartrix);
    }

    /**
     * 方阵求值
     *
     * @return
     * @throws Exception
     */
    public BigDecimal getDet() throws Exception {
        if (rowNum != colNum) {
            throw new Exception("不能求值,因为不是方阵!");
        }
        BigDecimal[][] a = data;
        BigDecimal result2 = new BigDecimal(0.0);
        if (a.length > 2) {
            //每次选择第一行展开
            for (int i = 0; i < a[0].length; i++) {
                //系数符号
                BigDecimal f = new BigDecimal(Math.pow(-1, i));
                //求余子式
                BigDecimal[][] yuzs = new BigDecimal[a.length - 1][a[0].length - 1];
                for (int j = 0; j < yuzs.length; j++) {
                    for (int j2 = 0; j2 < yuzs[0].length; j2++) {
                        //去掉第一行，第i列之后的行列式即为余子式
                        if (j2 < i) {
                            yuzs[j][j2] = a[j + 1][j2];
                        } else {
                            yuzs[j][j2] = a[j + 1][j2 + 1];
                        }

                    }
                }
                //行列式的拉普拉斯展开式，递归计算
                result2 = result2.add(a[0][i].multiply(new Matrix(yuzs).getDet()).multiply(f));
            }
        } else {
            //两行两列的行列式使用公式
            if (a.length == 2) {
                result2 = a[0][0].multiply(a[1][1]).subtract(a[0][1].multiply(a[1][0]));
            }
            //单行行列式的值即为本身
            else {
                result2 = a[0][0];
            }
        }
        return result2;
    }

    /**
     * 获得行列式的值
     *
     * @param bigDecimal
     * @return
     * @throws Exception
     */
    public BigDecimal getDet(BigDecimal[][] bigDecimal) throws Exception {
        Matrix matrix = new Matrix(bigDecimal);
        return matrix.getDet();
    }


    /**
     * 求(h,v)坐标的位置的余子式
     */
    public BigDecimal[][] getConfactor(BigDecimal[][] data, int h, int v) {
        int H = data.length;
        int V = data[0].length;
        BigDecimal[][] newdata = new BigDecimal[H - 1][V - 1];
        for (int i = 0; i < newdata.length; i++) {
            if (i < h - 1) {
                for (int j = 0; j < newdata[i].length; j++) {
                    if (j < v - 1) {
                        newdata[i][j] = data[i][j];
                    } else {
                        newdata[i][j] = data[i][j + 1];
                    }
                }
            } else {
                for (int j = 0; j < newdata[i].length; j++) {
                    if (j < v - 1) {
                        newdata[i][j] = data[i + 1][j];
                    } else {
                        newdata[i][j] = data[i + 1][j + 1];
                    }
                }
            }
        }

        return newdata;
    }

    /**
     * 获得逆矩阵
     *
     * @param data
     * @return
     * @throws Exception
     */
    private BigDecimal[][] getReverseMartrix(BigDecimal[][] data) throws Exception {
        BigDecimal[][] newdata = new BigDecimal[data.length][data[0].length];
        BigDecimal A = new Matrix(data).getDet();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if ((i + j) % 2 == 0) {
                    newdata[i][j] = getDet(getConfactor(data, i + 1, j + 1)).divide(A);
                } else {
                    newdata[i][j] = getDet(getConfactor(data, i + 1, j + 1)).negate().divide(A);
                }

            }
        }
        newdata = trans(newdata);
        return newdata;
    }

    private BigDecimal[][] trans(BigDecimal[][] newdata) {
        BigDecimal[][] newdata2 = new BigDecimal[newdata[0].length][newdata.length];
        for (int i = 0; i < newdata.length; i++)
            for (int j = 0; j < newdata[0].length; j++) {
                newdata2[j][i] = newdata[i][j];
            }
        return newdata2;
    }

    /**
     * 输出
     */
    public void printf() {
        for (BigDecimal[] datum : data) {
            for (BigDecimal bigDecimal : datum) {
                System.out.print(bigDecimal.doubleValue() + " ");
            }
            System.out.println();
        }
    }

    public BigDecimal[][] getData() {
        return data;
    }

    public void setData(BigDecimal[][] data) {
        this.data = data;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }
}

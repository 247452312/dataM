package formula;


/**
 * 公式类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2019年01月04日 23时08分
 */
public interface Formula {


    /**
     * 激励函数
     *
     * @param doubles
     * @return
     */
    Double getResult(Double doubles);


    /**
     * 求导后公式
     *
     * @param out 神经元实际输出
     * @return
     */
    Double getDerivatives(Double out);


    /**
     * 逆运算
     *
     * @param out 实际输出
     * @return 输入* 权重
     */
    Double inverseOperation(Double out);

}

package formula;


/**
 * 公式: Y=X
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2019年01月04日 23时10分
 */
public class FormulaU implements Formula {

    public Double getResult(Double doubles) {
        return doubles;
    }

    public Double getDerivatives(Double out) {
        return 1.0;
    }

    public Double inverseOperation(Double out) {
        return out;
    }
}

package formula;

/**
 * Y = X>=0 ? 1 : 0
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2019年01月05日 19时30分
 */
public class FormulaH implements Formula {
    public Double getResult(Double doubles) {
        if (doubles >= 0) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    public Double getDerivatives(Double out) {
        return 0.0;
    }

    public Double inverseOperation(Double out) {
        //TODO 不好说
        return null;
    }
}

package formula;

/**
 * Y = X>=0 ? AX : X
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2019年01月05日 19时33分
 */
public class FormulaP implements Formula {
    private Double a;

    public FormulaP(Double a) {
        this.a = a;
    }

    public Double getResult(Double doubles) {
        if (doubles >= 0) {
            return doubles;
        }
        return a * doubles;
    }

    public Double getDerivatives(Double out) {
        if (out >= 0) {
            return 1.0;
        }
        return a;
    }

    public Double inverseOperation(Double out) {
        if (out >= 0) {
            return out;
        }
        return out / a;
    }
}

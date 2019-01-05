package formula;

/**
 * 公式: Y = 1 / (1 + e^-x)
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2019年01月05日 11时16分
 */
public class FormulaS implements Formula {

    public Double getResult(Double doubles) {
        double k = Math.pow(Math.E, -doubles);
        return 1.0 / (1 + k);
    }

    public Double getDerivatives(Double out) {
        return out * (1.0 - out);
    }

    public Double inverseOperation(Double out) {
        return Math.log(out / (1 - out)) / Math.log(Math.E);
    }
}

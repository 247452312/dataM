package formula;

/**
 * 公式 Y = (e^x - e^-x) / (e^x + e^-x)
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2019年01月05日 18时46分
 */
public class FormulaT implements Formula {
    public Double getResult(Double doubles) {
        Double a = Math.pow(Math.E, doubles);
        Double b = Math.pow(Math.E, -doubles);
        return (a - b) / (a + b);
    }

    public Double getDerivatives(Double out) {
        Double result = getResult(out);
        return 1 - result * result;
    }

    public Double inverseOperation(Double out) {
        //TODO 算不出来
        return null;
    }
}

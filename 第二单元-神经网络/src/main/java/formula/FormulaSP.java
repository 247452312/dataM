package formula;

/**
 * Y = ln(1+e^x)
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2019年01月05日 19时42分
 */
public class FormulaSP implements Formula {
    public Double getResult(Double doubles) {
        return Math.log(1 + Math.pow(Math.E, doubles)) / Math.log(Math.E);
    }

    public Double getDerivatives(Double out) {
        return 1 / (1 + Math.pow(Math.E, -out));
    }

    public Double inverseOperation(Double out) {
        return Math.log(Math.pow(Math.E, out) - 1) / Math.log(Math.E);
    }
}

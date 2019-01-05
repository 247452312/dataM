package single.entity;


import formula.Formula;

import java.util.List;

/**
 * 神经元
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2019年01月04日 23时07分
 */
public class Neuron {

    /**
     * 权重
     */
    private List<Double> weights;

    /**
     * 激励函数
     */
    private Formula formula;

    /**
     * 学习率
     */
    private Double learnRate;

    public Neuron(List<Double> weights, Formula formula, Double learnRate) {
        this.weights = weights;
        this.formula = formula;
        this.learnRate = learnRate;
    }

    public List<Double> getWeights() {
        return weights;
    }

    public void setWeights(List<Double> weights) {
        this.weights = weights;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Double getLearnRate() {
        return learnRate;
    }

    public void setLearnRate(Double learnRate) {
        this.learnRate = learnRate;
    }

    /**
     * 获取输出
     *
     * @param doubles
     * @return
     * @throws Exception
     */
    public Double getOut(Double... doubles) throws Exception {
        if (doubles.length != weights.size() - 1) {
            throw new Exception("输入数量不对");
        }
        //W0 + W1 * X0 + W2 * X1 +...+ Wn * Xn-1
        Double result = new Double(0);
        //首先加入第一个权重
        result = result + weights.get(0);
        for (int i = 1; i < weights.size(); i++) {
            result = result + (weights.get(i) * (doubles[i - 1]));
        }
        //激励函数
        return formula.getResult(result);
    }

    /**
     * 获取输出(List)
     *
     * @param doubles
     * @return
     * @throws Exception
     */
    public Double getOut(List<Double> doubles) throws Exception {
        return getOut(doubles.toArray(new Double[]{}));
    }

    /**
     * 学习过程
     *
     * @param doubles 数据集
     * @param want    期望集
     * @throws Exception
     */
    public void learn(List<List<Double>> doubles, List<Double> want) throws Exception {
        if (doubles.size() != want.size()) {
            throw new Exception("期望数量和数据集数量不相等");
        }

        //以下4个变量都是记录学习进度的
        Long startTime = System.currentTimeMillis();
        /*↓↓↓↓↓将总数据集分为k份*/
        int k = 4;
        int size = doubles.size();
        int mark = 0;

        for (int i = 0; i < doubles.size(); i++) {
            List<Double> doubles1 = doubles.get(i);
            //实际输出
            Double out = getOut(doubles1);
            //误差
            Double difference = want.get(i) - out;
            //第一个权重修正为 W0 = W0 + (期望输出 - 实际输出) * 学习率 * 公式(实际输出) * 1
            weights.set(0, weights.get(0) + difference * learnRate * formula.getDerivatives(out) * 1.0);
            for (int j = 1; j < weights.size(); j++) {
                //其他的权重修正为 Wa = Wa + (期望输出 - 实际输出) * 学习率 * 公式(实际输出) * X(a-1)
                weights.set(j, weights.get(j) + difference * learnRate * formula.getDerivatives(out) * doubles1.get(j - 1));
            }
            if (i % (size / k) == 0) {
                System.out.println("学习进度:" + (mark++ / (k * 1.0)) * 100.0 + "%");
            }
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("学习耗时:" + (endTime - startTime) / 1000.0 + "秒");
    }


}

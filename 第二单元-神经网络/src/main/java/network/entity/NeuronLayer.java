package network.entity;

import single.entity.Neuron;

import java.util.ArrayList;
import java.util.List;

/**
 * 神经网络层
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2019年01月05日 15时31分
 */
public class NeuronLayer {

    /**
     * 神经元集
     */
    private List<Neuron> list;

    /**
     * 上一层
     */
    private NeuronLayer last;

    /**
     * 下一层
     */
    private NeuronLayer next;


    /**
     * 冗余变量,记录此次遍历的入参
     */
    private Double[] tempIn;

    /**
     * 冗余变量,记录此次遍历的出参
     */
    private Double[] tempOut;


    public NeuronLayer(List<Neuron> list) {
        this.list = list;
    }

    public List<Neuron> getList() {
        return list;
    }

    public void setList(List<Neuron> list) {
        this.list = list;
    }

    public NeuronLayer getLast() {
        return last;
    }

    public void setLast(NeuronLayer last) {
        this.last = last;
    }

    public NeuronLayer getNext() {
        return next;
    }

    public void setNext(NeuronLayer next) {
        this.next = next;
    }


    /**
     * 获取下一层的入参 或者结果
     *
     * @param data
     * @return
     */
    public Double[] getResult(Double... data) throws Exception {
        tempIn = data;
        Double[] result = new Double[list.size()];

        for (int i = 0; i < list.size(); i++) {
            Double out = list.get(i).getOut(data);
            result[i] = out;
        }

        tempOut = result;
        return result;
    }

    /**
     * 学习
     *
     * @param result 每一个神经元的误差
     * @return
     */
    public Double[] learn(Double[] result) {
        Double[] deviations = null;
        if (last != null) {

            // 要返回上一层的误差和权重的乘积的和               (结果)
            deviations = new Double[last.getList().size()];


            for (int i = 0; i < last.getList().size(); i++) {
                Double sum = 0.0;
                for (int j = 0; j < result.length; j++) {
                    sum += result[j] * list.get(j).getWeights().get(j + 1);
                }
                deviations[i] = sum;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            Neuron neuron = list.get(i);
            List<Double> weights = neuron.getWeights();
            weights.set(0, weights.get(0) + result[i] * neuron.getLearnRate() * neuron.getFormula().getDerivatives(tempOut[i]) * 1.0);
            for (int j = 1; j < weights.size(); j++) {
                weights.set(j, weights.get(j) + result[i] * neuron.getLearnRate() * neuron.getFormula().getDerivatives(tempOut[i]) * tempIn[j - 1]);
            }
        }

        return deviations;
    }
}

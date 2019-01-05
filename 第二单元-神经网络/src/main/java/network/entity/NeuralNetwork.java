package network.entity;

import formula.Formula;
import single.entity.Neuron;

import java.util.ArrayList;
import java.util.List;

/**
 * 神经网络
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2019年01月05日 15时34分
 */
public class NeuralNetwork {

    private List<NeuronLayer> list;

    public List<NeuronLayer> getList() {
        return list;
    }

    public void setList(List<NeuronLayer> list) {
        this.list = list;
    }


    /**
     * 学习的过程
     *
     * @param lists
     * @param want
     */
    public void learn(List<List<Double>> lists, List<List<Double>> want) throws Exception {
        for (int i = 0; i < lists.size(); i++) {
            //这次的实际输出 这里应该都是 length = 1
            Double[] result = getResult(lists.get(i).toArray(new Double[]{}));
            //这次的期望输出
            Double[] temp = new Double[want.get(i).size()];

            for (int j = 0; j < want.get(i).size(); j++) {
                temp[j] = want.get(i).get(j) - result[j];
            }
            for (int j = list.size() - 1; j >= 0; j--) {
                //当前次学习第j个(向前遍历)
                NeuronLayer neuronLayer = list.get(j);
                temp = neuronLayer.learn(temp);
            }
        }
    }

    /**
     * 获取结果
     *
     * @param data
     * @return
     */
    public Double[] getResult(Double... data) throws Exception {
        Double[] temp = data;
        for (NeuronLayer neuronLayer : list) {
            temp = neuronLayer.getResult(temp);
        }
        return temp;
    }


    /**
     * 构建神经网络(初始权重默认都是0) 默认1层隐含层
     *
     * @param formula     隐含层激励函数
     * @param overFormula 输出层激励函数
     * @param outCount    出参个数
     * @param inCount     入参个数
     * @param role        学习率
     * @return
     */
    public static NeuralNetwork structureNeuralNetwork(Formula overFormula, Integer outCount, Integer inCount, Double role, Formula... formula) {
        NeuralNetwork neuralNetwork = new NeuralNetwork();
        List<NeuronLayer> list = new ArrayList<NeuronLayer>();

        //创建隐含层神经元
        int count = (inCount + outCount) / 2;
        List<Neuron> neurons = new ArrayList<Neuron>(count);
        for (int i = 0; i < count; i++) {
            List<Double> weights = new ArrayList<Double>(inCount);
            for (int j = 0; j < inCount + 1; j++) {
                weights.add(0.0);
            }
            Neuron neuron = new Neuron(weights, formula[i], role);
            neurons.add(neuron);
        }
        NeuronLayer neuronLayer = new NeuronLayer(neurons);

        //创建输出层神经元
        List<Neuron> neuronsOver = new ArrayList<Neuron>(outCount);
        for (int i = 0; i < outCount; i++) {
            List<Double> weights = new ArrayList<Double>();
            for (int j = 0; j < count + 1; j++) {
                weights.add(0.0);
            }
            Neuron neuron = new Neuron(weights, overFormula, role);
            neuronsOver.add(neuron);
        }
        NeuronLayer neuronLayerOver = new NeuronLayer(neuronsOver);


        neuronLayer.setNext(neuronLayerOver);
        neuronLayerOver.setLast(neuronLayer);

        list.add(neuronLayer);
        list.add(neuronLayerOver);
        neuralNetwork.setList(list);
        return neuralNetwork;
    }
}

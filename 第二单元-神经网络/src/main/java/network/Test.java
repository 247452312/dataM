package network;

import formula.FormulaS;
import formula.FormulaU;
import network.entity.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2019年01月05日 17时33分
 */
public class Test {

    public static void main(String[] args) throws Exception {

        NeuralNetwork neuralNetwork = NeuralNetwork.structureNeuralNetwork(new FormulaU(), 1, 3, 0.01, new FormulaS(), new FormulaU());

        //初始化数据集
        List<List<Double>> list = new ArrayList<List<Double>>();
        List<List<Double>> want = new ArrayList<List<Double>>();
        //随机工具
        Random random = new Random();
        //10万个数据集
        for (int i = 0; i < 100000; i++) {
            List<Double> list_k = new ArrayList<Double>();
            list_k.add(new Double(random.nextInt(10)));
            list_k.add(new Double(random.nextInt(10)));
            list_k.add(new Double(random.nextInt(10)));
            // 期望为 2 +x1 + x2 - x3
            Double want_k = list_k.get(0) + list_k.get(1) - list_k.get(2);
            List<Double> temp_k = new ArrayList<Double>();
            temp_k.add(want_k);
            list.add(list_k);
            want.add(temp_k);
        }

        System.out.println("数据集构建完毕");

        neuralNetwork.learn(list, want);

        Double[] result = neuralNetwork.getResult(1.0, 1.0, 1.0);
        System.out.println(result[0]);

    }
}

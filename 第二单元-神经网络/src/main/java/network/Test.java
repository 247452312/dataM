package network;

import formula.Formula;
import formula.FormulaE;
import network.entity.Neuron;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2019年01月04日 23时31分
 */
public class Test {

    public static void main(String[] args) throws Exception {
        //初始化权重为0
        List<Double> weight = new ArrayList<Double>();
        for (int i = 0; i < 5; i++) {
            weight.add(0.0);
        }


        //创建神经网络节点, 激励函数为Y=1 / (1 + E^x)  学习率为0.01
        Formula formula = new FormulaE();
        Neuron neuron = new Neuron(weight, formula, new Double(0.1));

        //初始化数据集
        List<List<Double>> list = new ArrayList<List<Double>>();
        List<Double> want = new ArrayList<Double>();
        //随机工具
        Random random = new Random();
        //10万个数据集
        for (int i = 0; i < 100000; i++) {
            List<Double> list_k = new ArrayList<Double>();
            list_k.add(new Double(random.nextInt(10)));
            list_k.add(new Double(random.nextInt(10)));
            list_k.add(new Double(random.nextInt(10)));
            list_k.add(new Double(random.nextInt(10)));
            // 期望为 2 +x1 + x2 - x3
            Double want_k = formula.getResult(getResult(list_k.get(0), list_k.get(1), list_k.get(2), list_k.get(3)));
            list.add(list_k);
            want.add(want_k);
        }

        System.out.println("数据集构建完毕");


        //开始学习
        neuron.learn(list, want);

        //检测数据
        Double[] check = new Double[]{3.0, 4.0, 5.0, 2.0};
        //检验函数 实际中间值 即 W0 + W1*X1 +...
        Double out_temp = formula.inverseOperation(neuron.getOut(check));

        //期望中间值
        Double iWant = getResult(check);
        //检测和实际中间值的差距
        if (Math.abs((out_temp - iWant) / iWant) <= 0.1) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }

        System.out.print("计算结果为: " + out_temp.doubleValue());
        System.out.println("\t 期望中间值和实际中间值相差: " + new BigDecimal((out_temp.doubleValue() - iWant) * 100 / iWant).setScale(4, BigDecimal.ROUND_HALF_DOWN).doubleValue() + "%");
        List<Double> weights = neuron.getWeights();
        System.out.print("此时的权重为: ");
        for (int i = 0; i < weights.size(); i++) {
            System.out.print("  W" + i + " = " + String.format("%.2f", weights.get(i)));
        }
        System.out.println();
        System.out.print("此时的目标函数为: Y = " + String.format("%.2f", weights.get(0)));
        for (int i = 1; i < weights.size(); i++) {
            System.out.print(" + X" + (i - 1) + " * " + String.format("%.2f", weights.get(i)));
        }

    }

    /**
     * 目标函数
     *
     * @param doubles
     * @return
     */
    public static Double getResult(Double... doubles) {
        return 2 + doubles[0] * 1 + doubles[1] * 1 + doubles[2] * -1 + doubles[3] * -1;
    }
}

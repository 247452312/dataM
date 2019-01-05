package network;

import network.entity.neuron.Neuron;
import formula.FormulaE;

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
        for (int i = 0; i < 4; i++) {
            weight.add(0.0);
        }
        //创建神经网络节点, 权重默认都是0 , 激励函数为Y=X  学习率为0.0001
        Neuron neuron = new Neuron(weight, new FormulaE(), new Double(0.01));

        //初始化数据集
        List<List<Double>> list = new ArrayList<List<Double>>();
        List<Double> want = new ArrayList<Double>();
        //随机工具
        Random random = new Random();
        //100000个数据集
        for (int i = 0; i < 100000; i++) {
            List<Double> list_k = new ArrayList<Double>();
            list_k.add(new Double(random.nextInt(10)));
            list_k.add(new Double(random.nextInt(10)));
            list_k.add(new Double(random.nextInt(10)));
            // 期望为 x1 + x2 + x3 * 2 + 2
            Double want_k = list_k.get(0) + list_k.get(1) + list_k.get(2) * 3.0 + 2;
            list.add(list_k);
            want.add(want_k);
        }

        System.out.println("数据集构建完毕");

        try {
            //开始学习
            neuron.learn(list, want);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //检验函数 实际输出 应该是24
        Double out = neuron.getOut(3.0, 4.0, 5.0);

        //检测和实际输出差距
        if (Math.abs(out.doubleValue() - 24) <= 1) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }

        System.out.println("计算结果为: " + out.doubleValue());
        BigDecimal double_x = new BigDecimal((out.doubleValue() - 24) * 100 / 24.00);
        double_x = double_x.setScale(4, BigDecimal.ROUND_HALF_DOWN);
        System.out.println("相差: " + double_x.doubleValue() + "%");
        List<Double> weights = neuron.getWeights();
        System.out.println("此时的权重为: w1=" + weights.get(0) + " w2=" + weights.get(1) + " w3=" + weights.get(2) + " w4=" + weights.get(3));

    }
}

package com.uhyils;

/**
 * 无聊测试
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2018年12月27日 15时22分
 */
class Egg2 {
    protected class Yolk {
        public Yolk() {
            System.out.println("Egg2.Yolk();");
        }

        public void f() {
            System.out.println("Egg2.Yolk.f()");
        }
    }

    private Yolk y = new Yolk();

    public Egg2() {
        System.out.println("New Egg2()");
    }

    public void insertYolk(Yolk y) {
        this.y = y;
    }

    public void g() {
        y.f();
    }


}

public class BigEgg2 extends Egg2 {

    public class Yolk extends Egg2.Yolk {
        public Yolk() {
            System.out.println("BigEgg2.Yolk()");
        }

        public void f() {
            System.out.println("BigEgg2.Yolk.f()");
        }
    }

    public BigEgg2() {
        insertYolk(new Yolk());
    }

    public static void main(String[] args) {
        Egg2 egg2 = new BigEgg2();
        egg2.g();
        /**
         * 预计
         * Egg2.Yolk();
         * New Egg2();
         * Egg2.Yolk();
         * BigEgg2.Yolk()
         * BigEgg2.Yolk.f()
         */
    }
}

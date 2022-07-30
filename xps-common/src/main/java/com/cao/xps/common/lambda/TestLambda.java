package com.cao.xps.common.lambda;

/**
 * lambda表达式：
 *  接口只能存在一个函数
 */
public class TestLambda {

    //2.静态内部类
    class ILike implements Like{
        @Override
        public void lambda(int i) {
            System.out.println("I like lambda");
        }
    }
    public static void main(String[] args) {
        //3.局部内部类
        class ILike implements Like{
            @Override
            public void lambda(int i) {
                System.out.println("I like lambda");
            }
        }

        //默认方法
        ILike ilike = new ILike();
        ilike.lambda(1);

        //4.匿名内部类
        Like like= new Like() {
            @Override
            public void lambda(int i) {
                System.out.println("I like lambda"+i);
            }
        };
        like.lambda(1);

        //5.使用lambda表达式
        like = (int i)->{System.out.println("I like lambda"+i);};
        like.lambda(1);

        //6.lambda表达式（单个参数）
        like = a->{System.out.println("I like lambda"+a);};
        like.lambda(1);

        //7.lambda表达式（单个参数,单行代码）
        like = a->System.out.println("I like lambda"+a);;
        like.lambda(1);

    }
}
//定义一个函数接口
interface Like{
    void lambda(int i);
}

//1.定义一个类
class ILike implements Like{
    @Override
    public void lambda(int i) {
        System.out.println("I like lambda"+i);
    }
}

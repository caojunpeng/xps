package com.cao.xps.common.thread;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * 多线程方法-实现Callable 1.实现Callable接口;2.创建Thread对象;3.创建执行服务(ExecutorService);4.提交执行(submit);5.获取结果;6.结束服务
 * 优点：使用简便
 */
public class Thread3 implements Callable<Boolean> {


    @Override
    public Boolean call() throws Exception  {
        for (int i = 0; i < 20; i++) {
            System.out.println("线程："+i);
        }
        return true;
    }

    //主线程
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread3 t1 = new Thread3();
        Thread3 t2 = new Thread3();
        Thread3 t3 = new Thread3();
        FutureTask futureTask= new FutureTask(t3);
        new Thread(futureTask).start();
        //创建执行服务
        ExecutorService e =Executors.newFixedThreadPool(3);

        //提交执行
        Future<Boolean> r1 = e.submit(t1);
        Future<Boolean> r2 = e.submit(t2);
        Future<Boolean> r3 = e.submit(t3);

        //获取结果
        boolean b1 = r1.get();
        boolean b2 = r2.get();
        boolean b3 = r3.get();

        //结束服务
        e.shutdown();
    }


}

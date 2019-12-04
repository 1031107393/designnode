package com.hogan.designmode.singleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * TODO
 * wujun
 * 2019/12/02 23:04
 */
public class LazyInnerClassSingletonTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        /*CountDownLatch latch = new CountDownLatch(2);
        new Thread(new Process(latch)).start();
        new Thread(new Process(latch)).start();*/
        DemoBean singleton = SingletonEnum.getInstance();
        List lists = new ArrayList<>();
        lists.add(new DemoBean());
        singleton.setLists(lists);
        System.out.println(singleton.getLists());
        DemoBean copy = singleton.clone();
        System.out.println(copy.getLists());
        // 反射破坏单例
        /*try {
            Class clz =  Class.forName("com.hogan.designmode.singleton.LazyInnerClassSingleton");
            Constructor constructor = clz.getDeclaredConstructor();
            constructor.setAccessible(true);
            LazyInnerClassSingleton singleton2 = (LazyInnerClassSingleton) constructor.newInstance();
            Thread.sleep(1000);
            LazyInnerClassSingleton singleton1 = LazyInnerClassSingleton.getInstance();
            System.out.println(singleton1);
            System.out.println(singleton2);
            System.out.println(singleton1 == singleton2);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        // 序列化破坏单例
        /*
        LazyInnerClassSingleton singleton = LazyInnerClassSingleton.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.obj"));
        oos.writeObject(singleton);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.obj"));
        LazyInnerClassSingleton singleton1 = (LazyInnerClassSingleton) ois.readObject();
        System.out.println(singleton);
        System.out.println(singleton1);
        System.out.println(singleton == singleton1);*/
    }

    static class Process implements Runnable{

        private CountDownLatch latch;

        public Process(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            latch.countDown();
            DemoBean singleton = SingletonEnum.getInstance();
            List lists = new ArrayList<>();
            lists.add(new Object());
            singleton.setLists(lists);
            System.out.println(singleton.getLists());
            DemoBean copy = singleton.clone();
            System.out.println(copy.getLists());

            /*try {
             *//*Constructor constructor = SingletonEnum.class.getDeclaredConstructor(String.class, int.class);
                constructor.setAccessible(true);
                SingletonEnum singletonEnum = (SingletonEnum) constructor.newInstance("666", 666);
                DemoBean singleton2 = singletonEnum.getDemoBean();
                System.out.println(singleton2);*//*

            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }
    }
}

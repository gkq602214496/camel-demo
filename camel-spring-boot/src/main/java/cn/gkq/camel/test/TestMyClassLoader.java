package cn.gkq.camel.test;

import cn.gkq.camel.classloader.MyClassLoader;

/**
 * @author GKQ
 * @Classname TestMyClassLoader
 * @Description TODO
 * @Date 2021/3/5
 */
public class TestMyClassLoader {


    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader("F:\\360Downloads");
        MyClassLoader myClassLoader1 = new MyClassLoader("F:\\360Downloads");
        Class<?> aClass = myClassLoader.loadClass("cn.gkq.custom.TestU");
        System.out.println("main：" + aClass);
        new Thread(() -> {
            Thread.currentThread().setContextClassLoader(myClassLoader);
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            System.out.println(contextClassLoader);
            try {
                Class<?> aClass1 = contextClassLoader.loadClass("cn.gkq.custom.TestU");
                System.out.println(aClass1);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();

    }

}

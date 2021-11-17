package cn.gkq.camel.controller;

/**
 * @Author: liwuei
 * @Description:
 * @Date: 2021/8/30 10:25
 * @Version:
 */
public interface MessageContainer {



    /**
     * 必须实现toString方法对message进行序列化
     * @return  序列化结果
     */
    @Override
    String toString();
}

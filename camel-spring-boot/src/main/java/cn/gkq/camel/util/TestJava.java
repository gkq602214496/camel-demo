package cn.gkq.camel.util;

import cn.gkq.Necklet1;
import cn.gkq.webservice.impl.Necklet;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/8/13
 */
public class TestJava {

    static class Yak {

       private String name;

        {
            System.out.println("构造代码块");
        }

        public Yak() {
            System.out.println("Yak()");
        }

        public Yak(String name) {
            this.name = name;
            System.out.println("Yak(String name) ");
        }

        static {
            System.out.println("static");
        }
    }

    public static void main(String[] args) throws Exception {
        Yak yak = new Yak();
        Yak yak1 = new Yak();

        Class<?> aClass = Class.forName("cn.gkq.camel.util.TestJava");
        System.out.println(aClass);
    }
}

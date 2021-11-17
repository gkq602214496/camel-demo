package cn.gkq.camel.cxf;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.boot.SpringBootCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liulinchuan
 * @date 2021/8/11 10:19
 */
@Service
public class CxfService {

    @Autowired
    private SpringBootCamelContext camelContext;

    public void pulish() throws Exception {
        String name = "com.demo.base.cxf.BuildService";
        String src = "String buildTest(String action, String message);";
        buildClass(name, src);
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("cxf://http://localhost:9999/dynamicPublish?serviceClass=com.demo.base.cxf.BuildService&dataFormat=RAW&defaultOperationNamespace=http://webservice.cxf.base.demo.com/").process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);
                    exchange.getIn().setBody("test:" + body);
                    System.out.println(body);
                }).routeId("test1");
            }
        });
        testCamel();
    }

    public static void main(String[] args) throws Exception {
        String name = "com.demo.base.cxf.BuildService";
        String src = "String buildTest(String action, String message);";
//        buildClass(name, src);
    }

    public void testCamel() throws Exception {
        Thread.sleep(5000000);
    }

    public static Class buildClass( String name ,  String src ) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeInterface(name);
        CtMethod ctMethod = CtMethod.make(src, ctClass);
        ctClass.addMethod(ctMethod);
        ctClass.writeFile("D:\\bytecode");
        Class aClass = ctClass.toClass();
        System.out.println(aClass.getName());
        return aClass;
    }
}

package cn.gkq.camel.cxf;


import cn.gkq.camel.classloader.MyClassLoader;
import cn.gkq.camel.classloader.MyClassLoader1;
import cn.gkq.camel.util.ClassUtil;
import cn.gkq.webservice.WeatherService;
import cn.gkq.webservice.impl.Necklet;
import cn.gkq.webservice.impl.WeatherServiceImpl;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.camel.Exchange;
import org.apache.camel.Producer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.spring.boot.SpringBootCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * <p>CXF 示例</p>
 *
 * @author GKQ
 * @date 2021/3/25
 */

@RestController
@RequestMapping("/cxf")
public class CxfController {

    public static final String CXF_PREFIX = "cxf://";
    public static final String CXF_CLIENT_URL = "";
    @Autowired
    private SpringBootCamelContext camelContext;

    /**
     * CXF 服务端 作为camel路由里的入口（类似于起点）
     *
     * @param cxfProperty
     * @return
     * @throws Exception
     */
    @RequestMapping("/server")
    public String queryCxfServer(@RequestBody CxfProperty cxfProperty) throws Exception {


        CxfEndpoint cxfEndpoint = new CxfEndpoint();
        Producer producer = cxfEndpoint.createProducer();

        cxfEndpoint.setWsdlURL("/wsdl/queryWeather006.wsdl");
        cxfEndpoint.setAddress("http://localhost:8093/weather");
//        cxfEndpoint.setPortName("WeatherServiceImplPort");
//        cxfEndpoint.setServiceName("WeatherServiceImplService");
        //指定数据交互类型
        cxfEndpoint.setDataFormat(DataFormat.RAW);
        cxfEndpoint.setDefaultOperationNamespace("cn.gkq.dune");
        cxfEndpoint.setDefaultOperationName("test");
//        CxfProducer cxfProducer = new CxfProducer(cxfEndpoint);
//        cxfProducer.start();


        //注册camel到上下文
        camelContext.addEndpoint("cxfServer", cxfEndpoint);
        //注册到路由
        camelContext.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                RouteDefinition routeDefinition = from(cxfEndpoint).process(
                        exchange -> {
                            String parameter = exchange.getIn().getBody(String.class);
                            DefaultExchange defaultExchange = (DefaultExchange) exchange;
                            defaultExchange.getOut().setBody(parameter);
                        }
                );

            }

            /**
             * 定义聚合策略
             *
             * @param oldExchange the oldest exchange (is <tt>null</tt> on first aggregation as we only have the new exchange)
             * @param newExchange the newest exchange (can be <tt>null</tt> if there was no data possible to acquire)
             * @return {@link Exchange}
             */
            protected Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
                return oldExchange;
            }


        });


        return "ok";
    }

    @Autowired
    private CxfService cxfService;
    @Autowired
    private MyClassLoader1 myClassLoader1;

    public static void main(String[] args) throws Exception {
        ClassLoader myClassLoader1 = new MyClassLoader1();
        Class<?> aClass = myClassLoader1.loadClass("com.zdww.amc.hsb.bytecode.Ws$Test661");
        Method sendMessageA = aClass.getDeclaredMethod("sendMessageA", String.class, Integer.class);
        System.out.println(aClass);
        myClassLoader1= null;
        Method sendMessageA1 = aClass.getDeclaredMethod("sendMessageA", String.class, Integer.class);
        System.out.println(sendMessageA1);

    }

    @RequestMapping("/update")
    public String updateClass(String city) throws Exception {
        return null;
    }

    @RequestMapping("/raw")
    public String queryWeather2(String city) throws Exception {
        Class<?> aClass2 = ClassUtil.loadClass("D:\\bytecode", "com.zdww.amc.hsb.bytecode.Ws$Test661");
        String name = aClass2.getName();
//        String name = "com.zdww.amc.hsb.bytecode.Ws$Test661";
//        ClassLoader myClassLoader1 = new MyClassLoader1();
//        Class<?> aClass = myClassLoader1.loadClass("com.zdww.amc.hsb.bytecode.Ws$Test661");
//        System.out.println(aClass);
//        String name = aClass.getName();
//        String cxfInfo = "cxf://http://localhost:8091/weather?serviceClass="+name+"&defaultOperationNamespace=http://webservice.gkq.cn/";
        String cxfInfo = "cxf://http://localhost:8091/weather?serviceClass="+name;
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                RouteDefinition from = from(cxfInfo);
                from.process(
                        exchange -> {
                            String body = exchange.getIn().getBody(String.class);
                            body = "aaaaaa";
                            System.out.println(from);
                            System.out.println(body);
                            exchange.getOut().setBody(body);
                        }
                ).to("direct:cxf").process(exchange -> {
                    String body = exchange.getMessage().getBody(String.class);
                    System.out.println(body);
                });

//
//                from("direct:cxf").process(exchange -> {
//                    String body = exchange.getMessage().getBody(String.class);
//                    System.out.println(body);
//
//                });
            }
        });


        return "ok";
    }


    @RequestMapping("/raw1")
    public String queryWeather3(String city) throws Exception {
//        String cxfInfo = "cxf://http://localhost:8091/weather?wsdlURL=http://localhost:8080/hsb/v1/resource/download?contentKey=1418411283035082753&dataFormat=RAW&defaultOperationName=queryWeather&defaultOperationNamespace=http://webservice.gkq.cn/&username=admin&password=admin";
        String sss = TestCreate.getSSS();
        String cxfInfo = "cxf://http://localhost:8091/weather?serviceClass="+sss+"&dataFormat=CXF_MESSAGE&defaultOperationName=queryWeather&defaultOperationNamespace=http://webservice.gkq.cn/&username=admin&password=admin";
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {

                from("direct:cxf").process(exchange -> {
                    String body = exchange.getMessage().getBody(String.class);
                    System.out.println(body);
                    body = "bbbbb";
                    exchange.getMessage().setBody(body);

                });
            }
        });


        return "ok";
    }

    @RequestMapping("/payload")
    public String payload(String city) throws Exception {
        String cxfInfo = "cxf://http://localhost:8099/weather?wsdlURL=http://localhost:8099/weather?wsdl&dataFormat=PAYLOAD&defaultOperationName=queryWeather&defaultOperationNamespace=http://impl.webservice.gkq.cn/";
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("jetty://http://localhost:8088/payload").process(
                        exchange -> {
                            String body = exchange.getIn().getBody(String.class);
                            System.out.println(body);
                            exchange.getOut().setBody(body);
                        }
                ).to(cxfInfo);
            }
        });


        return "ok";
    }

    @RequestMapping("/get1")
    public String queryWeather1(String city) throws Exception {
        CxfEndpoint cxfEndpoint = new CxfEndpoint();
        cxfEndpoint.setAddress("http://localhost:8088/wwe");

        cxfEndpoint.setDataFormat(DataFormat.RAW);
//        cxfEndpoint.setCamelContext(camelContext);


        CxfEndpoint cxfEndpoint1 = new CxfEndpoint();
        cxfEndpoint1.setDataFormat(DataFormat.RAW);
        cxfEndpoint1.setCamelContext(camelContext);
        cxfEndpoint1.setServiceName("WeatherServiceImplService");
        cxfEndpoint1.setPortName("WeatherServiceImplPort");
        cxfEndpoint1.setDefaultOperationName("queryWeather");


        cxfEndpoint1.setWsdlURL("http://localhost:8099/weather?wsdl");
        cxfEndpoint1.setAddress("http://localhost:8099/weather");

        camelContext.addEndpoint("wwe", cxfEndpoint);
        camelContext.addEndpoint("wwe1", cxfEndpoint1);

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from(cxfEndpoint).process(exchange -> {
                    System.out.println(exchange);
                }).to(cxfEndpoint1);
            }
        });


//        String ccc = "cxf://http://localhost:8099/weather?wsdlURL=http://localhost:8099/weather?wsdl&dataFormat=RAW";
//
//
//
//        camelContext.addRoutes(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                from(ccc).process(
//                        exchange -> {
//                            String body = exchange.getIn().getBody(String.class);
//                            System.out.println(body);
//                        }
//                );
//            }
//        });


        return "ok";
    }


}

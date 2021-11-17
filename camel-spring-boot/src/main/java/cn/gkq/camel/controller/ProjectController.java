//package cn.gkq.camel.controller;
//
//import com.jayway.jsonpath.DocumentContext;
//import com.jayway.jsonpath.JsonPath;
//import org.apache.camel.Exchange;
//import org.apache.camel.Processor;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.spring.boot.SpringBootCamelContext;
//import org.apache.camel.support.DefaultExchange;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author GKQ
// * @description
// * @date 2021/5/12
// */
//@RestController
//@RequestMapping("/project")
//public class ProjectController {
//
//    @Autowired
//    private SpringBootCamelContext camelContext;
//
//    @RequestMapping("/save")
//    public String queryWeather2() throws Exception {
//        String cxfInfo = "cxf://http://localhost:8099/weather?wsdlURL=http://localhost:8099/weather?wsdl&dataFormat=PAYLOAD&loggingFeatureEnabled=true&defaultOperationName=queryWeather&defaultOperationNamespace=http://impl.webservice.gkq.cn/";
//
//        camelContext.addRoutes(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                from("jetty://http://localhost:8088/cxf")
//                        .to("direct:s");
//                        from("direct:s").process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        String parameter = exchange.getMessage().getBody(String.class);
//                        DefaultExchange defaultExchange = (DefaultExchange) exchange;
//                        defaultExchange.getOut().setBody(parameter);
//                    }
//                })
//                        .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        String body = exchange.getMessage().getBody(String.class);
//                        System.out.println(body);
//                    }
//                })
//                        .to(cxfInfo).process(
//                        exchange -> {
//                            String body = exchange.getIn().getBody(String.class);
//                            System.out.println(body);
//                            exchange.getMessage().setBody(body);
//
////                            DefaultExchange defaultExchange = (DefaultExchange) exchange;
////                            defaultExchange.getOut().setBody(body);
//                        }
//                );
//            }
//        });
//
//
//        return "ok";
//    }
//
//    /**
//     * 字典转换
//     *
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("/dict/transfer")
//    public String dictTransfer() throws Exception {
//        String routeInfo = "jetty://http://localhost:8088/dict";
//        camelContext.addRoutes(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                from(routeInfo).process(
//                        exchange -> {
//                            String body = exchange.getIn().getBody(String.class);
//                            System.out.println(body);
//                            DocumentContext context = JsonPath.parse(body);
//                            Object read = context.read("$.store.book[*].price.gender[*].sex");
//                            context.map("$.store.book[*].price.gender[*].sex", (currentValue, configuration) -> "撒发射点发生");
//                            String s = context.jsonString();
//                            System.out.println(s);
//                            exchange.getOut().setBody(body);
//                        }
//
//                );
//            }
//        });
//
//
//        return "ok";
//    }
//
//}

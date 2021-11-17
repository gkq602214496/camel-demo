//package cn.gkq.camel.controller;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.camel.ExchangePattern;
//import org.apache.camel.Message;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.model.RouteDefinition;
//import org.apache.camel.spring.boot.SpringBootCamelContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * <p></p>
// *
// * @author GKQ
// * @date 2021/6/4
// */
//@RestController
//@RequestMapping("/activemq")
//@Api(value = "Odin控制器")
//public class ActiveMqController {
//
//
//    public static final String ACTIVEMQ_PREFIX_URI = "activemq:";
//    public static final String AND_SEPARATOR = "&";
//
//
//    @Autowired
//    private SpringBootCamelContext camelContext;
//    @Autowired
//    private DefaultListableBeanFactory defaultListableBeanFactory;
//
//
//    @PostMapping("/test/uri")
//    @ApiOperation(value = "测试activemq")
//    public String testHttp() throws Exception {
//
//        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
//        activeMQConnectionFactory.setBrokerURL("tcp://47.119.148.27:61617");
//        activeMQConnectionFactory.setUserName("admin");
//        activeMQConnectionFactory.setPassword("admin");
//
//
//        ActiveMQConnectionFactory activeMQConnectionFactory1 = new ActiveMQConnectionFactory();
//        activeMQConnectionFactory1.setBrokerURL("tcp://47.119.148.27:22");
//        activeMQConnectionFactory1.setUserName("admin1");
//        activeMQConnectionFactory1.setPassword("admin1");
//
//        defaultListableBeanFactory.registerSingleton("111", activeMQConnectionFactory);
//        defaultListableBeanFactory.registerSingleton("222", activeMQConnectionFactory1);
//
//        Object factory = defaultListableBeanFactory.getBean("111");
//        Object factory1 = defaultListableBeanFactory.getBean("222");
//
//        System.out.println("done!");
//
//        String factory111 = "111";
//        String queue = "myQueue";
//        String username = "admin";
//        String password = "admin";
//        String disableReplyTo = "false";
//        StringBuilder sb = new StringBuilder(ACTIVEMQ_PREFIX_URI);
//        sb.append("queue:").append(queue).append("?")
//                .append("connectionFactory=").append(factory111)
////                .append(AND_SEPARATOR).append("replyTo=").append("queue2")
////                .append(AND_SEPARATOR).append("useMessageIDAsCorrelationID=").append("true")
//                .append(AND_SEPARATOR).append("disableReplyTo=").append("true");
//
//
//        String routeInfo = "jetty://http://localhost:8088/mq";
//        String mqInfo = sb.toString();
//        camelContext.addRoutes(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                RouteDefinition from = from(routeInfo);
////                from.process(exchange -> {
////                    String body = exchange.getMessage().getBody(String.class);
////                    ExchangePattern pattern = exchange.getPattern();
////                    Message message = exchange.getMessage();
////                    message.setBody(body);
////                });
//                from.to(mqInfo);
//
//            }
//        });
//
//
//        return "hello rabbitmq";
//    }
//
//    @PostMapping("/consume")
//    @ApiOperation(value = "消费activemq队列消息")
//    public String consume() throws Exception {
//
//
//        String factory = "activeMQConnectionFactory";
//        String queue = "myQueue";
//        String username = "admin";
//        String password = "admin";
//        String disableReplyTo = "false";
//        StringBuilder sb = new StringBuilder(ACTIVEMQ_PREFIX_URI);
//        sb.append("queue:").append(queue).append("?")
//                .append("connectionFactory=").append(factory)
//                .append(AND_SEPARATOR).append("disableReplyTo=").append(disableReplyTo);
//
//
//        String routeInfo = "jetty://http://localhost:8088/mq";
//        String mqInfo = sb.toString();
//        camelContext.addRoutes(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                RouteDefinition from = from(mqInfo);
//                from.process(exchange -> {
//                    String body = exchange.getMessage().getBody(String.class);
//                    ExchangePattern pattern = exchange.getPattern();
//                    System.out.println(body);
//                });
//
//
//            }
//        });
//
//
//        return "hello rabbitmq";
//    }
//
//}

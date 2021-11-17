//package cn.gkq.camel.controller;
//
//import cn.gkq.camel.log.CustomExchangeFormatter;
//import com.rabbitmq.client.ConnectionFactory;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.apache.camel.ExchangePattern;
//import org.apache.camel.Message;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.component.log.LogComponent;
//import org.apache.camel.model.RouteDefinition;
//import org.apache.camel.spring.boot.SpringBootCamelContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//
///**
// * <p></p>
// *
// * @author GKQ
// * @date 2021/6/1
// */
//@RestController
//@RequestMapping("/rabbit")
//@Api(value = "Odin控制器")
//public class RabbitMQController {
//
//    @Autowired
//    private SpringBootCamelContext camelContext;
//
//    @PostMapping("/test/uri")
//    @ApiOperation(value = "测试rabbitmq")
//    public String testHttp() throws Exception {
//        String routeInfo = "jetty://http://localhost:8088/mq";
//
//        String mqInfo = "rabbitmq:dune?hostname=47.119.148.27&portNumber=5672" +
//                "&username=guest&password=guest&autoDelete=false" +
//                "&exchangeType=topic&queue=ufc&vhost=ufc&requestTimeout=6000&exchangePattern=InOnly&routingKey=www";
//
//        String sourceInfo = "rabbitmq:dune?hostname=47.119.148.27&portNumber=5672&queue=ufc&routingKey=www&autoDelete=false";
//
//
//        camelContext.addRoutes(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                LogComponent logComponent = new LogComponent();
//                logComponent.setExchangeFormatter(new CustomExchangeFormatter());
//                try {
//                    camelContext.addComponent("log", logComponent);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("hello world");
//                RouteDefinition from = from(routeInfo);
//                from.to("log:cn.gkq?level=info&showExchangeId=true").process(exchange -> {
//                    String body = exchange.getMessage().getBody(String.class);
//                    Message message = exchange.getMessage();
////                    message.setHeader("CamelRabbitmqReplyTo", "ufc123");
////                    message.setHeader("CamelRabbitmqPriority", "0");
////                    message.setHeader("CamelRabbitmqExchangeName", "dune");
////                    message.setHeader("CamelRabbitmqRoutingKey", "dune.wwe");
//                    message.setBody(body);
//                });
//                from.to(mqInfo);
//
//            }
//        });
//
//
//        return "hello rabbitmq";
//    }
//
//    @PostMapping("/test/uri1")
//    @ApiOperation(value = "测试rabbitmq")
//    public String testHttp1() throws Exception {
//        String routeInfo = "jetty://http://localhost:8088/mq";
//
//        String mqInfo = "rabbitmq:dune?hostname=47.119.148.27&portNumber=5672" +
//                "&username=guest&password=guest&autoDelete=false" +
//                "&queue=ufc&vhost=ufc&routingKey=www";
//
//        String sourceInfo = "rabbitmq:dune?hostname=47.119.148.27&portNumber=5672&username=guest&password=guest&queue=ufc&routingKey=www&autoDelete=false&exchangeType=direct&vhost=ufc";
//
//
//        camelContext.addRoutes(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                System.out.println("hello get");
//                from(sourceInfo).process(exchange -> {
//                    String body = exchange.getMessage().getBody(String.class);
//                    System.out.println(body);
//                });
//
//            }
//        });
//        return "hello rabbitmq";
//    }
//
//
//}

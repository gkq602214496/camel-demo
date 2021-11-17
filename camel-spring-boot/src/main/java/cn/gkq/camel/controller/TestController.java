//package cn.gkq.camel.controller;
//
//import cn.gkq.camel.pojo.dto.CamelDTO;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.spring.boot.SpringBootCamelContext;
//import org.apache.camel.support.DefaultMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.sql.DataSource;
//import java.util.Map;
//
///**
// * @author GKQ
// * @Classname TestController
// * @Description TODO
// * @Date 2021/4/9
// */
//@RestController
//@RequestMapping("/test")
//public class TestController {
//
//    @Autowired
//    private SpringBootCamelContext camelContext;
//    @Autowired
//    private DataSource dataSource;
//
//    @RequestMapping(value = "/get", method = RequestMethod.POST)
//    public void testJDBC() throws Exception {
//        camelContext.addRoutes(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//
//                camelContext.getRegistry().bind("datasource", dataSource);
//
//                from("jetty:http://localhost:20660/start").group("jdbc").routeId("jetty-001").to("direct:jdbc-001");
//
//                from("direct:jdbc-001").group("jdbc").routeId("jdbc-001").process(exchange -> {
//                    String body = exchange.getIn().getBody(String.class);
//                    Object parse = JSONObject.parse(body);
//                    DefaultMessage defaultMessage = (DefaultMessage) exchange;
//
//
//                }).setBody(simple("select * from t_user where key_id = '${body[keyId]}'")).to("jdbc:myDataSource").process(exchange -> {
//                    String body = exchange.getMessage().getBody(String.class);
//                    System.out.println(body);
//                    exchange.getOut().setBody(body);
//                });
//
//            }
//        });
//
//
//    }
//
//}

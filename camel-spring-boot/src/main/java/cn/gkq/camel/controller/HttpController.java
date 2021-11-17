//package cn.gkq.camel.controller;
//
//import cn.gkq.camel.hsb.route.HttpRouteBuilder;
//import cn.gkq.camel.hsb.route.JDBCRouteBuilder;
//import cn.gkq.camel.pojo.dto.RouteDTO;
//import cn.gkq.camel.util.RouteType;
//import com.google.common.collect.Lists;
//import org.apache.camel.Exchange;
//import org.apache.camel.ExchangePattern;
//import org.apache.camel.Processor;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.spring.boot.SpringBootCamelContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.sql.DataSource;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author GKQ
// * @Classname HttpController
// * @Description TODO
// * @Date 2021/3/25
// */
//@RequestMapping("/http")
//@RestController
//public class HttpController {
//
//    @Autowired
//    private SpringBootCamelContext camelContext;
//    @Autowired
//    private DataSource dataSource;
//
//    @RequestMapping("/test1")
//    public String testCamelRoute1() throws Exception {
//        camelContext.addRoutes(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//
//                from("direct:003").process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        System.out.println("direct:003" + exchange);
//                    }
//                });
//
//                from("jetty:http://localhost:8099/start").to("direct:002");
//
//
//
//                from("direct:002").process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        System.out.println("direct:002" + exchange);
//
//                    }
//                }).to("direct:003");
//
//
//
//            }
//        });
//        return "ok";
//    }
//
//    @RequestMapping("/test")
//    public String testCamelRoute(RouteDTO routeDTO) throws Exception {
////        List<RouteBuilder> builders1 =  RouteBuilderUtils.transform(routeDTO);
////        //组装RouteBuilder数据
////        List<RouteBuilder> builders = assembleRouteBuilder();
////        for (RouteBuilder var0 : builders) {
////            camelContext.addRoutes(var0);
////        }
//        camelContext.addRoutes(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                from("jetty://http://localhost:8099/start").process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        System.out.println(exchange);
//                        HttpServletRequest httpServletRequest = exchange.getIn().getBody(HttpServletRequest.class);
//                        String username = (String) httpServletRequest.getParameter("username");
//                        exchange.getMessage().setHeader("username", constant(username));
//                        String sql = "SELECT * FROM t_user WHERE username = " + "'" + username + "'";
//                        exchange.getMessage().setBody(sql);
//                    }
//                }).to("jdbc:myDataSource?outputType=StreamList").process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        List body = exchange.getIn().getBody(List.class);
//                        System.out.println(body);
//                        exchange.getMessage().setBody(body);
//                    }
//                });
//            }
//        });
//        return "ok";
//    }
//
//    private List<RouteBuilder> assembleRouteBuilder() {
//        ArrayList<RouteBuilder> list = Lists.newArrayList();
//        //设置HTTP组件
//        String varJetty = "jetty://http://localhost:8099/start";
//        String varJdbc = "jdbc:myDataSource";
//        HttpRouteBuilder httpRouteBuilder = new HttpRouteBuilder();
//        List<String> targetIds = Lists.newArrayList();
//        targetIds.add("jdbc:002");
//        httpRouteBuilder.setSourceId("jetty:001");
//        httpRouteBuilder.setMode(ExchangePattern.InOut.name());
//        httpRouteBuilder.setType(RouteType.HTTP.getCode());
//        httpRouteBuilder.setTargetIds(targetIds);
//        JDBCRouteBuilder jdbcRouteBuilder = new JDBCRouteBuilder();
//        //设置JDBC组件
//        jdbcRouteBuilder.setSourceId("jdbc:002");
//        jdbcRouteBuilder.setType(RouteType.JDBC.getCode());
//        jdbcRouteBuilder.setRegisterMsg(varJdbc);
//        list.add(httpRouteBuilder);
//        list.add(jdbcRouteBuilder);
//        return list;
//    }
//
//}

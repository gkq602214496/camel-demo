//package cn.gkq.camel.hsb.route;
//
//import org.apache.camel.Exchange;
//import org.apache.camel.Processor;
//import org.apache.camel.model.RouteDefinition;
//
///**
// * @author GKQ
// * @Classname JDBCRouteBuilder
// * @Description JDBC 组件
// * @Date 2021/3/26
// */
//public class JDBCRouteBuilder extends BaseRouteBuilder  {
//
//    @Override
//    public Boolean isFirst() {
//        return false;
//    }
//
//    @Override
//    public RouteDefinition handle(RouteDefinition routeDefinition) {
//        routeDefinition.process(new Processor() {
//            @Override
//            public void process(Exchange exchange) throws Exception {
//
//            }
//        });
//        return routeDefinition;
//    }
//
//    @Override
//    public void destinations(RouteDefinition routeDefinition) {
//        for (String var0 : targetIds) {
//            routeDefinition.to(var0);
//        }
//    }
//}

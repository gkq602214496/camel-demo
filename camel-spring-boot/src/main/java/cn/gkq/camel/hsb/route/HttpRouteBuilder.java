//package cn.gkq.camel.hsb.route;
//
//import cn.gkq.camel.util.CustomExchangePattern;
//import cn.gkq.camel.util.RouteType;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import org.apache.camel.Exchange;
//import org.apache.camel.Processor;
//import org.apache.camel.model.RouteDefinition;
//
///**
// * @author GKQ
// * @Classname HttpRouteBuilder
// * @Description TODO
// * @Date 2021/3/26
// */
//@Data
//@EqualsAndHashCode(callSuper = true)
//public class HttpRouteBuilder extends BaseRouteBuilder {
//
//    @Override
//    public Boolean isFirst() {
//        //此时为监听模式
//        return (RouteType.HTTP.getCode().equalsIgnoreCase(type) &&
//                (CustomExchangePattern.IN.getCode().equalsIgnoreCase(mode)
//                        || CustomExchangePattern.IN_OUT.getCode().equalsIgnoreCase(mode)));
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
//
//}

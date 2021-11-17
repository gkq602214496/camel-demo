//package cn.gkq.camel.hsb.route;
//
//import cn.gkq.camel.util.RouteType;
//import lombok.Data;
//import lombok.experimental.Accessors;
//import org.apache.camel.Exchange;
//import org.apache.camel.ExchangePattern;
//import org.apache.camel.Processor;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.model.RouteDefinition;
//
//import java.util.List;
//
///**
// * @author GKQ
// * @Classname BaseRouteBuilder
// * @Date 2021/3/26
// */
//@Data
//public abstract class BaseRouteBuilder extends RouteBuilder {
//
//    public static final String PREFIX_DIRECT = "direct:";
//
//    /**
//     * 注册路由信息
//     */
//    private String registerMsg;
//
//    /**
//     * 组件ID
//     */
//    private String sourceId;
//
//    /**
//     * 决策监听入口
//     */
//    public abstract Boolean isFirst();
//
//    /**
//     * 组件类型（参考枚举类RouteType）
//     */
//    protected String type;
//    /**
//     * MEP
//     */
//    protected String mode;
//
//    /**
//     * 下游目标ID集合
//     */
//    protected List<String> targetIds;
//
//    @Override
//    public void configure()  {
//        RouteDefinition routeDefinition = entrance();
//        handle(routeDefinition);
//        destinations(routeDefinition);
//    }
//
//    public final RouteDefinition entrance() {
//        //此时为监听模式
//        if (isFirst()) {
//            return from(registerMsg);
//        } else {
//            return from(PREFIX_DIRECT + sourceId).to(registerMsg);
//        }
//    };
//
//    public abstract RouteDefinition handle(RouteDefinition routeDefinition);
//
//    public abstract void destinations(RouteDefinition routeDefinition);
//}

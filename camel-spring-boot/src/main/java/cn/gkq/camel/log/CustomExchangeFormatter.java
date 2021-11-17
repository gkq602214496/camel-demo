//package cn.gkq.camel.log;
//
//import org.apache.camel.Exchange;
//import org.apache.camel.spi.ExchangeFormatter;
//
///**
// * <p></p>
// *
// * @author GKQ
// * @date 2021/6/16
// */
//public class CustomExchangeFormatter implements ExchangeFormatter {
//
//    @Override
//    public String format(Exchange exchange) {
//        String body = exchange.getMessage().getBody(String.class);
//        System.out.println(exchange);
//        return "ok";
//    }
//
////    public static void main(String[] args) {
////        System.out.println(System.lineSeparator());
////        System.out.println(System.lineSeparator().length());
////    }
//}

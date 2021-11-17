//package cn.gkq.camel.config;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.*;
//
///**
// * @author GKQ
// * @Classname FeignConfiguration
// * @Description TODO
// * @Date 2021/2/2
// */
//public class FeignConfiguration implements RequestInterceptor {
//
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            Enumeration<String> values = request.getHeaders(headerName);
//            // 跳过 content-length
////            if (headerName.equals("content-length")){
////                continue;
////            }
//            while (values.hasMoreElements()) {
//                String value = values.nextElement();
//                requestTemplate.header(headerName, value);
//            }
//        }
//
//    }
//}

//package cn.gkq.camel.cxf;
//
//import org.apache.cxf.Bus;
//import org.apache.cxf.bus.spring.SpringBus;
//import org.apache.cxf.jaxws.EndpointImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.ws.Endpoint;
//
///**
// * @author GKQ
// * @Classname CxfConfig
// * @Description TODO
// * @Date 2021/3/26
// */
//
//@Configuration
//public class CxfConfig  {
//
//    @Autowired
//    private TestService testService;
//
//    @Bean
//    public Bus bus() {
//        Bus bus = new SpringBus();
//        return bus;
//    }
//
//}

package cn.gkq.camel.cxf;

import cn.gkq.webservice.WeatherService;
import cn.gkq.webservice.impl.WeatherServiceImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/5/25
 */
public class CxfPublish {


    public static void main(String[] args) throws IOException {
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setAddress("http://172.16.110.174:8099/weather");
        factory.setServiceClass(WeatherService.class);
        factory.setServiceBean(new WeatherServiceImpl());
        factory.getInInterceptors().add(new AuthInterceptor());
        factory.create();

    }



}

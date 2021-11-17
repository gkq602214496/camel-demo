package cn.gkq.webservice;


import cn.gkq.webservice.impl.Necklet;
import cn.gkq.webservice.impl.WeatherServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * @author GKQ
 * @Classname PublishService
 * @Description TODO
 * @Date 2021/3/25
 */
public class PublishService {

    public static void main(String[] args) {
        String address = "http://localhost:8098/weather";
        WeatherService weatherService = new WeatherServiceImpl();
        Endpoint.publish(address, weatherService);
    }

}

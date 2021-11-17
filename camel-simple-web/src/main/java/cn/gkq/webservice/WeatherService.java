package cn.gkq.webservice;

import cn.gkq.webservice.impl.Necklet;
import cn.gkq.webservice.pojo.Person;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

/**
 * @author GKQ
 * @Classname WeatherService
 * @Description TODO
 * @Date 2021/3/25
 */
@WebService(targetNamespace = "http://webservice.gkq.cn/")
public interface WeatherService {

    /**
     * queryWeather
     * @param city
     * @return
     */
    @WebMethod(operationName = "queryWeather")
    @WebResult(name = "Necklet")
    String queryWeather(@WebParam(name = "city", targetNamespace = "http://webservice.gkq.cn/") String city);

    @WebMethod(operationName = "queryWater")
    @WebResult(name = "wwe")
    List<Necklet> queryWater(@WebParam(name = "city", targetNamespace = "http://webservice.gkq.cn/")String city);

    @WebMethod(operationName = "getNecklet")
    @WebResult(name = "necklet")
    Necklet getNecklet(@WebParam(name = "city", targetNamespace = "http://webservice.gkq.cn/") String  city);

    @WebMethod(operationName = "getListString")
    @WebResult(name = "sss")
    List<String> getListString(@WebParam(name = "city", targetNamespace = "http://webservice.gkq.cn/") String city);

    @WebMethod(operationName = "hellworld")
    @WebResult(name = "wsss")
    String[] hellworld(@WebParam(name = "city", targetNamespace = "http://webservice.gkq.cn/") String city, @WebParam(name = "name", targetNamespace = "http://webservice.gkq.cn/") String name);

    @WebMethod(operationName = "getNeckletArray")
    @WebResult(name = "neckletArray")
    Necklet[] getNeckletArray(@WebParam(name = "city", targetNamespace = "http://webservice.gkq.cn/") String city);
}

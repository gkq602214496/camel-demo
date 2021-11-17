package cn.gkq.webservice.impl;

import cn.gkq.webservice.WeatherService;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

/**
 * @author GKQ
 * @description
 * @date 2021/4/20
 */
@WebService(targetNamespace = "http://webservice.gkq.cn/")
public class WeatherServiceImpl implements WeatherService {

    @Override
    @WebMethod(operationName = "queryWeather")
    @WebResult(name = "Necklet")
    public String queryWeather(@WebParam(name = "city", targetNamespace = "http://webservice.gkq.cn/") String city) {
        String beijing = "beijing";
        String leshan = "leshan";
        Integer a = null;
        System.out.println(a/1);
        if (beijing.equals(city)) {
            return "36";
        } else if (leshan.equals(city)) {
            return "26";
        } else {
            return "unknown";
        }
    }

    @Override
    public List<Necklet> queryWater(@WebParam(name = "city", targetNamespace = "http://webservice.gkq.cn/") String city) {
        List<Necklet> necklets = Lists.newArrayList();
        if (StringUtils.isEmpty(city)) {
            return null;
        }
        Necklet necklet = new Necklet();
        necklet.setId("1111111");
        necklet.setName("好多水哦。。。。。。。。。。");
        Necklet.Yak yak = new Necklet.Yak();
        yak.setWeight(212);
        yak.setName(city);
        yak.setId("11111111");
        necklet.setYak(yak);

        Necklet necklet1 = new Necklet();
        necklet1.setId("22222222222222222");
        necklet1.setName("好多水哦22222222。。。。。。。。。。");
        Necklet.Yak yak1 = new Necklet.Yak();
        yak1.setWeight(2123242);
        yak1.setName(city);
        yak1.setId("2222222222222222222");
        necklet1.setYak(yak1);
        necklets.add(necklet);
        necklets.add(necklet1);
        return necklets;
    }

    @Override
    public Necklet[] getNeckletArray(@WebParam(name = "city", targetNamespace = "http://webservice.gkq.cn/") String city) {
        if (StringUtils.isEmpty(city)) {
            return null;
        }
        Necklet necklet = new Necklet();
        necklet.setId("1111111");
        necklet.setName("好多水哦。。。。。。。。。。");
        Necklet.Yak yak = new Necklet.Yak();
        yak.setWeight(212);
        yak.setName(city);
        yak.setId("11111111");
        necklet.setYak(yak);

        Necklet necklet1 = new Necklet();
        necklet1.setId("1111111");
        necklet1.setName("好多水哦。。。。。。。。。。");
        Necklet.Yak yak1 = new Necklet.Yak();
        yak1.setWeight(212);
        yak1.setName(city);
        yak1.setId("11111111");
        necklet1.setYak(yak1);
        return new Necklet[]{
                necklet, necklet1
        };
    }

    @Override
    @WebMethod(operationName = "getNecklet")
    @WebResult(name = "necklet")
    public Necklet getNecklet(@WebParam(name = "city", targetNamespace = "http://webservice.gkq.cn/") String city) {
        Necklet necklet = new Necklet();
        necklet.setId("1111111");
        necklet.setName("好多水哦。。。。。。。。。。");
        Necklet.Yak yak = new Necklet.Yak();
        yak.setWeight(212);
        yak.setName(city);
        yak.setId("11111111");
        necklet.setYak(yak);
        return necklet;
    }

    @Override
    @WebMethod(operationName = "getListString")
    @WebResult(name = "sss")
    public List<String> getListString(@WebParam(name = "city", targetNamespace = "http://webservice.gkq.cn/") String city) {
        List<String> list = Lists.newArrayList("23e3243243", "fdasfdsafasdfasf", city);
        return list;
    }

    @Override
    @WebMethod(operationName = "hellworld")
    public String[] hellworld(@WebParam(name = "city", targetNamespace = "http://webservice.gkq.cn/") String city, @WebParam(name = "name", targetNamespace = "http://webservice.gkq.cn/") String name) {
        return new String[]{"response", city, name};
    }
}

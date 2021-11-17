package cn.gkq.camel.cxf;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/5/26
 */
@Service
public class ServieA {

    public static final Map<String, ServiceB> map = new HashMap<>();

    public void add(ServiceB serviceB) {
        map.put("serviceB", serviceB);
        System.out.println("say some thing");
    }


    public ServiceB get() {
        return map.get("serviceB");
    }

}

package cn.gkq.camel.cxf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/5/26
 */
@Service
public class ServiceB {

    @Autowired
    private ServieA servieA;

    public void say() {
        servieA.get();
    }

}

package cn.gkq.camel.cxf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/5/26
 */
@RestController
@RequestMapping("/test")
public class TestSpringController {

    @Autowired
    private ServieA servieA;
    @Autowired
    private ServiceB serviceB;

    @RequestMapping("/www")
    public void test() {
        ServiceB value = servieA.get();
        value.say();
        System.out.println(value.equals(serviceB));

    };
}

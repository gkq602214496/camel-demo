package cn.gkq.camel.cxf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/5/26
 */
@Component
public class InitAdd implements CommandLineRunner {

    @Autowired
    private ServieA servieA;

    @Override
    public void run(String... args) throws Exception {
        servieA.add(new ServiceB());
    }
}

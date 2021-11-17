package cn.gkq.camel.cxf;

import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @author GKQ
 * @Classname TestServiceImpl
 * @Description TODO
 * @Date 2021/3/25
 */
@WebService(name = "TestService")
@Component
public class TestServiceImpl implements TestService {
    @Override
    public String sendMessage(String username) {
        return "hello " + username;
    }
}

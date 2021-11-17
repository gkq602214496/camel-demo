package cn.gkq.camel.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author GKQ
 * @Classname TestService
 * @Description TODO
 * @Date 2021/3/25
 */
@WebService(name = "TestService")
public interface TestService {

    @WebMethod
    String sendMessage(@WebParam(name="username") String username);


}

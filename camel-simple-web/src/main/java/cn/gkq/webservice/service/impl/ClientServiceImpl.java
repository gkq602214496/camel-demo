package cn.gkq.webservice.service.impl;

import cn.gkq.webservice.service.ClientService;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/6/25
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Override
    public String getResult() {
        return "result";
    }
}

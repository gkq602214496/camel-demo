package cn.gkq.webservice.service.impl;

import cn.gkq.webservice.service.ClientService;
import cn.gkq.webservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/6/25
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final ClientService clientService;

    @Override
    public String getUserInfo() {
        String result = clientService.getResult();
        return result;
    }
}

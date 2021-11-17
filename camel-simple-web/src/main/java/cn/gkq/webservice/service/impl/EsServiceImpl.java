package cn.gkq.webservice.service.impl;

import cn.gkq.webservice.service.EsService;
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
public class EsServiceImpl implements EsService {

    private final UserService userService;

    @Override
    public String test() {
        String userInfo = userService.getUserInfo();
        return userInfo;
    }
}

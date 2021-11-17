package cn.gkq.camel.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author GKQ
 * @Classname LoginResponseVO
 * @Description TODO
 * @Date 2021/2/2
 */
@Data
@Accessors(chain = true)
public class LoginResponseVO implements Serializable {

    private String id;
    private String expiredPassword;
    private String passwordChangeRequired;


}

package cn.gkq.camel.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author GKQ
 * @Classname LoginDTO
 * @Description TODO
 * @Date 2021/2/2
 */
@Data
@Accessors(chain = true)
public class LoginDTO implements Serializable {

    private String username;
    private String password;

}

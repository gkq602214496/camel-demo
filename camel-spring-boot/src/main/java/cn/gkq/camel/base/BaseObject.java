package cn.gkq.camel.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author GKQ
 * @Classname BaseObject
 * @Description TODO
 * @Date 2021/3/5
 */
@Data
public class BaseObject implements Serializable {

    private String address;
    private String phone;



}

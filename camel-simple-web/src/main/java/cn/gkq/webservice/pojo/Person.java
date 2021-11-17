package cn.gkq.webservice.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/9/13
 */
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = -4236118056953272719L;
    private String id;

    private String name;

    private String address;

    private Child child;

    @Data
    public static class Child implements Serializable {

        private static final long serialVersionUID = 8491772450148249726L;

        private String id;

        private String name;
    }

}

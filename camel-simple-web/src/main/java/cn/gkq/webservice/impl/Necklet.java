package cn.gkq.webservice.impl;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/5/25
 */
@Data
@Accessors(chain = true)
public class Necklet implements Serializable {

    private static final long serialVersionUID = -2184539114373359976L;
    private String id;
    private String address;
    private Integer size;
    private String name;
    private Yak yak;

    @Data
    @Accessors(chain = true)
    public static class Yak implements Serializable {

        private String id;
        private String name;
        private Integer weight;

    }

}

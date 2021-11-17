package cn.gkq.es.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/11/4
 */
@Data
public class DataQuery implements Serializable {
    private static final long serialVersionUID = -4059331103035835405L;

    private String address;
    private String name;
}

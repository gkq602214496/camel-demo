package cn.gkq.camel.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/6/21
 */
@Data
@Accessors(chain = true)
public class MonitorServer implements Serializable {
    public static final Integer STOP = 0;
    public static final Integer RUNNING = 1;
    public static final Integer START_FAILURE = 2;
    public static final Integer STOP_FAILURE = 3;

    private static final long serialVersionUID = -8507744206350840955L;

    private String serviceId;
    private String serviceIp;
    private String serviceInfo;
    private Integer monitorType;
    private Integer servicePort;
    private Integer status;
    private Long createTime;

}

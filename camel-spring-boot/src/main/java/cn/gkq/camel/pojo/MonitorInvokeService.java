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
public class MonitorInvokeService implements Serializable {
    private static final long serialVersionUID = 1171594754464361981L;

    private String serviceId;
    private String requester;
    private String provider;
    private String content;
    private Integer messageFlow;
    private Integer monitorType;
    private Integer servicePort;
    private Integer type;
    private Integer status;
    private Long createTime;
    private Long startTime;
    private Long costTime;

}

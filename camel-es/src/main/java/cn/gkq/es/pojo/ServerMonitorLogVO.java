package cn.gkq.es.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/6/30
 */
@Data
public class ServerMonitorLogVO implements Serializable {

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 服务IP
     */
    private String ip;

    /**
     * 服务端口
     */
    private Integer port;

    /**
     * 状态(0:已停止; 1:正在运行；)
     */
    private Integer runningState;

    /**
     * 服务类型(0:总计服务; 1:发布服务；2:调用服务；)
     */
    private String serviceType;

    /**
     * 输入消息数量
     */
    private String inputMessageNumber;

    /**
     * 输入消息流量
     */
    private Double inputMessageFlow;


}

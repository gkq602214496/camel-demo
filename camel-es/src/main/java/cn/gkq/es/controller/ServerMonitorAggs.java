package cn.gkq.es.controller;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>服务器聚合信息</p>
 *
 * @author GKQ
 * @date 2021/7/14
 */
@Data
public class ServerMonitorAggs implements Serializable {
    private static final long serialVersionUID = 8427950511319040078L;
    /**
     * key（即服务ID）
     */
    private String key;

    /**
     * 输入消息数量
     */
    private String inputMessageNumber;

    /**
     * 输入消息流量
     */
    private Double inputMessageFlow;
}

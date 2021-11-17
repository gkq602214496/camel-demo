package cn.gkq.es.controller;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>服务日志聚合信息类</p>
 *
 * @author GKQ
 * @date 2021/7/15
 */
@Data
public class ServiceAggregateData implements Serializable {


    private static final long serialVersionUID = 585327017117524853L;

    /**
     * 总记录数
     */
    private Integer totalRecords;

    /**
     * 总流量
     */
    private String totalFlow;

    /**
     * 错误记录数
     */
    private Integer errorRecords;

    /**
     * 错误率
     */
    private String errorRate;

}

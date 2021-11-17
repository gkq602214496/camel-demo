package cn.gkq.camel.controller;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/6/29
 */
@Data
@Accessors(chain = true)
public class MessageMonitorLog implements Serializable {
    private static final long serialVersionUID = -7874677694668156690L;

    /**
     * 唯一性标识
     */
    private String uniqueIdentifier;

    /**
     * 组件ID
     */
    private String componentId;

    /**
     * 监控类型
     */
    private String monitorType;

    /**
     * 序号
     */
    private Integer seq;

    /**
     * 消息状态 （0：失败；1：成功）
     */
    private String state;

    /**
     * 异常消息
     */
    private String exceptionMessage;

    /**
     * 入口消息
     */
    private String entryMessage;

    /**
     * 出口消息
     */
    private String exportMessage;

    /**
     * 日志流量（单位：字节）
     */
    private Integer messageFlow;

    /**
     * 耗时
     */
    private Long costTime;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 请求方
     */
    private String requesterId;

    /**
     * 请求方
     */
    private String requesterName;

    /**
     * 提供方
     */
    private String providerId;

    /**
     * 提供方
     */
    private String providerName;

}

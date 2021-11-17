package cn.gkq.es.controller;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/9/23
 */
@Data
@Accessors(chain = true)
public class ProjectInvokeTraceLog implements Serializable {

    /**
     * 服务标识
     */
    private String serviceId;

    /**
     * 服务标识
     */
    private String name;

    /**
     * 服务标识
     */
    private String age;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求时间
     */
    private String requestTime;

    /**
     * 响应时间
     */
    private Object responseTime;

    /**
     * 耗时
     */
    private Long costTime;

    /**
     * 消息状态 （0：失败；1：成功）
     */
    private String state;

    /**
     * 异常消息
     */
    private String exceptionMessage;

    /**
     * 监控类型
     */
    private String traceType;

    /**
     * 唯一性标识
     */
    private String uniqueIdentifier;

    /**
     * 日志级别 （TEST、PRODUCE）
     */
    private String logType;

    /**
     * 业务域名称
     */
    private String businessDomainName;

    /**
     * 鉴权码
     */
    private String authenticationCode;

}

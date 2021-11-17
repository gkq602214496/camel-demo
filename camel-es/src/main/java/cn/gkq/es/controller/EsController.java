package cn.gkq.es.controller;

import cn.gkq.es.pojo.DataQuery;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/6/16
 */
@RestController
@RequestMapping("/es")
@Slf4j
public class EsController {

    Logger logger = LoggerFactory.getLogger("logstashLogger");

    @Autowired
    private BBossESStarter bBossESStarter;

    @GetMapping("/getData")
    public String getData(DataQuery dataQuery) throws Exception {
        System.out.println(1);
        return "response: [" + dataQuery + "]";
    }

    @PostMapping("/save/query1")
    public String query111222(ProjectInvokeTraceLog projectInvokeTraceLog) throws Exception {
        projectInvokeTraceLog.setAuthenticationCode(ThreadLocalRandom.current().nextInt(100) + "");
        projectInvokeTraceLog.setAge(ThreadLocalRandom.current().nextInt(80) + "");
        projectInvokeTraceLog.setName("测试");
        return "response: [" + projectInvokeTraceLog.toString() + "]";
    }

    @PostMapping("/handle/rollback")
    public String rollback(@RequestBody ProjectInvokeTraceLog projectInvokeTraceLog) throws Exception {
        System.out.println("回滚xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        projectInvokeTraceLog.setAuthenticationCode(ThreadLocalRandom.current().nextInt(100) + "");
        projectInvokeTraceLog.setAge(ThreadLocalRandom.current().nextInt(80) + "");
        projectInvokeTraceLog.setName("回滚成功");
        return "response: [" + projectInvokeTraceLog.toString() + "]";
    }

    @PostMapping("/handle/load")
    public String handleLoad(@RequestBody ProjectInvokeTraceLog projectInvokeTraceLog, @RequestHeader String logType) throws Exception {
        System.out.println("传参：" + projectInvokeTraceLog.getServiceId());
        projectInvokeTraceLog.setAuthenticationCode(ThreadLocalRandom.current().nextInt(100) + "");
        projectInvokeTraceLog.setLogType(logType);
        return "response: [" + projectInvokeTraceLog.toString() + "]";
    }

    @DeleteMapping("/handle/delete")
    public String invoke(@RequestParam String name, @RequestParam Integer age) throws Exception {
        System.out.println(1);
        return "response: [" + name + "," + age + "]";

    }

    @RequestMapping("/query")
    public String query() throws Exception {
        ClientInterface configRestClient = bBossESStarter.getConfigRestClient("EsDsl.xml");
//        ClientInterface configRestClient1 = bBossESStarter.getConfigRestClient("EsDsl1.xml");
        Map<String, Object> condition = new HashMap<>();
        List<String> serviceIdList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("aaaaaaaaaaaaa").append(",").append("nnnnnnnnnnnnnn");
//        serviceIdList.add("192a1a1a1");
        serviceIdList.add("172.16.110.174");
        serviceIdList.add(",");
        serviceIdList.add("0.0.0.0");
//        serviceIdList.add("1409451417620459522");
//        serviceIdList.add("1409451417620459523");
//        serviceIdList.add("1");
        condition.put("serviceIdList", sb.toString());
        ESDatas<Object> pageServerMonitor = configRestClient.searchList("gkq/_search", "pageServerMonitor", condition, Object.class);
        List<Map> buckets = (List<Map>) pageServerMonitor.getAggregations().get("aggs_data").get("buckets");
//        ESDatas<Object> pageServerMonitor1 = configRestClient1.searchList("gkq/_msearch", "msearch", condition, Object.class);
        for (Map bucket : buckets) {
            Map count_message1 = (Map) bucket.get("total_message_flow");
            Object count_message2 = (count_message1).get("value");
            String count_message = count_message2.toString();
            System.out.println(count_message);
        }
        System.out.println(pageServerMonitor);
        return "ok";
    }

    @RequestMapping("/query/overview")
    public String queryServiceOverview(@RequestParam(required = false) String appDomain) throws Exception {
        ClientInterface configRestClient = bBossESStarter.getConfigRestClient("EsDsl.xml");
//        ClientInterface configRestClient1 = bBossESStarter.getConfigRestClient("EsDsl1.xml");
        Map<String, Object> condition = new HashMap<>();
        condition.put("appDomain", appDomain);
        ESDatas<Object> pageServerMonitor = configRestClient.searchList("gkq/_search", "serviceOverview", condition, Object.class);
        List<Map> buckets = (List<Map>) pageServerMonitor.getAggregations().get("aggregate_data").get("buckets");
        System.out.println(pageServerMonitor);
        return "ok";
    }

    @RequestMapping("/query/service")
    public String queryService(@RequestParam(required = false) String state) throws Exception {
        ClientInterface configRestClient = bBossESStarter.getConfigRestClient("EsDsl.xml");
//        ClientInterface configRestClient1 = bBossESStarter.getConfigRestClient("EsDsl1.xml");
        Map<String, Object> condition = new HashMap<>();
        List<String> serviceIdList = new ArrayList<>();
        serviceIdList.add("1409451417620459524");
        serviceIdList.add("1409451417620459525");
//        serviceIdList.add("1");
        condition.put("serviceIdList", serviceIdList);
        condition.put("startTime", "2021-06-13T17:50:23.506Z");
        condition.put("endTime", "2021-08-14T09:30:29.333Z");
        if (StringUtils.isNotEmpty(state)) {
            condition.put("state", state);
        }
        ESDatas<Object> pageServerMonitor = configRestClient.searchList("gkq/_search", "pageServiceMonitor", condition, Object.class);
        List<Map> buckets = (List<Map>) pageServerMonitor.getAggregations().get("aggregate_data").get("buckets");
        for (Map bucket : buckets) {
            Map count_message1 = (Map) bucket.get("total_flow");
            Object count_message2 = (count_message1).get("value");
            String count_message = count_message2.toString();
            System.out.println(count_message);
        }
        System.out.println(pageServerMonitor);
        return "ok";
    }

    @RequestMapping("/saveServer1")
    public String saveServer1() throws Exception {
        MessageMonitorLog messageMonitorLog = new MessageMonitorLog();
        messageMonitorLog.setRequesterId(ThreadLocalRandom.current().nextInt(100) + "")
                .setProviderId("1409451417620459526").setUniqueIdentifier("911234").setComponentId("6123")
                .setMonitorType("message").setState("1").setAppDomain("HIS")
                .setMessageFlow(6).setCostTime(12L).setSeq(0).setEntryMessage("hello world new h bad").setEntryMessage("hello world new h bad");
        logger.info(JSONObject.toJSONString(messageMonitorLog));
        return "ok";
    }

    @RequestMapping("/saveServer")
    public String saveServer() throws Exception {
        MessageMonitorLog messageMonitorLog = new MessageMonitorLog();
        messageMonitorLog.setRequesterId(ThreadLocalRandom.current().nextInt(100) + "")
                .setProviderId("1409451417620459526").setUniqueIdentifier("911234").setComponentId("623456")
                .setMonitorType("message").setState("1").setAppDomain("HIS")
                .setMessageFlow(13).setCostTime(12L).setSeq(1).setEntryMessage("hello world new hhh bad").setEntryMessage("hello world new hhh bad");
        logger.info(JSONObject.toJSONString(messageMonitorLog));
        return "ok";
    }


}

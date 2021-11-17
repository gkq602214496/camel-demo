package cn.gkq.camel.controller;

import cn.gkq.camel.pojo.MonitorInvokeService;
//import cn.gkq.webservice.impl.Necklet;
import cn.gkq.camel.pojo.MonitorServer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.security.jca.ServiceId;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    Logger logger = LoggerFactory.getLogger("logstashLogger");

    @RequestMapping("/saveServer")
    public String saveServer() throws Exception {
        MessageMonitorLog messageMonitorLog = new MessageMonitorLog();
        messageMonitorLog.setRequesterId(ThreadLocalRandom.current().nextInt(10) + "")
                .setProviderId("1409451417620459522").setUniqueIdentifier("123456").setComponentId("123")
                .setMonitorType("message")
                .setMessageFlow(23).setCostTime(12L).setSeq(0).setEntryMessage("hello world").setEntryMessage("hello world");
        logger.info(JSONObject.toJSONString(messageMonitorLog));
        return "ok";
    }

    @RequestMapping("/logSave")
    public String logSave() throws Exception {
        MonitorInvokeService monitorInvokeService = new MonitorInvokeService();
        monitorInvokeService.setContent("sdfsdfsdfdsf").setCostTime(1221L)
                .setMessageFlow(33).setProvider("提供方2").setRequester("请求方2")
                .setMonitorType(1).setStatus(1).setServiceId("7406924393748176898");
        log.info(JSON.toJSONString(monitorInvokeService));
        return "ok";
    }

    @RequestMapping("/save")
    public String save() throws Exception {
        MonitorInvokeService monitorInvokeService = new MonitorInvokeService();
        monitorInvokeService.setContent("wwe").setCostTime(2331L)
                .setMessageFlow(23).setProvider("提供方2").setRequester("请求方2")
                .setMonitorType(1).setStatus(1).setServiceId("1406924393748176898");

//        Necklet necklet = new Necklet();
//        necklet.setId(123L);
//        necklet.setCity(666);
//        necklet.setName("www");
        IndexRequest index = new IndexRequest("gkq");
        index.source(JSON.toJSONString(monitorInvokeService), XContentType.JSON);
//        index.source("age", "wwwwwwwwwwwwwww", "name", "china", "id", 222L);
        restHighLevelClient.index(index, RequestOptions.DEFAULT);
        System.out.println(restHighLevelClient);
        return "ok";
    }

    @RequestMapping("/{id}")
    public String get(@PathVariable("id") String id) throws Exception {
        SearchRequest searchRequest = new SearchRequest("gkq");
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.matchQuery("serviceId", id));
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQueryBuilder);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            System.out.println(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    /**
     * 服务器监控-分页查询
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/server/page")
    public String pageServer() throws Exception {
        //指定索引
        SearchRequest searchRequest = new SearchRequest("gkq");
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //相当于表名
        boolQueryBuilder.must(QueryBuilders.termQuery("monitorType", 0));
        //开始索引
        searchSourceBuilder.from(0);
        //每页条数
        searchSourceBuilder.size(10);
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response1 = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String, Object>> collect = Arrays.stream(response1.getHits().getHits()).map(r -> {
            return r.getSourceAsMap();
        }).collect(Collectors.toList());
        for (Map<String, Object> map : collect) {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            String serviceId = (String) map.get("serviceId");
            BoolQueryBuilder queryBuilderItem = new BoolQueryBuilder();
            queryBuilderItem.must(QueryBuilders.termQuery("serviceId", serviceId));
            //相当于表名 消息表
            queryBuilderItem.must(QueryBuilders.termQuery("monitorType", 1));
            // total_messageFlow为聚合统计命名后的字段名称，统计messageFlow字段值的总和
            SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum("total_messageFlow")
                    .field("messageFlow");
            sourceBuilder.aggregation(sumAggregationBuilder);
            sourceBuilder.query(queryBuilderItem);
            searchRequest.source(sourceBuilder);
            SearchResponse response2 = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            List<Map<String, Object>> collect2 = Arrays.stream(response2.getHits().getHits()).map(r -> {
                return r.getSourceAsMap();
            }).collect(Collectors.toList());
            System.out.println(collect2);
            //TODO 通过ES聚合操作， 获取输入消息数据和输入消息流量 并设置相关属性并返回
        }
        return "ok";
    }


    /**
     * 服务监控-详情
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/server/info/{serviceId}")
    public String getServer(@PathVariable("serviceId") String serviceId) throws Exception {
        //指定索引
        SearchRequest searchRequest = new SearchRequest("gkq");
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //相当于表名
        boolQueryBuilder.must(QueryBuilders.termQuery("monitorType", 1));
//        //设置查询条件
        boolQueryBuilder.must(QueryBuilders.termQuery("serviceId", serviceId));
//        //设置请求方或提供方
        boolQueryBuilder.must(QueryBuilders.termQuery("requester.keyword", "请求方1"));
        //设置搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //求平均耗时可用
        AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders.avg("avg_costTime")
                .field("costTime");
        //服务作为请求方和提供方分别 分组查询
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.aggregation(avgAggregationBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse result = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String, Object>> collect2 = Arrays.stream(result.getHits().getHits()).map(r -> {
            return r.getSourceAsMap();
        }).collect(Collectors.toList());
        //拿取聚合数据
        Aggregations aggregations = result.getAggregations();
        Aggregation avg_costTime = aggregations.get("avg_costTime");
        //TODO 服务作为请求方和提供方分别 分组进行统计，再通过ES聚合得出平均耗时、调用次数，然后计算吞吐量和失败率
        return "ok";
    }

    /**
     * 服务监控-分页查询
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/service/page")
    public String pageService(@RequestParam String startTime, @RequestParam String endTime,  @RequestParam Integer serviceType) throws Exception {
        //指定索引
        SearchRequest searchRequest = new SearchRequest("gkq");
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //相当于表名
        boolQueryBuilder.must(QueryBuilders.termQuery("monitorType", 0));
        //开始索引
        searchSourceBuilder.from(0);
        //每页条数
        searchSourceBuilder.size(10);
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response1 = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String, Object>> collect = Arrays.stream(response1.getHits().getHits()).map(r -> {
            return r.getSourceAsMap();
        }).collect(Collectors.toList());
        for (Map<String, Object> map : collect) {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            String serviceId = (String) map.get("serviceId");
            BoolQueryBuilder queryBuilderItem = new BoolQueryBuilder();
            queryBuilderItem.must(QueryBuilders.termQuery("serviceId", serviceId));
            //时间搜索 在startTime和endTime之间
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("@timestamp");
            rangeQueryBuilder.from(startTime);
            rangeQueryBuilder.to(endTime);
            //相当于表名 消息表
            queryBuilderItem.must(QueryBuilders.termQuery("monitorType", 1));
            // 聚合统计命名为：serviceIds， 统计serviceId字段值的数量
            ValueCountAggregationBuilder valueCountAggregationBuilder = AggregationBuilders.count("serviceIds")
                    .field("serviceId");
            sourceBuilder.aggregation(valueCountAggregationBuilder);
            sourceBuilder.query(queryBuilderItem);
            sourceBuilder.query(rangeQueryBuilder);
            searchRequest.source(sourceBuilder);
            SearchResponse response2 = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            List<Map<String, Object>> collect2 = Arrays.stream(response2.getHits().getHits()).map(r -> {
                return r.getSourceAsMap();
            }).collect(Collectors.toList());
            System.out.println(collect2);
            //TODO 通过ES聚合操作， 统计消息总条数、错误条数、计算错误率，时间范围内的消息数等，然后填充属性返回前端
        }
        return "ok";
    }

}

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
                .setMessageFlow(33).setProvider("?????????2").setRequester("?????????2")
                .setMonitorType(1).setStatus(1).setServiceId("7406924393748176898");
        log.info(JSON.toJSONString(monitorInvokeService));
        return "ok";
    }

    @RequestMapping("/save")
    public String save() throws Exception {
        MonitorInvokeService monitorInvokeService = new MonitorInvokeService();
        monitorInvokeService.setContent("wwe").setCostTime(2331L)
                .setMessageFlow(23).setProvider("?????????2").setRequester("?????????2")
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
     * ???????????????-????????????
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/server/page")
    public String pageServer() throws Exception {
        //????????????
        SearchRequest searchRequest = new SearchRequest("gkq");
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //???????????????
        boolQueryBuilder.must(QueryBuilders.termQuery("monitorType", 0));
        //????????????
        searchSourceBuilder.from(0);
        //????????????
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
            //??????????????? ?????????
            queryBuilderItem.must(QueryBuilders.termQuery("monitorType", 1));
            // total_messageFlow????????????????????????????????????????????????messageFlow??????????????????
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
            //TODO ??????ES??????????????? ????????????????????????????????????????????? ??????????????????????????????
        }
        return "ok";
    }


    /**
     * ????????????-??????
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/server/info/{serviceId}")
    public String getServer(@PathVariable("serviceId") String serviceId) throws Exception {
        //????????????
        SearchRequest searchRequest = new SearchRequest("gkq");
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //???????????????
        boolQueryBuilder.must(QueryBuilders.termQuery("monitorType", 1));
//        //??????????????????
        boolQueryBuilder.must(QueryBuilders.termQuery("serviceId", serviceId));
//        //???????????????????????????
        boolQueryBuilder.must(QueryBuilders.termQuery("requester.keyword", "?????????1"));
        //??????????????????
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //?????????????????????
        AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders.avg("avg_costTime")
                .field("costTime");
        //??????????????????????????????????????? ????????????
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.aggregation(avgAggregationBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse result = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String, Object>> collect2 = Arrays.stream(result.getHits().getHits()).map(r -> {
            return r.getSourceAsMap();
        }).collect(Collectors.toList());
        //??????????????????
        Aggregations aggregations = result.getAggregations();
        Aggregation avg_costTime = aggregations.get("avg_costTime");
        //TODO ??????????????????????????????????????? ??????????????????????????????ES???????????????????????????????????????????????????????????????????????????
        return "ok";
    }

    /**
     * ????????????-????????????
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/service/page")
    public String pageService(@RequestParam String startTime, @RequestParam String endTime,  @RequestParam Integer serviceType) throws Exception {
        //????????????
        SearchRequest searchRequest = new SearchRequest("gkq");
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //???????????????
        boolQueryBuilder.must(QueryBuilders.termQuery("monitorType", 0));
        //????????????
        searchSourceBuilder.from(0);
        //????????????
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
            //???????????? ???startTime???endTime??????
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("@timestamp");
            rangeQueryBuilder.from(startTime);
            rangeQueryBuilder.to(endTime);
            //??????????????? ?????????
            queryBuilderItem.must(QueryBuilders.termQuery("monitorType", 1));
            // ????????????????????????serviceIds??? ??????serviceId??????????????????
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
            //TODO ??????ES??????????????? ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        }
        return "ok";
    }

}

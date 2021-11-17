package cn.gkq.camel.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/6/15
 */
@Configuration
public class ElasticsearchConfig {

    @Value("${elasticsearch.ip}")
    private String ip;

    @Value("${elasticsearch.port}")
    private Integer port;

    @Bean
    @Primary
    public RestClientBuilder restClientBuilder() {
        return RestClient.builder(makeHttpHost());
    }

    @Bean
    public RestHighLevelClient highLevelClient(@Autowired RestClientBuilder restClientBuilder) {
        return new RestHighLevelClient(restClientBuilder);
    }

    private HttpHost makeHttpHost() {
        return new HttpHost(ip, port, "http");
    }

}

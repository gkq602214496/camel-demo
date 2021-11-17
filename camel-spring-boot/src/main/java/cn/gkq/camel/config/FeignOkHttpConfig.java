//package cn.gkq.camel.config;
//
//import feign.Client;
//import feign.Feign;
//import lombok.Data;
//import okhttp3.ConnectionPool;
//import okhttp3.OkHttpClient;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.AutoConfigureBefore;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.commons.httpclient.OkHttpClientConnectionPoolFactory;
//import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.openfeign.FeignAutoConfiguration;
//import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PreDestroy;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author GKQ
// * @Classname FeignOkHttpConfig
// * @Description TODO
// * @Date 2021/2/2
// */
//@Component
//@ConfigurationProperties(prefix = "okhttp.pool")
//@Data
//@ConditionalOnClass(Feign.class)
//public class FeignOkHttpConfig {
//
//    private Long connectTimeout;
//    private Long readTimeout;
//    private Long writeTimeout;
//    private Boolean retryOnConnectionFailure;
//    private Integer maxIdleConnections;
//    private Long keepAliveDuration;
//
//    @Bean
//    @Primary
//    public okhttp3.OkHttpClient okHttpClient() {
//        ConnectionPool connectionPool = new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.MINUTES);
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        return builder.connectTimeout(connectTimeout, TimeUnit.SECONDS)
//                .readTimeout(readTimeout, TimeUnit.SECONDS)
//                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(retryOnConnectionFailure)
//                .connectionPool(connectionPool)
//                .build();
//    }
//
//    @Bean
//    @ConditionalOnMissingBean({ConnectionPool.class})
//    public ConnectionPool httpClientConnectionPool(FeignHttpClientProperties httpClientProperties, OkHttpClientConnectionPoolFactory connectionPoolFactory) {
//        Integer maxTotalConnections = httpClientProperties.getMaxConnections();
//        Long timeToLive = httpClientProperties.getTimeToLive();
//        TimeUnit ttlUnit = httpClientProperties.getTimeToLiveUnit();
//        return connectionPoolFactory.create(maxTotalConnections, timeToLive, ttlUnit);
//    }
//
//
//    @Bean
//    @ConditionalOnMissingBean({Client.class})
//    public Client feignClient(okhttp3.OkHttpClient client) {
//        return new feign.okhttp.OkHttpClient(client);
//    }
//
//}

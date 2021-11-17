package cn.gkq.camel.config;

import cn.gkq.camel.classloader.MyClassLoader1;
import org.apache.camel.spring.boot.SpringBootCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author GKQ
 * @Classname CamelConfig
 * @Description camel配置类
 * @Date 2021/2/25
 */
@Configuration
public class CamelConfig {

    @Autowired
    private MyClassLoader1 myClassLoader1;

    @Bean
    public SpringBootCamelContext springBootCamelContext(ApplicationContext applicationContext) {
        SpringBootCamelContext camelContext = new SpringBootCamelContext(applicationContext, true);
        camelContext.setApplicationContextClassLoader(myClassLoader1);
        return new SpringBootCamelContext(applicationContext, true);
    };




}

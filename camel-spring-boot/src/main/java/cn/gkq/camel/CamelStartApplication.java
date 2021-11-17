package cn.gkq.camel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

/**
 * @author GKQ
 * @Classname CamelStartApplication
 * @Description TODO
 * @Date 2021/2/1
 */
@SpringBootApplication
@EnableFeignClients
@Slf4j
public class CamelStartApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CamelStartApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        log.info("camel platform has started up : {} ", Arrays.toString(activeProfiles));
    }

}

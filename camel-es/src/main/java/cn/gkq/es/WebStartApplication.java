package cn.gkq.es;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

/**
 * @author GKQ
 * @Classname WebStartApplication
 * @Description TODO
 * @Date 2021/2/1
 */
@SpringBootApplication
@Slf4j
public class WebStartApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WebStartApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        log.info("camel platform has started up : {} ", Arrays.toString(activeProfiles));
    }

}

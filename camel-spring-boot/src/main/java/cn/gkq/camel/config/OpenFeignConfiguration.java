package cn.gkq.camel.config;

import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;

/**
 * @author GKQ
 * @Classname OpenFeignConfiguration
 * @Description TODO
 * @Date 2021/2/2
 */
public class OpenFeignConfiguration {

    @Bean
    public Decoder feignDecoder() {
        return new CustomDecoder();
    }

}

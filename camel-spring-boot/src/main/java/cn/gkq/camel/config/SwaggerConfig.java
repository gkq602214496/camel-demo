package cn.gkq.camel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author GKQ
 * @Classname SwaggerConfig
 * @Description TODO
 * @Date 2020/12/17
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String DEFAULT_PACKAGE = "cn.gkq.camel.controller";

    @Bean
    public Docket docket() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        ArrayList<Parameter> parameters = new ArrayList();
        parameterBuilder.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        parameters.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(DEFAULT_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    /**
     * 构建API文档详细信息
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("camel-示例")
                .version("1.0")
                .description("camel管理模块")
                .contact(new Contact("camel服务平台", "", "602214496@qq.com"))
                .build();
    }
}

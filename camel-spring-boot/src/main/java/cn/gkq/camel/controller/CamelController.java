package cn.gkq.camel.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpMessage;
import org.apache.camel.model.ChoiceDefinition;
import org.apache.camel.model.OnExceptionDefinition;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.spring.boot.SpringBootCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GKQ
 * @Classname OdinController
 * @Description TODO
 * @Date 2021/2/2
 */
@RestController
@RequestMapping("/camel")
@Api(value = "Odin控制器")
public class CamelController {

    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String PROJECT_DIR = USER_DIR;
    public static final String CXF_PREFIX = "cxf://";


    @Autowired
    private SpringBootCamelContext camelContext;


    @PostMapping("/testFallback")
    @ApiOperation(value = "testFallback")
    public void testFallback() throws Exception {
        camelContext.addRoutes(new RouteBuilder() {
            String routeInfo = "jetty://http://localhost:8088/choice";
            String httpClientInfo = "http://localhost:8098/save/load";
            String rollbackInfo = "http://localhost:8098/save/query";

            @Override
            public void configure() throws Exception {
                RouteDefinition from = from(routeInfo);
                from.removeHeaders("CamelHttp*");
                from.setHeader("CamelHttpMethod", this.constant("GET"));
                from.setHeader("Content-Type", this.constant("application/json1"));
                from.circuitBreaker().hystrixConfiguration().executionTimeoutInMilliseconds(5000)
                        .circuitBreakerSleepWindowInMilliseconds(10000)
                        .end().to(httpClientInfo).onFallback().transform().constant("Fallback message")
                        .end()
                        .to(rollbackInfo);
            }
        });
    }

    @PostMapping("/testWsClient")
    @ApiOperation(value = "camel文件上传")
    public String testHttp(@RequestParam("name") String name) throws Exception {
        String content = "<soap:Envelope\n" +
                "        xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "    <soap:Body>\n" +
                "        <hsb:queryWater\n" +
                "                xmlns:hsb=\"http://webservice.gkq.cn/\">\n" +
                "            <hsb:city>dss</hsb:city>\n" +
                "        </hsb:queryWater>\n" +
                "    </soap:Body>\n" +
                "</soap:Envelope>";

        String routeInfo = "jetty://http://localhost:8088/choice";
        String clientUri = "cxf://http://172.16.110.174:8099/weather?wsdlURL=http://172.16.110.174:8099/weather?wsdl&dataFormat=MESSAGE";
        camelContext.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from(routeInfo).process(exchange -> {
                    exchange.getIn().setBody(content);
                }).to(clientUri).process(exchange -> {
                    String body = exchange.getMessage().getBody(String.class);
                    System.out.println(body);
                    exchange.getMessage().setBody(DocumentContainer.build(body).toString());

                });
            }
        });
        return "hello camel test ：" + name;
    }

    @PostMapping("/upload")
    @ApiOperation(value = "camel文件上传")
    public Object upload(String routeId) throws Exception {
        System.out.println("第二版本");
        final String from = "file://D:/workspace/camel-demo/camel-spring-boot/src/main/resources/camel/inbox";
        final String to = "file://D:/workspace/camel-demo/camel-spring-boot/src/main/resources/camel/outbox";
        if ("imgSuffix".equals(routeId)) {

        } else if ("txtSuffix".equals(routeId)) {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from(from)
                            .group("txt.file")
                            .to(to)
                            .log(LoggingLevel.INFO, log, "Downloaded file ${file:name} Completed ！");
                }
            });
        } else {
            System.out.println("无效操作！");
        }

        return null;
    }


    /**
     * jdbc测试
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/jdbc")
    public String jdbc() throws Exception {
        System.out.println(System.getProperty("user.dir"));
        String routeInfo = "jetty://http://localhost:8088/choice";
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                String header = "CamelRequestMethod";
                RouteDefinition from = from(routeInfo);
                from.process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Message message = exchange.getMessage();
                        if (message instanceof HttpMessage) {
                            HttpMessage httpMessage = (HttpMessage) message;
                            Object header1 = httpMessage.getRequest().getHeader(header);
                            message.setHeader(header, header1);
                        }
                    }
                });
                ChoiceDefinition choiceDefinition = from.choice();

                choiceDefinition.when(exchange -> {
                    Object header1 = exchange.getMessage().getHeader(header);
                    return "a".equals(header1);
                }).to("direct:a")
                        .when(exchange -> "b".equals(exchange.getMessage().getHeader(header))).to("direct:b")
                        .otherwise().to("direct:c");

                from("direct:a").process(exchange -> System.out.println("调用了a"));

                from("direct:b").process(exchange -> System.out.println("调用了b"));

                from("direct:c").process(exchange -> System.out.println("调用了c"));

            }
        });


        return "ok";
    }


    /**
     * 字典转换
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/choice")
    public String choice() throws Exception {
        System.out.println(System.getProperty("user.dir"));
        String routeInfo = "jetty://http://localhost:8088/choice";
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                String header = "CamelRequestMethod";

                OnExceptionDefinition onExceptionDefinition = onException(ArithmeticException.class).process(exchange -> {
                    String body = exchange.getMessage().getBody(String.class);
                    Exception exception = exchange.getException();
                    Exception exce = exchange.getProperty("CamelExceptionCaught", Exception.class);
                    System.out.println(body);
                });


                onExceptionDefinition.to("direct:error");


                from("direct:error").process(exchange -> {
                    String body = exchange.getMessage().getBody(String.class);
                    System.out.println(body);
                });
                RouteDefinition from = from(routeInfo);


                from.process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Message message = exchange.getMessage();
                        if (message instanceof HttpMessage) {
                            HttpMessage httpMessage = (HttpMessage) message;
                            Object header1 = httpMessage.getRequest().getHeader(header);
                            message.setHeader(header, header1);
                        }
                    }
                });
                ChoiceDefinition choiceDefinition = from.choice();

                choiceDefinition.when(exchange -> {
                    Object header1 = exchange.getMessage().getHeader(header);
                    return "a".equals(header1);
                }).to("direct:a")
                        .when(exchange -> "b".equals(exchange.getMessage().getHeader(header))).to("direct:b")
                        .otherwise().to("direct:c");

                from("direct:a").process(exchange -> {
                    System.out.println("调用了a");
                    Integer a = 1 / 0;
                    System.out.println(a);
                }).to("direct:last");

                from("direct:b").process(exchange -> System.out.println("调用了b"));

                from("direct:c").process(exchange -> System.out.println("调用了c"));

                from("direct:last").process(exchange -> {
                    System.out.println("调用了last");
                });


            }
        });


        return "ok";
    }


}

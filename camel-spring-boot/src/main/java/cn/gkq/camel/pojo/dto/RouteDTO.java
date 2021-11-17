package cn.gkq.camel.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author GKQ
 * @Classname RouteDTO
 * @Description RouteDTO
 * @Date 2021/3/29
 */
@NoArgsConstructor
@Data
public class RouteDTO implements Serializable {

    @JsonProperty("serviceName")
    private String serviceName;
    @JsonProperty("component")
    private List<ComponentDTO> component;
    @JsonProperty("links")
    private List<LinksDTO> links;

    @NoArgsConstructor
    @Data
    public static class ComponentDTO {
        @JsonProperty("id")
        private String id;
        @JsonProperty("isFirst")
        private Boolean isFirst;
        @JsonProperty("data")
        private DataDTO data;

        @NoArgsConstructor
        @Data
        public static class DataDTO {

            @JsonProperty("business")
            private BusinessDTO business;

            @NoArgsConstructor
            @Data
            public static class BusinessDTO {

                @JsonProperty("name")
                private String name;
                @JsonProperty("description")
                private String description;
                @JsonProperty("type")
                private String type;
                @JsonProperty("mode")
                private String mode;
                @JsonProperty("retryPolicy")
                private RetryPolicyDTO retryPolicy;
                @JsonProperty("processingStrategy")
                private ProcessingStrategyDTO processingStrategy;
                @JsonProperty("properties")
                private PropertiesDTO properties;
                @JsonProperty("advancedProperties")
                private AdvancedPropertiesDTO advancedProperties;
                @JsonProperty("variablePassword")
                private Boolean variablePassword;
                @JsonProperty("state")
                private String state;
                @JsonProperty("coreThreadSize")
                private Integer coreThreadSize;
                @JsonProperty("maxThreadSize")
                private Integer maxThreadSize;
                @JsonProperty("queueSize")
                private Integer queueSize;
                @JsonProperty("threadKeepAliveTime")
                private Integer threadKeepAliveTime;
                @JsonProperty("useOwnThreadPool")
                private Boolean useOwnThreadPool;
                @JsonProperty("started")
                private Boolean started;

                @NoArgsConstructor
                @Data
                public static class RetryPolicyDTO {
                    /**
                     * maximumRetries : 3
                     * retryDelay : 500
                     * discardMessage : false
                     * maxDelay : 6000000
                     * mode : CONSTANT_INTERVAL
                     */

                    @JsonProperty("maximumRetries")
                    private Integer maximumRetries;
                    @JsonProperty("retryDelay")
                    private Integer retryDelay;
                    @JsonProperty("discardMessage")
                    private Boolean discardMessage;
                    @JsonProperty("maxDelay")
                    private Integer maxDelay;
                    @JsonProperty("mode")
                    private String mode;
                }

                @NoArgsConstructor
                @Data
                public static class ProcessingStrategyDTO {
                }

                @NoArgsConstructor
                @Data
                public static class PropertiesDTO {
                }

                @NoArgsConstructor
                @Data
                public static class AdvancedPropertiesDTO {
                }
            }
        }
    }

    @NoArgsConstructor
    @Data
    public static class LinksDTO {

        @JsonProperty("id")
        private String id;
        @JsonProperty("source")
        private SourceDTO source;
        @JsonProperty("target")
        private TargetDTO target;

        @NoArgsConstructor
        @Data
        public static class SourceDTO {

            @JsonProperty("cell")
            private String cell;
            @JsonProperty("port")
            private String port;
        }

        @NoArgsConstructor
        @Data
        public static class TargetDTO {

            @JsonProperty("cell")
            private String cell;
            @JsonProperty("port")
            private String port;
        }
    }
}

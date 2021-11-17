package cn.gkq.camel.cxf;

import lombok.Data;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/5/17
 */
@Data
public class CxfProperty implements Serializable {

    private String address;
    private String wsdlUrl;
    private String dataFormat;
    private String defaultOperationNamespace;
    private String defaultOperationName;
    private String loggingFeatureEnabled;


}

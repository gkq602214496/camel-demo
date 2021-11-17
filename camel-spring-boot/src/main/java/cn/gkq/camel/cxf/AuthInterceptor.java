package cn.gkq.camel.cxf;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/6/23
 */
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    public AuthInterceptor() {
        super(Phase.PRE_PROTOCOL);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        List<Header> headers = message.getHeaders();
        Object username1 = message.get("org.apache.cxf.configuration.security.AuthorizationPolicy");
        Object username2 = message.get("AuthorizationPolicy");
        Map<String, String> envelopeNs = message.getEnvelopeNs();
        Set<Map.Entry<String, Object>> entries = message.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(111);
        }
        for (Header header : headers) {
            SoapHeader soapHeader = (SoapHeader) header;
            Element e = (Element) soapHeader.getObject();
            NodeList usernameNode = e.getElementsByTagName("username");
            NodeList pwdNode = e.getElementsByTagName("password");
            String username = usernameNode.item(0).getTextContent();
            String password = pwdNode.item(0).getTextContent();
            System.out.println(1);
        }
    }
}

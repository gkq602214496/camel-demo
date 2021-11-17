package cn.gkq.camel.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/9/24
 */
public class TestJava {

    public static void main(String[] args) throws Exception {
        String responseTemplate = "<ns2:queryWaterResponse xmlns:ns2=\"http://webservice.gkq.cn/\">\n" +
                "            <wwe>\n" +
                "             \n" +
                "            </wwe>\n" +
                "            <wwe>\n" +
                "            </wwe>\n" +
                "        </ns2:queryWaterResponse>";
        Document returnResponse = DocumentHelper.parseText(responseTemplate);
        String text = returnResponse.getRootElement().getText();
        System.out.println("text:  " + text);


        String wsdlUrl = "http://172.16.110.174:8099/weather?wsdl";
//        String wsdlUrl = "http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl";
        Client client = JaxWsDynamicClientFactory.newInstance().createClient(wsdlUrl);
        String[] array = new String[]{"四川", "fsdfsafdsfas"};
        Object[] arrayData = client.invoke("getListString", array);

        List<Node> nodes = listTailNodes(returnResponse);
        if (arrayData.length > 0) {
            Object responseData = arrayData[0];
            if (responseData instanceof List) {
                List tempList = (List) responseData;
                if (tempList.get(0) instanceof String) {
                    //简单参数
                } else {
                    for (int i = 0; i < tempList.size(); i++) {
                        Object o = tempList.get(i);
                        String json = JSON.toJSONString(o);
                        System.out.println(json);
                        String s = XmlJsonConverterUtil.jsonToXmlNoXsd(json, "", false);
                        Document document = DocumentHelper.parseText(s);
                        Element rootElement = document.getRootElement();
                        List<Element> elements = rootElement.elements();
                        Element node = (Element) nodes.get(i);
                        for (Element element : elements) {
                            node.add((Node) element.clone());
                        }
                    }
                    System.out.println("List<Object>");
                }
            } else if (responseData instanceof String) {
                System.out.println("String");
            } else {
                System.out.println("String");
            }
        }

    }

    public static String getInet4Address() {
        Enumeration<NetworkInterface> nis;
        String ip = "127.0.0.1";
        try {
            nis = NetworkInterface.getNetworkInterfaces();
            for (; nis.hasMoreElements(); ) {
                NetworkInterface ni = nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                for (; ias.hasMoreElements(); ) {
                    InetAddress ia = ias.nextElement();
                    if (ia instanceof Inet4Address && !ia.getHostAddress().equals("127.0.0.1")) {
                        ip = ia.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

    private static void fillTailPaths(Set<Node> result, Element element) {
        List<Element> elements = element.elements();
        if (CollectionUtils.isNotEmpty(elements)) {
            for (Element node : elements) {
                fillTailPaths(result, node);
            }
        } else {
            result.add(element);
        }
    }

    /**
     * 获取文档尾节点path
     *
     * @param source
     * @return
     */
    public static List<Node> listTailNodes(Document source) {
        Set<Node> result = new HashSet<>();
        if (source != null) {
            Element rootElement = source.getRootElement();
            fillTailPaths(result, rootElement);
        }
        return new ArrayList<>(result);
    }
}

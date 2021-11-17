package cn.gkq.camel.controller;

import cn.gkq.camel.cxf.TestCXF;
import com.google.common.collect.Lists;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/5/27
 */
public class TestXpath {

    public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        String wsdlString = TestCXF.getCCC();
        byte[] bytes = wsdlString.getBytes("UTF-8");
        InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(byteArrayInputStream);
        XPathFactory xFactory = XPathFactory.newInstance();
        XPath xpath = xFactory.newXPath();
        String requestParamNameExpression = "/definitions/types/schema/complexType";
        String operationNameExpression = "/definitions/binding/operation/@name";

        Map<String, List<String>> map = new HashMap<>(16);

        NodeList operationNameNodeList = (NodeList) xpath.evaluate(operationNameExpression, document, XPathConstants.NODESET);
        List<String> operationNameList = Lists.newArrayList();

        for (int i = 0; i < operationNameNodeList.getLength(); i++) {
            List<String> paramList = Lists.newArrayList();
            StringBuilder sb = new StringBuilder(requestParamNameExpression);
            Node operationNameNode = operationNameNodeList.item(i);
            String operationName = operationNameNode.getNodeValue();
            operationNameList.add(operationName);
            String expr = sb.append("[@name='").append(operationName).append("']")
                    .append("/sequence/element").append("/@name").toString();
            NodeList paramNodeList = (NodeList) xpath.evaluate(expr, document, XPathConstants.NODESET);
            for (int j = 0; j < paramNodeList.getLength(); j++) {
                String nodeValue = paramNodeList.item(j).getNodeValue();
                paramList.add(nodeValue);
            }
            map.put(operationName, paramList);
        }

        System.out.println(map);

    }


    private static String getWsdlString() {
        String result = "<wsdl:definitions xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\" xmlns:tns=\"http://webservice.gkq.cn/\" xmlns:soap=\"http://schemas.xmlsoap.org/wsdl/soap/\" xmlns:ns1=\"http://schemas.xmlsoap.org/soap/http\" name=\"WeatherServiceService\" targetNamespace=\"http://webservice.gkq.cn/\">\n" +
                "    <wsdl:types>\n" +
                "        <xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:tns=\"http://webservice.gkq.cn/\" elementFormDefault=\"unqualified\" targetNamespace=\"http://webservice.gkq.cn/\" version=\"1.0\">\n" +
                "            <xs:element name=\"queryWater\" type=\"tns:queryWater\"/>\n" +
                "            <xs:element name=\"queryWaterResponse\" type=\"tns:queryWaterResponse\"/>\n" +
                "            <xs:element name=\"queryWeather\" type=\"tns:queryWeather\"/>\n" +
                "            <xs:element name=\"queryWeatherResponse\" type=\"tns:queryWeatherResponse\"/>\n" +
                "            <xs:complexType name=\"queryWater\">\n" +
                "                <xs:sequence>\n" +
                "                    <xs:element minOccurs=\"0\" name=\"arg0\" type=\"xs:string\"/>\n" +
                "                </xs:sequence>\n" +
                "            </xs:complexType>\n" +
                "            <xs:complexType name=\"queryWaterResponse\">\n" +
                "                <xs:sequence>\n" +
                "                    <xs:element minOccurs=\"0\" name=\"return\" type=\"tns:necklet\"/>\n" +
                "                </xs:sequence>\n" +
                "            </xs:complexType>\n" +
                "            <xs:complexType name=\"necklet\">\n" +
                "                <xs:sequence>\n" +
                "                    <xs:element minOccurs=\"0\" name=\"city\" type=\"xs:string\"/>\n" +
                "                    <xs:element minOccurs=\"0\" name=\"id\" type=\"xs:long\"/>\n" +
                "                    <xs:element minOccurs=\"0\" name=\"name\" type=\"xs:string\"/>\n" +
                "                </xs:sequence>\n" +
                "            </xs:complexType>\n" +
                "            <xs:complexType name=\"queryWeather\">\n" +
                "                <xs:sequence>\n" +
                "                    <xs:element minOccurs=\"0\" name=\"arg0\" type=\"xs:string\"/>\n" +
                "                    <xs:element minOccurs=\"0\" name=\"arg1\" type=\"xs:string\"/>\n" +
                "                </xs:sequence>\n" +
                "            </xs:complexType>\n" +
                "            <xs:complexType name=\"queryWeatherResponse\">\n" +
                "                <xs:sequence>\n" +
                "                    <xs:element minOccurs=\"0\" name=\"return\" type=\"tns:necklet\"/>\n" +
                "                </xs:sequence>\n" +
                "            </xs:complexType>\n" +
                "        </xs:schema>\n" +
                "    </wsdl:types>\n" +
                "    <wsdl:message name=\"queryWater\">\n" +
                "        <wsdl:part element=\"tns:queryWater\" name=\"parameters\"> </wsdl:part>\n" +
                "    </wsdl:message>\n" +
                "    <wsdl:message name=\"queryWeatherResponse\">\n" +
                "        <wsdl:part element=\"tns:queryWeatherResponse\" name=\"parameters\"> </wsdl:part>\n" +
                "    </wsdl:message>\n" +
                "    <wsdl:message name=\"queryWaterResponse\">\n" +
                "        <wsdl:part element=\"tns:queryWaterResponse\" name=\"parameters\"> </wsdl:part>\n" +
                "    </wsdl:message>\n" +
                "    <wsdl:message name=\"queryWeather\">\n" +
                "        <wsdl:part element=\"tns:queryWeather\" name=\"parameters\"> </wsdl:part>\n" +
                "    </wsdl:message>\n" +
                "    <wsdl:portType name=\"WeatherService\">\n" +
                "        <wsdl:operation name=\"queryWater\">\n" +
                "            <wsdl:input message=\"tns:queryWater\" name=\"queryWater\"> </wsdl:input>\n" +
                "            <wsdl:output message=\"tns:queryWaterResponse\" name=\"queryWaterResponse\"> </wsdl:output>\n" +
                "        </wsdl:operation>\n" +
                "        <wsdl:operation name=\"queryWeather\">\n" +
                "            <wsdl:input message=\"tns:queryWeather\" name=\"queryWeather\"> </wsdl:input>\n" +
                "            <wsdl:output message=\"tns:queryWeatherResponse\" name=\"queryWeatherResponse\"> </wsdl:output>\n" +
                "        </wsdl:operation>\n" +
                "    </wsdl:portType>\n" +
                "    <wsdl:binding name=\"WeatherServiceServiceSoapBinding\" type=\"tns:WeatherService\">\n" +
                "        <soap:binding style=\"document\" transport=\"http://schemas.xmlsoap.org/soap/http\"/>\n" +
                "        <wsdl:operation name=\"queryWater\">\n" +
                "            <soap:operation soapAction=\"\" style=\"document\"/>\n" +
                "            <wsdl:input name=\"queryWater\">\n" +
                "                <soap:body use=\"literal\"/>\n" +
                "            </wsdl:input>\n" +
                "            <wsdl:output name=\"queryWaterResponse\">\n" +
                "                <soap:body use=\"literal\"/>\n" +
                "            </wsdl:output>\n" +
                "        </wsdl:operation>\n" +
                "        <wsdl:operation name=\"queryWeather\">\n" +
                "            <soap:operation soapAction=\"\" style=\"document\"/>\n" +
                "            <wsdl:input name=\"queryWeather\">\n" +
                "                <soap:body use=\"literal\"/>\n" +
                "            </wsdl:input>\n" +
                "            <wsdl:output name=\"queryWeatherResponse\">\n" +
                "                <soap:body use=\"literal\"/>\n" +
                "            </wsdl:output>\n" +
                "        </wsdl:operation>\n" +
                "    </wsdl:binding>\n" +
                "    <wsdl:service name=\"WeatherServiceService\">\n" +
                "        <wsdl:port binding=\"tns:WeatherServiceServiceSoapBinding\" name=\"WeatherServicePort\">\n" +
                "            <soap:address location=\"http://172.16.110.174:8099/weather\"/>\n" +
                "        </wsdl:port>\n" +
                "    </wsdl:service>\n" +
                "</wsdl:definitions>";
        return result;
    }


}

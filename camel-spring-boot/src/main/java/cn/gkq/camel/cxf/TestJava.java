package cn.gkq.camel.cxf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.sun.org.apache.xerces.internal.dom.DeferredAttrImpl;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author GKQ
 * @date 2021/8/27
 */
public class TestJava {

    public static final String NAME_SPACE = "/definitions/@targetNamespace";
    public static final String METHOD_NAME_PATH = "/definitions/binding/operation/@name";
    public static final String METHOD_ARGS_PATH = "/definitions/types/schema/complexType[@name='";
    public static final String PARAM_NAME_PATH = "/definitions/types/schema/complexType";


    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("user.dir"));

    }

    private static String getWsdl() {

        return "<wsdl:definitions xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\" xmlns:tns=\"http://webservice.gkq.cn/\" xmlns:soap=\"http://schemas.xmlsoap.org/wsdl/soap/\" xmlns:ns1=\"http://schemas.xmlsoap.org/soap/http\" name=\"WeatherServiceService\" targetNamespace=\"http://webservice.gkq.cn/\">\n" +
                "<wsdl:types>\n" +
                "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:tns=\"http://webservice.gkq.cn/\" elementFormDefault=\"unqualified\" targetNamespace=\"http://webservice.gkq.cn/\" version=\"1.0\">\n" +
                "<xs:element name=\"queryWater\" type=\"tns:queryWater\"/>\n" +
                "<xs:element name=\"queryWaterResponse\" type=\"tns:queryWaterResponse\"/>\n" +
                "<xs:element name=\"queryWeather\" type=\"tns:queryWeather\"/>\n" +
                "<xs:element name=\"queryWeatherResponse\" type=\"tns:queryWeatherResponse\"/>\n" +
                "<xs:complexType name=\"queryWater\">\n" +
                "<xs:sequence>\n" +
                "<xs:element minOccurs=\"0\" name=\"city\" type=\"xs:string\"/>\n" +
                "</xs:sequence>\n" +
                "</xs:complexType>\n" +
                "<xs:complexType name=\"queryWaterResponse\">\n" +
                "<xs:sequence>\n" +
                "<xs:element minOccurs=\"0\" name=\"Necklet\" type=\"tns:necklet\"/>\n" +
                "</xs:sequence>\n" +
                "</xs:complexType>\n" +
                "<xs:complexType name=\"necklet\">\n" +
                "<xs:sequence>\n" +
                "<xs:element minOccurs=\"0\" name=\"city\" type=\"xs:int\"/>\n" +
                "<xs:element minOccurs=\"0\" name=\"id\" type=\"xs:long\"/>\n" +
                "<xs:element minOccurs=\"0\" name=\"name\" type=\"xs:string\"/>\n" +
                "</xs:sequence>\n" +
                "</xs:complexType>\n" +
                "<xs:complexType name=\"queryWeather\">\n" +
                "<xs:sequence>\n" +
                "<xs:element minOccurs=\"0\" name=\"city\" type=\"xs:string\"/>\n" +
                "<xs:element minOccurs=\"0\" name=\"name\" type=\"xs:string\"/>\n" +
                "</xs:sequence>\n" +
                "</xs:complexType>\n" +
                "<xs:complexType name=\"queryWeatherResponse\">\n" +
                "<xs:sequence>\n" +
                "<xs:element minOccurs=\"0\" name=\"Necklet\" type=\"tns:necklet\"/>\n" +
                "</xs:sequence>\n" +
                "</xs:complexType>\n" +
                "</xs:schema>\n" +
                "</wsdl:types>\n" +
                "<wsdl:message name=\"queryWater\">\n" +
                "<wsdl:part element=\"tns:queryWater\" name=\"parameters\"> </wsdl:part>\n" +
                "</wsdl:message>\n" +
                "<wsdl:message name=\"queryWeatherResponse\">\n" +
                "<wsdl:part element=\"tns:queryWeatherResponse\" name=\"parameters\"> </wsdl:part>\n" +
                "</wsdl:message>\n" +
                "<wsdl:message name=\"queryWaterResponse\">\n" +
                "<wsdl:part element=\"tns:queryWaterResponse\" name=\"parameters\"> </wsdl:part>\n" +
                "</wsdl:message>\n" +
                "<wsdl:message name=\"queryWeather\">\n" +
                "<wsdl:part element=\"tns:queryWeather\" name=\"parameters\"> </wsdl:part>\n" +
                "</wsdl:message>\n" +
                "<wsdl:portType name=\"WeatherService\">\n" +
                "<wsdl:operation name=\"queryWater\">\n" +
                "<wsdl:input message=\"tns:queryWater\" name=\"queryWater\"> </wsdl:input>\n" +
                "<wsdl:output message=\"tns:queryWaterResponse\" name=\"queryWaterResponse\"> </wsdl:output>\n" +
                "</wsdl:operation>\n" +
                "<wsdl:operation name=\"queryWeather\">\n" +
                "<wsdl:input message=\"tns:queryWeather\" name=\"queryWeather\"> </wsdl:input>\n" +
                "<wsdl:output message=\"tns:queryWeatherResponse\" name=\"queryWeatherResponse\"> </wsdl:output>\n" +
                "</wsdl:operation>\n" +
                "</wsdl:portType>\n" +
                "<wsdl:binding name=\"WeatherServiceServiceSoapBinding\" type=\"tns:WeatherService\">\n" +
                "<soap:binding style=\"document\" transport=\"http://schemas.xmlsoap.org/soap/http\"/>\n" +
                "<wsdl:operation name=\"queryWater\">\n" +
                "<soap:operation soapAction=\"\" style=\"document\"/>\n" +
                "<wsdl:input name=\"queryWater\">\n" +
                "<soap:body use=\"literal\"/>\n" +
                "</wsdl:input>\n" +
                "<wsdl:output name=\"queryWaterResponse\">\n" +
                "<soap:body use=\"literal\"/>\n" +
                "</wsdl:output>\n" +
                "</wsdl:operation>\n" +
                "<wsdl:operation name=\"queryWeather\">\n" +
                "<soap:operation soapAction=\"\" style=\"document\"/>\n" +
                "<wsdl:input name=\"queryWeather\">\n" +
                "<soap:body use=\"literal\"/>\n" +
                "</wsdl:input>\n" +
                "<wsdl:output name=\"queryWeatherResponse\">\n" +
                "<soap:body use=\"literal\"/>\n" +
                "</wsdl:output>\n" +
                "</wsdl:operation>\n" +
                "</wsdl:binding>\n" +
                "<wsdl:service name=\"WeatherServiceService\">\n" +
                "<wsdl:port binding=\"tns:WeatherServiceServiceSoapBinding\" name=\"WeatherServicePort\">\n" +
                "<soap:address location=\"http://localhost:8099/weather\"/>\n" +
                "</wsdl:port>\n" +
                "</wsdl:service>\n" +
                "</wsdl:definitions>";
    }
}

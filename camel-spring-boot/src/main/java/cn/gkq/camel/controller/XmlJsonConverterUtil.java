package cn.gkq.camel.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Author: liuwei
 * @Description: xml转json，json（带json数组）转xml工具类
 * @Date: 2021/8/16 10:05
 * @Version:
 */
@Slf4j
public class XmlJsonConverterUtil {

    /**
     * 根节点
     */
    private static final String ROOT_NODE = "root";

//    /**
//     * json转xml array 新增element_item_标签 存放array数据 xml转json时需去掉
//     */
//    private static final String ELEMENT_ITEM = "element_item_";

    private static final String DEFAULT_ELEMENT = "_defaultElement";
    /**
     * 自定义 xml元素Json_type
     */
    private static final String JSON_TYPE = "__json_type";

    /**
     *
     */
    private static final String JSON_ARRAY = "@array";

    /**
     *  json转xml（无xsd模式）
     * @param inputJson
     * @param rootEle
     * @param hasJsonType 是否生成jsonType标志
     * @return
     */
    @SneakyThrows
    public static String jsonToXmlNoXsd(String inputJson, String rootEle,Boolean hasJsonType) {
        Document document = jsonToXmlDoc(inputJson, rootEle,hasJsonType);
        return document.asXML();
    }

    /**
     * json 转xml 文档
     * @param inputJson
     * @param rootEle
     * @return
     */
    public static Document jsonToXmlDoc(String inputJson, String rootEle,Boolean hasJsonType) {
        if (!StringUtils.hasText(inputJson)) {
            log.error("{}", "参数不能为空");
        }
        Object jsonObj = null;
        try {
            // 格式化JSON数据，将json字符串转化为JSONObject并将数据的key以字母顺序排序
            jsonObj = JSON.parse(inputJson);
        } catch (Exception e) {
            log.error("{}", "参数格式有误");
        }
        // 创建dom对象
        Document document = DocumentHelper.createDocument();
        // 设置编码格式
        document.setXMLEncoding("UTF-8");
        // 添加父元素
        Element element = document.addElement(StringUtils.isEmpty(rootEle) ? ROOT_NODE : rootEle);
        // 添加子元素
        addChild(jsonObj, element, null,hasJsonType);
        return document;
    }



    /**
     * 判断是否json_array节点
     */
    private static boolean isArray(Element element) {
        return element.attribute(JSON_TYPE) != null && JSON_ARRAY.equals(element.attribute(JSON_TYPE).getValue());
    }

    /**
     * 添加子元素
     *
     * @param obj
     * @param childElement
     */
    private static void addChild(Object obj, Element childElement, Object parent,Boolean hasJsonType) {
        if (obj instanceof JSONObject) {
            //处理JSONObject
            if (parent instanceof JSONArray && hasJsonType) {
                childElement.addAttribute(JSON_TYPE, JSON_ARRAY);
            }
            jsonObjectToXml((JSONObject) obj, childElement,hasJsonType);
        } else if (obj instanceof JSONArray) {
            // 处理json数组 加type和array
            jsonArrayToXml((JSONArray) obj, childElement,hasJsonType);
        } else {
            // 不是json数组则为key添加值
            if (parent instanceof JSONArray && hasJsonType) {
                childElement.addAttribute(JSON_TYPE, JSON_ARRAY);
            }
            childElement.setText(String.valueOf(obj));
        }
    }

    /**
     * 处理JSONArray
     *
     * @param jsonArray
     * @param element
     */
    private static void jsonArrayToXml(JSONArray jsonArray, Element element,Boolean hasJsonType) {
        Element parent = element.getParent();
        if (parent == null) {
            parent = element;
            element = element.addElement(DEFAULT_ELEMENT);
        }
        parent.remove(element);
        for (Object value : jsonArray) {//设置子元素
            Element element1 = parent.addElement(element.getName());
            addChild(value, element1, jsonArray,hasJsonType);
        }
    }

    /**
     * 处理JSONObject
     *
     * @param jsonObject
     * @param element
     */
    private static void jsonObjectToXml(JSONObject jsonObject, Element element,Boolean hasJsonType) {
        jsonObject.forEach(
                (k, v) -> {
                    Element childElement = element.addElement(ToolMethods.replaceXmlName(k));
                    //设置子元素
                    addChild(v, childElement, jsonObject,hasJsonType);
                }
        );
    }


    public static void main(String[] args) {


    }
}

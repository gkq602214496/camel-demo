package cn.gkq.camel.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dom4j.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: liuwei
 * @Description: 封装Document并提供Document数据处理功能
 * @Date: 2021/8/23 9:36
 * @Version:
 */
@Data
@EqualsAndHashCode
public class DocumentContainer implements MessageContainer {

    private Document document;

    /**
     * 将xmlString转换为Document并封装到 DocumentContainer
     *
     * @param xmlString
     * @return
     */
    public DocumentContainer xmlToDocumentContainer(String xmlString) {
        try {
            this.setDocument(DocumentHelper.parseText(xmlString));
        } catch (DocumentException e) {
        }
        return this;
    }

    /**
     * 获取根节点
     *
     * @return
     */
    public Element getRootElement() {
        return document.getRootElement();
    }

    /**
     * 获取单个节点
     *
     * @param expression xpath值路径
     * @return
     */
    public Node getNode(String expression) {
        return document.selectSingleNode(expression);
    }

    /**
     * 获取多个节点
     *
     * @param expression xpath值路径
     * @return
     */
    public List<Node> getNodes(String expression) {
        return document.selectNodes(expression);
    }


    /**
     * 根据路径获取节点内容
     *
     * @param expression xpath值路径
     */
    public List<String> getNodesText(String expression) {
        return getNodes(expression).stream().map(Node::getText).collect(Collectors.toList());
    }


    /**
     * 替换xpath值路径对应值
     *
     * @return
     */
    public DocumentContainer convertValue(String expression, String newValue) {
        this.getNode(expression).setText(newValue);
        return this;
    }

    public static Document replaceElement(Document target,Document source,String xpath){
        Node node = target.selectSingleNode(xpath);
        if (node instanceof Element){
            Element ele = (Element) node;
            ele.clearContent();
            ele.elements().add((Element) source.getRootElement().clone());
        }
        return target;
    }

    /**
     * xml转换为DocumentContainer
     *
     * @param xmlString
     * @return
     */
    public static DocumentContainer build(String xmlString) {
        return getInstance().xmlToDocumentContainer(xmlString);
    }

    public static DocumentContainer getInstance() {
        return new DocumentContainer();
    }




    @Override
    public String toString() {
        return document.asXML();
    }
}

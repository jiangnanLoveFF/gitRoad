package com.hyb.xmlutils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public final class XMLUtils {
	/**
	 * 使用DOM解析XML，将获取Document封装为一个方法
	 */
	public static Document getXmlFile(String xmlFilePath) {
		// 1.建立DocumentBuilderFactory,用于取得DocumentBuilder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 2.通过DocumentBuilderFactory取得DocumentBuider
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		// 3.定义Document接口对象，通过DocumentBuilder类进行DOM树的转换操作
		Document doc = null;
		try {
			// 读取指定的XML文件
			doc = builder.parse(xmlFilePath);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

	// 创建Document接口对象
	public static Document creatDocument() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = builder.newDocument();
		return doc;
	}

	// 输出文档到文本中
	public static void outXmlFile(Document doc, String xmlPath) {
		// 输出文档到文本中
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = null;
		try {
			t = tf.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		// 设置编码
		t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		// 输出文档
		DOMSource source = new DOMSource(doc);
		// 指定输出位置
		StreamResult result = new StreamResult(new File(xmlPath));
		// 输出
		try {
			t.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}

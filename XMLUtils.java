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
	 * ʹ��DOM����XML������ȡDocument��װΪһ������
	 */
	public static Document getXmlFile(String xmlFilePath) {
		// 1.����DocumentBuilderFactory,����ȡ��DocumentBuilder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 2.ͨ��DocumentBuilderFactoryȡ��DocumentBuider
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		// 3.����Document�ӿڶ���ͨ��DocumentBuilder�����DOM����ת������
		Document doc = null;
		try {
			// ��ȡָ����XML�ļ�
			doc = builder.parse(xmlFilePath);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

	// ����Document�ӿڶ���
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

	// ����ĵ����ı���
	public static void outXmlFile(Document doc, String xmlPath) {
		// ����ĵ����ı���
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = null;
		try {
			t = tf.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		// ���ñ���
		t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		// ����ĵ�
		DOMSource source = new DOMSource(doc);
		// ָ�����λ��
		StreamResult result = new StreamResult(new File(xmlPath));
		// ���
		try {
			t.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}

package com.hyb.saxxml;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//定义SAX解析器
public final class SAXHandler extends DefaultHandler {

	@Override
	public void startDocument() throws SAXException {//文档开始
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
	}

	@Override
	public void endDocument() throws SAXException {//文档结束
		System.out.println("\n 文档读取结束...");
	}

	@Override//元素开始
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.print("<");
		System.out.print(qName);
		if(attributes != null) {
			for(int i = 0; i < attributes.getLength(); i++) {
				System.out.println("" + attributes.getQName(i) + "=\""
									  + attributes.getValue(i) + "\"");
			}
		}
		System.out.print(">");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.print("</");
		System.out.print(qName);
		System.out.print(">");
	}

	@Override//取得元素内容
	public void characters(char[] ch, int start, int length) throws SAXException {
		//输出内容
		System.out.print(new String(ch, start, length));
	}
	
	public static void SAXParse(String xmlFilePath) {
		//建立SAX解析工厂
				SAXParserFactory factory = SAXParserFactory.newInstance();
				//构造解析器
				SAXParser parser = null;
				try {
					parser = factory.newSAXParser();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				}
				//解析XML使用HANDLER
				try {
					parser.parse(xmlFilePath, new SAXHandler());
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
}

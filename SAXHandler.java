package com.hyb.saxxml;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//����SAX������
public final class SAXHandler extends DefaultHandler {

	@Override
	public void startDocument() throws SAXException {//�ĵ���ʼ
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
	}

	@Override
	public void endDocument() throws SAXException {//�ĵ�����
		System.out.println("\n �ĵ���ȡ����...");
	}

	@Override//Ԫ�ؿ�ʼ
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

	@Override//ȡ��Ԫ������
	public void characters(char[] ch, int start, int length) throws SAXException {
		//�������
		System.out.print(new String(ch, start, length));
	}
	
	public static void SAXParse(String xmlFilePath) {
		//����SAX��������
				SAXParserFactory factory = SAXParserFactory.newInstance();
				//���������
				SAXParser parser = null;
				try {
					parser = factory.newSAXParser();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				}
				//����XMLʹ��HANDLER
				try {
					parser.parse(xmlFilePath, new SAXHandler());
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
}

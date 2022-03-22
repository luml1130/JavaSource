package com.luml.java.data.xml;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Andre_lml
 * @date 2019/2/26
 * SAX实现方法：
 */
public class MyXmlReader2Sax extends DefaultHandler {

    java.util.Stack tags = new java.util.Stack();

    public MyXmlReader2Sax() {
         super();
    }

    public static void main(String[] args) {
        try{
            SAXParserFactory sf = SAXParserFactory.newInstance();
            SAXParser saxParser = sf.newSAXParser();
            MyXmlReader2Sax reader = new MyXmlReader2Sax();
            saxParser.parse(new InputSource("F:\\4-demo\\JAVASE\\JavaTest\\files\\school.xml"), reader);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void characters(char ch[], int start, int length){
        String tag = (String) tags.peek();
        if (tag.equals("NO")) {
            System.out.print("车牌号码：" + new String(ch, start, length));
        }
        if (tag.equals("ADDR")) {
            System.out.println("地址:" + new String(ch, start, length));
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName,Attributes attrs) {
        tags.push(qName);
    }

}

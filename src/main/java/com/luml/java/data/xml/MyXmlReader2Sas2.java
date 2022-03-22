package com.luml.java.data.xml;

import org.xml.sax.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Andre_lml
 * @date 2019/2/26
 */
public class MyXmlReader2Sas2 implements ContentHandler {
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
    //表示读取到第一个元素时开始做什么
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        System.out.print("<"+qName);
        for(int i=0;atts!=null&&i<atts.getLength();i++){
            String attName=atts.getQName(i);
            String attValueString=atts.getValue(i);
            System.out.print(" "+attName+"="+attValueString);
            System.out.print(">");
        }
    }
    //表示读取到第一个元素结尾时做什么
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print("</"+qName+">");
    }
    //表示读取字符串时做什么
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(new String(ch,start,length));
    }
    @Override
    public void setDocumentLocator(Locator locator) {}
    @Override
    public void startDocument() throws SAXException {}
    @Override
    public void endDocument() throws SAXException {}
    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException { }
    @Override
    public void endPrefixMapping(String prefix) throws SAXException {}
    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {}
    @Override
    public void skippedEntity(String name) throws SAXException {}
    @Override
    public void processingInstruction(String target, String data) throws SAXException {}
}

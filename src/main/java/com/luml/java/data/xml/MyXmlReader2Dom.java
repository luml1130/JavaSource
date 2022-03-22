package com.luml.java.data.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;

/**
 * DOM 实现方法
 * @author luml
 */
public class MyXmlReader2Dom {
    public static void main(String[] args) throws IOException{
        File file = new File("");
        String filePath = file.getCanonicalPath();
        ///System.out.println(filePath);
        try {
            File f = new File(filePath+"/files/school.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            NodeList nl = doc.getElementsByTagName("VALUE");
            for (int i = 0; i < nl.getLength(); i++) {
                System.out.print("车牌号码:"+ doc.getElementsByTagName("NO").item(i).getFirstChild().getNodeValue());
                System.out.println("车主地址:"+ doc.getElementsByTagName("ADDR").item(i).getFirstChild().getNodeValue());
            }
            }catch (Exception e){
            e.printStackTrace();
        }
    }

}

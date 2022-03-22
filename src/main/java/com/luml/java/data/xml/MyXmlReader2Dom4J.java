package com.luml.java.data.xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author Andre_lml
 * @date 2019/2/26
 * DOM4J实现方法
 */
public class MyXmlReader2Dom4J {
    public static void main(String[] args)throws IOException {
        File file = new File("");
        String filePath = file.getCanonicalPath();
        try {
            File f = new File(filePath + "/files/school.xml");
            SAXReader saxReader = new SAXReader();
            //取消DTD联网验证 避免链接不到网络时候报错
            saxReader.setValidation(false);
            saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

            Document doc = saxReader.read(f);
            Element rootElement = doc.getRootElement();
            for(Iterator i = rootElement.elementIterator("VALUE"); i.hasNext();){
                Element foo = (Element) i.next();
                System.out.print("车牌号码:" + foo.elementText("NO"));
                System.out.println("车主地址:" + foo.elementText("ADDR"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

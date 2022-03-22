package com.luml.java.data.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Andre_lml
 * @date 2019/2/26
 *  JDOM实现方法：
 */
public class MyXmlReader2JDom {

    public static void main(String[] args)throws IOException {
        File file = new File("");
        String filePath = file.getCanonicalPath();
        try {
            File f = new File(filePath + "/files/school.xml");
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(f);
            Element rootElement = doc.getRootElement();
            List allChildren = rootElement.getChildren();
            for (int i = 0; i < allChildren.size(); i++) {
                System.out.print("车牌号码:" + ((Element) allChildren.get(i)).getChild("NO").getText());
                System.out.println("车主地址:" + ((Element) allChildren.get(i)).getChild("ADDR").getText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

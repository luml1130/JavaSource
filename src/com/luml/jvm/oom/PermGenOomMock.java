package com.luml.jvm.oom;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2021/7/2 09:55
 * java -XX:PermSize=8m -XX:MapPermSize=8m com.luml.jvm.oom.PermGenOomMock
 */
public class PermGenOomMock {
    //https://www.cnblogs.com/paddix/p/5309550.html
    //1.6 1.7 1.8运行结果不一样
    public static void main(String[] args) {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while (true){
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("com.luml.jvm.oom.PermGenOomMock");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.luml.java.tools.hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;

import java.io.File;

/**
 * @author luml
 * @description
 * @date 2022/2/11
 */
public class FileNameUtilTest {

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0){
            return fileName.substring(fileName.lastIndexOf(".")+1);
        }else{
            return "";
        }
    }

    public static void main(String[] args) {
        File file = FileUtil.file("/opt/jdgui166.zip");
        System.out.println(getFileExtension(file));
        System.out.println(FileNameUtil.mainName(file));
        System.out.println(FileNameUtil.extName(file));
    }
}

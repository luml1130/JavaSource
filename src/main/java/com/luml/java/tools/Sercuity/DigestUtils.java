package com.luml.java.tools.Sercuity;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author luml
 * @description: DigestUtils-EMM
 * @date 2021/12/23
 */
public class DigestUtils {

    /**
     * 根据输入流获取文件的MD5 摘要
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String md5Hex(InputStream data) throws NoSuchAlgorithmException, IOException {
        return digestStream(data, "MD5");
    }
    /**
     * 根据输入流获取文件的SHA 摘要
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String shaHex(InputStream data) throws NoSuchAlgorithmException, IOException {
        return digestStream(data, "SHA");
    }
    /**
     * 获取文件的MD5 摘要
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String md5Hex(File data) throws NoSuchAlgorithmException, IOException {
        return digest(data, "MD5");
    }

    /**
     * 获取文件的SHA 摘要
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String shaHex(File data) throws NoSuchAlgorithmException, IOException {
        return digest(data, "SHA");
    }

    /**
     * 获取文件摘要，支持 MD5,SHA
     * @param data
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    private static String digest(File data, String algorithm) throws NoSuchAlgorithmException, IOException {
        FileInputStream fis = new FileInputStream(data);
        String ret = digestStream(fis, algorithm);
        IOUtils.closeQuietly(fis);
        return ret;
    }

    /**
     * 获取输入流摘要，支持 MD5,SHA
     * @param is
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    private static String digestStream(InputStream is, String algorithm) throws NoSuchAlgorithmException, IOException {
        MessageDigest msgDigest = MessageDigest.getInstance(algorithm);
        byte[] buffer = new byte[1024];
        int numRead = 0;
        while ((numRead = is.read(buffer)) > 0) {
            msgDigest.update(buffer, 0, numRead);
        }
        return new String(Hex.encodeHex(msgDigest.digest()));
    }

    /**
     * 获取byte数组md5摘要
     * @param data
     * @return
     */
    public static String md5Hex(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(data);
    }

    /**
     * 获取字符串md5摘要
     * @param data
     * @return
     */
    public static String md5Hex(String data) {
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(data.getBytes());
    }

    /**
     * 获取byte数组sha摘要
     * @param data
     * @return
     */
    public static String shaHex(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.shaHex(data);
    }

    /**
     * 获取字符串sha摘要
     * @param data
     * @return
     */
    public static String shaHex(String data) {
        return org.apache.commons.codec.digest.DigestUtils.shaHex(data.getBytes());
    }

}

package com.luml.java.tools.Sercuity.uem;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

/**
 * @author luml
 *  MD5加密：public static String digest(String text) {
 * 加密方法：public static String encryptMode(String source,String key) {
 * 解密函数：public static String decryptMode(String encodedSrc,String key) throws Exception{
 * 根据字符串生成密钥24位的字节数组：public static byte[] build3DesKey(String keyStr)
 * @description
 * @date 2022/6/26
 */
public class AlgorithmUtil {

    private static final String Algorithm = "DESede";
    private static final int RANDOM_LENGTH = 6;

    public static void main(String[] arg){
        String sourceString = "{  \"nonce\" : \"123\",  \"appId\" : \"com.nq.mdm\"}";
        String encryptKey = "VGk65wJgZtKuXcojgf4S";
        String decryptKey = "VGk65wJgZtKuXcojgf4S";
        try{
            String encodeString = AlgorithmUtil.encryptMode(sourceString,encryptKey);
            String decodeString = AlgorithmUtil.decryptMode(encodeString,decryptKey);
            System.out.println("encodeString: "+encodeString);
            System.out.println("decodeString: "+decodeString);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static String getRandomString(int length) {
        if(length < 2){
            length = RANDOM_LENGTH;
        }
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i ++) {
            sb.append(buffer.charAt(random.nextInt(range)));
        }
        return sb.toString();
    }

    /**
     * MD5加密
     */
    public static String digest(String text) {
        try {
            byte[] b = MessageDigest.getInstance("md5").digest(text.getBytes());
            String hs = "";
            String stmp = "";
            for (int n = 0; n < b.length; n++) {
                stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
                if (stmp.length() == 1) {
                    hs = hs + "0" + stmp;
                } else {
                    hs = hs + stmp;
                }
                return hs.toLowerCase();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        //TODO
        return "";
    }

    /**
     * 加密方法
     * @param source   源数据的字节数组   源数据的字节数组
     * @return
     */
    public static String encryptMode(String source,String key) {
        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(build3DesKey(key), Algorithm);
            // 实例化Cipher
            Cipher cipher = Cipher.getInstance(Algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] secretArr = cipher.doFinal(source.getBytes());
            String newString = Base64.getEncoder().encodeToString(secretArr);
            return newString;
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 解密函数
     * @param     encodedSrc   密文的字节数组
     * @return
     */
    public static String decryptMode(String encodedSrc,String key) throws Exception{
        SecretKey deskey = new SecretKeySpec(build3DesKey(key), Algorithm);
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.DECRYPT_MODE, deskey);
        byte[] encodedStr = Base64.getDecoder().decode(encodedSrc);
        byte [] result = c1.doFinal(encodedStr);
        return new String(result);
    }

    /**
     * 根据字符串生成密钥24位的字节数组
     * @param keyStr
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException {
        byte[] key = new byte[24];
        byte[] temp = keyStr.getBytes("UTF-8");
        if (key.length > temp.length) {
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    }

    /**
     * grantservice
     * pinHash由pinCode经过hash算法获得：
     *              String pinHash =AlgorithmUtil.digest(otaRequestDomain.getPincode());
     * @param activeRequestDomain
     * @return
     * @throws Exception
     */
    /*public ActiveResponseDomain initCredentialAndSave(ActiveRequestDomain activeRequestDomain) throws Exception{
        //Proxy收到激活请求，通过principal、tenantId (default tenantId是mdm) 找对应的pinHash，用pinHash解密，解密成功后得到的nonce与明文的nonce比对，用解密后得到的时间戳判断当前Request是否过期.
        String rand = activeRequestDomain.getRand();
        String encryptContent = activeRequestDomain.getContent();//加密内容
        String decryptContent;//解密内容
        try{
            //用pincode解密加密内容
            decryptContent = AlgorithmUtil.decryptMode(encryptContent, pincodeOM.getPincode());
        }catch (Exception ex){
            logger.error(requestDataRecorder.getLogInfo() +
                    TransactionErrorType.ENCRYPT_CONTENT_ERROR.getDesc());
            throw new InvalidQueryParameterValue(TransactionErrorType.ENCRYPT_CONTENT_ERROR);
            //加密内容不匹配
        }

        if(decryptContent.equals(rand)){ //匹配
            //Step 4:  解密成功。会为设备颁发通行证－－proxyAccessCredential，是加密下一个请求的key；
            ProxyGrantOM oldGrantOM = proxyGrantRepository.findByHostId(activeRequestDomain.getHostId());
            String credential;
            if(null == oldGrantOM){
                credential = AlgorithmUtil.getRandomString(algorithmSettings.getCredential());
                proxyGrantOM.setCredentialContent(credential);
                proxyGrantRepository.saveAndFlush(proxyGrantOM);
            }else{
                credential = oldGrantOM.getCredentialContent();
            }
            //Step 5: 用pinHash做为种子生成密钥加密proxyAccessCredential，返回给客户端。
            String encryptCredential = AlgorithmUtil.encryptMode(credential, pincodeOM.getPincode());
            responseDomain.setCredential(encryptCredential);
        }
    }*/


}

package com.luml.sence.encrypt.duichen_no;

import com.luml.java.keyword.Constant;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/** */

/**
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 */
public class RSAUtil_UEM {

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 固定公钥
     */
    public static final String PUBLIC_KEY_VAL = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDnT734M3vPLiQTgyOew4VOclY25r5BDsNNhJKIOaoTHA7hwU416gXauuKmFZX525JSvm2r9C3wozy4dvbDESfghztzYK3x2nMHosuh1R3JPrbCfb3e5qJmV/Ai/hEYMWtSh+lP1OzFpZPyKaw6Wz0o//GEu13Y8+UhugTEtdCkmQIDAQAB";
    /**
     * 固定私钥
     */
    public static final String PRIVATE_KEY_VAL = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOdPvfgze88uJBODI57DhU5yVjbmvkEOw02Ekog5qhMcDuHBTjXqBdq64qYVlfnbklK+bav0LfCjPLh29sMRJ+CHO3NgrfHacweiy6HVHck+tsJ9vd7momZX8CL+ERgxa1KH6U/U7MWlk/IprDpbPSj/8YS7Xdjz5SG6BMS10KSZAgMBAAECgYAVaOoaeRlymcC5FaBMZ6iFFEwqlLzzZJxrJmQxu7SkRCqUdsH4d4rHLhkgaksRb2NhyUJ5cgZ2iYVrRYedko7wKkebGYZWMgSzMF+iSnmR+41KJJCw/CWa8uLWRaupB/JZ+XKMibtWbFPYMcLze21Ph1Ke32EOjeNiVY52aSpmKQJBAPVzkhUz+vdGklj74ZVYpnPX6pyV62jcOz2u2Liinispg4BnvEj/bYN+P3SHCk5KaMPD7uiwB9FKBjCEoseSVb8CQQDxQJrwFvgjc9BQ3ecnuHSzxjW+bjHqYNYWCzjcxbjA/JmcCWJpPfHDn9jKc4wezwHgFgctwTudP7AMzQbaNIunAkEAttLZmb95DJxdP7iF44b/nMPT9cRZb2azHZPRy10dMQsf7xHVlg3j1ZDA1RwM2hnkhQS5PEp52DMp/xOcZ8ig4wJAAMfIuIYaf2LchaZyHPrHxd0aR2dr2eo+Rwv6PxyUoGswxARzwQtmQM+/j8gX5/Gbe/IWS9uYKcYO97uiDgqleQJBAM3jKodJlAkROx3kOEGmZBANJrtHJtl9WqW4l8vSrtV4QOqdjHrdnDB2OetPO39Pyx0KzWUzvYBfvjCn0zPGY4M=";

    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param data
     *            已加密数据
     * @param privateKey
     *            私钥(BASE64编码)
     *
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param data
     *            已加密数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @param sign
     *            数字签名
     *
     * @return
     * @throws Exception
     *
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64.decodeBase64(sign));
    }

    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData
     *            已加密数据
     * @param privateKey
     *            私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encryptedData
     *            已加密数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data
     *            源数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param data
     *            源数据
     * @param privateKey
     *            私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap
     *            密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap
     *            密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * java端公钥加密
     */
    public static String encryptedDataOnJava(String data, String PUBLICKEY) {
        try {
            data = Base64.encodeBase64String(encryptByPublicKey(data.getBytes(), PUBLICKEY));
        } catch (Exception e) {
            //log.error("encryptedDataOnJava error", e);
        }
        return data;
    }

    /**
     * java端私钥解密
     */
    public static String decryptDataOnJava(String data, String PRIVATEKEY) {
        String temp = null;
        try {
            byte[] rs = Base64.decodeBase64(data);
            temp = new String(RSAUtil_UEM.decryptByPrivateKey(rs, PRIVATEKEY),"UTF-8");
        } catch (Exception e) {
            //log.error("decryptDataOnJava error", e);
        }
        return temp;
    }

    public static String decryptData(String data, String PRIVATEKEY) throws Exception{
        if(StringUtils.isBlank(data)){
            return data;
        }
        byte[] rs = Base64.decodeBase64(data);
        return new String(RSAUtil_UEM.decryptByPrivateKey(rs, PRIVATEKEY),"UTF-8");
    }

    /**
     * 校验WebToken
     * @param webToken
     * @return
     */
	public static boolean validWebToken(String webToken){
		// 为了 应对 EI-2897 深圳办公厅UEM项目web应用程序漏洞扫描修复 
		// 此 webToken 是管理员没有登陆时，由WebUI 生成的 临时 token，只有 由前端发起的请求才可以允许访问
		if(StringUtils.isEmpty(webToken)){
			return false; 
		}else{
			try {
//				if(webToken.contains("%20") || webToken.contains("%2B") || webToken.contains("%2F") || webToken.contains("%3D")){
//					// webToken = URLDecoder.decode(webToken);
//				}
				
				String decryptWebToken = RSAUtil_UEM.decryptData(webToken, RSAUtil_UEM.PRIVATE_KEY_VAL);
				if(!Constant.WEBUI_ACCESS_TOKEN_KEY.equalsIgnoreCase(decryptWebToken)){
					///log.error("error.decryptWebToken:" + webToken + "decrypt:" + decryptWebToken);
					return false;
				}
			} catch (Exception e) {
				//log.error("error.decryptWebToken:" + webToken + " message:" + e.getMessage() ,e);
				return false; 
			}
		}	
		return true;
	}
	
    public static void main(String[] args) throws Exception {
//        Map<String, Object> keyMap = RSAUtil.genKeyPair();
//        String publicKey = RSAUtil.getPublicKey(keyMap);
//        System.out.println("--------publicKey--------");
//        System.out.println(publicKey);
//        String privateKey = RSAUtil.getPrivateKey(keyMap);
//        System.out.println("--------privateKey----------");
//        System.out.println(privateKey);

//        System.err.println("公钥加密——私钥解密");
//        String source = "你好123";
//        System.out.println("原文字：" + source);
//        byte[] data = source.getBytes();
//        byte[] encodedData = RSAUtil.encryptByPublicKey(data, publicKey);
//        String strEncodedData = Base64.encodeBase64String(encodedData);

        String strEncodedData = "ghBhLHn+bVLSeJjQx8yVGZX4VlTKZFkoDFiiY2F3wa5ASed/kA+MQBAab5Qg4TuQi9MT+zCdYVsKloBu2dCBqmdmxNHEyDhtRWObA6mH2MB3EzmahwNc5KMUT6cVfX0StoHqWnozYWc2UWi6PjwUadAS6eRquv9AjI2FzMaP1Tk=";
        System.out.println("加密后：" + strEncodedData);

        byte[] decodedData = RSAUtil_UEM.decryptByPrivateKey(Base64.decodeBase64(strEncodedData), PRIVATE_KEY_VAL);
        String target = new String(decodedData);
        System.out.println("解密后: " + target);
    }
}
package com.bymz.tasktool.modules.security.util;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

/**
 * 代码来自：https://www.cnblogs.com/pcheng/p/9629621.html博客
 */
public class RsaUtil {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    public static String publicKey;
    public static String privateKey;
    static {
        init();
    }

    private static void init(){
        KeyPair keyPair = null;
        try{
            keyPair = getKeyPair();
            privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
            publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * RSA加密
     *
     * @param data 待加密数据
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64.encodeBase64String(encryptedData));
    }

    /**
     * RSA解密
     *
     * @param data 待解密数据
     * @param privateKey 私钥
     * @return
     */
    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, "UTF-8");
    }

    /**
     * 签名
     *
     * @param data 待签名数据
     * @param privateKey 私钥
     * @return 签名
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     *
     * @param srcData 原始字符串
     * @param publicKey 公钥
     * @param sign 签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }


    public static String getAesKey(HttpServletRequest request){
        return request.getSession().getAttribute("AES_KEY").toString();
    }

    public static void main(String[] args) {
        try {
            // 生成密钥对
            KeyPair keyPair = getKeyPair();
            String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
            String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
            System.out.println("私钥:" + privateKey);
            System.out.println("公钥:" + publicKey);

            privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJt8JGeBNi1VbH0Gh8yLIRUunytnGKXl8pf37ITq3JNouLjEi4JU13fEzBS0yAaHMnEnt8D9K3dsihz6v73qsIhpYVLGKWBe4vG5pDnr+3WHr+sgJTpT/Wl0wANjUI1TewzAAbEILee8q+2Tq4MdMnVHDFoSkswF7RKjvwVjn1YzAgMBAAECgYA0DCBbjYNHNZs1VuRKciBG6O5bJw23KvxEJrfD3qKrrZh4MULKBACElRoDrGhw1vzpp/P45EaSmYC6Jq3csx0YZyuWSvyi9+/SghW/tWA51dmzK61O+ZtzBGE03pXmiGw/j10LAOnIP81Kmeysah+mcVV0M+WJbKyPNzLSgK6AUQJBAMiWB2qVenKI8SI04rqV9cSm54o9g6fsgSK7HKOgOn1cJV+fQ8QbpmClWxSWYDeEpFZjIH0HB7E1EeigctrzF80CQQDGcHGgTHucFMxoYaqpYWaUJ1GdCobpL0N7iSVkSUVjwMGqgCHoCdcCwuLKPbHWEJP95Sniiu9FdKA6o/qPMiX/AkEAnRmsS1Uxpv0MZy8mmN6Us+XJGOOe9ulsJsiH4LvAs6h0+RSjHbhOMgmwcUppp3HyyFow+tuDsc4P7bOk4UldzQJAQz2Q0w93hzDo/qmxiYmOl78nAX6yrksso6yaNfw5/g4v0ZVvZ9AFwlLFRGBZZT7i1tKTOcd4QmG9dYFhAqqsgwJAM1ob7iM5mB62m8IXn9PDMUOfUdtbnwDRBe4KT29Ugi18uoU0NE5TODlnXuk9IOrxzhKtEoBpAw4GmEU8fwcxlw==";
            publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbfCRngTYtVWx9BofMiyEVLp8rZxil5fKX9+yE6tyTaLi4xIuCVNd3xMwUtMgGhzJxJ7fA/St3bIoc+r+96rCIaWFSxilgXuLxuaQ56/t1h6/rICU6U/1pdMADY1CNU3sMwAGxCC3nvKvtk6uDHTJ1RwxaEpLMBe0So78FY59WMwIDAQAB";
            // RSA加密
            String data = "待加密的文字内容";
            String encryptData = encrypt(data, getPublicKey(publicKey));
            System.out.println("加密后内容:" + encryptData);
            // RSA解密
            String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
            System.out.println("解密后内容:" + decryptData);



            String password = "B/P62hlbplCrZYxki1ZziwbZ4vhNFPSBfje5t2yvqLjEzwjDrWjTp12GFOsABIAiP/npIfhZ9w1Q+vGc90xpJRt96v+SCRMIGvqZIpVHofSO+Js3KFC8sTX+WcNu0hy621NX+vcVllZoQ4k23q8oPUckGklaOFqR2ac3AbwZDRI=";
            decryptData = decrypt(password, getPrivateKey(privateKey));
            System.out.println("解密后内容:" + decryptData);

            // RSA签名
            String sign = sign(data, getPrivateKey(privateKey));
            // RSA验签
            boolean result = verify(data, getPublicKey(publicKey), sign);
            System.out.print("验签结果:" + result);




        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }
}
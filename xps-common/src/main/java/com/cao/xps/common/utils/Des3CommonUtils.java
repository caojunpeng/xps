package com.cao.xps.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * 字符串加解密工具类
 */
public class Des3CommonUtils {


    private static final Logger logger = LoggerFactory.getLogger(Des3CommonUtils.class);

    // 定义加密算法DESede(即3DES)
    private static final String Algorithm = "DESede";
    // 秘钥
    private static final String PASSWORD_CRYPT_KEY = "peng";

    /**
     * 加密方法
     *
     * @param originalText
     *            源数据的字节数组
     * @return
     */
    public static byte[] encryptMode(byte[] originalText) {
        try {
            SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm); // 生成密钥
            Cipher cipher = Cipher.getInstance(Algorithm); // 实例化负责加密/解密的Cipher工具类
            cipher.init(Cipher.ENCRYPT_MODE, deskey); // 初始化为加密模式
            return cipher.doFinal(originalText);
        } catch (Exception e) {
            logger.error("des普通加密异常"+e.getMessage(),e);
        }
        return null;
    }
    /**
     * 加密方法  返回字符串
     * @param originalText
     *            源数据的字节数组
     * @return
     */
    public static String encryptModeStr(byte[] originalText) {
        try {
            SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm); // 生成密钥
            Cipher cipher = Cipher.getInstance(Algorithm); // 实例化负责加密/解密的Cipher工具类
            cipher.init(Cipher.ENCRYPT_MODE, deskey); // 初始化为加密模式
            String str = byte2Hex(cipher.doFinal(originalText));
            return str;
        } catch (Exception e) {
            logger.error("des普通加密异常"+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 解密函数
     * @param cipherText
     *            密文的字节数组
     * @return
     */
    public static byte[] decryptMode(byte[] cipherText) {
        try {
            SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);
            Cipher cipher = Cipher.getInstance(Algorithm);
            cipher.init(Cipher.DECRYPT_MODE, deskey); // 初始化为解密模式
            return cipher.doFinal(cipherText);
        } catch (Exception e) {
            logger.error("des普通解密异常"+e.getMessage(),e);
        }
        return null;
    }
    /**
     * 解密函数
     * @return
     */
    public static String decryptMode(String cipherHexText) {
        try {
            byte[] cipherText = hexStr2byte(cipherHexText);
            SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);
            Cipher cipher = Cipher.getInstance(Algorithm);
            cipher.init(Cipher.DECRYPT_MODE, deskey); // 初始化为解密模式
            byte cipherByte [] = cipher.doFinal(cipherText);
            return new String(cipherByte,"utf-8");
        } catch (Exception e) {
            logger.error("des字符串普通解密异常"+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 16进制转字符串
     * @param s
     * @return
     */
    public static byte[] hexStr2byte(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return baKeyword;
    }

    /*
     * 根据 密钥字符串生成密钥字节数组
     *
     * @param keyStr 密钥字符串
     *
     * @return
     *
     * @throws UnsupportedEncodingException
     */
    public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException {
        byte[] key = new byte[24]; // 声明一个24位的字节数组，默认里面都是0
        byte[] temp = keyStr.getBytes("UTF-8"); // 将字符串转成字节数组
        /*
         * 执行数组拷贝 System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
         */
        if (key.length > temp.length) {
            // 如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            // 如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    }

    // 转换成十六进制字符串
    public static String byte2Hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
            if (n < b.length - 1)
                ;
        }
        return hs.toUpperCase();
    }

    public void getTest(){
        String s="";
        s+="";
    }

}

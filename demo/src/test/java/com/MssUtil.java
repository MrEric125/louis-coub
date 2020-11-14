package com;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 移动销售系统工具类
 * 
 * @author zhangbo 01084074
 *
 */
public class MssUtil {
	private MssUtil(){}
	private static final Logger logger = LoggerFactory.getLogger(MssUtil.class);
	private static final String UTF8 = "utf-8";
	/** 明文秘钥 */
	private static final String SECRET_KEY = "mss-APP";
	
	/** 向量：可有可无 终端后台也要约定 */
	private static final String IV = "01234567"; 
    
    /**
     * 初始化秘钥
     * 
     * @return	对明文秘钥进行MD5加密，加密后字符串当做秘钥使用
     * 
     * @author zhangbo 01084074 on 2016-09-12
     */
    private static String initSecretKey() {
        return mD5Encrypt(SECRET_KEY);
    }
    
    /**
     * MD5加密
     * 
     * @param str	被加密字符串
     * @return	加密后字符串
     * 
     * @author zhangbo 01084074 on 2016-10-25
     */
    public static String mD5Encrypt(String str) {
    	char[] hexDigits={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = str.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str1 = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str1[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str1[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str1);
        } catch (Exception e) {
        	logger.warn("Initialize SecretKey failed!",e);
            return "";
        }
    }
    
	/**
	 * 字符串加密
	 * 
	 * @param str	被加密字符串
	 * @return	加密后字符串
	 * 
	 * @author zhangbo 01084074 on 2016-09-10
	 */
	public static String encrypt(String str) {
		try {
			Key deskey ; 
			String key = initSecretKey();
			DESedeKeySpec spec = new DESedeKeySpec(key.getBytes(UTF8)); 
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede"); 
			deskey = keyfactory.generateSecret( spec); 
			Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding"); 
			IvParameterSpec ips = new IvParameterSpec( IV.getBytes()); 
			cipher.init(Cipher. ENCRYPT_MODE, deskey, ips); 
			byte[] encryptData = cipher.doFinal( str.getBytes(UTF8)); 
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < encryptData.length; i++) {
                String hex = Integer.toHexString(encryptData[i] & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
            }
			return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        	logger.warn("Encryption No Such Algorithm!",e);
        } catch (NoSuchPaddingException e) {
        	logger.warn("Encryption No Such Padding!",e);
        } catch (InvalidKeyException e) {
        	logger.warn("Encryption InvalidKey failed!",e);
        } catch (UnsupportedEncodingException e) {
        	logger.warn("Encryption Unsupported Encoding!",e);
        } catch (IllegalBlockSizeException e) {
        	logger.warn("Encryption Illegal Block Size!",e);
        } catch (BadPaddingException e) {
        	logger.warn("Encryption Bad Padding!",e);
        } catch (InvalidKeySpecException e) {
        	logger.warn("Encryption InvalidKey Spec!",e);
		} catch (InvalidAlgorithmParameterException e) {
			logger.warn("Encryption Invalid Algorithm Parameter!",e);
		}
		return null;
    }
	
	/**
	 * 字符串解密
	 * 
	 * @param str	被解密字符串
	 * @return 解密后字符串
	 * 
	 * @author zhangbo 01084074 on 2016-09-10
	 */
	public static String decrypt(String str) {
		if (str.length() < 1)
            return null;
        byte[] byteRresult = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            int high = Integer.parseInt(str.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(str.substring(i * 2 + 1, i * 2 + 2), 16);
            byteRresult[i] = (byte) (high * 16 + low);
        }
		try {
			Key deskey ; 
			String key = initSecretKey();
			DESedeKeySpec spec = new DESedeKeySpec(key.getBytes(UTF8)); 
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede"); 
			deskey = keyfactory. generateSecret( spec); 
			Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" ); 
			IvParameterSpec ips = new IvParameterSpec( IV.getBytes()); 
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips); 
			byte[] decryptData = cipher.doFinal(byteRresult); 
			return new String( decryptData, UTF8);
        } catch (NoSuchAlgorithmException e) {
        	logger.warn("Encryption No Such Algorithm!",e);
        } catch (NoSuchPaddingException e) {
        	logger.warn("Encryption No Such Padding!",e);
        } catch (InvalidKeyException e) {
        	logger.warn("Encryption InvalidKey failed!",e);
        } catch (IllegalBlockSizeException e) {
        	logger.warn("Encryption Illegal Block Size!",e);
        } catch (BadPaddingException e) {
        	logger.warn("Encryption Bad Padding!",e);
        } catch (UnsupportedEncodingException e) {
        	logger.warn("Encryption Unsupported Encoding!",e);
		} catch (InvalidKeySpecException e) {
			logger.warn("Encryption InvalidKey Spec!",e);
		} catch (InvalidAlgorithmParameterException e) {
			logger.warn("Encryption Invalid Algorithm Parameter!",e);
		}
        return null;
    } 
	


	
	/**
	 * 初始化token
	 * 
	 * @return
	 * 
	 * @author zhangbo 01084074 on 2016-09-10
	 */
	public static String initToken(){
		// 使用uuid来初始化token
		UUID uuid = UUID.randomUUID();
		
		String str = uuid.toString();

		return str.replace("-", "");
	}
	
}

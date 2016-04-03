package com.jonnyLee.jac;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * copyright    <a href="http://www.ypxgt.com/">一品效果图</a>
 * <pre>
 *     author      jonnyLee
 *     date        4/3/16
 *     email       2355952129qq.com
 *     desc
 * </pre>
 */
public class AES {

    public final String key = "CITM";

    public final String word = "hello World  CTIM";


    @Test
    public void testAES() throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, UnsupportedEncodingException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(key.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        //encrypt
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] result = cipher.doFinal(word.getBytes());
        System.out.println(Base64.encodeBase64String(result));

        //decrypt
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        System.out.println(new String(cipher.doFinal(result)));
    }



}

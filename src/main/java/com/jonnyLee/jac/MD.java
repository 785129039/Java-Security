package com.jonnyLee.jac;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * copyright    jonnyLee
 * <pre>
 *     author      jonnyLee
 *     date        3/12/16
 *     email       2355952129qq.com
 *     desc
 * </pre>
 */
public class MD {

    public final String tag = "jonnyLee pcs";


    public final String key = "ctim";


    @Test
    public void testMD5() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(tag.getBytes());
        byte[] res = messageDigest.digest();
        System.out.println(Hex.encodeHexString(res).length());
        System.out.println(Hex.encodeHexString(res));
        System.out.println(Hex.encodeHexString(DigestUtils.getMd5Digest().digest(tag.getBytes())));
        System.out.println(Hex.encodeHexString(DigestUtils.getMd5Digest().digest(tag.getBytes())));
        System.out.println(DigestUtils.getMd5Digest().getDigestLength());
    }


    @Test
    public void testHMAC() throws NoSuchAlgorithmException, InvalidKeyException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = keyGenerator.generateKey();
        System.out.println(new String (Base64.encodeBase64(secretKey.getEncoded())));


//        secretKey = new SecretKeySpec(secretKey.getEncoded(), secretKey.getAlgorithm());
        secretKey = new SecretKeySpec(key.getBytes(), secretKey.getAlgorithm());

        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        byte[] result = mac.doFinal(tag.getBytes());
        System.out.println(Hex.encodeHexString(result));

    }

}

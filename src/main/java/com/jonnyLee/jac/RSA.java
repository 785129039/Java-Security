package com.jonnyLee.jac;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import sun.security.rsa.RSAKeyPairGenerator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

/**
 * copyright    <a href="http://www.ypxgt.com/">一品效果图</a>
 * <pre>
 *     author      jonnyLee
 *     date        4/3/16
 *     email       2355952129qq.com
 *     desc
 * </pre>
 */
public class RSA {
    public final String word = "hello World  CTIM";


    @Test
    public void testRSA() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        RSAKeyPairGenerator rsaKeyPairGenerator = new RSAKeyPairGenerator();
        KeyPair keyPair = rsaKeyPairGenerator.generateKeyPair();

        System.out.println(Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
        System.out.println("=========");
        System.out.println(Base64.encodeBase64String(keyPair.getPrivate().getEncoded()));
        System.out.println("=========");


        //public key encrypt    client
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] res = cipher.doFinal(word.getBytes());
        System.out.println(Base64.encodeBase64String(res));
        System.out.println("--------");
        //private key decrypt   server
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        System.out.println(new String(cipher.doFinal(res)));


    }

    @Test
    public void testRSA2() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        RSAKeyPairGenerator rsaKeyPairGenerator = new RSAKeyPairGenerator();
        KeyPair keyPair = rsaKeyPairGenerator.generateKeyPair();

        System.out.println(Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
        System.out.println("=========");
        System.out.println(Base64.encodeBase64String(keyPair.getPrivate().getEncoded()));
        System.out.println("=========");


        //private key encrypt       server
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
        byte[] res = cipher.doFinal(word.getBytes());
        System.out.println(Base64.encodeBase64String(res));
        System.out.println("--------");
        //public key decrypt         client
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPublic());
        System.out.println(new String(cipher.doFinal(res)));


    }
}

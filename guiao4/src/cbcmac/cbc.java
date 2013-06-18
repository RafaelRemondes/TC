/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.cbcmac;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;

/**
 *
 * @author rafaelremondes
 */
public class cbc {

  static int n;
  static byte[] iv = {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0};
  static byte[] ivA;
  static SecretKey key;
  static byte[] plainText2 = "1234567812345678".getBytes();
  static byte[] plainText = "ifyourtimetoyouisworthsavingthenyoubetterstartswimmingoryouwillsinklikeastoneforthetimestheyareachanging".getBytes();

  public static boolean verifier(byte[] tag, List<byte[]> m) {
    List<byte[]> tags = cbcMac(m, 0);
    byte[] tag1 = tags.get(tags.size() - 1);
    return Arrays.equals(tag, tag1);
  }

  public static void genKey() throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException {
    KeyGenerator keyGen = KeyGenerator.getInstance("DES");
    key = keyGen.generateKey();
  }

  public static byte[] enc(byte[] input, SecretKey key) throws NoSuchAlgorithmException, ShortBufferException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
    Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] cipherText = cipher.doFinal(input);
    return cipherText;
  }

  public static List<byte[]> splitBlock(byte[] b, int i) {
    List<byte[]> lista = new ArrayList<>();
    int x = 0;
    while (x < b.length) {
      int j = 0;
      byte[] array = new byte[8];
      for (j = 0; j < 8; j++) {
        if (x + j < b.length) {
          array[j] = b[x + j];
        }
      }
      lista.add(array);
      x += j;
    }
    return lista;
  }

  public static byte[] xor(byte[] a, byte[] b) {
    byte[] array = new byte[a.length];
    for (int i = 0; i < a.length; i++) {
      array[i] = (byte) (a[i] ^ b[i]);
    }
    return array;
  }

  public static byte[] aux(byte[] plainText, byte[] tag) throws NoSuchAlgorithmException, ShortBufferException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
    byte[] array = xor(plainText, tag);
    array = enc(array, key);
    return array;
  }

  public static byte[] genIV() {
    byte[] b = new byte[8];
    Random rndm = new Random();
    rndm.nextBytes(b);
    return b;
  }

  public static List<byte[]> cbcMac(List<byte[]> blocks, int mod) {
    List<byte[]> tags = new ArrayList<>();
    byte[] tag = new byte[1];
    if (mod == 1) {
      tag = genIV();
      ivA = tag;
    } else {
      tag = iv;
    }
    for (byte[] b : blocks) {
      try {
        tag = aux(b, tag);
        tags.add(tag);
      } catch (NoSuchAlgorithmException | ShortBufferException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException ex) {
        Logger.getLogger(cbc.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return tags;
  }

  public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException {
    List<byte[]> blockList = splitBlock(plainText, 8);
    List<byte[]> forgedMessage = new ArrayList<>();
    genKey();
    List<byte[]> tags1 = cbcMac(blockList, 0);
    List<byte[]> tags2 = cbcMac(blockList,1);
    boolean v = verifier(tags1.get(tags1.size() - 1), blockList);
    System.out.println("Real cbc-mac: " + v);
    byte[] fMessage = xor(tags1.get(0), blockList.get(1));
    forgedMessage.add(fMessage);
    for (int i = 2; i <= blockList.size() - 1; i++) {
      fMessage = blockList.get(i);
      forgedMessage.add(fMessage);
    }
    fMessage = xor(tags1.get(tags1.size() - 1), blockList.get(0));
    forgedMessage.add(fMessage);
    v = verifier(tags1.get(0), forgedMessage);
    System.out.println("Forged cbc-mac:" + v);
    List<byte[]> ivForgedMessage = blockList;
    ivForgedMessage.set(0, xor(ivForgedMessage.get(0),ivA));
    v = verifier(tags2.get(tags2.size()-1),ivForgedMessage);
    System.out.println("Forged iv, cbc-mac:" + v);

  }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cbcmac;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
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
  static byte[] iv;
  static SecretKey key; 
  static byte[] plainText = "ifyourtimetoyouisworthsavingthenyoubetterstartswimmingoryouwillsinklikeastoneforthetimestheyareachanging".getBytes();
  
  
   public static void genKey() throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		    key = keyGen.generateKey();
	    }

  public static byte[] enc(byte[] input, SecretKey key) throws NoSuchAlgorithmException, ShortBufferException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE,key);   
    byte[] cipherText = cipher.doFinal(input);
    return cipherText;
  }
  
  public static List<byte[]> splitBlock(byte[] b,int i){
    List<byte[]> lista = new ArrayList<>();
    int x = 0;
    while(x<b.length){
      int j = 0;
      byte[] array = new byte[8];
      for(j=0;j<8;j++){
        if(x+j<b.length){
          array[j] =  b[x+j];
        }
      }
      lista.add(array);
      x+=j;
    }
    return lista;
  }
  
  public static byte[] xor(byte[] a, byte[] b){
    byte[] array = new byte[a.length];
    System.out.println(new String(a));
    System.out.println(new String(b));

    for( int i = 0; i < a.length; i++ ) {
      array[i] = (byte)(a[i] ^ b[i]);
    }
    return array;
  }
  
  public static byte[] aux(byte[] plainText, byte[] tag) throws NoSuchAlgorithmException, ShortBufferException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException{
    byte[] array = xor(plainText, tag);
    array = enc(array,key);
    return array;
  }
  
  public static void genIV(){
    iv = new byte[8];
    Random rndm = new Random();
    rndm.nextBytes(iv);
  }
  
  public static byte[] cbcMac(List<byte[]> blocks){
    byte[] tag = iv;
    for(byte[] b : blocks){
      try {
        tag = aux(b,tag);
      } catch (NoSuchAlgorithmException | ShortBufferException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException ex) {
        Logger.getLogger(cbc.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return tag;
  }
    
  public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, KeyStoreException, IOException, CertificateException{
    List<byte[]> blockList = splitBlock(plainText, 8);
    genKey();
    genIV();
    byte[] mac = cbcMac(blockList);
    System.out.println(mac);
  }
  
}

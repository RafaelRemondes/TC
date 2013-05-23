
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rafaelremondes
 */
public class ECB {
  
  public static int heigth;
  public static int width;
  public static BufferedImage img;
  
  
   public static SecretKey genKey() throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		    SecretKey key = keyGen.generateKey();
	      return key;
	    }

  static public byte[] enc(byte[] input, SecretKey key) throws NoSuchAlgorithmException, ShortBufferException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE,key);   
    byte[] cipherText = cipher.doFinal(input);
    return cipherText;
  }
  
  
 public static int[] byteToInt(byte[] data) {
    int[] ints = new int[data.length / 3];
    int byteIdx = 0;
    for (int pixel = 0; pixel < ints.length;pixel++) {
       int rgb = 0xff000000 | ((data[byteIdx] & 0xff) << 16) | ((data[byteIdx++] & 0xff) << 8) | (data[byteIdx++] &
        0xff);
        byteIdx++;
        /*int rByte = (int) data[byteIdx] & 0xFF;
        byteIdx++;
        int gByte = (int) data[byteIdx] & 0xFF;
        byteIdx++;
        int bByte = (int) data[byteIdx] & 0xFF;
        byteIdx++;
        int rgb = (rByte << 16) | (gByte << 8) | bByte;*/
        ints[pixel] = rgb;
    }
    return ints;
  }

  public static byte[] readImg(String path) throws IOException {
    
    File imgPath = new File("batman.bmp");
    img = ImageIO.read(imgPath);
    heigth = img.getHeight();
    width = img.getWidth();
    byte imageData[] = ((DataBufferByte)img.getData().getDataBuffer()).getData();
    return imageData;
  }
  
  

  public static void createImg(byte[] input) throws IOException {
    int[] pixels = byteToInt(input);
    File imgPath = new File("encodedBatman.bmp");
    BufferedImage   image = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);
    for(int i=1;i<width;i++){
      for(int j=1;j<heigth;j++){
        image.setRGB(i, j, pixels[i*j]);
      }
    }
    ImageIO.write(image, "bmp", imgPath);
  }

  public static void main(String[] args) throws IOException {
    try {

      SecretKey key = genKey();
      String path = "";
      byte[] clearImg = readImg(path); 
      byte[] cryptoImg = enc(clearImg, key);
     
      createImg(clearImg);
      
    } catch (ShortBufferException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException | KeyStoreException | CertificateException ex) {
      Logger.getLogger(ECB.class.getName()).log(Level.SEVERE, null, ex);
    }
     
   
  }
}

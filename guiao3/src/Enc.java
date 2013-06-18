package guiao3;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Enc {
	

	private SecretKey key;
	private IvParameterSpec iv;
	
	public Enc(){}

	public byte[] enc(byte[] input) {
		try{
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		    cipher.init(Cipher.ENCRYPT_MODE, this.key);   
		    byte[] cipherText = cipher.doFinal(input);
		    //this.iv = new IvParameterSpec(cipher.getIV());
		    return cipherText;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	
	public byte[] dec(byte[] input) {
		try{
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		    cipher.init(Cipher.DECRYPT_MODE, this.key);   
		    byte[] cipherText = cipher.doFinal(input);
		    return cipherText;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	
	public void genKey(){
		try {
			
			this.key = KeyGenerator.getInstance("DES").generateKey();
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}

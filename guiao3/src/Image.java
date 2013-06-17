package guiao3;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class Image {
	
	private BufferedImage img;

	public Image(){}
	
	
	public void startImageTransf(String path){
		this.readImage(path);
		this.writeImage(this.encImage());
	}
	
	public void readImage(String path){
		try {
			this.img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeImage(BufferedImage imgN){
		try {
			ImageIO.write(imgN, "bmp", new File("converted.bmp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private BufferedImage encImage(){
		
		int width = this.img.getWidth();
		int height = this.img.getHeight();
		
		BufferedImage imgN = new BufferedImage(width, height, this.img.getType());
		
		ByteBuffer buffer = ByteBuffer.allocate(width * height * 4);
		
		for(int i = 0; i < width ; i++){
			for(int j = 0; j < height; j++){
				buffer.putInt(this.img.getRGB(i, j));
			}
		}
		
		int[] nPixeis = this.enc(buffer.array());
		
		int x = 0;
		for(int i = 0; i < width ; i++){
			for(int j = 0; j < height; j++){
				imgN.setRGB(i, j, nPixeis[++x]);
			}
		}
		
		return imgN;
	}
	
	public int[] byteToInt(byte[] data) {
		int[] ints = new int[data.length/4];
		for(int i = 0; i < data.length/4; i++)
			ints[i] = ByteBuffer.wrap(data, i * 4, 4).getInt();
		return ints;
    }
	
	private int[] enc(byte[] input){
		Enc cipher = new Enc();
		
		cipher.genKey();
		
		byte[] result = cipher.enc(input);
		
		return this.byteToInt(result);
	}
}

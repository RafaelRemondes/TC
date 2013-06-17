
import java.math.BigInteger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rafaelremondes
 */
public class elGamal {
  
  static int[] cryptogram = {3781, 14409, 31552, 3930, 27214, 15442, 5809, 30274, 5400, 31486, 19936, 721, 27765, 29284, 29820, 7710, 31590, 26470, 3781, 14409, 15898, 30844, 19048, 12914, 16160, 3129, 301, 17252, 24689, 7776, 28856, 15720, 30555, 24611, 20501, 2922, 13659, 5015, 5740, 31233, 1616, 14170, 4294, 2307, 2320, 29174, 3036, 20132, 14130, 22010, 25910, 19663, 19557, 10145, 18899, 27609, 26004, 25056, 5400, 31486, 9526, 3019, 12962, 15189, 29538, 5408, 3149, 7400, 9396, 3058, 27149, 20535, 1777, 8737, 26117, 14251, 7129, 18195, 25302, 10248, 23258, 3468, 26052, 20545, 21958, 5713, 346, 31194, 8836, 25898, 8794, 17358, 1777, 8737, 25038, 12483, 10422, 5552, 1777, 8737, 3780, 16360, 11685, 133, 25115, 10840, 14130, 22010, 16081, 16414, 28580, 20845, 23418, 22058, 24139, 9580, 173, 17075, 2016, 18131, 19886, 22344, 21600, 25505, 27119, 19921, 23312, 16906, 21563, 7891, 28250, 21321, 28327, 19237, 15313, 28649, 24271, 8480, 26592, 25457, 9660, 7939, 10267, 20623, 30499, 14423, 5839, 24179, 12846, 6598, 9284, 27858, 24875, 17641, 1777, 8737, 18825, 19671, 31306, 11929, 3576, 4630, 26664, 27572, 27011, 29164, 22763, 8992, 3149, 7400, 8951, 29435, 2059, 3977, 16258, 30341, 21541, 19004, 5865, 29526, 10536, 6941, 1777, 8737, 17561, 11884, 2209, 6107, 10422, 5552, 19371, 21005, 26521, 5803, 14884, 14280, 4328, 8635, 28250, 21321, 28327, 19237, 15313, 28649};
  static BigInteger p = BigInteger.valueOf(31847);
  static BigInteger x = BigInteger.valueOf(7899);
  

  public static Pair[] pairConstructor(int[] crypto) {
    Pair[] pairs = new Pair[crypto.length / 2];
    int j = 0;
    for (int i = 0; i < crypto.length; i+=2) {      
        Pair a = new Pair(BigInteger.valueOf(crypto[i]), BigInteger.valueOf(crypto[i+1]));
        pairs[j] = a;
        j++;
    }
    return pairs;
  }

  
  public static BigInteger[] dec(Pair[] crypto) {
    BigInteger[] clearText = new BigInteger[crypto.length];
    int i = 0;
    while(i < crypto.length){
      BigInteger c1 = (BigInteger) crypto[i].getLeft();
      BigInteger c2 = (BigInteger) crypto[i].getRight();
      BigInteger s = c1.modPow(x.negate(), p);
      BigInteger m = s.multiply(c2).mod(p);
      clearText[i] = m;
      i++;
    }
    return clearText;
  }
  
   public static String getText(int num){
   StringBuilder sb = new StringBuilder();
   int c1=0;
   int c2=0;
   int c3=0;
   for(c1 = 0; c1<26;c1++){
        for(c2 = 0; c2<26;c2++){
             for(c3 = 0; c3<26;c3++){
               if(((c1*(26*26))+(c2*26)+c3)==num){
                 sb.append((char) (c1+65));
                 sb.append((char) (c2+65));
                 sb.append((char) (c3+65));
                 return sb.toString();
               }
             }
        }

   }
   return sb.toString();
 }

  public static void main(String[] args) {
    Pair[] pairs = pairConstructor(cryptogram);
    BigInteger[] clearText = dec(pairs);
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i<clearText.length;i++){
      sb.append(getText(clearText[i].intValue()));
    }
    System.out.println(sb.toString());
  }
}

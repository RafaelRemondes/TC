
import java.math.BigInteger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rafaelremondes
 */
public class rsaCracker {
  
  /*Como o n é relativamente pequeno, é fácil calcular p e q tal que pq = n, a partir dai é calcular a função totient de Euler (p-1)(q-1)
   * calcular o expoente privado tal que d = e mod(totient(n)). Decififrar consoante com o formato pedido. 
   */
  
  static int[] crypto1={6340, 8309, 14010, 8936, 27358 ,25023 ,16481, 25809,23614 ,
                 7135, 24996, 30590, 27570, 26486, 30388, 9395,27584, 14999, 
                 4517, 12146 ,29421 ,26439 ,1606, 17881,25774, 7647, 23901, 7372, 
                 25774, 18436, 12056, 13547,7908, 8635, 2149, 1908, 22076, 7372, 8686, 
                 1304,4082, 11803, 5314, 107, 7359, 22470, 7372, 22827,
                 15698, 30317, 4685, 14696, 30388, 8671, 29956, 15705,
                 1417, 26905, 25809, 28347, 26277, 7897, 20240, 21519,
                 12437, 1108, 27106, 18743, 24144, 10685, 25234, 30155,
                 23005, 8267, 9917, 7994, 9694, 2149, 10042, 27705,
                 15930, 29748, 8635, 23645, 11738, 24591, 20240, 27212,
                 27486, 9741, 2149, 29329, 2149, 5501, 14015, 30155,
                 18154, 22319, 27705, 20321, 23254, 13624, 3249, 5443,
                 2149, 16975, 16087, 14600, 27705, 19386, 7325, 26277,
                 19554, 23614, 7553, 4734, 8091, 23973, 14015, 107,
                 3183, 17347, 25234, 4595, 21498, 6360, 19837, 8463,
                 6000, 31280, 29413, 2066, 369, 23204, 8425, 7792, 25973, 4477, 30989};
  int[] crypto2;
  
  public static boolean ferman(BigInteger a) {
    boolean veredict = false;
    if (a.isProbablePrime(15)) {
      veredict = true;
    }
    return veredict;
  }
  
  @SuppressWarnings("empty-statement")
  public static BigInteger[] primeFactors(BigInteger n) {
    BigInteger[] factors = new BigInteger[2];
    BigInteger p = BigInteger.valueOf(500);
    BigInteger q = BigInteger.valueOf(500);
    while (p.compareTo(BigInteger.valueOf(0)) != 0) {
      if (ferman(p) == true) {
        while (q.compareTo(BigInteger.valueOf(0)) != 0) {          
          if (ferman(q) == true) {
            if (n.compareTo(p.multiply(q)) == 0) {
              factors[0] = p;
              factors[1] = q;
              return factors;
            }
          }
         q = q.subtract(BigInteger.valueOf(1));
        }
        q = BigInteger.valueOf(500);;
      }      
      p = p.subtract(BigInteger.valueOf(1));
    }
    return factors;
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
    int clearText=1;
    StringBuilder sb = new StringBuilder();
    BigInteger n = BigInteger.valueOf(31313);
    BigInteger e = BigInteger.valueOf(4913);
    BigInteger[] factors = new BigInteger[2];
    factors = primeFactors(n);
    BigInteger p = factors[0];
    BigInteger q = factors[1];
    BigInteger totient = p.subtract(BigInteger.ONE).multiply((q.subtract(BigInteger.ONE)));
    BigInteger d = e.modInverse(totient);
    System.out.println(d);
    for(int i = 0; i<crypto1.length;i++){
      clearText = (BigInteger.valueOf(crypto1[i]).modPow(d, n)).intValue();
      sb.append(getText(clearText));
    }
    System.out.println(sb.toString());
  }  
}

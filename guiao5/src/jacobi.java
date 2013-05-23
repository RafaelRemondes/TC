
import java.math.BigInteger;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rafaelremondes
 */
public class jacobi {
  
  static List<BigInteger> primes;
  
  public static BigInteger jacobi(BigInteger m, BigInteger n){
    if(m.compareTo(BigInteger.ZERO) == 0){
      return BigInteger.ZERO;
    }
    if(m.compareTo(BigInteger.ONE) == 0){
      return BigInteger.ONE;
    }
    if(m.compareTo(n)>= 0) {
      return jacobi(m.remainder(n),n);
    }
    if(m.compareTo(BigInteger.valueOf(2)) == 0){
      BigInteger module = n.remainder(BigInteger.valueOf(8));
      if(module.compareTo(BigInteger.ONE.mod(BigInteger.valueOf(8)))==0 || module.compareTo(BigInteger.valueOf(-1).mod(BigInteger.valueOf(8)))==0) {
        return BigInteger.ONE;
      }
      if(module.compareTo(BigInteger.valueOf(3).mod(BigInteger.valueOf(8)))==0 || module.compareTo(BigInteger.valueOf(-3).mod(BigInteger.valueOf(8)))==0){
        return BigInteger.valueOf(-1);
      }  
     }
     if(m.remainder(BigInteger.valueOf(4)).compareTo(BigInteger.valueOf(4))==0 && n.remainder(BigInteger.valueOf(4)).compareTo(BigInteger.valueOf(4))==0 && m.remainder(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO)!=0)
     {return jacobi(n,m).negate();}
     if(m.remainder(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) ==0){
       return jacobi(n,m);
     }
     else {
      return null;
    }
  }
  
  public static BigInteger gcd(BigInteger a, BigInteger b){
    BigInteger x = a;
    while(x.compareTo(BigInteger.ZERO) != 0){
      if(x.remainder(a).compareTo(BigInteger.ZERO)==0 && x.remainder(b).compareTo(BigInteger.ZERO)==0 ) {
      return x;
      }
      x = x.subtract(BigInteger.ONE);
    }
    return null;
  }
  
  public static boolean eulerPrime(BigInteger b, BigInteger n){
    BigInteger j = jacobi(b,n).remainder(n);
    BigInteger p = b.pow(n.intValue()-1).remainder(n);
    if(j.compareTo(p) == 0 && gcd(b,n).compareTo(BigInteger.ONE) == 0){
      return true;
    }
    else {
      return false;
    }
  }
  
  public static void eulerBase(BigInteger n){
      BigInteger i = BigInteger.valueOf(2);
      BigInteger ct = BigInteger.valueOf(0);
  while (ct.compareTo(BigInteger.valueOf(5))<0){
    if(eulerPrime(i,n)){
      primes.add(i);
      ct =ct.add(BigInteger.ONE);
      i = i.add(BigInteger.ONE);
    }
  }
  }
  
  
  public static void main(String[] args){
      BigInteger n = BigInteger.valueOf(837);
      eulerBase(n);
  }
  
}

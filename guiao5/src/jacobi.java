
import java.math.BigInteger;
import java.util.ArrayList;
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
  
  public static boolean is_odd(BigInteger n){
    return n.testBit(0);
  }
  
  public static BigInteger is_pow2(BigInteger k){
    int x = k.intValue();
    while(x!=0){
      if(BigInteger.valueOf(2).pow(x).compareTo(k) == 0){
        return BigInteger.valueOf(x);
      }
      x--;
    }
    return null;
  }
  
  public static List<BigInteger> list_factor(BigInteger m){
    BigInteger t,k;
    List<BigInteger> factor = new ArrayList<>();    
    if(is_odd(m)) {
      return null;
    }
    t=m.subtract(BigInteger.ONE);
    while(t.compareTo(BigInteger.ZERO)!=0){
      if(m.remainder(t).compareTo(BigInteger.ZERO)==0){
        k = m.divide(t);
        factor.add(k);
        factor.add(t);
        return factor;
      }
      t = t.subtract(BigInteger.ONE);
    }
    return factor;
  }
  
  public static BigInteger jacobi(BigInteger m, BigInteger n){
    if(!is_odd(m)){
      return null;
    }
    
    if(m.compareTo(BigInteger.ZERO) == 0){
      return BigInteger.ZERO;
    }
    if(m.compareTo(BigInteger.ONE) == 0){
      return BigInteger.ONE;
    }
 
    //propriedade a)
    if(m.compareTo(n)>= 0) {
      return jacobi(m.remainder(n),n);
    }
    
    //propriedade b)
    if(m.compareTo(BigInteger.valueOf(2)) == 0){
      BigInteger module = n.remainder(BigInteger.valueOf(8));
      if(module.compareTo(BigInteger.ONE.mod(BigInteger.valueOf(8)))==0 || module.compareTo(BigInteger.valueOf(-1).mod(BigInteger.valueOf(8)))==0) {
        return BigInteger.ONE;
      }
      if(module.compareTo(BigInteger.valueOf(3).mod(BigInteger.valueOf(8)))==0 || module.compareTo(BigInteger.valueOf(-3).mod(BigInteger.valueOf(8)))==0){
        return BigInteger.valueOf(-1);
      }  
     }
    
    //propriedade c)
    List<BigInteger> factor = list_factor(m);
    if(factor.size()==2){
      BigInteger k1 =  is_pow2(factor.get(0));
      BigInteger k2 = is_pow2(factor.get(1));
      if(k1!=null || k2!=null){
        if(k1!=null){
          return jacobi(BigInteger.valueOf(2),n).pow(k1.intValue()).multiply(jacobi(factor.get(1),n));
        }
        if(k2!=null){
          return jacobi(BigInteger.valueOf(2),n).pow(k2.intValue()).multiply(jacobi(factor.get(0),n));
        }
      }
      else{
        return jacobi(factor.get(0).multiply(jacobi(n,factor.get(1))),n);
      }
    }
    
    //propriedade d)
     if(is_odd(m)){
       if(n.compareTo(m)== 0 && m.compareTo(BigInteger.valueOf(3).mod(BigInteger.valueOf(4)))==0 )/*n==m==3 mod 4*/{
         return (jacobi(m,n)).negate();
       }
       else{
         return jacobi(m,n);
       }
     }
     
    /* if(m.remainder(BigInteger.valueOf(4)).compareTo(BigInteger.valueOf(4))==0 && n.remainder(BigInteger.valueOf(4)).compareTo(BigInteger.valueOf(4))==0 && m.remainder(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO)!=0)
     {return jacobi(n,m).negate();}
     if(m.remainder(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO) ==0){
       return jacobi(n,m);
     }*/
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

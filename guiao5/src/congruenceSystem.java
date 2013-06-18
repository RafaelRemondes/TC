
import java.math.BigInteger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rafaelremondes
 */
public class congruenceSystem {
  
  /*
   * x = 2 mod 3
   * x = 3 mod 4
   * x = 1 mod 5
   * 
   * For example, consider the problem of finding an integer x such that Using the extended Euclidean algorithm, for x modulo 3 and 20 [4×5], 
   * we find (−13) × 3 + 2 × 20 = 1, i.e. e1 = 40. For x modulo 4 and 15 [3×5], we get (−11) × 4 + 3 × 15 = 1, i.e. e2 = 45. 
   * Finally, for x modulo 5 and 12 [3×4], we get 5 × 5 + (−2) × 12 = 1, i.e. e3 = −24.
   * A solution x is therefore 2 × 40 + 3 × 45 + 1 × (−24) = 191. All other solutions are congruent to 191 modulo 60, [3 × 4 × 5 = 60], 
   * which means they are all congruent to 11 modulo 60.*/
  
  static class Equation{
     BigInteger a;
     BigInteger b;
     BigInteger c;
    
     Equation(int c,int a, int b){
      this.a = BigInteger.valueOf(a);
      this.b = BigInteger.valueOf(b);
      this.c = BigInteger.valueOf(c);
    }
     
    public BigInteger getA(){
      return a;
    }
    
    public BigInteger getB(){
      return b;
    }
    
    public void setA(BigInteger x){
      a = x;
    }
    
    public BigInteger getC(){
      return c;
    }
  }
  
  
  static BigInteger[] gcd(BigInteger p, BigInteger q) {
      if (q.compareTo(BigInteger.valueOf(0))==0){
         return new BigInteger[] { p, BigInteger.valueOf(1), BigInteger.valueOf(0) };
      }
      BigInteger[] vals = gcd(q, p.remainder(q));
      BigInteger d = vals[0];
      BigInteger a = vals[2];
      BigInteger b = vals[1].subtract((p.divide(q).multiply(vals[2])));
      return new BigInteger[] { d, a, b };
   }
  

  
  @SuppressWarnings("empty-statement")
  public static  Pair<BigInteger,BigInteger> extendedEuclidian(BigInteger a, BigInteger b){
   BigInteger[] vals = new BigInteger[3];
   vals = gcd(a,b);
   return new Pair(vals[1], vals[2]);    
  }
 
  public static void main(String[] args){
    Equation eq1 = new Equation(1,12,25);  
    Equation eq2 = new Equation(1,9,26);
    Equation eq3 = new Equation(1,23,27);
    BigInteger fact1 = eq2.getB().multiply(eq3.getB());
    BigInteger fact2 =  eq1.getB().multiply(eq3.getB());
    BigInteger fact3 = eq1.getB().multiply(eq2.getB());
    Pair<BigInteger,BigInteger> p1 = extendedEuclidian(eq1.getB(),fact1);
    Pair<BigInteger,BigInteger> p2 = extendedEuclidian(eq2.getB(),fact2);
    Pair<BigInteger,BigInteger> p3 = extendedEuclidian(eq3.getB(),fact3);
    BigInteger e1 =  fact1.multiply(p1.getRight());
    BigInteger e2 =  fact2.multiply(p2.getRight());
    BigInteger e3 =  fact3.multiply(p3.getRight());
    BigInteger val = (e1.multiply(eq1.getA())).add(e2.multiply(eq2.getA())).add(e3.multiply(eq2.getA()));
    BigInteger modulus = eq1.getB().multiply(eq2.getB()).multiply(eq3.getB());
    System.out.println("Val: "+ val.intValue() + " modulus: "+modulus.intValue() );
    System.out.println(val.mod(modulus).intValue());
    
    
    
  }
  
}

import java.lang.*;
import java.math.*;
public class Derp {
	BigInteger zero=new BigInteger("0");
	BigNumberUtils utils=new BigNumberUtils();
	BigInteger bignumber= new BigInteger("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
	BigInteger p=utils.generateRandomBigPrime(bignumber, 350);
	BigInteger q=utils.generateRandomBigPrime(bignumber, 350);
	BigInteger n=p.multiply(q);
	BigInteger pMinus1=p.subtract(new BigInteger("1"));
	BigInteger qMinus1=q.subtract(new BigInteger("1"));
	BigInteger phi=pMinus1.multiply(qMinus1);
	//BigInteger firstRelativePrimeToTheProduct=utils.generateRandomBigPrime(zero, phi.bitLength()-1);
	//BigInteger e=utils.spitLowestRelativePrime(phi);
	BigInteger e=new BigInteger("3");

	BigInteger secondRelativePrimeToTheProduct;


	public void method(){
		//System.out.println(bigPrime1);
		//System.out.println(bigPrime1Minus1);
		System.out.println(phi);
		System.out.println(e);
	}
}

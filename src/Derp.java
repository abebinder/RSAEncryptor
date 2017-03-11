import java.lang.*;
import java.math.*;
public class Derp {
	BigInteger zero=new BigInteger("0");
	BigInteger one=new BigInteger("1");
	BigNumberUtils utils=new BigNumberUtils();
	BigInteger lowerBoundForPrimes= new BigInteger("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
	BigInteger p=utils.generateRandomBigPrime(lowerBoundForPrimes, 350);
	BigInteger q=utils.generateRandomBigPrime(lowerBoundForPrimes, 350);
	BigInteger n=p.multiply(q);
	BigInteger pMinus1=p.subtract(new BigInteger("1"));
	BigInteger qMinus1=q.subtract(new BigInteger("1"));
	BigInteger phi=pMinus1.multiply(qMinus1);
	BigInteger e=utils.spitLowestRelativePrime(phi);
	BigInteger d=utils.bigEuclidean(phi, e, phi, phi, e, one);
	BigInteger de=d.multiply(e);


	public BigInteger encrypt(BigInteger encryptThis){
		BigInteger result=encryptThis.modPow(e, n);
		return result;
	}
	
	public BigInteger decrypt(BigInteger decryptThis){
		BigInteger result=decryptThis.modPow(d, n);
		return result;
	}
	
	public void method(){
		//System.out.println(bigPrime1);
		//System.out.println(bigPrime1Minus1);
		//System.out.println(phi);
		//System.out.println(e);
		//System.out.println(d);
		
		//BigInteger fuck=de.mod(phi);
		//System.out.println(fuck);
		BigInteger encryptThis=utils.generateRandomBigPrime(lowerBoundForPrimes, 349);
		System.out.println(encryptThis);
		System.out.println(e);
		System.out.println(encryptThis.pow(e.intValue()));
		BigInteger encrypted=encrypt(encryptThis);
		System.out.println(encrypted);
		BigInteger decrypted =decrypt(encrypted);
		System.out.println(decrypted);
		
	}
}

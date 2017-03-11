import java.lang.*;
import java.math.*;
public class PublicKeyEncryption {
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
	
	public BigInteger getN(){
		return n;
	}

	public BigInteger getPublicKey(){
		return e;
	}
	
	public BigInteger getPrivateKey(){
		return d;
	}
	
	public BigInteger encrypt(BigInteger encryptThis, BigInteger publicKey, BigInteger primeMultiple){
		BigInteger result=encryptThis.modPow(publicKey, primeMultiple);
		return result;
	}
	
	public BigInteger decrypt(BigInteger decryptThis, BigInteger privateKey, BigInteger primeMultiple){
		BigInteger result=decryptThis.modPow(privateKey, primeMultiple);
		return result;
	}
	
}

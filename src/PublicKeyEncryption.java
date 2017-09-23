import java.math.*;
public class PublicKeyEncryption {
	BigNumberUtils utils=new BigNumberUtils();
	BigInteger zero=new BigInteger("0");
	BigInteger one=new BigInteger("1");
	int digitsOfPrimes=103; //change this to change the number of digits of the primes that are generated.
	
	double base2PrimeDigitsAsDouble=(digitsOfPrimes*Math.log(10))/Math.log(2);/*
	This number translates the number of digits you want to have in your prime
	into the minimudsm bitlength a number with such digits can be expressed, which
	is the parameter the probablePrime method uses. 
	*/
	
	int base2PrimeDigitsAsInt=(int)base2PrimeDigitsAsDouble; //casts the double as int
	
	BigInteger lowerBoundForPrimes=zero;
	BigInteger p=utils.generateRandomBigPrime(lowerBoundForPrimes, base2PrimeDigitsAsInt);
	BigInteger q=utils.generateRandomBigPrime(lowerBoundForPrimes, base2PrimeDigitsAsInt);
	BigInteger n=p.multiply(q);
	BigInteger pMinus1=p.subtract(one);
	BigInteger qMinus1=q.subtract(one);
	BigInteger phi=pMinus1.multiply(qMinus1);
	BigInteger e=utils.spitLowestRelativePrime(phi);
	BigInteger d=utils.bigEuclideanExtendedShortHand(phi, e, phi, phi, e, one);//d is found by using the euclidean algorithim on e and phi

	public BigInteger getN(){
		return n;
	}

	public BigInteger getPublicKey(){
		return e;
	}

	public BigInteger getPrivateKey(){
		return d;
	}
	
	/*
	 * This is kind of amazing. All the encryption really does is raise the message
	 * to the e power mod n. That's it. Yet, there is no way to undo this 
	 * function without knowing d, which is obtained by knowing phi which is obtained by 
	 * knowing the prime factorization of n.
	 */
	public BigInteger encrypt(BigInteger encryptThis, BigInteger publicKey, BigInteger primeMultiple){
		BigInteger result=encryptThis.modPow(publicKey, primeMultiple);
		return result;
	}

	/*
	 * Decrypting is equally amazing. All we do is raise the encrypted message to
	 * d power mod n, and it spits back are original message.
	 */
	public BigInteger decrypt(BigInteger decryptThis, BigInteger privateKey, BigInteger primeMultiple){
		BigInteger result=decryptThis.modPow(privateKey, primeMultiple);
		return result;
	}
	
	
/*
 * In the case that our message is too short (raising it to the eth power does not 
 * cross n) or that the message begins with a zero, we must pad the number by
 * transforming it into a larger integer by first adding a one to the beginning and 
 * end and then adding a bunch of zeroes until it is a size that is encryptable mod(n)
 */
	public BigInteger pad(String padThis){
		String padThisString=padThis.toString();
		padThisString="1"+padThisString+"1";
		boolean b=true;
		while(b){
			if(padThisString.length()<100){
				padThisString=padThisString+"0";
			}
			else{
				b=false;
			}
		}
		BigInteger padded=new BigInteger(padThisString);
		return padded;
	}
	
	
	/*
	 * Just undoes the padding to get back the proper original message
	 * after a message is decrypted. First it removes
	 * the zeroes, then the one at the front and back of the number.
	 */
	public String dePad(BigInteger dePadThis){
		String dePadThisString=dePadThis.toString();
		boolean b=true;
		while(b){
			if(dePadThisString.charAt(dePadThisString.length()-1)=='0'){
				dePadThisString=dePadThisString.substring(0, dePadThisString.length()-1);
			}
			else{
				b=false;
			}
		}
		dePadThisString=dePadThisString.substring(1,dePadThisString.length()-1);
		return dePadThisString;
	}
	
	

}

import java.lang.*;
import java.math.*;
import java.util.ArrayList;
public class PublicKeyEncryption {
	BigInteger zero=new BigInteger("0");
	BigInteger one=new BigInteger("1");
	int digitsOfPrimes=103;//hi
	double convertBase10DigitsToBase2Digits=(digitsOfPrimes*Math.log(10))/Math.log(2);
	int inputAsInt=(int)convertBase10DigitsToBase2Digits;
	BigNumberUtils utils=new BigNumberUtils();
	//BigInteger lowerBoundForPrimes= new BigInteger("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
	BigInteger lowerBoundForPrimes=new BigInteger("0");
	BigInteger p=utils.generateRandomBigPrime(lowerBoundForPrimes, inputAsInt);
	BigInteger q=utils.generateRandomBigPrime(lowerBoundForPrimes, inputAsInt);
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
	
	public String addAOneToBeginning(String s){
		s="1"+s;
		return s;
	}
	
	public String removeAOneFromBeginning(String s){
		s=s.substring(1, s.length());
		return s;
	}

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
	
	public BigInteger encryptLong(BigInteger encryptThis, BigInteger multipliedPrimes,
			BigInteger publicKey){
		ArrayList<String> bunch=new ArrayList<String>();
		String multipliedPrimesString=multipliedPrimes.toString();
		int lengthEachNeedsBe=multipliedPrimesString.length()-2;
		String encryptThisString=encryptThis.toString();
		String subString="";
		for(int i=0; i<multipliedPrimesString.length(); i++){
			subString=subString+encryptThisString.charAt(i);
			if(i%lengthEachNeedsBe==0&&i!=0&&i!=1||i==multipliedPrimesString.length()-1){
				bunch.add(new String(subString));
				subString=""; //toDo
			}
			if(i==multipliedPrimesString.length()-1){
				String encryptedString="";
				for(int j=0; j<bunch.size(); j++){
					BigInteger partialToEncrypt=new BigInteger(bunch.get(j));
					BigInteger partialEncrypted=encrypt(partialToEncrypt, publicKey, multipliedPrimes);
					String partialEncryptedString=partialEncrypted.toString();
					encryptedString=encryptedString+partialEncryptedString;
				}
				BigInteger result= new BigInteger(encryptedString);
				return result;
			}
		}
		
		return new BigInteger("0");
	}
	
	public BigInteger decryptLong(BigInteger decryptThis, BigInteger multipliedPrimes,
			BigInteger privateKey){
				
		
		return privateKey;
		
	}

}

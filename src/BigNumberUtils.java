import java.math.*;
import java.util.*;
public class BigNumberUtils {
	
/*
 * This method gives the first number 3 or higher that has no common divisors with the 
 * parameter (including itself) besides one.
 */
	public BigInteger spitLowestRelativePrime(BigInteger big){ 
		boolean b=true;
		BigInteger i=new BigInteger("3");
		BigInteger one=new BigInteger("1");
		while(b){ //this while loop increments i until it finds a number that works
			if(gcd(i,big).compareTo(one)==0){
				return i;
			}
			i=i.add(one);
		}
		return big;	
	}

	
	/*
	 * This method generates a large number that is probably prime by using 
	 * the biginteger probableprime method. The chance that the number found is not
	 * prime is tiny (2^-100).
	 */
	public BigInteger generateRandomBigPrime(BigInteger lowerBound, int upperBound2toTheN){
		Random r= new Random();
		while(true){
			BigInteger prime=BigInteger.probablePrime(upperBound2toTheN, r);
			if(prime.compareTo(lowerBound)>=0){
				return prime;
			}
		}
	}

/*
 * This method finds the gcd of two bigIntegers via the euclidean algorithim.
 */
	public static BigInteger gcd(BigInteger p, BigInteger q) {
		BigInteger zero=new BigInteger("0");
		if (q.compareTo(zero)==0) {
			return p;
		}
		return gcd(q, p.remainder(q));
	}
	
	/*
	 * 
	 * Through online research, I found a shorthand way to implement the extended
	 * euclidean algorithim that avoided back substitution, reducing the amount
	 * of code I had to write. This is NOT copied code,
	 * however, the algorithim that I implemented I did not come up with on my own
	 *  and was described in detail by Anthony Vance
	 * on youtube. I translated his line of reasoning
	 * into code. https://youtu.be/Z8M2BTscoD4?t=10m6s
	 */
	public BigInteger bigEuclideanExtendedShortHand(BigInteger phi, BigInteger e,
			BigInteger leftTop, BigInteger rightTop, BigInteger leftBottom,
			BigInteger rightBottom){
		BigInteger zero=new BigInteger("0");
		BigInteger one=new BigInteger("1");
		BigInteger leftTopDivideLeftBottom=leftTop.divide(leftBottom);
		BigInteger leftBottomMultiplyLeftTopDivideLeftBottom=leftTopDivideLeftBottom.multiply(leftBottom);
		BigInteger rightBottomMultiplyLeftTopDivideLeftBottom=leftTopDivideLeftBottom.multiply(rightBottom);
		BigInteger newLeftBottom=leftTop.subtract(leftBottomMultiplyLeftTopDivideLeftBottom);
		BigInteger newRightBottom=rightTop.subtract(rightBottomMultiplyLeftTopDivideLeftBottom);
		
		
		if(newRightBottom.compareTo(zero)<=0){
			while(newRightBottom.compareTo(zero)<=0){
			newRightBottom=newRightBottom.add(phi);
			}
		}
		
		if(newLeftBottom.compareTo(one)==0){
			return newRightBottom;
		}
		return bigEuclideanExtendedShortHand(phi, e, leftBottom, rightBottom, newLeftBottom, newRightBottom);
	}
	
	
	
	
	
	
	
	
	
}

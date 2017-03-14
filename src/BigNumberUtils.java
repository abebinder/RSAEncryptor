import java.math.*;
import java.util.*;
public class BigNumberUtils {

	public BigInteger spitLowestRelativePrime(BigInteger big){
		boolean b=true;
		BigInteger i=new BigInteger("3");
		BigInteger one=new BigInteger("1");
		while(b){
			if(gcd(i,big).compareTo(one)==0){
				return i;
			}
			i=i.add(one);
		}
		return big;	
	}

	public BigInteger generateRandomBigPrime(BigInteger lowerBound, int upperBound2toTheN){
		Random r= new Random();
		while(true){
			BigInteger prime=BigInteger.probablePrime(upperBound2toTheN, r);
			if(prime.compareTo(lowerBound)>=0){
				return prime;
			}
		}
	}

	public BigInteger generateRandomBigInteger(BigInteger upperbound){
		BigInteger big;
		Random r =new Random();
		big=new BigInteger(upperbound.bitLength(), r);
		return big;
	}

	public static BigInteger gcd(BigInteger p, BigInteger q) {
		BigInteger zero=new BigInteger("0");
		if (q.compareTo(zero)==0) {
			return p;
		}
		return gcd(q, p.remainder(q));
	}
	
	
	public BigInteger bigEuclidean(BigInteger phi, BigInteger e,
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
		return bigEuclidean(phi, e, leftBottom, rightBottom, newLeftBottom, newRightBottom);
	}
	
	public BigInteger stickAOneOnTheEnd(BigInteger big){
		String bigAsString=big.toString();
		bigAsString=bigAsString+"1";
		BigInteger newbig= new BigInteger(bigAsString);
		return newbig;
	}
	
	public BigInteger stickAOneOnTheBeginning(BigInteger big){
		String bigAsString=big.toString();
		bigAsString="1"+bigAsString;
		BigInteger newbig= new BigInteger(bigAsString);
		return newbig;
	}
	
	public BigInteger takeAOneOffEnd(BigInteger big){
		String bigAsString=big.toString();
		bigAsString=bigAsString.substring(0, bigAsString.length()-1);
		BigInteger newBig= new BigInteger(bigAsString);
		return newBig;
	}
	
	public BigInteger takeAOneOffTheBeginning(BigInteger big){
		String bigAsString=big.toString();
		bigAsString=bigAsString.substring(1, bigAsString.length());
		BigInteger newbig= new BigInteger(bigAsString);
		return newbig;
	}
	
	
	
}

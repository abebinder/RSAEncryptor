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
		BigInteger one=new BigInteger("1");
		BigInteger derp=leftTop.divide(leftBottom);
		BigInteger hurka=derp.multiply(leftBottom);
		BigInteger durka=derp.multiply(rightBottom);
		BigInteger newLeftBottom=leftTop.subtract(hurka);
		BigInteger newRightBottom=rightTop.subtract(durka);
	}
	
	public void solve(long a, long b)
    {
        long x = 0, y = 1, lastx = 1, lasty = 0, temp;
        while (b != 0)
        {
            long q = a / b;
            long r = a % b;
 
            a = b;
            b = r;
 
            temp = x;
            x = lastx - q * x;
            lastx = temp;
 
            temp = y;
            y = lasty - q * y;
            lasty = temp;            
        }
        System.out.println("Roots  x : "+ lastx +" y :"+ lasty);
    }
	
	
}

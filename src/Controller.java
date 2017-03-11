import java.math.*;
import java.util.*;
public class Controller {

	public static void main(String[] args) {
		Derp derp=new Derp();
	BigNumberUtils utils=new BigNumberUtils();
	BigInteger first=new BigInteger("5000");
	BigInteger two=new BigInteger("4000");
	BigInteger gcd=utils.gcd(first, two);
	System.out.println(gcd);
	derp.method();
	}

}

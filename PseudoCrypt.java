import java.math.BigInteger;

public class PseudoCrypt {
	/* Next prime greater than 62 ^ n / 1.618033988749894848 */
	private static final String[] primes = new String[] {
		"1",
        "41",
        "2377",
        "147299",
        "9132313",
        "566201239",
        "35104476161",
        "2176477521929",
        "134941606358731",
        "8366379594239857",
        "518715534842869223",
	};
	
	 /* Modular multiplicative inverse */
	private static final String[] modMullInv = new String[] {
			"1",
            "59",
            "1677",
            "187507",
            "5952585",
            "643566407",
            "22071637057",
            "294289236153",
            "88879354792675",
            "7275288500431249",
            "280042546585394647",
	};

	private static final String chars62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static String base62(String sint) {
		String key = "";
		while(bccomp(sint, "0") > 0) {
			int _mod = bcmod(sint, "62").intValue();
			key += chars62.charAt(_mod);
			sint = toString(bcdiv(sint, 62));
		}
		
		return strrev(key);
	}
	
	/* default len = 5 */
	public static String hash(int num, int len) {
		BigInteger ceil =  bcpow("62", len);
		String prime = primes[len];
		BigInteger dec = bcmod(toString(bcmul(toString(num), prime)), toString(ceil));
		String hash = base62(toString(dec));
		
		return str_pad_left(hash, len, "0");
	}
	
	public static BigInteger unbase62(String key) {
		BigInteger _int = new BigInteger("0");
		key = strrev(key);
		for(int i=0; i<key.length(); i++) {
			int dec = chars62.indexOf((int)key.charAt(i));
			String bcmulRes = bcmul(toString(dec), toString(bcpow("62", i))).toString();
			_int = bcadd(bcmulRes, toString(_int));
		}
		
		return _int;
	}
	
	public static String unhash(String hash) {
		int len = hash.length();
		String ceil = toString(bcpow("62", len));
		String mmi = modMullInv[len];
		BigInteger num = unbase62(hash);
		String dec = toString(bcmod(toString(bcmul(toString(num), mmi)), ceil));
		
		return dec;
	}
	
	private static String str_pad_left(String numStr, int length, String c) {
		if(numStr.length() < length) {
			for(int i=0; i< length - numStr.length(); i++){
				numStr = c + numStr;
			}
		}
		
		return numStr;
	}
	
	private static String strrev(String s) {
		StringBuffer sb = new StringBuffer(s);
		return sb.reverse().toString();
	}
	
	private static BigInteger bcmod(String n1, String n2) {
		BigInteger bn1 = new BigInteger(n1);
		BigInteger bn2 = new BigInteger(n2);
		BigInteger res = bn1.mod(bn2);
		
		return res; 
	}
	
	private static BigInteger bcadd(String n1, String n2) {
		BigInteger bn1 = new BigInteger(n1);
		BigInteger bn2 = new BigInteger(n2);
		BigInteger res = bn1.add(bn2);
		
		return res; 
	}
	
	private static BigInteger bcmul(String n1, String n2) {
		BigInteger bn1 = new BigInteger(n1);
		BigInteger bn2 = new BigInteger(n2);
		BigInteger res = bn1.multiply(bn2);
		
		return res; 
	}
	
	private static BigInteger bcdiv(String n1, int n2) {
		BigInteger bn1 = new BigInteger(n1);
		BigInteger bn2 = new BigInteger(Integer.toString(n2));
		BigInteger res = bn1.divide(bn2);
		
		return res; 
	}
	
	private static BigInteger bcpow(String n1, int exponent) {
		BigInteger bn1 = new BigInteger(n1);
		BigInteger res = bn1.pow(exponent);
		
		return res; 
	}
	
	private static int bccomp(String n1, String n2) {
		BigInteger bn1 = new BigInteger(n1);
		BigInteger bn2 = new BigInteger(n2);
		
		return bn1.compareTo(bn2);
	}
	
	private static String toString(BigInteger n) {
		return n.toString();
	}
	
	private static String toString(int n) {
		return Integer.toString(n);
	}
}

public class PseudoCryptTest {

	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			String hash = PseudoCrypt.hash(i, 5);
			String unhash = PseudoCrypt.unhash(hash);
		
			System.out.println(i + " - " + hash + " - " + unhash);
		}
	}	
}

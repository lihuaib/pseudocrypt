
public class PseudoCryptTest {

	public static void main(String[] args) {
		
		/* the result should be
		 * 1 - cJinsP - 1
		 * 2 - EdRbko - 2
		 * 3 - qxAPdD - 3
		 * 4 - TGtDVc - 4
		 * 5 - 5ac1O1 - 5
		 * 6 - huKpGQ - 6
		 * 7 - KE3d8p - 7
		 * 8 - wXmR1E - 8
		 * 9 - YrVEtd - 9
		 * 10 - BBE2m2 - 10
		 * */
		for(int i=1; i<=10; i++) {
			String hash = PseudoCrypt.hash(i, 6);
			String unhash = PseudoCrypt.unhash(hash);
		
			System.out.println(i + " - " + hash + " - " + unhash);
		}
	}	
}

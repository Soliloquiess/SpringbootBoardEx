package MS03.solution;

public class ThreeSixNineGame {

	public static void main(String[] args) {
		
		for (int i = 1; i <= 100; i++) {
		
			int 십의자리 = i / 10;
			int 일의자리 = i % 10;
			
//			boolean 십369 = 십의자리 == 3 || 십의자리 == 6 || 십의자리 == 9;
//			boolean 일369 = 일의자리 == 3 || 일의자리 == 6 || 일의자리 == 9;
			boolean 십369 = 십의자리 != 0 && 십의자리 % 3 == 0;
			boolean 일369 = 일의자리 != 0 && 일의자리 % 3 == 0;
			
			if( 십369 && 일369 ) {
				System.out.println("**");
			} else if( 십369 || 일369 ) {
				System.out.println("*");
			} else {
				System.out.println(i);
			}
			
		}
		
	}
}

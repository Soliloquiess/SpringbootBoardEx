package MS03;

public class ThreeSixNineGame {
	public static void main(String[] args) {
		
		int num;
		
		for(num =1; num<=100; num++) {
			

			int a = num/10;
			int b = num%10;
			
		 
			if(num<10) {
				if(num==3||num==6||num==9)
					System.out.println("*");
				else
					System.out.println(num);
			}
			else {
				
				if(a==3||a==6||a==9) {
					if(b==3||b==6||b==9) {
						System.out.println("**");
						continue;
					}

					System.out.println("*");
				}
				else if(b==3||b==6||b==9) {
						System.out.println("*");
					}
				else 
				{
					System.out.println(num);
				}
				//ㅜㅜ
			}
		}
	}
}

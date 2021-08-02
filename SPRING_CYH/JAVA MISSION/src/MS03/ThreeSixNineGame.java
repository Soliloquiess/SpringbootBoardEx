package MS03;

import java.util.Arrays;

public class ThreeSixNineGame {
	public static String solution(String s){
		for(int i=0; i < 5 ; i++){
		      for(int j=i+1; j< 5 ; j++){
		            if( i+j < 5){
		                  System.out.print("★");
		            }else{
		                  System.out.print("☆");
		            }
		      }
		      System.out.println();
		}
		return s;
	}

		public static void main(String[] args) {
			String s="zraf";
			solution(s);
		}
}
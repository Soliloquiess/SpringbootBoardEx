package com.webjjang.util;

import java.util.Scanner;

/**
 * <h3>데이터를 입력하는 객체</h3>
 * 
 * <p>
 * <h4>getString()</h4>
 * 문자열을 scnner.nextLine()에 의해서 사용자가 입력하는 메서드
 * 
 * <h4>getString(String msg)</h4>
 * 문자열을 입력하지 전에 msg를 출력하고 사용자가 입력(getString() 호출)을 하는 메서드.
 * 
 * <h4>getInt()</h4>
 * 사용자가 입력한 문자열을 받아서(getString() 호출) int로 바꿔서 리턴하는 메서드. 정확한 int 숫자가 들어오지 않으면 계속 반복처리한다.
 * 
 * <h4>getInt(String msg)</h4>
 * 문자열을 입력하지 전에 msg를 출력하고 사용자가 입력한 문자열을 받아서(getString() 호출) int로 바꿔서 리턴하는 메서드.
 *  정확한 int 숫자가 들어오지 않으면 계속 반복처리한다.
 * 
 * <h4>getLong()</h4>
 * 사용자가 입력한 문자열을 받아서(getString() 호출) long로 바꿔서 리턴하는 메서드. 정확한 long 숫자가 들어오지 않으면 계속 반복처리한다.

 * <h4>getLong(String msg)</h4>
 * 문자열을 입력하지 전에 msg를 출력하고 사용자가 입력한 문자열을 받아서(getString() 호출) long로 바꿔서 리턴하는 메서드.
 *  정확한 long 숫자가 들어오지 않으면 계속 반복처리한다.
 * </p>
 * 
 * 	@버전		1.0
 *  @작성일	2021.01.21
 *  @작성자	이영환
 */
public class Input {

	private static final Scanner SCANNER = new Scanner(System.in);
	
	// 문자열만 받아내는 기본 메서드
	public static String getString() {
		return SCANNER.nextLine();
	}
	
	// 메시지를 출력 문자열 받기
	public static String getString(String msg) {
		System.out.print(msg + " -> ");
		return getString();
	}
	

	// int 데이터를 받는 메서드
	public static int getInt() {
		while(true) {
			try {
				System.out.print("int 숫자입력 - > ");
				return Integer.parseInt(getString());
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("int 숫자만 입력하셔야 합니다.");
			}
		}
	}
	
	// int 데이터를 받는 메서드 - 입력 메시지 출력을 먼저하는 메서드
	public static int getInt(String msg) {
		System.out.println(msg);
		return getInt();
	}
	
	
	// long 데이터를 받는 메서드
	public static long getLong() {
		while(true) {
			try {
				System.out.print("long 숫자입력 - > ");
				return Long.parseLong(getString());
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("long 숫자만 입력하셔야 합니다.");
			}
		}
	}
	
	// long 데이터를 받는 메서드 - 입력 메시지 출력을 먼저하는 메서드
	public static long getLong(String msg) {
		System.out.println(msg);
		return getLong();
	}
	
	
}

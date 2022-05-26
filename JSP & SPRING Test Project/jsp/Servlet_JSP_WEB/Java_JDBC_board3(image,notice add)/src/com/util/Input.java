/*
 * 데이터를 입력하는 객체
 * getString(), getInt(), getLong()
 */
package com.util;

import java.util.Scanner;

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

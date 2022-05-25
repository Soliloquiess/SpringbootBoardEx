package com.webjjang.util.io;

import java.util.Scanner;

public class In {

	// 키보드(System.in)로 입력받는 객체 생성
	private static Scanner scanner = new Scanner(System.in);
	
	// 문자열을 입력 받는 메서드 - 메시지가 입력되지 않는 경우
	// In.getString()
	public static String getString() {
		return scanner.nextLine();
	}
	
	// 입력 받는 메시지를 출력하고 문자열을 입력받는 메서드 - 메시지가 입력되는 경우
	// In.getString("메뉴입력")
	public static String getString(String msg) {
		System.out.print(msg + " --> ");
		return getString();
	}
	
	// Long 타입 데이터를 받는 메서드 - 데이터만 받기
	public static Long getLong() throws Exception {
		// 문자열을 받아서 Long타입의 데이터로 만든 후 리턴해 준다.
		return Long.parseLong(getString());
	}
	
	// Long 타입 데이터를 받는 메서드 - 메시지 출력하고 데이터 받기
	public static Long getLong(String msg) {
		// 정확한 데이터가 들어 오기 전까지 무한 반복
		while(true) {
			// 입력이 숫자가 아닌 것이 들어오면 예외 발생.
			try {
				System.out.print(msg + " --> ");
				return getLong();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("숫자만 입력이 가능합니다. 너무 큰 숫자는 입력이 불가능합니다.");
			}
		}
	}
	
	// Integer 타입 데이터를 받는 메서드 - 데이터만 받기
	public static Integer getInt() throws Exception {
		// 문자열을 받아서 Long타입의 데이터로 만든 후 리턴해 준다.
		return Integer.parseInt(getString());
	}
	
	// Integer 타입 데이터를 받는 메서드 - 메시지 출력하고 데이터 받기
	public static Integer getInt(String msg) {
		// 정확한 데이터가 들어 오기 전까지 무한 반복
		while(true) {
			// 입력이 숫자가 아닌 것이 들어오면 예외 발생.
			try {
				System.out.print(msg + " --> ");
				return getInt();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("숫자만 입력이 가능합니다. 너무 큰 숫자는 입력이 불가능합니다.");
				System.out.println("소숫점 불가하다. Inter에 맞는 양식의 숫자 입력");
			}
		}
	}
	
}

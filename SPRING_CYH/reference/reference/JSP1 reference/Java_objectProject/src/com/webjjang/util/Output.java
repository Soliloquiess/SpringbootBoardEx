/*
 * 출력에 관련된 객체
 * posion(), menu(), greeting()
 */
package com.webjjang.util;

public class Output {

	// str 문자열을 받아서 cnt만큼 반복해서 출력한다.
	public static void line(String str, int cnt) {
		for (int i = 1; i <= cnt ; i++)
			System.out.print(str);
		// 줄바꿈
		System.out.println();
	}
	
	// 환영인사, 헤이짐인사
	public static void greeting(String str) {
		printWithLine(str, "*", 30);
	}
	
	// 위치 정보 출력하기
	public static void position(String p) {
		printWithLine("postion : " + p, "-", 30);
	}
	
	// 메뉴 출력
	public static void menu(String ... strs) {
		line("-", 40);
		for(String str : strs)
			System.out.println("- " + str);
		line("-", 40);
	}
	
	// 실행 위치 확인을 위한 출력
	public static void exePos(String str) {
		System.out.println("***+++ " + str + " +++***");
	}
	
	// 제목을 출력
	public static void title(String str) {
		printWithLine(str, "*", 20);
	}
	
	// 위, 아래 반복되는 문자와 반복 횟수를 받아서 입력받은 문자열 가운데 출력하는 메서드
	public static void printWithLine(String str, String loopChar, int cnt) {
		line(loopChar, cnt);
		System.out.println(loopChar + " " + str);
		line(loopChar, cnt);
	}
	
}

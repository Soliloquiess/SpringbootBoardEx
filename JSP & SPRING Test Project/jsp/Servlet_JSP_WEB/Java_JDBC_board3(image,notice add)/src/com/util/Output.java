package com.util;

/**
 * <h3>[Output class - JAVA Contsol 출력에 관련된 객체]</h3>
 * 
 * <p>
 * <h4>line()</h4>
 * 특별한 문자의 반복으로 한줄을 출력하는 메서드<br/>
 *  - line(반복문자열, 반복회수)
 * 
 * <h4>greeting()</h4>
 * 프로그램이 시작되거나 끝날때 사용자에게 인사를 해주는 메서드. 위아래 '*' 라인 포함.<br/>
 *  - greeting(인사말문자열)
 *  
 * <h4>position()</h4>
 * 프로그램의 실행 위치를 출력하는 메서드. 위아래 '-' 라인 포함.<br/>
 *  - position(위치정보-main>board)
 * 
 * <h4>menu()</h4>
 *  메뉴를 출력한다. 여러줄로 출력하고 싶으면 여러개의 문자열을 전달한다. 위아래 '-' 라인 포함.<br/>
 *   - menu(메뉴문자열, ..., 메뉴문자열)
 *   
 * <h4>exePos()</h4>
 * 실행하는 클래스와 메서드 정보를 출력하는 메서드. 앞뒤로 '***+++ 문자열 +++***' 둘러싸여 있다.<br/>
 *  - exePos(실행정보의 문자열)
 * 
 * <h4>title()</h4>
 * 제목을 출력하는 메서드.  위아래 '*' 라인 포함.
 *  - title(제목문자열)
 * 
 * <h4>printWithLine()</h4>
 * 위아래 전달한 문자열 라인을 포함 시켜서 입력한 문자열을 출력해 주는 메서드.
 *  - printWithLine(입력한문자열, 라인문자열, 반복회수)
 * 
 * </p>
 * 
 * 	@버전		1.0
 *  @작성일	2021.01.21
 *  @작성자	이영환
 */

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

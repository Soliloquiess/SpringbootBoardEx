/*
 * 데이터 베이스 처리를 위한 정보를 가지고 있는 객체
 * 개발환경 : 자바 8, 오라클  11g XE
 * 작성일 : 2021.01.18
 * 작성자 : 이영환
 */
package com.webjjang.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBInfo {

	// DB 정보 변수
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String ID = "java00";
	private static final String PW = "java00";
	
	// 드라이버가 있는지 여부를 변수
	private static boolean checkDriver = false;//true면 드라이버가 있다.
	
	// static 초기화 블록- 한번 사용될때 딱한번만 실행이되는 초기화블록 - static 변수의 값을 초기화시킬 때 사용한다.
	static {
		System.out.println("DBInfo.static{} ------------------------+");
		try {
			Class.forName(DRIVER);
			checkDriver = true;
			System.out.println("오라클 DB를 위한 드라이버가 존재합니다.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 사용자
			System.out.println("오라클 DB를 위한 드라이버가 존재하지 않습니다. \n이후의 DB처리에 문제가 발생됩니다.");
		}
	}
	
	// DB 연결을 하는 메서드 - DB를 사용하는 곳에서 모두 써야하므로 메서드로 선언 - Connection 객체를 넘겨준다.
	public static final Connection getConnection() throws Exception{
		// 연결을 하기 위해서 먼저 드라이버 확인 - 없으면 예외를 생성하고 던진다.
		if(!checkDriver) throw new Exception("드라이버가 존재하지 않습니다.");
		return DriverManager.getConnection(URL, ID, PW);
	}
	
	// DB 사용객체 닫기 - 연결, 실행객체 닫기 - insert, update, delete
	public static final void close(Connection con, PreparedStatement pstmt) throws Exception {
		// 예외처리를 안하고 있다. - 메서드에 throws Exception로 호출한 쪽으로 예외를 던진다.
		// if(객체가 열려져 있는가?) 열려져 있는 경우만 닫는다.
		if(con != null ) con.close(); // 연결 객체 닫기
		if(pstmt != null ) pstmt.close(); // 실행 객체 닫기
	}
	
	// DB 사용객체 닫기 - 연결, 실행, 결과 객체 닫기 - select
	public static final void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws Exception {
		// 예외처리를 안하고 있다. - 메서드에 throws Exception로 호출한 쪽으로 예외를 던진다.
		// if(객체가 열려져 있는가?) 열려져 있는 경우만 닫는다.
		close(con, pstmt); // 연결, 실행 객체 닫기 호출
		if(rs != null ) rs.close(); // 결과 객체 닫기
	}
	
}

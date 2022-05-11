package com.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	
	//DB 정보 - DB.DRIVER
	private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	//private은 여기서만 쓸것이므로 public일 필요가 없는거. 다른데는 다 지워도 됨.(다른데 classforname 다 지워도 됨)
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:orcl"; // 서버, 포트 정보 등이 있어야 한다.
	private final static String UID = "java00";
	private final static String UPW = "java00";
	
	//checkDriver가 true면 있는거. false면 없는거
	private static boolean checkDriver = false;
	//static 초기화 블록
	//처음 실행되는 main에서 class.forName(DB)코드를 넣어줘야 실행된다.
	static {
		try {
		Class.forName(DRIVER);
		//드라이버 있는 경우
		checkDriver = true;
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
		}
	}	
	//연결 객체를 받아가는 메서드 = getConnection()
	public static Connection getConnection() throws SQLException {
		if(checkDriver) return DriverManager.getConnection(URL, UID, UPW);
//		return null; 
		//예외처리
		throw new SQLException("DB드라이버가 없다");
	}
	
	//2개의 객체 닫기 - con, pstmt
	public static void close(Connection con, PreparedStatement pstmt) throws SQLException {
		if(con!=null) con.close();
		if(pstmt!=null) pstmt.close();
	}
	
	//3개의 객체 닫기 - con, pstmt, rs
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		close(con,pstmt);
		if(rs!=null) rs.close();
	}
}

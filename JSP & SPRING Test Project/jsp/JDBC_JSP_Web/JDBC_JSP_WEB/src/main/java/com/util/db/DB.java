package com.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	
	//DB ���� - DB.DRIVER
	private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	//private�� ���⼭�� �����̹Ƿ� public�� �ʿ䰡 ���°�. �ٸ����� �� ������ ��.(�ٸ��� classforname �� ������ ��)
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:orcl"; // ����, ��Ʈ ���� ���� �־�� �Ѵ�.
	private final static String UID = "java00";
	private final static String UPW = "java00";
	
	//checkDriver�� true�� �ִ°�. false�� ���°�
	private static boolean checkDriver = false;
	//static �ʱ�ȭ ���
	//ó�� ����Ǵ� main���� class.forName(DB)�ڵ带 �־���� ����ȴ�.
	static {
		try {
		Class.forName(DRIVER);
		//����̹� �ִ� ���
		checkDriver = true;
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
		}
	}	
	//���� ��ü�� �޾ư��� �޼��� = getConnection()
	public static Connection getConnection() throws SQLException {
		if(checkDriver) return DriverManager.getConnection(URL, UID, UPW);
//		return null; 
		//����ó��
		throw new SQLException("DB����̹��� ����");
	}
	
	//2���� ��ü �ݱ� - con, pstmt
	public static void close(Connection con, PreparedStatement pstmt) throws SQLException {
		if(con!=null) con.close();
		if(pstmt!=null) pstmt.close();
	}
	
	//3���� ��ü �ݱ� - con, pstmt, rs
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		close(con,pstmt);
		if(rs!=null) rs.close();
	}
}

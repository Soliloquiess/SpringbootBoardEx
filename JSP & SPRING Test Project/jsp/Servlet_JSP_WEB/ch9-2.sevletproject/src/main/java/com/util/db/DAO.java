package com.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * DAO 에 필요한 객체를 가지고 있는 부모 클래스<p>
 * - 다른 DAO프로그램들은 이것은 반드시 상속 받아서 개발해야만 한다.<br>
 * @author EZEN
 *
 */
public class DAO {

	// JDBC 사용 객체
	public Connection con = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;
	
}

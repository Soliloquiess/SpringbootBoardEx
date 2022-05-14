package com.webjjang.image.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.image.vo.ImageVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.db.DB;

public class ImageDAO {


	// 필요한 객체
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<ImageVO> list(PageObject pageObject) throws Exception{
		List<ImageVO> list = null;
		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3.
			// 3-1. 원본 데이터 가져오기
			String sql = "select i.no, i.title, i.id, m.name, "
					+ " to_char(i.writeDate, 'yyyy-mm-dd') writeDate, i.fileName "
					+ " from image i, member m "
					+ " where (m.id = i.id) order by i.no desc";
			// 3-2. 순서번호를 붙인다.
			sql = " select rownum rnum, no, title, id, name, "
					+ "	writeDate, fileName from (" + sql + ")";
			// 3-3. 페이지에 대한 데이터 가져오기
			sql = " select rnum, no, title, id, name, "
					+ "	writeDate, fileName from (" + sql + ") "
					+ " where rnum between ? and ? ";
			//4. 실행 객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, pageObject.getStartRow());
			pstmt.setLong(2, pageObject.getEndRow());
			// 5
			rs = pstmt.executeQuery();
			// 6. 
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<ImageVO>();
					ImageVO vo = new ImageVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setFileName(rs.getString("fileName"));
					
					list.add(vo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7.
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println("ImageDAO.list().list - " + list );
		return list;
	}

	// 페이지 처리를 위해서 전체 데이터 갯수를 가져오는 메서드
	public long getTotalRow(PageObject pageObject) {
		// TODO Auto-generated method stub
		long totalRow = 0;
		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			//3
			String sql = "select count(*)  from image";
			// 4.
			pstmt = con.prepareStatement(sql);
			//5. 실행
			rs = pstmt.executeQuery();
			// 6. 
			if(rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//7.
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return totalRow;
	}


	public ImageVO view(long no) throws Exception {
		// TODO Auto-generated method stub
		ImageVO vo = null;
		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3.
			String sql = "select i.no, i.title, i.content, i.id, m.name, "
					+ " to_char(i.writeDate, 'yyyy-mm-dd') writeDate, i.fileName "
					+ " from image i, member m "
					+ " where (no = ?) and (m.id = i.id) ";
			//4 
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			// 5
			rs = pstmt.executeQuery();
			// 6. 
			if(rs != null && rs.next()) {
					vo = new ImageVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setFileName(rs.getString("fileName"));
					
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7.
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println("ImageDAO.list().vo - " + vo);
		
		return vo;
	}


	public int write(ImageVO vo) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3.
			String sql = " INSERT INTO image(no, title, content, id, fileName) "  
			+ " VALUES (image_seq.nextval, ?, ?, ?, ?)";
			//4 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			pstmt.setString(4, vo.getFileName());
			// 5
			result = pstmt.executeUpdate();
			// 6. 
			System.out.println("ImageDAO.write() - 이미지 등록 성공");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try { 
				// 7.
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		
		return result;
	}

	public int update(ImageVO vo) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		
		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3.
			String sql = " UPDATE image SET title = ?, content = ? WHERE no = ? ";
			//4 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
			// 5
			result = pstmt.executeUpdate();
			// 6. 
			System.out.println("ImageDAO.update() - 이미지 정보 수정 성공");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try { 
				// 7.
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		
		return result;
	}

	public int delete(long no) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		
		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3.
			String sql = " DELETE FROM image WHERE no = ? ";
			//4 
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			// 5
			result = pstmt.executeUpdate();
			// 6. 
			System.out.println("ImageDAO.update() - 이미지 삭제 성공");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try { 
				// 7.
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		
		return result;
	}

	public int imageChange(ImageVO vo) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		// 예외처리
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3.
			String sql = " UPDATE image SET fileName = ? WHERE no = ? ";
			//4 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getFileName());
			pstmt.setLong(2, vo.getNo());
			// 5
			result = pstmt.executeUpdate();
			// 6. 
			System.out.println("ImageDAO.imageChange() - 이미지 변경 수정 성공");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try { 
				// 7.
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		
		return result;
	}
	
}

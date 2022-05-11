package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.board.vo.BoardVO;

import com.util.db.*;

public class BoardDAO {

	// �ʿ��� ��ü �ѹ��� ����
	// ���������� ���� ������ ������ �����ϸ� �ʱⰪ�� null�� �����ȴ�.
	public Connection con;
	public PreparedStatement pstmt;
	public ResultSet rs;

	// �Խ��� ����Ʈ ó�� �޼���
	public List<BoardVO> list() {
		System.out.println("boarddao.list()");
		List<BoardVO> list = null;

		// ����ó��
		try {
			// 1. 2.
			con = DB.getConnection();
			// 3.sql
			String sql = "select no, title, writer, writeDate, hit from board order by no desc";

			// 4.���ఴü
			pstmt = con.prepareStatement(sql);
			// 5. ����
			rs = pstmt.executeQuery();
			// 6.ǥ�� �Ǵ� ���
			if (rs != null) {
				// �������� �ִٸ� �����۷�
				while (rs.next()) {
					// ���� ������ ���� ����Ʈ�� ���̸� �� ��Ƽ� �ѹ��� �����ؾ� ��.(���� �ȵ�)
					if (list == null)
						list = new ArrayList<BoardVO>();
					// �������� ������ ���� ��ü ����
					BoardVO vo = new BoardVO();
					// setter�̿��ؼ� ������ ��� ����
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));

					// vo�� list�� ���

					list.add(vo);

				}
			} // end of if(rs!=null)
		} catch (Exception e) {
			// handle exception
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		} // end of try

		System.out.println("BoardDAO.list().list- " + list);
		return list;
	}

//	�Խ��� �ۺ��� ó�� �޼���
//	public BoardVO view(long no) throws Exception {
//		BoardVO vo = null;
//		
//		//����ó��
//		try {
//			//1. 2. ���ᰴü
//			con = DB.getConnection();
//			//3. sql
//			String sql  = "select no, title, content, writer, writerDate, hit from board where no= ?";
//			//4.���ఴü & �����ͼ���
//			pstmt = con.prepareStatement(sql);
//			pstmt.setLong(1, no);	//1���� �����
//			//5.����
//			rs = pstmt.executeQuery();
//			//6.ǥ�� & ������ ����
//			if(rs!=null && rs.next()) {
//				vo = new BoardVO();
//				vo.setNo(rs.getLong("no"));
//				vo.setTitle(rs.getString("title"));
//				vo.setContent(rs.getString("content"));
//				vo.setWriter(rs.getString("writer"));
//				vo.setWriteDate(rs.getString("writeDate"));
//				vo.setHit(rs.getLong("hit"));
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				//7.�ݱ�
//				DB.close(con, pstmt, rs);
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println("BoardDAO.view().vo - "+vo);
//		//������ �������� ó��
//		return vo;
//	}    

	public BoardVO view(long no) throws Exception {
		// �ֿܼ��� Ȯ���ؾ� �� ����.
		BoardVO vo = null;

		// JDBC ���α׷�
		try {
			// 1. ����̹� Ȯ�� + 2. ����
			con = DB.getConnection();
			// 3. sql
//			String sql  = "select no, title, content, writer, writerDate, hit from board where no= ?";
			String sql = "select no, title, content, writer, to_char(writeDate, 'yyyy.mm.dd') writeDate, "
					+ " hit from board where no = ?";
//			System.out.println("BoardDAO.view().sql - " + sql);
			// 4. ���ఴü && ������
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			// 5. ����
			rs = pstmt.executeQuery();
			// 6. ǥ�� / ������ ���
			if (rs != null && rs.next()) {
				// �����Ͱ� ������ vo �� �����Ѵ�.
				vo = new BoardVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("�Խ��� �ۺ��� - ������ �������� �� DB ����");
		} finally {
			// 7. �ݱ�
			DB.close(con, pstmt, rs);
		}

		return vo;
	} // view()�� ��

	public void write(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		// ����ó��
		try {
			// 1.2. ����
			con = DB.getConnection();
			// 3.sql
			String sql = "insert into board(no, title, content,writer) values(board_seq.nextval,?,?,?)";

			// 4.���ᰴü & ������ ����
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());

			// 5. ����
			int result = pstmt.executeUpdate();
			// 6.ǥ��
			System.out.println((result == 1) ? "�Խ��� ��� �Ϸ� " : "�Խ��� ��� ����");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 7.�ݱ�
				DB.close(con, pstmt);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	// 4. �ۼ���
	public int update(BoardVO vo) throws Exception {
		System.out.println("BoardDAO.update().vo - " + vo);
		int result = 0;
		// �ʿ��� ��ü -- ���� ���������� ���� - ����
		// JDBC ���α׷�
		try {
			// 1. ����̹� Ȯ�� + 2. ����
			con = DB.getConnection();
			// 3. sql
			String sql = "update board set title = ?, content = ?, writer = ? where no = ?";
			// 4. ���ఴü & ������ ����
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setLong(4, vo.getNo());
			// 5. ����
			result = pstmt.executeUpdate();
			// 6. ���ǥ�� & ������ ���

			if (result == 0)
				System.out.println("������ �����Ͱ� �������� �ʽ��ϴ�.");
			else
				System.out.println("������ ������ �Ϸ�Ǿ����ϴ�.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("�Խ��� �ۼ��� - �ۼ��� �� DB ����");
		} finally {
			// 7. �ݱ�
			DB.close(con, pstmt);
		}

		return result;
	}

	// 5. �ۻ���
	public int delete(long no) throws Exception {
		System.out.println("BoardDAO.delete().no - " + no);
		int result = 0;

		// JDBC ���α׷�
		try {
			// 1. ����̹� Ȯ�� + 2. ����
			con = DB.getConnection();
			// 3.
			String sql = "delete from board where no = ?";
			// 4.
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			// 5.
			result = pstmt.executeUpdate();
			// 6.
			if (result == 0)
				System.out.println("������ Ȯ���� �ּ���.");
			else
				System.out.println("�����Ͱ� ���� �Ǿ����ϴ�.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("�Խ��� �ۻ��� - �ۻ��� �� DB ����");
		} finally {
			// 7. �ݱ�
			DB.close(con, pstmt);
		}

		return result;
	}
}
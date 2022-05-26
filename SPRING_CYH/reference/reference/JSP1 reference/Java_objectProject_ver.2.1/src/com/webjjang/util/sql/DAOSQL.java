package com.webjjang.util.sql;

public class DAOSQL {

	//=============== 게시판에 활용되는 쿼리 ==================
	// 1. 게시판 리스트
	public static final String BOARD_LIST 
	// 3) 붙여진 순서번호 중에서 페이지(1)에 알맞은 데이터 가져오기
	= " select rnum, no, title, writer, to_char(writeDate, 'yyyy.mm.dd') writeDate, hit "
	+ " from ( "
		// 2) 가져온 원본데이터에 순서 번호 붙이기
	    + " select rownum rnum, no, title, writer, writeDate, hit from ( "
	    	// 1) 원본 데이터 가져오기
	    	+ " select no, title, writer, writeDate, hit from  board "
	    	+ " order by no desc "
	    + " ) "
	+ " ) where rnum between 1 and 10 ";
	
	// 2. 게시판 글보기
	public static final String BOARD_VIEW
	= " select no, title,content, writer,  to_char(writeDate, 'yyyy.mm.dd') writeDate, hit "
	+ " from board where no = ?";
	// 2-1. 게시판 글보기 전에 먼저 조회수 1증가시키는 쿼리
	public static final String BOARD_INCREASE
	= " update board set hit = hit + 1 where no = ?";

	// 3. 게시판 글쓰기
	public static final String BOARD_WRITE
	= " insert into board(no, title, content, writer) values(board_seq.nextval, ?, ?, ?) ";

	// 4. 게시판 글수정
	public static final String BOARD_UPDATE
	= " update board set title = ?, content = ?, writer = ? where no = ? ";
	
	// 5. 게시판 글삭제
	public static final String BOARD_DELETE
	= " delete from board where no = ? ";
	
	
	//=============== 이미지 게시판에 활용되는 쿼리 ==================
	// 1. 이미지 게시판 리스트
	public static final String IMAGE_LIST 
	// 3) 붙여진 순서번호 중에서 페이지(1)에 알맞은 데이터 가져오기
	= " select rnum, no, title, id, to_char(writeDate, 'yyyy.mm.dd') writeDate, fileName "
			+ " from ( "
			// 2) 가져온 원본데이터에 순서 번호 붙이기
			+ " select rownum rnum, no, title, id, writeDate, fileName from ( "
				// 1) 원본 데이터 가져오기
				+ " select no, title, id, writeDate, fileName from image "
				+ " order by no desc "
			+ " ) "
		+ " ) where rnum between 1 and 10 ";
	
	// 2. 이미지 게시판 글보기
	public static final String IMAGE_VIEW
	= " select no, title,content, id,  to_char(writeDate, 'yyyy.mm.dd') writeDate, fileName "
			+ " from image where no = ?";
	
	// 3. 이미지 게시판 글쓰기
	public static final String IMAGE_WRITE
	= " insert into image(no, title, content, id, fileName) values(image_seq.nextval, ?, ?, ?, ?) ";
	
	// 4. 이미지 게시판 글수정
	public static final String IMAGE_UPDATE
	= " update image set title = ?, content = ? where no = ? ";
	
	// 5. 이미지 게시판 글삭제
	public static final String IMAGE_DELETE
	= " delete from image where no = ? ";
	
	
	//=============== 공지사항에 활용되는 쿼리 ==================
	// 1. 공지 리스트
	public static final String NOTICE_LIST 
	// 3) 붙여진 순서번호 중에서 페이지(1)에 알맞은 데이터 가져오기
	= " select rnum, no, title, "
			+ " to_char(startDate, 'yyyy.mm.dd') startDate, "
			+ " to_char(endDate, 'yyyy.mm.dd') endDate, "
			+ " to_char(writeDate, 'yyyy.mm.dd') writeDate, "
			+ " to_char(updateDate, 'yyyy.mm.dd') updateDate "
			+ " from ( "
			// 2) 가져온 원본데이터에 순서 번호 붙이기
			+ " select rownum rnum, no, title, startDate, endDate, writeDate, updateDate from ( "
			// 1) 원본 데이터 가져오기
			+ " select no, title, startDate, endDate, writeDate, updateDate from notice "
			+ " order by startDate asc "
			+ " ) "
			+ " ) where rnum between 1 and 10 ";
	
	// 2. 공지보기
	public static final String NOTICE_VIEW
	= " select no, title, content, "
			+ " to_char(startDate, 'yyyy.mm.dd') startDate, "
			+ " to_char(endDate, 'yyyy.mm.dd') endDate, "
			+ " to_char(writeDate, 'yyyy.mm.dd') writeDate, "
			+ " to_char(updateDate, 'yyyy.mm.dd') updateDate "
			+ " from notice where no = ?";
	
	// 3. 공지쓰기
	public static final String NOTICE_WRITE
	= " insert into notice(no, title, content, startDate, endDate) values(notice_seq.nextval, ?, ?, ?, ?) ";
	
	// 4. 공지수정
	public static final String NOTICE_UPDATE
	= " update notice set title = ?, content = ?, startDate = ?, endDate = ? where no = ? ";
	
	// 5. 공지삭제
	public static final String NOTICE_DELETE
	= " delete from notice where no = ? ";
	
}

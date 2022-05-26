package com.util.sql;

public class DAOSQL {
//여기에 SQL에 해당되는 내용을 다 만든다.
	
	
	// 게시판에 활용되는 쿼리
	// 1. 게시판 리스트
	public static final String BOARD_LIST 
	// 3) 붙여진 순서번호 중에서 페이지(1)에 알맞은 데이터 가져오기		(순서번호 = rnum)
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
	
}

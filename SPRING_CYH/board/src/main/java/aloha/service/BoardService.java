package aloha.service;

import java.util.List;

import aloha.domain.Board;

public interface BoardService {
	
	//게시글 쓰기
	public void register(Board board) throws Exception;
	//게시글 목록
	public List<Board> list() throws Exception;
	//게시글 조회
	public Board read(Integer boardNo) throws Exception;

	//게시글 수정
	public void modify(Board board) throws Exception;	//메서드 이름 달라도 됨. 매퍼로 타고들어감
	
	//게시글 삭제
	public void remove(Integer boardNo) throws Exception;
}

package hell.service;

import java.util.List;

import hell.domain.Board;
import hell.domain.BoardAttach;
import hell.domain.Page;
import hell.domain.Reply;

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
	
	//게시글 검색
	public List<Board> search(String keyword) throws Exception;
	
	//전체 게시글 수
	public Integer totalCount() throws Exception;
	
	//페이지 게시글 목록
	public List<Board> list(Page page) throws Exception;
	
	//검색어 게시글 수 조회
	public Integer totalCount(String keyword) throws Exception;
	
	//[페이지][검색어] 게시글 검색
	public List<Board> search(Page page) throws Exception;


	//파일 업로드
	
	public void uploadFile(BoardAttach attach) throws Exception;
	
	
	//파일 목록
		public List<BoardAttach> readFileList(Integer boardNo) throws Exception;
	
		
		// 파일 삭제
		
		public void deleteFile(Integer fileNo ) throws Exception;
		
		//	댓글목록
		public List<Reply> replyList(Integer boardNo) throws Exception;
	
		//댓글 등록
		public void replyRegister (Reply reply) throws Exception;
		
		//댓글 수정
		public void replyModify (Reply reply) throws Exception;
}

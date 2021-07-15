package hell.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hell.domain.Board;
import hell.domain.BoardAttach;
import hell.domain.Page;
import hell.domain.Reply;

@Mapper
public interface BoardMapper {
	// 게시글 쓰기
	public void insert(Board board) throws Exception;

	// 게시글 목록

	public List<Board> list() throws Exception;

	// 게시글 조회
	public Board read(Integer boardNo) throws Exception;

	// 게시글 수정
	public void update(Board board) throws Exception;

	// 게시글 삭제
	public void delete(Integer boardNo) throws Exception;

	// 게시글 검색
	public List<Board> search(String keyword) throws Exception;

	// 전체 게시글 수
	public Integer totalCount() throws Exception;

	// 페이지 게시글 목록
	public List<Board> listWithPage(Page page) throws Exception;

	// 검색어 게시글 수
	public Integer totalCountByKeyword(String keyword) throws Exception;

	// [페이지][검색어] 게시글 검색
	public List<Board> searchWithPage(Page page) throws Exception;

	// 파일 업로드

	public void uploadFile(BoardAttach attach) throws Exception;

	// 파일 목록
	public List<BoardAttach> readFileList(Integer boardNo) throws Exception;

	// 파일 삭제

	public void deleteFile(Integer fileNo) throws Exception;

	// 댓글목록
	public List<Reply> replyList(Integer boardNo) throws Exception;

	// 댓글 등록
	public void replyCreate(Reply reply) throws Exception;
	
	//댓글 수정
	public void replyUpdate (Reply reply) throws Exception;
}

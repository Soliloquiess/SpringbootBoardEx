package aloha.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import aloha.domain.Board;
import aloha.domain.BoardAttach;
import aloha.domain.BoardDTO;
import aloha.domain.Page;
import aloha.domain.Reply;
import aloha.service.BoardService;
import aloha.util.FileUtils;



@RestController	//@Controller  + @ResponseBody
@RequestMapping("/board")
//경로
public class BoardController {
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired 
	//의존성 자동주입
	private BoardService service;

	//게시글 목록
	@GetMapping({"/",""})
	public BoardDTO list(Board board, Page page) throws Exception{
		
		String keyword = page.getKeyword();
		Integer totalCount = null;
		Integer rowsPerPage= null;
		Integer pageCount = null;
		
		//조회된 게시글의 수
		if(page.getTotalCount()==0)
			totalCount = service.totalCount();
		else 
			totalCount = page.getTotalCount(); 
		
		//페이지당 노출 게시글 수 
		if(page.getRowsPerPage()==0)
			rowsPerPage=10;
		else
			rowsPerPage= page.getRowsPerPage();
		
		//노출 페이지 수
		if(page.getPageCount()==0)
			pageCount =10;
		else
			pageCount = page.getPageCount();
		
		if(page.getPageNum()==0) {
			page = new Page(1,rowsPerPage,pageCount,totalCount, keyword);
		} else {
			page = new Page(page.getPageNum(),rowsPerPage,pageCount,totalCount,keyword);
		}
		
		List<Board> list = null;
		if(keyword==null||keyword=="") {
			page.setKeyword("");
			list=service.list(page);
		
			for(Board b :list) {
				log.info(b.toString());
			}
		}
		else {
			page.setKeyword(keyword);
			list =service.search(page);
		}
		
		BoardDTO boardDTO = new BoardDTO(list,page);
		
		return boardDTO;
		//전달받은거 모델등록
	}
	
	//페이지
//	@GetMapping("/page")
//	public Page page(Board board, Page page) throws Exception{
//		
//		String keyword = page.getKeyword();
//		Integer totalCount = null;
//		Integer rowsPerPage= null;
//		Integer pageCount = null;
//		
//		//조회된 게시글의 수
//		if(page.getTotalCount()==0)
//			totalCount = service.totalCount();
//		else 
//			totalCount = page.getTotalCount(); 
//		
//		//페이지당 노출 게시글 수 
//		if(page.getRowsPerPage()==0)
//			rowsPerPage=10;
//		else
//			rowsPerPage= page.getRowsPerPage();
//		
//		//노출 페이지 수
//		if(page.getPageCount()==0)
//			pageCount =10;
//		else
//			pageCount = page.getPageCount();
//		
//		if(page.getPageNum()==0) {
//			page = new Page(1,rowsPerPage,pageCount,totalCount, keyword);
//		} else {
//			page = new Page(page.getPageNum(),rowsPerPage,pageCount,totalCount,keyword);
//		}
//		
//		if(keyword==null||keyword=="") {
//			page.setKeyword("");
//			page.setKeyword(keyword);
//		}
//		
//		return page;
//		//전달받은거 모델등록
//	}
	

	
	
	//게시글 쓰기 - 화면,처리
	@PostMapping({"/",""})
	public void register(@RequestBody Board board) throws Exception{
				// [파일 정보]
				MultipartFile[] files = board.getFile();
				
				// 글쓰기 요청
				service.register(board);
				
				// 파일업로드 - File
				ArrayList<BoardAttach> attachList = FileUtils.uploadFiles(files);
				
				// 파일업로드 - DB
				for (BoardAttach attach : attachList) {
					service.uploadFile(attach);
				}
	}
	
	//게시글 읽기 - 파일
	@GetMapping("/{boardNo}")
	public BoardDTO read(@PathVariable Integer boardNo, Board board) throws Exception {
		board = service.read(boardNo);
		List<BoardAttach> attachList=service.readFileList(boardNo);
		List<Reply> replyList = service.replyList(boardNo);
		
//		BoardDTO boardDTO = new BoardDTO();
//		boardDTO.setBoard(board);
//		boardDTO.setAttachList(attachList);
//		boardDTO.setReplyList(replyList);
	
		BoardDTO boardDTO = new BoardDTO(board,attachList, replyList);
		
		return boardDTO;
//		return service.read(boardNo);
	}
	

//	//게시글 읽기 - 파일 목록
//	@GetMapping("/read/files")
//	public List<BoardAttach> readFile(Integer boardNo) throws Exception {
//		return service.readFileList(boardNo);
//	}
//	
//	//게시글 읽기 - 댓글 목록
//	@GetMapping("/read/replys")
//	public List<Reply> readReplys(Integer boardNo) throws Exception {
//		
//		List<Reply> replyList = service.replyList(boardNo);
//		
//		for(Reply reply: replyList) {
//			log.info(reply.toString());
//		}
//		return replyList;
////		return service.replyList(boardNo);
//	}
	
	
	
	//게시글 수정
	@PutMapping("/{boardNo}")
	public void modify(@PathVariable Integer boardNo , @RequestBody Board board) throws Exception{
		
		
		
		// [파일 정보]
		MultipartFile[] files = board.getFile();
		
		//글 수정 요청
		service.modify(board);
		
		// 파일업로드 - File
		ArrayList<BoardAttach> attachList = FileUtils.uploadFiles(files);
		
		// 파일업로드 - DB
		for (BoardAttach attach : attachList) {
			service.uploadFile(attach);
		}
	}
	
	
	//게시글 삭제
	@DeleteMapping("/{boardNo}")
	public void delete(@PathVariable Integer boardNo , @RequestBody Board board) throws Exception{
		
		// 게시글 삭제 요청
		service.remove(boardNo);
		
		// 게시글에 첨부된 파일목록 조회
		List<BoardAttach> attachList =  service.readFileList(boardNo);
		
		// 게시글에 첨부한 파일 삭제 - File
		boolean delChk = FileUtils.deleteFiles(attachList);
		
		//실제 파일 삭제되면 디비파일도 삭제(게시글 첨부한 파일 삭제 -DB)
		if( delChk ) {
			service.deleteFiles(boardNo);
		}
	}
	
	/* 댓글 */
	
	
	// 댓글 목록
	@GetMapping("/{boardNo}/replys")
	public List<Reply> replyList(@PathVariable("boardNo") Integer boardNo) throws Exception{	//PathVariable("boardNo") Integer boardNo ()안 생략가능
//		Integer boardNo = board.getBoardNo();
		
		List<Reply> replyList = service.replyList(boardNo);
		return replyList;
	}
			
			
	// 댓글 쓰기
	@PostMapping("/{boardNo}/replys")
	public void replyWrite(@PathVariable("boardNo") Integer boardNo,Reply reply) throws Exception{
		service.replyRegister(reply);
	}
	
	
	// 댓글 읽기
	@GetMapping("/{boardNo}/replys/{replyNo}")
	public Reply replyRead(@PathVariable("boardNo") Integer boardNo,@PathVariable Integer replyNo) throws Exception{
		Reply reply = new Reply();
		reply.setBoardNo(boardNo);
		reply.setReplyNo(replyNo);
		
		service.replyRead(reply);
		
		return reply;
	}
	
	// 댓글 수정
	@PutMapping("/{boardNo}/replys/{replyNo}")
		public void replyUpdate(@PathVariable("boardNo") Integer boardNo,@PathVariable Integer replyNo , Reply reply) throws Exception{
			service.replyModify(reply);
	}
	
	// 댓글 삭제
	@DeleteMapping("/{boardNo}/replys/{replyNo}")
	public void replyDelete(@PathVariable("boardNo") Integer boardNo,@PathVariable Integer replyNo , Reply reply) throws Exception{
		reply.setBoardNo(boardNo);
		reply.setReplyNo(replyNo);
		service.replyRemove(reply);
	}
	
	// 댓글 삭제(전체)
	@DeleteMapping("/{boardNo}/replys")
	public void replysDelete(@PathVariable("boardNo") Integer boardNo,@PathVariable Integer replyNo , Reply reply) throws Exception{
		
		reply.setBoardNo(boardNo);
		reply.setBoardNo(boardNo);
		service.replyRemoveAll(reply);	//
	}
	
	/* 파일 */
	
	//파일 목록
	@GetMapping("/{boardNo}/files")
	public List<BoardAttach> fileList(@PathVariable Integer boardNo) throws Exception{
		return service.readFileList(boardNo);
	}
	//파일 등록
	@PostMapping("/{boardNo}/files")
	public void fileWrite(@PathVariable Integer boardNo, Board board) throws Exception{
		// [파일 정보]
		MultipartFile[] files = board.getFile();

		// 파일업로드 - File
		ArrayList<BoardAttach> attachList = FileUtils.uploadFiles(files);
		
		// 파일업로드 - DB
		for (BoardAttach attach : attachList) {
			service.uploadFile(attach);
		}
	}
	//파일 읽기
	@GetMapping("/{boardNo}/files/{fileNo}")
	public BoardAttach fileRead(@PathVariable Integer boardNo, @PathVariable Integer fileNo, BoardAttach attach) throws Exception{
		
		attach.setBoardNo(boardNo);
		attach.setFileNo(fileNo);
		
		return service.readFile(attach);
	}
	
	//파일 삭제
	@DeleteMapping("/{boardNo}/files/{fileNo}")
	public void fileDelete(@PathVariable Integer boardNo, @PathVariable Integer fileNo, BoardAttach attach) throws Exception{
		service.deleteFile(fileNo);
	}
	//파일 삭제(전체)
	
	@DeleteMapping("/{boardNo}/files")
	public void filesDelete(@PathVariable Integer boardNo, @PathVariable Integer fileNo, BoardAttach attach) throws Exception{
		service.deleteFiles(fileNo);
	}
}
	




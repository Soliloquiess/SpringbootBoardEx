package aloha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import aloha.domain.Board;
import aloha.domain.Page;
import aloha.service.BoardService;

@Controller
@RequestMapping("/board")
//경로
public class BoardController {
	
	@Autowired 
	//의존성 자동주입
	private BoardService service;
	
	//게시글 쓰기 - 화면,처리
	
	@GetMapping("/register")
//	@RequestMapping(vaule="/register",method = RequestMethod.GET)
	public void registerForm(Model model, Board board) throws Exception{
	
	}
	
	@PostMapping("/register")
	public String register(Model model, Board board) throws Exception{
		//글쓰기 요청
		service.register(board);	//DTO 넘김
		
		model.addAttribute("msg", "등록완료");
		return "board/success";	//이동
		
	}
	
	//게시글 목록
	@GetMapping("/list")
	public void list(Model model, Board board, Page page) throws Exception{
		
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
		
		if(keyword==null||keyword=="") {
			page.setKeyword("");
			model.addAttribute("list",service.list(page));
		}else {
			page.setKeyword(keyword);
			model.addAttribute("list",service.search(page));
		}
		
		
		model.addAttribute("page",page);
		//전달받은거 모델등록
	}
	
	//게시글 읽기
	
	@GetMapping("/read")
	public void read(Model model, Integer boardNo) throws Exception{
		
		model.addAttribute("board",service.read(boardNo));
		
	}
	
	//게시글 수정화면
	
	@GetMapping("/modify")
	public void modifyForm(Model model, Integer boardNo) throws Exception{

		model.addAttribute("board",service.read(boardNo));
	}
	
	
	//게시글 수정처리
	
	@PostMapping("/modify")
	public String modify(Model model, Board board) throws Exception{

		service.modify(board);	//board객체 받아옴
		model.addAttribute("msg","수정 완료되었습니다.");
		return "board/success";
		
	}
	
	//게시글 삭제처리
	
	@PostMapping("/remove")
	public String remove(Model model, Integer boardNo) throws Exception{

		service.remove(boardNo);
		model.addAttribute("msg","삭제 완료되었습니다.");
		return "board/success";
		
	}
	
	//게시글 검색
	@PostMapping("/search")
	public String search(Model model, Page page) throws Exception{	//keyword대신 page객체로 받아오는 거로 바꿈
		
		

		String keyword = page.getKeyword();
		Integer totalCount = service.totalCount(keyword);
		Integer rowsPerPage= null;
		Integer pageCount = null;
		
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
		
		
		page = new Page(1,rowsPerPage,pageCount,totalCount, keyword);

		
		model.addAttribute("list",service.search(page));	//page객체 포함하는 search
		model.addAttribute("page",page);
			
		
		return "board/list";
//		결과 창 동일
	}
	
	
	//성공
}

package aloha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import aloha.domain.Board;
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
	public void list(Model model, Board board) throws Exception{
		
		model.addAttribute("list",service.list());
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
	public String search(Model model, String keyword) throws Exception{
		
		model.addAttribute("list",service.search(keyword));
		//
		
		service.search(keyword);
		
		return "board/list";
//		결과 창 동일
	}
	
	
	//성공
}

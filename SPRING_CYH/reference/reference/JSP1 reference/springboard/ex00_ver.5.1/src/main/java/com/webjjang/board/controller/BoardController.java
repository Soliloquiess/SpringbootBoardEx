package com.webjjang.board.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webjjang.board.service.BoardService;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.myapp.HomeController;
import com.webjjang.util.PageObject;
import com.webjjang.util.cookieUtil.CookieUtil;
import com.webjjang.util.msg.MSGUtil;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	// 의존성 자동 주입(Dependency Inject) -> 자동으로 하도록 지정하는 어노테이션 : @Autowired, @Inject
	@Autowired
	private BoardService service;
	
	// 1. list
	// 처리 결과를 request에 담아야 하는데 Spring에서는 request가 model에 존재한다.
	// model에 넣어주면 request에 담기도록 프로그램 되어있다. 파라메터로 모델 객체를 전달 받아서 사용한다.
	@GetMapping("/list.do")
	public String list(@ModelAttribute PageObject pageObject, Model model) throws Exception{
		// 페이지가 1보다 작으면 1페이지로 세팅해 준다.
		if(pageObject.getPage() < 1) pageObject.setPage(1);
		
		System.out.println("BoardController.list().pageObject - " + pageObject);
		
		model.addAttribute("list", service.list(pageObject));
		return "board/list";
	}
	
	// 2. view
	// 처리 결과를 request에 담아야 하는데 Spring에서는 request가 model에 존재한다.
	// model에 넣어주면 request에 담기도록 프로그램 되어있다. 파라메터로 모델 객체를 전달 받아서 사용한다.
	@GetMapping("/view.do")
	public String view(long no, int inc, Model model) throws Exception {
		System.out.println("BoardController.view().no,inc - " + no + ", " + inc);
		
		BoardVO vo = service.view(no, inc);
		// 글 내용 중에서 줄바꿈처리 해야만 한다. \n -> <br>로 바꾼다.
		vo.setContent(vo.getContent().replace("\n", "<br>"));
		model.addAttribute("vo", vo);
		
		return "board/view";
	}
	// 3-1. writeForm
	@GetMapping("/write.do")
	public String writeForm() throws Exception{
		return "board/write";
	}
	// 3-2. write
	@PostMapping("/write.do")
	public String write(BoardVO vo, int perPageNum, RedirectAttributes rttr, HttpServletResponse response) throws Exception{
		System.out.println("BoardController.write().vo - " + vo);
		service.write(vo);
		
		// redirect 하는 페이지에서 한번만 사용되는 속성값을 전달할 수 있다. -> session
//		rttr.addFlashAttribute("msg", "성공적으로 글 등록이 되었습니다.");
		
		CookieUtil.createMessageCookie(MSGUtil.MSG_BOARD_WRITE, response);
		
		return "redirect:list.do?page=1&perPageNum=" + perPageNum;
	}
	// 4-1. updateForm
	@GetMapping("update.do")
	public String updateForm(long no, Model model) throws Exception {
		System.out.println("BoardController.updateForm().no - " + no);
		
		model.addAttribute("vo", service.view(no, 0));
		
		return "board/update";
	}
	// 4-2. update
	@PostMapping("update.do")
	public String update(PageObject pageObject, BoardVO vo) throws Exception{
		System.out.println("BoardController.update().vo - " + vo);
		service.update(vo);
		return "redirect:view.do?no=" + vo.getNo() + "&inc=0"
				+ "&page="+ pageObject.getPage() +"&perPageNum=" + pageObject.getPerPageNum()
				+ "&key="+ pageObject.getKey() +"&word=" + pageObject.getWord();
	}
	// 5. delete
	@GetMapping("delete.do")
	public String delete(long no, int perPageNum, RedirectAttributes rttr) throws Exception{
		System.out.println("BoardController.delete().no - " + no);
		service.delete(no);
		
		// redirect 하는 페이지에서 한번만 사용되는 속성값을 전달할 수 있다. -> session
		rttr.addFlashAttribute("msg", "성공적으로 글 삭제가 되었습니다.");
		
		return "redirect:list.do?perPageNum=" + perPageNum;
	}
	
	
}

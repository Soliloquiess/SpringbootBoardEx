package com.webjjang.notice.controller;

import java.net.URLEncoder;

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

import com.webjjang.notice.service.NoticeService;
import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.myapp.HomeController;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/notice")
// log 객체를 자동으로 만들어 준다.
@Log4j
public class NoticeController {

	// private static final Logger log = LoggerFactory.getLogger(NoticeController.class);
	
	// 의존성 자동 주입(Dependency Inject) -> 자동으로 하도록 지정하는 어노테이션 : @Autowired, @Inject
	@Autowired
	private NoticeService service;
	
	// 1. list
	// 처리 결과를 request에 담아야 하는데 Spring에서는 request가 model에 존재한다.
	// model에 넣어주면 request에 담기도록 프로그램 되어있다. 파라메터로 모델 객체를 전달 받아서 사용한다.
	// period - 현재 공지 : pre, 지난공지 : old, 예약공지 : res, 전체 공지 : all
	@GetMapping("/list.do")
	public String list(@ModelAttribute PageObject pageObject, Model model) throws Exception{
		// 페이지가 1보다 작으면 1페이지로 세팅해 준다.
		if(pageObject.getPage() < 1) pageObject.setPage(1);
		
		// 콘솔에 출력
		System.out.println("NoticeController.list().pageObject - " + pageObject);
		
		// 로그 정보 출력
		log.info("log info 출력 - pageObject : " + pageObject);
		// 로그 경고 출력
		// log.warn("log warn 출력 - pageObject : " + pageObject);
		// 로그 오류 출력
		// log.error("log error 출력 - pageObject : " + pageObject);
		
		model.addAttribute("list", service.list(pageObject));
		return "notice/list";
	}
	
	// 2. view
	// 처리 결과를 request에 담아야 하는데 Spring에서는 request가 model에 존재한다.
	// model에 넣어주면 request에 담기도록 프로그램 되어있다. 파라메터로 모델 객체를 전달 받아서 사용한다.
	@GetMapping("/view.do")
	public String view(long no, Model model) throws Exception {
		System.out.println("NoticeController.view().no - " + no );
		
		NoticeVO vo = service.view(no);
		// 글 내용 중에서 줄바꿈처리 해야만 한다. \n -> <br>로 바꾼다.
		vo.setContent(vo.getContent().replace("\n", "<br>"));
		model.addAttribute("vo", vo);
		
		return "notice/view";
	}
	// 3-1. writeForm
	@GetMapping("/write.do")
	public String writeForm() throws Exception{
		return "notice/write";
	}
	// 3-2. write
	@PostMapping("/write.do")
	public String write(NoticeVO vo, int perPageNum, RedirectAttributes rttr) throws Exception{
		System.out.println("NoticeController.write().vo - " + vo);
		service.write(vo);
		
		// redirect 하는 페이지에서 한번만 사용되는 속성값을 전달할 수 있다. -> session
		rttr.addFlashAttribute("msg", "성공적으로 공지 글이 등록되었습니다.");

		return "redirect:list.do?page=1&perPageNum=" + perPageNum;
	}
	// 4-1. updateForm
	@GetMapping("update.do")
	public String updateForm(long no, Model model) throws Exception {
		System.out.println("NoticeController.updateForm().no - " + no);
		
		model.addAttribute("vo", service.view(no));
		
		return "notice/update";
	}
	// 4-2. update
	@PostMapping("update.do")
	public String update(PageObject pageObject, NoticeVO vo, RedirectAttributes rttr) throws Exception{
		System.out.println("NoticeController.update().vo - " + vo);
		service.update(vo);
		
		// redirect 하는 페이지에서 한번만 사용되는 속성값을 전달할 수 있다. -> session
		rttr.addFlashAttribute("msg", "성공적으로 공지 글이 수정되었습니다.");
		
		return "redirect:view.do?no=" + vo.getNo()
				+ "&page="+ pageObject.getPage() +"&perPageNum=" + pageObject.getPerPageNum()
				// 자바 부분의 한글 코드와 운영 한글 코드가 다르므로 자바에서 꺼내서 넣으면 깨진다. 엔코딩을 해야 한다.
				+ "&key="+ pageObject.getKey() +"&word=" + URLEncoder.encode(pageObject.getWord(), "utf-8")
				;
	}
	// 5. delete
	@GetMapping("delete.do")
	public String delete(long no, int perPageNum, RedirectAttributes rttr) throws Exception{
		System.out.println("NoticeController.delete().no - " + no);
		service.delete(no);
		
		// redirect 하는 페이지에서 한번만 사용되는 속성값을 전달할 수 있다. -> session
		rttr.addFlashAttribute("msg", "성공적으로 글 삭제가 되었습니다.");
		
		return "redirect:list.do?perPageNum=" + perPageNum;
	}
	
	
}

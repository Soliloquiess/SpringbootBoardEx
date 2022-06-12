package com.webjjang.image.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.webjjang.image.service.ImageService;
import com.webjjang.image.vo.ImageVO;
import com.webjjang.member.vo.LoginVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.file.FileUtil;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/image")
@Log4j
public class ImageController {

	// 자동 DI
	@Autowired
	private ImageService service;
	
	@GetMapping("/list.do")
	public String list(PageObject pageObject, Model model) throws Exception{
		
		// 페이지가 0이나 음수 값이면 1로 세팅한다.
		if(pageObject.getPage() < 1) pageObject.setPage(1);
		// perPageNum == 10이면 8로 고치자. perPageNum - 4, 8, 12, 16 --> 기본 한페이지의 데이터 개수는 8로 세팅한다.
		if(pageObject.getPerPageNum() == 10) pageObject.setPerPageNum(8);
		
		log.info("list()");
		
		model.addAttribute("list", service.list(pageObject));
		
		return "image/list";
	}
	
	// 이미지 보기
	@GetMapping("/view.do")
	public String view(long no, Model model) throws Exception{
		// DB에서 데이터를 가져온다
		ImageVO vo = service.view(no);
		// 내용의 줄바꿈 처리
		vo.setContent(vo.getContent().replace("\n", "<br>"));
		// model에 담는다. jsp에서 request에서 꺼낼 수 있다.
		model.addAttribute("vo", vo);
		// 가져온 데이터를 JSP에 표시하기 위해서 JSP 정보를 리턴해 준다.
		return "image/view";
	}
	
	@PostMapping("/changeImage.do")
	public String changeImage(PageObject pageObject, ImageVO vo, HttpServletRequest request) throws Exception{
		
		String path = "/upload/image";
		
		// 1. 서버에 파일을 업로드한다. -> DB에 저장할 파일정보가 나온다.
		String fileName = FileUtil.upload(path, vo.getImage(), request);
		vo.setFileName(fileName);
		
		// 2. DB에 파일 정보를 바꾼다. -> 번호, 파일명 -> controller -service - mapper
		service.changeImage(vo);
		
		// 3. 이전의 파일은 지운다.
		FileUtil.remove(FileUtil.getRealPath("", vo.getDeleteImage(), request));
		
		// 파일이 로드 되는 중간에 보기로 이동을 해 버리면 이미지가 안보인다. 그래서 올리는 동안의 시간을 일정 시간을 재워서 보기로 이동 시키지 않도록 한다.
		Thread.sleep(500);
		
		return "redirect:view.do?no=" + vo.getNo()
				+ "&page=" + pageObject.getPage()
				+ "&perPageNum=" + pageObject.getPerPageNum()
				+ "&key=" + pageObject.getKey()
				+ "&word=" + URLEncoder.encode(pageObject.getWord(), "utf-8")
				;
	}
	
	@GetMapping("/write.do")
	public String writeForm() throws Exception{
		return "image/write";
	}
	
	@PostMapping("/write.do")
	public String write(PageObject pageObject, ImageVO vo, HttpSession session, HttpServletRequest request) throws Exception{
		log.info(pageObject);
		
		// 작성자 아이디 세팅
		vo.setId(((LoginVO)session.getAttribute("login")).getId());
		
		// 파일명을 세팅한다.
		String path = "/upload/image";
		String fileName = FileUtil.upload(path, vo.getImage(), request);
		vo.setFileName(fileName);
		
		log.info(vo);
		
		//DB 정보 저장
		service.write(vo);
		
		// 파일이 로드 되는 중간에 보기로 이동을 해 버리면 이미지가 안보인다. 그래서 올리는 동안의 시간을 일정 시간을 재워서 보기로 이동 시키지 않도록 한다.
		Thread.sleep(500);

		return "redirect:list.do?page=1&perPageNum=" + pageObject.getPerPageNum();
	}
	
	// 4.이미지 수정 폼 - 정보만 수정
	@GetMapping("/update.do")
	public String updateForm(PageObject pageObject, long no, Model model) throws Exception{
		log.info(no);
		
		model.addAttribute("vo", service.view(no));
		
		return "image/update";
	}
	
	@PostMapping("/update.do")
	public String update(PageObject pageObject, ImageVO vo) throws Exception{
		
		log.info(vo);
		log.info(pageObject);
		
		//DB에 수정 : Controller - service - mapper
		service.update(vo);
		
		return "redirect:view.do?no=" + vo.getNo() 
				+ "&page=" +pageObject.getPage()
				+ "&perPageNum=" +pageObject.getPerPageNum()
				+ "&key=" +pageObject.getKey()
				+ "&word=" + URLEncoder.encode(pageObject.getWord(), "utf-8")
				;
	}
	
	@GetMapping("/delete.do")
	public String delete(PageObject pageObject, long no, String deleteImage, HttpServletRequest request) throws Exception{
		
		log.info(no);
		log.info(deleteImage);
		log.info(pageObject);
		
		// 1. DB의 정보를 지운다. - no
		service.delete(no);
		
		// 2. 서버에서 파일을 지운다. - deleteImage
		FileUtil.remove(FileUtil.getRealPath("", deleteImage, request));
		
		return "redirect:list.do?perPageNum=" + pageObject.getPerPageNum();
	}
	
}

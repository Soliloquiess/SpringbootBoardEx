package aloha.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import aloha.domain.Board;
import aloha.domain.BoardAttach;
import aloha.domain.Page;
import aloha.domain.Reply;
import aloha.service.BoardService;
import jdk.internal.org.jline.utils.Log;



@RestController	//@Controller  + @ResponseBody
@RequestMapping("/board")
//경로
public class BoardController {

	
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired 
	//의존성 자동주입
	private BoardService service;
	
	
//	//게시글 목록
//	@GetMapping("/list")
//	public List<Board> list() throws Exception{
//		return service.list();
//	}
//	
	
	
	//게시글 목록
	@GetMapping("/list")
	public List list(Board board, Page page) throws Exception{
		
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
		
		return list;
		//전달받은거 모델등록
	}
	
	//페이지
	@GetMapping("/page")
	public Page page(Board board, Page page) throws Exception{
		
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
			page.setKeyword(keyword);
		}
		
		return page;
		//전달받은거 모델등록
	}
	

	
	
	//게시글 쓰기 - 화면,처리
	@PostMapping("/register")
	public void register(@RequestBody Board board) throws Exception{
		service.register(board);
	}
	
	
	@GetMapping("/read")
	public Board read(Integer boardNo) throws Exception {
		return service.read(boardNo);
	}
	

	//게시글 읽기 - 파일 목록
	@GetMapping("/read/files")
	public List<BoardAttach> readFile(Integer boardNo) throws Exception {
		return service.readFileList(boardNo);
	}
	

	@GetMapping("/read/replys")
	public List<Reply> readReplys(Integer boardNo) throws Exception {
		
		List<Reply> replyList = service.replyList(boardNo);
		
		for(Reply reply: replyList) {
			log.info(reply.toString());
		}
		return replyList;
//		return service.replyList(boardNo);
	}
	
	
	
	//게시글 수정
	@PutMapping("/modify")
	public void modify(@RequestBody Board board) throws Exception{
		service.modify(board);
	}
	
	
	//게시글 삭제
	@DeleteMapping("/delete")
	public void delete(@RequestBody Board board) throws Exception{
		service.remove(board.getBoardNo());
	}
}
	
	// 
	//logger
	
//	
//	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
//
//	//업로드 경로
//	@Value("${upload.path}")
//	private String uploadPath;
//	
//	
//	@GetMapping("/register")
////	@RequestMapping(vaule="/register",method = RequestMethod.GET)
//	public void registerForm(Model model, Board board) throws Exception{
//	
//	
//	}
//	@PostMapping("/register")
//	public String register(Model model, Board board) throws Exception{
//		//파일정보
//		MultipartFile[] files =board.getFile();
//		for(MultipartFile file :files) {
//			log.info("originFileName:" + file.getOriginalFilename());
//			log.info("size : "+file.getSize());
//			log.info("type : "+file.getContentType());
//		}
//		
//		//파일업로드 처리 - uploadFile()
//		ArrayList<BoardAttach> attachList = uploadFiles(files);
//		
//		
//		//글쓰기 요청
//		service.register(board);	//DTO 넘김
//		//첨부파일 등록
//		
//		for(BoardAttach attach: attachList) {
//			service.uploadFile(attach); 
//			//attach 등록 
//		}
//		
//		model.addAttribute("msg", "등록완료");
//		return "board/success";	//이동
//		
//	}
//	
//	//게시글 목록
////	@GetMapping("/list")
////	public void list(Model model, Board board, Page page) throws Exception{
////		
////		String keyword = page.getKeyword();
////		Integer totalCount = null;
////		Integer rowsPerPage= null;
////		Integer pageCount = null;
////		
////		//조회된 게시글의 수
////		if(page.getTotalCount()==0)
////			totalCount = service.totalCount();
////		else 
////			totalCount = page.getTotalCount(); 
////		
////		//페이지당 노출 게시글 수 
////		if(page.getRowsPerPage()==0)
////			rowsPerPage=10;
////		else
////			rowsPerPage= page.getRowsPerPage();
////		
////		//노출 페이지 수
////		if(page.getPageCount()==0)
////			pageCount =10;
////		else
////			pageCount = page.getPageCount();
////		
////		if(page.getPageNum()==0) {
////			page = new Page(1,rowsPerPage,pageCount,totalCount, keyword);
////		} else {
////			page = new Page(page.getPageNum(),rowsPerPage,pageCount,totalCount,keyword);
////		}
////		
////		if(keyword==null||keyword=="") {
////			page.setKeyword("");
////			model.addAttribute("list",service.list(page));
////		}else {
////			page.setKeyword(keyword);
////			model.addAttribute("list",service.search(page));
////		}
////		
////		
////		model.addAttribute("page",page);
////		//전달받은거 모델등록
////	}
//	
//	//게시글 읽기 화면
//	
//	@GetMapping("/read")
//	public void read(Model model, Integer boardNo) throws Exception{
//			
//		// 게시글 조회
//		model.addAttribute("board",service.read(boardNo));
//		
//		//파일 목록 조회
//		model.addAttribute("files",service.readFileList(boardNo));
//		//댓글 목록 조회
//		// model.addAttribute("replyList", service.replyList(boardNo));
//	}
//	
//	//게시글 수정화면
//	
//	@GetMapping("/modify")
//	public void modifyForm(Model model, Integer boardNo) throws Exception{
//
//		model.addAttribute("board",service.read(boardNo));
//		model.addAttribute("files",service.readFileList(boardNo));
//	}
//	
//	
//	//게시글 수정처리
//	
//	@PostMapping("/modify")
//	public String modify(Model model, Board board) throws Exception{
//
//		service.modify(board);	//board객체 받아옴
//		model.addAttribute("msg","수정 완료되었습니다.");
//		return "board/success";
//		
//	}
//	
//	//게시글 삭제처리
//	
//	@PostMapping("/remove")
//	public String remove(Model model, Integer boardNo) throws Exception{
//		
//		List<BoardAttach> attachList =  service.readFileList(boardNo);
//		
//		// 게시글에 첨부한 파일 삭제
//		deleteFiles(attachList);
//
//		service.remove(boardNo);
//		model.addAttribute("msg","삭제 완료되었습니다.");
//		return "board/success";
//		
//	}
//	
//	//게시글 검색
//	@PostMapping("/search")
//	public String search(Model model, Page page) throws Exception{	//keyword대신 page객체로 받아오는 거로 바꿈
//		
//		
//
//		String keyword = page.getKeyword();
//		Integer totalCount = service.totalCount(keyword);
//		Integer rowsPerPage= null;
//		Integer pageCount = null;
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
//		
//		page = new Page(1,rowsPerPage,pageCount,totalCount, keyword);
//
//		
//		model.addAttribute("list",service.search(page));	//page객체 포함하는 search
//		model.addAttribute("page",page);
//			
//		
//		return "board/list";
////		결과 창 동일
//	}
//	
//	
//	//파일 업로드
//	public ArrayList<BoardAttach> uploadFiles(MultipartFile[] files) throws Exception{
//		ArrayList<BoardAttach> attachList = new ArrayList<BoardAttach>();
//	
//	
//		//업로드 경로에 파일 복사
//		for(MultipartFile file: files) {
//			//file 존재여부 확인
//			if(file.isEmpty()) {
//				continue;
//			}
//			
//			//파일 중복 방지를 위한 고유 id생성(UUID)
//			UUID uid = UUID.randomUUID();
//			
//			//실제 원본 파이 이름
//			String originalFileName = file.getOriginalFilename();
//			//UID_강아지.png.
//			String uploadFileName = uid.toString()+"_"+originalFileName;
//			//업로드 폴더에 업로드할 파일 복사(upload)
//			byte[] fileData = file.getBytes();
//			File target = new File(uploadPath, uploadFileName);
//			FileCopyUtils.copy(fileData, target);
//			
//			
//			BoardAttach attach=new BoardAttach();
//			attach.setFullName(uploadPath+"/"+uploadFileName);
//			attach.setFileName(originalFileName);
//			attachList.add(attach);
//			
//		}
//		return attachList;
//	}
//	
//	
//	// 실제 파일 삭제 - 해당 게시글 전체파일 삭제
//		public void deleteFiles(List<BoardAttach> deleteFileList) throws Exception {
//			
//			// 해당 게시글의 첨부파일 전체 삭제
//			for (BoardAttach deleteFile : deleteFileList) {
//				String fullName = deleteFile.getFullName();
//				Integer fileNo = deleteFile.getFileNo();
//				
//				File file = new File(fullName);
//				// 실제로 파일이 존재하는지 확인
//				if(file.exists()) {
//					// 파일 삭제
//					if(file.delete()) {
//						log.info("삭제한 파일 : " + fullName);
//						log.info("파일삭제 성공");
//						
//						// DB에서 해당 파일 데이터 삭제 
//						service.deleteFile(fileNo);
//					} else {
//						log.info("파일삭제 실패");
//						
//					}
//				} else {
//					log.info("삭제(실패) : " + fullName);
//					log.info("파일이 존재하지 않습니다.");
//				}
//			}
//			
//		}
//		
//		// 댓글 등록
//		
//		@PostMapping("/replyRegister")
//		public String replyRegister(Model model, Reply reply) throws Exception{
//			//Reply이라는 객체로 받아옴( boardNo, writer,content)
//			//댓글 등록
//			service.replyRegister(reply);
//			//넘어온 걸 디비 등록 먼저(service.replyRegister로 요청보냄)
//			Integer boardNo = reply.getBoardNo();
//			//댓글 목록 조회(등록된 상태에서 다시조회)
//			model.addAttribute("replyList", service.replyList((boardNo)));
//			return "reply/list";
//		}
//		
//	// 댓글 수정
//		
//		@PostMapping("/replyModify")
//		public String replyModify(Model model, Reply reply) throws Exception{
//			
//			//댓글 등록
//			service.replyModify(reply);
//			
//			Integer boardNo = reply.getBoardNo();
//			//댓글 목록 조회
//			//boardNo까지 같이 넘긴 이유? = 댓글 목록을 조회해와야 되니까 그리고 모델에 담아야되서 글 번호에 담음.
//			model.addAttribute("replyList", service.replyList((boardNo)));
//			return "reply/list";
//		}
//		
//		//댓글 삭제
//		@PostMapping("/replyRemove")
//		public String replyRemove(Model model, Reply reply) throws Exception{
//			service.replyRemove(reply);
//			
//			
//			Integer boardNo = reply.getBoardNo();
//			
//			model.addAttribute("replyList", service.replyList(boardNo));
//			//댓글 목록 조회
//			model.addAttribute("replyList", service.replyList(boardNo));
//			
//			return "reply/list";
//		}
//		
//		//댓글 목록 조회
//		@GetMapping("/replyList")
//		public String replyList(Model model, Reply reply) throws Exception{
//			
//			Integer boardNo = reply.getBoardNo();
//			//댓글 목록 조회
//			model.addAttribute("replyList", service.replyList(boardNo));
//			return "reply/list";
//		}
//		
//		//답글 쓰기 화면
//		
//		@GetMapping("/answer")
//		public void answerForm(Model model, Board board) throws Exception {
//			Integer boardNo = board.getGroupNo();
//			board = service.read(boardNo);
//			//부모글 정보 조회
//			model.addAttribute("board",board);
//			//파일 목록 조회
//			model.addAttribute("files",service.readFileList(boardNo));
//		}
//		
//		//답글 쓰기 처리
//		@PostMapping("/answerRegister")
//		public String answerRegister(Model model, Board board) throws Exception{
//			//파일 업로드
//			MultipartFile[] files = board.getFile();
//			
//			//파일 업로드 처리
//			ArrayList<BoardAttach> attachList = uploadFiles(files);
//			service.answerRegister(board);
//			
//			for(BoardAttach attach : attachList) {
//				service.uploadFile(attach);
//			}
//			model.addAttribute("msg", "등록이 완료되었습니다.");
//			
//			return "board/success";
//		
//		}
//}



//게시글 읽기, @PathVariable Integer boardNo
//@GetMapping("/read")
//	public JsonObject read(@RequestBody Board board) throws Exception{
//
//		int boardNo = board.getBoardNo();		
//		
//		JsonObject jsonObject = new JsonObject();
//		
//		board = service.read(boardNo);	//보드 게시글 정보 가져오고
//		List<BoardAttach> attachList = service.readFileList(boardNo);	//리스트로 가져온 파일 정보를
//		
//		JsonArray jsonArr = new Gson().toJsonTree(attachList).getAsJsonArray();	//json 어레이에 파싱해서 attachlist를 제이슨에 넣음
//		jsonObject.add("attachList", jsonArr);
//
//		//보드객체 정보를 문자열로 파싱해서
//		String str = "{'boardNo':"+board.getBoardNo()
//							+",'title':"+board.getTitle()
//							+",'content':"+board.getContent()
//							+",'writer':" + board.getWriter()
//							+"}";
//		
//		
//		JsonParser jsonParser =  new JsonParser();
//		JsonArray jsonArray = (JsonArray) jsonParser.parse(str);
//		
//		//게시글 정보
////		JsonElement jsonEle = new Gson().toJson(board).get;
//		jsonObject.add("board", jsonArray);
//		
//		
//		
//		return jsonObject;
//		// 게시글 조회
////		model.addAttribute("board",service.read(boardNo));
////		
////		//파일 목록 조회
////		model.addAttribute("files",service.readFileList(boardNo));
////		
////		//댓글 목록 조회
////		 model.addAttribute("replyList", service.replyList(boardNo));
//	}
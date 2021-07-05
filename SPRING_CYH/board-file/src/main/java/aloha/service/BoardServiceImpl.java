package aloha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aloha.domain.Board;
import aloha.domain.BoardAttach;
import aloha.domain.Page;
import aloha.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public void register(Board board) throws Exception {
		mapper.insert(board);
	}

	@Override
	public List<Board> list() throws Exception {
		
		return mapper.list();
	}

	@Override
	public Board read(Integer boardNo) throws Exception {
		
		return mapper.read(boardNo);
	}

	@Override
	public void modify(Board board) throws Exception {
		mapper.update(board);	//매퍼의 update호출,  추가작업 가능.
		
	}

	@Override
	public void remove(Integer boardNo) throws Exception {
		mapper.delete(boardNo);
		
	}

	@Override
	public List<Board> search(String keyword) throws Exception {
		
		return mapper.search(keyword);
	}

	@Override
	public Integer totalCount() throws Exception {
		return mapper.totalCount();
	}

	@Override
	public List<Board> list(Page page) throws Exception {
		
		return mapper.listWithPage(page);
	}

	@Override
	public Integer totalCount(String keyword) throws Exception {
		// TODO Auto-generated method stub
		return mapper.totalCountByKeyword(keyword);
	}

	@Override
	public List<Board> search(Page page) throws Exception {
		// TODO Auto-generated method stub
		return mapper.searchWithPage(page);	//페이지 넘겨줌.
	}

	@Override
	public void uploadFile(BoardAttach attach) throws Exception {
	
			mapper.uploadFile(attach);
	}

	@Override
	public List<BoardAttach> readFileList(Integer boardNo) throws Exception {
		return mapper.readFileList(boardNo);
	}

	@Override
	public void deleteFile(Integer fileNo) throws Exception {
		 mapper.deleteFile(fileNo);
		
	}
	
}

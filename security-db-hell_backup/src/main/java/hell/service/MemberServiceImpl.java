package hell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hell.domain.Member;
import hell.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper mapper;

	@Override
	public void register(Member member) throws Exception {
		// 회원정보 등록
		mapper.create(member);
		
		// 회원권한 등록
	}

	@Override
	public List<Member> list() throws Exception {
		return mapper.list();
	}
	
	
	
	
}

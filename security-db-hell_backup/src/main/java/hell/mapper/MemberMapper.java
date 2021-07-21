package hell.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hell.domain.Member;

@Mapper
public interface MemberMapper {
	
	// 회원정보 등록
	public void create(Member member) throws Exception;
	
	// 회원정보 목록 조회
	public List<Member> list() throws Exception; 
}

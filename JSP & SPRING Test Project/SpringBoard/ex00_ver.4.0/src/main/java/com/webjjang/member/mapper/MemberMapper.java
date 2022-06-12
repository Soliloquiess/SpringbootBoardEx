package com.webjjang.member.mapper;

import java.util.List;

import com.webjjang.member.vo.MemberVO;
import com.webjjang.member.vo.LoginVO;
import com.webjjang.util.PageObject;

//  interface 작성 -> interface 상속 받은 클래스 작성 -> 클래스 생성 interface 타입으로 저장해서 실행
public interface MemberMapper {
	
	// dao에서 작성한 메서드 형식으로 만들어준다.
	// interface만 만들어 두면 MyBatis 라이브러리에서 DAO를 대신 만들어 준다.
	// 주고 받는 데이터 타입 정의, sql 문과 처리 명령문이 필요한다.-> MemberMapper.xml, 
	
	// 1-1. 리스트
	public List<MemberVO> list(PageObject pageObject) throws Exception;
	// 1-2. 전체 데이터 개수
	public long getTotalRow(PageObject pageObject) throws Exception;
	// 2-1. 보기
	public MemberVO view(long no) throws Exception;
	// 3. 회원가입
	public int write(MemberVO vo) throws Exception;
	// 4. 수정
	public int update(MemberVO vo) throws Exception;
	// 5. 탈퇴
	public int delete(long no) throws Exception;

	//6. 로그인처리
	public LoginVO login(LoginVO invo) throws Exception;
}

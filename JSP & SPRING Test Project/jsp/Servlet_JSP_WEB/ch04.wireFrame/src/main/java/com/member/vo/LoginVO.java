package com.member.vo;
/**
 * 로그인 정보를 저장하는 객체
 * 아이디, 비밀번호, 이름, 등급번호, 등급명
 */
public class LoginVO {

	// 필드선언 = 변수
	private String id;
	private String pw;
	private String name;
	private int gradeNo;
	private String gradeName;
	
	// getter, setter - 데이터 읽기 쓰기
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGradeNo() {
		return gradeNo;
	}
	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	// 데이터 확인용
	@Override
	public String toString() {
		return "LoginVO [id=" + id + ", pw=" + pw + ", name=" + name + ", gradeNo=" + gradeNo + ", gradeName="
				+ gradeName + "]";
	}
	
}

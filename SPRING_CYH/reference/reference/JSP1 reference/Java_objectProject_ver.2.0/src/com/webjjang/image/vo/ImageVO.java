/*
 * 이미지 게시판 데이터를 저장하는 객체
 * - 글번호, 제목, 내용, 작성자 ID, 작성일, 파일명
 * 작성일 : 2021.01.20
 * 작성자 : 이영환
 */

package com.webjjang.image.vo;

public class ImageVO {

	private long no;
	private String title;
	private String content;
	private String id;
	private String writeDate;
	private String fileName;
	
	// getter() - 데이터 가져가기, setter() - 데이터 저장하기
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	// 데이터 확인용 - 객체를 출력하면 자동으로 호출당하는 메서드
	@Override
	public String toString() {
		return "ImageVO [no=" + no + ", title=" + title + ", content=" + content + ", id=" + id + ", writeDate="
				+ writeDate + ", fileName=" + fileName + "]";
	}
	
}

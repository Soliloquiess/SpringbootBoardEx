package com.notice.vo;

/**
 * <h2>게시판 데이터를 저장하는 객체</h2>
 * 
 * 번호, 제목, 내용, 작성자, 작성일, 조회수<p>
 * 데이터를 가져가는 getter()<br>
 * 데이터를 저장하는 setter()<br>
 * 
 * 데이터 내용을 확인하는 toString()<br>
 * 
 * @author EZEN
 *
 */

public class NoticeVO {

	private long no;
	private String title, content, writer, writeDate;
	private long hit;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public long getHit() {
		return hit;
	}
	public void setHit(long hit) {
		this.hit = hit;
	}
	
	// 데이터 확인용
	// Object안에 있는 toString() Method를 Override 했다. 덮어씌기 원래의 것을 재정의했다.
	// 객체를 출력할때 자동으로 호출되는 메서드로 동작한다.
	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer + ", writeDate="
				+ writeDate + ", hit=" + hit + "]";
	}

	
}

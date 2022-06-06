package com.webjjang.notice.vo;

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
	private String title, content, startDate, endDate, writeDate, updateDate;
	
	// setter & getter
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	// 데이터 확인용.
	@Override
	public String toString() {
		return "NoticeVO [no=" + no + ", title=" + title + ", content=" + content + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", writeDate=" + writeDate + ", updateDate=" + updateDate + "]";
	}
	
}

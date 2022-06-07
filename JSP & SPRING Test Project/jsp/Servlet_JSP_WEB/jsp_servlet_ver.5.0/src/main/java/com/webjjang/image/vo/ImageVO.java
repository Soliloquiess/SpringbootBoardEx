package com.webjjang.image.vo;

/**
 * <h2>이미지 게시판 데이터를 저장하는 객체</h2>
 * 
 * 번호, 제목, 내용, 작성자 ID, 작성자 이름, 작성일, 파일명, 내 좋아요, 좋아요 개수<p>
 * 데이터를 가져가는 getter()<br>
 * 데이터를 저장하는 setter()<br>
 * 
 * 데이터 내용을 확인하는 toString()<br>
 * 
 * @author EZEN
 *
 */

public class ImageVO {

	private long no;
	private String title, content, id, name, writeDate, fileName, myLiked;
	private long likeCnt;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
	public String getMyLiked() {
		return myLiked;
	}
	public void setMyLiked(String myLiked) {
		this.myLiked = myLiked;
	}
	public long getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(long likeCnt) {
		this.likeCnt = likeCnt;
	}
	
	@Override
	public String toString() {
		return "ImageVO [no=" + no + ", title=" + title + ", content=" + content + ", id=" + id + ", name=" + name
				+ ", writeDate=" + writeDate + ", fileName=" + fileName + ", myLiked=" + myLiked + ", likeCnt="
				+ likeCnt + "]";
	}

}

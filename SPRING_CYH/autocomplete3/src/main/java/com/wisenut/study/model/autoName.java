package com.wisenut.study.model;

public class autoName {
	
	private String nmSeq;
	private String enNm;
	private String useYn;
	
	public String getNmSeq() {
		return nmSeq;
	}
	
	public void setNmSeq(String nmSeq) {
		this.nmSeq = nmSeq;
	}
	public String getEnNm() {
		return enNm;
	}
	public void setEnNm(String enNm) {
		this.enNm = enNm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	@Override
	public String toString() {
		return "Common [nmSeq=" + nmSeq + ", enNm=" + enNm + ", useYn=" + useYn + "]";
	}
	
	

}

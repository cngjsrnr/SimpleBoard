package com.sb.dto;

public class BoardDto {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bauthor;
	private String bauthorid;	
	private String bdate;
	
	public BoardDto() {}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBauthor() {
		return bauthor;
	}

	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public String getBauthorid() {
		return bauthorid;
	}

	public void setBauthorid(String bauthorid) {
		this.bauthorid = bauthorid;
	}

	@Override
	public String toString() {
		return "BoardDto [bno=" + bno + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bauthor=" + bauthor
				+ ", bdate=" + bdate + "]";
	}
	
}

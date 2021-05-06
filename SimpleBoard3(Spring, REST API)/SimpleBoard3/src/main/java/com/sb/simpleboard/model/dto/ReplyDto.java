package com.sb.simpleboard.model.dto;

public class ReplyDto {

	private int rno;
	private int bno;
	private String uid;
	private String uname;	
	private String rcontent;
	private String rdate;

	public ReplyDto() {
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getRcontent() {
		return rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "ReplyDto [rno=" + rno + ", bno=" + bno + ", uid=" + uid + ", uname=" + uname + ", rcontent=" + rcontent
				+ ", rdate=" + rdate + "]";
	}
	
}

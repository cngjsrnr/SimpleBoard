package com.sb.util;

public class PageNavigation {

	private boolean startRange; // 이전 버튼 관리.
	private boolean endRange; // 다음 버튼 관리.
	private int totalCount; // 총 글의 갯수.
	private int newCount;
	private int totalPageCount; // 총 페이지수.
	private int currentPage; // 현재 페이지 번호.
	private int naviSize; // 네비게이션 사이즈.
	private int countPerPage;
	private String navigator;

	public boolean isStartRange() {
		return startRange;
	}

	public void setStartRange(boolean startRange) {
		this.startRange = startRange;
	}

	public boolean isEndRange() {
		return endRange;
	}

	public void setEndRange(boolean endRange) {
		this.endRange = endRange;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getNewCount() {
		return newCount;
	}

	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNaviSize() {
		return naviSize;
	}

	public void setNaviSize(int naviSize) {
		this.naviSize = naviSize;
	}

	public String getNavigator() {
		return navigator;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	
//	public void makeNavigator() {
//		int startPage=(currentPage -1)/naviSize*naviSize+1;//나누어 떨어지는 애때문에 처음에 -1 해줌
//		int endPage = startPage+naviSize-1;//만약 마지막 페이지가 38까지이면 이렇게 하면 40이 나옴
//		if(endPage > totalPageCount) {//그래서 마지막페이지보다 더 크면 마지막 페이지로 바꿔줘
//			endPage=totalPageCount;
//		}
//		//w3c스쿨에서 부트스트랩의 paginatoin 복사해 오신거래
//		StringBuilder buffer = new StringBuilder();
//		buffer.append("		<ul class=\"pagination justify-content-center\"> \n");
//		buffer.append("			<li class=\"page-item\"> \n");
//		buffer.append("				<a href=\"javascript:firstPageMove();\" class=\"page-link\">최신목록</a> \n");
//		buffer.append("			</li> \n");
//		buffer.append("			<li class=\"page-item\"> \n");
//		buffer.append("				<a href=\"javascript:pageMove(" + (this.startRange? 1 : startPage-1) + ");\" class=\"page-link\">이전</a> \n");
//		buffer.append("			</li> \n");
//		for(int i=startPage;i<=endPage;i++) {
//			buffer.append("			<li class=\"" + (currentPage == i ? "page-item active" : "page-item") + "\"><a href=\"javascript:pageMove(" + i + ");\" class=\"page-link\">" + i + "</a></li> \n");
//		}
//		buffer.append("			<li class=\"page-item\"> \n");
//		buffer.append("				<a href=\"javascript:pageMove(" + (this.endRange? totalPageCount: endPage+1) + ");\" class=\"page-link\">다음</a> \n");
//		buffer.append("			</li> \n");
//		buffer.append("			<li class=\"page-item\"> \n");
//		buffer.append("				<a href=\"javascript:pageMove(" +totalPageCount  + ");\" class=\"page-link\">끝목록</a> \n");
//		buffer.append("			</li> \n");
//		buffer.append("		</ul> \n");
//		this.navigator = buffer.toString();
//	}

}

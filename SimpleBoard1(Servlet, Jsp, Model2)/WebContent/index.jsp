<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Simple Board</title>
</head>
<body>
<%@ include file="/include/header.jsp" %>
<div id="main">
<div class="col-md-8">
	<h2>게시글 목록</h2> 
	
	<form id="pageform">
		<input type="hidden" name="act" id="act" value="">
		<input type="hidden" name="pg" id="pg" value="">
		<input type="hidden" name="key" id="key" value="${key}">
		<input type="hidden" name="word" id="word" value="${word}">
		<input type="hidden" name="articleno" id="articleno" value="">
	</form>
	<c:if test="${user!=null}">
		<div class="d-flex flex-row-reverse">
		<button type="button" class="btn btn-link" onclick="javascript:movewrite();">글쓰기</button>
		</div>
	</c:if>

	<form id="searchform" method="get" class="form-inline" action="">
	<input type="hidden" name="act" id="act" value="getboardlist">
	<table class="table table-borderless">
		<tr>
			<td align="right">
	 	  <select class="form-control" name="key" id="skey">
	 	  	<option class="opt" value="" selected="selected">선택</option>
	    <option class="opt" value="bauthor">아이디</option>
	    <option class="opt" value="bno">글번호</option>
	    <option class="opt" value="btitle">제목</option>
	  </select>
	  <input type="text" class="form-control" placeholder="검색어 입력." name="word" id="sword" value="${word}">
	  <button type="button" class="btn btn-primary" onclick="javascript:searchArticle();">검색</button>
	</td>
		</tr>
	</table>
	</form>
	<c:if test="${boards.size() != 0}">
		
		<table class="table">
			  <tr>
			  	<td>글번호</td>
			  	<td>제목</td>
			  	<td>작성자</td>
			  	<td>작성일</td>
			  </tr>
			  <c:forEach var="article" items="${boards}">
		     <tr onclick="javascript:articleinfo(${article.bno});" style="cursor:pointer">
		     	<td>${article.bno}</td>
		     	<td><strong>${article.btitle}</strong></td>
		       <td>${article.bauthor}</td>				        
		       <td>${article.bdate}</td>
		     </tr>			   
		    </c:forEach>
		</table>
	
	
		<fmt:parseNumber var="startPage" integerOnly="true" value="${(navigator.currentPage-1)/navigator.naviSize}"/>
		<c:set var="startPage" value="${startPage*navigator.naviSize+1}"/>
		<c:set var="endPage" value="${startPage + navigator.naviSize - 1 }"></c:set>
		<c:if test="${endPage > navigator.totalPageCount}">
			<c:set var="endPage" value="${navigator.totalPageCount}"></c:set>
		</c:if>
		

		<ul class="pagination justify-content-center">
			<li class="page-item">
				<a href="javascript:firstPageMove();" class="page-link">최신목록</a>
			</li>
			<li class="page-item">
				<a href="javascript:pageMove(getPrePage(${navigator.startRange}));" class="page-link">이전</a>
			</li>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
				<li class="getPagenationClass(${currentPage},${i})"><a href="javascript:pageMove(${i});" class="page-link">${i}</a></li>
			</c:forEach>
			<li class="page-item">
				<a href="javascript:pageMove(getNextPage(${navigator.endRange}));" class="page-link">다음</a>
			</li>
			<li class="page-item">
				<a href="javascript:pageMove(${navigator.totalPageCount});" class="page-link">끝목록</a>
				</li>
		</ul>
		<%-- ${navigator.navigator} //jstl없이 서버쪽에서 html다 만들어서 올거면 이렇게(makeNavigator주석풀면됨)--%>
	</c:if>
	<c:if test="${boards.size() == 0}">
		<table class="table table-active">
		  <tbody>
		    <tr class="table-info" align="center">
		      <td>작성된 글이 없습니다.</td>
		    </tr>
		  </tbody>
		</table>
	</c:if>	
</div>
</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		var key = "<c:out value='${key}'/>";
	    $.each($('.opt'),function(index, item){
	        if(item.value == key) $(item).attr("selected", "selected");
	        
	    })
	});
	
	function movewrite() {
		location.href="${root}/main?act=mvarticlewrite";
	}
	function searchArticle() {
		if(document.getElementById("sword").value == "") {
			alert("검색어칸을 비울시 모든 목록이 조회됩니다");
		} else {
			if(document.getElementById("skey").value == "") {
				alert("검색 조건을 선택해 주세요");
				return;
			}
		}
		$("#searchform").action = "${root}/main";
		$("#searchform").submit();
	}
	function firstPageMove() {
		$("#act").val("getboardlist");
		$("#pg").val(1);
		$("#key").val("");
		$("#word").val("");			
		$("#pageform").action = "${root}/main";
		$("#pageform").submit();
	}
	function pageMove(pg) {
		console.log(pg);
		$("#act").val("getboardlist");
		$("#pg").val(pg);
		$("#pageform").action = "${root}/main";
		$("#pageform").submit();
	}
	function getPrePage(startRange){
		return startRange? 1 : "${startPage-1}";
	}
	function getNextPage(endRange){
		return endRange? "${navigator.totalPageCount}": "${endPage+1}";
	}
	
	function getPagenationClass(currentPage,i){
		return (currentPage == i ? "page-item active" : "page-item");
	}
	
	function articleinfo(no){
		//lot no=event.target.parentElement.getAttribute("bno");
		location.href="${root}/main?act=articleinfo&bno="+no;
	}
</script>
</html>
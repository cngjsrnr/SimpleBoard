<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Simple Board</title>
<script type="text/javascript">
	function articleinfo(){
		lot no=event.target.parentElement.getAttribute("bno");
		location.href="${pageContext.request.contextPath}/main?act=articleinfo&isbn="+no;
	}
</script>
</head>
<body>
<%@ include file="/include/header.jsp" %>
<div id="main">
<div class="col-md-10">
<h2>게시글 목록</h2>
	<div>
	<table>
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>				
			</tr>
		</thead>
		<tbody>
		<c:forEach var="article" items="${boards}">
			<tr bno="${article.bno} onclick="articleinfo()">
				<td>${article.bno}</td>
				<td>${article.btitle}</td>
				<td>${article.bauthor}</td>
				<td>${article.bdate}</td>				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</div>
</div>
</body>
</html>
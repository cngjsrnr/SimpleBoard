<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Simple Board</title>
<script type="text/javascript">
	function boardinfo(){
		lot no=event.target.parentElement.getAttribute("bno");
		location.href="${pageContext.request.contextPath}/main?act=boardinfo&isbn="+no;
	}
</script>
</head>
<body>
<%@ include file="/include/header.jsp" %>
<h2>게시글 목록</h2>
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
		<c:forEach var="board" items="${boards}">
			<tr bno="${board.bno} onclick="bookinfo()">
				<td>${board.bno}</td>
				<td>${board.btitle}</td>
				<td>${board.bauthor}</td>
				<td>${board.bdate}</td>				
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
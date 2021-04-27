<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<c:set var="root" value="${pageContext.request.contextPath}" />
<style>
#title {
	text-align: center;
}

#loginInfo {
	text-align: right;
}

#menu>li {
	display: inline-block;
	width: 150px;
}
.error{
	color: red;
}
table{
	width: 100%;
	border-collapse: collapse;
}
table td, table th{
	border:1px solid black;
}
#main{
	width:70%;
	margin: auto;
}
</style>

<h1 id="title">Simple board</h1>
<div id="loginInfo">
	<c:if test="${user==null}">
		<form method="post" action="${root }/main?act=login">
			<input type="hidden" name="act" value="login">
			<input type="text" name="id" placeholder="아이디">
			<input type="password" name="pass" placeholder="비밀번호">
			<input type="submit" value="login">
		</form>
	</c:if>
	<c:if test="${user!=null}">
		<a href="${root}/main?act=logout">로그아웃</a>
	
	</c:if>

</div>
<!-- 
<ul id="menu">
	<li><a href="${root }/main?act=booklist">도서 목록</a></li>
	<li><a href="${root }/main?act=registForm">도서 등록</a></li>
</ul>
 -->
<hr>
<script>
	// request.setAttribute("msg", "id 또는 pass를 확인하세요") 형태로 attribute를 넘겨주면 alert을 확인할 수 있다.
	let msg = "${msg}";
	if (msg!="") {
		alert(msg);
		<% request.getSession().removeAttribute("msg");%>
	}
</script>


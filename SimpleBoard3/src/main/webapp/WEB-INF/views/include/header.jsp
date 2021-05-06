<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
#main{
	display: flex;
	flex-direction: column;
	align-items: center;
}
</style>

<h1 id="title" onclick="mvmain()" style="cursor:pointer">Simple board</h1>
<div id="loginInfo">
	<c:if test="${user==null}">
		<form method="post" action="${root }/user/login" style="display: inline-block;">
			<input type="text" name="uid" placeholder="아이디">
			<input type="password" name="upass" placeholder="비밀번호">
			<input class="btn btn-primary" type="submit" value="login">
		</form>
		<button class="btn btn-light" onclick="regist()">회원가입</button>
		
	</c:if>
	<c:if test="${user!=null}">
		<a href="${root}/user/mvmodify?id=${user.uid}">회원정보수정</a>
		<a href="${root}/user/logout">로그아웃</a>
	
	</c:if>

</div>
<hr>
<script>
	let msg = "${msg}";
	if (msg!="") {
		alert(msg);
		<% request.getSession().removeAttribute("msg");%>
		msg="";
	}
	function regist(){
		location.href="${root}/user/mvregist";
	}
	function mvmain(){
		location.href="${root}/main";
	}
</script>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
<%@ include file="/include/header.jsp" %>
<div id="main" >
	<div style="width:400px">
	<h1>회원정보수정</h1>
	<form id="regist-form" method="Post" action="${root}/main" >
	  <input type="hidden" name="act" value="usermodify">
	  <div class="form-group">
	    <label for="id">아이디:</label>
	    <input type="email" class="form-control" id="id" name="id" readonly="readonly" value="${user.uid}">
	  </div>
	  <div class="form-group">
	    <label for="password">비밀번호:</label>
	    <input type="password" class="form-control" id="password" name="password" value="${user.upass}">
	  </div>
	    <div class="form-group">
	    <label for="name">이름:</label>
	    <input type="text" class="form-control" id="name" name="name" value="${user.uname}">
	  </div>
	  <button type="button" id="regist-btn" class="btn btn-default">회원정보수정</button>
	</form>
	</div>
</div>
</body>
<script type="text/javascript">
$("#regist-btn").on("click",function(){
	if($("#password").val()=="" || $("#id").val()=="" || $("#name").val()=="")
		alert("회원정보를 모두 입력해 주세요");
	else
		$("#regist-form").submit();	
})
</script>
</html>
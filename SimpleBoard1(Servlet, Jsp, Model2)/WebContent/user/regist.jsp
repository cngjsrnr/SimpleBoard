<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<%@ include file="/include/header.jsp" %>
<div id="main" >
	<div style="width:400px">
	<h1>회원가입</h1>
	<form id="regist-form" method="Post" action="${root}/main" >
	  <input type="hidden" name="act" value="userregist">
	  <div class="form-group">
	    <label for="id">아이디:</label>
	    <input type="email" class="form-control" id="id" name="id">
	    <span id="iderrmsg" class="error"></span>
	  </div>
	  <div class="form-group">
	    <label for="password">비밀번호:</label>
	    <input type="password" class="form-control" id="password" name="password">
	  </div>
	    <div class="form-group">
	    <label for="name">이름:</label>
	    <input type="text" class="form-control" id="name" name="name">
	  </div>
	  <button type="button" id="regist-btn" class="btn btn-default">회원가입</button>
	</form>
	</div>
</div>
</body>
<script type="text/javascript">
$("#id").focusout(function(e) {
	$.ajax({
		url:"${root}/main?act=isexistid&id="+$("#id").val(),
		type:"GET",
		dataType:"html",
		success: function(data){
			if(data=="f"){
				$("#iderrmsg").text("이미 존재하는 아이디 입니다");
			}else
				$("#iderrmsg").text("");
		},
		error:function(status,a,e){
			console.log(status);
			console.log(a);
			console.log(e);
			
		}
	});
});
$("#regist-btn").on("click",function(){
	if($("#iderrmsg").text()!=""){
		alert("이미 존재하는 아이디 입니다");
	}else if($("#password").val()=="" || $("#id").val()=="" || $("#name").val()=="")
		alert("회원정보를 모두 입력해 주세요");
	else
		$("#regist-form").submit();	
})
</script>
</html>
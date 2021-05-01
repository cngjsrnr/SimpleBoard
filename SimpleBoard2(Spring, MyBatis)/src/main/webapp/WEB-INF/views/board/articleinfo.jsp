<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>게시글 보기</title>
</head>
<body>
<%@ include file="../include/header.jsp" %>
<div id="main">

		
		
	<div align="center" style="width:100%;">		
		<div class="d-flex" style="width:70%;">
			<div class="" style="width:50%;">
				<div>제목:</div>
				<div class="border">${article.btitle}</div>
			</div>
			<div class="" style="width:20%;">
				<div >작성자:</div>
				<div class="border">${article.bauthor}</div>
			</div>
			<div class="" style="width:30%;">
				<div>작성일:</div>
				<div class="border">${article.bdate}</div>
			</div>
		</div>
		
		<div class="" style="width:70%" >
			<div align="left">내용:</div>
			<div class="border w-100 p-2" style="width:70%;height:500px;">${article.bcontent}</div>
		</div>
		<button type="button" class="btn btn-primary mt-2" onclick="javascript:mvboardList();">글목록</button>
		<c:if test="${user.uname == article.bauthor}">
			<button type="button" class="btn btn-warning mt-2" onclick="javascript:modifyArticle(${article.bno});">글수정</button>
			<button type="button" class="btn btn-danger mt-2" onclick="javascript:deleteArticle(${article.bno});">글삭제</button>
		</c:if>
	</div>
			



</div>
</body>
<script type="text/javascript">		
	function mvboardList() {
		location.href="${root}/main";
		return;
	}
	function modifyArticle(bno){
		location.href="${root}/board/mvmodify?bno="+bno;
		return;
	}
	function deleteArticle(bno){
		location.href="${root}/board/delete?bno="+bno;
		return;
	}
</script>
</html>
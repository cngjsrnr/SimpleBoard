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
<title>게시글 작성</title>
</head>
<body>
<%@ include file="/include/header.jsp" %>
<div id="main">
<div class="col-md-8">
	<form action="${root}/main" method="post">
	  <input type="hidden" name="act" value="articlewrite">
	  <div class="form-group">
	    <label for="title">제목:</label>
	    <input type="text" class="form-control text-left" id="title" name="title">
	  </div>
	  <div class="form-group">
	  <label for="content">내용:</label>
	  <textarea class="form-control" rows="15" style="resize:none" id="content" name="content"></textarea>
	  </div>
	  <button type="submit" class="btn btn-primary float-right">글작성</button>
	</form>
</div>
</div>
</body>
</html>
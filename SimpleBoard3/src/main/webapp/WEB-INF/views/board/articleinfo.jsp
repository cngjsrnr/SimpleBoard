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
<script type="text/javascript">
	let root="${pageContext.request.contextPath}";
	let ismodify=0;
	let pretext="";
	//댓글목록
	function getlist(){
		let bno="${article.bno}";
		
		$.ajax({
			url:root+'/reply/'+bno,  
			type:'GET',
			dataType:'json',//넘어오는 data타입
			success:function(replys) {
				$("#reply-list").empty();
				let str="";
				$(replys).each(function(index, reply){
					str+="<div class='reply' rno='"+reply.rno+"'><div> 작성자: ";
					str+= reply.uname+"</div>";
					str+= "<div>"+ reply.rcontent+"</div>";
					
					str+= "<div>"+reply.rdate+"</div>";
					if(reply.uid== "${user.uid}")//작성자 일경우 
						str+= "<div><button class='reply-modify-btn btn btn-primary'>수정</button> <button class='reply-remove-btn btn btn-danger'>삭제</button></div>";
					str+= "</div><hr>";
				});
				$("#reply-list").append(str);
			},
			error:function(xhr,status,msg){
				console.log("상태값 : " + status + " Http에러메시지 : "+msg);
			},
			statusCode:{//옛날에 프로젝트 할때 햇던것처럼 무조건 success에서 받게한뒤 msg같은거 하나 보내서 에러 처리해 줘도됨
				404: function(){
					alert("댓글을 불러오지 못했습니다");
				},
				500: function(){
					alert("댓글을 불러오지 못했습니다");
				}
			}
		});		
	}
	$(document).ready(function() {
		//댓글 목록		
		getlist();
		
		//댓글 작성
		$("#reply-write-btn").click(function(){
			let nowuser="";
			nowuser="${user.uid}"
			let data = JSON.stringify({
				"bno" : "${article.bno}", 
				"uid" : nowuser, 
				"rcontent" : $("#reply-input").val(), 
			   });
			$.ajax({
				url:root+'/reply/',  
				type:'POST',
				contentType:'application/json;charset=utf-8',
				data: data,
				success:function(replys) {
					getlist();
					$("#reply-input").val("");
				},
				error:function(xhr,status,msg){
					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
				},
				statusCode:{//옛날에 프로젝트 할때 햇던것처럼 무조건 success에서 받게한뒤 msg같은거 하나 보내서 에러 처리해 줘도됨
					400: function(){
						alert("잘못된 데이터입니다");
					},
					404: function(){
						alert("댓글을 불러오지 못했습니다");
					},
					500: function(){
						alert("댓글을 불러오지 못했습니다");
					}
				}
			});				
		})
		
		//댓글 수정
		$(document).on("click",".reply-modify-btn",function(){
			console.log(ismodify);
			if(ismodify!=0){
				alert("수정중인 댓글창을 닫아주세요");
				return;
			}
			pretext=event.target.parentNode.parentNode.childNodes[1].innerText;
			event.target.parentNode.parentNode.childNodes[1].innerHTML="<input type='text' value='"+ pretext+"' id='reply-modify-input'>"
			+"<button id='reply-modify-submit-btn' class='btn btn-primary'>수정</button><button id='reply-modify-cancel-btn' class='btn btn-warning'>취소</button>";
			event.target.parentNode.parentNode.childNodes[3].innerHTML=""
			ismodify=1;
		});
		//댓글 수정 취소
		$(document).on("click","#reply-modify-cancel-btn",function(){
			event.target.parentNode.parentNode.childNodes[3].innerHTML="<button class='reply-modify-btn btn-primary btn'>수정</button> <button class='reply-remove-btn btn-danger btn'>삭제</button>";
			event.target.parentNode.parentNode.childNodes[1].innerHTML=""+pretext+"";
			ismodify=0;
		})
		//댓글 수정 완료
		$(document).on("click","#reply-modify-submit-btn",function(){
			let rno=event.target.parentNode.parentNode.getAttribute("rno");
			let data = JSON.stringify({
				"rno" : rno, 
				"rcontent" : $("#reply-modify-input").val(), 
			   });
			$.ajax({
				url:root+'/reply/',  
				type:'PUT',
				contentType:'application/json;charset=utf-8',
				data: data,
				success:function(replys) {
					getlist();
				},
				error:function(xhr,status,msg){
					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
				},
				statusCode:{//옛날에 프로젝트 할때 햇던것처럼 무조건 success에서 받게한뒤 msg같은거 하나 보내서 에러 처리해 줘도됨
					400: function(){
						alert("잘못된 데이터입니다");
					},
					404: function(){
						alert("댓글을 불러오지 못했습니다");
					},
					500: function(){
						alert("댓글을 불러오지 못했습니다");
					}
				}
			});				
		});
		
		//댓글 삭제
		$(document).on("click",".reply-remove-btn",function(){
			let rno=event.target.parentNode.parentNode.getAttribute("rno");
			$.ajax({
				url:root+'/reply/'+rno,  
				type:'DELETE',
				success:function() {
					getlist();
				},
				error:function(xhr,status,msg){
					console.log("상태값 : " + status + " Http에러메시지 : "+msg);
				},
				statusCode:{//옛날에 프로젝트 할때 햇던것처럼 무조건 success에서 받게한뒤 msg같은거 하나 보내서 에러 처리해 줘도됨
					404: function(){
						alert("댓글을 불러오지 못했습니다");
					},
					500: function(){
						alert("댓글을 불러오지 못했습니다");
					}
				}
			});	
		})
	});
</script>
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
		
		<br><br><br>
		<div><b>댓글</b></div>
		<hr>
		<div style="width:70%" id="reply-list">

		</div>
		<c:if test="${user !=null}">
			<div style="width:70%" id="reply-write">
				<textarea id="reply-input" style="width:100%; resize: none;" ></textarea><br>
				<button id="reply-write-btn" class='btn btn-primary'>작성</button>
			</div>
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
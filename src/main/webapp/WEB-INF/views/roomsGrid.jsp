<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>rooms</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>채팅방</h1>
		<div class="input-group mb-3">
			<input type="text" class="form-control"
				placeholder="Recipient's username" aria-label="Recipient's username"
				aria-describedby="button-addon2">
			<div class="input-group-append">
				<button class="btn btn-outline-secondary" type="button"
					id="button-addon2">Search</button>
			</div>
		</div>
		<div class="row row-cols-3" id="rooms">
		</div>

		<button type="button" class="btn btn-primary"
			onclick="roomCreateForm()">방만들기</button>
	</div>
	<script src="<c:url value="/resources/js/jquery.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script type="text/javascript">
		function roomCreateForm(){
			location.href = "roomCreateForm";
		}
		function getRooms(){
			$.ajax({
				url : 'rooms',
				method : 'POST',
				dataType : 'json',
				contentType: "application/json; charset=UTF-8",
				success:(data)=>{
					
					roomsHtml = '';
					for(i = 0; i < Object.keys(data).length; i++){
						
						roomsHtml += '<div class="col border"><div>'+data[i].name+'</div><div>'+data[i].memberCnt+'/'+data[i].capacity+'</div><span><button type="button" class="btn btn-primary" onclick="enterRoom('+data[i].id+')">Enter</button></span><span>'+data[i].host+'</span></div>';
						
					}
					$('#rooms').html(roomsHtml);
					
				},
				error:(err)=>{
					alert("fail : "+ JSON.stringify(err));
				}
			});
		}
		function enterRoom(id){
			location.href = "room?id=" + id;
		}
		$(function (){
			getRooms();
		});
	</script>
</body>
</html>
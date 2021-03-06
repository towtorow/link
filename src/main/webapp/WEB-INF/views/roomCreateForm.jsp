<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>create room</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>채팅방 만들기</h1>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">이름</span>
			</div>
			<input type="text" class="form-control" placeholder="RoomName"
				aria-label="Username" aria-describedby="basic-addon1" id="name">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">비밀번호</span>
			</div>
			<input type="text" class="form-control" placeholder="Password"
				aria-label="Username" aria-describedby="basic-addon1" id="pw">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="basic-addon1">인원수</span>
			</div>
			<input type="text" class="form-control" placeholder="UserCapacity"
				aria-label="Username" aria-describedby="basic-addon1" id="capacity">
		</div>
		<button type="button" class="btn btn-primary" onclick="createRoom()">방만들기</button>
		<button type="button" class="btn btn-primary" onclick="cancel()">취소</button>
	</div>
	<script src="<c:url value="/resources/js/jquery.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script type="text/javascript">
		function createRoom() {
			$.ajax({
				url : 'room/create',
				method : 'POST',
				data : JSON.stringify({
					name : $('#name').val(),
					pw : $('#pw').val(),
					capacity : $('#capacity').val(),
					host : sessionStorage.getItem("id")
				}),
				dataType : 'json',
				contentType: "application/json; charset=UTF-8",
				success:(data)=>{
					alert("success : "+JSON.stringify(data));
					location.href = "roomsGrid";
				},
				error:(err)=>{
					alert("fail : "+ JSON.stringify(err));
				}
			});
		}
		
		function cancel() {
			location.href = "roomsGrid";
		}

	</script>
</body>
</html>
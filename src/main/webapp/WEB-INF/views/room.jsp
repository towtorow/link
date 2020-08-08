<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>room</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
</head>
<body>
	<div class="container">
	<div id="name"></div>
		<div class="form-group">
			<textarea class="form-control" id="data"
				rows="15"></textarea>

		</div>
		<div class="input-group mb-3">
			<input type="text" class="form-control"
				aria-label="Recipient's username" aria-describedby="button-addon2" id="message">
			<div class="input-group-append">
				<button class="btn btn-outline-secondary" type="button"
					id="sendBtn">입력</button>
			</div>
		</div>
		<button type="button" class="btn btn-primary" onclick="roomModifyForm()">방 수정</button>
		<button type="button" class="btn btn-primary"onclick="deleteRoom()">방 삭제</button>
	</div>
	<script src="<c:url value="/resources/js/jquery.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script src="<c:url value="/resources/js/sockjs.js" />"></script>
	<script type="text/javascript">
		$(function (){
			$.ajax({
				url : 'rooms/'+${id},
				method : 'POST',
				dataType : 'json',
				contentType: "application/json; charset=UTF-8",
				success:(data)=>{
					$('#name').html('<h1>'+data.name+'</h1><div>'+data.host+'</div>');
				},
				error:(err)=>{
					alert("fail : "+ JSON.stringify(err));
				}
			});
			
			$("#sendBtn").click(function() {
	            sendMessage();
	            $('#message').val('')
	        });

	        $("#message").keydown(function(key) {
	        	if (key.keyCode == 13) {// 엔터
      	          	sendMessage();
	        		$('#message').val('');
	           	}
	        });

	        var sock = new SockJS('/link/echo?${id}');
	        
	        sock.onopen = function () {
	            sock.send(JSON.stringify({roomId: ${id}, type: 1, writer: sessionStorage.getItem("id")}));
	            sock.onmessage = onMessage
	        }
	        
	        sock.onclose = onClose;
	        
	        function sendMessage() {
	        	sock.send(JSON.stringify({roomId: ${id}, type: 0, writer: sessionStorage.getItem("id"), message: $("#message").val()}));
	        }

	        function onMessage(msg) {
	        	var content = JSON.parse(msg.data);
	        	$("#data").append('(' + content.writer +') '+content.message+'\n');
	        }
	        function onClose(evt) {
	           $("#data").append("연결 끊김");
	        }
			
		});
		
		function roomModifyForm(){
			location.href = "roomModifyForm?id=" + ${id};
		}
		
		function deleteRoom(){
			$.ajax({
				url : 'room/delete',
				method : 'POST',
				data : JSON.stringify({
					id : ${id},
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
	</script>
</body>
</html>

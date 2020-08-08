<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signin</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>Signin</h1>
		<form name="f">
			<div class="form-group">
				<label for="exampleInputEmail1">Email address</label> <input
					type="email" class="form-control" id="id"
					aria-describedby="emailHelp"> <small id="emailHelp"
					class="form-text text-muted">We'll never share your email
					with anyone else.</small>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Password</label> <input 
					type="password" class="form-control" id="pw">
			</div>
			<button type="button" class="btn btn-primary" onclick="signin()">Submit</button>
			<button type="button" class="btn btn-primary"onclick="loginForm()">Login</button>
			
		</form>
	</div>
	<script src="<c:url value="/resources/js/jquery.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script type="text/javascript">
	function signin() {

		$.ajax({
			url : 'signin',
			method : 'POST',
			data : JSON.stringify({
				id : $('#id').val(),
				pw : $('#pw').val()
			}),
			dataType : 'json',
			contentType: "application/json; charset=UTF-8",
			success:(data)=>{
				alert("success : "+JSON.stringify(data));
				location.href = "home";
			},
			error:(err)=>{
				alert("fail : "+ JSON.stringify(err));
			}
		});
	}
	
	function loginForm(){
		location.href = "home";
	}
	</script>
</body>
</html>
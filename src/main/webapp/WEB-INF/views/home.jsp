<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1>Login</h1>
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
			<button type="button" class="btn btn-primary" onclick="login()">Submit</button>
			<button type="button" class="btn btn-primary" onclick="signinForm()">Signin</button>
		</form>
	</div>
	<script src="<c:url value="/resources/js/jquery.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script type="text/javascript">
		function login() {

			$.ajax({
				url : 'login',
				method : 'POST',
				data : JSON.stringify({
					id : $('#id').val(),
					pw : $('#pw').val()
				}),
				dataType : 'json',
				contentType: "application/json; charset=UTF-8",
				success:(data)=>{
					alert("success : "+JSON.stringify(data));
					sessionStorage.setItem("id",data.id);
					location.href = "chat/roomsGrid"
				},
				error:(err)=>{
					alert("fail : "+ JSON.stringify(err));
				}
			});
		}
		
		function signinForm() {
			location.href = "signinForm";
		}
		
	</script>

</body>
</html>

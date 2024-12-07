<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up</title>
</head>
<body>
	<h1>Sign up</h1>
	<form action="Sign_up" method="post">
	
		<label for="fullname">Fullname:</label> <input type="text"
				id="fullname" name="fullname" required><br> 
		<label for="username">Email:</label> <input type="text"
				id="username" name="username" required><br> 
		<label for="username">Username:</label> <input type="text"
				id="username" name="username" required><br> 
		<label
			for="password">Password:</label> <input type="password" id="password"
			name="password" required><br> 
			<input type="submit"
			value="Sign up">
	</form>
	<p>
		Already have an account? <a href="sign_in">Sign in</a>
	</p>
</body>
</html>

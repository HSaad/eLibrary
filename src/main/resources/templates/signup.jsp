<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns:th="http://www.thymeleaf.org">
	<head>
		<meta charset="ISO-8859-1">
		<title>Sign Up for an E-Library Account</title>
		<link rel="stylesheet" href="../assets/css/bootstrap.css" th:href="@{/assets/css/bootstrap.css}" />
        <link rel="stylesheet" href="../assets/css/thymeleaf-demo.css" th:href="@{/assets/css/style.css}" />	
	</head>
	<body>
		<section class="signup-section">
			<h2>Sign Up</h2>
			
			<form method="get" action="signup" >
				<h3>Enter your details: </h3>
				<input placeholder=" First Name " type="text" name="firstname" id="firstname">
				<input placeholder=" Last Name " type="text" name="lastname" id="lastname">
				<input placeholder=" Email address " type="text" name="email" id="email">
				<input placeholder=" Username " type="text" name="username" id="username">
				
				<h3>Set a password: </h3>
				<p> Your password must be at least 8 characters.</p>
				<input placeholder=" Password " type="password" name="pw" id="pw">
				<input placeholder=" Password again " type="password" name="pw1" id="pw1">
				
				<p> By clicking "Sign up," you confirm that you're 13 or older and agree to <br>
				E-Library's Privacy Policy and Terms and Conditions </p>
				
				
				<input class="signup" type="submit" value="Sign Up">
			</form>
			
		</section>
		
		<!-- Footer: Company Links
		<footer>
			<a class="contact" href="contact.jsp"> Contact </a> 
			<a class="about" href="about.jsp">About Us</a>
		</footer> -->
	</body>
</html>
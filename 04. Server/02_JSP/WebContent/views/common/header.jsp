<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.Period"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../error/error.jsp" %>
    
<% 
	LocalDateTime now = LocalDateTime.now();
	String today = now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh시 mm분 ss초"));
	long days = ChronoUnit.DAYS.between(now.toLocalDate(), LocalDate.of(2022, 1, 1));
	
	// ▼ errorPage 확인을 위해 일부러 만든 에러
	// int result = 1 /0 ;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지시자 테스트</title>
</head>
<body>
	<header>
	<h1>지시자 테스트</h1>
		오늘은 <span style="color: tomato"><%= today %></span>입니다.<br>
		2022년 새해까지 <span style="color: tomato"><%= days %></span> 일 남았네..요.....
	</header>
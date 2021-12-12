<%@page import="com.kh.el.model.vo.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
	1. 기존 Scriptlet 을 이용하는 방식으로, request, session 객체에 담겨있는 데이터 출력
	   테스트를 위한 로컬 변수 설정
	   
	// ▼ request 객체에 저장된 속성 (Attribute) 을 가져옴
	String classRoom = (String)request.getAttribute("classRoom");
	Person student = (Person)request.getAttribute("student");
	
	// ▼ session 객체에 저장된 속성 (Attribute) 을 가져옴
	String academy = (String)session.getAttribute("academy");
	Person teacher = (Person)session.getAttribute("teacher");
	*/
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL</title>
</head>
<body>
	<h2>EL (Expression Language)</h2>
	
	<h3>1. 기존 Scriptlet 을 이용하는 방식으로, request, session 객체에 담겨있는 데이터 출력</h3>
	
	<%--
	학원명 : <%= academy %> <br>	
	강의장 : <%= classRoom %> <br>	
	강사 : <%= teacher.getName() %>, <%= teacher.getAge() %>, <%= teacher.getGender() %> <br>	
	수강생 : <%= student.getName() %>, <%= student.getAge() %>, <%= student.getGender() %> <br>	
	--%>
	
	<h3>2. EL을 이용하는 방식으로, request, session 객체에 담겨있는 데이터를 출력</h3>
	<!-- 
		1. EL 은 request, session 등 jsp 영역의 객체를 구분하지 않아도 (= 변수 설정을 안해도)
		   자동으로 입력된 속성명 (키값) 을 검색해서, 존재하는 경우 값을 가져옴
		   : page ▷ request ▷ session ▷ application 영역 순으로 해당 속성을 찾아서 값을 가져옴
	 
	 	2. EL 은 Scriptlet 과 다르게 getter를 사용하지 않고 필드명으로 직접 접근하는 것 같지만,
	 	   내부적으로 해당 객체의 getter 를 자동으로 할당하여 저장된 값을 읽어옴
	 	   ex1. 게터세터가 있는 Person 객체의 필드에 private 를 걸어도, 값을 읽어올 수 있음
	 	   ex2. Person 객체의 lombok 라이브러리 중 @Data 를 지우면, 
	 	        게터세터가 없어지고 값을 가져올 수 없음
	 	   ex3. Person 객체의 lombok 라이브러리 중 @Data 를 지우고, 
	 	        게터세터를 없어진 후 @Getter 를 추가하면 값을 가져올 수 있음!!
	 -->
	학원명 : ${ academy } <br>
	강의장 : ${ classRoom } <br>
	강사 : ${ teacher.name }, ${ teacher.age }, ${ teacher.gender }<br>
	수강생 : ${ student.name }, ${ student.age }, ${ student.gender }<br>


	<h3>3. EL 사용 시 영역의 객체에 저장된 속성명이 같은 경우</h3>
	<!-- ▼  page ▷ request ▷ session ▷ application 영역 순으로 해당 속성을 찾아서, 나오는대로 값을 가져옴
	        ▶ 자금은 page 영역에 속성이 없어서 가장 먼저 찾은 request 의 scope 의 속성값을 가져옴 -->
	scope : ${ scope } <br> 
	
	<%
		// ▼ 테스트를 위해 Page Scope에 데이터 저장
		pageContext.setAttribute("scope", "Page 영역");
	%>
	
	<!--  ▼ 여러 스코프에 동일한 속성을 저장했다면,
	        명시적으로 스코프를 지정해서 원하는 영역의 속성값을 읽어올 수 있음 -->
	pageScope.scope : ${ scope } 또는 ${ pageScope.scope }<br> 
	requestScope.scope : ${ requestScope.scope }<br>
	sessionScope.scope : ${ sessionScope.scope }<br>
	applicationScope.scope : ${ applicationScope.scope }<br>
	
	<h3>4. ContextPath 가져오기</h3>
	<h4>4-1) Scriptlet을 이용하는 방식</h4>
	ContextPath : <%= request.getContextPath() %>
	
	<h4>4-2) EL을 이용하는 방식</h4>
	<!-- ▼ pageContext 는 다른 내장 객체들을 가져올 수 있으므로,
	       request 에 접근해서 contextPath 를 가져오는 방식임 -->
	ContextPath : ${ pageContext.request.contextPath }
	
	<h3>5. 헤더에 접근하기</h3>
	<h4>5-1) Scriptlet을 이용하는 방식</h4>
	Host : <%= request.getHeader("host") %> <br>
	User-Agent : <%= request.getHeader("User-Agent") %> <br>
	
	<h4>5-2) EL을 이용하는 방식</h4>
	Host : ${ header.host } <br>
	User-Agent : ${ header['User-Agent'] } <br>
</body>
</html>
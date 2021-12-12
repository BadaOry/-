<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 액션태그 - include</title>
</head>
<body>
	<h2>jsp:include 액션 태그</h2>
	<p>
		: 현재 JSP 페이지에 다른 페이지를 포함하고자 할 때 쓰이는 액션 태그
	</p>
	
	<h3>1) include 지시어 (정적 include 방식)</h3>
	<p>
		: 다른 페이지를 포함하는 JSP 파일이 컴파일 되기 전에 페이지 삽입됨
	</p>
	
 	<%--
 	
	<!-- ▼ JSP 파일이 서블렛(java) 로 컴파일 되기 전에 소스로 삽입됨 --!>
	<%@ include file="includePage.jsp" %>
	
	<!-- ▼ include 한 페이지(includePage 파일)에서 
	       scriptlet으로 선언된 변수 (여기서는 year) 를 그대로 사용 가능 
	       ▷ include.jsp 가 컴파일 되기 전 includePage 의 내용이 포함되기 때문 -->
	include 한 페이지의 year 변수값 <%= year %> <br><br>
	
	<!-- ▼ 현재 페이지와 include 한 페이지(includePage 파일)의 변수명이 중복되면 
	       Duplicate Local Variable 에러 발생 
	       ▷ java 에서 동일한 이름의 변수 두 개를 만들 수 없는 이유와 같음 -->
	<%
		String year = "2022";
	%>
 	
 	--%>
 	

	<h3>2) include 액션 태그 (동적 include 방식)</h3>
	<p>
		: 다른 페이지를 포함하는 JSP 파일이 화면에 출력되는 시점(런타임)에 삽입됨
	</p>
	
	<%-- ▼ include.jsp 파일과 includePage.jsp 파일이 각각 서블렛(java) 로 컴파일 된 후
		   ▷ include.jps 파일에서 위에서부터 쭉 내려오면서 화면을 구성하다가
		   ▷ include.jsp 파일 안의 jsp:include 액션 태그를 만나면
		   ▷ includePage.jsp 파일로 넘어가서 위에서부터 아래로 쭉 내려오며 화면을 구성하고
		   ▷ 다시 돌아와서 jsp:include 액션 태그 다음의 내용을 실행할것임
	       ▶ 이게 "런타임시 합쳐지면서 삽입됨" 의 의미 --%> 
	<jsp:include page="includePage.jsp" />
	
	
	<%
		// ▼ 중복되는 변수명으로 선언해도 에러가 발생하지 않음
		String year = "2022";
	%>
	<span style="color:gray">▼ includePage.jsp 에서 include.jsp로 다시 돌아와서 읽는 변수값</span><br>
	 include.jsp의 year 변수값 : <%= year %> <br><br>
	
	<!-- ▼ jsp:param 액션 태그를 이용해서 
	       포함되는 페이지(includePage.jsp) 로 값을 전달할 수 있음 
	       ▷ 단, jsp:include 와 jsp:forward 의 하위 태그로만 사용 가능 -->
	<jsp:include page="includePage.jsp">
		<jsp:param name="pName" value="아이폰 12 미니"/>
	</jsp:include>
	
</body>
</html>
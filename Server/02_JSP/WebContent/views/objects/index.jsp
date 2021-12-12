<%@page import="java.util.Date"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 내장 객체</title>
</head>
<body>
	<h2>JSP 내장 객체</h2>
	
	<h3>request 객체</h3>

	<h4>헤더와 관련된 메소드</h4>
	<table border="1">
		<tr>
			<th>헤더 이름</th>
			<th>헤더 값</th>
		</tr>
		
	<%
		// ▼ request 에서 header 의 정보들을 가져오는 코드들
		//   ▷ header 의 이름을 알면 getHeader()로 정보를 가져올 수 있지만
		//     지금 우리는 아는 헤더 이름이 없으므로
		//   ▷ hasMoreElements 메소드로 다음 다음 다음 정보들을 가져옴
		Enumeration<String> headerNames = request.getHeaderNames();
		
		while(headerNames.hasMoreElements()) {
			String headerName  = headerNames.nextElement();
	%>	
		<tr>
			<td><%= headerName %></td>
			<td><%= request.getHeader(headerName) %></td>
		</tr>
	<%
			// System.out.println(headerName + " " + request.getHeader(headerName));
		}
	%>
	</table>	
	
	<h3>URL / URI, 요청 방식과 관련된 메소드</h3>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>값</th>
		</tr>
		<tr>
			<td>서버 도메인 명</td>
			<td><%= request.getServerName() %></td>
		</tr>
		<tr>
			<td>서버 포트 번호</td>
			<td><%= request.getServerPort() %></td>
		</tr>
		<tr>
			<td>서버 URL</td>
			<td><%= request.getRequestURL() %></td>
		</tr>
		<tr>
			<td>서버 URI</td>
			<td><%= request.getRequestURI() %></td>
		</tr>
		<tr>
			<td>요청 쿼리</td>
			<td><%= request.getQueryString() %></td>
		</tr>
		<tr>
			<td>클라이언트의 IP 주소</td>
			<td><%= request.getRemoteAddr() %></td>
		</tr>
		<tr>
			<td>프로토콜</td>
			<td><%= request.getProtocol() %></td>
		</tr>
		<tr>
			<td>요청 방식</td>
			<td><%= request.getMethod() %></td>
		</tr>
		<tr>
			<td>웹 애플리케이션 경로</td>
			<td><%= request.getContextPath() %></td>
		</tr>
	</table>
	
	
	<h3>response 객체</h3>
	<p>
		sendRedirect(String url)<br>
		: 지정한 URL로 요청을 재전송 (▷ 브라우저에 표시되는 URL이 변경됨) <br>
		  브라우저에 표시되는 주소가 바뀌는 방식을 리다이렉트 방식이라고 부름 <br>
		  리다이렉트 방식은 이동할 페이지로 요청과 응답 객체를 새로 생성하여 전송하므로,
		   요청과 응답 정보가 유지되지 않음
	</p>
	<a href="redirect.jsp">redirect 테스트</a>
	
	<h3>pageContext 객체</h3>
	<p>
		forward(String url)<br>
		: 지정한 URL로 현재 페이지의 요청과 응답에 관한 제어권을 영구적으로 넘김<br>
		  브라우저에 표시되는 주소가 바뀌지 않는 방식을 포워딩 방식이라고 함<br>
		  포워딩 방식은 현재 페이지의 요청과 응답의 정보에 대한 제어권만 다른 페이지로 넘어가므로,
		  요청 정보와 응답 정보가 유지됨
	</p>
	<a href="forward.jsp">forward 테스트</a>
	
	<h3>session 객체</h3>
	
	<!-- ▼ 세션을 생성하는 메소드 : true 또는 false 리턴 -->
	isNew() : <%= session.isNew() %> <br>
	생성 시간 : <%= new Date(session.getCreationTime()) %> <br>
	최종 접속 시간 : <%= new Date(session.getLastAccessedTime()) %><br>
	세션 ID : <%= session.getId() %><br>
	
	<h3>application 객체</h3>
	<table border="1">
		<tr>
			<td>JSP 버전</td>
			<td><%= application.getMajorVersion() %>.<%= application.getMinorVersion() %></td>
		</tr>
		<tr>
			<td>컨테이너 정보</td>
			<td><%= application.getServerInfo() %></td>
		</tr>
		<tr>
			<td>웹 애플리케이션의 실제 파일 시스템 경로</td>
			<td><%= application.getRealPath("/") %></td>
		</tr>
	</table>
	
	
	<h3>config 객체</h3>
	<table border="1">
		<tr>
			<th>초기 파라미터 이름</th>
			<th>초기 파라미터 값</th>
		</tr>
	<%
		Enumeration<String> initParamNames = config.getInitParameterNames();
		
		while(initParamNames.hasMoreElements()) {
			String initParamName = initParamNames.nextElement();
	%>
		<tr>
			<td><%= initParamName %></td>
			<td><%= config.getInitParameter(initParamName) %></td>
		</tr>	
	<%	
		}
	%>
		
	</table>
	
</body>
</html>
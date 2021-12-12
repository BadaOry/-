<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 스크립트 요소 (Elements)</title>
</head>
<body>
	<h2>JSP 스크립트 요소 (Elements)</h2>
	<!-- HTML 주석 -->
	<%-- JSP 주석 --%>
	<%-- 두 주석의 차이점 --%>
	<%-- 페이지의 소스 보기 혹은 개발자 도구에 HTML 주석은 확인가능하고 JSP 주석은 확인이 불가능함 --%>

	<%!
		// 선언부 (필드, 메소드 선언)
		// ▷ 컴파일된 java 파일의 필드 부분에 선언됨
		private String name = "무닌수";
		private LocalDate date;  // ◀ import 지시어 확인을 위한 태그
	%>
	<%
		// 자바 코드를 기술
		// ▷ 컴파일된 java 파일 내의 jspService 라는 메소드 내에 만들어짐
		// ▶ 지역변수로 만들어지며, 따라서 접근 제한자를 쓸 수 없음
		int total = 0; 
	
		for(int i = 1; i <= 10; i++) {
			total += i;
	
	// ▼ 이렇게 중간에 끊어서 웹 요소를 넣어주면 가독성 떨어짐
	//   ▷ 안녕하세요 10번 말함
	%> 
		안녕하세요.<br>
	<%
		}
		
		// ▼ 이클립스 내의 콘솔에 찍어줌
		System.out.println(total);
	%>
	
	
	<%-- ▼ out.print( name ); 와 같이 서블렛형태로 바뀌게 됨 --%>
	저의 이름은 <%= name %> 입니다.
	
	<br>
	 
	expression 출력 : 
	1 부터 10 까지의 합은 <%= total %> 입니다. <br>
	
	scriptlet 출력 :
	1 부터 10 까지의 합은 <% out.print(total); %> 입니다. <br>
	<!-- write 는 숫자를 문자 형태로 바꿔주므로,
	     값이 55가 나오는건 맞는데 아스키코드로 변환돼서 7로 출력됨 -->
	1 부터 10 까지의 합은 <% out.write(total); %> 입니다. <br>
	
	
</body>
</html>
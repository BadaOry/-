<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 액션태그 - forward</title>
</head>
<body>
	<h2>jsp:forward 액션 태그</h2>
	<p>
		: 현재 페이지의 요청과 응답에 대한 처리권을 다른 페이지로 넘기는 액션 태그
	</p>
	
	<!-- ▼ 포워딩 전에 응답 객체 쓴 내용은 forwarding 되면서 버퍼가 지워져서, 아무런 효과가 없음
	       ▷ 만약 아래 코드가 서블렛으로 바뀌면
	         out.print("<script>alert('안녕하세요.');</script>"); 이 됨
	         ▷ out 은 버퍼에 쌓이는 것이기 때문 (사용자에게 넘어가는것이 아님)
	-->
	<script>alert('안녕하세요.');</script>
	
	
	
	<!-- ▼ 브라우저가 아닌 서버에서 처리함 -->
	<jsp:forward page="forwardPage.jsp" />
	
	<%--
	<!-- ▼ 위 태그와 같은 내용의 태그임 -->
	<jsp:forward page="forwardPage.jsp"></jsp:forward>	
	--%>
</body>
</html>
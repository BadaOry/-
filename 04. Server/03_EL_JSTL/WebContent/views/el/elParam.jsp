<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
    // ▼ 요청된 파라미터들에 대한 인코딩 정보 (안하면 한글 꺠져서 나옴)
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 내역</title>
</head>
<body>
	<h2>주문 내역</h2>
	<p>
		param : 해당 페이지 요청 시, 전달된 파라미터 값을 받아올 때 사용 <br>
		paramValues : 해당 페이지 요청 시, 전달된 파라미터 값을 배열로 받아올 때 사용 <br>
	</p>
	
	상품명 : ${ param.pName } <br>
	수량 : ${ param.pCount } <br>
	
	<!--  값이 두개 이상이지만 param 내장 객체를 통해 접근이 가능
	      단, 첫 번째 값만 가져올 수 있음                   -->
	옵션 : ${ param.option } <br>
	옵션1 : ${ paramValues.option[0] } <br>
	옵션2 : ${ paramValues.option[1] } <br>
	
	for 문을 사용한 자동 출력 : 
	<!-- ▼ 내가 만들어본 for 반복문으로 자동 출력하기 -->
	<%
		String[] arr = request.getParameterValues("option");

		for(int i = 0; i < arr.length; i++) {
	%>	
		<span style="color:tomato"><%= arr[i] + " " %></span>
	<%
		}
	%>	
</body>
</html>
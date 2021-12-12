<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Formatting Library</title>
</head>
<body>
	<h2>JSTL Formatting Library</h2>
	- 톰캣 서버의 Locale 정보 : ${ pageContext.response.locale }  <br>
	
	<!-- ▼ 톰캣 서버의 Locale을 아예 바꿔버림 -->
	<fmt:setLocale value="zh_cn"/>
	
	<h3>1. formatNumber</h3>
	<p>
		: 숫자의 데이터 포맷을 지정할 때 사용하는 태그 <br>
		  표현하고자 하는 숫자의 형식을 통화 기호, % 등 원하는 형식으로 지정 가능
	</p>
	
	<!--  groupingUsed 속성 : 숫자 단위의 구분자 (,) 표시 여부 지정 (default : true) -->
	숫자 그대로 출력 : <fmt:formatNumber value="123456789" groupingUsed="false" />	<br>
	, 으로 구분하여 출력 : <fmt:formatNumber value="123456789" groupingUsed="true" />	<br><br>
	
	<!--  pattern : 화면에 표시할 데이터의 스타일을 지정함
	                #, 0 기호를 사용하여 지정하고 초과된 부분은 반올림 됨 -->
	<!-- ▼ #의 경우 부족한 부분을 표시하지 않음 -->
	#을 이용하여 출력 1 : <fmt:formatNumber value="1.2346" groupingUsed="#.###" />	<br>
	#을 이용하여 출력 2 : <fmt:formatNumber value="1.23" groupingUsed="#.###" />	<br>
	<!-- ▼ 0의 경우 부족한 부분은 0으로 표시 -->
	0을 이용하여 출력 1 : <fmt:formatNumber value="1.2346" pattern="0.000" />	<br>
	0을 이용하여 출력 2 : <fmt:formatNumber value="1.23" pattern="0.000" />	<br><br>

	<!--  type : number (일반 숫자, 기본값), percent (백분율), currency (통화) -->
	number       : <fmt:formatNumber type="number" value="50000" />  <br>
	currency     : <fmt:formatNumber type="currency" value="50000" />  <br>
	currency ($) : <fmt:formatNumber type="currency" value="50000" currencySymbol="$"/> <br>
	percent      : <fmt:formatNumber type="percent" value="0.75" /> <br><br>

	<h3>2. formatDate</h3>
	<p>
		: 날짜와 시간의 데이터 포맷을 지정할 때 사용하는 태그 <br>
		  value 속성에는 java.util.Date 객체를 사용해야 함
	</p>
	
	<c:set var="now" value="<%= new Date() %>"/>
	<!-- ▼ type 생략 가능 (기본값 : date) -->
	<fmt:formatDate value="${ now }"/>

	<ul>
		<li>날짜        : <fmt:formatDate type="date" value="${ now }"/></li>
		<li>시간        : <fmt:formatDate type="time" value="${ now }"/></li>
		<li>날짜 & 시간  : <fmt:formatDate type="both" value="${ now }"/></li>
		<li>[short]    : <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${ now }"/></li>
		<li>[medium]   : <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${ now }"/></li>
		<li>[long]     : <fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${ now }"/></li>
		<li>[full]     : <fmt:formatDate type="both" dateStyle="full" timeStyle="full" value="${ now }"/></li>
		<li>내 패턴      : <fmt:formatDate type="both" pattern="yyyy-MM-dd(E) HH:mm:ss(a)" value="${ now }"/></li>
	</ul>
	
	<br><br><br>

</body>
</html>
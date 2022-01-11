<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.el.model.vo.Person"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- ◆ uri   : 설치한 태그 라이브러리 안에서 core 에 해당하는 것을 찾아오는 식별자 개념
     ◆ preix : custom 태그에 사용할 접두어 (맘대로 지정 가능) --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Core Library</title>
</head>
<body>
	<h2>JSTL Core Library</h2>
	 
	<h3>1. 변수</h3>
	
	<h4>1) 변수 선언 (c:set)</h4>
	<p>
		[ 정의 ]<br>
		- 변수를 선언하고 초기값을 대입하는 기능을 가진 태그 <br>
		- 변수 선언 시 scope를 지정할 수 있음 <br>
		  (* 지정하지 않으면 기본적으로 pageScope에 저장된다.) <br>
		[ 사용 방법 ] <br>
		 - 변수 타입은 별도로 선언하지 않음 <br>
		 - 초기값은 반드시 지정해야 함 <br>
		 - c:set 태그로 선언한 변수는 EL 안에서 사용이 가능하고, <br>
		   스크립트릿 요소에서는 직접 사용할 수 없음
	</p>
	
	<%-- ▼ 아래와 같은 코드 : pageContext.setAttribute("num1", "10"); 
	                     ▷ page 영역 객체에 값이 20인 num2 라는 이름을 가진 속성을 추가--%>
	<c:set var="num1" value="10"/>
	
	<%-- ▼ 아래와 같은 코드 : request.setAttribute("num2", "20"); 
	                     ▷ request 객체에 값이 20인 num2 라는 이름을 가진 속성을 추가 --%>
	<c:set var="num2" value="20" scope="request"/>
	
	<%-- ▼ 위에서 만든 num1, num2 는 EL식에서 사용 가능해서 이렇게도 쓸 수 있음
	       ▷ 아래와 같은 코드 : session.setAttribute("result", num1+num2) 
	         * scope = "session" 은 테스트를 위해 임의로 session 을 넣은거임--%>
	<c:set var="result" value="${ num1 + num2 }" scope="session"/>
	
	<%-- ▼ 배열 형태로 넣어주고 싶으면 아래와 같이 , 로 구분하면 됨 --%>
	<c:set var="array" scope="request">
		red, blue, yellow, pink, green
	</c:set>

	
	<%-- ▼ c:set 에서 쓴 변수는 EL에서만 사용 가능해서 ${} 안에 넣은 것임 --%>
	<%-- ▼ c:set 에서 쓴 변수는 EL에서만 사용 가능해서 ${} 안에 넣은 것임 --%>
	num1 변수 값 (EL로 출력)              : ${ num1 } 또는 ${ pageScope.num1 }<br>
	
	<%-- ▼ 이건 에러남. 당연함. 스크립트릿 요소에서는 "직접" 사용할 수 없음 --%>
	<%-- num1 변수값  (스크립트릿으로 출력)   : <%= num1 %> <br> --%> 
	
	<%-- ▼ page 영역에 저장된 속성을 표현식을 사용하여 출력함 --%>
	num1 변수 값 (표현식으로 출력)           : <%= pageContext.getAttribute("num1") %> <br>
	
	num2 변수 값                        : ${ num2 } 또는 ${ requestScope.num2 } <br>
	
	result 변수 값                      : ${ result } 또는 ${ sessionScope.result } <br>
	 
	array 배열 값                       : ${ array }
	
	
	
	<h4>2) 변수 삭제 (c:remove)</h4>
	<p>
		: 지정한 변수를 모든 scope 에서 검색해서 삭제함 <br>
		  (scope 속성을 지적해서 특정 scope 의 변수만 삭제하는 것도 가능)
	</p>
	
	<%-- ▼ var 명이 같아도 scope 가 달라서 가능 --%>
	<c:set var="result" value="99999" scope="request" />
	
	삭제 전 : ${ result }  <%-- ▶ 99999 출력 
	                            : EL은 page ▷  request ▷ session ▷ application 순으로 찾아오기 때문 --%>
	
	<h5>* 특정 scope 에서 삭제</h5>
	<c:remove var="result" scope="request"/>
	삭제 후 : ${ result }  <%-- ▶ 30
	                            : 모든 request scope 의 result 를 지우기 때문 --%>
	
	<h5>* 모든 scope 에서 삭제</h5>
	<c:remove var="result" />
	삭제 후 : ${ result }  <%-- ▶ 값이 안나옴
	                            : 모든 scope 의 result 를 지우기 때문 --%>
	
	<h4>3) 변수 출력 (c:out)</h4>
	<p>
		: 데이터를 출력할 때 사용하는 태그
	</p>
	
	태그를 문자열로 출력 : <c:out value="<b>태그로 출력하기</b>" /> 
	<br>
	태그로 해석되어 출력 : <c:out value="<b>태그로 출력하기</b>" escapeXml="false" />
	<br>
	기본값 출력 : <c:out value="${ result }" default="값이 없음.."></c:out>
	
	<h3>2. 조건문</h3>
	<h4>1) IF (c:if)</h4>
	<p>
		: java 의 if 문과 비슷한 역할을 하는 태그 <br>
		  태그 사용시의 조건은 test 속성에 지정하고, <br>
		  이 때 조건식은 반드시 EL 형식으로 기술해야 함 <br>
	</p>
	
	<!-- ▼ test 의 내용이 참이면 출력됨
	       ▷ num1이 num2보다 작다 가 참이므로 출력됨 -->
	<c:if test= "${ num1 > num2 }">
		num1 이 num2 보다 크다
	</c:if>
		
	<c:if test= "${ num1 < num2 }">
		num1 이 num2 보다 작다
	</c:if>

	
	<h4>2) Choose (c:choose, c:when)</h4>
	<p>
		: java 의 if else if 문 또는 switch 문과 비슷한 역할 <br>
		  각 조건들은 c:choose 문의 하위 요소로 c:when 을 통해서 작성 <br>
		  (default 값으로는 c:otherwise)
	</p>
	
	<c:set var="count" value="0" />
	
	<c:choose>
		<c:when test="${ count eq 0 }">
			처음 뵙겠습니다W. <br>
		</c:when>
		<c:when test="${ count eq 1 }">
			반갑습니다. 또 뵙네요.<br>
		</c:when>
		<c:otherwise>
			안녕하세요. <br>		
		</c:otherwise>
	</c:choose>
	
	
	<h3>3. 반복문</h3>
	<h4>1) forEach (c:forEach)</h4>
	<p>
		: java 의 for, for ~ in 뮨에 해당하는 기능을 제공하는 태그
	</p>
	
	<h5>* 기본 사용법</h5>
	<c:forEach var="i" begin="1" end="10">
		반복 확인 : ${ i } 번째 반복 <br>
	</c:forEach>
	
	<!-- ▼ 태그에도 적용 가능
	       ▷ step 의 값 : 1 씩이 아니라 step 만큼씩 증가시켜 반복함 -->
	<c:forEach var="i" begin="1" end="6" step="2">
		<h${ i }>EL로 반복문 사용</h${ i }>
	</c:forEach>
	
	<!-- items 속성 : 배열 혹은 컬렉션의 값을 하나씩 가져와서 출력   -->
	<c:forEach var="color" items="${ array }">
		<font color="${ color }">반복 확인 : ${ color }</font> <br>
	</c:forEach>
	
	<%
		List<Person> list = new ArrayList<>();
		
		list.add(new Person("무닌수", 20, '남'));
		list.add(new Person("홍갈동", 21, '남'));
		list.add(new Person("성춘향", 17, '여'));
		list.add(new Person("이몽룡", 29, '남'));	
		
		// ▼ setAttribute 안하면 EL 에서 바로 사용 못하므로 아무 scope 에 임의로 넣어봄
		pageContext.setAttribute("list", list);
	%>
	
	<br> 
	
	<table border="1">
		<tr>
			<th>인덱스</th>
			<th>순번</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
			<th>isFirst</th>
			<th>isLast</th>
		</tr>
		<%-- ▼ varStatus : 반복에 대한 상태를 가지고 있는 변수 --%> 
		<c:forEach var="person" items="${ list }" varStatus="status">
			<tr>
				<td>${ status.index }</td>
				<td>${ status.count }</td>
				<td>${ person.name }</td>
				<td>${ person.age }</td>
				<td>${ person.gender }</td>
				<td>${ status.first }</td>
				<td>${ status.last }</td>
			</tr>
		</c:forEach>
	</table>
	
	<h4>2) forTokens (c:forTokens)</h4>
	<p> 
		: 문자열에 포함된 구분자를 통해 토큰을 분리해서 반복 처리하는 태그 <br>
		  java의 String.split() 또는 StringTokenizer 와 비슷한 기능을 함 <br>
	</p>
	
	<c:set var="device">
		컴퓨터, 노트북, 핸드폰.TV/에어컨,냉장고.세탁기
	</c:set>
	
	<ul>
		<c:forTokens var="d" items="${ device }" delims=",./">
			<li>${ d }</li>
		</c:forTokens>
	</ul>
	<ul>
		<c:forTokens var="color" items="pink aqua tomato yellow lime blue" delims=" ">
			<li style="background-color:${ color }; color:black">${ color }</li>
			
		</c:forTokens>
	</ul>
	
	
	<h3>4. URL (c:url)</h3>
	<p>
		: URL 경로를 생성하고, 해당 URL 의 param 속성을 선언하여
		  쿼리 스트링을 정의할 수 있는 태그 <br>
		  해당 태그를 통해 URL 경로와 관련된 쿼리 스트링 값을 미리 결정하여 이를 제어할 수 있음
	</p>
	
	<c:url var="url" value="urlPage.jsp">
		<c:param name="pName" value="아이폰 12 미니"></c:param>
		<c:param name="pCount" value="1"></c:param>
		<c:param name="option" value="화이트"></c:param>
		<c:param name="option" value="64기가"></c:param>
	</c:url>
	
	url 의 쿼리 스트링 값 : ${ url } <br>
	<%-- ▼ get 방식으로 전송 --%>
	<a href="${ url }">▶ 이동</a>

</body>
</html>
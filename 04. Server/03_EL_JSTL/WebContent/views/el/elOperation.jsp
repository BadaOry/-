<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.el.model.vo.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL (연산자)</title>
</head>
<body>
	<h2>EL 연산자</h2>
	
	
	<h3>산술 연산</h3>
	10 더하기 5 =${ 10 + 5 } <br>
	10 뺴기 5 =${ 10 - 5 } <br>
	10 곱하기 5 =${ 10 * 5 } <br>
	10 나누기 5 =${ 10 / 5 } 또는 ${ 10 div 5 } <!--  div 는 이클립스에서 가끔 인식 못함 --><br>
	10 나누기 7의 나머지 = ${ 10 % 7 } 또는 ${ 10 mod 7 } <!--  mod 는 이클립스에서 가끔 인식 못함 --><br>
	<br>
	'10' 더하기 5 =${ '10' + 5 } <br> <!-- ▶ 15 나옴 (EL 에는 문자열 연산 기호 없음) -->
	
	<h3>객체 비교 연산</h3>
	<% 
		String str1 = "하이";
		String str2 = new String("하이");
		Person p1 = new Person("무닌수", 20, '남');
		Person p2 = new Person("홍길동", 20, '남');
		
		// ▼ 아래 EL 로 출력하는 테스트를 위해 임의로 아무 영역객체 (Page Scope) 에 데이터 담아본 것
		pageContext.setAttribute("str1", str1);
		pageContext.setAttribute("str2", str2);
		pageContext.setAttribute("p1", p1);
		pageContext.setAttribute("p2", p2);
	%>
	
	<%-- ▼ 위에 저장된 scriptlet 의 값 (str1, str2) 은 EL 으로 출력 불가
	       ▷ EL은 page >..>application 순서로 영역 내장 객체에 저장되어있는 속성(Attribute)에서 가져옴 
	       ▷ 근데 scriptlet 의 값은 Attribute 에 담겨있지 않아서 EL 으로 출력이 안되는 것임
	       ▶ 영역객체.setAttribute("키값(속성명)", "밸루값") 사용하면 출력 가능 --%>
	<%--
	str1 : ${ str1 } <br>
	str2 : ${ str2 } <br>
	 --%>
	 
	 
	 <table border="1">
	 	<tr>
	 		<th>비교식</th>
	 		<th>표현식</th>
	 		<th>EL</th>
	 	</tr>
	 	<tr>
	 		<td>str1 == str2</td>
	 		<td><%= str1 == str2 %></td>                        <!-- ▶ false (equals 메소드 안썼기 때문) -->
	 		<td>${ str1 == str2 } 또는 ${ str1 eq str2 }</td>    <!-- ▶ true (EL의 == 연산은 equals()와 같은 동작을 함) -->
	 	</tr>
	 	<tr>
	 		<td>str1 != str2</td>
	 		<td><%= str1 != str2 %></td>                        <!-- ▶ true -->
	 		<td>${ str1 != str2 } 또는 ${ str1 ne str2 }</td>    <!-- ▶ false 또는 false -->
	 	</tr>
	 	<tr>
		 	<td>p1 == p2</td>
		 	<td><%= p1 == p2 %></td>                            <!-- ▶ false -->
		 	<td>${ p1 == p2 } 또는 ${ p1 eq p2 }</td>	 	        <!-- ▶ false -->
	 	</tr>
	 	<tr>
		 	<td>p1 != p2</td>
		 	<td><%= p1 != p2 %></td>                            <!-- ▶ true -->
		 	<td>${ p1 != p2 } 또는 ${ p1 ne p2 }</td>	 	        <!-- ▶ true -->
	 	</tr>
	 </table>
	 
	 <h3>숫자형 자동 형변환</h3>
	 
	 <%
	 	pageContext.setAttribute("big", 10);
	 	pageContext.setAttribute("small", 3);

	    // ▼ EL은 문자열로 저장해도 Object(Integer) 타입으로 알아서 형변환 후 저장해줌
	 	// pageContext.setAttribute("big", "10");
	    // ▼ 문자를 넣어주면 EL 은 알아서 코드값으로 형변환 해줌
	 	// pageContext.setAttribute("big", 'a');  
	    // ▼ 문자열을 넣어주면 EL 이 형변환 안해줌 (코드값으로 변환 못함)
	 	// pageContext.setAttribute("big", "a");  
	    
	 %>
	 
	 <!--  ▼ Object 끼리는 덧셈이 불가능 
	         ▷ Integer(오토언박싱) 혹은 int 로 강제 형변환 해야함 -->
	Scriptlet (오토언박싱) : <%= (Integer)pageContext.getAttribute("big") + (Integer)pageContext.getAttribute("small") %> <br>
	Scriptlet (int 강제형변환): <%= (int)pageContext.getAttribute("big") + (int)pageContext.getAttribute("small") %>

	<br>
	<!--  EL 은 객체에 속성으로 담긴 Object 타입을 
	      자동으로 인식 & 형변환하여 연산을 처리함 -->
	EL : ${ big + small } <br>
	
	<!-- 자동 형변환 덕분에 비교 연산도 가능함 -->
	big&gt;small : ${ big > small } 또는 ${ big gt small }  <!-- greater than --> <br>
	big&gt;small : ${ big < small } 또는 ${ big lt small }  <!-- less than --> <br>
	big&gt;small : ${ big >= small } 또는 ${ big ge small } <!-- greater or equal --> <br>
	big&gt;small : ${ big <= small } 또는 ${ big le small } <!-- less or equal --> <br>

	<h3>객체가 null 또는 비어있는지 체크하는 연산자</h3>
	<%
		// ▼ 문자열의 경우 비어있는것도 체크해 줌
		//String str3 = null;
	    // ▼ null은 null 이고, 어쨌든 얘는 값이 있긴 한것임
		String str3 = "";
		List<String> list = new ArrayList<>();
		
		list.add(str3);
		
		pageContext.setAttribute("str3", str3);
		pageContext.setAttribute("list", list);
	%>
	
	Scriptlet : <br>
	- str3 == null    : <%= str3 == null %> <br>     <!-- ▶ false -->
	- list.isEmpty()  : <%= list.isEmpty() %> <br>   <!-- ▶ false -->
	
	<br>
	
	<!-- EL 은 null 인지, 비어있는지(empty) 를 empty 라는 한 가지 명령어로 퉁쳐서 사용 -->
	EL : <br>
	- empty str3     : ${ empty str3 } <br>      <!-- ▶ true -->
	- !empty str 3   : ${ !empty str3 } <br>     <!-- ▶ false  -->
	- not empty str3 : ${ not empty str3 } <br>  <!-- ▶ false -->
	- empty list     : ${ empty list } <br>      <!-- ▶ false -->
	- not empty list : ${ not empty list } <br>  <!-- ▶ true  -->
	
	<h3>논리 연산자 / 부정 연산자</h3>
	${ true && false } 또는 ${ true and false } <br>     <!-- ▶ false -->
	${ true || false } 또는 ${ true or false } <br>      <!-- ▶ true -->
	${ big > small } 또는 ${ ( big > small) } <br>       <!-- ▶ true -->
	${ !(big > small) } 또는 ${ not(big > small) } <br>  <!-- ▶ false -->
	
</body>
</html>
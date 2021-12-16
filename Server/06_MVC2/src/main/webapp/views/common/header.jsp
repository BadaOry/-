<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${ pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ path }/resources/css/style.css">
<script src="${ path }/resources/js/jquery-3.6.0.js"></script>
</head>
<body>
	<header>
		<h1>Hello MVC</h1>
		<div class="login-container">
			<c:if test="${ empty loginMember }">
				<form id="loginFrm" action="${ path }/login" method="post">
					<table>
						<tr>
							<td>
								<input type="text" name="userId" id="userId" placeholder="아이디" 
									   value="${ empty cookie.saveId ? '' : cookie.saveId.value }" required>
									                                        <%-- ▲ 쿠키의 값 --%>
							</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<input type="password" name="userPwd" id="userPwd" placeholder="비밀번호" required>
							</td>
							<td>
								<input type="submit" value="로그인">						
							</td>
						</tr>
						<tr>
							<td colspan="2">
																			<%-- cookie의 saveId 가 비어있으면 공백, 비어있지 않으면 checked 표시 --%>
								<label><input type="checkbox" name="saveId" ${ empty cookie.saveId ? "" : "checked" }>아이디 저장</label>
								<input type="button" value="회원가입" onclick="location.href = '${ path }/member/enroll';"> 
							</td>
						</tr>
					</table>
				</form>
			</c:if>
			<%-- ▼ 로그인이 되면 넘어갈 페이지 --%>
			<c:if test="${ !empty loginMember }">
				<table>
					<tr>
						<td colspan="2">
							${ loginMember.name } 님 안녕하세요.
						</td>
					</tr>
					<tr>
						<td>
							<!-- 뒤로가기 되어도 괜찮으니 location.href 사용-->
							<!-- myPage.jsp 파일이 아니라 서블릿을 통해서 들어가는 것임 -->
							<button onclick="location.href='${ path }/member/myPage'">내 정보</button>
						</td>
						<td>
							<!-- 뒤로가기 안되게 하려고 location.replace 사용-->
							<button onclick="location.replace('${ path }/logout')">로그아웃</button>
						</td>
					</tr>
				</table>
			</c:if>
		</div>
		<nav>
			<ul class="main-nav">
				<li class="home"><a href="${ path }/">Home</a></li>
				<li id="board"><a href="${ path }/board/list">게시판</a></li>
			</ul>
		</nav>
	</header>
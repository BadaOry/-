<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="/views/common/header.jsp" />

<style>
    section>div#board-write-container{width:600px; margin:0 auto; text-align:center;}
    section>div#board-write-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    div#comment-container button#btn-insert{width:60px;height:50px; color:white; background-color:#3300FF;position:relative;top:-20px;}
    
    /*댓글테이블*/
    table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl-comment button.btn-delete{display:none;}
    table#tbl-comment tr:hover {background:lightgray;}
    table#tbl-comment tr:hover button.btn-delete{display:inline;}
    table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
    table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
</style>
<section id="content">   
	<div id="board-write-container">
		<h2>게시판</h2>
		<table id="tbl-board">
			<tr>
				<th>글 번호</th>
				<td>${ board.no }</td>
			</tr>
			<tr>
				<th>제 목</th>
				<td>${ board.title }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${ board.writerId }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${ board.readCount }</td>
			</tr>
			<tr>
				<th>첨부파일</th> 
				<td>
					<c:if test="${ empty board.originalFileName }">
								<span> - </span>
					</c:if>
					<c:if test="${ !empty board.originalFileName }">
						<img src="${ pageContext.request.contextPath }/resources/images/file.png" width="20" height="20"/>
						<%-- 파일 다운로드 방법 두 가지 --%>
						<%-- 1) ﻿webapp (루트) 에 저장되어 있는 경우, 경로로 접근하기
						        단, rname 으로 다운로드 되므로 앵커태그의 download 속성에 oname 을 넣으면 됨
						<a href="${ pageContext.request.contextPath }/resources/upload/board/${ board.renamedFileName }"
							download=${ board.originalFileName }>
							<c:out value="${ board.originalFileName }" />
						</a>
						--%>
						<%-- ﻿2) 같은 서버 내의 다른 경로에 저장된 경우, Stream 으로 접근하기 
						        function 연결해주고 board.oname 과 borad.rname 넘겨줌 
						        ▷ 서블렛으로 요청 --%>
						<a href="javascript:fileDownload('${ board.originalFileName }', '${ board.renamedFileName }')">
							<c:out value="${ board.originalFileName }" />
						</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td>${ board.content }</td>
			</tr>
			<%-- [ 글작성자/관리자인경우 수정삭제 가능 ] --%>
			<tr>
				<th colspan="2">
					<%-- 실제 게시글 작성자만 볼 수 있게 하는 로직 --%>
					<c:if test="${ !empty loginMember && loginMember.id == board.writerId }">
						<button type="button" onclick="location.href='${ pageContext.request.contextPath }/board/update?no=${ board.no }'">수정</button>
						<button type="button" id="btnDelete">삭제</button>
					</c:if>
					
					<button type="button" onclick="location.href='${ pageContext.request.contextPath }/board/list'">목록으로</button>
				</th>
			</tr>
		</table>
		<div id="comment-container">
	    	<div class="comment-editor">
	    		<form action="" method="">
	    			<input type="hidden" name="boardNo" value="">
	    			<input type="hidden" name="writer" value="">
					<textarea name="content" cols="55" rows="3"></textarea>
					<button type="submit" id="btn-insert">등록</button>	    			
	    		</form>
	    	</div>
	    </div>
	    <table id="tbl-comment">
    	   	<tr class="level1">
	    		<td>
	    			<sub class="comment-writer">aa</sub>
	    			<sub class="comment-date">2021.05.07</sub>
	    			<br>
	    			컨텐츠
	    		</td>
	    		<td>
    				<button class="btn-delete">삭제</button>

	    		</td>
	    	</tr>
	    </table>
    </div>
</section>

<script>
	$(document).ready(() => {
		$("#btnDelete").on("click", () => {
			if(confirm("정말로 게시글을 삭제 하시겠습니까?")) {
				location.replace("${ pageContext.request.contextPath }/board/delete?no=${ board.no }");
				
			}
			
		});
		
		$("#replyContent").on("focus", (e) => {
			if(${ empty loginMember }) {
				alert("로그인 후 이용해주세요!");
			
				$("#userId").focus();
			}
		});	
	});
	
	function fileDownload(oname, rname) {
		// ▼ 연결 확인용 alert
		//alert(oname + "," + rname);
		
		// ▼ 원래 oname 과 퍼센트 인코딩한 oname 을 시험삼아 출력해봄
		console.log(oname, encodeURIComponent(oname));
		
		// ▼ encodeURIComponent()
		//  - 아스키문자(a~z, A~Z, 1~9, ...) 는 그대로 전달하고
		//    기타 문자 (한글, 특수문자 등)만 %XX (16진수 2자리)와 같이 변환됨
		// ▼ oname, rname 정보를 퍼센트 인코딩 방법으로 인코딩하여 FileDownloadServlet 로 넘김
		location.assign("${ pageContext.request.contextPath }/board/fileDown?oname=" + encodeURIComponent(oname) + "&rname=" + encodeURIComponent(rname));
	}
	
</script>

<jsp:include page="/views/common/footer.jsp" />
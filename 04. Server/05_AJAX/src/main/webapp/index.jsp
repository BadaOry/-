<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
	<!-- ▼ c:set 으로 path 라는 변수를 선언하여 ContextPath 를 편하게 쓰는 용도 -->
	<c:set var="path" value="${ pageContext.request.contextPath }"/>     
	<h2>AJAX</h2>
	<h3>1. JavaScript 를 이용한 AJAX 테스트</h3>
	<h4>1) GET 방식으로 서버에 데이터 전송 및 응답</h4>
	
	<button onclick="jsAjaxTest1()">GET 방식 전송</button>
	
	<p id="test1"></p>

	<script>
		function jsAjaxTest1() {
			// 1. XMLHttpRequest 객체 생성
			let xhr = new XMLHttpRequest();
			
			/* 브라우저 버전 별 ajax 지원 여부 및 호환성 검사를 해야 함 
			// ▼ IE 7버전 이상 또는 그 외의 브라우저들
			if(window.XMLHttpRequest) {
				xhr = new XMLHttpRequest();
			}
			 // ▼ IE 6버전 이하일 경우
			else if (window.ActiveXObject){
				try {
					xhr = ActiveXObject("Microsoft.XMLHTTP");
				} catch(e) {
					xhr = null;
				}
			} 
			// ▼ ajax를 지원하지 않는 브라우저
			else {
				xhr = null;
			}
			*/ 
			
			// 2. onreadystatechange
			//    : AJAX 통신에 대한 응답 상태에 변경이 있을 때 마다 실행되는 함수
			//      ▷ readyState 와 status 라는 두 가지 값을 확인할 것임 
			xhr.onreadystatechange = () => {
				
				/*  1) readyState : 서버 응답 상태 확인 (AJAX 통신 진행 상황 확인)
						- 0 : 요청이 초기화 되지 않은 상태
						- 1 : 서버 연결이 설정된 상태
						- 2 : 서버가 요청을 받은 상태
						- 3 : 서버가 요청을 처리하는 상태
						- 4 : 서버가 요청에 대한 처리가 끝났고, 응답을 준비하는 상태
				*/
				console.log("readyState : " + xhr.readyState);
				
				// ▼ readyState의 값이 4(응답을 준비하는 상태)일 경우 처리하는 조건문
				if (xhr.readyState === 4) {
					
				/*	2) status : HTTP 응답 상태 코드 (응답이 정상적으로 이루어졌는지 확인)
						- 200 : OK
						- 404 : Not Found 
						등등..
						(https://developer.mozilla.ord/docs/web/HTTP/Status)
				*/
				if (xhr.status === 200) {
					// ▼ 응답 데이터 문자열을 담고 있는 속성
					let str = xhr.responseText; 
					
					document.getElementById('test1').innerHTML = str;
				// ▼ 에러가 있는 경우 실행될 것임
				} else {
					console.log("AJAX 통신 실패 : " + xhr.status);
				}
			}
		}
				
			//	3. open() : 서버와 데이터 교환 시 필요한 정보 입력
			//              (요청 방식, 대상(서버), 비동기/동기 여부 (기본값 true))
			//              ▷ 여기선 GET 방식이므로 쿼리 스트링 형식으로 넘겨줄 것임
		xhr.open("GET", "${path}/jsAjax.do?name=무닌수&age=20", true);
			
			//	4. send() : 서버에 데이터 전송 (요청)
		xhr.send();
		}
	</script>
	
	<h4>2) POST 방식으로 서버에 데이터 전송 및 응답</h4>
	
	<button onclick="jsAjaxTest2()">POST 방식 전송</button>
	<p id="test2"></p>
	
	<script>
		function jsAjaxTest2() {
			// 1. XMLHttpRequest 객체 생성
			let xhr = new XMLHttpRequest();
			
			// 2. onreadystatechange
			xhr.onreadystatechange = () => {
				if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
					// ▼ 응답 데이터 문자열을 담고 있는 속성
					let str = xhr.responseText;
					
					document.getElementById('test2').innerHTML = str;
				}
			}
			
			// 3. open()
			xhr.open("POST", "${path}/jsAjax.do", true);
			
			// * POST 방식 데이터 전송 시, send() 호출 전에 MINE TYPE 을 설정해서 헤더를 변경해줘야 함
			//                                     (실제로 주고받는 데이터의 데이터 타입)
			//   ▷ 아래의 헤더를 추가하지 않으면, 서버에서 전송한 파라미터를 인식하지 못함
			//      ▷ 모질라 AJAX 홈페이지에서 확인 가능
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			
			// 4. send()
			//    : POST 방식은 쿼리 스트링으로 데이터를 보내지 않으므로, 
			//      변수에 그냥 쓰는 대신 setRequestHeader 를 설정해야함
			xhr.send("name=홍길동&age=30");
		}
	</script>
	
	<h3>2. jQuery 를 이용한 AJAX 테스트</h3>
	<h4>1) GET 방식으로 서버에 데이터 전송 및 응답</h4>
	입력 : <input type="text" id="input1" /> <br>
	응답 : <input type="text" id="output1" readonly/> <br>
	<button id="btn1">전송</button> <br>
	
	<script>
		$(document).ready(() => {
			// ▼ btn1 에 이벤트 등록
			$("#btn1").on("click", () => {
				// ▼ 입력값을 id 로 가져오기
				let input = $("#input1").val();
				
				// ▼ jQuery 에서 ajax 객체를 불러오는 코드 ($.ajax)
				//   이 때 필요한 설정값은 ajax 객체 리터럴 형태로 가져와서 전달할 것임
				$.ajax({
					type: "get",          // ▷ 전송 방식 (GET 또는 POST)
					url : "jqAjax1.do",   // ▷ 데이터를 전송(요청)할 URL  (필수값 !!)
					data: {               // ▷ 요청 시 전달할 파라미터 설정
						input: input      // ▶ 속성명 input : let 변수값 input
					},
					success: function(result) {
						                  // ▷ AJAX 통신 성공 시 처리할 콜백 함수 지정
										  //   매개변수는 서버에서 응답이 왔을 때 그 값이 저장되는 변수 (임의로 변수명 지정 가능)
						console.log(result)
						
						// ▼ result 의 값을 id 가 output1 인 텍스트박스에 넣는 코드
						$("#output1").val(result);  
					},
					error: function(error) {
						                  // ▷ AJAX 통신 실패 시 처리할 콜백 함수 지정
										  //   매개변수는 서버에서 응답이 왔을 때 그 값이 저장되는 변수 (임의로 변수명 지정)
						console.log("error", error);
					},
					complete: function() {
						                  // ▷ AJAX 통신의 성공 여부와 상관 없이 실행되는 콜백 함수 지정
						console.log("complete");
						
					}
				});
			});
		});
	</script>
	

	<h4>2) POST 방식으로 서버에 데이터 전송 및 응답</h4>
	입력 : <input type="text" id="input2" /> <br>
	응답 : <input type="text" id="output2" /> <br>
	<button id="btn2">전송</button> <br>
	
	<script>
		$(document).ready(() => {
			$("#btn2").on("click", () => {
				let input = $("#input2").val();
				
				$.ajax({
					type: "post",
					url: "jqAjax1.do",
					data: {
						input // ▷ input: input으로 변경됨 (속성명과 해당 속성값을 담고 있는 변수명이 동일한 경우)
					},
					success: function(result) {
						$("#output2").val(result);
					},
					error: function(error) {
						console.log(error);
					}
				});
				
			});	
		});
	</script>
	
	<h4>3) 서버로 데이터 전송 후, 응답을 객체(Object)로 받기</h4>
	<!-- 자바 객체를 만들어서 JSON 으로 넘길거고, 넘겨받은 JSON 을 JavaScript 로 변환하여 사용할 것임  -->
	<p>
		: 회원 번호를 입력하여 조회 버튼을 클릭 <br>
		  사용자가 존재하면 사용자 정보 출력, 존재하지 않으면 "사용자 정보가 없습니다." 출력
	</p>
	
	회원 번호 입력 : <input type="text" id="userNo" />
	<button id="btn3">조회</button><br><br>
	
	<textarea id="textArea1" rows="4" cols="40"></textarea>
	
	<script>
		$(document).ready(() => {
			$("#btn3").on("click", () => {
				let userNo = $("#userNo").val();
				
				$.ajax({
					type: "get",
					url: "jqAjax2.do",
					dataType: "json", // ▷ 응답 데이터 형식 (미 작성 시 자동으로 응답 데이터 형식을 판단하여 지정)
					data: {
						userNo
					},
					success: function(obj) {
						console.log(obj); 
						
						let result = "";
						
						if(obj !== null) {
							result = "no : " + obj.no + ", name : " + obj.name + ", age: " + obj.age + ", gender: "+ obj.gender;
						} else {
								result = "사용자 정보가 없습니다.";
						}
						
						$("#textArea1").val(result);
						
					},
					error: function(error) {
						console.log(error)
					}
				});
			});
		});
	</script>
	
	<h4>4) 서버로 데이터 전송 후, 응답을 리스트(List)로 받기</h4>
	<p>
		: 선택한 성별을 가진 모든 회원 정보를 출력
	</p>
	성별 : 
	<label><input type="radio" name="gender" value="남">남자</label>
	<label><input type="radio" name="gender" value="여">여자</label>
	
	<button id="btn4">조회</button>
	
	<br><br>
	
	<textarea id="textArea2" rows="4" cols="40"></textarea>
	
	<script>
		$(document).ready(() => {
			$("#btn4").on("click", () => {
				// ▼ input 태그 중 속성이 name인 것 중 gender가 들어가있고, check 되어있는 요소의 값(val)을 가져옴
				let gender = $("input[name=gender]:checked").val();
				
				$.ajax({
					type: "get",
					url: "jqAjax3.do",
					dataType: 'json',
					data: {
						gender
					},
					success: (list) => {
						let result = "";
						
						// ▼ jQuery each 문으로 반복해서 돌려보기
						$.each(list, (i) => {
							result += 
								"no : " + list[i].no + ", name ; " + list[i].name + " \n";
						});
						
						$("#textArea2").val(result);
					},
					error:(error) => {
						console.log(error);
					}
				});
				
			});

		});
	
	</script>
</body>
</html>
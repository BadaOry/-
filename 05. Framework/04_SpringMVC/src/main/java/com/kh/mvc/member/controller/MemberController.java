package com.kh.mvc.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
// ▼ @SessionAttributes 
//   	: Model 객체에 loginMember라는 키값으로 객체가 추가되면 
//        해당 객체를 세션 스코프에 추가하는 어노테이션
@SessionAttributes("loginMember")
public class MemberController {

/*
	// ▼ RequestMapping : 컨트롤러가 처리할 요청을 정의 (ex. URL, Method 등 ..)
//	@RequestMapping(value = "/login", method = {RequestMethod.GET})
	@GetMapping("/login")
	public String login() {
		
		log.info("login() - 호출");
		
		// ▼ view 이름을 String 값으로 리턴하면 알아서 찾아줌
		return "home";
	}
	
	사용자의 파라미터를 전송받는 방법
	
	1. HttpServletRequest 를 통해서 전송받기 ▷ 기존 JSP / Servlet 방식
		- 메소드의 매개변수로 HttpServletReuqest 를 작성하면, 
		  메소드 실행 시 Spring 의 컨테이너가 자동으로 객체를 인자로 주입해줌
	
	@PostMapping("/login")
	public String login(HttpServletRequest request) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		log.info("login() - 호출 : {} {}", id, password);
		
		return "home";
	}
	
	2. @ReuquestParam 어노테이션을 통해서 전송받기
		- Spring 에서 조금 더 간편하게 파라미터를 받아올 수 있는 방법 중 하나
		- 내부적으로는 Request 객체를 이용해서 데이터를 전송받는 방식임
		- 단, 매개변수의 이름과 name 속성의 값이 동일하게 설정된 경우에만 자동으로 주입됨
		  ▷ 어노테이션을 사용하는 것이 아니기 때문에, defaultValue 설정은 불가능

	@RequestMapping(value = "login", method = {RequestMethod.POST} )
//	public String login(@RequestParam("id") String id, @RequestParam("password") String password) {
	public String login(String id, String password) {
	
		log.info("login() - 호출 : {} {}", id, password);
		
		return "home";
	}
	
	2-2. @ReuquestParam 에 default 값 설정
		- defaultValue 속성을 사용하면, 파라미터 name 속성에 값이 없을경우 기본값 지정 가능

	@PostMapping("/login")
	public String login(@RequestParam("id") String id, 
			  			@RequestParam(value="password", defaultValue ="0000") String password) {
	
		log.info("login() - 호출 : {} {}", id, password);
		
		return "home";
	}
	2-3. @ReuquestParam 에 실제 존재하지 않는 파라미터를 받으려고 할 때 
		- 파라미터 name 속성에 없는 값이 넘어올 경우 에러가 발생
		- @RequestParam(required = flase) 로 저장하면 null 값을 넘겨줌
		- 단, defaultValue 를 설정하면 defaultValue 에 설정된 값으로 넘겨줌 (에러가 발생하지 않음)

	@PostMapping("/login")
	public String login(@RequestParam("id") String id, 
			  			@RequestParam("password") String password,
//			  			@RequestParam(value="address", required = false) String address) {
			  			@RequestParam(value="address", defaultValue = "서울특별시") String address) {
	
		log.info("login() - 호출 : {} {} {}", new Object[] {id, password, address});
		
		return "home";
	}
	
	3. @PathVariable 어노테이션을 통해서 전송받기 (자주 사용하진 않음)
		- URL 패스 상에 있는 특정 값을 가져오기 위해 사용하는 방법
		- REST API 를 사용할 때, 요청 URL 상에서 필요한 값을 가져오는 경우 주로 사용
 		- 매핑 URL 에 {} 로 묶는다면, {} 안의 값을 Path Variable 로 사용하고
 		  요청 시 실제 경로상의 값을 해당 Path Variable 로 받겠다는 의미 
		- 매핑 URL 의 {} 안의 변수명과 매개변수의 변수명이 동일하다면,
		  @PathVariable 의 괄호는 생략 가능 (단, 어노테이션 자체는 생략 불가)

	@GetMapping("/member/{id}")
//	public String findMember(@PathVariable("id") String id) {
	public String findMember(@PathVariable String id) {
		
		log.info("Member ID : {}", id);
		
		return "home";
	}
	
	4. @ModelAttribute 어노테이션을 통해서 전송받기
		- 요청 파라미터가 많은 경우, 객체 타입으로 넘겨받는 방법
		- Spring Container 가 기본 생성자를 통해 객체를 생성하고
		  파라미터 name 속성의 값과 동일한 필드명을 가진 필드에 값을 주입
		- 단, 기본 생성자와 Setter 가 존재해야 함
		- @ModelAttribute 어노테이션을 생략해도 객체로 매핑됨
		   (* 그래도 명시적으로 해주는게 좋긴 하겠지 ㅎ.ㅎ)

	@PostMapping("/login")
	public String login(@ModelAttribute Member member) {
		
		log.info("{}, {}", member.getId(), member.getPassword());
		
		return "home";
	}

*/
	
	@Autowired
	private MemberService service;
	
	/* 
	 ■ 로그인 처리
 		1. HttpSession과 Model 객체
 	  		1) Model
 	    		- 컨트롤러에서 데이터를 뷰로 전달하고자 할 때 사용하는 객체
 	    		- 전달하고자 하는 데이터를 맵 형태 (key, value) 로 담을 수 있음
 	    		- Model 객체의 Scope는 Request 임

	
	@PostMapping("/login")
	public String login(
			HttpSession session, Model model,
			@RequestParam("id") String id, @RequestParam("password")String password) {
		
		log.info("{}, {}", id, password);
		
		Member member = service.login(id, password);
		
		
		if (member != null ) {
			// ▼ HttpSession 객체에 member 정보 저장하기
			session.setAttribute("loginMember", member);
		
			
			// 		* return "문자열"
			//			: forwarding 방식으로 ViewResolver 에 의해 
			//            /WEB-INF/views/home.jsp 로 포워드
			//		* return "redirect:/"
			//			: redirect 방식으로, 
			//			  여기서 리턴한 경로로 브라우저에서 다시 요청하도록 반환
			
			// ▼ 문자열 리턴해서 해당 이름의 view 로 포워딩 하기
			return "redirect:/";
		} else {
			model.addAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다.");
			model.addAttribute("location", "/");
		
			// ▼ ViewResovler 에 의해, 
			//   /WEB-INF/views/common/msg.jsp 로 포워딩 됨
			return "common/msg";
		}
	}
	
	// ■ 로그아웃 처리 : Session 지워주기
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}


	 ■ 로그인 처리
 		2. @SessionAttributes과 ModelAndView 객체
 	  		1) @SessionAttributes("키값")
 	    		- Model 객체에서 "키값"에 해당하는 Attribute를 
 	    		  Session Scope까지 범위를 확장시킴
 	  		2) ModelAndView
 	    		- 컨트롤러에서 뷰로 전달할 데이터 & 포워딩 해줄 view 의 정보를 담는 객체
 	    		- addAttribute()가 아닌 addObject()를 통해서 데이터를 담을 수 있다.

*/
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public ModelAndView login (
			ModelAndView model,
			@RequestParam("id") String id, @RequestParam("password") String password) {
		 
		log.info("{}, {}", id, password);
		
		Member loginMember = service.login(id, password);
		
		if (loginMember != null) {
			
			// ▼ model 은 Request scope 이라서 안됨
			model.addObject("loginMember", loginMember);
			
			model.setViewName("redirect:/");
		} else {
			
			model.addObject("msg", "아이디나 비밀번호가 일치하지 않습니다.");
			model.addObject("location", "/");
			
			model.setViewName("common/msg");
		}
		 
		
		return model;
	}
	
	
	// ■ 로그아웃 처리 : SessionStatus 를 사용해 Session 지워주기
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		
		// ▼ status.setComplete() 가 잘 돌아갔는지 물어보는 코드 ▷ 아직이라 false 출력
		log.info("status.isComplete() : {}", status.isComplete());
		
		// ▼ SessionStatus 객체의 setComplete() 메소드로 
		//   Session scope 로 스확장된 객체들을 지워주는 코드
		status.setComplete();
		
		// ▼ status.setComplete() 가 잘 돌아갔는지 물어보는 코드 ▷ 잘 됐으니 true 출력
		log.info("status.isComplete() : {}", status.isComplete());
		
		return "redirect:/";
	}
	
	
	@GetMapping("/member/enroll")
	public String enroll() {
		
		log.info("회원 가입 페이지 요청");
		
		return "member/enroll";
	}
	
	@PostMapping("/member/enroll")
	public ModelAndView enroll(ModelAndView model, @ModelAttribute Member member) {
		
		// ▼ Member 값이 잘 넘어오는지 확인하는 코드 ▷ no 가 0으로 출력
		log.info(member.toString());
		
		int result = service.save(member);
		
		// ▼ Member 값이 잘 넘어오는지 확인하는 코드 ▷ no (pk값) 가 입력되어 출력
//		log.info(member.toString());	
		
		if (result > 0 ) {
			model.addObject("msg", "회원 가입이 정상적으로 완료되었습니다.");
			model.addObject("location", "/");
		} else {
			model.addObject("msg", "회원 가입이 실패했습니다.");
			model.addObject("location", "/member/enroll");
		}
		
		model.setViewName("common/msg");
		
		return model;
	}	
	
	
	/*
	 * @ResponseBody
	 * 	- 일반적으로, 컨트롤러 메소드의 반환형이 String 타입일 경우 뷰의 이름을 반환
	 *  - @ResponseBody 가 붙은 String 반환은, 해당 요청을 보낸 클라이언트에 전달할 데이터 의미
	 *
	 * jackson 라이브러리
	 * 	- 자바 객체를 JSON 형태의 데이터로 변환하기 위한 라이브버리 (GSON, jsonSiple)
	 *  - Spring 에서는 jackson 라이브러리를 추가하고 @ResponseBody 를 사용하면
	 *    리턴되는 객체를 자동으로 JSON 으로 변경하여 응답함
	 *    
	 * @RestController
	 * 	- 해당 컨트롤러의 모든 메소드에서 데이터를 반환하는 경우 사용
	 *    ▷ @Controller 대신 사용
	 * 	- @Controller 와 @ResponseBody 를 합쳐놓은 역할 수행
	 */
	@GetMapping("/member/jsonTest")
	@ResponseBody
//	public String jsonTest() {
	public Object jsonTest() {
//		Map<String, String> map = new HashMap<>();
		
//		map.put("hi", "hello");
		
//		return "Hello";
		return new Member("ismoon", "1234", "스댕");
//		return map;		
	}
	
	//                                       ▼ 이 메소드에선 JSON 데이터를 생산(등록)하고, 소비한다는 뜻 
	@PostMapping(value = "/member/idCheck" /* , produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}*/)
	@ResponseBody
//	public String idCheck(@RequestParam("userId") String userId) {
	public Object idCheck(@RequestParam("userId") String userId) {
		Map<String, Boolean> map = new HashMap<>();
		
		log.info("{}", userId);
		
		// ▼ Service 객체에 넘기기전에 그냥 false 넣어서 테스트 해보는 코드
//		map.put("duplicate", false);
		
		// ▼ 본격적으로 Service 객체에 넘겨보는 코드
		map.put("duplicate", service.isDuplicateId(userId));
		
//		return "Hello" + userId;
		return map;
	}
	
	@GetMapping("/member/myPage")
	public String myPage() {
		
		return "member/myPage";
	}
	
	@PostMapping("/member/update")
//	public String update(
	public ModelAndView update(
			ModelAndView model,
			@SessionAttribute(name="loginMember") Member loginMember,
			@ModelAttribute Member member) {
		int result = 0;
		
		// ▼ 잘 가져오는지 확인해보는 코드들
//		log.info(member.toString());      // ▷ no 값은 안가져옴
//		log.info(loginMember.toString()); // ▷ no 값까지 가져옴
		
		// ▼ loginMember 에서 no 값을 꺼내와 member 객체에 담는 코드
		member.setNo(loginMember.getNo());
		
		result = service.save(member);
		
		if(result > 0) {
			model.addObject("loginMember", service.findMemberById(loginMember.getId()));
			model.addObject("msg", "회원정보 수정을 완료했습니다.");
			model.addObject("location", "/member/myPage");
		} else {
			model.addObject("msg", "회원정보 수정을 실패했습니다.");
			model.addObject("location", "/member/myPage");
		}
		
		model.setViewName("common/msg");
		
//		return "member/myPage";
		return model;
	}
	
	@GetMapping("/member/delete")
	public ModelAndView delete(ModelAndView model,
			@SessionAttribute(name="loginMember") Member loginMember) {
		int result = 0;
		
		result = service.delete(loginMember.getNo());
		
		if(result > 0) {
			model.addObject("msg", "정상적으로 탈퇴되었습니다.");
			model.addObject("location", "/logout");
		} else {
			model.addObject("msg", "회원 탈퇴에 실패하였습니다.");
			model.addObject("location", "/memher/myPage");
		}
		
		model.setViewName("common/msg");
//		return "member/myPage";  // ▶ 맨 처음 컨트롤러 동작부터 확인하는 용도
		
		return model;
	}
}

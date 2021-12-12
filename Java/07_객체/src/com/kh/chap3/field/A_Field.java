package com.kh.chap3.field;
/*
 * 변수 구분
 * 
 *  1. 필드 (인스턴스 변수, 멤버 변수)
 *    - 클래스 영역에 바로 선언하는 변수, 클래스 내에서 어디든 사용 가능
 *    - 필드의 생성과 소멸
 *      생성 시점 : new 연산자를 통해서 객체 생성 시 heap 메모리 영역에 할당될 때
 *      소멸 시점 : 객체가 소멸 시 같이 소멸
 *  
 *  2. 클래스 변수 (static 변수)
 *    - static 예약이 붙은 필드를 클래스 변수 또는 static 필드라고 함
 *    - 클래스 변수는 프로그램 실행과 동시에 메모리에 올려놓고 객체들이 공유하면서 사용할 목적으로 선언
 *    - 클래스 변수의 생성과 소멸
 *      생성 시점 : 프로그램이 실행될 때 JVM에 의해서 해당 클래스가 로드될 대 static 메모리 영역에 할당
 *      소멸 시점 : 프로그램이 종료될 때 소멸
 *      
 *  3. 지역 변수
 *    - 클래스 영역 안의 어떤 특정한 구역({})에 선언하는 변수, 해당 구역에서만 사용 가능
 *      (메소드, 제어문(if, for) 등)
 *    - 지역 변수는 접근 제한자를 사용할 수 없음
 *    - 지역 변수의 생성과 소멸
 *      생성 시점 : 특정한 구역 ({}) 실행 시 stack 메모리 영역에 할당
 *      소멸 시점 : 특정한 구역 ({}) 종료 시 소멸 (ex. 메소드 종료 시)
 *      
 *  필드에서 사용 가능한 접근 제한자
 *    1. public
 *      - 모든 패키지에서 아무런 제한 없이 필드에 접근 가능
 *    2. protected
 *      - 같은 패키지에 속하는 클래스이거나 다른 패키지에 속한 클래스이더라도
 *        상속 구조 (부모, 자식간의 관계)에서는 필드에 접근 가능
 *    3. default
 *      - 같은 패키지 내에서는 아무런 제한 없이 필드 접근 가능
 *    4. private
 *      - 동일 패키지이건 다른 패키지이건 상관없이 무조건 해당 클래스에서만 필드 접근 가능
 */

public class A_Field {
	// 필드 선언
	private int field;
	
	// 필드 접근 제한자 테스트
	public String publicField = "public";
	
	protected String protectedField = "protected";
	
	String defaultField = "default"; // default는 쓸 때 접근제한자 필요 없음

	private String privateField = "private";
	
	// 매개 변수가 있는 메소드
	// 매개 변수도 일종의 지역변수로 메소드 영역에서 사용이 가능
	public void test(int num) {
		// 지역 변수
//		public int local; (에러남)
		int local;
		
		// 필드 출력 ▶ 필드는 클래서 전역에서 사용 가능 (ex. 생성자, 메소드)
		// 미 초기화 시 JVM이 기본 값으로 자동 초기화 해 줌
		System.out.println("필드 출력 : " + this.field);
									    // 필드를 생성자, 메소드 등에서 사용할때 this.붙이는 습관 ~
		
		// 지역 변수 출력 ▶ 반드시 초기화 필요 (미 초기화 시 에러)
		local = 5;
		System.out.println("메소드 내에 선언된 지역 변수 출력 : " + local);
		
		// 매개 변수 출력 ▶ 메소드 호출 시 반드시 값이 전달되어 오기 때문에 초기화 안해도 출력 가능 
		System.out.println("매개 변수 출력 : " + num);
		
	}
	
}

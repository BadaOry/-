package com.kh.chap4.constructor;
/*
 *  생성자
 *  [표현법]
 *    [접근 제한자] 클래스명([매개변수]) {
 *      .. 필드를 초기화 하거나, 객체를 사용할 준비..
 *    }
 *    
 *  - 생성자는클래스로부터 객체를 생성할 때 호출되어 객체의 초기화를 담당 (필드 초기화, 객체 사용할 준비)
 *  - 생성자 작성 시 주의사항
 *    ● 생성자 명은 반드시 클래스 명과 동일해야 함
 *    ● 반환형이 존재하지 않음
 *    ● 매개 변수가 있는 생성자를 작성하는 경우, 기본 생성자를 JVM이 자동으로 만들어주지 않음
 *      (즉, 기본 생성자는 항상 기본으로 작성하는 습관을 들여야 함)
 */

public class User {
	private String id;
	
	private String pwd;
	
	private String name;
	
	private int age;
	
	private char gender;
	
	// 기본 생성자 (앞으로 무조건 쓸거임)
	// 객체 생성만을 목적으로 사용
	public User() {
		System.out.println("기본 생성자 호출");
	}
	
	// 생성자를 private로 선언하면 외부에서 절대로 객체를 생성할 수 없음
	// 클래스 내부에서만 생성자 호출 & 객체 생성 할 수 있음 ▶ 디자인에서 활용
//	private User() {
//		
//	}
	
	// 매개변수가 있는 생성자 (아이디, 패스워드, 이름)
	// 객체 생성과 동시에 전달된 값들을 매개변수로 받아서 필드를 초기화하는 목적으로 사용
	public User(String id, String pwd, String name) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}
	
	// 매개변수 5개 다 넣는 생성자
		// 우클릭 > source or ctrl shif s 
		// >> Gernerate Constructor using field
	public User(String id, String pwd, String name, int age, char gender) {
		// 클래스 내에서 문자열 매개 변수 3개를 가지는 생성자가 존재하므로
		// this() 를 통해 생성자 호출
		this(id, pwd, name); // 단, 코드 맨 첫줄에 써야함
//		this.id = id;  ◀ 얘들은 위에서 이미 썼으니, 중복 피하기 위해 위에 있는 생성자를 활용해볼게요
//		this.pwd = pwd; ◀ 얘들은 위에서 이미 썼으니, 중복 피하기 위해 위에 있는 생성자를 활용해볼게요
//		this.name = name; ◀ 얘들은 위에서 이미 썼으니, 중복 피하기 위해 위에 있는 생성자를 활용해볼게요
		this.age = age;
		this.gender = gender;
	}	
		
		
	
	 public String information() {
//		 this(); // 에러, 생성자 안 첫번째 라인에만 있을 수 있음
		return this.id + ", " + this.pwd + ", " + this.name + ", "
				+ this.age + ", " + this.gender ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	
	 
	
	
}

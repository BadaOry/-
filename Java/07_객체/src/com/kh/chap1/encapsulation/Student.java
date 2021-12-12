package com.kh.chap1.encapsulation;

public class Student {
	// 필드부(필드 = 인스턴스 변수 = 멤버 변수)
	// 캡슐화를 적용시키기 위해 접근 제한자를 private로 변경
	private int id = 13; 			// 학번
	private String name;		// 이름
	private int age;			// 나이
	private String address; 	// 주소
	private String gender;		// 성별z	
	private int mathScore;		// 수학 점수
	private int engScore;		// 영어 점수

	// 생성자부 : 생략 시 JVM이 알아서 자동 생성
//	public Student() {
//	}
	
	// 메소드부
	/*
	 *  1) getter 메소드
	 *     - 외부에서 접근이 가능해야 하기 때문에 접근제한자를 public으로 사용
	 *     - 한 필드의 값을 반환해 주는 기능을 하는 메소드
	 *     - 메소드명은 getXXX(필드명) 과 같이 붙여주게 됨 feat 낙타표기법
	 *     - getter 메소드는 필드에 담겨있는 값을 반환만 시켜주기 때문에 
	 *       전달되는 매개값이 없음
	 *     - getter 메소드는 값을 반환해야 하기 때문에
	 *       반환하고자 하는 값의 자료형을 반환형에 써줘야 함
	 *       
	 *  2) setter 메소드
	 *     - 외부에서 접근이 가능해야 하기 때문에 접근 제한자를 public으로 사용
	 *     - 한 필드의 값을 변경·수정하는 기능을 하는 메소드
	 *     - 메소드 명은 setXXX(필드명)과 같이 붙여주게 됨 feat 낙타표기법
	 *     - setter 메소드는 해당 필드의 값을 수정해야 하기 때문에
	 *       전달되는 매개값이 필요함
	 *       (단, 매개값은 수정하려는 필드의 자료형과 동일해야 함)
	 *     - 필드에 대해서 값을 수정하는 메소드이므로 반환값이 없음
	 */
	
	public int getId() {
		
		return this.id; // 어떤 값이라도 넘겨줘야함 ▷ 안그러면 에러
	}
	
	public void setId(int id) { // int id는 로컬변수
		// this : 객체 자신을 의미 (객체 본인의 주소값)
		this.id = id;	// this 안쓰면 id = id 니까 뭔소리여 하게 됨
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address.substring(0,3); 
		// substring : 문자열 자르는 메소드
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		// Application에서 setMatchScore를 수정할 때, - 입력하면 안되는 옵션을 더 걸어볼게요
		if(mathScore <= 0) {
			this.mathScore = 0;
		} else {
		this.mathScore = mathScore;
		}
	}

	public int getEngScore() {
		return engScore;
	}

	public void setEngScore(int engScore) {
		// Application에서 setEngScore를 수정할 때, - 입력하면 안되는 옵션을 더 걸어볼게요
		if(engScore <= 0) {
			this.engScore = 0;
		} else {
			this.engScore = engScore;
		}	
	}
	
	public String information() {
		String info = null;
		
		info = "학번 : " + this.id + ", " + 
			   "이름 : " + this.name + ", " +
			   "수학 점수 : " + this.mathScore + ", " +
			   "영어 점수 : " + this.engScore ;
		
		return info; 
	}	
}

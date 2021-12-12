package com.kh.chap2_class;

/*
 *  클래스에서 사용 가능한 접근 제한자
 * 
 *  1. public
 *    - 동일한 패키지 뿐만 아니라 다른 패키지에서도 아무런 제약 없이 사용 가능
 *    
 *  2. default
 *    - 클래스를 선언할 때 public을 생략했다면 클래스는 default 접근 제한을 가짐
 *    - 동일한 패키지에는 아무런 제약없이 사용 가능
 *      단, 다른 패키지에서는 사용할 수 없도록 제한
 */
public class Person {
	private String name;
	
	private int age;
	
	// Application 클래스에서 Phone 클래스를 사용하기 위한 밑작업
	// : Phone 클래스는 접근 제한이 default라서
	//   동일한 패키지에 존재하는 Person 클래스에서 생성 가능
	private Phone phone = new Phone();

	// getter 역할 하는 메소드 whoAreYou를 만들어서 사실 없어도 됨
//	public String getName() {
//		return name;
//	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// getter 역할 하는 메소드 whoAreYou를 만들어서 사실 없어도 됨
//	public int getAge() {
//		return age;
//	}


	public void setAge(int age) {
		this.age = age;
	}
	

	public void setPhone(String name, String color, String brand) {
		this.phone = new Phone();
		
		phone.setName(name);
		phone.setColor(color);
		phone.setBrand(brand);
	}
	
	public void whoAreYou() { // = public String whoAreYou()
//		 println 써도 됨 ▶ 그리고 return 빼고 메소드를 void로 바꾸면 됨
		System.out.println("안녕하세요. 저는 " + this.name 
				+ "입니다. 나이는 " + this.age + "세 입니다.");
		System.out.println("저는 " + phone.getBrand() + " 브랜드의 "
				           + phone.getName() + "을 가지고 있습니다.");
//		return "안녕하세요. 저는 " + this.name 
//				+ "입니다. 나이는 " + this.age + "세 입니다." 
//				+ "저는 " + phone.getBrand() + "브랜드의 "
//		           + phone.getName() + "을 가지고 있습니다."; 
	}
}

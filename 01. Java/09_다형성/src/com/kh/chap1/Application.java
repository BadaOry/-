package com.kh.chap1;

import com.kh.chap1.polymorphism.Desktop;
import com.kh.chap1.polymorphism.Product;
import com.kh.chap1.polymorphism.SmartPhone;
import com.kh.chap1.polymorphism.Tv;

/*
 * 다형성
 *   - 부모 타입으로부터 파생된 여러 가지 자식 객체를 부모 클래스의 참조변수로 다룰 수 있는 기술
 *   - 즉, 부모 클래스의 참조변수로 다양한 자식 객체를 가질 수 있음
 *   
 * UpCasting
 *   - 자식 타입 ▶ 부모 타입으로 형변환 가능
 *   - 형변환 연산자 생략 가능 (자동 형변환)
 *   - 자식 타입의 멤버(필드, 메소드)에는 접근할 수 없음
 *   
 * 
 * DownCasting
 *   - 부모 타입 ▶ 자식 타입으로 형변환 가능
 *   - 형변환 연산자 생략 불가 (명시적 형변환)
 *   - ((자식타입)부모참조변수).자식메소드();
 * 	
 */

public class Application {
 
	public static void main(String[] args) {
		// 1. 부모 타입의 레퍼런스로 부모 객체를 다루는 경우
		Product product = new Product();
		
		// product 참조변수로 Product에만 접근 가능
		System.out.println(product.information());
		
		// 2. 자식 타입의 레퍼런스로 자식 객체를 다루는 경우
		Desktop desktop = new Desktop();
		
		// desktop 참조변수로 Product, Desktop 멤버에 접근 가능
		System.out.println(desktop.getAllInOne());
		System.out.println(desktop.information());
		
		// 3. 부모 타입의 레퍼런스로 자식 객체를 다루는 경우 (다형성 적용)
		Product product2 = /*(Product)*/ new Desktop();
		
		// product2 참조변수로 Product의 멤버만 접근 가능
		// 하지만 Desktop의 멤버에 접근하고 싶으면 명시적 형변환을 해줘야 함 (강제(명시적) 형변환)
		System.out.println(((Desktop)product2).getAllInOne());
		System.out.println(product2.information()); // 자식 객체가 재정의한 information 출력
		
		// 4. 객체배열과 다형성
		// 다형성을 사용하기 전
		Desktop[] arr1 = new Desktop[2];
		arr1[0] = new Desktop();
		arr1[1]	= new Desktop();
		
		SmartPhone[] arr2 = new SmartPhone[2];
		arr2[0] = new SmartPhone();
		arr2[1] = new SmartPhone();
		
		// 다형성을 적용하면 : 부모 클래스의 레퍼런스로 다양한 자식 객체들을 가리킬 수 있음
		Product[] arr3 = new Product[4]; // 부모 타입의 객체를 담을 수 있는 배열
		arr3[0] = new Desktop();
		arr3[1] = new Desktop();
		arr3[2] = new SmartPhone();
		arr3[3] = new SmartPhone();
		
		// 객체 배열을 사용하는 경우 반복문을 통해서 쉽게 출력 가능
		for(int i = 0; i < arr3.length; i++) {
			
			/*
			 * instanceof 연산자
			 *   - 현재 레퍼런스가 실제로 어떤 클래스 형의 객체의 주소를 참조하고있는지 확인할 떄 사용
			 */
			
			if(arr3[i] instanceof Desktop) { // Desktop를 참조하는 객체니?
				System.out.println("getAllInOne : " + ((Desktop)arr3[i]).getAllInOne());
			} else if(arr3[i] instanceof SmartPhone) { // SmartPhone을 참조하는 객체니?
				System.out.println("getMobileAgency : " + ((SmartPhone)arr3[i]).getMobileAgency());
			}
		
		}
		
		
		Product product3 = new Desktop("삼성", "D-1", "매직스테이션", 1300000, false);
		Product product4 = new SmartPhone("삼성", "S-1", "갤럭시S2", 200000, "KT");
		Product product5 = new Tv("삼성", "T-1", "스마트TV", 2000000,60);
		
		/*
		 * 다형성에 오버라이딩의 개념을 접목하게 되면
		 * 실제 참조하고 있는 클래스의 오버라이딩된 메소드를 찾아가서 실행함
		 * 
		 * 동적 바인딩 : 프로그램이 실행되기 전에 컴파일되면서 모든 메소드는 정적 바인딩 되는데
		 *           컴파일 시 정적 바인딩 된 메소드를
		 *           실행할 당시의 객체 타입을 기준으로 바인딩 되는 것
		 */
		System.out.println(product3.information());  // 동적 바인딩 : Desktop의 information임을 실행시점에 결정
		System.out.println(product4.information());  // 동적 바인딩 : SmartPhone의 information임을 실행시점에 결정
		System.out.println(product5.information());  // 동적 바인딩 : Tv의 information임을 실행시점에 결정
	}
}
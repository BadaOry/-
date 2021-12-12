package com.kh.chap2;

import com.kh.chap2._interface.Basic;
import com.kh.chap2._interface.Desktop;
import com.kh.chap2._interface.Product;
import com.kh.chap2._interface.SmartPhone;
import com.kh.chap2._interface.Tv;
import com.kh.chap2._abstract.BasketBall;
import com.kh.chap2._abstract.FootBall;
import com.kh.chap2._abstract.Sports;

public class Application {

	public static void main(String[] args) {
		/*
		 * 추상 클래스
		 *   [표현법] 
		 *     [접근 제한자] abstract class 클래스명 {...}
		 *     
		 *      - 미완성 클래스로 추상 메소드를 포함한 클래스
		 *      - 미완성 클래스이기 때문에 new 연산자를 통해서 객체 생성 불가능
		 *      - 일반적인 멤버(필드, 메소드)도 포함 가능 (필드 + 메소드 + [추상 메소드])
		 *      - 추상 클래스로 객체 생성은 불가하나
		 *        
		 * 추상 메소드
		 *   [표현법]
		 *     [접근 제한자] abstract 반환형 메소드명{[매개변수, ...]} ;
		 *     
		 *     - 미완성 메소드로 실행부({})가 구현되지 않는 메소드
		 *     - 추상 클래스를 상속받는 자식 클래스에서 반드시 오버라이딩(재정의)해야 함
		 *     - 오버라이딩(재정의) 해주지 않으면 컴파일 에러 발생
		 *     - 자식 클래스에 강제성을 부여할 수 있는 방법
		 *     
		 *  인터페이스
		 *   [표현법] 
		 *     [접근 제한자] interface 인터페이스명 {...}
		 *     
		 *     - 인터페이스는 개발 코드와 객체가 서로 통신하는 접점 역할을 함
		 *     - 인터페이스에서 필드는 무조건 상수 필드임 
		 *       (public static final 생략해도 JVM에서 자동으로 넣음)
		 *     - 인터페이스에서 메소드는 무조건 추상 메소드임 
		 *        (public abstract 생략해도 JVM에서 자동으로 넣음)
		 * 	   - 객체가 구현해야 하는 기능이 있을 때, 
		 *       인터페이스에 추상 메소드를 추가해서 객체가 기능흘 구현하도록 만듦
		 *     - 인터페이스를 "구현"하려면 implements 키워드를 사용해서 "구현"해야 함
		 *     - 인터페이스는 다중 상속(구현)을 허용
		 *     	 - 인터페이스는 필드, 메소드를 가질 수 없고 
		 *         상수 필드와 추상 메소드만 가질 수 있음
		 *       - 즉, 메소드에 대한 정의만 하고 있기 때문에
		 *         메소드가 겹치더라도 최종 구현 부분은 구현 클래스에서 이루어지기 때문에
		 *         다중 상속(구현)이 가능함
		 *     - 추상 클래스와 다르게 좀 더 강한 규칙성, 강제성을 가짐
		 *     
		 *  추상 클래스와 인터페이스
		 *    공통점 
		 *     - 객체 생성은 안되나, 참조 변수로서 사용이 가능 (다형성 적용 가능)
		 *     - 상속(구현)하는 클래스에서 추상 메소드를 재정의하도록 강제함
		 *     
		 *    차이점
		 *     - 추상 클래스는 abstract 키워드로 클래스가 정의되어있고
		 *       클래스 내에 필드, 메소드를 생성 가능하며, 추상 메소드가 포함되어있음
		 *     - 인터페이스는 interface 키워드로 클래스가 정의되어있고
		 *       인터페이스 내에 필드, 메소드를 생성 불가능하며, 
		 *       모든 변수는 상수필드, 모든 메소드는 추상 메소드로 정의됨 
		 *     - 존재 목적의 차이 
		 *       추상 클래스는 추상 클래스를 상속받아 기능을 이용하고, 클래스 확장의 목적을 가짐
		 *       인터페이스는 클래스의 기능 구현을 강제하기 위해 사용되어, 구현을 강제해 구현 객체의 같은 동작을 보장   
		 *       
		 *  extends 와 implements
		 *     - 클래스 간에 상속 관게일 때 : 클래스면 extends 부모클래스명
		 *     - 클래스와 인터페이스의 구현 관계일 때 : 구현클래스면 implements 인터페이스명1 [,인터페이스명2]
		 *     - 인터페이스 간의 상속 관계일 경우 : 인터페이스명 extends 인터페이스명1, [,인터페이스명2]
		 */

		System.out.println("===== 추상 클래스 테스트 ====");
		// 미완성(추상) 클래스라서 객체 생성 불가
		// 추상 클래스는 상속받는 자식 클래스를 만들어서 추상 메소드들을 완성시켜줘야 함
//		Sports sports = new Sports();  // 에러 남
		
		// 추상 클래스는 객체 생성이 안될 뿐, 참조 변수로 사용 가능
		// 다형성이 적용돼서 자식 객체를 생성하여 가리킬 수 있음
//		Sports sports = new FootBall(11);
		
		
		// 객체 배열의 다형성을 이용해, FootBall과 BasketBall 객체의 선수 수와 룰을 출력
		Sports[] sports = new Sports[2];
		
		sports[0] = new FootBall(11);
		sports[1] = new BasketBall(15);
		
		for(Sports s : sports) {
			s.rule();
		}	
		
		System.out.println("===== 인터페이스 테스트 ====");
		// 인터페이스는 객체 생성시 불가능
		// 인터페이스를 구현하는 자식 클래스를 통해서 객체를 생성해야 함
//		Basic basic = new Basic(); // 인터페이스는 객체 생성 불가
		
		// 인터페이스는 객체 생성이 안될 뿐, 참조변수로 사용 가능
		// 다형성이 적용돼서 자식 객체를 생성해서 가리킬 수 있음
		
		Basic desktop = new Desktop("삼성", "D-1", "매직스테이션", 1300000, false);
		Basic phone = new SmartPhone("삼성", "S-1", "갤럭시S2", 200000, "KT");
		Basic tv = new Tv("삼성", "T-1", "스마트TV", 2000000,60);
		
		System.out.println(Basic.NUM);
		
		desktop.turnOn();
		desktop.turnOff();
		
		phone.turnOn();
		phone.turnOff();
		
		tv.turnOn();
		tv.turnOff();
		
		
		
		
		
	}

}

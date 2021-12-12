package com.kh.lambda;

import com.kh.lambda.basic.A_Lambda;
import com.kh.lambda.basic.B_Functional;
import com.kh.lambda.standard.A_Consumer;
import com.kh.lambda.standard.B_Supplier;
import com.kh.lambda.standard.C_Function;
import com.kh.lambda.standard.D_Operator;
import com.kh.lambda.standard.E_Predicate;

public class Application {

	public static void main(String[] args) {
		/*
		 *  람다식 (Lambda Expressions)
		 *  - 자바에서 함수적 프로그래밍을 위해 자바 8 부터 람다식을 지원
		 *  - 람다식은 익명 함수를 생성하기 위한 식으로, 객체 지향 언어보다는 함수 지향 언어에 가까움
		 *  - 람다식은 매개 변수를 가지는 함수와 같은 코드 블록이지만
		 *    런타임 시에는 인터페이스의 익명 구현 객체를 생성함
		 *  - 람다식을 사용하면 코드가 간결해지고,
		 *    컬렉션의 요소를 필터링하거나 매핑하여 원하는 결과를 쉽게 가져올 수 있음
		 *    
		 *  [표현법]
		 * 		([타입 매개변수, ...]) -> {
		 *  	실행문;
		 *  		...
		 *  	};
		 *  
		 *  		
		 *  	- "([타입 매개변수, ...])"는 중괄호 블록 ({})을 실행할 때 필요한 값을 제공하는 역할을 함 (매개변수)
		 *  	- "->" 기호는 매개 변수를 이용해서 중괄호 블록 ({})을 실행한다는 뜻으로 생각하면 됨
		 *  	
		 *  	- ex1 )   (int a) -> {System.out.println(a);};
		 *  	- ex2 )   (a) -> {System.out.println(a);};
		 *                 ▶ 매개변수의 타입을 생략 가능 (런타임 시에 대입되는 값에 따라 자동으로 인식) 
		 *  	- ex3 )   a -> {System.out.println(a);};
		 *                 ▷ 매개변수가 하나, 실행문이 하나일 때 소괄호 (), 중괄호 {} 생략 가능
		 *  	- ex4 )   () -> {System.out.println(a);};
		 *                 ▶ 매개변수가 없다면, 빈 괄호()를 반드시 사용해야 함
		 *  	- ex5 )   (a, b) -> {return a + b;};
		 *                 ▶ 결과 값을 리턴해야 한다면, return 문으로 결과값 지정   
		 *  	- ex6 )   (a, b) -> a + b;
		 *                 ▶ 중괄호({})에 리턴문 하나만 있으면 return 문과 중괄호({}) 생략 가능
		 *        
		 */
		
		
//		Student student = new Student("무닌수", 20, "남자");
//		Student student2 = new Student("무닌수", 20, "남자");
//
//		System.out.println(student);
//		System.out.println(student.equals(student2));
//		System.out.println(student.hashCode());
//		System.out.println(student2.hashCode());
		
		
//		new A_Lambda().method1();
		
//		System.out.println("=======================================");
		
		/*
		 * 타겟 타입 (Target Type)
		 * 	- 람다식은 매개변수를 가진 코드 블록이므로, 자바의 매소드 선언처럼 보여지지만
		 *    자바는 메소드를 단독으로 선언할 수 없고, 클래스의 구성 멤버로 선언
		 *    ▷ 람다식은 단순히 메소드를 선언하는 것이 아니라,
		 *      이 메소드를 가지고 있는 객체를 생성하는 것 
		 *  - 즉, 람다식은 인터페이스 타입의 변수에 대입되고 람다식은 인터페이스의 익명 구현 객체를 생성함
		 *  - 람다식은 대입될 인터페이스의 종류에 따라 작성 방법이 달라지므로,
		 *    람다식이 대입될 인터페이스를 타겟 타입이라고 부름
		 *    
		 * 함수적 인터페이스 (Function Interface)
		 *  - 모든 인터페이스를 타겟 타입으로 사용할 수는 없음
		 *  - 람다식은 하나의 메소드를 정의하기 때문에,
		 *    두 개이상의 추상 메소드가 선언된 인터페이스는 람다식을 이용해서 구현 객체를 생성할 수 없음
		 *  - 즉, 하나의 추상 메소드기 선언된 인터페이스만 람다식의 타겟이 될 수 있는데
		 *       이러한 인터페이스를 함수적 인터페이스라고 함
		 *  - 함수적 인터페이스를 선언할 때 @FunctionalInterface 어노테이션을 붙이면
		 *    두 개 이상의 추상메소드가 선언되지 않도록 컴파일러가 체크해 줌
		 *    ▷ 두 개 이상의 추상 메소드 선언 시 컴파일 에러 발생
		 *  - 물론 @FunctionalInterface 어노테이션이 없다고 해서 함수적 인터페이스가 아니란 뜻은 아니고,
		 *    실수로 두 개 이상의 추상 메소드가 선언되는 것을 방지해줌
		 */
		
//		new B_Functional().method1();
//		new B_Functional().method2();
//		new B_Functional().method3();
//		new B_Functional().method4(30);
		
		/*
		 * 표준 함수적 인터페이스
		 *  - 자바에서 제공되는 표준 API들에서 한 개의 추상 메소드를 가지는 인터페이스들은
		 *    모두 람다식을 이용해서 익명 구현 객체로 표현이 가능함 (Runnable)
		 *  - 자바 8부터 빈번히 사용되는 함수적 인터페이스는 java.util.function 패키지로 제공됨
		 *    - 이 패키지의 목적은 메소드 또는 생성자의 매개 타입으로 사용되어 람다식을 대입할 수 있도록 하기 위함
		 *    - 자바 8 부터 추가되거나 변경된 API는 이 표준 함수적 인터페이스들을 매개 타입으로 많이 사용함
		 *  - 표준 함수적 인터페이스는 용도에 따라 크게 Consumer, Supplier, Function, Operator, Predicate 로 구분됨
		 *    		 
		 *    
		*/
		
		
		System.out.println("=======================================");
		
//		new A_Consumer().method1();
		new B_Supplier().method1();
//		new C_Function().method1();
//		new D_Operator().method1();
//		new E_Predicate().method1();
		
		
		
		
		
		
		
	}

}

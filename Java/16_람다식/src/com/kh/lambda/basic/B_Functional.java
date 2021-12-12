package com.kh.lambda.basic;

import com.kh.lambda.basic.functional.A_FuncInterface;
import com.kh.lambda.basic.functional.B_FuncInterface;
import com.kh.lambda.basic.functional.C_FuncInterface;

public class B_Functional {
	
	private int num = 100;
	
	public void method1() {
		// ▼ 익명 구현 객체 사용 (방법 1)
		//   : 람다식은 인터페이스 타입(여기서는 A_FuncInterface)의 변수에 대입됨
		//     ▷ fi : 익명 구현 객체
		A_FuncInterface fi =  () -> {
			String str = "매개변수와 반환값이 없어요~~";
			
			System.out.println("매개변수와 반환값이 없는 람다식 1");
			
			System.out.println(str);
		};
		
		// ▼ A_FuncInterface 에서 구현한 메소드인 method 호출하여 실행
		fi.method();
		
		// ▼ 익명 구현 객체 사용 (방법 2)
		//   : 실행문이 한 줄이라면 중괄호 {} 를 생략 가능
		fi = () -> System.out.println("매개변수와 반환값이 없는 람다식 2");
		
		
//		// ▼ 위의 익명 구현 객체 만드는 거랑 똑같은 내용의 식
//		fi = new A_Functional() {
//			
//			@Override
//			public void method() {
//				System.out.println("익명 구현 객체를 통한 인터페이스 구현");
//			}
//		};
	}
	
	
	public void method2() {
		B_FuncInterface fi = (int a) -> {
			System.out.println(a * a);
		};
		
		fi.method(5);
		
		// ▼ 매개변수의 자료형을 생략 가능
		//   매개변수가 하나이면 괄호()를 생략 가능
		//   여기서는 한줄이라 중괄호{}도 생략 가능
		fi = a -> System.out.println(a * 5);
		
		fi.method(10);
	}
	
	
	public void method3() {
		int result = 0;
		C_FuncInterface fi = null;
				
		fi = (int x, int y) -> {
			return x + y;
		};
		
		result = fi.method(10, 20);
		
		System.out.println("10 + 20 = " + result);
		
		// ▼ 기호들 최대한 생략해보기
		//    : 매개변수의 자료형, return 구문, 중괄호 {} 생략
		fi =  (x, y) ->  x * y;
		
		System.out.println("10 * 20 = " + fi.method(10, 20));
	}
	
	/*
	 * 람다식의 실행 블록에서는 필드 및 로컬변수(매개변수)를 사용할 수 있음
	 * 인스턴스 변수는 제약사항 없이 사용하지만, 로컬변수(매개변수)는 제약사항이 따름
	 * 람다식에 사용되는 this : 익명 구현 객체의 참조가 아니라, 람다식을 실행하는 객체의 참조
	 * 람다식에서 로컬변수 & 매개변수 이용 시, 변수들이 final 이거나 effectively final 이어야 함
	 * 	- 로컬변수(매개변수)는 람다식에서 읽는 것은 허용하지만, 람다식 내부에서 또는 외부에서 변경할 수 없음
	 *  - 초기화 된 이후에 값이 한번도 변경되지 않는 변수를 effectively final 이라고 함               
	 */
	
	public void method4(int arg) {
		int num = 20;
		
		// ▼ 필드 (인스턴스변수) 사용
//		this.num = 200;  // ▷ 200
		
		// ▼ 람다식의 this는 람다식을 실행하는 객체 (여기서는 B_functional) 의미
		//   : fi 안의 지역 변수에 접근, 변경 가능
		A_FuncInterface fi = () -> {
//			this.num = 200; // ▷ 200
			System.out.println(this.num);
		};
		
		fi.method(); // ▷ 100
		
//		fi = new A_FuncInterface() {
//			
//			@Override
//			public void method() {
//				// ▼ A_FuncInterface 에는 num 이라는 변수가 없으므로 에러
//				System.out.println(this.num);	
//			}
//		};
		
		// ▼ 로컬 변수 사용 (메소드내 선언된 변수, 매개변수 등)
		//   : 값을 바꿀 수 없음
//		num = 200;
//		arg = 300;
		fi = () -> {
			// ▼ 로컬 변수 사용
			//   : 값을 바꿀 수 없음
//			num = 200;
//			arg = 300;
			
			System.out.println(num + ", " + arg);
		};
		
		fi.method();
		 
	}
	
}
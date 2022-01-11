package com.kh.chap5;

import com.kh.chap4.constructor.User;
import com.kh.chap5.method.A_Method;
import com.kh.chap5.method.C_StaticMethod;

public class Application {

	public static void main(String[] args) {
		A_Method methodTest = new A_Method();
		
		// 1. 매개변수가 없고 반환값도 없는 메소드
		methodTest.method1();
		
		// 2. 매개 변수는 없고 반환값은 있는 메소드 호출
		String method2 = methodTest.method2(); // alt shif L : 자동 변수 생성
		System.out.println(method2);
		
		// 3. 매개 변수가 있고 반환값이 없는 메소드 호출
		methodTest.method3(10, 20);
		
		// 4. 매개 변수가 있고 반환값도 있는 메소드 호출
		System.out.println(methodTest.method4(30, 20));
		
		// 5. 매개 변수로 User 객체 전달받는 메소드 호출
		User user = new User("ismoon", "1234", "무닌수");
		
		System.out.println(user.information());
		methodTest.method5(user);
		System.out.println(user.information());
		
		// 6. 매개 변수로 가변인자를 전달받는 메소드
		// 배열 넘기는 방법 #1 : 배열 생성한다음에 넘기거나
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
		methodTest.method6(arr);
//		// 배열 넘기는 방법 #2 : new로 넘기거나
//		methodTest.method6(new int[]{1, 2, 3, 4, 5, 6, 7, 8});  // 바로 넘겨줘야함
		methodTest.method7("문인수", 1, 2, 3, 5, 6, 7);
		methodTest.method7("문인수");
		System.out.printf("%d %s ", 12, "무닌수 \n"); // ctrl 눌러보면 가변인자를 사용하는 메소드임
		
		
		System.out.println("================");
		
		// 1. 매개 변수와 반환값이 없는 메소드 호출
		C_StaticMethod.method1();
		
//		// 정적 멤버는 객체 참조 변수로도 접근 가능하지만, 보통 그러지 않는다
//		new C_StaticMethod().method1();  
		
		// 2. 매개 변수가 없고 반환값이 있는 메소드 호출
		System.out.println("Result : " + C_StaticMethod.method2());
	
		// 3. 매개 변수가 있고 반환값이 없는 메소드 호출
		C_StaticMethod.method3("무닌수");
		
		// 4. 매개 변수가 있고 반환값도 있는 메소드
		int result = 0;
		
		result = C_StaticMethod.method4(1, 2, 3);
		System.out.println(result);
		
		result = C_StaticMethod.method4(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		System.out.println(result);
		
	}

}

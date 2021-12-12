package com.kh.operator.practice;

public class B_InDecrease {
	/*
	 * 증감 연산자
	 *  
	 *  [표현법]
	 *    (증감 연산자)값;  또는  값(증감연산자); 
	 * 
	 *  - 증감 연산자는 피연산자의 값을 1 증가시키거나 1 감소시키는 연산자
	 *  - ++ : 피연산자의 값을 1 증가시키는 연산자 (++값;  또는  값++;)
	 *  - -- : 피연산자의 값을 1 감소히키는 연산자 (--값;  또는  값--;)
	 *  - (증감 연산자)값 : 전위 연산자로 먼저 증감 연산을 수행하고 다른 연산을 수행
	 *  - 값(증감 연산자) : 후위 연산자로 먼저 다른 연산을 수행하고 증감 연산을 수행
	 */
	
	public void method1() {
		int num = 10;
		
		// 전위 연산자 테스트
		System.out.println("전위 연산자 적용 전 num의 값 : " + num);  // 10
		System.out.println("1회 수행 후 값 : " + ++num);  			// 11 (num = num + 1과 같은 결과)
		System.out.println("2회 수행 후 값 : " + ++num);  			// 12
		System.out.println("3회 수행 후 값 : " + ++num);  			// 13
		System.out.println("전위 연산자 적용 후 num의 값 : " + num); // 11
		System.out.println();
		
		// 후위 연산자 테스트
		System.out.println("후위 연산자 적용 전 num의 값 : " + num);  // 13
		System.out.println("1회 수행 후 값 : " + num++);  		  // 13 -> 14  : 앞의 문자열 더하기 연산자 후 ++ 진행
		System.out.println("2회 수행 후 값 : " + num++);  		  // 14 -> 15
		System.out.println("3회 수행 후 값 : " + num++);  		  // 15 -> 16
		System.out.println("후위 연산자 적용 후 num의 값 : " + num);  // 16
	}
	
	public void method2() {
		int num = 20;
		int result = num++ * 3;  // result 60, num 21
		int num1= 10, num2 = 20, num3 = 30;  // 이렇게 써도 되지만 잘 안씀
		
		System.out.println("result : " + result);
		System.out.println("후위 연산 후 num : " + num +"\n");
		
		System.out.println(num1++);  // num1 : 프린트는 10, 실행 후 11
		System.out.println((++num1) + (num2++));  // num1은 12, 출력은 12+20=32, num2는 21
		System.out.println((num1++) + (--num2) + (--num3) + "\n");  // num2는 20, num3은 29, 출력은 12+20+29=61, num1은 13
		
		System.out.println(num1);
		System.out.println(num2);
		System.out.println(num3);
	}
}

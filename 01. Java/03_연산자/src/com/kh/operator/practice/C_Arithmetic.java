package com.kh.operator.practice;

public class C_Arithmetic {
	/*
	 *  산술 연산자
	 *    + 더하기
	 *    - 뺴기
	 *    * 곱하기
	 *    / 나누기
	 *    % 나누기 나머지
	 * 
	 */
	
	public void method1() {
		int num1 = 10;
		int num2 = 3;
		double dNum1 = 35.0;
		double dNum2 = 10.0;
		
		System.out.println("====== 정수형의 사칙연산 ======");
		System.out.println("num1 + num2 = " + (num1 + num2)); 
		System.out.println("num1 - num2 = " + (num1 - num2)); 
		System.out.println("num1 * num2 = " + (num1 * num2)); 
//		System.out.println("num1 / num2 = " + ((double)num1 / num2));  // num1은 강제형변환, 따라서 num2가 double로 자동형변환 됨
		System.out.printf("num1 / num2 = %.1f\n", ((double)num1 / num2));  // 소수점 첫째 자리까지만 출력
		System.out.println("num1 % num2 = " + (num1 % num2));
		System.out.println();
		
		
		System.out.println("====== 실수형의 사칙연산 ======");
		System.out.println("dNum1 + dNum2 = " + (dNum1 + dNum2));  // 덧셈 괄호 안넣으면 문자열로 더해짐
		System.out.println("dNum1 - dNum2 = " + (dNum1 - dNum2));  // 덧셈 괄호 안넣으면 문자열로 더해짐
		System.out.println("dNum1 * dNum2 = " + (dNum1 * dNum2));  // 덧셈 괄호 안넣으면 문자열로 더해짐
		System.out.println("dNum1 / dNum2 = " + (dNum1 / dNum2));  // 덧셈 괄호 안넣으면 문자열로 더해짐
		System.out.println("dNum1 % dNum2 = " + (dNum1 % dNum2));  // 덧셈 괄호 안넣으면 문자열로 더해짐	
	
		// 참고
//		System.out.println(5 / 0); // 0으로 나눌 수 없음, ArithmeticException
		System.out.println(5 / 0.0); // Infinity, 무한대 값이 나온다
		System.out.println((5 / 0.0) + 2); // 무한대에 2 더해도 무한대
		System.out.println(5 % 0.0); // NaN(Not a Number), 숫자가 아니라고 나옴
		System.out.println((5 % 0.0) + 2); // 숫자 아닌값에 숫자 더해도 숫자 아님
		System.out.println(Double.isInfinite(5/0.0)); // 연산 결과가 무한대인지 알아보는 방법
		System.out.println(Double.isNaN(5%0.0)); // 연산 결과가 무한대인지 알아보는 방법
	
	}
}

package com.kh.operator.practice;

public class F_Compound {
	/*
	 * 복합 대입 연산자
	 *  - 다른 연산자와 대입 연산자를 함께 사용하는 연산자로 내부적 연산 처리 속도가 빠르므로 사용 권장
	 *    (메모리에서 직접 연산 수행)
	 *  - 증감 연산자(++, --)와 비슷해 보이지만 증감 연산자는 값을 1씩 증감시키고
	 *    복합 대입 연산자는 원하는 값만큼 증감시킬 수 있음 
	 *  
	 *  +=, -=, *=, /=, %=
	 *  
	 *  a = a + 3;  =>  a += 3;
	 *  a = a - 3;  =>  a -= 3;
	 *  a = a * 3;  =>  a *= 3;
	 *  a = a / 3;  =>  a /= 3;
	 *  a = a % 3;  =>  a %= 3;
	 */
	
	public void method1() {
		int num = 12;
		String str = "Hello";
		
		str += "\tWorld!!"; // str = str + "\tWorld!!";
		
		System.out.println(str);
		
		// 문자열에 연산 하면 값이 문자열이 됨
		// 1이 문자열이 바뀌어서 문자로 붙음
		str += "1"; // str = str +1;  
		System.out.println(str);
		
		num += 3; // num = num + 3;
		System.out.println("num += 3 는(은) " + num);
		
		num -= 5; // num = num - 5;
		System.out.println("num -= 5 는(은) " + num);
		
		num *= 6; // num = num * 6;
		System.out.println("num *= 6 는(은) " + num);
		
		num /= 3; // num = num / 3;
		System.out.println("num /= 3 는(은) " + num);
		
		num %= 4; // num = num % 4;
		System.out.println("num %= 4 는(은) " + num);
		
		
		
	}

}

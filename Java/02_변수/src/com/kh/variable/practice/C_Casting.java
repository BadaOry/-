package com.kh.variable.practice;

public class C_Casting {
	/* 
	 * 형 변환 (boolean 제외)
	 * 
	 * 	* 컴퓨터에서의 값의 처리 규칙
	 *  1. 대입 연산자를 기준으로 왼쪽과 오른쪽은 같은 자료형이어야 함
	 *     즉, 같은 자료형에 해당하는 값만 대입이 가능하고 다른 자료형의 값을 대입하고자 한다면 형 변환이 필수!
	 *     자료형 변수명 = (자료형) 값;
	 *     
	 *  2. 같은 자료형 끼리만 연산이 가능 (연산 결과도 같은 자료형으로 나옴)
	 *  
	 *  * 형 변환의 종류
	 *  1. 자동 형변황(묵시적 형변환)
	 *     - 자동으로 형 변환이 이루어지기 때문에 개발자가 형 변환을 시켜줄 필요가 없음
	 *     - 데이터 표현 범위가 작은 자료형을 큰 자료형으로 변환
	 */
	
	public void autoCasting() {
		short s = 12;
		int i = s;		// short -> int 자동 형변환 됨
		long l = i; 	// int -> long 자동 형변환 됨
		double d = l;	// long -> double 자동 형변환 됨 (12.0)
		double result = 12 + 3.3;	// int + double 인 상황 -> 12를 12.0으로 바꿈 
		long result2 = 30 + 30;		// 30 + 30 = 60이고, 결과값 60은 int  -> 60을 long으로 캐스팅하여 저장(60L)
		long result3 = 30 + 30L; 	// int 30이 30L으로 바뀜 -> 30L + 30L = 60L
		float f = 10000000000000L; 	// float이 long보다 표현 가능한 수의 범위가 더 크기 때문에 자동 형변환이 가능
		

		System.out.println("s : " + s);
		System.out.println("i : " + i);
		System.out.println("l : " + l);
		System.out.println("d : " + d);
		System.out.println("result : " + result);
		System.out.println("result2 : " + result2);
		System.out.println("result3 : " + result3);
		System.out.println("f : " + f);
		
		i = '문'; // 문자의 유니코드 값 대입
		char ch = 65; // 숫자 값을 해당 숫자와 일치하는 문자를 유니코드 표에서 찾아 대입
//		char ch2 = -65; // char에 음수는 저장 불가 : 유니코드 범위가 0 ~ 65,535까지기 때문
		
		System.out.println("i : " + i);
		System.out.println("ch : " + ch);
		
		byte b1 = 1;
		byte b2 = 10;
		byte result4 = (byte)(b1 + b2); 	// 정수형의 경우 byte, short연산은 값을 무조건 int로 변환하여 게산 (int가 정수 기본형이라서)
		
		System.out.println("result4 : " + result4);	
	}
	
	/*
	 *  2. 강제 형변환(명시적 형변환)
	 *     [표현법]
	 *       (바꿀 자료형) 값;
	 *       
	 *     - 범위가 큰 크기의 자료형을 작은 크기의 자료형으로 변환하려면 형 변환 연산자를 사용해야 함
	 *     - (바꿀 자료형)을 cast 연산자, 즉 형 변환 연산자라고 함
	 *     - 강제 형변환의 경우 데이터의 손실이 발생할 수 있음
	 */
	public void Casting() {
		double d = 4.123;
//		float f = d; 	// 에러 발생 : 큰범위 > 작은 범위 -> 손실 생김
		float f = (float) d;
//		int i = d;	// 에러 발생 : 큰범위 > 작은 범위 -> 손실 생김
		int i = (int) d;
		int iSum = 0;
		double dSum = 0.0;
		
		System.out.println("d : " + d);
		System.out.println("f : " + f);
		System.out.println("i : " + i);
		
		// iSum = i + d => 15
		i = 10;
		d = 5.76;
		/*내가 푼거
		iSum = (int) (i+d);
		System.out.println("iSum : " + iSum);
		*/
		
//		// 방법 1. 수행 결과를 int로 강제 형변환
////		iSum = i + d;  // i가 double형태로 바뀜 -> 10.0 + 5.76 = 15.76 -> 15
//		iSum = (int) (i + d);
	
		// 방법 2. double 값을 int로 강제 형변환 후 계산
		iSum = i + (int) d;		// d를 int로 바꿈 -> 10 + 5 = 15 -> 15
		
		// 방법 3. double로 값을 받는다.
		dSum = i + d;
		
		System.out.println("iSum : " + iSum);
		System.out.println("dSum : " + dSum);
		
		
		// 데이터 손실 메소드 
		int iNum = 290;
		byte bNum = (byte) iNum;
		
		System.out.println();
		System.out.println("iNum : " + iNum);
		System.out.println("bNum : " + bNum);
		
		
		
	}
	
	
	
}

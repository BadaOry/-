package com.kh.variable.practice;

public class A_Variable {
	
	public void printValue() { // printValue라는 이름의 메소드 만들었당
		// 변수를 사용하지 않으면
		System.out.println(2 * 3.141592653589793 * 10);
		System.out.println(3.141592653589793 * 10 * 10);
		System.out.println(3.141592653589793 * 10 * 10 * 20);
		System.out.println(4 * 3.141592653589793 * 10 * 10);
		System.out.println( ); //콘솔에서 줄바꿈을 위한 호출
		
		// 변수를 사용하면
		double pi = 3.141592653589793;
		int r = 10;
		int h = 20;
		
		System.out.println(2 * pi * r);
		System.out.println(pi * r * r);
		System.out.println(pi * r * r * h);
		System.out.println(4 * pi * r * r);
	}
	
	public void declare() {
		/*
		 * 변수의 선언
		 *   [표현법]
		 *      자료형 변수명;
		 *      
		 *    - 자료형은 어떤 값을 저장할지, 어떤 크기의 메모리를 할당 받을지에 대한 정보
		 *    - 변수명은 변수의 이름으로 값을 읽고/쓰고/지우고/변경하는 작업을 할 때 사용
		 */
		
		// 논리형
		boolean isTrue; // 1byte의 논리 값을 담을 수 있는 변수 선언
		
		// 정수형 
		byte bNum; 		// 1byte의 정수를 담을 수 있는 변수 선언
		short sNum; 	// 2byte의 정수를 담을 수 있는 변수 선언
		int iNum; 		// 4byte의 정수를 담을 수 있는 변수 선언
		long lNum;		// 8byte의 정수를 담을 수 있는 변수 선언
		
		// 실수형
		float fNum;		// 4byte의 실수를 담을 수 있는 변수 선언
		double dNum;	// 8byte의 실수를 담을 수 있는 변수 선언
		
		// 문자형
//		char ch;		// 2byte의 문자를 담을 수 있는 변수 선언 (유니코드는 대부분 2byte로 표현)
//		char ch = '\u0000'; 유니코드 형태로 초기화 (빈 문자)
		char ch = 'a'; 
		
		// 문자열
//		String name;		// 문자열을 가리킬 수 있는 참조형 변수 선언
//		String name = new String("문인수");
		String name = "문인수";  // 주소값을 name에 저장
	
		/* 
		 * 변수의 초기화
		 *   [표현식] 
		 *      변수명 = 값; (= 같다(X) 변수명에 값을 대입한다(O))
		 *   = 는 오른쪽의 데이터(값)를 왼쪽의 변수에 대입(저장)하는 연산자
		*/
		
		// 선언 후 초기화
		isTrue = false;			// true & false값만 저장 가능
		
		// 로컬변수 생성 후 초기화 되지 않은 경우, 사용하려고 하면 에러 발생
//		System.out.println("bNum : " + bNum); 
		
		bNum = 11;
		sNum = 12;
		iNum = 300;
		// 정수형 리터럴은 기본적으로 int 형으로 컴파일 됨
		// long 타입 리터럴은 숫자 뒤에 L 또는 l을 입력해야 함
		lNum = 3456345212343L;
		
		// 실수형 리터럴은 기본적으로 double 형으로 컴파일 됨
		// float 타입 리터럴은 숫자 뒤에 F 또는 f를 입력해야 함
		fNum = 3.14F; 
		// double 타입의 리터럴에서 숫자 뒤에 D 또는 d는 생략이 가능
//		dNum = 1.23425235D;
		dNum = 1.23425235;
		
		System.out.println("isTrue : " + isTrue);
		System.out.println("bNum : " + bNum);
		System.out.println("sNum : " + sNum);
		System.out.println("iNum : " + iNum);
		System.out.println("lNum : " + lNum);
		System.out.println("fNum : " + fNum);
		System.out.println("dNum : " + dNum);
		System.out.println("ch : " + ch);
		System.out.println("name : " + name);
		
		// 변수의 명명 규칙
		// 1) 대소문자 구분
		int number;
//		String number; 	// 자료형이 달라도 변수명이 같으면 에러 발생
		int NUMBER;
		int NumBer;
		
		// 2) 예약어는 사용할 수 없음 (변수, 클래스, 메소드 등등)
//		int double;
//		int void;
//		int public;
		
		// 3) 숫자로 시작하면 안됨 (정수형 리터럴으로 인식에 헷갈리기 때문)
//		int 1age;
		int age1;
		
		// 4) 특수 문자는 '_', '$' 만 사용 가능
		int _age;
		int a$g$e;
//		int @age;
//		int %age;
		
	}

	public void overflow() {
		// byte 자료형의 저장 범위 : -128 ~ 127
		byte bNum = 127;
		byte result = (byte)(bNum + 1);
		
		System.out.println("bNum : " + bNum);
		System.out.println("result : " + result);
		System.out.println("byte의 표현 범위 : " + Byte.MIN_VALUE + " ~ " + Byte.MAX_VALUE);
		System.out.println("short의 표현 범위 : " + Short.MIN_VALUE + " ~ " + Short.MAX_VALUE);
		System.out.println("int의 표현 범위 : " + Integer.MIN_VALUE + " ~ " + Integer.MAX_VALUE);
		System.out.println("long의 표현 범위 : " + Long.MIN_VALUE + " ~ " + Long.MAX_VALUE);
		System.out.println("float의 표현 범위 : " + Float.MIN_VALUE + " ~ " + Float.MAX_VALUE);
		System.out.println("double의 표현 범위 : " + Double.MIN_VALUE + " ~ " + Double.MAX_VALUE);
		System.out.println("char의 표현 범위 : " + (int)Character.MIN_VALUE + " ~ " + (int)Character.MAX_VALUE);

		 
	}

	public void constant() {
		/*
		 * 상수
		 *   [표현법] 
		 *     final 자료형 변수명;
		 *     
		 *   - 변하지 않는 값으로 특정 변수의 값이 변경되는 걸 원하지 않을 때 사용
		 *   - 초기화 이후에는 값을 변경할 수 없음
		 */
		
		int age;
		final int AGE;
		
		age = 20;
		AGE = 30;
		
		System.out.println("age : " + age);
		System.out.println("AGE : " + AGE);
		
		age = 25;
//		AGE = 35; 	// 에러남. 당연함. 로컬상수 AGE는 이미 assigned 됐는걸.
		
	}
}

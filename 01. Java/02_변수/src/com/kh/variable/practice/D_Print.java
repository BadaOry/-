package com.kh.variable.practice;

public class D_Print {
	public void printMethod() {
		int iNum = 10;
		int iNum2 = 20;

		// System.out.println("포맷", 출력하고자 하는 값1, 값2, 값3, ..);
		// 출력하고자 하는 값들을 제시된 포맷(서식)에 맞춰서 출력 진행, 줄바꿈 안함
		//
		System.out.printf("%d\n", iNum);
		System.out.printf("%5d\n", iNum);  	// 서식(자리)가 하나여도 값이 두개면 에러가 나진 않음
		System.out.printf("%-5d\n", iNum, iNum2);  	// 서식(자리)가 하나여도 값이 두개면 에러가 나진 않음
//		System.out.printf("%d %d\n", iNum);			// 두번쨰 서식의 값이 없어서 에러 -> 서식 지정자에 해당하는 값들이 모두 존재해야 함
		System.out.printf("%d + %d = %d\n", iNum, iNum2, (iNum + iNum2));
	
		System.out.println("=======================");
		
		float fNum = 3.14159272f;
		double dNum = 4.56;
		
		// %f : 실수 값을 소수점 아래 여섯째 자리까지 보여줌
		//  	단, 범위를 넘어가면 반올림되고 소수점 아래 값이 없으면 0으로 채워줌
		System.out.printf("%f \t %f \n", fNum, dNum);
		System.out.printf("%.3f \t %.3f \n", fNum, dNum);
		
		System.out.println("=======================");
		
		char ch = 'A';
		String str = "\"Hello\"";	// 문자열로의 "" 는 역슬래쉬를 붙여서 사용
		System.out.printf("%c %s\n", ch, str); 	
		System.out.printf("%s %s\n", ch, str); 	// %s는 char타입도 표현 가능
		System.out.printf("%C %S\n", ch, str);	// 대문자로도 표현 가능
		
		
	}
}

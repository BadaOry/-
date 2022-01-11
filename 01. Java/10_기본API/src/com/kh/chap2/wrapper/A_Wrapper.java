package com.kh.chap2.wrapper;
/*
 * Wrapper 클래스
 *   - 기본 자료형을 객체로 포장해 주는 클래스
 *   - 기본 자료형으로 사용해도 되지만, 
 *      프로그램에 따라서 해당 기본 타입을 객체로 취급해 처리해야 하는 경우 사용
 *   - 기본 자료형을 Wrapper 클래스로 포장해 주는것을 Boxing
 *     Wrapper 클래스를 기본 자료형으로 변경해주는 것을 Unboxing
 */
public class A_Wrapper {
//	@Deprecated
	
	public void method1() {
		// Boxing : 기본 자료형 ▷ Wrapper
		int iNum = 10;
		double dNum = 3.14;
		double dNum2 = 3.15;
		
		// 1. 객체 생성을 통한 방법 (Deprecated (비권장))
		Integer integer = new Integer(iNum);
		Double double1 = new Double(dNum);
		Double double2 = new Double(dNum2);
		
		System.out.println(integer);
		System.out.println(double1);
		System.out.println(double2);
		
		System.out.println(double1 == double2);
		System.out.println(double1.equals(double2));
		// compareTo() : 두 값을 비교해 앞이 크면 1, 뒤가 크면 -1, 같으면 0 반환
		System.out.println(double1.compareTo(double2)); 
		System.out.println();
		
		// 2. 객체를 직접 생성하지 않고 정적 메소드를 통한 방법
		Integer integer2 = Integer.valueOf(3);
		Double double3 = Double.valueOf("1.11"); // 문자열을 숫자화 함. 단, 문자열에 숫자가 아닌 값 있으면 오류남
		
		System.out.println(integer2);
		System.out.println(double3);
		System.out.println();
		
		// 3. AutoBoxing (Java 1.5이상부터 제공) : 기본 자료형 ▷ Wrapper 
		Integer integer3 = 5;  // 같은 타입만 Auto Boxing이 가능
		Double double4 = 3.555;
		
		// 4. UnBoxing : Wrapper ▷ 기본 자료형\
		int iNum2 = integer2.intValue();
		int iNum3 = integer3.intValue();
		
		System.out.println(iNum2 + iNum3);
		
		int iNum4 = integer3; // Auto UnBoxing
		
		System.out.println(integer2 + integer3);  // integer2는 3, integer3은 5로 계산됨
	
//		System.out.printf("%s, %d", "HI", 4);
	}

	public void method2() {
		String str1 = "10";
		String str2 = "3.14";
		
		System.out.println(str1 + str2);
		
		// 1. 문자열을 기본 자료형으로 변경 
		// (Wrapper 클래스에서 제공하는 parseXXX() 정적 메소드 사용)
		int iNum = Integer.parseInt(str1);
		double dNum = Double.parseDouble(str2);
		
		System.out.println(iNum + dNum);
		
		// 에러남 : 더블을 어떻게 인트로 바꾸니 
//		int iNum1 = Integer.parseInt(str2);
//		double dNum1 = Double.parseDouble(str1);
//		
//		System.out.println(iNum1 + dNum1);
		
		
		// 2. 기본 자료형을 문자열로 변경
		// String의 valueOf() 메소드를 사용하는 방법
		String str3 = String.valueOf(iNum);
		String str4 = String.valueOf(dNum);
		
		System.out.println(str3);
		System.out.println(str4);
		
		// Wrapper 클래스에서 제공하는 valueOf().toString() 메소드 사용하는 방법
		String str5 = Integer.valueOf(iNum).toString();
		String str6 = Double.valueOf(dNum).toString();
		
		System.out.println(str5);
		System.out.println(str6);	
	}
}

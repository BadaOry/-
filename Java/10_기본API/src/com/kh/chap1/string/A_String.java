package com.kh.chap1.string;

import java.util.StringTokenizer;

public class A_String {
	
	public void method1() {
		// 문자열 리터럴과 new 문으로 생성된 문자열 비교
		
		// str1, str2와 같이 리터럴로 값을 초기화 하게 되면
		// StringPool이라는 Heap메모리의 영역에 값이 저장 됨
		//    - StringPool 영역에는 중복되는 값이 존재할 수 없음
		//    - 만약 기존에 존재하던 값이 있을 경우, 존재하는 값의 주소값을 전달
		String str1 = "hello";
		String str2 = "hello";
		String str3 = new String("hello");
		String str4 = new String("hello");
		
		// 참조변수라 주소를 비교할거임
		System.out.println("str1 == str2 ? ▷ " + (str1 == str2));
		System.out.println("str1 == str3 ? ▷ " + (str1 == str3));
		System.out.println("str3 == str4 ? ▷ " + (str3 == str4));
	
		// equals() ▷ String 클래스에서 이미 오바리이딩 되어있는 메소드
		// : 가진 문자열이 동일한 경우 true 리턴
		System.out.println("str1.equals(str2) ? ▷ " + str1.equals(str2));
		System.out.println("str2.equals(str3) ? ▷ " + str2.equals(str3));
		System.out.println("str3.equals(str4) ? ▷ " + str2.equals(str4));
		
		// hashCode() ▷ String 클래스에서 이미 오버라이딩 되어있는 메소드
		System.out.println("str1.hashCode() : " + str1.hashCode());
		System.out.println("str2.hashCode() : " + str2.hashCode());
		System.out.println("str3.hashCode() : " + str3.hashCode());
		System.out.println("str4.hashCode() : " + str4.hashCode());
		
		// 문자열 객체의 실제 주소값에 대해 할고싶으면 
		// System.identityHashCode 정적 메소드 사용으로 확인 가능
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		System.out.println(System.identityHashCode(str3));
		System.out.println(System.identityHashCode(str4));
		
		// toString() ▷ String 클래스에서 이미 오바리이딩 되어있는 메소드
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str4);
		
		
	}

	public void method2() {
		//String 클래스에서 제공하는 메소드 사용
		String str = "Hello World!";
		
		// 1. charAt(int index) : 전달받은 index 위치에 있는 하나의 문자만 추출해서 리턴
		char ch = str.charAt(3);
		System.out.println(ch);
		System.out.println();
		
		// 2. concat(String str) 
		//	: 전달받은 str과 원본 문자열을 하나로 합친 새로운 문자열을 생성해서 리턴
		String str2 = str.concat("!!!!");
		System.out.println(str2);
		System.out.println(str);
		System.out.println();
		
		// 3. substring(int beginIndex) 
		//      : 문자열의 beginIndex 위치에서부터 끝까지의 새로운 문자열을 생성해서 리턴
		//    substring(int beginIndex, int endIndex)
		//      : 문자열의 beginIndex 위치에서부터 endIndex-1까지의 새로운 문자열을 생성해서 리턴
		String str3 = str.substring(6);
		String str4 = str.substring(2, 6);
		
		System.out.println(str3);
		System.out.println(str4); // 공백도 포함되어 있음
		System.out.println(str);
		System.out.println();
		
		// 4. replace(char oldChar, char newChar) 
		//      : 문자열의 old문자를 new 문제로 변경된 새로운 문자열을 생성해서 리턴
		String str5 = str.replace('l', 'c');
		
		System.out.println(str5);
		System.out.println(str);
		System.out.println();
		
		
		// 5. toUpperCaseE(0 / toLowerCase() 
		// : 문자열을 모두 대/소문자로 변경한 새로운 문자열을 생성해서 리턴
		System.out.println(str.toUpperCase());
		System.out.println(str.toLowerCase());
		System.out.println(str);
		System.out.println();
		
		// 6. trim() : 문자열의 앞뒤 공백을 제거한 새로운 문자열을 생성하여 리턴
		str = " java ";
		System.out.println(str.trim());
		System.out.println(str);
		System.out.println();
		
		// 7. toCharArary() : 문자열의 문자들을 문자 배열에 담아서 해당 배열의 주소값 리턴
		str = "Hello";
		char[] arr = str.toCharArray();
		
		System.out.println(arr[0]);  // H 출력
//		for(char c : arr) {
//			System.out.print(c + " ");
//		}
		System.out.println();
		
		// 8. valueOf([기본자료형, 문자 배열, 객체])
		// : 입력받은 값들을 문자열 변경해서 리턴
		String str6 = String.valueOf(true); // 정적 메소드 호출
		String str7 = String.valueOf(arr); // 정적 메소드 호출
		String str8 = String.valueOf(3.141592F); // 정적 메소드 호출
		
		System.out.println(str6);
		System.out.println(str7);
		System.out.println(str8);	
	}

	public void method3() {
		// 구분자를 이용하여 문자열 분리
		String str = "Java,Oracle,JDBC,HTML,CSS,Spring";
		
		// 1. String 클래스와 split 메소드를 이용하는 방법
		//    split(String regex) : 입력받은 구분자로 문자열을 분리해서 문자열의 배열로 담아 리턴
		
		String[] strArr = str.split(",");
		System.out.println("strArr.length : " + strArr.length);
		
		for(String s : strArr) {
			System.out.println(s);
		}
		
		System.out.println();
		
		// 2. StringTokenizer 객체를 이용하는 방법
		StringTokenizer st = new StringTokenizer(str, ",");
		System.out.println("분리 후 문자열의 개수 : " + st.countTokens());
		
		// for문을 통한 출력 방법
//		int num = st.countTokens();
//		
//		for(int i = 0; i < st.countTokens(); i++) { 
//			System.out.println(st.nextToken());
//		}
//		
		// while문을 통한 출력 방법
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		
	}

}

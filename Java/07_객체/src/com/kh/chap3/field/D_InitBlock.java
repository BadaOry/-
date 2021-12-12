package com.kh.chap3.field;

/*
 * 필드의 초기화 순서
 *   1. 필드의 초기화 순서
 *     1) JVM이 정한 기본 값으로 초기화 
 *     2) 명시적 초기화
 *     3) 인스턴스 블록을 이용한 초기화
 *     4) 생성자를 이용한 초기화 
 *     
 *   2. 클래스 변수의 초기화 순서
 *     1) JVM이 정한 기본 값으로 초기화
 *     2) 명시적 초기화
 *     3) static 블록을 이용한 초기화
 */
public class D_InitBlock {
	// 1. JVM이 정한 기본 값으로 초기화 
	private String name;
	private static int age;
	
	
	// 2. 명시적 초기화
	private String phoneName = "아이폰SE";
	private int price = 0;
	private static String brand = "애플";
	
	// 3. 초기화 블록을 이용한 초기화
	// 필드(인스턴스 블록)를 초기화 시키는 블럭으로 객체 생성 시 마다 실행되고 초기화 함
	{
		phoneName = "아이폰12미니";
		price = 5000000;
		
		/* 인스턴스 블록에서는 클래스 변수도 초기화 할 수 있음
		 * 하지만, 클래스 변수는 프로그램 시작 시에 초기화 되기 때문에 
		 * 객체 생성 이후에 값을 초기화 하는 인스턴스 블럭의 초기화 값으로 덮어쓰게 됨
		 */
//		brand = "생성";
	}
	
	// 클래스 변수를 초기화 시키는 블록으로 프로그램 시작 시 단 한번만 실행되고 초기화 됨
	 static {
		 age = 20; 
		 
//		 name = "홍길동"; // 에러남. 당연함. 객체 생성 전이기 때문.
	 }
	
	// 4. 생성자를 이용한 초기화
	public D_InitBlock() {
		phoneName = "아이폰13";
	}

	public String getName() {
		return name;
	}
	

	public static int getAge() {
		return age;
	}

	public String getPhoneName() {
		return phoneName;
	}

	public int getPrice() {
		return price;
	}

	public static String getBrand() {
		return brand;
	}
	
	
	
	
	
	
}

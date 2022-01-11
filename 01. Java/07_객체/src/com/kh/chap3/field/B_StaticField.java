package com.kh.chap3.field;

public class B_StaticField {
	// static 키워드가 붙은 필드는 프로그램 실행하자 마자 static 메모리 영역에 할당 됨
	// static 필드는 객체로 생성하지 않아도 클래스 명을 통해 접근 가능
	// 즉, 객체에 소속된 멤버가 아니라 클래스에 소속된 멤버이므로 클래스 변수라고 함
	public static String pubSta = "public static 필드";
	private static String priSta = "private static 필드";
	
	// static 필드에 대한 getter & setter 메소드 또한 static 키워드를 붙여서 만들어 주어야 함
	public static String getPriSta() {
		return priSta;
	}
	public static void setPriSta(String priSta) {
		// 객체를 생성하지 않고 클래스명으로 접근, 즉 객체를 생성하지 않아서 this는 존재하지 않음
//		this.priSta = priSta;
		B_StaticField.priSta = priSta;
	}
	
	
}

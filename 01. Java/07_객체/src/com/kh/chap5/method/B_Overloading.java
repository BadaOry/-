package com.kh.chap5.method;

public class B_Overloading {
	/*
	 *  메소드 오버로딩
	 *   - 한 클래스 내에 같은 이름의 메소드를 여러 개 정의하는 것 
	 *   - 메소드 오버로딩은 매개별수의 자료형 개수와 순서가 다르게 작성되어야 함
	 *   - 단, 매개 변수명, 접근 제한자, 반환형은 메소드 오버로딩에 영향을 주지 않음
	 */

	public void test() {	
	}
	
	public void test(int a) {	
	}
	
	public void test(int a, String s) {	
	}
	
	public void test(int a, int b) {	
	}
	
	/* 
	 * 에러 발생
	 *   - 매개 변수 이름과 상관없이 자료형의 개수와 순서가 같아서 에러 발생
	 *   - 즉, 매개 변수의 자료형의 개수와 순서가 다르게 책정되어야 함
	 */
//	public void test (int c, int d) {
//	}
	
	public void test(int a, int b, String s) {
	}
	
	public void test(int a, String s, int b) {
		
	}
	/* 
	 * 에러 발생
	 *   - 반환형이 다르다고 해서 오버로딩이 적용되는건 아님
	 *   - 메소드 호출 시점에 매개변수가 동일하므로, 에러가 발생
	 *   - 즉, 반환형과 상관없이 매개 변수의 자료형의 개수와 순서가 다르게 작성되어야 함
	 */
//	public int test(int a, int b, String s) {
//	}
	
	/*
	 * 에러 발생
	 *   - 접근 제한자가 다르다고 오버로딩이 적용되지 않음
	 *   - 메소드 호출 시점에 매개 변수가 동일하므로, 에러가 발생
	 *   - 즉, 접근 제한자와 상관없이 매개 변수의 자료형의 개수와 순서가 다르게 작성되어야 함
	 */
//	private void test(int a, int b, String s) {
//	}
	

	
}

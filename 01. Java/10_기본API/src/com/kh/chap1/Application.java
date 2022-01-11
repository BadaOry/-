package com.kh.chap1;

import com.kh.chap1.string.A_String;
import com.kh.chap1.string.B_StringBuilder;

public class Application {
	/*
	 * 1. API (Application Programming Interface)
	 *   - API는 운영체제나 프로그래밍 언어가 제공하는 기능을 제어할 수 있게 만든 인터페이스
	 * 	 - 자바에서는 프로그램 개발에 자주 사용되는 클래스 및 인터페이스의 모음 (=라이브러리)
	 * 
	 * 2. String 클래스
	 *   - String 클래스로 생성된 객체는 불변의 객체 ▶ 객체 생성 후 변경 불가
	 *   - 변경이 적고 읽기가 많은 경우에 사용하는 것이 좋음
	 *   
	 * 3. StringBuilder & StringBuffer
	 *   - StringBuilder, StringBuffer 클래스로 생성된 객체는 가변 객체임
	 *   - 16개의 문자를 저장할 수 있는 버퍼가 미리 생성되고, 문자가 저장됨이 따라 자동으로 증가
	 * 	 - StringBuilder와 StringBuffer의 차이점은 동기화가 되느냐 안되느냐의 차이
	 *        - 멀티 스레드 환경 ▷ StringBuffer 사용 권장
	 *        - 단일 스레드 환경 ▷ StringBuilder 사용 권장
	 * 
	 * 4. StringTokenizer
	 *   - 생성자로 전달받은 문자열을 구분자를 이용하여 분리
	 *   - 토큰 : 분리된 최소 단위 
	 */

	public static void main(String[] args) {
//		new A_String().method1();
//		new A_String().method2();
//		new A_String().method3();

//		new B_StringBuilder().method1();
		new B_StringBuilder().method2();
	}

}

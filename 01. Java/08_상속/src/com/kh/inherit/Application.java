package com.kh.inherit;

import com.kh.inherit.after.Desktop;
import com.kh.inherit.after.SmartPhone;
import com.kh.inherit.after.Tv;
import com.kh.inherit.override.Book;

public class Application {

	public static void main(String[] args) {
		// Desktop 객체 생성
		Desktop desktop = new Desktop("애플", "a1111", "맥 24인치", 2000000, true);
		
		// SmartPhone 객체 생성
		SmartPhone phone = new SmartPhone("애플", "a2222", "아이폰12", 1000000, "KT");
		
		// Tv 객체 생성
		Tv tv = new Tv("엘지", "t-01", "올레드 벽걸이 TV", 2500000, 60);
		
		// 출력
		System.out.println(desktop.information());
		System.out.println(phone.information());
		System.out.println(tv.information());
		
		// 오버라이딩 테스트
		Book book1 = new Book("자바의 정석", "무닌수", 3000);
		Book book2 = new Book("자바의 정석", "무닌수", 3000);
		System.out.println();
		
		// toString() 메소드 테스트
		// 오버라이드 된 toString을 주석으로 막으면 클래스 풀네임과 @어쩌구주소가 나옴
		System.out.println(book1);
//		System.out.println(book1.toString());  위랑 같아요
		System.out.println();
		
		// equals() 메소드 테스트
		System.out.println("book1과 book2가 같은 같은 책입니까? " + (book1 == book2)); // 주소 비교
		System.out.println("book1과 book2가 같은 같은 책입니까? " + book1.equals(book2));  // 오버라이된 equals
		System.out.println();
		
		// hashCode() 메소드 테스트
		System.out.println("book1의 해시코드 : " + book1.hashCode());
		System.out.println("book2의 해시코드 : " + book2.hashCode());
	
	
	}

}

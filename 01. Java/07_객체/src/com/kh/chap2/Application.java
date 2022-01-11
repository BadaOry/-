package com.kh.chap2;

import com.kh.chap2_class.Person;

public class Application {

	public static void main(String[] args) {
		
		Person ismoon = new Person();
		// Phone 클래스는 접근 제한자가 default인 클래스로 외부(다른 패키지)에서 접근 불가
//		Phone phone = new Phone(); ▶ 안됨. 에러남.
		
		ismoon.setName("문인수");
		ismoon.setAge(20);
		ismoon.setPhone("아이폰12미니", "화이트", "애플");
		ismoon.setPhone("아이폰11", "블랙", "애플");
		
		ismoon.whoAreYou();
//		System.out.println(ismoon.whoAreYou());
	}

}

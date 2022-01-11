package com.kh.lambda.standard;

import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import com.kh.lambda.standard.model.vo.Student;

public class E_Predicate {
	/*
	 * Predicate
	 *  - Predicate 함수적 인터페이스는 매개변수와 boolean 리턴 값이 있는 testXXX() 메소드를 가짐
	 *  - 이 메소드들은 매개값을 조사하여 true / false를 리턴하는 역할임
	 */
	
	public void method1() {
		Student student1 = new Student("무닌수", 20, "남자", 80, 70);
		Student student2 = new Student("추냥이", 20, "여자", 100, 100);
		
		Predicate<Student> predicate = (s) -> {
			return s.getGender().equals("남자");	
		};
	
		System.out.println("무닌수는 남자입니까? " + predicate.test(student1));
		System.out.println("추냥이는 남자입니까? " + predicate.test(student2));
	
		
		
		IntPredicate intPredicate = (int i) ->  i > 100;
	
		System.out.println(intPredicate.test(100));
		System.out.println(intPredicate.test(101));
	}
}

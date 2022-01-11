package com.kh.lambda.standard;

import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import com.kh.lambda.standard.model.vo.Student;

public class C_Function {
	/*
	 * Function 
	 *  - Function 함수적 인터페이스는 매개값과 리턴이 있는 applyXXX() 메소드를 가짐
	 *  - 주로 매개값을 리턴 값으로 매핑(타입 변환) 하는 역할을 함
	 */
	
	public void method1() {
		Student student = new Student("무닌수", 20, "남자", 80, 70);
		
		// ▼ 매개변수 Student 를 받아서 String 타입으로 변환
		//   : getName (이름) 으로 리턴해줄 것임
		Function<Student, String> function = (Student s) -> s.getName(); 
//		{
//			return s.getName();
//		};
		
		System.out.println("넌 이름이 뭐니 ? : " + function.apply(student));
	
		System.out.print("[수학점수] : ");
		printInt((s)-> s.getMath(), student);
		
		System.out.print("[영어점수] : ");
		printInt((s)-> s.getEnglish(), student);
		
		System.out.print("[평균점수] : ");
		printInt((s)-> (s.getEnglish() + s.getMath()) / 2, student);
		
	}
	
	
	// ▼ printInt 라는 이름의 메소드를 만드는데, 메소드의 매개 변수는 두개로 지정
	//   ▷ 매개 변수 1: ToIntFunction 을 사용하여 Student 타입의 매개변수를 받아 int 로 리턴
	//   ▷ 매개 변수 2 : Student 타입
	//   ▶ 우리는 ToIntFunction 에 매개값으로 들어갈 람다식만 만들어주면 됨
	//   
	public void printInt(ToIntFunction<Student> function, Student student) {
		System.out.println(function.applyAsInt(student));
	}
	
}
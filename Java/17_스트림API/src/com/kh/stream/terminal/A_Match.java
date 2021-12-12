package com.kh.stream.terminal;

import java.util.Arrays;
import java.util.List;

import com.kh.stream.model.vo.Student;

public class A_Match {
	/*
	 * 매칭
	 *  - 최종 처리 단계에서 요소들이 특정 조건에 만족하는지 조사하는 기능
	 *   - allMatch()  : 모든 요소들이 매개값으로 주어진 Predicate의 조건을 만족하는지 조사
	 *   - anyMatch()  : 최소한 한 개의 요소가 매개값으로 주어진 Predicate의 조건을 만족하는지 조사
	 *   - noneMatch() : 모든 요소들이 매개값으로 주어진 Predicate의 조건을 만족하지 않는지 조사
	 */

	public void method1() {
		boolean result = false;
		int[] values = {2, 4, 6};

		result = Arrays.stream(values).allMatch(value -> value % 2 == 0);
		System.out.println(result);      // ▷ true
		
		
		result = Arrays.stream(values).anyMatch(value -> value % 2 == 0);
		System.out.println(result);      // ▷ true
		
		
		result = Arrays.stream(values).noneMatch(value -> value % 2 == 0);
		System.out.println(result);      // ▷ false
	}
	
	
	
	
	public void method2() {
		boolean result = false;
		
		List<Student> list = Arrays.asList(
				new Student("무닌수", 29, "남자", 80, 70),
			 	new Student("이몽룡", 20, "남자", 50, 60),
			 	new Student("성춘향", 17, "여자", 100, 100)
			 );
		
		// ▼ 나이가 20살 이상인 학생이 모두 남자인지 확인
		result = list.stream()
				     .filter(student -> student.getAge() >= 20)
				     .allMatch(student -> student.getGender().equals("남자"));
		
		System.out.println("20살 이상인 학생들은 모두 남자입니까? : " + result);
		
		
		// ▼ 남학생들 중 평균이 70점 이상인 학생이 한 명이라도 존재하는지 확인
		result = list.stream()
				     .filter(student -> student.getGender().equals("남자"))
				     .anyMatch(student -> (student.getMath()+student.getEnglish() / 2) >= 70);
		
		System.out.println("평균이 70점 이상인 남학생들이 한 명이라도 있습니까? : " + result);
	}
}

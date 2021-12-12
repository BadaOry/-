package com.kh.stream.terminal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.kh.stream.model.vo.Student;

public class C_Collect {
	/*
	 * 수집
	 *  - 스트림 요소들은 필터링 또는 매핑한 후 요소들을 수집하는 최종 처리 메소드인 collect() 제공
	 *  - collect() 메소드 사용 시 필요한 요소만 새로운 컬렉션으로 담아서 리턴받을 수 있음 
	 */

	public void method1() {
		
		List<Student> students = Arrays.asList(
				new Student("무닌수", 20, "남자", 80, 70),
				new Student("이몽룡", 20, "남자", 50, 60),
				new Student("성춘향", 20, "여자", 100, 100),
				new Student("심청이", 16, "여자", 100, 100),
				new Student("홍길동", 18, "여자", 40, 80)
				);
		
		// ▼ 남자인 학생을 필터링 후 리스트를 만듦 
		List<Student> maleList = students.stream()
							.filter(student -> student.getGender().equals("남자"))
							// ▼ 남자들만 필터링 해서 Collectors를 사용해 새로운 리스트 생성
							.collect(Collectors.toList());
		
		System.out.println(maleList);
		
		// ▼ maleList 라는 리스트 객체로 뽑았으니, 이거로 스트림을 다시 뽑아 다른 작업을 할 수 있음
		maleList.stream().forEach(student -> System.out.println(student));
		
		System.out.println();
		
		
		
		// ▼ 여자인 학생을 필터링 후 리스트를 만듦
		Set<Student> femaleList = students.stream() 
									      .filter(student -> student.getGender().equals("여자"))
//									      // ▼ 1) 내부적으로 HashSet을 만들어줌
									      .collect(Collectors.toSet());
//									      // ▼ 2) HashSet을 직접 전달
//									      .collect(Collectors.toCollection(() -> {return new HashSet<>();}));
									      .collect(Collectors.toCollection(HashSet::new));

									      // ▼ femaleList 라는 셋 객체로 뽑았으니, 이거로 스트림을 다시 뽑아 다른 작업을 할 수 있음
		femaleList.stream().forEach(student -> System.out.println(student));
		
	} 
		
}

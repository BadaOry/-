package com.kh.stream.intermediate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.kh.stream.model.vo.Student;

public class C_Sorted {
	/*
	 * 정렬
	 *  - 스트림은 요소가 최종 처리되기 전에, 중간 단계에서 요소를 정렬하여 최종 처리의 순서를 변경할 수 있음
	 *  - Stream<T>은 요소 객체가 Comparable을 구현하지 않을 경우 예외가 발생 
	 *  - IntStream, LongStream, DoubleStream 은 요소를 오름차순으로 정렬함
	 */
	
	// 1) 요소가 객체일 때 정렬
	public void method1() {
		List<String> names = Arrays.asList("무닌수", "이몽룡", "성춘향", "변사또", "이순신");
			
		// 1-1) Comparable 구현 내용에 따라서 정렬
		names.stream()
		// 1-1-1) sorted 사용
		     .sorted()
		     
	     // 1-1-2) compareTo 메소드 사용 : int 매개값 두 개를 받아 비교후 음수 / 0 / 양수 리턴
//		     .sorted((n1, n2) -> n1.compareTo(n2))
		     
	     // 1-1-3) Comparator 인터페이스의 naturalOrder() static 메소드 사용
	     //        ▷ Comparator 객체를 리턴해줌 
//		     .sorted(Comparator.naturalOrder())
		     
		     .forEach(name -> System.out.println(name));
		
		
		// ▼ 이건 에러남
		//   : Student 객체가 comparable 인터페이스를 구현하지 않았기 때문 
		//     (implements Comparable<Student>이 없음)
//		List<Student> students = Arrays.asList(
//				new Student("무닌수", 20, "남자", 80, 70),
//				new Student("이몽룡", 20, "남자", 50, 60),
//				new Student("성춘향", 20, "여자", 100, 100)
//				);
//		
//		students.stream()
//		        .sorted()
//		        .forEach(student -> System.out.println(student));
		
		
		
		// 1-2) Comparable 구현 내용과 반대로 정렬
		names.stream()
	    // 1-2-1) compareTo 메소드 사용 : int 매개값 두 개를 받아 비교후 음수 / 0 / 양수 리턴
		//                            위와 다른점은 n2를 기준으로 compareTo 사용하는 것임
		     .sorted((n1, n2) -> n2.compareTo(n1))
		     
	     // 1-1-3) Comparator 인터페이스의 reverserOrder() static 메소드 사용
	     //        ▷ Comparator 객체를 리턴해줌 
		     .sorted(Comparator.reverseOrder())
		
		     .forEach(name -> System.out.println(name));
	}
	
	
	
	
	// 2) 요소가 기본 자료형일 때 정렬
	public void method2() {
		// 2-1) 기본 자료형의 요소를 가지는 스트림은 오름차순으로 정렬함
		//      : 기본 자료형이므로 Comparable 인터페이스를 굳이 구현할 수 없임
		//        따라서, 여기서는 int 스트림으로 반환되어 바로 sorted 사용
		Arrays.stream(new int[] {5, 2, 4, 3, 1})
		      .sorted()
		      .forEach(value -> System.out.println(value));
		
		System.out.println();
		
		
		// 2-2) 기본 자료형을 내림차순으로 정렬하는 방법
		//      : 기본 자료형에는 내림차순으로 정렬하는 기능이 없음
		//        따라서, boxed()를 사용해 기본 자료형을 Comparable 인터페이스가 구현되어있는
		//              Integer객체로 만들어서 재정렬
		//        혹은, mapToInt 를 사용해 다시 기본 자료형인 int 형으로 만들어도 됨
		Arrays.stream(new int[] {5, 2, 4, 3, 1})
	          .boxed()
	          .sorted(Comparator.reverseOrder())
	          .mapToInt(value -> value.intValue())
	          .forEach(value -> System.out.println(value));
	}

}

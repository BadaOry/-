package com.kh.stream.terminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class B_Aggregate {
	/*
	 * 기본 집계
	 *  : 최종 처리 기능으로 요소들을 처리해서
	 *    카운팅, 합계, 평균값, 최대값, 최소값 등과 같이 하나의 값으로 산출하는 것 
	 *  - count()     : 요소의 개수 리턴
	 *  - sum()       : 요소들의 합계 리턴
	 *  - average()   : 요소들의 평균 리털
	 *  - max()       : 최대 요소 리턴
	 *  - min()       : 최소 요소 리턴
	 *  - findFirst() : 첫 번째 요소 리턴
	 * 
	 * 커스텀 집계
	 *  - 스트림에서 기본 집계 메소드를 제공하지만,
	 *    다양한 집계 결과물을 만들 수 있도록 reduce() 메소드 제공
	 *       - 매개 타입으로 XXXOperator 
	 *       - 리턴 타입으로 OptionalXXX
	 *       
	 *  Optional 클래스
	 *  - 스트림의 최종 결과값을 저장하는 객체
	 *  - 단순히 값만 저장하는 것이 아니라, 값의 존재 여부를 확인하고
	 *    값이 존재하지 않을 경우 디폴트 값을 설정할 수 있는 객체
	 *     - get()               : 객체에 저장된 값을 얻기 위해 사용
	 *     - isPresent()         : 값이 저장되어있는지 검사
	 *     - orElse()            : get()과 동일하게 저장된 값을 얻어오는 메소드로
	 *                             값이 저장되어 있지 않을 경우 디폴트 값을 지정함
	 *     - ifPresent(Consumer) : 값이 저장되어 있을 경우, Consumer에서 값 처리
	 */
	
	public void method1() {
		int[] values = {1, 2, 3, 4, 5, 6};
		
		// ▼ 2의 배수의 개수
		long count = Arrays.stream(values)
					       .filter(value -> value % 2 == 0)
					       .count();
		
		System.out.println("2의 배수의 개수 : " + count);
		
		
		// ▼ 2의 배수의 합
		int sum = Arrays.stream(values)
				        .filter(value -> value % 2 == 0)
				        .sum();
					
		System.out.println("2의 배수의 합 : " + sum);
  
		
		// ▼ 2의 배수의 평균
		OptionalDouble average = Arrays.stream(values)
							           .filter(value -> value % 2 == 0)
							           .average();
		
		System.out.println("2의 배수의 평균 : " + average.getAsDouble());
		   
		
		// ▼ 2의 배수의 최대값
		OptionalInt max = Arrays.stream(values)
		           .filter(value -> value % 2 == 0)
		           .max();
		
		System.out.println("2의 배수의 최대값 : " + max.getAsInt());
		
		
		// ▼ 2의 배수의 최소값
		OptionalInt min = Arrays.stream(values)
		           .filter(value -> value % 2 == 0)
		           .min();
		
		System.out.println("2의 배수의 최소값 : " + min.getAsInt());
		
		
		// ▼ 2의 배수의 첫 번째 요소
		OptionalInt findFirst = Arrays.stream(values)
						              .filter(value -> value % 2 == 0)
						              .findFirst();
						
		System.out.println("2의 배수의 첫 번째 요소 : " + findFirst.getAsInt());
		
	}
	
	
	
	
	public void method2() {
		List<Integer> values = new ArrayList<>();
		
//		values.add(1);
//		values.add(4);
		
		// ▼ stream() 까지만 쓰면 객체를 다루는 거기 때문에, average 함수 사용 불가
		//   ▷ mapToInt 로 숫자형으로 바꿔줌
//		double avg = values.stream()
//						   .mapToInt(value -> value.intValue())
//						   .average()
//						   .getAsDouble();
//		
//		System.out.println(avg);
		
//		근데 평균 구할 때 요소 값이 하나도 없으면 0으로 나누고 에러가 나지 않나요?
	
		// ▼ 빈 값을 처리하는 방법 1 : 값의 존재 여부 확인
		//                      ▷ OptionalDouble.empty
		OptionalDouble avg1 = values.stream()
								    .mapToInt(value -> value.intValue())
								    .average();
			
		if(avg1.isPresent()) {
			System.out.println("평균 : " + avg1.getAsDouble());
		} else {
			System.out.println("데이터 없음");
		}

		
		// ▼ 빈 값을 처리하는 방법 2 : 디폴트 값 설정
		double avg2 = values.stream()
						    .mapToInt(value -> value.intValue())
						    .average()
						    .orElse(0.0);

		System.out.println("평균 : " + avg2);
		
		
		// ▼ 빈 값을 처리하는 방법 3 : 집계 값을 처리하는 Consumer 등록
		values.stream()
			  .mapToInt(value -> value.intValue())
			  .average()
			  .ifPresent(avg3 -> System.out.println("평균 : " + avg3));
	}
	
	
	public void method3() {
		int[] values = {1, 2, 3, 4, 5, 6};
//		int[] values = {};
		
		
		Arrays.stream(values)
		      .reduce((left, right) -> left * right)
			  .ifPresent(value -> System.out.println("요소들의 곱은 얼마인가요? : " + value));
		
		// ▼ reduce(0, (left, right) -> left * right) = 0 * 1 * 2 * 3 * 4* 5 * 6   ▷ 0
		//   reduce(1, (left, right) -> left * right) = 1 * 1 * 2 * 3 * 4* 5 * 6   ▷ 720
		//   reduce(2, (left, right) -> left * right) = 2 * 1 * 2 * 3 * 4* 5 * 6   ▷ 1440
		//   reduce(3, (left, right) -> left * right) = 3 * 1 * 2 * 3 * 4* 5 * 6   ▷ 21600
		int result = Arrays.stream(values)
				          .reduce(3, (left, right) -> left * right);
		
		System.out.println(result);
		
	}
	
}

package com.kh.chap1.list;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kh.chap1.list.compare.ArtistAscending;
import com.kh.chap1.list.compare.TitleAscending;
import com.kh.chap1.list.model.vo.Music;

public class A_ArrayList {

	public void method1() {
		// ArrayList를 생성, 데이터 저장, 출력 테스트
		
		/*
		 * ArrayList
		 *    - ArrayList를 생성하면 내부적으로 10개의 객체를 저장할 수 있는 공간 생성
		 *    - 저장 순서가 유지되고 인덱스를 통해서 관리됨
		 *    - 객체만 저장 가능하고 null도 저장 가능
		 *    - 중복되는 객체의 저장도 허용
		 * 
		 */
		
		ArrayList list = new ArrayList();
		
		System.out.println("list : " + list);
		System.out.println("list.isEmpty() : " + list.isEmpty());
		
		list.add("안녕하세요.");
		list.add(3.14); // 오토 박싱이 적용됨
		list.add(LocalDateTime.now());
		
		System.out.println("list : " + list);  // 내부적으로 객체참조 넘기면 toString 호출
		System.out.println("Size : " + list.size());
		System.out.println();
		
		for(int i = 0; i < list.size(); i++) {
			// 인덱스에 해당하는 값을 가져올 때는 get()메소드 사용
			System.out.println(list.get(i));
		}
		System.out.println();
		
//		// list에서 값을 꺼내서 대입하기 위해서는 해당 자료형에 맞게 형변환이 필수
//		String str = (String) list.get(0); // Objectt로 저장되어있어서 String 형변환 필요
//		int num = (int) list.get(3); // get이 리턴해주는 타입이 Object라 int로 형변환 필요
		
		// 객체의 중복 저장 허용
		list.add("안녕하세요."); 
		System.out.println("list : " + list);
		System.out.println();
		
		// 원하는 인덱스 위치에 객체 추가 가능
		list.add(2, 1234500); // 인덱스 위치를 밀고 새로 추가함 (값 변경이 아님)
		System.out.println("list : " + list);
		System.out.println();
		
		// set을 사용해야 원하는 인덱스 위치의 객체를 변경 가능 
		list.set(2, false);
		System.out.println("list : " + list);
		System.out.println();
		
		// 저장된 객체를 삭제할 떄 remove() 메소드 사용
		list.remove(2); // 2번 인덱스 삭제하고 뒤에서 땡겨옴
		list.remove("안녕하세요.");	 // 해당 Object가 있으면 앞에서부터 지우고, 뒤에건 삭제 안됨
								 // ▶ 가장 첫 번째 객체만 지움
		list.remove(3.14);
		System.out.println("list : " + list);
		System.out.println("list.isEmpty() : " + list.isEmpty());
		System.out.println();
		
		// 저장된 객체를 모두 삭제할 때는 clear() 메소드 사용
		// 단 배열이 소멸되는게 아니고, heap에 자리는 남아있지만 객체 연결이 끊어지는 것
		list.clear();
		System.out.println("list : " + list);
		System.out.println("list.isEmpty() : " + list.isEmpty());
		
	}

	public void method2() {
		
		/*
		 * 리스트 정렬
		 *   - 기본 자료형, 문자열의 경우엔 Comparable 인터페이스가 내부적으로 구현되어 있어
		 *     Collections.sort()를 통해 정렬 가능
		 *   - 사용자가 작성하는 클래스의 객체도 Collections.sort()를 통해서 정렬이 가능하게 하고싶다면
		 *     Comparable 인터페이스를 구현해 주면 됨     
		 *     
		 * 제네릭스(<>)를 사용하는 이유
		 *   - 명시된 타입의 객체만 저장하도록 제한을 두기 위해 사용
		 *   - 컬렉션에 저장된 객체를 꺼내서 사용할 때 매번 형변환을 하지 않아도 됨
		 */
		List<String> list = new ArrayList<>();
//		List<int> list2 = new ArrayList<>();     // 기본 자료형으로 저장 불가
		List<Integer> list2 = new ArrayList<>(); // 객체만 담을 수 있으니 래퍼 클래스로 하셔야죠
		
		list.add("apple");
		list.add("orange");
		list.add("banana");
		list.add("mango");
		list.add("grape");
		
		// 오토 박싱으로 그냥 넘겨주는 중 ~~
		list2.add(7);  // Integer e = 7; 인거랑 같음
		list2.add(1);
		list2.add(8);
		list2.add(10);
		list2.add(3);
		list2.add(2);
		
		System.out.println(list); // 저장한 순서대로 출력
		System.out.println(list2);
		System.out.println();
		
		// 오름차순으로 리스트를 정렬하는 메소드
		Collections.sort(list);
		Collections.sort(list2);
		System.out.println(list);
		System.out.println(list2);
		System.out.println();
		
		// 내림차순으로 리스트를 정렬하는 메소드
		Collections.reverse(list);
		Collections.reverse(list2);
		System.out.println(list);
		System.out.println(list2);
		System.out.println();
		
		
		
		
	}

	public void method3() {
		// Music 객체를 list에 담아서 출력하고 정렬하기
		
		List<Music> list = new ArrayList<>(); // Music클래스로 만들어진 객체만 담을거야
		
		list.add(new Music("Permission to Dance", "BTS", 1));
		list.add(new Music("Butter", "BTS", 3));
		list.add(new Music("Ice Cream", "BlackPink", 5));
		list.add(new Music("헤픈 우연", "헤이즈", 2));
		list.add(new Music("눈의꽃", "박효신", 4));
		
		for(Music music : list) {
			System.out.println(music);
		}
		System.out.println();
		
		// 오름차순 구현
		Collections.sort(list); // 우리의 list 는 Comparable 인터페이스를 구현안했기 때문에 에러
		for(Music music : list) {
			System.out.println(music);
		}
		System.out.println();
		
		// 내림차순 구현
		Collections.reverse(list);
		for(Music music : list) {
			System.out.println(music);
		}
		System.out.println();
		// ▼ 리버스 안쓰고 내림차순으로 정렬하고 싶으면 아래 코드처럼
		// int result = (this.ranking > music.ranking) ? -1 : (this.ranking == music.ranking) ? 0 : 1;
		
		// title 순으로 오름차순
		Collections.sort(list, new TitleAscending());
		for(Music music : list) {
			System.out.println(music);
		}
		System.out.println();
		
		// Artist 순으로 오름차순
		
		Collections.sort(list, new ArtistAscending());
		for(Music music : list) {
			System.out.println(music);
		}
		System.out.println();
	}
}

package com.kh.chap2.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.kh.chap2.set.model.vo.Music;

public class A_HashSet {
	public void method1() {
		Set<String> set = new HashSet<>();
		
		set.add(null);
		set.add("반갑습니다.");
		set.add(new String("반갑습니다."));
		set.add("여러분");
		set.add(null);
		set.add("안녕하세요.");
		set.add("여러분");
		set.add(null);
		
		/*
		 * 저장 순서가 유지되지 않고 중복된 데이터는 제거됨
		 *   - String 클래스에는 hashCode(), equals() 메소드가 오버라이딩 되어있음
		 *   - 실제 필드값이 동일한 경우에 동일 객체로 인식해 저장되지 않음
		 */
		System.out.println(set);
		System.out.println();
		
		// Set에 저장된 객체에 접근하는 방법
		// 1. 향상된 for문 사용
		for(String str : set) {
			System.out.println(str);
		}
		System.out.println();
		
		// 2. HashSet에 저장된 객체들을 ArrayList에 담아서 출력하는 방법
		List<String> list = new ArrayList<>(set);
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println();
		
		// 3. Iterator 반복자를 사용하는 방법
		//   - 가져올 객체가 있는지 확인할 대는 hasNext() 메소드를 사용함
		//   - 하나의 객체를 가져올 때는 next() 메소드 사용
		Iterator<String> iterator = set.iterator();
	
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}
	
	public void method2() {
		Set<Music> set = new HashSet<>();
		/*
		 *  - Music 클래스는 hashCode(), equals() 메소드를 오버라이딩 하지 않았으므로
		 *     중복제거가 되지 않음
		 *  - 기본적으로 Object 객체에 있는 hashCode(),equals() 메소드를 사용함
		 *  - 이 두 메소드는 객체의 주소값을 가지고 비교하기 때문에
		 *    값과 상관 ㅇ벗이 모두 다른 객체로 인식해서 중복이 제거되지 못함
		 *  - Music 클래스에서 hashCode(), equals() 메소드 재정의(오버라이딩) 필요
		 */
		set.add(new Music("Permission to Dance", "BTS", 1));
		set.add(new Music("Permission to Dance", "BTS", 1));
		set.add(new Music("Butter", "BTS", 3));
		set.add(new Music("Ice Cream", "BlackPink", 5));
		set.add(new Music("Ice Cream", "BlackPink", 5));
		set.add(new Music("헤픈 우연", "헤이즈", 2));
		set.add(new Music("눈의꽃", "박효신", 4));
		set.add(new Music("눈의꽃", "박효신", 4));
		
		for(Music music : set) {
			System.out.println(music);		
		}
		
	}	
}

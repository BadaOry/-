package com.kh.chap3.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.kh.chap3.map.model.vo.Snack;

public class A_Hashmap {

	public void method1() {
		Map<String, Snack> map = new HashMap<>();
		
		// 저장 순서 유지 안됨
		// 키값 중복 X, 중복된 키값으로 저장하면 기존에 있던 값을 덮어 씀
		// value 값은 중복되어도 잘 저장함
		map.put("다이제", new Snack("초코맛", 1200));
		map.put("칸쵸", new Snack("바나나맛", 250));
		map.put("허니버터칩", new Snack("꿀버터맛", 500));
		map.put("칸쵸", new Snack("초코맛", 250));
		map.put("꼬북칩", new Snack("오리지널", 1200));
		map.put("꼬북칩", new Snack("초코맛", 1200));
		
		System.out.println(map);
		System.out.println();
		
		// Map 컬렉션에서 키값에 해당하는 value를 가져오는 메소드 
		Snack snack = map.get("다이제");
		
		System.out.println(snack);
		
		// Map 컬렉션에 담겨있는 Entry 객체들의 개수를 가져오는 메소드
		System.out.println("개수 : " + map.size());
		System.out.println();
		
		// 키 값에 해당하는 Entry 객체를 삭제하는 메소드
		map.remove("칸쵸");
		System.out.println(map);
		System.out.println("개수 : " + map.size());
		System.out.println("isEmpty? : " + map.isEmpty());
		System.out.println();
		
		// Map 컬렉션에 담겨있는 모든 Entry 객체를 삭제하는 메소드
		map.clear();
		System.out.println(map);
		System.out.println("개수 : " + map.size());
		System.out.println("isEmpty? : " + map.isEmpty());
		System.out.println();		
	}

	public void method2() {
		Map<String, Snack> map = new HashMap<>();
		
		map.put("다이제", new Snack("초코맛", 1200));
		map.put("칸쵸", new Snack("바나나맛", 250));
		map.put("허니버터칩", new Snack("꿀버터맛", 500));
		map.put("꼬북칩", new Snack("오리지널", 1200));
		map.put("나쵸", new Snack("짠맛", 600));
		
		// Map은 인덱스 개념이 없어 반복문, 향상된 for문 사용 불가
//		for(Entry<String, Snack> i : map) {
//		}
		
		// List와 Map의 구조가 다르므로 ArrayList에 담아서 출력하는것도 사용 불가
		// Map 객체는 Collection을 상속하지 않으니까 ~
//		List<Entry, Snack>	list = new ArrayList<>(map);
		
		// 1. keySet() 이용 방법
		// - keySet() 메소드는 Map 컬렉션에 있는 Entry 객체 안의 키 값들만
		//	 Set 컬렉션에 담아서 반환함 
		// - 반환된 Set 컬렉션에서 반복자를 얻어서 반복 가능
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		
		//   1) iterator 사용한 출력
		while(iterator.hasNext()) {
			String key = iterator.next();
			
			System.out.println("key : " + key);
			System.out.println("value : " + map.get(key));
		}
		
		//   2) iterator 대신 향상된 for문 사용한 출력
		//      Set으로 변경하면 반복자 사용하지않고 향상된 for문으로도 출력 가능
		for(String key : keySet) {
			System.out.println("key : " + key);
			System.out.println("value : " + map.get(key));	
		}
		
		System.out.println();
		
		// 2. entrySet() 이용하는 방법
		//  - entrySet() 메소드는 Map 컬렉션에 있는 Entry객체(Key, Value 쌍으로 이루어진)를
		//    Set 컬렉션에 담아서 반환함
		
		Set<Entry<String, Snack>> entrySet = map.entrySet();
		Iterator<Entry<String, Snack>> it = entrySet.iterator();
		
		
		//   1) iterator 사용한 출력
//		while(it.hasNext()) {
//			Entry<String, Snack> entry = it.next();
//			
//			System.out.println("key : " + entry.getKey());
//			System.out.println("value : " + entry.getValue());	
//		}
//		
		//   2) iterator 대신 향상된 for문 사용한 출력
		for(Entry<String, Snack> entry : entrySet) {
			
			System.out.println("key : " + entry.getKey());
			System.out.println("value : " + entry.getValue());	
			
		}
		
		
	}
	
	
	
}

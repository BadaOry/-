package linear;

import java.util.ArrayList;
import java.util.Iterator;

public class A_List {
/* 
[ 조원 이름 ]
: 이수정

[ 정의 ]﻿
: Collection 인터페이스를 확장한 자료형 중 하나로서
  동일한 데이터의 중복 입력이 가능하고, 순차적이고 다량의 데이터를 입력할 때 사용

	
[ 특징 ]﻿
- 빈 엘리먼트 없음 = 빈틈없는 데이터의 적재 
- 인덱스 없음
 	* 리스트에서 인덱스는 몇 번째 데이터인지의 정도(순서) 의미를 가짐
- 불연속적으로 메모리 공간 차지
- 포인터(참조 변수)를 통해 접근


[ 장단점 ]
	1) 장점
		- 포인터(참조변수)를 사용해 데이터의 위치를 가르키므로 삽입·삭제가 용이함
		- 동적이므로 크기가 정해져 있지 않음
		- 메모리 재사용 편리

	2) 단점
		- 검색 성능이 좋지 않음
		- 포인터(참조변수) 사용 ▷ 추가적인 메모리 공간 발생
		- 순차성 보장 X
		  ▷ cache locality 보장 X
		  ▶ cache hit 의 어려움
				* cache (memory)
				: 처리 속도가 다른 장치 간의 속도차에 따른 병목현상을 줄이는 범용 메모리
				  ▷ 프로세서(CPU) 가까이 위치하면서 빈번하게 사용되는 데이터를 놔두는 장소
				
				* locality (지역성)
				: 장치 내의 정보를 어느 한 순간에 특정 부분을 집중적으로 참조하는 특성
			      ▷ 데이터 접근이 시간적, 공간적으로 가깝에 일어나는 것
				
				* cache locality
				: 프로세서가 시간적, 공간적으로 유사한 데이터를 집중적으로 참조하는 성질
				
				* cache hit
				: 프로세서 (CPU) 에서 요청한 데이터가 캐시에 존재하는 경우


[ 배열 vs 리스트 ]
	1) 인덱스의 역할
		- 배열 : 인덱스는 값에 대한 유일무이한 식별자로서, 배열은 인덱스와 값의 쌍으로 구성
				▷ 검색의 용이
				▷ 연속적 ▶ 메모리 관리 용이
		- 리스트 : 몇 번째 데이터인가 정도·순서의 의미
				▷ 검색 불편 (포인터 사용)
					* 포인터 : 주소를 저장하는 변수로서 변수의 주소(&)나 내용(*) 추출
				▷ 불연속적 ▶ 추가 메모리 사용


	2) 크기
		- 배열 : 컴파일 이전에 크기를 설정하며 정적으로 고정
				▶ 컴파일 이후 변경 불가
		- 리스트 : 동적이므로 크기가 정해져 있지 않음

﻿
﻿[ ArrayList ]
	1) 정의
	: Collection 인터페이스를 확장한 자료형 중 하나로서
	   동일한 데이터의 중복 입력이 가능하고, 순차적이고 다량의 데이터를 입력할 때 사용하는
	   List 컬렉션 클래스에 속하는 하위 클래스

	2) 특징
		- 객체만 저장 가능
		- 크기를 동적으로 조절 가능
		- 특정 인덱스 객체 제거시, 그 뒤 인덱스부터 마지막 인덱스까지 한 칸씩 앞으로 이동
		   ▶ 잦은 객체 이동, 삭제가 발생할 경우 LinkedList 사용이 더 용이함
		- 타입 안정성을 보장해주는 제네릭스( < > ) 사용 가능
		- 데이터 추가 과정에서 사용하는 배열이 꽉차면
		  내부적으로 기본 배열 대비 2배 큰 배열을 새로 만들고 기존 데이터를 새로운 배열로 복제

	3) 동작 과정 (내부적)
		① 객체 삽입
			- List의 크기를 삽입될 자료만큼 늘림
			- 삽입될 자료 위치를 기준으로 기존 데이터를 앞/뒤로 이동
			- 해당 위치에 자료 입력 ▶ 삽입 완료
		② 객체 삭제
			- 삭제 될 자료가 위치한 인덱스 자료 삭제
			- 삭제 된 자료 위치를 기준으로 이후의 자료를 이동
			- List의 맨 마지막은 비어있는 상태로 삭제 완료

 */
	
// [ 코드 ]
	public static void main(String[] args) {	

	// 1. ArrayList 객체 생성 (java.util에서 import 필요)
		ArrayList arraylist = new ArrayList(); // 제너릭스 < > 도 추가 가능
		B_LinkedList b = new B_LinkedList();
		
		
		
	// 2. 엘리먼트 추가 
	// - add() 메소드 사용
	// - ArrayList 가장 뒤에 데이터 추가
	// - 오토박싱 (기본타입 자료형 ▷ Wrapper 클래스) 적용 
		arraylist.add("추석에 잘먹어서");
		arraylist.add(3.14);
		arraylist.add('k');
		arraylist.add('g');
		arraylist.add("증가했습니다.");
		arraylist.add("증가했습니다."); // 데이터 중복 저장 가능
		arraylist.add(b);
		
		System.out.println("======= 엘리먼트 추가 =======");
		System.out.println("arraylist : " + arraylist); // 내부적으로 toString 알아서 호출해줌
		System.out.println();
		
	// - 인덱스를 사용해서 추가 가능
		arraylist.add(4, "정도");
		System.out.println("======= 4번 인덱스에 엘리먼트 추가 =======");
		System.out.println("arraylist : " + arraylist); 
		System.out.println();
		
		
		
	// 3. 엘리먼트 가져오기
	// - get(int index) 메소드 사용
	// - 인덱스가 index 번인 데이터를 가져옴
		System.out.println("======= 엘리먼트 가져오기 =======");
		System.out.println("arraylist의 0번 인덱스 데이터 : " + arraylist.get(0));
		System.out.println("arraylist의 6번 인덱스 데이터 : " + arraylist.get(6));
		System.out.println();
		
		
		
	// 4. Iterator를 사용한 arraylist 출력
	// - java.util의 iterator를 import하여 Iterator 객체 생성
		Iterator it = arraylist.iterator();
		
		System.out.println("======= iterator를 사용한 출력 =======");
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		System.out.println();
		
		
		
	// 5. 엘리먼트 삭제 
	// - remove() 메소드 사용
	// - 인덱스, 객체타입 활용하여 삭제 가능
		System.out.println("======= 엘리먼트 삭제 =======");
		
		// 1) 내용 지정 삭제
		arraylist.remove("증가했습니다.");
		System.out.println("증가했습니다 삭제 \n▶▶ " + arraylist);
		System.out.println();
		
		// 2) 인덱스 지정 삭제 : arraylsit의 3번쨰 데이터 삭제
		arraylist.remove(2); 
		System.out.println("2번 인덱스(k) 삭제 \n▶▶" + arraylist);
		System.out.println();
		
		// 3) 객체 지정 삭제 : 괄호 안에 객체 타입 입력
		arraylist.remove(b);
		System.out.println("객체 b 삭제 \n▶▶" + arraylist);
		System.out.println();
		
		// 4) clear() 메소드를 사용한 전체 삭제
		//    - 단 배열 소멸이 아닌, heap 영역에 공간은 남아있으나 객체 연결이 끊어지는 것
		//      ▶ 모든 엘리먼트를 돌면서 null 대입해버림
		arraylist.clear();
		System.out.println("clear() 메소드 사용 후 arraylist 출력 \n▶▶" + arraylist);
		System.out.println("arraylist 내용을 모두 삭제했습니까? : " + arraylist.isEmpty());
		System.out.println();
		
		// 5) removeAll() 메소드를 사용한 전체 삭제
		// 	  - X.removeAll(Y);
		//      : X에서 Y에 있는 것을 삭제할 때 사용
		//		  ▷ 삭제할 엘리먼트 하나하나를 작업 단위로 보고 처리 (batch)
		//        ▷ 엘리먼트 갯수만큼 equals 메소드로 내용이 같은지 하나하나 검사하고 지움
		//        ▶ 컬렉션을 두번 거치므로 clear()보다 속도가 느림
		arraylist.removeAll(arraylist); // arraylist에서 arraylist를 지운다 
										// = 하나하나 대입해가면서 지워간다
		System.out.println("remove() 메소드 사용 후 arraylist 출력 \n▶▶" + arraylist);
		System.out.println("arraylist 내용을 모두 삭제했습니까? : " + arraylist.isEmpty());

		
	}
	
/*
[ 출처 ]
﻿
https://han.gl/zz5T3

https://bibimnews.com/entry/%EC%BA%90%EC%8B%9C-%EC%A7%80%EC%97%AD%EC%84%B1Locality

http://compiler.sangji.ac.kr/lecture/ds/2018/lecture05.pdf

https://icandooit.tistory.com/63

https://power-overwhelming.tistory.com/23

https://han.gl/Dz9Xz

https://han.gl/Q02IY


 */
}
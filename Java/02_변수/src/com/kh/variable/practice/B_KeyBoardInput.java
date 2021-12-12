package com.kh.variable.practice;

import java.util.Scanner;

public class B_KeyBoardInput {
	
	public void inputByScanner() {
		// scanner라는 이름의 클래스를 만들었음
		Scanner scanner = new Scanner(System.in); // 콘솔에서 사용자가 입력할 수 있도록 커서가 나올거얌 > 그 값을 시스템에 전달
		String name = "";
		int age = 0;
		double height = 0;
		char gender = '\u0000';  // 문자는 ''초기화 못함
		
		System.out.println("당신의 이름은 무엇입니까?");
		/* 
		 * scanner.nextLine() : 사용자가 임력한 값을 모두 읽어옴
		 * scanner.next() : 사용자가 입력한 값 중에 공백이 있을 경우 공백 이전까지의 값만 읽음
		 * scanner.nextInt() : 정수를 입력받을 때 사용
		 * scanner.nextDouble() : 실수를 입력받을 때 사용
		 * . . .  그 밖에 byte, boolean 등의 기본 자료형을 입력받을때도 마찬가지로 nextXXX()로 입력받음
		 */
		
		// 입력이 완료되면 해당 값을 변수에 대입한다.
		// 대입 구문(=, 담는 공간인 변수)이 없으면 입력한 값을 눈으로 확인할 수 없음
		name = scanner.nextLine();	// scanner라는 클래스에서 next.Line()이라는 메소드 호출
		// 사용자로부터 입력이 있을 때 까지 대기하고
		// 입력이 있으면 데이터를 다음 줄에 넘겨주고 진행하도록 함
		
		System.out.println("당신의 나이는 몇 살입니까?");
		age = scanner.nextInt(); // 정수형으로만 입력해야 함
		
		System.out.println("당신의 키는 몇 입니까? (소수점 첫째 자리 까지 입력하세요.)");
		height = scanner.nextDouble(); // 실수형 입력 가능
		
		System.out.println("당신의 성별은 무엇입니까? (M/F)");
		
//		scanner.nextXXX() 메소드 뒤에 scanner.nextLine() 메소드를 사용하게 된다면 
//		버퍼에 남아있는 '엔터'를 빼주기 위해 scanner.nextLine() 메소드를 한 번 더 써줘야 함
		scanner.nextLine();
		
//		scanner.nextChar();      // 이런거 없음
//		scanner.nextLine();을 통해 문자열을 읽어오고 String 클래스에서 제공해주는 charAt()을 통해 원하는 문자를 뽑아냄
//		이 때 위치를 지정하는 방법 : 제일 앞 문자부터 0으로 시작하여 1씩 증가하면서 원하는 문자를 뽑아냄
		String line = scanner.nextLine(); 	 // 일단 문자열로 받아오고, String 클래스 쓰고
		gender = line.charAt(0);			 // charAt은 String클래스 안의 메소드, 문자열에서 지정된 위치의 문자를 하나 뽑아오는 메소드
		
		
//		System.out.println("당신의 이름은 " + name + "이고 나이는 " + age + "세, 키는 " + height + "cm, 성별은 " + gender + "입니다.");
		// printf 구문으로 바꿔볼게요
		System.out.printf("당신의 이름은 %s이고 나이는 %d세, 키는 %.1fcm, 성별은 %c 입니다.", name, age, height, gender);
	
	}
}
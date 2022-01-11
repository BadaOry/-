package com.kh.array.practice;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ArrayPractice {

	public void method1() {
		int[] arr = new int[10];
		
		int value = 1;
		
		for(int i = 0 ; i < arr.length ; i++) {
			arr[i] = value++;
		}
		
		for(int i = 0 ; i < arr.length ; i++) {
			System.out.print(arr[i] + " ");
		} 
	}

	public void method1_teacher() {
		int[] arr = new int[10];
		
		for(int i = 0; i < arr.length ; i++) {
			arr[i] = i + 1;
		}
		
		for(int v : arr) {
			System.out.print(v + " ");
		}
	}

	public void method2() {
		
		int[] arr = new int[10];
		
		int value = 10;
		for(int i = 0 ; i < arr.length ; i++) {
			arr[i] = value--;
		}
	
		for(int i = 0 ; i < arr.length ; i++) {
			System.out.print(arr[i] + " ");
		}	
	}
	
	public void method2_teacher() {
		int[] arr = new int[10];
		
		for(int i = 0; i < arr.length ; i++) {
			arr[i] = arr.length - i;
		}
		
		for(int v : arr) {
			System.out.print(v + " ");
		}
	}	

	public void method3() {
		Scanner sc = new Scanner(System.in);
		int num = 0;
		
		
		System.out.print("양의 정수 : ");
		num = sc.nextInt();
		
		int[] arr = new int[num];
		int value = 1;
		
		for(int i = 0; i < arr.length ; i++) {
			arr[i] = value++;
		}
		
		for(int i = 0; i < arr.length ; i++) {
			System.out.print(arr[i] + " ");
		}
		
		
	}

	public void method3_teacher() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("쌤 버전 양의 정수 : ");
		int num = sc.nextInt();
		
		int[] arr = new int[num];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
		
		for(int v : arr) {
			System.out.print(v + " ");
		}
		
	}

	public void method4() {
		String [] strarr = {"사과", "귤", "포도", "복숭아", "참외"};
		
		System.out.println(strarr[1]);
		
	}

	public void method5() {
		Scanner sc = new Scanner(System.in);
		String str;
		
		System.out.print("문자열 : ");
		str = sc.nextLine();
		
		int strcount = str.length();

		
		char[] charArr = new char [strcount];
		
		for(int i = 0; i < strcount ; i++) {
			charArr[i] = str.charAt(i);
		}
		
		// 배열에 잘 들어갔는지 확인해보았습니다~
//		for(int i = 0; i < strcount ; i++) {
//			System.out.print(charArr[i] + " ");
//		}
	
		
		System.out.print("문자 : ");
		char ch = sc.nextLine().charAt(0);
		
		
		// 중복 갯수 찾아낼거야
		int countSame = 0;
		for(int i = 0 ; i < strcount ; i++) {
			if(ch == str.charAt(i)) {
				countSame += 1;
				continue;
			}
		}	
			
		// 인덱스 찾아낼거야 : 찾았는데 두개 출력은 못하겠다..
		int index = 0;

		for(int i = 0; i < strcount ; i++) {
			if(ch == charArr[i]) {
				index = i;
				if(i <= countSame) {
					break;
				}
			}
			
				}
		
		System.out.println(str + "에 " + ch + "가 존재하는 위치(인덱스) : " + index);
		System.out.println(ch + "개수 : " + countSame);	
	
	}
	
	public void method5_2ndTry() {
		Scanner sc = new Scanner(System.in);
		String str = "";
		
		System.out.print("문자열 : ");
		str = sc.nextLine();
		
		// 배열에 넣기
		char[] arr = new char[str.length()];
		
		for(int i = 0; i < arr.length ; i++) {
			arr[i] = str.charAt(i);
		}
		
		// 문자 입력
		System.out.print("문자 : ");
		char ch = sc.nextLine().charAt(0);
		
		// ch 찾기
		System.out.printf("%s에 %c가 존재하는 위치(인덱스) : ", str, ch);
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == ch) {
				System.out.print(i + " ");
			} else {
				continue;
			}
		}
		System.out.println();
		
		// ch 개수
		System.out.printf("%c 개수 : ", ch);
		int countCh = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == ch) {
				countCh += 1;
			} else {
				continue;
			}
		}
		System.out.println(countCh);
		
	}

	public void method5_teacher() {
		Scanner sc = new Scanner(System.in);
		
		String str = "";
		char ch = '\u0000';
		char[] arr = null;
		int count = 0;
		
		System.out.print("문자열 : ");
		str = sc.nextLine();
		
		System.out.print("문자 : ");
		ch = sc.nextLine().charAt(0);
		
		arr = new char[str.length()];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = str.charAt(i);
		}
		
		System.out.print(str + "에 " + ch + "가 존재하는 위치(인덱스) : ");
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == ch) {
				System.out.print(i + " ");
				
				count++;
			} 
		}
		
		System.out.println();
		System.out.println(ch + "개수 : " + count);
	}
	
	public void method5_3rdTry() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("문자열 : ");
		String str = sc.nextLine();
		
		char[] arr = new char[str.length()];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = str.charAt(i);
		}
		
		System.out.print("문자 : ");
		char ch = sc.nextLine().charAt(0);
		
		System.out.print(str + "에 " + ch + "가 존재하는 위치(인덱스) : ");
		int count = 0;
		for(int i = 0; i < arr.length; i++) {
			if(ch == arr[i]) {
				System.out.print(i + " ");
				
				count++;
			}
		}
		
		System.out.println();
		System.out.println(ch + " 개수 : " + count);
		
		
	}
	
	public void method6() {
		
		String[] arr = {"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"};
		Scanner sc = new Scanner(System.in);
		int index = 0;
		
		System.out.print("0 ~ 6 사이 숫자 입력 : ");
		index = sc.nextInt();
		
		if(index >=0 && index <= 6) {
			System.out.println(arr[index]);
		} else {
			System.out.println("잘못 입력하셨습니다.");
		}
	}

	public void method7() {
		Scanner sc = new Scanner(System.in);
		int num = 0;
		
		System.out.print("정수 : ");
		num = sc.nextInt();
		
		int[] arr = new int[num];
		
		// 인덱스마다 값 넣기
		for(int i = 0; i < arr.length; i++) {
			System.out.printf("배열 %d번째 인덱스에 넣을 값 : ", i);
			num = sc.nextInt();
			arr[i] = num;
		}
		
		// 값 출력하기
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		// 총합 구하기
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		System.out.println("총 합 : " + sum);
	}

	public void method7_2ndTry() {
		Scanner sc = new Scanner(System.in);
		int num = 0;
		int sum = 0;
		
		
		System.out.print("2트 정수 :");
		num = sc.nextInt();
		int[] arr = new int[num];
		
		for(int i = 0; i < arr.length; i++) {
			System.out.printf("배열 %d번째 인덱스에 넣을 값 : ", i);
			int arrs = sc.nextInt();
			arr[i] = arrs;
			sum += arr[i];
		}
		
		for(int v : arr) {
			System.out.print(v + " ");
		}
		System.out.println();
		
		System.out.println("총 합 : " + sum);
	}
	
	public void method8() {
		Scanner sc = new Scanner(System.in);
		int num;
		
		
		System.out.print("정수 : ");
		num = sc.nextInt();
		
		int[] arr = new int[num];
		
		while(true) {
			// 3이상 홀수정수 입력받아 인덱스 값 초기화
			if((num >= 3) && (num % 2 == 1)) {
				// num / 2 > 정수 부분까지 입력
				for(int i = 0; i < arr.length / 2 ; i++) {
					arr[i] = i + 1; 
				}
				// num / 2 < 정수
				for(int j = arr.length ; j > arr.length /2 ; j--) {
					arr[j-1] += 1;
				}
				
			
				// 출력
				for(int k = 0 ; k < arr.length ; k++) {
					System.out.print(arr[k] + ", ");
					if(k == arr.length) {
						System.out.print(arr[k]);
					}
				}
				break;
			} else {
				System.out.println("다시 입력하세요.");
				return;
			}
		}
		
	}

	public void method8_2ndTry() {
		Scanner sc = new Scanner(System.in);
		int num = 0;
		
		while(true) {
			System.out.print("정수 : ");
			num = sc.nextInt();
			
			if(num >= 3 && num % 2 == 1) {
				int[] arr = new int[num];
				for(int i = 0; i <= arr.length / 2 ; i++) {
					arr[i] = i + 1;
				}
				for(int i = num -1 ; i > arr.length / 2; i--) {
					arr[i] = num - i;
				}
				for(int v : arr) {
					System.out.print(v + " ");
				}
				System.out.println();
 			} else {
				System.out.println("다시 입력하세요.");
 			}
			
		}
	}

	public void method8_teacher() {
		Scanner sc = new Scanner(System.in);
		int size = 0;
		int count = 1;
		
		while(true) {
			System.out.print("쌤 버전 정수 : ");
			size = sc.nextInt();
			
			if(size >= 3 && size % 2 == 1) {
				int[] arr = new int[size];
				
				for(int i = 0; i < arr.length; i++) {
					arr[i] = count;
					
					if(i < arr.length / 2) {
						arr[i] = count++;
					} else {
						arr[i] = count--;
					}
				}
				
				for(int i = 0; i < arr.length; i++) {
					
					if(i < arr.length -1) {
						System.out.print(arr[i] + ", ");
					} else {
						System.out.print(arr[i]);
					}
				}
				
				break;
				
			} else {
				System.out.println("다시 입력하세요.");
			}	
		}
		
	}
	
	public void method8_3rdTry() {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.print("정수 : ");
			int num = sc.nextInt();

			if(num >= 3 && num % 2 ==1) {
				int[] arr = new int[num];
				
				int value = 1;
				for(int i = 0; i < arr.length; i++) {
					arr[i] = value;
					if(i < arr.length / 2) {
					
						value++;	
						
					} else {
						
						value--;	
					}
					
				}
				
//				int value = 1;
//				for(int i = 0; i <= arr.length / 2; i++) {
//					arr[i] = value++;
//				}
//				
//				value = num;
//				for(int i = arr.length -1 ; i > arr.length / 2; i--) {
//					arr[i] = num - i;
//				}
				
				
				for(int i = 0; i < arr.length; i++) {
					if(i < arr.length-1) {
						System.out.print(arr[i] + ", ");
					} else {
						System.out.print(arr[i]);
					}
				}
				System.out.println();
				
//				for(int i = 0; i < arr.length-1; i++) {
//					System.out.print(arr[i] + ", ");
//				}
//				
//				System.out.print(arr[num-1]);
//				break;
				
				
			} else {
				
				System.out.println("다시 입력하세요.");
			}
			
		}
	}
	
	public void method9() {
		int[] arr = new int[10];
		
		int random = 0;
		
		for(int i = 0 ; i < arr.length ; i++) {
			random = (int)(Math.random() * 10 + 1);
			arr[i] = random;
			random = 0;
		}
		
		for(int j = 0 ; j < arr.length ; j++) {
			System.out.print(arr[j] + " ");
		}
	
	}

	public void method9_teacher() {
		int[] arr = new int[10];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 10 + 1);
			
			System.out.print(arr[i] + " ");
		}
	}
	
	public void method10() {
		int[] arr = new int[10];
		int random = 0;
		int max = arr[0];
		int min = arr[0];
		
		for(int i = 0; i < arr.length ; i++) {
			random = (int)(Math.random() * 10 + 1);
			arr[i] = random;
			random = 0;
		}
		
		for(int i = 0; i < arr.length ; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		for(int num : arr) {
			if(num > max) {
				max = num;
			}
		}
		
		for(int num : arr) {
			if (num < min) {
				min = num;
			}
		}
		System.out.println("최대값 출력 : " + max);
		System.out.println("최소값 출력 : " + min);
		// 최소값이 안나오네
	}

	public void method10_teacher() {
		int[] arr = new int[10];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 10 + 1);
			System.out.print(arr[i] + " ");
		}
		
		Arrays.sort(arr);
		
		System.out.println();
		System.out.println("최대값 : " + arr[9]);
		System.out.println("최소값 : " + arr[0]);
		
	}

	public void method11() {
		int[] arr = new int[10];

		int max = arr[0];
		int min = arr[0];
		
		for(int i = 0; i < arr.length ; i++) {
			int random = (int)(Math.random() * 10 + 1);
			arr[i] = random;
		}
		
		for(int value : arr) {
			System.out.print(arr[value] + " ");
		}
	}
	
	public void method11_teacher() {
		int[] arr = new int[10];
		
		for(int i = 0; i < arr.length ; i++) {
			arr[i] = (int)(Math.random() * 10 + 1);
			
			for(int j = 0; j < i; j++) {
				if(arr[i] == arr[j]) {
					i--;
					
					break; 
				}
			}
		}
		
		for(int v : arr) {
			System.out.print(v + " ");
		}
	}
	
	public void method12() {
		int[] arr = new int[6];
		
		for(int i = 0; i < arr.length; i++) {
			int random = (int)(Math.random() * 10 + 1);
			arr[i] = random;
			// 중복 값 제거를 못하겟엉
		}
		
		
		Arrays.sort(arr);
		
		for(int v : arr) {
			System.out.print(v + " ");
		}
	}

	public void method12_2ndTry() {
		int[] arr = new int[6];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 45 + 1);
			
			for(int j = 0; j < i; j++) {
				if(arr[i] == arr[j]) {
					
					i--;
					
					break;
				}
			}
		}
		
		Arrays.sort(arr);
		
		for(int v : arr) {
			System.out.print(v + " ");
		}
		
	}
	
	public void method13() {
		String number = "";
		Scanner sc = new Scanner(System.in);
		char[] arr = new char[14];
		
		System.out.print("주민등록번호(- 포함) : ");
		number = sc.nextLine();
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = number.charAt(i);
		}
		
		for(int i = 0 ; i < 8; i++) {
			System.out.print(arr[i]);
		}
		
		for(int j = 8; j < arr.length; j++) {
			System.out.print("*");
		}
	}

	public void method10_2ndTry() {
		int[] arr = new int[10];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 10 + 1);
		}
		
		for(int v : arr) {
			System.out.print(v + " ");
		}
		System.out.println();
		
		Arrays.sort(arr);
		
		
		System.out.println("최대값 : " + arr[9]);
		System.out.println("최소값 : " + arr[0]);
		
	}

	public void method11_2ndTry() {
		int[] arr = new int[10];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 10 + 1);
			
			for(int j = 0; j < i; j++) {
				if(arr[i] == arr[j]) {
					
					i--;
					continue;
				}
			}
		}
		
		for(int v : arr) {
			System.out.print(v + " ");
		}
	}
	

}
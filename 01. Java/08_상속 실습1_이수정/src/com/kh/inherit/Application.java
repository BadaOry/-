package com.kh.inherit;

import java.util.Scanner;

import com.kh.inherit.practice.Employee;
import com.kh.inherit.practice.Student;

public class Application {

	public static void main(String[] args) {
// 1st Try는 약간 알듯말듯 맞춤 (Application이 어려웠음)
// 2nd Try
	
		Student[] student = new Student[3];
		
		student[0] = new Student("홍길동", 20, 178.2, 70.0, 1, "정시과");
		student[1] = new Student("김말똥", 21, 187.3, 80.0, 2, "경영");
		student[2] = new Student("강개순", 23, 167.0, 45.0, 4, "정통과");
		
		for(Student s : student) {
			System.out.println(s.information());
		}
	
		//===========================
		Employee[] employee = new Employee[10];
		Scanner sc = new Scanner(System.in);
		String name = null; 
		int age = 0; 
		double height = 0;
		double weight = 0; 
		int salary = 0; 
		String dept = null;
		char c = '\u0000';
		int count = 0;
		
		while(true) {
			System.out.print("이름 : ");
			name = sc.nextLine();
			
			System.out.print("나이 : ");
			age = sc.nextInt();
			
			System.out.print("신장 : ");
			height = sc.nextDouble();
			
			System.out.print("몸무게 : ");
			weight = sc.nextDouble();
			
			System.out.print("급여 : ");
			salary = sc.nextInt();
			sc.nextLine();
			
			System.out.print("부서 : ");
			dept = sc.nextLine();
			
	
//			for(int i = 0; i < employee.length; i++) {
//				employee[i] = new Employee(name, age, height, weight, salary, dept);
//			}
			
			employee[count] = new Employee(name, age, height, weight, salary, dept);
			count++;
			
			System.out.println("더 추가할래? y/n");
			c = sc.nextLine().charAt(0);
			
		
			if(c == 'Y' || c == 'y') {
				System.out.println("더 입력합니다");
				
				continue;
			} else if (c == 'n' || c == 'N') {
				System.out.println("끝낼게 ~~");
				
				for(int i = 0; i < employee.length; i++) {
					System.out.println(employee[i].information());
				}
				
				break;
			} else {
				System.out.println("원하지않는 문자 꺼져 !");
				
				return;
			}
		}
	}
}


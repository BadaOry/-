package com.kh.chap1.file;

import java.io.File;

public class A_File {
	public void method1() {
		// File 클래스 테스트
		
		// ▼ File io 임포트
		
		try {
			File file = new File("test.txt");	// heap 메모리상에만 존재하는 객체
			
			System.out.println("파일명 : " + file.getName());
			System.out.println("절대 경로 : " + file.getAbsolutePath());
			System.out.println("상대 경로 : " + file.getPath());
			System.out.println("파일 용량 : " + file.length());
			System.out.println("파일 존재 여부 : " + file.exists());
			System.out.println();
		
		
			file.createNewFile(); // 메소드를 실행해야만 실제 파일 생성,IO예외 처리가 안됨
			
//			// 존재하는 경로까지 지정해주면 해당 위치에 파일 생성
//			File file2 = new File("D:/test2.txt");
//			
//			file2.createNewFile();  // 메소드 실행하면 디드라이브에 생성됨
			
			
			// 만약 존재하지 않는 경로를 제시하면 IOException 발생
			File file2 = new File("D:/temp/test2.txt");
			
			file2.mkdirs();  // MakeDirectory. 제시한 경로에 폴더를 만드는 메소드
							 // 디드라이브에 temp라는 폴더가 없어도 에러 안남
							 // 왜냐면 test2.txt를 파일이 아니라 디렉토리 만드느걸로 인식하기 때문
							 // 그래서 temp 파일 안에 test2.txt 폴더를 만들어줌
//			
			// 디렉토리(=폴더) 만들기
			File tempDir = new File("D:/temp");
			
			tempDir.mkdir();
			
			File file3 = new File("D:/temp/test2.txt");
			
			file3.createNewFile();
			
			System.out.println("파일2 존재 여부 : " + file2.exists());
			System.out.println("파일3 존재 여부 : " + file3.exists());
			// isDirectory() : file이면 false 반환, directory면 true 반환
			System.out.println("tempDir.isDirectory() : " + tempDir.isDirectory());
			System.out.println("file3.isDirectory() : " + file3.isDirectory());
			// isFile() : file이면 true 반환, directory면 false 반환
			System.out.println("file2.isFile() : " + file2.isFile());
			System.out.println("file3.isFile() : " + file3.isFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
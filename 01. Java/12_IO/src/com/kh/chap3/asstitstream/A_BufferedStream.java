package com.kh.chap3.asstitstream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class A_BufferedStream {
	/*
	 * 보조 스트림
	 *   - 기반 스트림과 연결되어 여러가지 편리한 기능을 제공하는 스트림
	 *     (Input/OutputStream, Reader/Writer)
	 *   - 단독으로 외부 매체(파일, 네트워크, 키보드, 모니터 등)와 데이터를 직접 주고받을 수 없음
	 *   - 기반 스트림을 먼저 생성하고 보조 스트림을 연결해 주어야 함
	 *   
	 * 성능 향상 보조 스트림
	 *   - 기반 스트림의 속도를 향상시켜주는 기능
	 *   - 기반 스트림에 대한 레퍼런스 필요
	 *   - 버퍼라는 공간을 사용해서 속도 향상에 도움을 줌
	 *     : 버퍼라는 공간에 데이터를 쌓아놨다가 한 번에 입/출력 시킴
	 *   - 기반 스트림에서 제공하지 않는 메소드 (newLine(), readLine()) 들을 제공
	 * 
	 */
	
	public void fileSave() {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("c_buffer.txt", true))){
			// 한줄로 줄여서 try의 resource에 넣었습니다~
//			FileWriter fw = new FileWriter("c_buffer.txt");
//			BufferedWriter bw = new BufferedWriter(fw); // 상속관계, 매개변수 다형성 적용해서 fw가 bw에 들어감
			
			bw.write("안녕하세요!!\n");
			bw.write("아직도.. IO가 어렵나요...");
			bw.newLine(); // 기반 스트림에서 제공하지 않는 메소드, 개행할 때 사용
			bw.write("쏘 쌔드..");
			bw.write("쏘쏘 쌔드....");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void fileRead() {

		try(BufferedReader br = new BufferedReader(new FileReader("c_buffer.txt"))) {
//			// 괜히 두줄 쓰지말아요, 한 줄로 줄여서 리소스에 넣어버려요 !
//			FileReader fr = new FileReader("c_buffer.txt");
//			BufferedReader br = new BufferedReader(fr);
			
			String value = null;
			// 기반 스트림에서 제공하지 않는 메소드, 파일에서 한 줄 읽어올 때 사용
			while((value = br.readLine()) != null) {
				System.out.println(value);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
	}
}

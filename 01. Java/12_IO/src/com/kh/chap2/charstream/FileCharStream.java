package com.kh.chap2.charstream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCharStream {
	public void fileSave() {
		System.out.println("fileSave() 호출");
		// try-with-resource 문을 통해서 사용한 리소스를 자동으로 close() 시킴
		// try () 안에 리소스를 넣으면 됨
		// ▶ FileWriter를 사용해서 파일을 생성하고 데이터를 문자 단위로 저장함
		try(FileWriter fw = new FileWriter("b_char.txt", true)) {
			
			// write로 문자, 문자열, 문자 배열 모두 가능
			fw.write("IO 재밌나요?");
			fw.write("혼자서도 잘 할 수 있어요 ~!");
			fw.write('A');
			fw.write(' ');
			fw.write('\n');
			fw.write("혼자서도 잘 할 수 있어요 ~!");
//			fw.write(new char[] {'a', 'p', 'p', 'l','e'}, 1, 3); // 1번 인덱스부터 3개
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			System.out.println("fileSave() 호출 종료");
		}
	}
	
	public void fileRead() {
		System.out.println("fileRead() 호출");
		
		// FileReader를 사용해서 파일에서 데이터를 문자 단위로 읽어옴
		try (FileReader fr = new FileReader("b_char.txt")) {
			
			int value = 0;
			
			while((value = fr.read()) != -1) {
				System.out.print((char)value);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("fileRead() 호출 종료");
		}
		
	}
}

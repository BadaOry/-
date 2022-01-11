package com.kh.chap2.bytestream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileByteStream {
	public void fileSave() {
		// FileOutputStream을 사용해서 파일을 생성하고 데이터를 바이트 단위로 저장
		FileOutputStream fos = null;
		
		try {
			
			// write는 이어쓰는게 아니라 새로 만드는 거임
			// ▼ 만약 이어쓰고 싶으면 생성자 매개변수에 true 추가 
//			fos = new FileOutputStream("a_byte.dat");
			fos = new FileOutputStream("a_byte.dat", true); // 파일 이름과 파일자체를 넘겨주는 두가지 모두 가능
													  // FileNotFoundException을 던지고있어서 예외처리 필요

			fos.write(97/*아스키코드 값 a*/); 	// IOException도 던지는중 ▷ 예외처리 고  
			fos.write('b');					// b 저장
			fos.write(10); 					// 줄바꿈 문자
			fos.write(97);					// a 저장
//			fos.write('한'); 				// byte단위 입력이라 한글 입력하면 깨짐
			
			
			byte[] arr = {'a', 'b', 'c', 'd'};
			
			fos.write(arr);
			fos.write(arr, 1, 2);  // 1번 인덱스부터 2개 출력할거야
			fos.flush();
			
//			fos.close(); 	// 여기에 close 하면 위에서 write에서 에러 발생 시 실행 되지 않음
//			                // 따라서 반드시 실행하는 구문인 finally{} 안에서 close() 메소드 실행시켜야 함
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close(); // finally에 넣었더니 또 에러남
				             // close() IOException 가능성이 있는애라 try~catch 다시 고~
			} catch (IOException e) {
				e.printStackTrace();
			}	
			
		}
		
		
		
		}
		
	public void fileRead() {
		// FileInuptStream을 사용해서 파일에서 데이터를 바이트 단위로 읽어옴
		FileInputStream fis = null;
		
		try {
			// 파일이 존재하지 않으면 FileNotFoundExceoption 예외 발생
			fis = new FileInputStream("a_byte.dat");
			
			/*
			 * 파일로부터 데이터를 읽어올 때는 read() 메소드를 사용
			 * 하지만 실제 파일에는 얼마만큼의 데이터가 있는지 모름
			 * read() 메소드는 더이상 읽어올 데이터가 없으면 (=파일의 끝) -1 리턴
			 * 따라서, 반복문을 사용해 read() 메소드에서 -1이 리턴되기 전 까지 데이터를 읽어오면 됨
			 */
			
			
			byte[] arr = new byte[100];
			
			System.out.println((char)(fis.read())); // IOException 예외 발생 가능성 있음
										    		// try catch에 추가
													// (char) 추가해서 아스키코드 값으로 변환 ▶ a 읽음
			System.out.println(fis.read(arr));		// 읽어온 byte의 개수
			System.out.println(fis.read());  // 더이상 읽어올게 없어서 -1 리턴할거임
			System.out.println(Arrays.toString(arr));
//			// read 메소드 호출 시 맨 처음부터 값을 읽어오는데
//			// 다시 read를 호출하면 2번째 값부터 읽어옴
//			// 그래서 하나씩 뒤로가서 더이상 읽을 바이트값이 없으면(=파일의 끝) -1 리턴
			
			// 실행하면 이상한 값 출력함 (죄다 붙여서 출력)
			// 왜냐면 fis.read를 두 번 호출했기 때문에 정상적으로 값을 출력할 수 없음
//			int value = 0;
//			while(fis.read() != -1) {
//				System.out.print((char)fis.read());
//			}
			
			
			int value = 0;
			
			while((value = fis.read()) != -1) {
				System.out.print((char)value);
			}
		
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			try {
				// 스트림 다 이용했으니 닫아줘야 함
				fis.close();  // 다시 예외처리
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	
}

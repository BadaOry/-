package com.kh.chap3.asstitstream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 기본 타입 입출력 보조 스트림
 *   - 데이터를 읽고 쓸 때 byte 단위가 아닌 기본 자료형의 크기로 읽고 쓸 수 있음
 *   - 단, 기본 자료형의 크기가 다르기 때문에 출력한 데이터를 다시 읽어올 때는 
 *        동일한 순서로 읽어와야 함
 */
public class C_DataStream {
	public void fileSaveAndRead() {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("d_data.txt"));
				DataInputStream dis = new DataInputStream(new FileInputStream("d_data.txt"))) {
////			FileOutputStream fos = new FileOutputStream("d_data.txt");
//			DataOutputStream dos = new DataOutputStream(new FileOutputStream("d_data.txt"));
////			FileInputStream fis = new FileInputStream("d_data.txt");
//			DataInputStream dis = new DataInputStream(new FileInputStream("d_data.txt"));
			
			// 파일에 출력하기 : 문자열로 받는게 아니라 꺠져서 txt에 입력됨
			dos.writeUTF("무닌수");  // UTF : 매개값으로 문자열 받아 출력
			dos.writeInt(20);
			dos.writeChar('M');
			dos.writeBoolean(false);
			dos.writeDouble(3.141592);
			
			// 파일에서 값을 읽어오기
			while(true) {
				System.out.println("이름은 " + dis.readUTF() + 
						", 나이는 " + dis.readInt() + 
						", 성별은" + dis.readChar() +
						", 결혼은 " + dis.readBoolean() +
						", IQ는 " + dis.readDouble());
				
			// 여까지 쓰고 실행하면 EOFException (파일 끝났다) 에러 남 
			// ▶ catch 문 추가하여 해결 !
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e1) {
			System.out.println("파일 읽기 완료");
		} catch (IOException e2) {
			e2.printStackTrace();
		} 
		
	}
}
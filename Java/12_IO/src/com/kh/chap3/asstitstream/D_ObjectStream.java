package com.kh.chap3.asstitstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.kh.chap3.asstitstream.model.vo.Member;

public class D_ObjectStream {

	public void fileSave() {
		Member member = new Member("ismoon","1234","무닌수", 20, 'M');
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("e_object.dat"))) {
			System.out.println("객체 입력 전");

			oos.writeObject(member); // 영어 외에는 다 깨져서 보일 거임
			
			System.out.println("객체 입력 후");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void fileRead() {
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("e_object.dat"))) {
			System.out.println("객체 출력 전");
			
			Member member = (Member)(ois.readObject());  // 다운클래스 후 에러 처리
			
			System.out.println(member.toString());
			
			System.out.println("객체 출력 후");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	
	// ▼ 직접 해보시죠
	public void obejctsSave() {
		// Member 객체를 배열에 담아 반복문을 통해 파일("f_objects.dat")에 저장
		

		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("f_objects.dat"))) {

			Member[] arr = new Member("chyroagi", "1234", "ds", 10, 'd');
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void objectsRead() {
		// 파일에 젖아 된 Member 객체를 읽어와서 배열에 저장 후 객체의 toString() 메소드 출력	
	
	
	
	}
		
}


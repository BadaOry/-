package com.kh.chap3.asstitstream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B_ByteToCharStream {
	// 기반 스트림이 Byte 스트링이고 (System.in, Systen.out)
	// 보조 스트림이 Char 스트림인 경우 사용
	
	
	public void input()	{
		// System.in을 InputStreamReader를 통해서 문자 기반으로 변경하고 사용자로부터 입력 받고 출력하겠습니다
		// 아래와 같이 보조 스트림 여러개 사용도 가능
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//			// 한줄로 줄이고 리소스로 넣어버릴거얌
//			InputStreamReader isr = new InputStreamReader(System.in);
//			BufferedReader br = new BufferedReader(isr);
			
			String value = null;
			
			System.out.print("문자열 입력 : ");
			value = br.readLine();
			
			System.out.println("value : " + value);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void output() {
		// System.out을 OutputStreamWriter를 사용해 문자 기반으로 변경 후 콘솔에 출력
	
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			bw.write("넌 IO가 어렵니..?");
			bw.newLine();
			bw.write("앞으로 Oracle, JDBC, HTML, CSS, ... 도 있어.....");
			bw.newLine();
			bw.write("힘내..자....");
			bw.close();
			
		} catch (Exception e) {
		e.printStackTrace();
		}

	}
}

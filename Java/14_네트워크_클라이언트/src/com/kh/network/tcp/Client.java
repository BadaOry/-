package com.kh.network.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	/*
	 * 클라이언트용 TCP 소켓 프로그래밍 순서
	 *   1. 서버의 IP 주소와 서버가 정한 포트번호를 매개변수로 하여 클라이언트용 소켓 객체 생성
	 *   2. 서버와의 입출력 스트림 오픈
	 *   3. 보조 스트림을 통해 성능 개선
	 *   4. 스트림을 통해 읽고 쓰기
	 *   5. 통신 종료 (스트림, 소켓 닫기)
	 */
	public void clientStart() {
		// 1. 서버의 IP 주소와 서버가 정한 포트번호를 매개변수로 하여 클라이언트용 소켓 객체 생성
		int port = 30027;
		String serverIP = null;
		Socket socket = null;
		String str = null;
		String message = null;
		
		try {
			serverIP = InetAddress.getLocalHost().getHostAddress(); // 현재 PC의 IP주소를 가져홈
			
//			System.out.println(InetAddress.getLocalHost());  
//			System.out.println(serverIP);  
//			System.out.println(port); 
			socket = new Socket(serverIP, port);   // IOException 처리
												   // 서버와 연결이 실패한다면 null 반환
												   // socket이 만든어졌다는 건 서버와 연결이 가능한 상태가 되었다는 것
			     								   // ▶ 바로 서버와의 입출력 스트림 오픈 가능
			if(socket != null) {
		// 2. 서버와의 입출력 스트림 오픈
				 InputStream is = socket.getInputStream();	// 서버로부터 값을 입력받는 스트림
				 OutputStream os =  socket.getOutputStream();  // 서버로 값을 출력하는 스트림
				
		// 3. 보조 스트림 통해 성능 개선	
				 BufferedReader br = new BufferedReader(new InputStreamReader(is));
				 PrintWriter pw = new PrintWriter(os, true);
				 
		// 4. 스트림을 통해 읽고 쓰기
				 Scanner scanner = new Scanner(System.in); 
				 
				 do {
					 System.out.println("서버에 보낼 내용 : ");
					 str = scanner.nextLine();
					 
					 pw.println(str);
					 pw.flush();
					 
					 if(str == null || "exit".equals(str)) {
						 System.out.println("접속 종료..");
						 
						 break;
					 }
					 
					 message = br.readLine();
					 System.out.println("받은 메세지 : " + message);
					 
				 } while(true);
				 // 5. 통신 종료 (스트림, 소켓 닫기)
				 scanner.close();
				 pw.close();
				 br.close();
				 socket.close();
				 
			} else {
				System.out.println("연결 실패");
			}
			
		
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
	}
	

}

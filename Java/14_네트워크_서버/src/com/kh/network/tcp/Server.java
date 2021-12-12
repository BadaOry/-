package com.kh.network.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.kh.network.thread.Receiver;

public class Server {
	/*
	 * 서버용 TCP 소켓 프로그래밍 순서
	 *   1. 서버의 포트 번호 지정
	 *   2. 서버용 소켓 객체 생성
	 *   3. 클라이언트 쪽에서 접속 요청이 오길 기다림
	 *   4. 접속 요청이 오면 요청 수락 후 해당 클라이언트에 대한 소켓 객체 생성
	 *   5. 연결된 클라이언트와 입출력 스트림 생성
	 *   6. 보조 스트림을 통해 성능 개선
	 *   7. 스트림을 통해 데이터를 읽고 쓰기
	 *   8. 통신 종료
	 */
	public void serverStart() {
		// 1. 서버의 포트 번호 지정
		//   - 0 ~ 1023 시스템 포트 번호
		//   - 1024 ~ 65535의 포트 번호 사용을 추천
		int port = 30027;
		
		// 2. 서버용 소켓 객체 생성
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(port);  //IOException 처리 필요
			System.out.println("서버 시작...");
		
			while(true) {
				// 3. 클라이언트 쪽에서 접속 요청이 오길 기다림
				// 4. 접속 요청이 오면 요청 수락 후 해당 클라이언트에 대한 소켓 객체 생성
					// ▼ 변수 자동 추출하면 Socket으로 추출
					System.out.println("클라이언트 대기중..."); 
					Socket client = server.accept(); // 클라이언트의 접속 요청이 올때까지 서버가 블로킹(대기) 함
					 // 메소드 실행 되면 서버는 대기중, 클라이언트와 연결 후 socket을 리턴해주고 아래의 문장 수행
					 
					System.out.println("클라이언트 연결 됨...");
					
					Thread receiver = new Thread(new Receiver(client));
					
					receiver.start();
					
//				// 5. 연결된 클라이언트와 입출력 스트림을 생성함
//					InputStream is = client.getInputStream(); 	// 클라이언트로부터 값을 입력받을 스트림 
//					OutputStream os = client.getOutputStream();	// 클라이언트로 값을 출력하는 스트림
//				
//				// 6. 보조 스트림을 통해 성능 개선
//					BufferedReader br = new BufferedReader(new InputStreamReader(is));
//					PrintWriter pw = new PrintWriter(os, true);
//					
//				// 7. 스트림을 통해 데이터를 읽고 쓰기 
//					// client가 exit 누를 때 까지 계속 입력 & 출력할거임
//					while(true) {
//					String message = br.readLine(); // 클라이언트로부터 값 입력이 있을 때 까지 블로킹(대기) 됨 like Scanner
//						
//						if(message == null || message.equals("exit")) {
//							System.out.println("접속 종료");
//							
//							break;
//						} else {
//							System.out.println(client.getInetAddress().getHostAddress()  // 클라이언트 ip 찍어주는 과정
//									+ "가 보낸 메시지 : " + message);
//							
//							pw.println("메시지 받기 성공!");
//						}	
//					}
//					
//				//8. 통신 종료
//				//  클라이언트, 서버 등에서 사용한 리소스를 종료
//					br.close(); // InputStream도 같이 종료됨
//					pw.close(); // OutputStrean도 같이 종료됨
//					client.close();
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
}

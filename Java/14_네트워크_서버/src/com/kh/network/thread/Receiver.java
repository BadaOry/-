package com.kh.network.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Receiver implements Runnable {
// 5. 부터 작업을 실행할 소켓 객체 필요
// ▷ 클라이언트와 통신을 위한 Thread 구현
	// Thread에서 사용할 필드 지정
	private Socket client;
	private BufferedReader br;
	private PrintWriter pw;
	
	public Receiver() {
	}
	
	// 생성자에서 클라이언트 받아올 떄 br, pw도 같이 초기화할게요
	public Receiver(Socket client) {
		try {
			this.client = client;
			
			this.br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			this.pw = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	@Override
	public void run() {
		try {
		// 7. 스트림을 통해 데이터를 읽고 쓰기 
			// client가 exit 누를 때 까지 계속 입력 & 출력할거임
			while(true) {
			String message = br.readLine(); // 클라이언트로부터 값 입력이 있을 때 까지 블로킹(대기) 됨 like Scanner
				
				if(message == null || message.equals("exit")) {
					System.out.println("접속 종료");
					
					break;
				} else {
					System.out.println(client.getInetAddress().getHostAddress()  // 클라이언트 ip 찍어주는 과정
							+ "가 보낸 메시지 : " + message);
					
					pw.println("메시지 받기 성공!");
				
				}	
			}
		} catch (IOException e ) {
			e.printStackTrace();
		} finally {
			//8. 통신 종료
			//  클라이언트, 서버 등에서 사용한 리소스를 종료
				try {
					br.close(); // InputStream도 같이 종료됨
					pw.close(); // OutputStrean도 같이 종료됨
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}
}

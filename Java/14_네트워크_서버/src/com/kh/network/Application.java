package com.kh.network;

import com.kh.network.tcp.Server;

public class Application {

	public static void main(String[] args) {
		new Server().serverStart(); // 포트 생성을 위한 접근요청 창 뜸
	}
}

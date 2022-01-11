package com.kh.chap2;

import com.kh.chap2.bytestream.FileByteStream;
import com.kh.chap2.charstream.FileCharStream;

public class Application {

	public static void main(String[] args) {
//		new FileByteStream().fileSave(); // 실행하고 네비게이터 refresh 
										 // 지금 출력문 없어서 콘솔에 뭐 안나옴
//		
//		new FileByteStream().fileRead(); // 파일이 존재하지 않는 경우 에러남

//		new FileCharStream().fileSave();
		new FileCharStream().fileRead();
		
		
	}

}

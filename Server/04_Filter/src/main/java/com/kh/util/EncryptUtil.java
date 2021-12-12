package com.kh.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptUtil {
	/*
	 *  암호화의 방향성
	 *  	1) 단방향 : 암호회된 평문을 다시 복호화 할 수 없는 암호화 방식 (SHA2, MD5)
	 *  	2) 양방향 : 암호회된 평문을 다시 복호화 할 수 없는 암호화 방식
	 *  		2-1) 대칭키 방식    : 암/복호화에 사용하는 키가 동일 (AES, DES)
	 *  					       ▷ 속도가 빠르나, 키가 노출될 수 있음
	 *  		2-2) 비 대칭키 방식 : 암/복호화에 사용하는 키가 다름 (RSA)
	 *                             ▷ 속도가 느리나, 키가 노출되어도 복호화키가 달라서 괜찮음
	 *	SHE2(Secure Hash Algorithm 2)
	 *		1) SHA-256
	 *			: 임의의 길이 메세지를 256비트 (32바이트) 의 축약된 메시지로 만들어내는 해시 알고리즘
	 *		2) SHA-512
	 *			: 임의의 길이 메세지를 512비트 (64바이트) 의 축약된 메시지로 만들어내는 해시 알고리즘
	 *            * 암호화 해시 함수의 안정성을 강화하기 위해 추가로 스트레칭, 솔트 방법 등을 사용함
	 */
	
	// [ 단방향 암호화 실습 ] 
	//   : MessageDigest 객체에서 지원해주는
	//     algorithm(MD2, MD5, SHA, SHA-1, SHA-256, SHA-512 등)을 사용하여
	//     단방향 암호화 실습을 해볼 것임
	
	public static String oneWayEnc(String message, String algorithm) {
		byte[] digest;
		String encMessage = "";
		
		try {
			// ▼ MessageDigest
			//  : java.security 에서 제공하는 클래스
			//    ▷ 입력 값의 길이에 상관없이, 수학적 연산을 통해 항상 동일한 길이의 해시 값을 만들어줌
			MessageDigest md = MessageDigest.getInstance(algorithm);
			
			// ▼ update() 메소드 
			//   : 평문(message)을 byte[] 로 변환하여 MessageDigest 로 전달
			md.update(message.getBytes(Charset.forName("UTF-8")));
			
			// ▼ 암호화 형식에 맞는 byte[] 로 변경하여 값을 리턴
			digest = md.digest();
			
			// ▼ Base64
			//  : java.util 에서 제공하는 클래스
			//    ▷ byte 형식의 데이터를 문자열로 인코딩, 디코딩 해주는 객체
			encMessage = Base64.getEncoder().encodeToString(digest);
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		
		return encMessage;
	}
	
}

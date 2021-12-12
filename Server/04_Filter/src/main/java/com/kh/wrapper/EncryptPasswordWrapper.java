package com.kh.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.kh.util.EncryptUtil;

public class EncryptPasswordWrapper extends HttpServletRequestWrapper {

	public EncryptPasswordWrapper(HttpServletRequest request) {
		super(request);
	}

	// ▼ alt shift s 눌러서 Override/Implement method 를 통해, getParameter(String) 오버라이드
	@Override
	public String getParameter(String name) {
		String value = "";
		
		//   ▼ 클라이언트가 전달하는 값 중에 name 값이 password 인 매개값만 암호화 처리하고, 
		//     나머지는 정상적으로 리턴되도록 하는 조건문 설정
		if(name.equals("password")) {
			value = EncryptUtil.oneWayEnc(super.getParameter(name), "SHA-256");
		} else {
			value = super.getParameter(name);
		}
		return value;
	}

	
}

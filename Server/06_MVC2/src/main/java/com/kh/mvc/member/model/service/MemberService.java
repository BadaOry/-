package com.kh.mvc.member.model.service;

import java.sql.Connection;

import static com.kh.mvc.common.jdbc.JDBCTemplate.*;
import com.kh.mvc.member.model.dao.MemberDao;
import com.kh.mvc.member.model.vo.Member;


public class MemberService {
	
	// ▼ dao 객체에게 요청해서 dao를 통해 DB 에 실제로 접근할 것임
	private MemberDao dao = new MemberDao();
	
	public Member login(String id, String password) {
		
		// id 를 통해 DB에서 데이터를 찾아오면 member 객체 리턴, 
		//                      못찾으면 null 리턴하게 만들 것임
		
		
		// ▼ JDBC Template 작업 후 커넥션 객체를 가져오는 코드
		Connection connection = getConnection();
		
		Member member = dao.findMemberById(connection, id);
		
		close(connection);
		
		// ▼ 아이디가 맞으면 패스워드가 일치하는지 확인해볼 것임
		//   ▷ 만약if(member.getPassword()) 로 바로 비밀번호를 가져오면
		//      member 를 조회하지 못했을 경우 null을 리턴하고,
		//      nullPointException 생길거라 안됨
		//   ▶ member 가 null 이 아니도록 nullpointexception 체크 필요
		//     ▷ 패스워드가 일치하면 member 리턴, 불일치하면 null 리턴
	
		
		if(member != null && member.getPassword().equals(password)) {
			return member;
		} else {
			return null;
		}
	
	}

	public int save(Member member) {
		int result = 0;
		// ▼ insert 전에 Connection 객체를 가져오는 코드
		Connection connection = getConnection();
		
		// ▼ dao 가 실제로 매개값을 받아서 INSERT 진행 
		//   ▷ 파라미터에 connection 추가해서 DAO 에 insertMember 메소드 추가할 것
		result = dao.insertMember(connection, member);
		
		// ▼ 0 보다 크면 INSERT 작업이 성공했다는 뜻 ▷ 커밋해버림
		if(result > 0) {
			commit(connection);  // ◁ JDBCTemplate 을 import static 해서 클래스명 생략
		// ▼ 0 보다 크지 않으면 INSERT 작업이 실패했다는 뜻 ▷ 롤백해버림
		} else {
			rollback(connection);
		}
		
		// ▼ 비즈니스 로직이 끝나기 전 Connection 객체를 close 하는 코드
		close(connection);
		
		return result;
	}

}

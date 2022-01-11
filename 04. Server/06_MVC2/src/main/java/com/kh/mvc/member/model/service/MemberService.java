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

	// [ INSERT 와 UPDATE 를 구분하는 차이 ]
	// : no 를 넘겨주는지 아닌지 확인
	//   ▷ INSERT (회원가입) 는 no 가 아직 정해지지 않음 (no 존재 X)
 	//   ▷ UPDATE (회원정보수정) 는 no 값을 같이 넘겨줌  (no 존재 O)
	public int save(Member member) {
		int result = 0;
		// ▼ insert 전에 Connection 객체를 가져오는 코드
		Connection connection = getConnection();
		
		if(member.getNo() != 0) {
			result = dao.updateMember(connection, member);
		} else {
			result = dao.insertMember(connection, member);
		}
		
//		// ▼ dao 가 실제로 매개값을 받아서 INSERT 진행 
//		//   ▷ 파라미터에 connection 추가해서 DAO 에 insertMember 메소드 추가할 것
//		result = dao.insertMember(connection, member);
		
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
	
	public int delete(int no) {
		int result = 0;
		Connection connection = getConnection();
		
		// ▼ dao 가 실제로 매개값을 받아서 UPDATE 진행 
		//   : 세번째 매개값dp 바꾸려고 하는 데이터값을 넣음
		result = dao.updateMemberStatus(connection, no, "N");
		
		// ▼ 0 보다 크면 UPDATE 작업이 성공했다는 뜻 ▷ 커밋해버림
		if(result > 0) {
			commit(connection);  // ◁ JDBCTemplate 을 import static 해서 클래스명 생략
		// ▼ 0 보다 크지 않으면 UPDATE 작업이 실패했다는 뜻 ▷ 롤백해버림
		} else {
			rollback(connection);
		}
		
		// ▼ 비즈니스 로직이 끝나기 전 Connection 객체를 close 하는 코드
		close(connection);
		
		return result;
	}
	
	public int updatePassword(int no, String password) {
		int result = 0;
		Connection connection = getConnection();
		
		result = dao.updateMemberPassword(connection, no, password);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
	
		close(connection);
		
		return result;
	}


	public Boolean isDuplicateID(String id) {
		Connection connection = getConnection();
		
		Member member = dao.findMemberById(connection, id);
		
		// ▼ 위의 두 줄을 줄여서 이렇게도 쓸 수 있음
//		Member member = this.findMemberId():
		
		close(connection);
		
		// ▼ 위의 세줄을 몽땅 줄여버리는 한 줄의 코드
		//   : member 가져와서, 비교하고 넘기고 !
//		Member member = this.findMemberId(id);
		
		
		// ▼ Member 값이 null 이 아니면 id 가 중복 되었다는 뜻이고,
		//              null 이면 중복이 안되었다는 뜻 
		return member != null;
		
		// ▼ 아니면 줄이고 줄여서 위에거 다 지우고 아래 한줄로 바꿔도 됨
		//   : member 가져와서, 비교하고 넘기고 !
//		return this.findMemberById(id) != null;
	}
	
	// ▼ MemberService 내부에서만 쓰고,
	//   비즈니스 로직 처리용이 아니라 멤버 호출 용이라 private 로 선언했었음
	//   ▷ 근데 UpdateServlet 의 3. 회원정보 수정을 위해 public 으로 변경
	
	public Member findMemberById(String id) {
		Connection connection = getConnection();
		Member member = dao.findMemberById(connection, id);
		
		close(connection);
		
		return member;
	}






}

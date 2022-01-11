package com.kh.mvc.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.kh.mvc.common.jdbc.JDBCTemplate.*;
import com.kh.mvc.member.model.vo.Member;

public class MemberDao {

		// * JDBC Template 에서 가져오니까, Connection 이라 지워도 됨
	public Member findMemberById(Connection connection, String id) {
		// 0. 상단에 변수들을 미리 선언
		Member member = null;              // ▶ 찾는 데이터가 있으면 member 리턴, 없으면 null 리턴하게 만들 것임
//		Connection conn = null;            // ▶ DB 에 실제로 연결되는 객체
		PreparedStatement pstm = null;     // ▶ 쿼리문을 실행하고 결과값을 받아오는 객체 
		ResultSet rs = null;               // ▶ 쿼리문의 결과값을 얻어와서 담아주는 객체
		String query = "SELECT * FROM MEMBER WHERE ID=? AND STATUS='Y'"; // ▶ MEMBER를 조회하는 쿼리문
		
		try {
		/* Connection 매개변수 때문에 지워줘도 됨
		// 1. ﻿DriverManager 에 해당 DBMS Driver 등록
		//    ▷ 빨간줄 뜨면 try catch 문으로 예외처리 해주면 됨
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 2. ﻿해당 Driver 로 부터 Connection instance 획득 넘겨줌
			// ▼ getConnection 메소드의 매개변수로 url 정보, id, pwd 를 넘겨줌 ﻿
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "WEB", "WEB");
		*/
			
		// 3. ﻿Connection instance 로 부터 Statement instance 획득
			// ▼ 쿼리문을 수행할 수 있는 PreparedStatement 객체 리턴
			pstm = connection.prepareStatement(query);
			
			// ▼ 쿼리문의 ? 값 세팅 
			//   ▷ 쿼리문의 첫번째 ? 에 매개값으로 받은 id 값이 들어가고, 
			//      executeQuery 로 쿼리문 실행 후 리턴되는 ResultSet 객체에 결과값을 담아줌
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			
			// ▼ next () 메소드를 통해 여러 행에서 결과값이 있는지 찾아보는 코드
			if(rs.next()) {
				// ▼ 찾는 데이터가 있을 경우 Member 객체 생성하여 member 리턴
				member = new Member();
				
				// ▼ 한 행의 각 컬럼에 저장되는 데이터값들을 객체로 만들어서 저장
				//   ▷ 객체에는 컬럼에 대응되는 필드들을 만들고, getter setter 도 가져올 것임
				//   ▷ Member로 가서 필드들 만들고 롬복으로 생성자, 게터세터 만들어놓음
				member.setNo(rs.getInt("NO"));
				member.setId(rs.getString("ID"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setRole(rs.getString("ROLE"));
				member.setName(rs.getString("NAME"));
				member.setPhone(rs.getString("PHONE"));
				member.setEmail(rs.getString("EMAIL"));
				member.setAddress(rs.getString("ADDRESS"));
				member.setHobby(rs.getString("HOBBY"));
				member.setStatus(rs.getString("STATUS"));
				member.setEnrollDate(rs.getDate("ENROLL_DATE"));
				member.setModifyDate(rs.getDate("MODIFY_DATE"));
				
//				// ▼ 실험용 출력
//				System.out.println("ID : " + rs.getString("ID") + ", Password : " + rs.getString(3) + ", Name : " + rs.getString("NAME"));
			}
		/*} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */ 
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		/*	try {
		// 6. ﻿DB 로 부터 획득한 instance 들을 획득한 역순으로 반환
		//    ▷ 예외처리 안해줘서 빨간 줄 뜨니까 try catch 문으로 처리
				rs.close();
				pstm.close();
//				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		*/
		}
		
		return member;
	}

	public int insertMember(Connection connection, Member member) {
		// int 로 리턴해주므로 resultSet 객체는 필요 없음
		int result = 0;
		PreparedStatement pstmt = null;
		// ▼ 멤버 추가하는 쿼리문 
		String query = "INSERT INTO MEMBER VALUES(SEQ_UNO.NEXTVAL,?,?,DEFAULT,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
		
		try {
			// ▼ 쿼리문 객체 넘겨줌
			pstmt = connection.prepareStatement(query);
			
			// ▼ query 의  에 해당하는 값들을 다 넣어주는 중
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getHobby());
			
			// ▼ executeUpdate() : INSERT, UPDATE, DELETE 를 사용해주는 메소드
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// ▼ Connection 은 MemberService 에서 close 해주므로
			//   여기서는 pstmt 만 close 해주면 됨
			close(pstmt);
		}
		return result;
	}

	public int updateMember(Connection connection, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER SET NAME=?,PHONE=?,EMAIL=?,ADDRESS=?,HOBBY=?,MODIFY_DATE=SYSDATE WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			// ▼ ? 자리에 파라미터 값 넣어주기
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getHobby());
			pstmt.setInt(6, member.getNo());
			
			// ▼ 쿼리문 실행 후 업데이트 되는 행 개수를 result 에 담음
			result = pstmt.executeUpdate();
			
			
			/* 그런데 !! 수정 하고서도 바로 수정된 내용이 보이지 않고
			 *         로그아웃 하고 다시 재접속 해야 수정된 내용으로 보임
			 *  ▷ 로그인하는 시점에 DB 에 저장되어있는 Member 정보를 담아줌
			 *  ▷ 그런데 정보를 수정하고 안바뀌는 이유는, 
			 *     정보 수정한다 해서 로그인 했을때 가져온 Member 의 정보가 바뀌는 것은 아니기 때문임
			 *     (= 회원정보 수정이 이미 로그인했을 떄 session 에 저장된 Member 정보를 바꾸냐? 그건 아님 !)
			 *  ▶ 그래서 수정하자마자 변경되어 보이지는 않는 것임
			 *  ▷ 해결 방법 두가지
			 *     1) 회원 정보 수정 후, 정상적으로 수정되었으면
			 *        session 을 갱신
			 *     2) 로그인이 되면 myPageServlet 으로 가서 loginMember 의 아이디나 no 를
			 *        DB 에서 찾아서, Member 객체를 가져와서 request 영역에 담아 포워딩
			 *  ▶ 우리는 앞에서 loginMember 를 사용했기 때문에, 1) session 갱신 방법을 쓸 것임
			 */
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMemberStatus(Connection connection, int no, String status) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER SET STATUS=? WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateMemberPassword(Connection connection, int no, String password) {
		int result = 0; 
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER SET PASSWORD=? WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1,  password);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}

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

}

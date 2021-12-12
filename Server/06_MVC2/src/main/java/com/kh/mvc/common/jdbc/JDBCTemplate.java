package com.kh.mvc.common.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	public static Connection getConnection() {
		// [ 커넥션을 가져와주는 로직 ] 
		Connection connection = null;
		
		try {
			// ▼ 커넥션 가져오는 코드
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "WEB", "WEB");
		
			// ▼ INSERT, DELETE, UPDATE 는 자동 커밋이 되므로, 오토 커밋을 끄는 코드
			//  ex) insert1, insert2, update1 이 한개의 트랜젝션인 경우
			//      update1 을 실패하면 롤백해야하는데 insert 1, 2 는 커밋되어 롤백이 불가
			//      ▷ 트랜젝션 관리가 매우 어려움
			connection.setAutoCommit(false);
			
			// ▼ 따라서, 오토 커밋을 껐기 때문에 명시적으로 commit 과 rollback 을 명령해야 함
//			connection.commit();
//			connection.rollback();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}

	// commit, rollback, close 도 DAO 를 통하지않고 JDBC 템플릿의 메소드로 수행할 수 있도록 함
	
	public static void commit(Connection connection) {
		try {
			// ▼ 커넥션이 null 이 아니거나 닫혀있지 않은지 체크하는 조건문
			if(connection != null && connection.isClosed()) {
				connection.commit();	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection connection) {
		try {
			// ▼ 커넥션이 null 이 아니거나 닫혀있지 않은지 체크하는 조건문
			if(connection != null && connection.isClosed()) {
				connection.rollback();	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ▼ 메소드 오버로딩
	public static void close(Connection connection) {
		try {
			// ▼ 커넥션이 null 이 아니거나 닫혀있지 않은지 체크하는 조건문
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ▼ 메소드 오버로딩
	public static void close(Statement statement) {
		try {
			// ▼ statement 가  null 이 아니거나 닫혀있지 않은지 체크하는 조건문
			if(statement != null && !statement.isClosed()) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ▼ 메소드 오버로딩
	public static void close(ResultSet resultSet) {
		try {
			// ▼ resultSet 이  null 이 아니거나 닫혀있지 않은지 체크하는 조건문
			if(resultSet != null && !resultSet.isClosed()) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
